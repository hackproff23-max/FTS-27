package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinLogger;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class n {
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;

    n(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
        this.b = appLovinSdkImpl.getLogger();
    }

    private int a(Throwable th) {
        if (th instanceof SocketTimeoutException) {
            return AppLovinErrorCodes.FETCH_AD_TIMEOUT;
        }
        if (!(th instanceof IOException)) {
            return th instanceof JSONException ? -104 : -1;
        }
        String message = th.getMessage();
        return (message == null || !message.toLowerCase(Locale.ENGLISH).contains("authentication challenge")) ? -100 : 401;
    }

    private HttpURLConnection a(String str, String str2, int i) throws ProtocolException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod(str2);
        httpURLConnection.setConnectTimeout(i < 0 ? ((Integer) this.a.a(bb.t)).intValue() : i);
        if (i < 0) {
            i = ((Integer) this.a.a(bb.v)).intValue();
        }
        httpURLConnection.setReadTimeout(i);
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setAllowUserInteraction(false);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    private static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
            }
        }
    }

    private void a(String str, int i, String str2, o oVar) {
        this.b.d("ConnectionManager", i + " received from from \"" + str2 + "\": " + str);
        if (i < 200 || i >= 300) {
            this.b.e("ConnectionManager", i + " error received from \"" + str2 + "\"");
            oVar.a(i);
            return;
        }
        JSONObject jSONObject = new JSONObject();
        if (i != 204 && str != null && str.length() > 2) {
            jSONObject = new JSONObject(str);
        }
        oVar.a(jSONObject, i);
    }

    private void a(String str, String str2, int i, long j) {
        this.b.i("ConnectionManager", "Successful " + str + " returned " + i + " in " + ((System.currentTimeMillis() - j) / 1000.0f) + " s over " + p.a(this.a) + " to \"" + str2 + "\"");
    }

    private void a(String str, String str2, int i, long j, Throwable th) {
        this.b.e("ConnectionManager", "Failed " + str + " returned " + i + " in " + ((System.currentTimeMillis() - j) / 1000.0f) + " s over " + p.a(this.a) + " to \"" + str2 + "\"", th);
    }

    private static void a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e) {
            }
        }
    }

    void a(String str, int i, o oVar) throws Throwable {
        a(str, UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_GET, i, (JSONObject) null, oVar);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x00de A[Catch: all -> 0x00fb, TRY_ENTER, TryCatch #4 {all -> 0x00fb, blocks: (B:15:0x0054, B:16:0x00ab, B:18:0x00b1, B:29:0x00de, B:30:0x00e2, B:24:0x00d2), top: B:45:0x0024 }] */
    void a(String str, String str2, int i, JSONObject jSONObject, o oVar) throws Throwable {
        HttpURLConnection httpURLConnectionA;
        InputStream inputStream;
        InputStream inputStream2 = null;
        if (str == null) {
            throw new IllegalArgumentException("No endpoint specified");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("No method specified");
        }
        if (oVar == null) {
            throw new IllegalArgumentException("No callback specified");
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        int iA = -1;
        try {
            try {
                this.b.i("ConnectionManager", "Sending " + str2 + " request to \"" + str + "\"...");
                httpURLConnectionA = a(str, str2, i);
                if (jSONObject != null) {
                    try {
                        String string = jSONObject.toString();
                        this.b.d("ConnectionManager", "Request to \"" + str + "\" is " + string);
                        httpURLConnectionA.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                        httpURLConnectionA.setDoOutput(true);
                        httpURLConnectionA.setFixedLengthStreamingMode(string.getBytes(Charset.forName("UTF-8")).length);
                        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(httpURLConnectionA.getOutputStream(), "UTF8"));
                        printWriter.print(string);
                        printWriter.close();
                    } catch (Throwable th) {
                        th = th;
                        if (iA == 0) {
                            iA = a(th);
                        }
                        a(str2, str, iA, jCurrentTimeMillis, th);
                        oVar.a(iA);
                        a(inputStream2);
                        a(httpURLConnectionA);
                        return;
                    }
                }
                iA = httpURLConnectionA.getResponseCode();
                if (iA > 0) {
                    InputStream inputStream3 = httpURLConnectionA.getInputStream();
                    try {
                        String strA = p.a(inputStream3);
                        a(str2, str, iA, jCurrentTimeMillis);
                        a(strA, httpURLConnectionA.getResponseCode(), str, oVar);
                        inputStream = inputStream3;
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream2 = inputStream3;
                        a(inputStream2);
                        a(httpURLConnectionA);
                        throw th;
                    }
                } else {
                    a(str2, str, iA, jCurrentTimeMillis, (Throwable) null);
                    oVar.a(iA);
                    inputStream = null;
                }
                a(inputStream);
                a(httpURLConnectionA);
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            httpURLConnectionA = null;
        }
    }

    void a(String str, JSONObject jSONObject, o oVar) throws Throwable {
        a(str, UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_POST, -1, jSONObject, oVar);
    }
}
