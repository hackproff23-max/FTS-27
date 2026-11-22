package com.facebook.ads.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import com.facebook.FacebookSdkVersion;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.share.internal.ShareConstants;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONTokener;

/* JADX INFO: loaded from: classes.dex */
public class j {
    private static final String a = j.class.getSimpleName();
    private final Context b;
    private final String c;
    private final AdSize d;
    private final n e;
    private final String f;
    private final int g;
    private final b h;
    private final o i;
    private final boolean j;

    private static class a {
        JSONObject a;
        AdError b;

        private a() {
            this.a = null;
            this.b = null;
        }
    }

    public interface b {
        void a(AdError adError);

        void a(l lVar);
    }

    public j(Context context, String str, AdSize adSize, n nVar, o oVar, int i, boolean z, b bVar) {
        if (str == null || str.length() < 1) {
            throw new IllegalArgumentException("placementId");
        }
        if (adSize == null) {
            throw new IllegalArgumentException("adSize");
        }
        if (bVar == null) {
            throw new IllegalArgumentException("callback");
        }
        this.b = context;
        this.c = str;
        this.d = adSize;
        this.e = nVar;
        this.f = s.a(context, oVar);
        this.i = oVar;
        this.g = i;
        this.j = z;
        this.h = bVar;
    }

