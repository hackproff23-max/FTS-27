package com.mopub.nativeads;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import com.mopub.common.util.Intents;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
class UrlResolutionTask extends AsyncTask<String, Void, String> {
    private static final int REDIRECT_LIMIT = 10;

    @NonNull
    private final UrlResolutionListener mListener;

    interface UrlResolutionListener {
        void onFailure();

        void onSuccess(@NonNull String str);
    }

    public static void getResolvedUrl(@NonNull String urlString, @NonNull UrlResolutionListener listener) {
        UrlResolutionTask urlResolutionTask = new UrlResolutionTask(listener);
        try {
            AsyncTasks.safeExecuteOnExecutor(urlResolutionTask, urlString);
        } catch (Exception e) {
            MoPubLog.d("Failed to resolve url", e);
            listener.onFailure();
        }
    }

    UrlResolutionTask(@NonNull UrlResolutionListener listener) {
        this.mListener = listener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    @Nullable
    public String doInBackground(@Nullable String... urls) {
        if (urls == null || urls.length == 0) {
            return null;
        }
        String previousUrl = null;
        try {
            String locationUrl = urls[0];
            for (int redirectCount = 0; locationUrl != null && redirectCount < 10; redirectCount++) {
                if (Intents.isHttpUrl(locationUrl)) {
                    previousUrl = locationUrl;
                    locationUrl = getRedirectLocation(locationUrl);
                } else {
                    return locationUrl;
                }
            }
            String locationUrl2 = previousUrl;
            return locationUrl2;
        } catch (IOException e) {
            return null;
        }
    }

    @Nullable
    private String getRedirectLocation(@NonNull String urlString) throws IOException {
        String headerField;
        URL url = new URL(urlString);
        HttpURLConnection httpUrlConnection = null;
        try {
            httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setInstanceFollowRedirects(false);
            int responseCode = httpUrlConnection.getResponseCode();
            if (responseCode >= 300 && responseCode < 400) {
                headerField = httpUrlConnection.getHeaderField("Location");
            } else {
                headerField = null;
            }
            return headerField;
        } finally {
            if (httpUrlConnection != null) {
                httpUrlConnection.disconnect();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(@Nullable String resolvedUrl) {
        super.onPostExecute(resolvedUrl);
        if (isCancelled() || resolvedUrl == null) {
            onCancelled();
        } else {
            this.mListener.onSuccess(resolvedUrl);
        }
    }

    @Override // android.os.AsyncTask
    protected void onCancelled() {
        super.onCancelled();
        this.mListener.onFailure();
    }
}
