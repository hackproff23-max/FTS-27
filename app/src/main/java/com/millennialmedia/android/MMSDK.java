package com.millennialmedia.android;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import org.apache.http.conn.util.InetAddressUtils;

/* JADX INFO: loaded from: classes.dex */
public final class MMSDK {
    private static final String BASE_URL_TRACK_EVENT = "http://ads.mp.mydas.mobi/pixel?id=";
    static final int CACHE_REQUEST_TIMEOUT = 30000;
    static final int CLOSE_ACTIVITY_DURATION = 400;
    public static final String DEFAULT_APID = "28911";
    public static final String DEFAULT_BANNER_APID = "28913";
    public static final String DEFAULT_RECT_APID = "28914";
    static final String EMPTY = "";
    static final int HANDSHAKE_REQUEST_TIMEOUT = 3000;
    static final String JSON_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss ZZZZ";
    public static final int LOG_LEVEL_DEBUG = 1;
    public static final int LOG_LEVEL_ERROR = 0;
    public static final int LOG_LEVEL_INFO = 2;

    @Deprecated
    public static final int LOG_LEVEL_INTERNAL = 4;

    @Deprecated
    public static final int LOG_LEVEL_PRIVATE_VERBOSE = 5;
    public static final int LOG_LEVEL_VERBOSE = 3;
    static final int OPEN_ACTIVITY_DURATION = 600;
    static final String PREFS_NAME = "MillennialMediaSettings";
    static final int REQUEST_TIMEOUT = 10000;
    public static final String SDKLOG = "MMSDK";
    public static final String VERSION = "5.3.0-c3980670.a";
    private static boolean hasSpeechKit;
    private static boolean isBroadcastingEvents;
    static int logLevel;
    static String macId;

    @Deprecated
    static boolean disableAdMinRefresh = false;
    private static int nextDefaultId = 1897808289;
    static String COMMA = ",";
    static Handler mainHandler = new Handler(Looper.getMainLooper());
    private static String getMMdidValue = null;

    private MMSDK() {
    }

    static {
        hasSpeechKit = false;
        try {
            System.loadLibrary("nmsp_speex");
            hasSpeechKit = true;
        } catch (UnsatisfiedLinkError e) {
        }
    }

    public static int getDefaultAdId() {
        int i;
        synchronized (MMSDK.class) {
            i = nextDefaultId + 1;
            nextDefaultId = i;
        }
        return i;
    }

    public static void resetCache(Context context) {
        AdCache.resetCache(context);
    }

    public static void setBroadcastEvents(boolean enable) {
        isBroadcastingEvents = enable;
    }

    public static boolean getBroadcastEvents() {
        return isBroadcastingEvents;
    }

    @Deprecated
    public static void setLogLevel(int level) {
        switch (level) {
            case 0:
                MMLog.setLogLevel(6);
                break;
            case 1:
                MMLog.setLogLevel(3);
                break;
            case 2:
                MMLog.setLogLevel(4);
                break;
            case 3:
                MMLog.setLogLevel(2);
                break;
            default:
                MMLog.setLogLevel(4);
                break;
        }
    }

    @Deprecated
    public static int getLogLevel() {
        return MMLog.getLogLevel();
    }

    static class Event {
        public static final String INTENT_CALENDAR_EVENT = "calendar";
        public static final String INTENT_EMAIL = "email";
        public static final String INTENT_EXTERNAL_BROWSER = "browser";
        public static final String INTENT_MAPS = "geo";
        public static final String INTENT_MARKET = "market";
        public static final String INTENT_PHONE_CALL = "tel";
        public static final String INTENT_STREAMING_VIDEO = "streamingVideo";
        public static final String INTENT_TXT_MESSAGE = "sms";
        private static final String KEY_ERROR = "error";
        static final String KEY_INTENT_TYPE = "intentType";
        static final String KEY_INTERNAL_ID = "internalId";
        static final String KEY_PACKAGE_NAME = "packageName";
        protected static final String TAG = Event.class.getName();

        Event() {
        }

