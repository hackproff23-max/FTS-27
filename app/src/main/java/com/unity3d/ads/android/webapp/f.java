package com.unity3d.ads.android.webapp;

import android.os.AsyncTask;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.util.ByteArrayBuffer;

/* JADX INFO: compiled from: UnityAdsWebData.java */
/* JADX INFO: loaded from: classes.dex */
final class f extends AsyncTask {
    final /* synthetic */ UnityAdsWebData a;
    private URL b;
    private e h;
    private String i;
    private int j;
    private String k;
    private String l;
    private String m;
    private HttpURLConnection c = null;
    private int d = 0;
    private InputStream e = null;
    private BufferedInputStream f = null;
    private String g = "";
    private Boolean n = false;

    @Override // android.os.AsyncTask
    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return b();
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onPostExecute(Object obj) {
        String str = (String) obj;
        if (!isCancelled() && !this.n.booleanValue()) {
            this.n = true;
            c();
            UnityAdsWebData.c(this.a, this);
        }
        super.onPostExecute(str);
    }

    @Override // android.os.AsyncTask
    protected final /* bridge */ /* synthetic */ void onProgressUpdate(Object[] objArr) {
        super.onProgressUpdate((Integer[]) objArr);
    }

    public f(UnityAdsWebData unityAdsWebData, String str, String str2, String str3, e eVar, int i) {
        this.a = unityAdsWebData;
        this.b = null;
        this.h = null;
        this.i = null;
        this.j = 0;
        this.k = UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_GET;
        this.l = null;
        this.m = null;
        try {
            this.i = str;
            this.m = str;
            if (str3.equals(UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_GET) && str2 != null && str2.length() > 2) {
                this.i += "?" + str2;
            }
            this.b = new URL(this.i);
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Problems with url! Error-message: " + e.getMessage());
        }
        this.l = str2;
        this.k = str3;
        UnityAdsWebData.b(unityAdsWebData);
        UnityAdsDeviceLog.debug("Total urlLoaders created: " + unityAdsWebData.j);
        this.h = eVar;
        this.j = i;
    }

    public final int getRetries() {
        return this.j;
    }

    public final String getUrl() {
        return this.b.toString();
    }

    public final String getBaseUrl() {
        return this.m;
    }

    public final String getData() {
        return this.g;
    }

    public final String getQueryParams() {
        return this.l;
    }

    public final String getHTTPMethod() {
        return this.k;
    }

    public final e getRequestType() {
        return this.h;
    }

    public final void clear() {
        this.b = null;
        this.d = 0;
        this.g = "";
        this.h = null;
        this.i = null;
        this.j = 0;
        this.k = null;
        this.l = null;
        this.m = null;
    }

    private void a() {
        UnityAdsUtils.runOnUiThread(new d(this.a, this));
    }

    /* JADX WARN: Code duplicated, block: B:19:0x012a A[Catch: Exception -> 0x01c2, TRY_LEAVE, TryCatch #3 {Exception -> 0x01c2, blocks: (B:16:0x00d9, B:17:0x0121, B:19:0x012a, B:30:0x01a3), top: B:40:0x00d9 }] */
    /* JADX WARN: Code duplicated, block: B:42:0x01a3 A[SYNTHETIC] */
    private String b() {
        long j;
        ByteArrayBuffer byteArrayBuffer;
        int i;
        try {
            if (this.b.toString().startsWith("https://")) {
                this.c = (HttpsURLConnection) this.b.openConnection();
            } else {
                this.c = (HttpURLConnection) this.b.openConnection();
            }
            this.c.setConnectTimeout(20000);
            this.c.setReadTimeout(30000);
            this.c.setRequestMethod(this.k);
            this.c.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            this.c.setDoInput(true);
            if (this.k.equals(UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_POST)) {
                this.c.setDoOutput(true);
            }
            if (this.c != null) {
                if (this.k.equals(UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_POST)) {
                    try {
                        PrintWriter printWriter = new PrintWriter((Writer) new OutputStreamWriter(this.c.getOutputStream(), "UTF-8"), true);
                        printWriter.print(this.l);
                        printWriter.flush();
                        try {
                            UnityAdsDeviceLog.debug("Connection response: " + this.c.getResponseCode() + ", " + this.c.getResponseMessage() + ", " + this.c.getURL().toString() + " : " + this.l);
                            this.e = this.c.getInputStream();
                            this.f = new BufferedInputStream(this.e);
                            j = 0;
                            this.d = this.c.getContentLength();
                            try {
                                UnityAdsWebData.d(this.a);
                                UnityAdsDeviceLog.debug("Total urlLoaders that have started running: " + this.a.k);
                                UnityAdsDeviceLog.debug("Reading data from: " + this.b.toString() + " Content-length: " + this.d);
                                byteArrayBuffer = new ByteArrayBuffer(20480);
                                do {
                                    i = this.f.read();
                                    if (i != -1) {
                                        j++;
                                        byteArrayBuffer.append((byte) i);
                                    } else {
                                        this.g = new String(byteArrayBuffer.toByteArray());
                                        UnityAdsDeviceLog.debug("Read total of: " + j);
                                        break;
                                    }
                                } while (!isCancelled());
                            } catch (Exception e) {
                                UnityAdsDeviceLog.error("Problems loading url! Error-message: " + e.getMessage());
                                a();
                            }
                        } catch (Exception e2) {
                            UnityAdsDeviceLog.error("Problems opening stream: " + e2.getMessage());
                            a();
                        }
                    } catch (Exception e3) {
                        UnityAdsDeviceLog.error("Problems writing post-data: " + e3.getMessage() + ", " + e3.getStackTrace());
                        a();
                    }
                } else {
                    UnityAdsDeviceLog.debug("Connection response: " + this.c.getResponseCode() + ", " + this.c.getResponseMessage() + ", " + this.c.getURL().toString() + " : " + this.l);
                    this.e = this.c.getInputStream();
                    this.f = new BufferedInputStream(this.e);
                    j = 0;
                    this.d = this.c.getContentLength();
                    UnityAdsWebData.d(this.a);
                    UnityAdsDeviceLog.debug("Total urlLoaders that have started running: " + this.a.k);
                    UnityAdsDeviceLog.debug("Reading data from: " + this.b.toString() + " Content-length: " + this.d);
                    byteArrayBuffer = new ByteArrayBuffer(20480);
                    do {
                        i = this.f.read();
                        if (i != -1) {
                            j++;
                            byteArrayBuffer.append((byte) i);
                        } else {
                            this.g = new String(byteArrayBuffer.toByteArray());
                            UnityAdsDeviceLog.debug("Read total of: " + j);
                            break;
                        }
                    } while (!isCancelled());
                }
            }
        } catch (Exception e4) {
            UnityAdsDeviceLog.error("Problems opening connection: " + e4.getMessage());
            a();
        }
        return null;
    }

    @Override // android.os.AsyncTask
    protected final void onCancelled() {
        this.n = true;
        c();
        UnityAdsWebData.b(this.a, this);
    }

    private void c() {
        try {
            this.e.close();
            this.e = null;
            this.f.close();
            this.f = null;
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Problems closing streams: " + e.getMessage());
        }
    }
}
