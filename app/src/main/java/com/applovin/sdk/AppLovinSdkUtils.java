package com.applovin.sdk;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class AppLovinSdkUtils {
    private static boolean a(Context context) {
        Bundle bundleE = e(context);
        return bundleE != null && bundleE.getBoolean("applovin.sdk.verbose_logging", false);
    }

    private static long b(Context context) {
        Bundle bundleE = e(context);
        if (bundleE != null) {
            return bundleE.getInt("applovin.sdk.ad_refresh_seconds", -100);
        }
        return -100L;
    }

    private static String c(Context context) {
        String string;
        Bundle bundleE = e(context);
        return (bundleE == null || (string = bundleE.getString("applovin.sdk.auto_preload_ad_sizes")) == null) ? "BANNER,INTER" : string;
    }

    private static String d(Context context) {
        String string;
        Bundle bundleE = e(context);
        return (bundleE == null || (string = bundleE.getString("applovin.sdk.auto_preload_ad_types")) == null) ? AppLovinAdType.REGULAR.getLabel() + "," + AppLovinAdType.INCENTIVIZED.getLabel() : string;
    }

    private static Bundle e(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(AppLovinLogger.SDK_TAG, "Unable to retrieve application metadata", e);
            return null;
        }
    }

    public static String retrieveSdkKey(Context context) {
        Bundle bundleE = e(context);
        if (bundleE == null) {
            return null;
        }
        String string = bundleE.getString("applovin.sdk.key");
        return string != null ? string : "";
    }

    public static AppLovinSdkSettings retrieveUserSettings(Context context) {
        AppLovinSdkSettings appLovinSdkSettings = new AppLovinSdkSettings();
        appLovinSdkSettings.setVerboseLogging(a(context));
        appLovinSdkSettings.setBannerAdRefreshSeconds(b(context));
        appLovinSdkSettings.setAutoPreloadSizes(c(context));
        appLovinSdkSettings.setAutoPreloadTypes(d(context));
        return appLovinSdkSettings;
    }
}
