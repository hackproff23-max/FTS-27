package com.millennialmedia.android;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/* JADX INFO: loaded from: classes.dex */
class HttpGetRequest {
    private static final String TAG = "HttpGetRequest";
    private HttpClient client;
    private HttpGet getRequest;

    HttpGetRequest() {
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 10000);
        this.client = new DefaultHttpClient(params);
        this.getRequest = new HttpGet();
    }

    HttpGetRequest(int timeout) {
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 10000);
        HttpConnectionParams.setSoTimeout(params, timeout);
        this.client = new DefaultHttpClient(params);
        this.getRequest = new HttpGet();
    }

    HttpResponse get(String url) throws Exception {
        HttpResponse response = null;
        if (!TextUtils.isEmpty(url)) {
            try {
                this.getRequest.setURI(new URI(url));
                response = this.client.execute(this.getRequest);
            } catch (Exception ex) {
                if (ex == null) {
                    return null;
                }
                MMLog.e(TAG, "Error connecting:", ex);
                return null;
            } catch (OutOfMemoryError e) {
                MMLog.e(TAG, "Out of memory!", e);
                return null;
            }
        }
        return response;
    }

    void trackConversion(String goalId, boolean isFirstLaunch, long installTime, TreeMap<String, String> extraParams) throws Exception {
        int i = isFirstLaunch ? 1 : 0;
        try {
            StringBuilder urlBuilder = new StringBuilder("http://cvt.mydas.mobi/handleConversion?firstlaunch=" + i);
            if (goalId != null) {
                urlBuilder.append("&goalId=" + goalId);
            }
            if (installTime > 0) {
                urlBuilder.append("&installtime=" + (installTime / 1000));
            }
            if (extraParams != null) {
                for (Map.Entry<String, String> entry : extraParams.entrySet()) {
                    urlBuilder.append(String.format("&%s=%s", entry.getKey(), URLEncoder.encode(entry.getValue(), "UTF-8")));
                }
            }
            String url = urlBuilder.toString();
            MMLog.d(TAG, String.format("Sending conversion tracker report: %s", url));
            this.getRequest.setURI(new URI(url));
            HttpResponse response = this.client.execute(this.getRequest);
            if (response.getStatusLine().getStatusCode() == 200) {
                MMLog.v(TAG, String.format("Successful conversion tracking event: %d", Integer.valueOf(response.getStatusLine().getStatusCode())));
            } else {
                MMLog.e(TAG, String.format("Conversion tracking error: %d", Integer.valueOf(response.getStatusLine().getStatusCode())));
            }
        } catch (IOException e) {
            MMLog.e(TAG, "Conversion tracking error: ", e);
        }
    }

    static String convertStreamToString(InputStream is) throws IOException {
        BufferedReader reader = null;
        try {
            if (is == null) {
                throw new IOException("Stream is null.");
            }
            try {
                BufferedReader reader2 = new BufferedReader(new InputStreamReader(is), 4096);
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String line = reader2.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line + "\n");
                    }
                    if (reader2 != null) {
                        try {
                            reader2.close();
                        } catch (IOException e) {
                            MMLog.e(TAG, "Error closing file", e);
                        }
                    }
                    return sb.toString();
                } catch (OutOfMemoryError e2) {
                    e = e2;
                    MMLog.e(TAG, "Out of Memeory: ", e);
                    throw new IOException("Out of memory.");
                } catch (Throwable th) {
                    th = th;
                    reader = reader2;
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e3) {
                            MMLog.e(TAG, "Error closing file", e3);
                        }
                    }
                    throw th;
                }
            } catch (OutOfMemoryError e4) {
                e = e4;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    static void log(final String[] urls) {
        if (urls != null && urls.length > 0) {
            Utils.ThreadUtils.execute(new Runnable() { // from class: com.millennialmedia.android.HttpGetRequest.1
                @Override // java.lang.Runnable
                public void run() {
                    String[] arr$ = urls;
                    for (String url : arr$) {
                        MMLog.v(HttpGetRequest.TAG, String.format("Logging event to: %s", url));
                        try {
                            HttpGetRequest getRequest = new HttpGetRequest();
                            getRequest.get(url);
                        } catch (Exception e) {
                            MMLog.e(HttpGetRequest.TAG, "Logging request failed.", e);
                        }
                    }
                }
            });
        }
    }
}
