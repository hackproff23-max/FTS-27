package com.millennialmedia.android;

import android.app.Activity;
import android.content.Context;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
class BridgeMMCachedVideo extends MMJSObject implements AdCache.AdCacheTaskListener {
    private static final String AVAILABLE_CACHED_VIDEOS = "availableCachedVideos";
    private static final String CACHE_VIDEO = "cacheVideo";
    private static final String END_VIDEO = "endVideo";
    private static final String PAUSE_VIDEO = "pauseVideo";
    private static final String PLAY_CACHED_VIDEO = "playCachedVideo";
    private static final String PLAY_VIDEO = "playVideo";
    private static final String RESTART_VIDEO = "restartVideo";
    private static final String TAG = "BridgeMMCachedVideo";
    private static final String VIDEO_ID_EXISTS = "videoIdExists";
    private boolean success;

    BridgeMMCachedVideo() {
    }

    @Deprecated
    public MMJSResponse videoIdExists(Map<String, String> arguments) {
        VideoAd ad;
        Context context = this.contextRef.get();
        String videoId = arguments.get("videoId");
        if (videoId == null || context == null || (ad = (VideoAd) AdCache.load(context, videoId)) == null || !ad.isOnDisk(context) || ad.isExpired()) {
            return null;
        }
        return MMJSResponse.responseWithSuccess(videoId);
    }

    public MMJSResponse availableCachedVideos(Map<String, String> arguments) {
        final Context context = this.contextRef.get();
        if (context == null) {
            return null;
        }
        final JSONArray array = new JSONArray();
        AdCache.iterateCachedAds(context, 2, new AdCache.Iterator() { // from class: com.millennialmedia.android.BridgeMMCachedVideo.1
            @Override // com.millennialmedia.android.AdCache.Iterator
            boolean callback(CachedAd ad) {
                if ((ad instanceof VideoAd) && ad.isOnDisk(context) && !ad.isExpired()) {
                    array.put(ad.getId());
                    return true;
                }
                return true;
            }
        });
        MMJSResponse response = new MMJSResponse();
        response.result = 1;
        response.response = array;
        return response;
    }

    public MMJSResponse playCachedVideo(Map<String, String> arguments) {
        VideoAd ad;
        Context context = this.contextRef.get();
        String videoId = arguments.get("videoId");
        if (videoId == null || context == null || (ad = (VideoAd) AdCache.load(context, videoId)) == null || !ad.canShow(context, null, false)) {
            return null;
        }
        long adImplId = getAdImplId(arguments.get("PROPERTY_EXPANDING"));
        ad.show(context, adImplId);
        return MMJSResponse.responseWithSuccess(String.format("Playing Video(%s)", videoId));
    }

    @Override // com.millennialmedia.android.AdCache.AdCacheTaskListener
    public void downloadCompleted(CachedAd ad, boolean success) {
        synchronized (this) {
            Context context = this.contextRef.get();
            if (success && context != null) {
                AdCache.save(context, ad);
            }
            this.success = success;
            notify();
        }
    }