    private a a(InputStream inputStream) {
        a aVar = new a();
        try {
            Object objNextValue = new JSONTokener(p.a(inputStream)).nextValue();
            if (objNextValue instanceof JSONObject) {
                JSONObject jSONObject = (JSONObject) objNextValue;
                if (jSONObject.has("error")) {
                    aVar.b = new AdError(2000, ((JSONObject) p.a(jSONObject, "error")).optString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, AdError.SERVER_ERROR.getErrorMessage()));
                } else {
                    aVar.a = jSONObject;
                    aVar.b = null;
                }
            }
        } catch (Exception e) {
            aVar.b = new AdError(2000, e.getMessage());
        }
        if (aVar.a == null && aVar.b == null) {
            return null;
        }
        return aVar;
    }

    private void a(Map map) {
        map.put("os", "Android");
        map.put("os_version", Build.VERSION.RELEASE);
        DisplayMetrics displayMetrics = this.b.getResources().getDisplayMetrics();
        int i = (int) (displayMetrics.widthPixels / displayMetrics.density);
        int i2 = (int) (displayMetrics.heightPixels / displayMetrics.density);
        map.put("screen_width", Integer.valueOf(i));
        map.put("screen_height", Integer.valueOf(i2));
        try {
            PackageInfo packageInfo = this.b.getPackageManager().getPackageInfo(this.b.getPackageName(), 0);
            map.put("app_build", Integer.valueOf(packageInfo.versionCode));
            map.put("app_version", packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            map.put("app_version", 0);
        }
        Locale locale = this.b.getResources().getConfiguration().locale;
        if (locale == null) {
            locale = Locale.getDefault();
        }
        map.put("locale", locale.toString());
    }

    private void a(Map map, p.a aVar) {
        t tVarA = t.a(this.b, aVar);
        if (tVarA == null) {
            map.put("tracking_enabled", true);
        } else {
            map.put("tracking_enabled", Boolean.valueOf(tVarA.b() ? false : true));
            map.put("device_id", tVarA.a());
        }
    }

    private void b(Map map) {
        map.put("package_name", this.b.getPackageName());
    }

    private String c() {
        switch (this.i) {
            case NATIVE:
                return "network_ads_native";
            default:
                return "network_ads";
        }
    }

    private String c(Map map) {
        boolean z;
        StringBuilder sb = new StringBuilder(512);
        boolean z2 = true;
        for (Map.Entry entry : map.entrySet()) {
            if (z2) {
                z = false;
            } else {
                sb.append("&");
                z = z2;
            }
            sb.append(URLEncoder.encode((String) entry.getKey(), "utf-8")).append("=").append(URLEncoder.encode(String.valueOf(entry.getValue()), "utf-8"));
            z2 = z;
        }
        return sb.toString();
    }

    private Map d() throws Throwable {
        HashMap map = new HashMap();
        p.a aVarA = p.a(this.b.getContentResolver());
        map.put("ad_type", Integer.valueOf(this.i.a()));
        map.put("sdk_capabilities", m.b());
        map.put("sdk_version", FacebookSdkVersion.BUILD);
        map.put("placement_id", this.c);
        map.put("attribution_id", aVarA.a);
        map.put("width", Integer.valueOf(this.d.getWidth()));
        map.put("height", Integer.valueOf(this.d.getHeight()));
        map.put("template_id", Integer.valueOf(this.e.a()));
        map.put("test_mode", Boolean.valueOf(this.j));
        map.put("child_directed", Boolean.valueOf(AdSettings.isChildDirected()));
        map.put("events", c.a());
        map.put("num_ads_requested", Integer.valueOf(this.g));
        a(map);
        b(map);
        a(map, aVarA);
        return map;
    }

    private URL e() {
        String urlPrefix = AdSettings.getUrlPrefix();
        return new URL(String.format("%s/%s", ag.a(urlPrefix) ? "https://graph.facebook.com" : String.format("http://graph.%s.facebook.com", urlPrefix), c()));
    }

    private HttpURLConnection f() throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) e().openConnection();
        httpURLConnection.setRequestProperty("User-Agent", this.f);
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestMethod(UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_POST);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(30000);
        String strC = c(d());
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(bufferedOutputStream, "utf-8"));
        bufferedWriter.write(strC);
        bufferedWriter.flush();
        bufferedWriter.close();
        bufferedOutputStream.close();
        httpURLConnection.connect();
        return httpURLConnection;
    }

    public AsyncTask a() {
        com.facebook.ads.a.a.a(this.b);
        return new AsyncTask() { // from class: com.facebook.ads.a.j.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public a doInBackground(Void... voidArr) {
                return j.this.b();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(a aVar) {
                if (aVar == null) {
                    j.this.h.a(AdError.INTERNAL_ERROR);
                } else if (aVar.b != null) {
                    j.this.h.a(aVar.b);
                } else {
                    j.this.h.a(l.a(j.this.b, aVar.a));
                }
            }
        }.execute(new Void[0]);
    }

    public a b() throws Throwable {
        InputStream inputStream;
        HttpURLConnection httpURLConnectionF;
        IOException iOException;
        SocketTimeoutException socketTimeoutException;
        a aVar;
        InputStream errorStream = null;
        errorStream = null;
        httpURLConnection = null;
        httpURLConnection = null;
        errorStream = null;
        httpURLConnection = null;
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                try {
                    httpURLConnectionF = f();
                    try {
                        try {
                            errorStream = httpURLConnectionF.getResponseCode() >= 400 ? httpURLConnectionF.getErrorStream() : httpURLConnectionF.getInputStream();
                            try {
                                aVar = a(errorStream);
                                p.a((Closeable) errorStream);
                                if (httpURLConnectionF != null) {
                                    httpURLConnectionF.disconnect();
                                }
                            } catch (SocketTimeoutException e) {
                                inputStream = errorStream;
                                httpURLConnection = httpURLConnectionF;
                                socketTimeoutException = e;
                                aVar = new a();
                                aVar.b = new AdError(1000, socketTimeoutException.getMessage());
                                p.a((Closeable) inputStream);
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                            } catch (IOException e2) {
                                inputStream = errorStream;
                                httpURLConnection = httpURLConnectionF;
                                iOException = e2;
                                aVar = new a();
                                aVar.b = new AdError(1000, iOException.getMessage());
                                p.a((Closeable) inputStream);
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                            }
                        } catch (Exception e3) {
                            e = e3;
                            Log.e(a, "Unexpected error", e);
                            aVar = new a();
                            aVar.b = AdError.INTERNAL_ERROR;
                            p.a((Closeable) errorStream);
                            if (httpURLConnectionF != null) {
                                httpURLConnectionF.disconnect();
                            }
                        }
                    } catch (SocketTimeoutException e4) {
                        inputStream = null;
                        httpURLConnection = httpURLConnectionF;
                        socketTimeoutException = e4;
                    } catch (IOException e5) {
                        inputStream = null;
                        httpURLConnection = httpURLConnectionF;
                        iOException = e5;
                    } catch (Throwable th) {
                        th = th;
                        inputStream = null;
                        httpURLConnection = httpURLConnectionF;
                        p.a((Closeable) inputStream);
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (SocketTimeoutException e6) {
                socketTimeoutException = e6;
                inputStream = null;
            } catch (IOException e7) {
                iOException = e7;
                inputStream = null;
            } catch (Exception e8) {
                e = e8;
                httpURLConnectionF = null;
            } catch (Throwable th3) {
                th = th3;
                inputStream = null;
            }
            return aVar;
        } catch (Throwable th4) {
            th = th4;
            inputStream = errorStream;
            httpURLConnection = httpURLConnectionF;
        }
    }
}
