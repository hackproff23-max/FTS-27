package com.mopub.mobileads.util;

import org.apache.http.client.HttpClient;

/* JADX INFO: loaded from: classes.dex */
public class HttpClients {
    public static void safeShutdown(final HttpClient httpClient) {
        new Thread(new Runnable() { // from class: com.mopub.mobileads.util.HttpClients.1
            @Override // java.lang.Runnable
            public void run() {
                if (httpClient != null && httpClient.getConnectionManager() != null) {
                    httpClient.getConnectionManager().shutdown();
                }
            }
        }).start();
    }
}
