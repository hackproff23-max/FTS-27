package com.unity3d.ads.android.data;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import java.net.NetworkInterface;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(9)
public class UnityAdsDevice {
    public static String getSoftwareVersion() {
        return new StringBuilder().append(Build.VERSION.SDK_INT).toString();
    }

    public static String getHardwareVersion() {
        return Build.MANUFACTURER + " " + Build.MODEL;
    }

    public static int getDeviceType() {
        return UnityAdsProperties.getCurrentActivity().getResources().getConfiguration().screenLayout;
    }

    @SuppressLint({"DefaultLocale"})
    public static String getAndroidId(boolean z) {
        try {
            String string = Settings.Secure.getString(UnityAdsProperties.getCurrentActivity().getContentResolver(), "android_id");
            if (z) {
                return UnityAdsUtils.Md5(string).toLowerCase();
            }
            return string;
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Problems fetching androidId: " + e.getMessage());
            return "unknown";
        }
    }

    public static String getAndroidSerial() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getMethod("get", String.class).invoke(cls, "ro.serialno");
            try {
                return UnityAdsUtils.Md5(str).toLowerCase();
            } catch (Exception e) {
                return str;
            }
        } catch (Exception e2) {
            return "unknown";
        }
    }

    public static String getMacAddress() {
        NetworkInterface interfaceFor = getInterfaceFor("eth0");
        if (interfaceFor == null) {
            interfaceFor = getInterfaceFor("wlan0");
        }
        return buildMacAddressFromInterface(interfaceFor);
    }

    public static String getAdvertisingTrackingId() {
        return UnityAdsAdvertisingId.getAdvertisingTrackingId();
    }

    public static boolean isLimitAdTrackingEnabled() {
        return UnityAdsAdvertisingId.getLimitedAdTracking();
    }

    @SuppressLint({"DefaultLocale"})
    public static String buildMacAddressFromInterface(NetworkInterface networkInterface) {
        byte[] bArr = null;
        if (networkInterface == null) {
            return "unknown";
        }
        try {
            bArr = (byte[]) NetworkInterface.class.getMethod("getHardwareAddress", new Class[0]).invoke(networkInterface, new Object[0]);
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Could not getHardwareAddress");
        }
        if (bArr == null) {
            return "unknown";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(String.format("%02X:", Byte.valueOf(b)));
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return UnityAdsUtils.Md5(sb.toString()).toLowerCase();
    }

    public static NetworkInterface getInterfaceFor(String str) {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (str != null && networkInterface.getName().equalsIgnoreCase(str)) {
                    UnityAdsDeviceLog.debug("Returning interface: " + networkInterface.getName());
                    return networkInterface;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getConnectionType() {
        return isUsingWifi() ? "wifi" : "cellular";
    }

    public static boolean isUsingWifi() {
        ConnectivityManager connectivityManager;
        Activity currentActivity = UnityAdsProperties.getCurrentActivity();
        if (currentActivity != null && (connectivityManager = (ConnectivityManager) currentActivity.getSystemService("connectivity")) != null) {
            TelephonyManager telephonyManager = null;
            if (currentActivity != null) {
                telephonyManager = (TelephonyManager) currentActivity.getSystemService("phone");
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !connectivityManager.getBackgroundDataSetting() || !connectivityManager.getActiveNetworkInfo().isConnected() || telephonyManager == null) {
                return false;
            }
            if (activeNetworkInfo.getType() == 1) {
                return activeNetworkInfo.isConnected();
            }
            return false;
        }
        return false;
    }

    public static int getScreenDensity() {
        return UnityAdsProperties.getCurrentActivity().getResources().getDisplayMetrics().densityDpi;
    }

    public static int getScreenSize() {
        return getDeviceType();
    }
}
