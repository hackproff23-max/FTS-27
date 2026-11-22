package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.ads.InterstitialAd;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class fq extends fl.a {
    private static final Object ut = new Object();
    private static fq uu;
    private final Context mContext;
    private final fw uv;
    private final cn uw;
    private final bm ux;

    fq(Context context, bm bmVar, cn cnVar, fw fwVar) {
        this.mContext = context;
        this.uv = fwVar;
        this.uw = cnVar;
        this.ux = bmVar;
    }

    private static gv.a I(final String str) {
        return new gv.a() { // from class: com.google.android.gms.internal.fq.3
            @Override // com.google.android.gms.internal.gv.a
            public void a(gu guVar) {
                String str2 = String.format("javascript:%s(%s);", "AFMA_buildAdURL", str);
                gr.V("About to execute: " + str2);
                guVar.loadUrl(str2);
            }
        };
    }

    private static fj a(final Context context, bm bmVar, cn cnVar, fw fwVar, final fh fhVar) {
        String string;
        gr.S("Starting ad request from service.");
        cnVar.init();
        fv fvVar = new fv(context);
        if (fvVar.vr == -1) {
            gr.S("Device is offline.");
            return new fj(2);
        }
        final fs fsVar = new fs(fhVar.applicationInfo.packageName);
        if (fhVar.tL.extras != null && (string = fhVar.tL.extras.getString("_ad")) != null) {
            return fr.a(context, fhVar, string);
        }
        Location locationA = cnVar.a(250L);
        final String strBu = bmVar.bu();
        String strA = fr.a(fhVar, fvVar, locationA, bmVar.bv(), bmVar.bw(), bmVar.bx());
        if (strA == null) {
            return new fj(0);
        }
        final gv.a aVarI = I(strA);
        gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.fq.1
            @Override // java.lang.Runnable
            public void run() {
                gu guVarA = gu.a(context, new ay(), false, false, null, fhVar.lO);
                guVarA.setWillNotDraw(true);
                fsVar.b(guVarA);
                gv gvVarDD = guVarA.dD();
                gvVarDD.a("/invalidRequest", fsVar.uG);
                gvVarDD.a("/loadAdURL", fsVar.uH);
                gvVarDD.a("/log", cc.pX);
                gvVarDD.a(aVarI);
                gr.S("Loading the JS library.");
                guVarA.loadUrl(strBu);
            }
        });
        try {
            fu fuVar = fsVar.cR().get(10L, TimeUnit.SECONDS);
            if (fuVar == null) {
                return new fj(0);
            }
            if (fuVar.getErrorCode() != -2) {
                return new fj(fuVar.getErrorCode());
            }
            return a(context, fhVar.lO.wS, fuVar.getUrl(), fuVar.cV() ? fwVar.K(fhVar.tM.packageName) : null, fuVar);
        } catch (Exception e) {
            return new fj(0);
        } finally {
            gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.fq.2
                @Override // java.lang.Runnable
                public void run() {
                    fsVar.cS();
                }
            });
        }
    }

    public static fj a(Context context, String str, String str2, String str3, fu fuVar) {
        try {
            ft ftVar = new ft();
            gr.S("AdRequestServiceImpl: Sending request: " + str2);
            URL url = new URL(str2);
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            URL url2 = url;
            int i = 0;
            while (true) {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();
                try {
                    gi.a(context, str, false, httpURLConnection);
                    if (!TextUtils.isEmpty(str3)) {
                        httpURLConnection.addRequestProperty("x-afma-drt-cookie", str3);
                    }
                    if (fuVar != null && !TextUtils.isEmpty(fuVar.cU())) {
                        httpURLConnection.setDoOutput(true);
                        byte[] bytes = fuVar.cU().getBytes();
                        httpURLConnection.setFixedLengthStreamingMode(bytes.length);
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                        bufferedOutputStream.write(bytes);
                        bufferedOutputStream.close();
                    }
                    int responseCode = httpURLConnection.getResponseCode();
                    Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
                    if (responseCode >= 200 && responseCode < 300) {
                        String string = url2.toString();
                        String strA = gi.a(new InputStreamReader(httpURLConnection.getInputStream()));
                        a(string, headerFields, strA, responseCode);
                        ftVar.a(string, headerFields, strA);
                        fj fjVarI = ftVar.i(jElapsedRealtime);
                        httpURLConnection.disconnect();
                        return fjVarI;
                    }
                    a(url2.toString(), headerFields, (String) null, responseCode);
                    if (responseCode < 300 || responseCode >= 400) {
                        gr.W("Received error HTTP response code: " + responseCode);
                        fj fjVar = new fj(0);
                        httpURLConnection.disconnect();
                        return fjVar;
                    }
                    String headerField = httpURLConnection.getHeaderField("Location");
                    if (TextUtils.isEmpty(headerField)) {
                        gr.W("No location header to follow redirect.");
                        fj fjVar2 = new fj(0);
                        httpURLConnection.disconnect();
                        return fjVar2;
                    }
                    url2 = new URL(headerField);
                    i++;
                    if (i > 5) {
                        gr.W("Too many redirects.");
                        fj fjVar3 = new fj(0);
                        httpURLConnection.disconnect();
                        return fjVar3;
                    }
                    ftVar.e(headerFields);
                    httpURLConnection.disconnect();
                } catch (Throwable th) {
                    httpURLConnection.disconnect();
                    throw th;
                }
            }
        } catch (IOException e) {
            gr.W("Error while connecting to ad server: " + e.getMessage());
            return new fj(2);
        }
    }

    public static fq a(Context context, bm bmVar, cn cnVar, fw fwVar) {
        fq fqVar;
        synchronized (ut) {
            if (uu == null) {
                uu = new fq(context.getApplicationContext(), bmVar, cnVar, fwVar);
            }
            fqVar = uu;
        }
        return fqVar;
    }

    private static void a(String str, Map<String, List<String>> map, String str2, int i) {
        if (gr.v(2)) {
            gr.V("Http Response: {\n  URL:\n    " + str + "\n  Headers:");
            if (map != null) {
                for (String str3 : map.keySet()) {
                    gr.V("    " + str3 + InterstitialAd.SEPARATOR);
                    Iterator<String> it = map.get(str3).iterator();
                    while (it.hasNext()) {
                        gr.V("      " + it.next());
                    }
                }
            }
            gr.V("  Body:");
            if (str2 != null) {
                for (int i2 = 0; i2 < Math.min(str2.length(), 100000); i2 += 1000) {
                    gr.V(str2.substring(i2, Math.min(str2.length(), i2 + 1000)));
                }
            } else {
                gr.V("    null");
            }
            gr.V("  Response Code:\n    " + i + "\n}");
        }
    }

    @Override // com.google.android.gms.internal.fl
    public fj b(fh fhVar) {
        return a(this.mContext, this.ux, this.uw, this.uv, fhVar);
    }
}
