package com.unity3d.ads.android.properties;

import android.app.Activity;
import com.facebook.internal.ServerProtocol;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.campaign.UnityAdsCampaign;
import com.unity3d.ads.android.data.UnityAdsDevice;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsProperties {
    public static final int MAX_BUFFERING_WAIT_SECONDS = 20;
    public static final int MAX_NUMBER_OF_ANALYTICS_RETRIES = 5;
    public static String CAMPAIGN_DATA_URL = "https://impact.applifier.com/mobile/campaigns";
    public static String WEBVIEW_BASE_URL = null;
    public static String ANALYTICS_BASE_URL = null;
    public static String UNITY_ADS_BASE_URL = null;
    public static String CAMPAIGN_QUERY_STRING = null;
    public static String UNITY_ADS_GAME_ID = null;
    public static String UNITY_ADS_GAMER_ID = null;
    public static Boolean TESTMODE_ENABLED = false;
    public static WeakReference BASE_ACTIVITY = null;
    public static WeakReference CURRENT_ACTIVITY = null;
    public static UnityAdsCampaign SELECTED_CAMPAIGN = null;
    public static int CAMPAIGN_REFRESH_VIEWS_COUNT = 0;
    public static int CAMPAIGN_REFRESH_VIEWS_MAX = 0;
    public static int CAMPAIGN_REFRESH_SECONDS = 0;
    public static String TEST_DATA = null;
    public static String TEST_URL = null;
    public static String TEST_JAVASCRIPT = null;
    public static Boolean RUN_WEBVIEW_TESTS = false;
    public static Boolean UNITY_DEVELOPER_INTERNAL_TEST = false;
    public static String TEST_DEVELOPER_ID = null;
    public static String TEST_OPTIONS_ID = null;
    private static Map a = null;
    private static String b = null;
    private static boolean c = false;

    public static String getCampaignQueryUrl() {
        String str;
        Object[] objArr;
        String str2;
        String str3;
        Object[] objArr2;
        String str4 = "?";
        try {
            String str5 = String.format("%s&%s=%s", String.format("%s%s=%s", "?", "platform", "android"), "deviceId", URLEncoder.encode(UnityAdsDevice.getAndroidId(true), "UTF-8"));
            String advertisingTrackingId = UnityAdsDevice.getAdvertisingTrackingId();
            if (advertisingTrackingId != null) {
                Object[] objArr3 = new Object[3];
                objArr3[0] = str5;
                objArr3[1] = "trackingEnabled";
                objArr3[2] = Integer.valueOf(UnityAdsDevice.isLimitAdTrackingEnabled() ? 0 : 1);
                str5 = String.format("%s&%s=%s", String.format("%s&%s=%s", String.format("%s&%s=%d", objArr3), "advertisingTrackingId", URLEncoder.encode(UnityAdsUtils.Md5(advertisingTrackingId).toLowerCase(), "UTF-8")), "rawAdvertisingTrackingId", URLEncoder.encode(advertisingTrackingId, "UTF-8"));
            } else {
                if (!UnityAdsDevice.getAndroidId(false).equals("unknown")) {
                    str5 = String.format("%s&%s=%s", String.format("%s&%s=%s", str5, "androidId", URLEncoder.encode(UnityAdsDevice.getAndroidId(true), "UTF-8")), "rawAndroidId", URLEncoder.encode(UnityAdsDevice.getAndroidId(false), "UTF-8"));
                }
                if (!UnityAdsDevice.getMacAddress().equals("unknown")) {
                    str5 = String.format("%s&%s=%s", str5, "macAddress", URLEncoder.encode(UnityAdsDevice.getMacAddress(), "UTF-8"));
                }
            }
            str4 = String.format("%s&%s=%s", String.format("%s&%s=%s", String.format("%s&%s=%s", String.format("%s&%s=%s", String.format("%s&%s=%s", String.format("%s&%s=%s", String.format("%s&%s=%s", str5, "gameId", URLEncoder.encode(UNITY_ADS_GAME_ID, "UTF-8")), "sdkVersion", URLEncoder.encode(UnityAdsConstants.UNITY_ADS_VERSION, "UTF-8")), "softwareVersion", URLEncoder.encode(UnityAdsDevice.getSoftwareVersion(), "UTF-8")), UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_HARDWAREVERSION_KEY, URLEncoder.encode(UnityAdsDevice.getHardwareVersion(), "UTF-8")), "deviceType", Integer.valueOf(UnityAdsDevice.getDeviceType())), "connectionType", URLEncoder.encode(UnityAdsDevice.getConnectionType(), "UTF-8")), "screenSize", Integer.valueOf(UnityAdsDevice.getScreenSize()));
            str = String.format("%s&%s=%s", str4, "screenDensity", Integer.valueOf(UnityAdsDevice.getScreenDensity()));
        } catch (Exception e) {
            str = str4;
            UnityAdsDeviceLog.error("Problems creating campaigns query: " + e.getMessage() + e.getStackTrace().toString());
        }
        if (TESTMODE_ENABLED.booleanValue()) {
            str = String.format("%s&%s=%s", str, UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_TEST_KEY, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            if (TEST_OPTIONS_ID != null && TEST_OPTIONS_ID.length() > 0) {
                str = String.format("%s&%s=%s", str, "optionsId", TEST_OPTIONS_ID);
            }
            if (TEST_DEVELOPER_ID != null && TEST_DEVELOPER_ID.length() > 0) {
                objArr = new Object[3];
                objArr[0] = str;
                objArr[1] = "developerId";
                str2 = TEST_DEVELOPER_ID;
                str3 = "%s&%s=%s";
                objArr2 = objArr;
                objArr[2] = str2;
                str = String.format(str3, objArr2);
            }
        } else if (getCurrentActivity() != null) {
            objArr = new Object[3];
            objArr[0] = str;
            objArr[1] = UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_ENCRYPTED_KEY;
            if (UnityAdsUtils.isDebuggable(getCurrentActivity())) {
                str2 = "false";
                str3 = "%s&%s=%s";
                objArr2 = objArr;
            } else {
                str2 = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
                str3 = "%s&%s=%s";
                objArr2 = objArr;
            }
            objArr[2] = str2;
            str = String.format(str3, objArr2);
        }
        b = str;
        String str6 = CAMPAIGN_DATA_URL;
        if (getBaseActivity() != null && UnityAdsUtils.isDebuggable(getBaseActivity()) && TEST_URL != null) {
            str6 = TEST_URL;
        }
        return String.format("%s%s", str6, b);
    }

    public static Activity getBaseActivity() {
        if (BASE_ACTIVITY == null || BASE_ACTIVITY.get() == null || ((Activity) BASE_ACTIVITY.get()).isFinishing() || isActivityDestroyed((Activity) BASE_ACTIVITY.get())) {
            return null;
        }
        return (Activity) BASE_ACTIVITY.get();
    }

    public static Activity getCurrentActivity() {
        return (CURRENT_ACTIVITY == null || CURRENT_ACTIVITY.get() == null || ((Activity) CURRENT_ACTIVITY.get()).isFinishing() || isActivityDestroyed((Activity) CURRENT_ACTIVITY.get())) ? getBaseActivity() : (Activity) CURRENT_ACTIVITY.get();
    }

    public static void setExtraParams(Map map) {
        if (map.containsKey("testData")) {
            TEST_DATA = (String) map.get("testData");
        }
        if (map.containsKey("testUrl")) {
            TEST_URL = (String) map.get("testUrl");
        }
        if (map.containsKey("testJavaScript")) {
            TEST_JAVASCRIPT = (String) map.get("testJavaScript");
        }
    }

    public static boolean isActivityDestroyed(Activity activity) {
        Method method = null;
        try {
            method = Activity.class.getMethod("isDestroyed", new Class[0]);
        } catch (Exception e) {
            if (!c) {
                c = true;
                UnityAdsDeviceLog.error("Couldn't get isDestroyed -method");
            }
        }
        if (method != null && activity != null) {
            try {
                return ((Boolean) method.invoke(activity, new Object[0])).booleanValue();
            } catch (Exception e2) {
                UnityAdsDeviceLog.error("Error running isDestroyed -method");
            }
        }
        return false;
    }
}
