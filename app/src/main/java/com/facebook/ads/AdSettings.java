package com.facebook.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import com.facebook.ads.a.ag;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class AdSettings {
    private static final String DEVICE_ID_HASH_PREFS_KEY = "deviceIdHash";
    private static final String PREFS_NAME = "FBAdPrefs";
    static volatile boolean testDeviceNoticeDisplayed;
    public static final String TAG = AdSettings.class.getSimpleName();
    private static String urlPrefix = null;
    private static boolean childDirected = false;
    private static String deviceIdHash = null;
    private static final Collection testDevices = new HashSet();
    private static final Collection emulatorProducts = new HashSet();

    static {
        emulatorProducts.add("sdk");
        emulatorProducts.add("google_sdk");
        emulatorProducts.add("vbox86p");
        emulatorProducts.add("vbox86tp");
        testDeviceNoticeDisplayed = false;
    }

    public static void addTestDevice(String str) {
        testDevices.add(str);
    }

    public static void addTestDevices(Collection collection) {
        testDevices.addAll(collection);
    }

    public static void clearTestDevices() {
        testDevices.clear();
    }

    public static String getUrlPrefix() {
        return urlPrefix;
    }

    public static boolean isChildDirected() {
        return childDirected;
    }

    public static boolean isTestMode(Context context) {
        if (emulatorProducts.contains(Build.PRODUCT)) {
            return true;
        }
        if (deviceIdHash == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
            deviceIdHash = sharedPreferences.getString(DEVICE_ID_HASH_PREFS_KEY, null);
            if (ag.a(deviceIdHash)) {
                deviceIdHash = ag.b(UUID.randomUUID().toString());
                sharedPreferences.edit().putString(DEVICE_ID_HASH_PREFS_KEY, deviceIdHash).apply();
            }
        }
        if (testDevices.contains(deviceIdHash)) {
            return true;
        }
        printTestDeviceNotice(deviceIdHash);
        return false;
    }

    private static void printTestDeviceNotice(String str) {
        if (testDeviceNoticeDisplayed) {
            return;
        }
        testDeviceNoticeDisplayed = true;
        Log.d(TAG, "Test mode device hash: " + str);
        Log.d(TAG, "When testing your app with Facebook's ad units you must specify the device hashed ID to ensure the delivery of test ads, add the following code before loading an ad: AdSettings.addTestDevice(\"" + str + "\");");
    }

    public static void setIsChildDirected(boolean z) {
        childDirected = z;
    }

    public static void setUrlPrefix(String str) {
        urlPrefix = str;
    }
}
