package com.mopub.common;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import com.mopub.common.logging.MoPubLog;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

/* JADX INFO: loaded from: classes.dex */
public class DownloadTask extends AsyncTask<HttpUriRequest, Void, DownloadResponse> {
    private final DownloadTaskListener mDownloadTaskListener;
    private String mUrl;

    public interface DownloadTaskListener {
        void onComplete(String str, DownloadResponse downloadResponse);
    }

    public DownloadTask(DownloadTaskListener downloadTaskListener) throws IllegalArgumentException {
        if (downloadTaskListener == null) {
            throw new IllegalArgumentException("DownloadTaskListener must not be null.");
        }
        this.mDownloadTaskListener = downloadTaskListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public DownloadResponse doInBackground(HttpUriRequest... httpUriRequests) {
        if (httpUriRequests == null || httpUriRequests.length == 0 || httpUriRequests[0] == null) {
            MoPubLog.d("Download task tried to execute null or empty url");
            return null;
        }
        HttpUriRequest httpUriRequest = httpUriRequests[0];
        this.mUrl = httpUriRequest.getURI().toString();
        AndroidHttpClient httpClient = null;
        try {
            httpClient = HttpClient.getHttpClient();
            HttpResponse httpResponse = httpClient.execute(httpUriRequest);
            DownloadResponse downloadResponse = new DownloadResponse(httpResponse);
        } catch (Exception e) {
            MoPubLog.d("Download task threw an internal exception", e);
            return null;
        } finally {
            if (httpClient != null) {
                httpClient.close();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(DownloadResponse downloadResponse) {
        if (isCancelled()) {
            onCancelled();
        } else {
            this.mDownloadTaskListener.onComplete(this.mUrl, downloadResponse);
        }
    }

    @Override // android.os.AsyncTask
    protected void onCancelled() {
        MoPubLog.d("DownloadTask was cancelled.");
    }
}