        protected static void logEvent(final String logString) {
            MMLog.d("Logging event to: %s", logString);
            Utils.ThreadUtils.execute(new Runnable() { // from class: com.millennialmedia.android.MMSDK.Event.1
                @Override // java.lang.Runnable
                public void run() {
                    HttpGetRequest getRequest = new HttpGetRequest();
                    try {
                        getRequest.get(logString);
                    } catch (Exception e) {
                        MMLog.e(Event.TAG, "Error logging event: ", e);
                    }
                }
            });
        }

        static void adSingleTap(final MMAdImpl adImpl) {
            if (adImpl != null) {
                MMSDK.runOnUiThread(new Runnable() { // from class: com.millennialmedia.android.MMSDK.Event.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (adImpl != null && adImpl.requestListener != null) {
                            try {
                                adImpl.requestListener.onSingleTap(adImpl.getCallingAd());
                            } catch (Exception exception) {
                                MMLog.e(MMSDK.SDKLOG, "Exception raised in your RequestListener: ", exception);
                            }
                        }
                    }
                });
                if (MMSDK.isBroadcastingEvents) {
                    sendIntent(adImpl.getContext(), new Intent(MMBroadcastReceiver.ACTION_OVERLAY_TAP), adImpl.internalId);
                    sendIntent(adImpl.getContext(), new Intent(MMBroadcastReceiver.ACTION_AD_SINGLE_TAP), adImpl.internalId);
                }
            }
        }

        static void intentStarted(Context context, String intentType, long adImplId) {
            if (MMSDK.isBroadcastingEvents && intentType != null) {
                sendIntent(context, new Intent(MMBroadcastReceiver.ACTION_INTENT_STARTED).putExtra(KEY_INTENT_TYPE, intentType), adImplId);
            }
        }

        static void fetchStartedCaching(final MMAdImpl adImpl) {
            if (adImpl == null) {
                MMLog.w(MMSDK.SDKLOG, "No Context in the listener: ");
                return;
            }
            MMSDK.runOnUiThread(new Runnable() { // from class: com.millennialmedia.android.MMSDK.Event.3
                @Override // java.lang.Runnable
                public void run() {
                    if (adImpl != null && adImpl.requestListener != null) {
                        try {
                            adImpl.requestListener.MMAdRequestIsCaching(adImpl.getCallingAd());
                        } catch (Exception e) {
                            MMLog.e(MMSDK.SDKLOG, "Exception raised in your RequestListener: ", e);
                        }
                    }
                }
            });
            if (MMSDK.isBroadcastingEvents) {
                sendIntent(adImpl.getContext(), new Intent(MMBroadcastReceiver.ACTION_FETCH_STARTED_CACHING), adImpl.internalId);
            }
        }

        static void displayStarted(MMAdImpl adImpl) {
            if (adImpl != null) {
                if (MMSDK.isBroadcastingEvents) {
                    sendIntent(adImpl.getContext(), new Intent(MMBroadcastReceiver.ACTION_DISPLAY_STARTED), adImpl.internalId);
                }
                overlayOpened(adImpl);
                return;
            }
            MMLog.w(MMSDK.SDKLOG, "No Context in the listener: ");
        }

        static void overlayOpened(final MMAdImpl adImpl) {
            if (adImpl == null) {
                MMLog.w(MMSDK.SDKLOG, "No Context in the listener: ");
            } else {
                MMSDK.runOnUiThread(new Runnable() { // from class: com.millennialmedia.android.MMSDK.Event.4
                    @Override // java.lang.Runnable
                    public void run() {
                        if (adImpl != null && adImpl.requestListener != null) {
                            try {
                                adImpl.requestListener.MMAdOverlayLaunched(adImpl.getCallingAd());
                            } catch (Exception exception) {
                                MMLog.e(MMSDK.SDKLOG, "Exception raised in your RequestListener: ", exception);
                            }
                        }
                    }
                });
                overlayOpenedBroadCast(adImpl.getContext(), adImpl.internalId);
            }
        }

        static void overlayOpenedBroadCast(Context context, long adImplId) {
            if (MMSDK.isBroadcastingEvents) {
                sendIntent(context, new Intent(MMBroadcastReceiver.ACTION_OVERLAY_OPENED), adImplId);
            }
        }

        static void overlayClosed(final MMAdImpl adImpl) {
            if (adImpl == null) {
                MMLog.w(MMSDK.SDKLOG, "No Context in the listener: ");
                return;
            }
            MMSDK.runOnUiThread(new Runnable() { // from class: com.millennialmedia.android.MMSDK.Event.5
                @Override // java.lang.Runnable
                public void run() {
                    if (adImpl != null && adImpl.requestListener != null) {
                        try {
                            adImpl.requestListener.MMAdOverlayClosed(adImpl.getCallingAd());
                        } catch (Exception exception) {
                            MMLog.e(MMSDK.SDKLOG, "Exception raised in your RequestListener: ", exception);
                        }
                    }
                }
            });
            if (MMSDK.isBroadcastingEvents && adImpl.getContext() != null) {
                sendIntent(adImpl.getContext(), new Intent(MMBroadcastReceiver.ACTION_OVERLAY_CLOSED), adImpl.internalId);
            }
        }

        static void requestCompleted(final MMAdImpl adImpl) {
            if (adImpl == null) {
                MMLog.w(MMSDK.SDKLOG, "No Context in the listener: ");
                return;
            }
            MMSDK.runOnUiThread(new Runnable() { // from class: com.millennialmedia.android.MMSDK.Event.6
                @Override // java.lang.Runnable
                public void run() {
                    if (adImpl != null && adImpl.requestListener != null) {
                        try {
                            adImpl.requestListener.requestCompleted(adImpl.getCallingAd());
                        } catch (Exception exception) {
                            MMLog.e(MMSDK.SDKLOG, "Exception raised in your RequestListener: ", exception);
                        }
                    }
                }
            });
            if (MMSDK.isBroadcastingEvents) {
                String action = adImpl.getRequestCompletedAction();
                sendIntent(adImpl.getContext(), new Intent(action), adImpl.internalId);
            }
        }

        static void requestFailed(final MMAdImpl adImpl, final MMException error) {
            if (adImpl == null) {
                MMLog.w(MMSDK.SDKLOG, "No Context in the listener: ");
                return;
            }
            MMSDK.runOnUiThread(new Runnable() { // from class: com.millennialmedia.android.MMSDK.Event.7
                @Override // java.lang.Runnable
                public void run() {
                    if (adImpl != null && adImpl.requestListener != null) {
                        try {
                            adImpl.requestListener.requestFailed(adImpl.getCallingAd(), error);
                        } catch (Exception exception) {
                            MMLog.e(MMSDK.SDKLOG, "Exception raised in your RequestListener: ", exception);
                        }
                    }
                }
            });
            if (MMSDK.isBroadcastingEvents) {
                String action = adImpl.getRequestFailedAction();
                sendIntent(adImpl.getContext(), new Intent(action).putExtra("error", error), adImpl.internalId);
            }
        }

        private static final void sendIntent(Context context, Intent intent, long adImplId) {
            String type;
            if (context != null) {
                intent.addCategory(MMBroadcastReceiver.CATEGORY_SDK);
                if (adImplId != -4) {
                    intent.putExtra(KEY_INTERNAL_ID, adImplId);
                }
                intent.putExtra(KEY_PACKAGE_NAME, context.getPackageName());
                String type2 = intent.getStringExtra(KEY_INTENT_TYPE);
                if (!TextUtils.isEmpty(type2)) {
                    type = String.format(" Type[%s]", type2);
                } else {
                    type = "";
                }
                MMLog.v(MMSDK.SDKLOG, " @@ Intent: " + intent.getAction() + " " + type + " for " + adImplId);
                context.sendBroadcast(intent);
            }
        }
    }

    static void runOnUiThread(Runnable action) {
        if (isUiThread()) {
            action.run();
        } else {
            mainHandler.post(action);
        }
    }

    static void runOnUiThreadDelayed(Runnable action, long delayMillis) {
        mainHandler.postDelayed(action, delayMillis);
    }

    static boolean isUiThread() {
        return mainHandler.getLooper() == Looper.myLooper();
    }

    static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    static String getIpAddress(Context context) {
        try {
            StringBuilder ips = new StringBuilder();
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = en.nextElement();
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        if (ips.length() > 0) {
                            ips.append(',');
                        }
                        String inetAddressHost = inetAddress.getHostAddress().toUpperCase();
                        boolean isIPv4 = InetAddressUtils.isIPv4Address(inetAddressHost);
                        if (isIPv4) {
                            ips.append(inetAddressHost);
                        } else {
                            int delim = inetAddressHost.indexOf(37);
                            String noInterfaceNameAddress = delim < 0 ? inetAddressHost : inetAddressHost.substring(0, delim);
                            ips.append(noInterfaceNameAddress);
                        }
                    }
                }
            }
            return ips.toString();
        } catch (Exception ex) {
            MMLog.e(SDKLOG, "Exception getting ip information: ", ex);
            return "";
        }
    }

    static String getConnectionType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return "unknown";
        }
        if (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected()) {
            int type = connectivityManager.getActiveNetworkInfo().getType();
            int subType = connectivityManager.getActiveNetworkInfo().getSubtype();
            if (type == 1) {
                return "wifi";
            }
            if (type == 0) {
                switch (subType) {
                    case 1:
                        return "gprs";
                    case 2:
                        return "edge";
                    case 3:
                        return "umts";
                    case 4:
                        return "cdma";
                    case 5:
                        return "evdo_0";
                    case 6:
                        return "evdo_a";
                    case 7:
                        return "1xrtt";
                    case 8:
                        return "hsdpa";
                    case 9:
                        return "hsupa";
                    case 10:
                        return "hspa";
                    case 11:
                        return "iden";
                    case 12:
                        return "evdo_b";
                    case 13:
                        return "lte";
                    case 14:
                        return "ehrpd";
                    case 15:
                        return "hspap";
                    default:
                        return "unknown";
                }
            }
            return "unknown";
        }
        return "offline";
    }

    static synchronized String getMMdid(Context context) {
        String string = null;
        synchronized (MMSDK.class) {
            try {
                if (getMMdidValue != null) {
                    string = getMMdidValue;
                } else {
                    String mmdid = Settings.Secure.getString(context.getContentResolver(), "android_id");
                    if (mmdid != null) {
                        StringBuilder mmdidBuilder = new StringBuilder("mmh_");
                        try {
                            MessageDigest md = MessageDigest.getInstance("MD5");
                            byte[] hashBytes = md.digest(mmdid.getBytes());
                            mmdidBuilder.append(byteArrayToString(hashBytes));
                            mmdidBuilder.append("_");
                            MessageDigest md2 = MessageDigest.getInstance("SHA1");
                            byte[] hashBytes2 = md2.digest(mmdid.getBytes());
                            mmdidBuilder.append(byteArrayToString(hashBytes2));
                            string = mmdidBuilder.toString();
                            getMMdidValue = string;
                        } catch (Exception e) {
                            MMLog.e(SDKLOG, "Exception calculating hash: ", e);
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return string;
    }

    static synchronized void setMMdid(String value) {
        getMMdidValue = value;
    }

    static String byteArrayToString(byte[] ba) {
        StringBuilder hex = new StringBuilder(ba.length * 2);
        for (byte b : ba) {
            hex.append(String.format("%02X", Byte.valueOf(b)));
        }
        return hex.toString();
    }

    private static String getDensityString(Context context) {
        return Float.toString(getDensity(context));
    }

    static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    static void checkPermissions(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.INTERNET") == -1) {
            createMissingPermissionDialog(context, "INTERNET permission").show();
        }
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == -1) {
            createMissingPermissionDialog(context, "ACCESS_NETWORK_STATE permission").show();
        }
    }

    private static AlertDialog createMissingPermissionDialog(Context context, String permission) {
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Whoops!");
        dialog.setMessage(String.format("The developer has forgot to declare the %s in the manifest file. Please reach out to the developer to remove this error.", permission));
        dialog.setButton(-3, "OK", new DialogInterface.OnClickListener() { // from class: com.millennialmedia.android.MMSDK.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog2, int which) {
                dialog2.cancel();
            }
        });
        dialog.show();
        return dialog;
    }

    static void checkActivity(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            ComponentName cn = new ComponentName(context, "com.millennialmedia.android.MMActivity");
            pm.getActivityInfo(cn, 128);
        } catch (PackageManager.NameNotFoundException e) {
            MMLog.e(SDKLOG, "Activity MMActivity not declared in AndroidManifest.xml", e);
            e.printStackTrace();
            createMissingPermissionDialog(context, "MMActivity class").show();
        }
    }

    static boolean isCachedVideoSupportedOnDevice(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != -1 && (!Build.VERSION.SDK.equalsIgnoreCase("8") || (Environment.getExternalStorageState().equals("mounted") && AdCache.isExternalEnabled));
    }

    static String getMcc(Context context) {
        String networkOperator;
        Configuration config = getConfiguration(context);
        return (config.mcc != 0 || (networkOperator = getNetworkOperator(context)) == null || networkOperator.length() < 6) ? String.valueOf(config.mcc) : networkOperator.substring(0, 3);
    }

    static String getMnc(Context context) {
        String networkOperator;
        Configuration config = getConfiguration(context);
        return (config.mnc != 0 || (networkOperator = getNetworkOperator(context)) == null || networkOperator.length() < 6) ? String.valueOf(config.mnc) : networkOperator.substring(3);
    }

    static Configuration getConfiguration(Context context) {
        return context.getResources().getConfiguration();
    }

    static String getNetworkOperator(Context context) {
        TelephonyManager tel = (TelephonyManager) context.getSystemService("phone");
        return tel.getNetworkOperator();
    }

    static String getCn(Context context) {
        TelephonyManager tel = (TelephonyManager) context.getSystemService("phone");
        return tel.getNetworkOperatorName();
    }

    static void insertUrlCommonValues(Context context, Map<String, String> paramsMap) {
        StatFs stat;
        AdvertisingIdClient.Info info;
        MMLog.d(SDKLOG, "executing getIDThread");
        paramsMap.put("density", getDensityString(context));
        paramsMap.put("hpx", getDpiHeight(context));
        paramsMap.put("wpx", getDpiWidth(context));
        paramsMap.put("sk", hasSpeechKit(context));
        paramsMap.put("mic", Boolean.toString(hasMicrophone(context)));
        String aaidValue = null;
        String ateValue = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) == 0 && (info = getAdvertisingInfo(context)) != null && (aaidValue = getAaid(info)) != null && info.isLimitAdTrackingEnabled()) {
            ateValue = "false";
        }
        if (aaidValue != null) {
            paramsMap.put("aaid", aaidValue);
            paramsMap.put("ate", ateValue);
        } else {
            String mmdid = getMMdid(context);
            if (mmdid != null) {
                paramsMap.put("mmdid", mmdid);
            }
        }
        if (isCachedVideoSupportedOnDevice(context)) {
            paramsMap.put("cachedvideo", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        } else {
            paramsMap.put("cachedvideo", "false");
        }
        if (Build.MODEL != null) {
            paramsMap.put("dm", Build.MODEL);
        }
        if (Build.VERSION.RELEASE != null) {
            paramsMap.put("dv", "Android" + Build.VERSION.RELEASE);
        }
        paramsMap.put("sdkversion", VERSION);
        paramsMap.put("mcc", getMcc(context));
        paramsMap.put("mnc", getMnc(context));
        String cn = getCn(context);
        if (!TextUtils.isEmpty(cn)) {
            paramsMap.put("cn", cn);
        }
        Locale locale = Locale.getDefault();
        if (locale != null) {
            paramsMap.put("language", locale.getLanguage());
            paramsMap.put("country", locale.getCountry());
        }
        try {
            String pkid = context.getPackageName();
            paramsMap.put("pkid", pkid);
            PackageManager pm = context.getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(pkid, 0);
            paramsMap.put("pknm", pm.getApplicationLabel(ai).toString());
        } catch (Exception e) {
            MMLog.e(SDKLOG, "Can't insert package information", e);
        }
        String schemes = HandShake.sharedHandShake(context).getSchemesList(context);
        if (schemes != null) {
            paramsMap.put("appsids", schemes);
        }
        String vid = AdCache.getCachedVideoList(context);
        if (vid != null) {
            paramsMap.put("vid", vid);
        }
        try {
            String connectionType = getConnectionType(context);
            if (AdCache.isExternalStorageAvailable(context)) {
                stat = new StatFs(AdCache.getCacheDirectory(context).getAbsolutePath());
            } else {
                stat = new StatFs(context.getFilesDir().getPath());
            }
            String freeSpace = Long.toString(((long) stat.getAvailableBlocks()) * ((long) stat.getBlockSize()));
            String devicePluggedIn = null;
            String deviceBatteryLevel = null;
            Intent intent = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (intent != null) {
                devicePluggedIn = intent.getIntExtra("plugged", 0) == 0 ? "false" : ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
                int scale = intent.getIntExtra("scale", 100);
                float ratio = 100.0f / scale;
                int level = (int) (intent.getIntExtra("level", 0) * ratio);
                deviceBatteryLevel = Integer.toString(level);
            }
            if (deviceBatteryLevel != null && deviceBatteryLevel.length() > 0) {
                paramsMap.put("bl", deviceBatteryLevel);
            }
            if (devicePluggedIn != null && devicePluggedIn.length() > 0) {
                paramsMap.put("plugged", devicePluggedIn);
            }
            if (freeSpace.length() > 0) {
                paramsMap.put("space", freeSpace);
            }
            if (connectionType != null) {
                paramsMap.put("conn", connectionType);
            }
            String ip = URLEncoder.encode(getIpAddress(context), "UTF-8");
            if (!TextUtils.isEmpty(ip)) {
                paramsMap.put("pip", ip);
            }
        } catch (Exception e2) {
            MMLog.e(SDKLOG, "Exception inserting common parameters: ", e2);
        }
        MMRequest.insertLocation(paramsMap);
    }

    static String getAaid(AdvertisingIdClient.Info adInfo) {
        if (adInfo == null) {
            return null;
        }
        return adInfo.getId();
    }

    static AdvertisingIdClient.Info getAdvertisingInfo(Context context) {
        try {
            AdvertisingIdClient.Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            return adInfo;
        } catch (GooglePlayServicesNotAvailableException e) {
            MMLog.e(SDKLOG, "Google Play services is not available entirely.", e);
            return null;
        } catch (GooglePlayServicesRepairableException e2) {
            MMLog.e(SDKLOG, "Google Play Services is not installed, up-to-date, or enabled", e2);
            return null;
        } catch (IOException e3) {
            MMLog.e(SDKLOG, "Unrecoverable error connecting to Google Play services (e.g.,the old version of the service doesnt support getting AdvertisingId", e3);
            return null;
        } catch (IllegalStateException e4) {
            MMLog.e(SDKLOG, "IllegalStateException: ", e4);
            return null;
        }
    }

    static String getDpiWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return Integer.toString(metrics.widthPixels);
    }

    static String getDpiHeight(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return Integer.toString(metrics.heightPixels);
    }

    public static void trackEvent(Context context, String eventId) {
        if (!TextUtils.isEmpty(eventId)) {
            String mmdid = getMMdid(context);
            if (!TextUtils.isEmpty(mmdid)) {
                String trackEventUrl = BASE_URL_TRACK_EVENT + eventId + "&mmdid=" + mmdid;
                Utils.HttpUtils.executeUrl(trackEventUrl);
            }
        }
    }

    public static void trackConversion(Context context, String goalId) {
        MMConversionTracker.trackConversion(context, goalId, null);
    }

    public static void trackConversion(Context context, String goalId, MMRequest request) {
        MMConversionTracker.trackConversion(context, goalId, request);
    }

    private static String hasSpeechKit(Context context) {
        return (hasSpeechKit && hasRecordAudioPermission(context)) ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false";
    }

    public static void initialize(Context context) {
        HandShake handShake = HandShake.sharedHandShake(context);
        handShake.sendInitRequest();
        handShake.startSession();
    }

    static boolean hasMicrophone(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.microphone");
    }

    static boolean hasRecordAudioPermission(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.RECORD_AUDIO") == 0;
    }

    static int getMediaVolume(Context context) {
        AudioManager am = (AudioManager) context.getApplicationContext().getSystemService("audio");
        return am.getStreamVolume(3);
    }

    static void printDiagnostics(MMAdImpl adImpl) {
        if (adImpl != null) {
            final Context context = adImpl.getContext();
            MMLog.i(SDKLOG, String.format("MMAd External ID: %d", Integer.valueOf(adImpl.getId())));
            MMLog.i(SDKLOG, String.format("MMAd Internal ID: %d", Long.valueOf(adImpl.internalId)));
            MMLog.i(SDKLOG, String.format("APID: %s", adImpl.apid));
            Object[] objArr = new Object[1];
            objArr[0] = AdCache.isExternalStorageAvailable(context) ? "" : "not ";
            MMLog.i(SDKLOG, String.format("SD card is %savailable.", objArr));
            if (context != null) {
                MMLog.i(SDKLOG, String.format("Package: %s", context.getPackageName()));
                MMLog.i(SDKLOG, String.format("MMDID: %s", getMMdid(context)));
                MMLog.i(SDKLOG, "Permissions:");
                Object[] objArr2 = new Object[1];
                objArr2[0] = context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == -1 ? "not " : "";
                MMLog.i(SDKLOG, String.format("android.permission.ACCESS_NETWORK_STATE is %spresent", objArr2));
                Object[] objArr3 = new Object[1];
                objArr3[0] = context.checkCallingOrSelfPermission("android.permission.INTERNET") == -1 ? "not " : "";
                MMLog.i(SDKLOG, String.format("android.permission.INTERNET is %spresent", objArr3));
                Object[] objArr4 = new Object[1];
                objArr4[0] = context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == -1 ? "not " : "";
                MMLog.i(SDKLOG, String.format("android.permission.WRITE_EXTERNAL_STORAGE is %spresent", objArr4));
                Object[] objArr5 = new Object[1];
                objArr5[0] = context.checkCallingOrSelfPermission("android.permission.VIBRATE") == -1 ? "not " : "";
                MMLog.i(SDKLOG, String.format("android.permission.VIBRATE is %spresent", objArr5));
                Object[] objArr6 = new Object[1];
                objArr6[0] = context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == -1 ? "not " : "";
                MMLog.i(SDKLOG, String.format("android.permission.ACCESS_COARSE_LOCATION is %spresent", objArr6));
                Object[] objArr7 = new Object[1];
                objArr7[0] = context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == -1 ? "not " : "";
                MMLog.i(SDKLOG, String.format("android.permission.ACCESS_FINE_LOCATION is %spresent", objArr7));
                MMLog.i(SDKLOG, "Cached Ads:");
                AdCache.iterateCachedAds(context, 2, new AdCache.Iterator() { // from class: com.millennialmedia.android.MMSDK.2
                    @Override // com.millennialmedia.android.AdCache.Iterator
                    boolean callback(CachedAd ad) {
                        Object[] objArr8 = new Object[4];
                        objArr8[0] = ad.getTypeString();
                        objArr8[1] = ad.getId();
                        objArr8[2] = ad.isOnDisk(context) ? "" : "not ";
                        objArr8[3] = ad.isExpired() ? "" : "not ";
                        MMLog.i(MMSDK.SDKLOG, String.format("%s %s is %son disk. Is %sexpired.", objArr8));
                        return true;
                    }
                });
            }
        }
    }

    static String getSupportsSms(Context context) {
        PackageManager pm = context.getPackageManager();
        return String.valueOf(pm.hasSystemFeature("android.hardware.telephony"));
    }

    static boolean getSupportsCalendar() {
        return Build.VERSION.SDK_INT >= 14;
    }

    static String getSupportsTel(Context context) {
        PackageManager pm = context.getPackageManager();
        return String.valueOf(pm.hasSystemFeature("android.hardware.telephony"));
    }

    static String getOrientation(Context context) {
        switch (context.getResources().getConfiguration().orientation) {
            case 1:
                return "portrait";
            case 2:
                return "landscape";
            case 3:
                return "square";
            default:
                return UnityAdsConstants.UNITY_ADS_ZONE_DEFAULT_KEY;
        }
    }

    static final String getOrientationLocked(Context context) {
        return Settings.System.getString(context.getContentResolver(), "accelerometer_rotation").equals(AppEventsConstants.EVENT_PARAM_VALUE_YES) ? "false" : ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
    }

    static boolean removeAccelForJira1164() {
        return Integer.parseInt(Build.VERSION.SDK) >= 14;
    }

    static boolean hasSetTranslationMethod() {
        return Integer.parseInt(Build.VERSION.SDK) >= 11;
    }

    static boolean supportsFullScreenInline() {
        return Integer.parseInt(Build.VERSION.SDK) >= 11;
    }
}
