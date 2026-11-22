package com.mopub.mobileads.factories;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/* JADX INFO: loaded from: classes.dex */
public class HttpClientFactory {
    public static final int SOCKET_SIZE = 8192;
    private static HttpClientFactory instance = new HttpClientFactory();

    @Deprecated
    public static void setInstance(HttpClientFactory factory) {
        instance = factory;
    }

    public static DefaultHttpClient create(int timeoutMilliseconds) {
        return instance.internalCreate(timeoutMilliseconds);
    }

    public static DefaultHttpClient create() {
        return instance.internalCreate(0);
    }

    protected DefaultHttpClient internalCreate(int timeoutMilliseconds) {
        HttpParams httpParameters = new BasicHttpParams();
        if (timeoutMilliseconds > 0) {
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutMilliseconds);
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutMilliseconds);
        }
        HttpConnectionParams.setSocketBufferSize(httpParameters, 8192);
        return new DefaultHttpClient(httpParameters);
    }
}
