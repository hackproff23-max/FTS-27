package com.firsttouchgames.fts15;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.os.Vibrator;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.InputDevice;
import com.google.android.gms.auth.GoogleAuthUtil;
import java.io.File;
import java.io.FileFilter;
import java.util.Currency;
import java.util.Locale;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class DeviceManager {
    public static MainActivity mActivity = null;
    public static Intent batteryStatus = null;
    static boolean bControllerConnected = false;
    static int iControllerButtons = 0;
    static float fCtrollerAxisX = 0.0f;
    static float fCtrollerAxisY = 0.0f;

    public static void SetActivity(MainActivity activity) {
        mActivity = activity;
        IntentFilter ifilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        batteryStatus = activity.getApplicationContext().registerReceiver(null, ifilter);
    }

    public static int GetinternetConnectivity() {
        ConnectivityManager cm = (ConnectivityManager) mActivity.getApplicationContext().getSystemService("connectivity");
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (!isConnected) {
            return 0;
        }
        boolean isWiFi = activeNetwork.getType() == 1;
        return isWiFi ? 1 : 2;
    }

    public static String GetDeviceID() {
        String sRet;
        int iPath;
        if (mActivity == null) {
            sRet = null;
            iPath = 1;
        } else {
            String strDeviceID = Settings.Secure.getString(mActivity.getApplicationContext().getContentResolver(), "android_id");
            if (strDeviceID == "9774d56d682e549c") {
                TelephonyManager tManager = (TelephonyManager) mActivity.getSystemService("phone");
                String strSerial = tManager.getDeviceId();
                if (strSerial == null) {
                    sRet = null;
                    iPath = 2;
                } else {
                    sRet = strSerial;
                    iPath = 3;
                }
            } else {
                sRet = strDeviceID;
                iPath = 4;
            }
        }
        Log.d("EB:", " " + sRet + " " + iPath);
        return sRet;
    }

    public static String GetDeviceType() {
        return Build.MANUFACTURER + " " + Build.MODEL;
    }

    public static String GetOS() {
        return Build.VERSION.RELEASE;
    }

    public static String GetBoard() {
        return Build.BOARD;
    }

    public static String GetDisplay() {
        return Build.DISPLAY;
    }

    public static String GetHardware() {
        return Build.HARDWARE;
    }

    public static String GetDeviceTypeAndOS() {
        return Build.MANUFACTURER + " " + Build.MODEL + " " + Build.VERSION.RELEASE;
    }

    public static String GetDeviceInformationString() {
        String sInfo = "Device ID: " + GetDeviceID() + ", Device Type: " + GetDeviceType() + ", OS: " + GetOS() + ", Board: " + GetBoard() + ", Display: " + GetDisplay() + ", Hardware: " + GetHardware();
        return sInfo;
    }

    public static String GetAccountUsername() {
        AccountManager accountManager = AccountManager.get(mActivity.getApplicationContext());
        Account[] accounts = accountManager.getAccountsByType(GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
        if (accounts[0] != null) {
            return accounts[0].name;
        }
        return null;
    }

    public static String GetDefaultLanguageISO3() {
        return mActivity.getApplicationContext().getResources().getConfiguration().locale.toString();
    }

    public static float GetDeviceBatteryLevel() {
        int level = batteryStatus.getIntExtra("level", -1);
        int iscale = batteryStatus.getIntExtra("scale", -1);
        float batteryPct = level / iscale;
        return batteryPct;
    }

    public static String GetCurrencyCode() {
        Locale locale = mActivity.getApplicationContext().getResources().getConfiguration().locale;
        Currency currency = Currency.getInstance(locale);
        return currency.getCurrencyCode();
    }

    public static int GetAvailableMemory() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        long lFreeMB = (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / 1048576;
        Log.d("MEM", "Free Memory: " + lFreeMB + "MB");
        return (int) lFreeMB;
    }

    public static long GetElapsedRealtime() {
        return SystemClock.elapsedRealtime() / 1000;
    }

    public static void Vibrate(int iMilliSeconds) {
        Vibrator v = (Vibrator) mActivity.getSystemService("vibrator");
        v.vibrate(iMilliSeconds);
    }

    public static float ConversionDPtoPX(int dp) {
        DisplayMetrics metrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return (dp * metrics.density) + 0.5f;
    }

    public static void CheckControllerConnected() {
        int[] iDevices = InputDevice.getDeviceIds();
        for (int i : iDevices) {
            InputDevice device = InputDevice.getDevice(i);
            if (device != null) {
                int iSrouce = device.getSources();
                if ((iSrouce & 1025) == 1025) {
                    Log.d("", "");
                }
                if ((iSrouce & 16) == 16) {
                    Log.d("", "");
                }
            }
        }
    }

    public static void SetControllerConnected(boolean bConected) {
        bControllerConnected = bConected;
    }

    public static void SetControllerButtons(int iButtons) {
        iControllerButtons |= iButtons;
    }

    public static void ClearControllerButtons(int iButtons) {
        iControllerButtons &= iButtons ^ (-1);
    }

    public static void SetControllerAxisX(float fVal) {
        fCtrollerAxisX = fVal;
    }

    public static void SetControllerAxisY(float fVal) {
        fCtrollerAxisY = fVal;
    }

    public static boolean GetControllerConnected() {
        return bControllerConnected;
    }

    public static int GetControllerButtons() {
        return iControllerButtons;
    }

    public static float GetControllerAxisX() {
        return fCtrollerAxisX;
    }

    public static float GetControllerAxisY() {
        return fCtrollerAxisY;
    }

    public static int GetCPUCores() {
        try {
            File dir = new File("/sys/devices/system/cpu/");
            File[] files = dir.listFiles(new FileFilter() { // from class: com.firsttouchgames.fts15.DeviceManager.1CpuFilter
                @Override // java.io.FileFilter
                public boolean accept(File pathname) {
                    return Pattern.matches("cpu[0-9]+", pathname.getName());
                }
            });
            return files.length;
        } catch (Exception e) {
            return 1;
        }
    }
}
