package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ba {
    private static com.chartboost.sdk.Libraries.e.a g = null;
    private String a;
    private String b;
    private com.chartboost.sdk.Libraries.e.a c;
    private Map<String, Object> d;
    private Map<String, Object> e;
    private int m;
    private c h = null;
    private boolean i = false;
    private boolean j = false;
    private com.chartboost.sdk.Libraries.g.a k = null;
    private boolean n = false;
    private boolean o = true;
    private l.a p = l.a.NORMAL;
    private String f = UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_POST;
    private bb l = bb.a(com.chartboost.sdk.b.l());

    public static abstract class b implements c {
    }

    public interface c {
        void a(com.chartboost.sdk.Libraries.e.a aVar, ba baVar);

        void a(com.chartboost.sdk.Libraries.e.a aVar, ba baVar, CBError cBError);
    }

    public ba(String str) {
        this.a = str;
        a(0);
    }

    protected void a() {
        if (this.e == null) {
            this.e = new HashMap();
        }
        this.e.put("Accept", "application/json");
        this.e.put("X-Chartboost-Client", CBUtility.d());
        this.e.put("X-Chartboost-API", "5.1.1");
        this.e.put("X-Chartboost-Client", CBUtility.d());
    }

    protected String b() {
        return "application/json";
    }

    public void a(String str, Object obj) {
        if (this.c == null) {
            this.c = com.chartboost.sdk.Libraries.e.a.a();
        }
        this.c.a(str, obj);
    }

    public void a(String str, String str2) {
        if (this.e == null) {
            this.e = new HashMap();
        }
        this.e.put(str, str2);
    }

    public void a(String str, com.chartboost.sdk.Libraries.e.a aVar) {
        if (aVar != null && aVar.c(str)) {
            a(str, (Object) aVar.e(str));
        }
    }

    protected void a(Context context) {
        String strSubstring;
        String strSubstring2 = null;
        a("app", (Object) com.chartboost.sdk.b.b());
        if ("sdk".equals(Build.PRODUCT)) {
            a("model", (Object) "Android Simulator");
        } else {
            a("model", (Object) Build.MODEL);
        }
        a("device_type", (Object) (Build.MANUFACTURER + " " + Build.MODEL));
        a("os", (Object) ("Android " + Build.VERSION.RELEASE));
        a("country", (Object) Locale.getDefault().getCountry());
        a("language", (Object) Locale.getDefault().getLanguage());
        a("sdk", (Object) "5.1.1");
        a("timestamp", (Object) String.valueOf(Long.valueOf(new Date().getTime() / 1000).intValue()));
        a("session", Integer.valueOf(CBUtility.a().getInt("cbPrefSessionCount", 0)));
        a("reachability", Integer.valueOf(az.a().b()));
        b(context);
        a("scale", (Object) ("" + context.getResources().getDisplayMetrics().density));
        try {
            String packageName = context.getPackageName();
            a("bundle", (Object) context.getPackageManager().getPackageInfo(packageName, 128).versionName);
            a("bundle_id", (Object) packageName);
        } catch (Exception e) {
            CBLogging.b("CBRequest", "Exception raised getting package mager object", e);
        }
        if (g == null) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null && telephonyManager.getPhoneType() != 0) {
                String simOperator = telephonyManager.getSimOperator();
                if (TextUtils.isEmpty(simOperator)) {
                    strSubstring = null;
                } else {
                    strSubstring = simOperator.substring(0, 3);
                    strSubstring2 = simOperator.substring(3);
                }
                g = com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("carrier-name", telephonyManager.getNetworkOperatorName()), com.chartboost.sdk.Libraries.e.a("mobile-country-code", strSubstring), com.chartboost.sdk.Libraries.e.a("mobile-network-code", strSubstring2), com.chartboost.sdk.Libraries.e.a("iso-country-code", telephonyManager.getNetworkCountryIso()), com.chartboost.sdk.Libraries.e.a("phone-type", Integer.valueOf(telephonyManager.getPhoneType())));
            } else {
                g = com.chartboost.sdk.Libraries.e.a.a();
            }
        }
        a("carrier", (Object) g);
        a("custom_id", (Object) com.chartboost.sdk.b.k());
    }

    public void c() {
        a("identity", (Object) com.chartboost.sdk.Libraries.c.b());
        com.chartboost.sdk.Libraries.c.a aVarC = com.chartboost.sdk.Libraries.c.c();
        if (aVarC.b()) {
            a("tracking", Integer.valueOf(aVarC.a()));
        }
    }

    public void d() {
        String strB = com.chartboost.sdk.b.b();
        String strB2 = com.chartboost.sdk.Libraries.b.b(com.chartboost.sdk.Libraries.b.a(String.format(Locale.US, "%s %s\n%s\n%s", this.f, e(), com.chartboost.sdk.b.c(), f()).getBytes()));
        a("X-Chartboost-App", strB);
        a("X-Chartboost-Signature", strB2);
    }

    public String e() {
        return g() + CBUtility.a(this.d);
    }

    public String f() {
        return this.c.toString();
    }

    public void b(Context context) {
        Exception exc;
        int i;
        int iHeight;
        int i2 = 0;
        if (this.c == null || !this.c.a("w").c() || !this.c.a("h").c()) {
            try {
                if (context instanceof Activity) {
                    Rect rect = new Rect();
                    ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                    int iWidth = rect.width();
                    try {
                        iHeight = rect.height();
                        i2 = iWidth;
                    } catch (Exception e) {
                        i = iWidth;
                        exc = e;
                        CBLogging.c("CBRequest", "Exception getting activity size", exc);
                        i2 = i;
                        iHeight = 0;
                    }
                } else {
                    iHeight = 0;
                }
            } catch (Exception e2) {
                exc = e2;
                i = 0;
            }
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            int i3 = displayMetrics.widthPixels;
            int i4 = displayMetrics.heightPixels;
            a("dw", (Object) ("" + i3));
            a("dh", (Object) ("" + i4));
            a("dpi", (Object) ("" + displayMetrics.densityDpi));
            if (i2 <= 0 || i2 > i3) {
                i2 = i3;
            }
            if (iHeight <= 0 || iHeight > i4) {
                iHeight = i4;
            }
            a("w", (Object) ("" + i2));
            a("h", (Object) ("" + iHeight));
        }
    }

    public String g() {
        if (this.a == null) {
            return "/";
        }
        return (this.a.startsWith("/") ? "" : "/") + this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public boolean h() {
        return g().equals("/api/track");
    }

    public com.chartboost.sdk.Libraries.e.a i() {
        return this.c;
    }

    public Map<String, Object> j() {
        return this.e;
    }

    public boolean k() {
        return this.j;
    }

    public void a(boolean z) {
        this.j = z;
    }

    public com.chartboost.sdk.Libraries.g.a l() {
        return this.k;
    }

    public boolean m() {
        return this.n;
    }

    public void b(boolean z) {
        this.n = z;
    }

    public void a(com.chartboost.sdk.Libraries.g.a aVar) {
        if (!com.chartboost.sdk.Libraries.g.c(aVar)) {
            throw new IllegalArgumentException("Validation predicate must be a dictionary style -- either VDictionary, VDictionaryExact, VDictionaryWithValues, or just a list of KV pairs.");
        }
        this.k = aVar;
    }

    public void a(com.chartboost.sdk.Libraries.g.k... kVarArr) {
        this.k = com.chartboost.sdk.Libraries.g.a(kVarArr);
    }

    public void b(String str) {
        this.b = str;
    }

    public void a(l.a aVar) {
        this.p = aVar;
    }

    public l.a n() {
        return this.p;
    }

    public int o() {
        return this.m;
    }

    public void a(int i) {
        this.m = i;
    }

    public boolean p() {
        return this.o;
    }

    public void c(boolean z) {
        this.o = z;
    }

    public boolean q() {
        return this.i;
    }

    public void d(boolean z) {
        this.i = z;
    }

    public c r() {
        return this.h;
    }

    public static abstract class d implements c {
        @Override // com.chartboost.sdk.impl.ba.c
        public void a(com.chartboost.sdk.Libraries.e.a aVar, ba baVar, CBError cBError) {
        }
    }

    private static class a implements c {
        private d a;
        private b b;

        public a(d dVar, b bVar) {
            this.a = dVar;
            this.b = bVar;
        }

        @Override // com.chartboost.sdk.impl.ba.c
        public void a(com.chartboost.sdk.Libraries.e.a aVar, ba baVar) {
            if (this.a != null) {
                this.a.a(aVar, baVar);
            }
        }

        @Override // com.chartboost.sdk.impl.ba.c
        public void a(com.chartboost.sdk.Libraries.e.a aVar, ba baVar, CBError cBError) {
            if (this.b != null) {
                this.b.a(aVar, baVar, cBError);
            }
        }
    }

    public void s() {
        a((d) null, (b) null);
    }

    public void a(c cVar) {
        this.h = cVar;
        d(true);
        this.l.a(this, cVar);
    }

    public void a(d dVar, b bVar) {
        d(true);
        this.h = new a(dVar, bVar);
        this.l.a(this, this.h);
    }

    public void b(c cVar) {
        if (cVar != null) {
            this.h = cVar;
        }
    }

    public static ba a(com.chartboost.sdk.Libraries.e.a aVar) {
        try {
            ba baVar = new ba(aVar.e("path"));
            baVar.f = aVar.e("method");
            baVar.d = aVar.a("query").f();
            baVar.c = aVar.a(UnityAdsConstants.UNITY_ADS_FAILED_URL_BODY_KEY);
            baVar.e = aVar.a("headers").f();
            baVar.j = aVar.i("ensureDelivery");
            baVar.b = aVar.e(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_TYPE_KEY);
            baVar.a = aVar.e("path");
            baVar.m = aVar.f("retryCount");
            if (aVar.a("callback") instanceof c) {
                baVar.h = (c) aVar.a("callback");
            }
            return baVar;
        } catch (Exception e) {
            CBLogging.d("CBRequest", "Unable to deserialize failed request", e);
            return null;
        }
    }

    public com.chartboost.sdk.Libraries.e.a t() {
        return com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("path", this.a), com.chartboost.sdk.Libraries.e.a("method", this.f), com.chartboost.sdk.Libraries.e.a("query", com.chartboost.sdk.Libraries.e.a(this.d)), com.chartboost.sdk.Libraries.e.a(UnityAdsConstants.UNITY_ADS_FAILED_URL_BODY_KEY, this.c), com.chartboost.sdk.Libraries.e.a(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_TYPE_KEY, this.b), com.chartboost.sdk.Libraries.e.a("headers", com.chartboost.sdk.Libraries.e.a(this.e)), com.chartboost.sdk.Libraries.e.a("ensureDelivery", Boolean.valueOf(this.j)), com.chartboost.sdk.Libraries.e.a("retryCount", Integer.valueOf(this.m)), com.chartboost.sdk.Libraries.e.a("callback", this.h));
    }
}