    public synchronized MMJSResponse cacheVideo(Map<String, String> arguments) {
        MMJSResponse mMJSResponseResponseWithSuccess = null;
        synchronized (this) {
            try {
                Context context = this.contextRef.get();
                String url = arguments.get("url");
                if (url != null && context != null) {
                    try {
                        HttpResponse httpResponse = new HttpGetRequest().get(url);
                        if (httpResponse == null) {
                            MMLog.i(TAG, "HTTP response is null");
                        } else {
                            HttpEntity httpEntity = httpResponse.getEntity();
                            if (httpEntity == null) {
                                MMLog.d(TAG, "Null HTTP entity");
                            } else if (httpEntity.getContentLength() == 0) {
                                MMLog.d(TAG, "Millennial ad return failed. Zero content length returned.");
                            } else {
                                Header httpHeader = httpEntity.getContentType();
                                if (httpHeader != null && httpHeader.getValue() != null && httpHeader.getValue().equalsIgnoreCase("application/json")) {
                                    try {
                                        String json = HttpGetRequest.convertStreamToString(httpEntity.getContent());
                                        VideoAd videoAd = new VideoAd(json);
                                        if (videoAd != null && videoAd.isValid()) {
                                            videoAd.downloadPriority = 3;
                                            if (AdCache.startDownloadTask(context, null, videoAd, this)) {
                                                try {
                                                    try {
                                                        wait();
                                                        if (this.success) {
                                                            mMJSResponseResponseWithSuccess = MMJSResponse.responseWithSuccess(String.format("Cached video(%s)", url));
                                                        }
                                                    } catch (InterruptedException e) {
                                                        MMLog.e(TAG, "Caching interrupted: ", e);
                                                        notify();
                                                    }
                                                } finally {
                                                    notify();
                                                }
                                            } else {
                                                mMJSResponseResponseWithSuccess = MMJSResponse.responseWithError(String.format("Unable to start download for Cached video(%s)", url));
                                            }
                                        }
                                    } catch (IOException e1) {
                                        MMLog.e(TAG, "Millennial ad return failed. Invalid response data.", e1);
                                    } catch (IllegalStateException e2) {
                                        MMLog.e(TAG, "Millennial ad return failed. Invalid response data.", e2);
                                    }
                                }
                            }
                        }
                    } catch (Exception e3) {
                        MMLog.e(TAG, "HTTP error: ", e3);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return mMJSResponseResponseWithSuccess;
    }

    public MMJSResponse playVideo(Map<String, String> arguments) {
        final VideoPlayerActivity vpa = getVPA();
        if (vpa != null) {
            return runOnUiThreadFuture(new Callable<MMJSResponse>() { // from class: com.millennialmedia.android.BridgeMMCachedVideo.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public MMJSResponse call() {
                    vpa.resumeVideo();
                    return MMJSResponse.responseWithSuccess();
                }
            });
        }
        return null;
    }

    public MMJSResponse endVideo(Map<String, String> arguments) {
        final VideoPlayerActivity vpa = getVPA();
        if (vpa != null) {
            return runOnUiThreadFuture(new Callable<MMJSResponse>() { // from class: com.millennialmedia.android.BridgeMMCachedVideo.3
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public MMJSResponse call() {
                    vpa.endVideo();
                    return MMJSResponse.responseWithSuccess();
                }
            });
        }
        return null;
    }

    public MMJSResponse pauseVideo(Map<String, String> arguments) {
        final VideoPlayerActivity vpa = getVPA();
        if (vpa != null) {
            return runOnUiThreadFuture(new Callable<MMJSResponse>() { // from class: com.millennialmedia.android.BridgeMMCachedVideo.4
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public MMJSResponse call() {
                    vpa.pauseVideoByUser();
                    return MMJSResponse.responseWithSuccess();
                }
            });
        }
        return null;
    }

    public MMJSResponse restartVideo(Map<String, String> arguments) {
        final VideoPlayerActivity vpa = getVPA();
        if (vpa != null) {
            return runOnUiThreadFuture(new Callable<MMJSResponse>() { // from class: com.millennialmedia.android.BridgeMMCachedVideo.5
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public MMJSResponse call() {
                    vpa.restartVideo();
                    return MMJSResponse.responseWithSuccess();
                }
            });
        }
        return null;
    }

    private VideoPlayerActivity getVPA() {
        MMWebView webView;
        Activity activity;
        if (this.mmWebViewRef == null || this.mmWebViewRef.get() == null || !(this.mmWebViewRef.get().getActivity() instanceof MMActivity) || (webView = this.mmWebViewRef.get()) == null || (activity = webView.getActivity()) == null || !(activity instanceof MMActivity)) {
            return null;
        }
        MMActivity mmActivity = (MMActivity) activity;
        if (mmActivity.getWrappedActivity() == null || !(mmActivity.getWrappedActivity() instanceof VideoPlayerActivity)) {
            return null;
        }
        VideoPlayerActivity vpa = (VideoPlayerActivity) mmActivity.getWrappedActivity();
        return vpa;
    }

    @Override // com.millennialmedia.android.AdCache.AdCacheTaskListener
    public void downloadStart(CachedAd ad) {
    }

    @Override // com.millennialmedia.android.MMJSObject
    MMJSResponse executeCommand(String name, Map<String, String> arguments) {
        if (AVAILABLE_CACHED_VIDEOS.equals(name)) {
            MMJSResponse response = availableCachedVideos(arguments);
            return response;
        }
        if ("cacheVideo".equals(name)) {
            MMJSResponse response2 = cacheVideo(arguments);
            return response2;
        }
        if (END_VIDEO.equals(name)) {
            MMJSResponse response3 = endVideo(arguments);
            return response3;
        }
        if (PAUSE_VIDEO.equals(name)) {
            MMJSResponse response4 = pauseVideo(arguments);
            return response4;
        }
        if (PLAY_CACHED_VIDEO.equals(name)) {
            MMJSResponse response5 = playCachedVideo(arguments);
            return response5;
        }
        if ("playVideo".equals(name)) {
            MMJSResponse response6 = playVideo(arguments);
            return response6;
        }
        if (RESTART_VIDEO.equals(name)) {
            MMJSResponse response7 = restartVideo(arguments);
            return response7;
        }
        if (!VIDEO_ID_EXISTS.equals(name)) {
            return null;
        }
        MMJSResponse response8 = videoIdExists(arguments);
        return response8;
    }
}
