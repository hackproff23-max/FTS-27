package com.google.android.gms.internal;

import android.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.android.gms.ads.AdActivity;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.nio.CharBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class gi {
    private static String wD;
    private static final Object ut = new Object();
    private static final SimpleDateFormat[] wB = {new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"), new SimpleDateFormat("yyyyMMdd")};
    private static boolean wC = true;
    private static boolean wE = false;

    private static final class a extends BroadcastReceiver {
        private a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                boolean unused = gi.wC = true;
            } else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                boolean unused2 = gi.wC = false;
            }
        }
    }

    public static String L(String str) {
        return Uri.parse(str).buildUpon().query(null).build().toString();
    }

    public static int M(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            gr.W("Could not parse value:" + e);
            return 0;
        }
    }

    public static boolean N(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)");
    }

    public static long O(String str) {
        long j = -1;
        if (TextUtils.isEmpty(str)) {
            return -1L;
        }
        for (SimpleDateFormat simpleDateFormat : wB) {
            try {
                simpleDateFormat.setLenient(false);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                return simpleDateFormat.parse(str).getTime();
            } catch (ParseException e) {
            }
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e2) {
            return j;
        }
    }

    public static String a(Readable readable) throws IOException {
        StringBuilder sb = new StringBuilder();
        CharBuffer charBufferAllocate = CharBuffer.allocate(2048);
        while (true) {
            int i = readable.read(charBufferAllocate);
            if (i == -1) {
                return sb.toString();
            }
            charBufferAllocate.flip();
            sb.append((CharSequence) charBufferAllocate, 0, i);
        }
    }

    private static JSONArray a(Collection<?> collection) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            a(jSONArray, it.next());
        }
        return jSONArray;
    }

    static JSONArray a(Object[] objArr) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Object obj : objArr) {
            a(jSONArray, obj);
        }
        return jSONArray;
    }

    public static void a(Context context, String str, WebSettings webSettings) {
        webSettings.setUserAgentString(c(context, str));
    }

    public static void a(Context context, String str, List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            new gp(context, str, it.next()).start();
        }
    }

    public static void a(Context context, String str, List<String> list, String str2) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            new gp(context, str, it.next(), str2).start();
        }
    }

    public static void a(Context context, String str, boolean z, HttpURLConnection httpURLConnection) {
        a(context, str, z, httpURLConnection, false);
    }

    public static void a(Context context, String str, boolean z, HttpURLConnection httpURLConnection, String str2) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(z);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty("User-Agent", str2);
        httpURLConnection.setUseCaches(false);
    }

    public static void a(Context context, String str, boolean z, HttpURLConnection httpURLConnection, boolean z2) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(z);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty("User-Agent", c(context, str));
        httpURLConnection.setUseCaches(z2);
    }

    public static void a(WebView webView) {
        if (Build.VERSION.SDK_INT >= 11) {
            gm.a(webView);
        }
    }

    private static void a(JSONArray jSONArray, Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            jSONArray.put(c((Bundle) obj));
            return;
        }
        if (obj instanceof Map) {
            jSONArray.put(t((Map<String, ?>) obj));
            return;
        }
        if (obj instanceof Collection) {
            jSONArray.put(a((Collection<?>) obj));
        } else if (obj instanceof Object[]) {
            jSONArray.put(a((Object[]) obj));
        } else {
            jSONArray.put(obj);
        }
    }

    private static void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            jSONObject.put(str, c((Bundle) obj));
            return;
        }
        if (obj instanceof Map) {
            jSONObject.put(str, t((Map<String, ?>) obj));
            return;
        }
        if (obj instanceof Collection) {
            if (str == null) {
                str = "null";
            }
            jSONObject.put(str, a((Collection<?>) obj));
        } else if (obj instanceof Object[]) {
            jSONObject.put(str, a(Arrays.asList((Object[]) obj)));
        } else {
            jSONObject.put(str, obj);
        }
    }

    public static boolean a(PackageManager packageManager, String str, String str2) {
        return packageManager.checkPermission(str2, str) == 0;
    }

    public static boolean a(ClassLoader classLoader, Class<?> cls, String str) {
        try {
            return cls.isAssignableFrom(Class.forName(str, false, classLoader));
        } catch (Throwable th) {
            return false;
        }
    }

    public static void b(WebView webView) {
        if (Build.VERSION.SDK_INT >= 11) {
            gm.b(webView);
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0046 A[Catch: all -> 0x003c, TryCatch #3 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x0009, B:9:0x000b, B:12:0x0011, B:13:0x003a, B:19:0x0040, B:21:0x0046, B:22:0x0050, B:24:0x0054, B:30:0x0080, B:31:0x00a3, B:27:0x005b, B:29:0x007a, B:34:0x00a7), top: B:42:0x0003, inners: #1, #2 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x007a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:40:0x0054 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static String c(final Context context, String str) {
        String str2;
        synchronized (ut) {
            if (wD != null) {
                str2 = wD;
            } else if (Build.VERSION.SDK_INT >= 17) {
                try {
                    wD = go.getDefaultUserAgent(context);
                    wD += " (Mobile; " + str + ")";
                    str2 = wD;
                } catch (Exception e) {
                    if (gq.dB()) {
                        try {
                            wD = r(context);
                        } catch (Exception e2) {
                            wD = dw();
                        }
                        wD += " (Mobile; " + str + ")";
                        str2 = wD;
                    } else {
                        gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.gi.1
                            @Override // java.lang.Runnable
                            public void run() {
                                synchronized (gi.ut) {
                                    String unused = gi.wD = gi.r(context);
                                    gi.ut.notifyAll();
                                }
                            }
                        });
                        while (wD == null) {
                            try {
                                ut.wait();
                            } catch (InterruptedException e3) {
                                wD = dw();
                                gr.W("Interrupted, use default user agent: " + wD);
                            }
                        }
                        wD += " (Mobile; " + str + ")";
                        str2 = wD;
                    }
                }
            } else if (gq.dB()) {
                gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.gi.1
                    @Override // java.lang.Runnable
                    public void run() {
                        synchronized (gi.ut) {
                            String unused = gi.wD = gi.r(context);
                            gi.ut.notifyAll();
                        }
                    }
                });
                while (wD == null) {
                    ut.wait();
                }
                wD += " (Mobile; " + str + ")";
                str2 = wD;
            } else {
                wD = r(context);
                wD += " (Mobile; " + str + ")";
                str2 = wD;
            }
        }
        return str2;
    }

    public static Map<String, String> c(Uri uri) {
        if (uri == null) {
            return null;
        }
        HashMap map = new HashMap();
        UrlQuerySanitizer urlQuerySanitizer = new UrlQuerySanitizer();
        urlQuerySanitizer.setAllowUnregisteredParamaters(true);
        urlQuerySanitizer.setUnregisteredParameterValueSanitizer(UrlQuerySanitizer.getAllButNulLegal());
        urlQuerySanitizer.parseUrl(uri.toString());
        for (UrlQuerySanitizer.ParameterValuePair parameterValuePair : urlQuerySanitizer.getParameterList()) {
            map.put(parameterValuePair.mParameter, parameterValuePair.mValue);
        }
        return map;
    }

    private static JSONObject c(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            a(jSONObject, str, bundle.get(str));
        }
        return jSONObject;
    }

    public static void c(Context context, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        a(context, str, arrayList);
    }

    public static boolean dt() {
        return wC;
    }

    public static int du() {
        return Build.VERSION.SDK_INT >= 9 ? 6 : 0;
    }

    public static int dv() {
        return Build.VERSION.SDK_INT >= 9 ? 7 : 1;
    }

    static String dw() {
        StringBuffer stringBuffer = new StringBuffer(256);
        stringBuffer.append("Mozilla/5.0 (Linux; U; Android");
        if (Build.VERSION.RELEASE != null) {
            stringBuffer.append(" ").append(Build.VERSION.RELEASE);
        }
        stringBuffer.append("; ").append(Locale.getDefault());
        if (Build.DEVICE != null) {
            stringBuffer.append("; ").append(Build.DEVICE);
            if (Build.DISPLAY != null) {
                stringBuffer.append(" Build/").append(Build.DISPLAY);
            }
        }
        stringBuffer.append(") AppleWebKit/533 Version/4.0 Safari/533");
        return stringBuffer.toString();
    }

    public static String dx() {
        UUID uuidRandomUUID = UUID.randomUUID();
        byte[] byteArray = BigInteger.valueOf(uuidRandomUUID.getLeastSignificantBits()).toByteArray();
        byte[] byteArray2 = BigInteger.valueOf(uuidRandomUUID.getMostSignificantBits()).toByteArray();
        String string = new BigInteger(1, byteArray).toString();
        for (int i = 0; i < 2; i++) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(byteArray);
                messageDigest.update(byteArray2);
                byte[] bArr = new byte[8];
                System.arraycopy(messageDigest.digest(), 0, bArr, 0, 8);
                string = new BigInteger(1, bArr).toString();
            } catch (NoSuchAlgorithmException e) {
            }
        }
        return string;
    }

    public static boolean p(Context context) {
        boolean z;
        Intent intent = new Intent();
        intent.setClassName(context, AdActivity.CLASS_NAME);
        ResolveInfo resolveInfoResolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
        if (resolveInfoResolveActivity == null || resolveInfoResolveActivity.activityInfo == null) {
            gr.W("Could not find com.google.android.gms.ads.AdActivity, please make sure it is declared in AndroidManifest.xml.");
            return false;
        }
        if ((resolveInfoResolveActivity.activityInfo.configChanges & 16) == 0) {
            gr.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "keyboard"));
            z = false;
        } else {
            z = true;
        }
        if ((resolveInfoResolveActivity.activityInfo.configChanges & 32) == 0) {
            gr.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "keyboardHidden"));
            z = false;
        }
        if ((resolveInfoResolveActivity.activityInfo.configChanges & 128) == 0) {
            gr.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "orientation"));
            z = false;
        }
        if ((resolveInfoResolveActivity.activityInfo.configChanges & 256) == 0) {
            gr.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "screenLayout"));
            z = false;
        }
        if ((resolveInfoResolveActivity.activityInfo.configChanges & 512) == 0) {
            gr.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "uiMode"));
            z = false;
        }
        if ((resolveInfoResolveActivity.activityInfo.configChanges & 1024) == 0) {
            gr.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "screenSize"));
            z = false;
        }
        if ((resolveInfoResolveActivity.activityInfo.configChanges & 2048) != 0) {
            return z;
        }
        gr.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "smallestScreenSize"));
        return false;
    }

    public static void q(Context context) {
        if (wE) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        context.getApplicationContext().registerReceiver(new a(), intentFilter);
        wE = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String r(Context context) {
        return new WebView(context).getSettings().getUserAgentString();
    }

    public static int s(Context context) {
        int i;
        int top = 0;
        if (context instanceof Activity) {
            Window window = ((Activity) context).getWindow();
            Rect rect = new Rect();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            i = rect.top;
            top = window.findViewById(R.id.content).getTop() - i;
        } else {
            i = 0;
        }
        return top + i;
    }

    public static JSONObject t(Map<String, ?> map) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            for (String str : map.keySet()) {
                a(jSONObject, str, map.get(str));
            }
            return jSONObject;
        } catch (ClassCastException e) {
            throw new JSONException("Could not convert map to JSON: " + e.getMessage());
        }
    }

    public static int[] t(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        float f = 160.0f / displayMetrics.densityDpi;
        return new int[]{(int) (displayMetrics.widthPixels * f), (int) (f * displayMetrics.heightPixels)};
    }
}
