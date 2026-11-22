package com.millennialmedia.android;

import android.content.Context;
import android.text.TextUtils;
import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

/* JADX INFO: loaded from: classes.dex */
class PreCacheWorker extends Thread {
    private static final String TAG = "PreCacheWorker";
    private static boolean busy;
    private Context appContext;
    private DTOCachedVideo[] cachedVideos;
    private volatile boolean downloadedNewVideos = false;
    private String noVideosToCacheURL;

    static synchronized void preCacheVideos(DTOCachedVideo[] cachedVideos, Context context, String noVideosToCacheURL) {
        if (!busy) {
            busy = true;
            PreCacheWorker preCacheWorker = new PreCacheWorker(cachedVideos, context, noVideosToCacheURL);
            preCacheWorker.start();
        }
    }

    private PreCacheWorker(DTOCachedVideo[] cachedVideos, Context context, String noVideosToCacheURL) {
        this.cachedVideos = cachedVideos;
        this.noVideosToCacheURL = noVideosToCacheURL;
        this.appContext = context.getApplicationContext();
    }

    /* JADX WARN: Code duplicated, block: B:41:0x007f  */
    @Override // java.lang.Thread, java.lang.Runnable
    public synchronized void run() {
        try {
            if (this.cachedVideos != null) {
                DTOCachedVideo[] arr$ = this.cachedVideos;
                for (DTOCachedVideo cachedVideo : arr$) {
                    try {
                        HttpGetRequest httpGetRequest = new HttpGetRequest();
                        HttpResponse httpResponse = httpGetRequest.get(cachedVideo.url);
                        if (httpResponse == null) {
                            MMLog.d(TAG, "Pre cache worker: HTTP response is null");
                        } else {
                            HttpEntity httpEntity = httpResponse.getEntity();
                            if (httpEntity == null) {
                                MMLog.d(TAG, "Pre cache worker: Null HTTP entity");
                            } else if (httpEntity.getContentLength() == 0) {
                                MMLog.d(TAG, "Pre cache worker: Millennial ad return failed. Zero content length returned.");
                            } else {
                                handleContent(cachedVideo, httpEntity);
                            }
                        }
                    } catch (Exception e) {
                        MMLog.d(TAG, String.format("Pre cache worker HTTP error: %s", e));
                    }
                }
                synchronized (PreCacheWorker.class) {
                    busy = false;
                    if (!this.downloadedNewVideos && !TextUtils.isEmpty(this.noVideosToCacheURL) && this.cachedVideos == null) {
                        MMSDK.Event.logEvent(this.noVideosToCacheURL);
                    }
                }
            } else {
                synchronized (PreCacheWorker.class) {
                    busy = false;
                    if (!this.downloadedNewVideos) {
                        MMSDK.Event.logEvent(this.noVideosToCacheURL);
                    }
                }
            }
        } catch (Throwable th) {
            synchronized (PreCacheWorker.class) {
                busy = false;
                if (!this.downloadedNewVideos && !TextUtils.isEmpty(this.noVideosToCacheURL) && this.cachedVideos == null) {
                    MMSDK.Event.logEvent(this.noVideosToCacheURL);
                }
                throw th;
            }
        }
        throw th;
    }

    private void handleContent(DTOCachedVideo cachedVideo, HttpEntity httpEntity) {
        Header httpHeader = httpEntity.getContentType();
        if (httpHeader != null) {
            String contentType = httpHeader.getValue();
            if (contentType != null && contentType.equalsIgnoreCase("application/json")) {
                handleJson(cachedVideo, httpEntity);
            } else if (contentType != null && contentType.startsWith("video/")) {
                handleVideoFile(cachedVideo, httpEntity);
            }
        }
    }

    private void handleJson(final DTOCachedVideo cachedVideo, HttpEntity httpEntity) {
        VideoAd videoAd = null;
        try {
            String json = HttpGetRequest.convertStreamToString(httpEntity.getContent());
            if (!TextUtils.isEmpty(json)) {
                VideoAd videoAd2 = new VideoAd(json);
                videoAd = videoAd2;
            }
            if (videoAd != null && videoAd.isValid()) {
                try {
                    videoAd.downloadPriority = 1;
                    if (AdCache.startDownloadTask(this.appContext, null, videoAd, new AdCache.AdCacheTaskListener() { // from class: com.millennialmedia.android.PreCacheWorker.1
                        @Override // com.millennialmedia.android.AdCache.AdCacheTaskListener
                        public void downloadStart(CachedAd ad) {
                            MMSDK.Event.logEvent(cachedVideo.preCacheStartURL);
                        }

                        @Override // com.millennialmedia.android.AdCache.AdCacheTaskListener
                        public synchronized void downloadCompleted(CachedAd ad, boolean success) {
                            try {
                                if (success) {
                                    AdCache.save(PreCacheWorker.this.appContext, ad);
                                    PreCacheWorker.this.downloadedNewVideos = true;
                                    MMSDK.Event.logEvent(cachedVideo.preCacheCompleteURL);
                                } else {
                                    MMSDK.Event.logEvent(cachedVideo.preCacheFailedURL);
                                }
                                notify();
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    })) {
                        wait();
                    } else {
                        MMSDK.Event.logEvent(cachedVideo.preCacheStartURL);
                        MMSDK.Event.logEvent(cachedVideo.preCacheFailedURL);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    MMLog.e(TAG, "Pre cache worker interrupted: ", e);
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
            MMLog.d(TAG, "Pre cache worker: Millennial ad return failed. Invalid response data.");
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
            MMLog.d(TAG, "Pre cache worker: Millennial ad return failed. Invalid response data.");
        }
    }

    private void handleVideoFile(DTOCachedVideo cachedVideo, HttpEntity httpEntity) {
        if (!TextUtils.isEmpty(cachedVideo.videoFileId)) {
            MMSDK.Event.logEvent(cachedVideo.preCacheStartURL);
            if (AdCache.downloadComponentInternalCache(cachedVideo.url, cachedVideo.videoFileId + "video.dat", this.appContext)) {
                MMSDK.Event.logEvent(cachedVideo.preCacheCompleteURL);
            } else {
                MMSDK.Event.logEvent(cachedVideo.preCacheFailedURL);
            }
        }
    }
}
