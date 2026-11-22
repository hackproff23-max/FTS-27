package com.applovin.impl.sdk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.applovin.sdk.AppLovinLogger;
import com.mopub.common.GpsHelper;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
class q {
    private final AppLovinSdkImpl a;
    private final Context b;
    private final AppLovinLogger c;

    q(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = appLovinSdkImpl;
        this.c = appLovinSdkImpl.getLogger();
        this.b = appLovinSdkImpl.getApplicationContext();
    }

    static boolean a(String str, Context context) {
        if (str == null) {
            throw new IllegalArgumentException("No permission name specified");
        }
        if (context == null) {
            throw new IllegalArgumentException("No context specified");
        }
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }

    t a() {
        TelephonyManager telephonyManager;
        t tVar = new t();
        tVar.h = Locale.getDefault();
        tVar.a = Build.MODEL;
        tVar.b = Build.VERSION.RELEASE;
        tVar.c = Build.MANUFACTURER;
        tVar.e = Build.VERSION.SDK_INT;
        tVar.d = Build.DEVICE;
        if (a("android.permission.READ_PHONE_STATE") && (telephonyManager = (TelephonyManager) this.b.getSystemService("phone")) != null) {
            tVar.f = telephonyManager.getSimCountryIso().toUpperCase(Locale.ENGLISH);
            String networkOperatorName = telephonyManager.getNetworkOperatorName();
            try {
                tVar.g = URLEncoder.encode(networkOperatorName, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                tVar.g = networkOperatorName;
            }
        }
        return tVar;
    }

    boolean a(String str) {
        return a(str, this.b);
    }

    s b() {
        ApplicationInfo applicationInfo = this.b.getApplicationInfo();
        long jLastModified = new File(applicationInfo.sourceDir).lastModified();
        PackageManager packageManager = this.b.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(this.b.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
        }
        s sVar = new s();
        sVar.c = applicationInfo.packageName;
        sVar.d = jLastModified;
        sVar.a = String.valueOf(packageManager.getApplicationLabel(applicationInfo));
        sVar.b = packageInfo != null ? packageInfo.versionName : "";
        return sVar;
    }

    r c() {
        Object objInvoke;
        try {
            Class<?> cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            if (cls != null && (objInvoke = cls.getMethod("getAdvertisingIdInfo", Context.class).invoke(null, this.b)) != null) {
                Class<?> cls2 = objInvoke.getClass();
                Object objInvoke2 = cls2.getMethod(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, null).invoke(objInvoke, null);
                Object objInvoke3 = cls2.getMethod("getId", null).invoke(objInvoke, null);
                r rVar = new r();
                String str = (String) objInvoke3;
                String str2 = str == null ? "" : str;
                rVar.a = ((Boolean) objInvoke2).booleanValue();
                rVar.b = str2;
                return rVar;
            }
        } catch (ClassNotFoundException e) {
            this.c.userError("DataCollector", "Could not collect Google Advertising ID - this will negatively impact your eCPMs! Please integrate the Google Play Services SDK into your application. More info can be found online at http://developer.android.com/google/play-services/setup.html. If you're sure you've integrated the SDK and are still seeing this message, you may need to add a ProGuard exception: -keep public class com.google.android.gms.** { public protected *; }", e);
        } catch (Throwable th) {
            this.c.e("DataCollector", "Could not collect Google Advertising ID - this will negatively impact your eCPMs! Please integrate the Google Play Services SDK into your application. More info can be found online at http://developer.android.com/google/play-services/setup.html. If you're sure you've integrated the SDK and are still seeing this message, you may need to add a ProGuard exception: -keep public class com.google.android.gms.** { public protected *; }", th);
        }
        r rVar2 = new r();
        rVar2.b = "";
        rVar2.a = false;
        return rVar2;
    }
}
