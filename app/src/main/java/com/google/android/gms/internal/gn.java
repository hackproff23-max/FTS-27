package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
@ey
public class gn {
    public static final a<Void> wN = new a() { // from class: com.google.android.gms.internal.gn.1
        @Override // com.google.android.gms.internal.gn.a
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public Void b(InputStream inputStream) {
            return null;
        }

        @Override // com.google.android.gms.internal.gn.a
        /* JADX INFO: renamed from: dz, reason: merged with bridge method [inline-methods] */
        public Void cQ() {
            return null;
        }
    };

    public interface a<T> {
        T b(InputStream inputStream);

        T cQ();
    }

    public <T> Future<T> a(final String str, final a<T> aVar) {
        return gh.submit(new Callable<T>() { // from class: com.google.android.gms.internal.gn.2
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r1v0 */
            /* JADX WARN: Type inference failed for: r1v1 */
            /* JADX WARN: Type inference failed for: r1v11 */
            /* JADX WARN: Type inference failed for: r1v12, types: [int] */
            /* JADX WARN: Type inference failed for: r1v16 */
            /* JADX WARN: Type inference failed for: r1v17 */
            /* JADX WARN: Type inference failed for: r1v2, types: [java.net.HttpURLConnection] */
            /* JADX WARN: Type inference failed for: r1v3, types: [java.net.HttpURLConnection] */
            /* JADX WARN: Type inference failed for: r1v4, types: [java.net.HttpURLConnection] */
            /* JADX WARN: Type inference failed for: r1v5 */
            /* JADX WARN: Type inference failed for: r1v7 */
            /* JADX WARN: Type inference failed for: r1v9 */
            @Override // java.util.concurrent.Callable
            public T call() throws Throwable {
                ?? responseCode = 0;
                responseCode = 0;
                responseCode = 0;
                try {
                    try {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                        try {
                            httpURLConnection.connect();
                            responseCode = httpURLConnection.getResponseCode();
                            if (responseCode >= 200 && responseCode <= 299) {
                                T t = (T) aVar.b(httpURLConnection.getInputStream());
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                                return t;
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                        } catch (MalformedURLException e) {
                            responseCode = httpURLConnection;
                            e = e;
                            gr.d("Error making HTTP request.", e);
                            if (responseCode != 0) {
                                responseCode.disconnect();
                            }
                        } catch (IOException e2) {
                            responseCode = httpURLConnection;
                            e = e2;
                            gr.d("Error making HTTP request.", e);
                            if (responseCode != 0) {
                                responseCode.disconnect();
                            }
                        } catch (Throwable th) {
                            responseCode = httpURLConnection;
                            th = th;
                            if (responseCode != 0) {
                                responseCode.disconnect();
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (MalformedURLException e3) {
                    e = e3;
                } catch (IOException e4) {
                    e = e4;
                }
                return (T) aVar.cQ();
            }
        });
    }
}
