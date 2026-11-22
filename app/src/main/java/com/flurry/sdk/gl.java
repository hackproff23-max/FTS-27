package com.flurry.sdk;

import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class gl extends hr {
    private static final String a = gl.class.getSimpleName();
    private String b;
    private a c;
    private c j;
    private HttpURLConnection k;
    private boolean l;
    private boolean m;
    private Exception n;
    private int d = 10000;
    private int e = 15000;
    private boolean f = true;
    private final fu<String, String> g = new fu<>();
    private int o = -1;
    private final fu<String, String> p = new fu<>();
    private final Object q = new Object();

    public interface c {
        void a(gl glVar);

        void a(gl glVar, InputStream inputStream) throws Exception;

        void a(gl glVar, OutputStream outputStream) throws Exception;
    }

    public static class b implements c {
        @Override // com.flurry.sdk.gl.c
        public void a(gl glVar, OutputStream outputStream) throws Exception {
        }

        @Override // com.flurry.sdk.gl.c
        public void a(gl glVar, InputStream inputStream) throws Exception {
        }

        @Override // com.flurry.sdk.gl.c
        public void a(gl glVar) {
        }
    }

    public enum a {
        kUnknown,
        kGet,
        kPost,
        kPut,
        kDelete,
        kHead;

        @Override // java.lang.Enum
        public String toString() {
            switch (this) {
                case kPost:
                    return UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_POST;
                case kPut:
                    return "PUT";
                case kDelete:
                    return "DELETE";
                case kHead:
                    return "HEAD";
                case kGet:
                    return UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_GET;
                default:
                    return null;
            }
        }
    }

    public void a(String str) {
        this.b = str;
    }

    public String a() {
        return this.b;
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public void a(String str, String str2) {
        this.g.a(str, str2);
    }

    public void a(c cVar) {
        this.j = cVar;
    }

    public boolean b() {
        boolean z;
        synchronized (this.q) {
            z = this.m;
        }
        return z;
    }

    public boolean c() {
        return !f() && d();
    }

    public boolean d() {
        return this.o >= 200 && this.o < 400;
    }

    public int e() {
        return this.o;
    }

    public boolean f() {
        return this.n != null;
    }

    public List<String> b(String str) {
        if (str == null) {
            return null;
        }
        return this.p.a(str);
    }

    public void g() {
        synchronized (this.q) {
            this.m = true;
        }
        p();
    }

    @Override // com.flurry.sdk.hq
    public void safeRun() {
        try {
            if (this.b == null) {
                return;
            }
            if (!fj.a().c()) {
                gd.a(3, a, "Network not available, aborting http request: " + this.b);
                return;
            }
            if (this.c == null || a.kUnknown.equals(this.c)) {
                this.c = a.kGet;
            }
            m();
            gd.a(4, a, "HTTP status: " + this.o + " for url: " + this.b);
        } catch (Exception e) {
            gd.a(4, a, "HTTP status: " + this.o + " for url: " + this.b);
            gd.a(3, a, "Exception during http request: " + this.b, e);
            this.n = e;
        } finally {
            n();
        }
    }

    @Override // com.flurry.sdk.hr
    public void h() {
        g();
    }

    private void m() throws Exception {
        BufferedOutputStream bufferedOutputStream;
        InputStream inputStream;
        Closeable closeable = null;
        if (this.m) {
            return;
        }
        this.b = hp.a(this.b);
        try {
            this.k = (HttpURLConnection) new URL(this.b).openConnection();
            this.k.setConnectTimeout(this.d);
            this.k.setReadTimeout(this.e);
            this.k.setRequestMethod(this.c.toString());
            this.k.setInstanceFollowRedirects(this.f);
            this.k.setDoOutput(a.kPost.equals(this.c));
            this.k.setDoInput(true);
            for (Map.Entry<String, String> entry : this.g.b()) {
                this.k.addRequestProperty(entry.getKey(), entry.getValue());
            }
            if (!a.kGet.equals(this.c) && !a.kPost.equals(this.c)) {
                this.k.setRequestProperty("Accept-Encoding", "");
            }
            if (this.m) {
                o();
                return;
            }
            if (a.kPost.equals(this.c)) {
                try {
                    OutputStream outputStream = this.k.getOutputStream();
                    try {
                        bufferedOutputStream = new BufferedOutputStream(outputStream);
                        try {
                            a(bufferedOutputStream);
                            hp.a(bufferedOutputStream);
                            hp.a(outputStream);
                        } catch (Throwable th) {
                            th = th;
                            closeable = outputStream;
                            hp.a(bufferedOutputStream);
                            hp.a(closeable);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedOutputStream = null;
                        closeable = outputStream;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedOutputStream = null;
                }
            }
            this.o = this.k.getResponseCode();
            for (Map.Entry<String, List<String>> entry2 : this.k.getHeaderFields().entrySet()) {
                Iterator<String> it = entry2.getValue().iterator();
                while (it.hasNext()) {
                    this.p.a(entry2.getKey(), it.next());
                }
            }
            if (!a.kGet.equals(this.c) && !a.kPost.equals(this.c)) {
                o();
                return;
            }
            if (this.m) {
                o();
                return;
            }
            try {
                InputStream inputStream2 = this.k.getInputStream();
                try {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream2);
                    try {
                        a(bufferedInputStream);
                        hp.a(bufferedInputStream);
                        hp.a(inputStream2);
                        o();
                    } catch (Throwable th4) {
                        th = th4;
                        closeable = bufferedInputStream;
                        inputStream = inputStream2;
                        hp.a(closeable);
                        hp.a(inputStream);
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    inputStream = inputStream2;
                }
            } catch (Throwable th6) {
                th = th6;
                inputStream = null;
            }
        } catch (Throwable th7) {
            o();
            throw th7;
        }
    }

    private void a(OutputStream outputStream) throws Exception {
        if (this.j != null && !b() && outputStream != null) {
            this.j.a(this, outputStream);
        }
    }

    private void a(InputStream inputStream) throws Exception {
        if (this.j != null && !b() && inputStream != null) {
            this.j.a(this, inputStream);
        }
    }

    private void n() {
        if (this.j != null && !b()) {
            this.j.a(this);
        }
    }

    private void o() {
        if (!this.l) {
            this.l = true;
            if (this.k != null) {
                this.k.disconnect();
            }
        }
    }

    private void p() {
        if (!this.l) {
            this.l = true;
            if (this.k != null) {
                new Thread() { // from class: com.flurry.sdk.gl.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        try {
                            if (gl.this.k != null) {
                                gl.this.k.disconnect();
                            }
                        } catch (Throwable th) {
                        }
                    }
                }.start();
            }
        }
    }
}
