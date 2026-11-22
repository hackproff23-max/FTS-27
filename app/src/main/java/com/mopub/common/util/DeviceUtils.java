package com.mopub.common.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StatFs;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.DisplayMetrics;
import com.mopub.common.logging.MoPubLog;
import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Locale;
import org.apache.http.conn.util.InetAddressUtils;

/* JADX INFO: loaded from: classes.dex */
public class DeviceUtils {
    private static final int MAX_DISK_CACHE_SIZE = 104857600;
    private static final int MAX_MEMORY_CACHE_SIZE = 31457280;
    private static final int MIN_DISK_CACHE_SIZE = 31457280;

    private DeviceUtils() {
    }

    public enum IP {
        IPv4,
        IPv6;

        /* JADX INFO: Access modifiers changed from: private */
        public boolean matches(String address) {
            switch (this) {
                case IPv4:
                    return InetAddressUtils.isIPv4Address(address);
                case IPv6:
                    return InetAddressUtils.isIPv6Address(address);
                default:
                    return false;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String toString(String address) {
            switch (this) {
                case IPv4:
                    return address;
                case IPv6:
                    return address.split("%")[0];
                default:
                    return null;
            }
        }
    }

    public static String getIpAddress(IP ip) throws SocketException {
        for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
            for (InetAddress address : Collections.list(networkInterface.getInetAddresses())) {
                if (!address.isLoopbackAddress()) {
                    String hostAddress = address.getHostAddress().toUpperCase(Locale.US);
                    if (ip.matches(hostAddress)) {
                        return ip.toString(hostAddress);
                    }
                }
            }
        }
        return null;
    }

    public static String getHashedUdid(Context context) {
        if (context == null) {
            return null;
        }
        String udid = Settings.Secure.getString(context.getContentResolver(), "android_id");
        return Utils.sha1(udid);
    }

    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        }
        int internetPermission = context.checkCallingOrSelfPermission("android.permission.INTERNET");
        if (internetPermission == -1) {
            return false;
        }
        int networkAccessPermission = context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE");
        if (networkAccessPermission == -1) {
            return true;
        }
        try {
            ConnectivityManager connnectionManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkInfo networkInfo = connnectionManager.getActiveNetworkInfo();
            return networkInfo.isConnected();
        } catch (NullPointerException e) {
            return false;
        }
    }

    public static int memoryCacheSizeBytes(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        long memoryClass = activityManager.getMemoryClass();
        if (VersionCode.currentApiLevel().isAtLeast(VersionCode.HONEYCOMB)) {
            try {
                int flagLargeHeap = ApplicationInfo.class.getDeclaredField("FLAG_LARGE_HEAP").getInt(null);
                if (Utils.bitMaskContainsFlag(context.getApplicationInfo().flags, flagLargeHeap)) {
                    memoryClass = ((Integer) new Reflection.MethodBuilder(activityManager, "getLargeMemoryClass").execute()).intValue();
                }
            } catch (Exception e) {
                MoPubLog.d("Unable to reflectively determine large heap size on Honeycomb and above.");
            }
        }
        long result = Math.min(31457280L, (memoryClass / 8) * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID);
        return (int) result;
    }

    public static long diskCacheSizeBytes(File dir, long minSize) {
        long size = minSize;
        try {
            StatFs statFs = new StatFs(dir.getAbsolutePath());
            long availableBytes = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
            size = availableBytes / 50;
        } catch (IllegalArgumentException e) {
            MoPubLog.d("Unable to calculate 2% of available disk space, defaulting to minimum");
        }
        return Math.max(Math.min(size, 104857600L), 31457280L);
    }

    public static long diskCacheSizeBytes(File dir) {
        return diskCacheSizeBytes(dir, 31457280L);
    }

    public static int getScreenOrientation(@NonNull Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        boolean isPortrait = ((rotation == 0 || rotation == 2) && height > width) || ((rotation == 1 || rotation == 3) && width > height);
        if (isPortrait) {
            switch (rotation) {
                case 0:
                    return 1;
                case 1:
                    return 0;
                case 2:
                    return 9;
                case 3:
                    return 8;
                default:
                    MoPubLog.d("Unknown screen orientation. Defaulting to portrait.");
                    return 1;
            }
        }
        switch (rotation) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 8;
            case 3:
                return 9;
            default:
                MoPubLog.d("Unknown screen orientation. Defaulting to landscape.");
                return 0;
        }
    }
}
