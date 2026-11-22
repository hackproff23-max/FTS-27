package com.mopub.mobileads;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import com.mopub.common.CacheService;
import com.mopub.common.HttpClient;
import com.mopub.common.logging.MoPubLog;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

/* JADX INFO: loaded from: classes.dex */
public class VastVideoDownloadTask extends AsyncTask<String, Void, Boolean> {
    private static final int MAX_VIDEO_SIZE = 26214400;
    private final VastVideoDownloadTaskListener mVastVideoDownloadTaskListener;

    public interface VastVideoDownloadTaskListener {
        void onComplete(boolean z);
    }

    public VastVideoDownloadTask(VastVideoDownloadTaskListener listener) {
        this.mVastVideoDownloadTaskListener = listener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Boolean doInBackground(String... params) {
        if (params == null || params[0] == null) {
            return false;
        }
        String videoUrl = params[0];
        AndroidHttpClient httpClient = null;
        try {
            try {
                AndroidHttpClient httpClient2 = HttpClient.getHttpClient();
                HttpGet httpget = HttpClient.initializeHttpGet(videoUrl);
                HttpResponse response = httpClient2.execute(httpget);
                if (response == null || response.getEntity() == null) {
                    throw new IOException("Obtained null response from video url: " + videoUrl);
                }
                if (response.getEntity().getContentLength() > 26214400) {
                    throw new IOException("Video exceeded max download size");
                }
                InputStream inputStream = new BufferedInputStream(response.getEntity().getContent());
                boolean diskPutResult = CacheService.putToDiskCache(videoUrl, inputStream);
                inputStream.close();
                Boolean boolValueOf = Boolean.valueOf(diskPutResult);
                if (httpClient2 == null) {
                    return boolValueOf;
                }
                httpClient2.close();
                return boolValueOf;
            } catch (Exception e) {
                MoPubLog.d("Failed to download video: " + e.getMessage());
                if (0 == 0) {
                    return false;
                }
                httpClient.close();
                return false;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                httpClient.close();
            }
            throw th;
        }
    }

    @Override // android.os.AsyncTask
    protected void onCancelled() {
        onPostExecute((Boolean) false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Boolean success) {
        if (this.mVastVideoDownloadTaskListener != null) {
            this.mVastVideoDownloadTaskListener.onComplete(success.booleanValue());
        }
    }
}
