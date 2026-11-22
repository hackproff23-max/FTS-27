package com.facebook.ads.a;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import com.facebook.ads.AdSettings;
import com.mopub.common.Constants;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.security.KeyStore;
import java.util.Map;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* JADX INFO: loaded from: classes.dex */
public class p {
    private static final Uri a = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");

    protected static class a {
        protected String a;
        protected String b;
        protected boolean c;

        public a(String str, String str2, boolean z) {
            this.a = str;
            this.b = str2;
            this.c = z;
        }
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0078  */
    /* JADX WARN: Code duplicated, block: B:7:0x0025 A[Catch: all -> 0x007c, Exception -> 0x007e, TRY_LEAVE, TryCatch #0 {Exception -> 0x007e, blocks: (B:5:0x001f, B:11:0x0033, B:7:0x0025), top: B:29:0x001f }] */
    /* JADX WARN: Code duplicated, block: B:9:0x002f  */
    public static a a(ContentResolver contentResolver) throws Throwable {
        Cursor cursorQuery;
        a aVar;
        try {
            cursorQuery = contentResolver.query(a, new String[]{"aid", "androidid", "limit_tracking"}, null, null, null);
            if (cursorQuery != null) {
                try {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            aVar = new a(cursorQuery.getString(cursorQuery.getColumnIndex("aid")), cursorQuery.getString(cursorQuery.getColumnIndex("androidid")), Boolean.valueOf(cursorQuery.getString(cursorQuery.getColumnIndex("limit_tracking"))).booleanValue());
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                        } else {
                            aVar = new a(null, null, false);
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                        }
                    } catch (Exception e) {
                        aVar = new a(null, null, false);
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    throw th;
                }
            } else {
                aVar = new a(null, null, false);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        } catch (Exception e2) {
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = null;
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
        return aVar;
    }

    public static Object a(Object obj, Method method, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (Exception e) {
            return null;
        }
    }

    public static Object a(JSONObject jSONObject, String str) throws JSONException {
        Object objOpt = jSONObject.opt(str);
        if (objOpt != null && (objOpt instanceof String)) {
            objOpt = new JSONTokener((String) objOpt).nextValue();
        }
        if (objOpt == null || (objOpt instanceof JSONObject) || (objOpt instanceof JSONArray)) {
            return objOpt;
        }
        throw new IllegalArgumentException(str);
    }

    public static String a(InputStream inputStream) throws Throwable {
        InputStreamReader inputStreamReader;
        BufferedInputStream bufferedInputStream = null;
        try {
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream);
            try {
                inputStreamReader = new InputStreamReader(bufferedInputStream2);
                try {
                    StringBuilder sb = new StringBuilder();
                    char[] cArr = new char[2048];
                    while (true) {
                        int i = inputStreamReader.read(cArr);
                        if (i == -1) {
                            String string = sb.toString();
                            a((Closeable) bufferedInputStream2);
                            a(inputStreamReader);
                            return string;
                        }
                        sb.append(cArr, 0, i);
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream = bufferedInputStream2;
                    a((Closeable) bufferedInputStream);
                    a(inputStreamReader);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                inputStreamReader = null;
                bufferedInputStream = bufferedInputStream2;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStreamReader = null;
        }
    }

    public static String a(Map map) {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry entry : map.entrySet()) {
            try {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject.toString();
    }

    public static Method a(Class cls, String str, Class... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Method a(String str, String str2, Class... clsArr) {
        try {
            return a(Class.forName(str), str2, clsArr);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static void a(Context context, String str) {
        if (AdSettings.isTestMode(context)) {
            Log.d("FBAudienceNetworkLog", str + " (displayed for test ads only)");
        }
    }

    public static void a(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
        }
    }

    public static boolean a() {
        String urlPrefix = AdSettings.getUrlPrefix();
        return !ag.a(urlPrefix) && urlPrefix.endsWith(".sb");
    }

    public static boolean a(Context context, View view, int i, int i2, int i3) {
        if (!((PowerManager) context.getSystemService("power")).isScreenOn()) {
            return false;
        }
        if (view == null || view.getParent() == null || view.getVisibility() != 0) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 11 && view.getAlpha() < 0.9f) {
            return false;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (iArr[0] < 0 || displayMetrics.widthPixels - iArr[0] < i) {
            return false;
        }
        int i4 = (int) ((((double) i2) * (100.0d - ((double) i3))) / 100.0d);
        if ((iArr[1] >= 0 || Math.abs(iArr[1]) <= i4) && (iArr[1] + i2) - displayMetrics.heightPixels <= i4) {
            return true;
        }
        return false;
    }

    public static HttpClient b() {
        if (a()) {
            try {
                KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load(null, null);
                ae aeVar = new ae(keyStore);
                aeVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                BasicHttpParams basicHttpParams = new BasicHttpParams();
                HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
                HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
                SchemeRegistry schemeRegistry = new SchemeRegistry();
                schemeRegistry.register(new Scheme(Constants.HTTP, PlainSocketFactory.getSocketFactory(), 80));
                schemeRegistry.register(new Scheme(Constants.HTTPS, aeVar, 443));
                return new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
            } catch (Exception e) {
            }
        }
        return new DefaultHttpClient();
    }
}
