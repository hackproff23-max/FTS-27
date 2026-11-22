package com.unity3d.ads.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.gms.drive.DriveFile;
import com.unity3d.ads.android.cache.IUnityAdsCacheListener;
import com.unity3d.ads.android.cache.UnityAdsCacheManager;
import com.unity3d.ads.android.cache.UnityAdsDownloader;
import com.unity3d.ads.android.campaign.UnityAdsCampaign;
import com.unity3d.ads.android.campaign.UnityAdsCampaignHandler;
import com.unity3d.ads.android.item.UnityAdsRewardItem;
import com.unity3d.ads.android.item.UnityAdsRewardItemManager;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import com.unity3d.ads.android.video.UnityAdsVideoPausedView;
import com.unity3d.ads.android.view.IUnityAdsMainViewListener;
import com.unity3d.ads.android.view.UnityAdsFullscreenActivity;
import com.unity3d.ads.android.view.UnityAdsMainView;
import com.unity3d.ads.android.webapp.IUnityAdsWebBridgeListener;
import com.unity3d.ads.android.webapp.IUnityAdsWebDataListener;
import com.unity3d.ads.android.webapp.UnityAdsWebData;
import com.unity3d.ads.android.zone.UnityAdsIncentivizedZone;
import com.unity3d.ads.android.zone.UnityAdsZone;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(9)
public class UnityAds implements IUnityAdsCacheListener, IUnityAdsMainViewListener, IUnityAdsWebBridgeListener, IUnityAdsWebDataListener {
    public static final String UNITY_ADS_OPTION_GAMERSID_KEY = "sid";
    public static final String UNITY_ADS_OPTION_MUTE_VIDEO_SOUNDS = "muteVideoSounds";
    public static final String UNITY_ADS_OPTION_NOOFFERSCREEN_KEY = "noOfferScreen";
    public static final String UNITY_ADS_OPTION_OPENANIMATED_KEY = "openAnimated";
    public static final String UNITY_ADS_OPTION_VIDEO_USES_DEVICE_ORIENTATION = "useDeviceOrientationForVideo";
    public static final String UNITY_ADS_REWARDITEM_NAME_KEY = "name";
    public static final String UNITY_ADS_REWARDITEM_PICTURE_KEY = "picture";
    public static UnityAdsCacheManager cachemanager = null;
    public static UnityAdsWebData webdata = null;
    public static UnityAdsMainView mainview = null;
    private static boolean a = false;
    private static boolean b = false;
    private static boolean c = false;
    private static boolean d = false;
    private static boolean e = false;
    private static boolean f = false;
    private static boolean g = false;
    private static boolean h = false;
    private static boolean i = false;
    private static AlertDialog j = null;
    private static TimerTask k = null;
    private static Timer l = null;
    private static TimerTask m = null;
    private static Timer n = null;
    private static long o = 0;
    private static UnityAds p = null;
    private static IUnityAdsListener q = null;

    static /* synthetic */ boolean b() {
        a = true;
        return true;
    }

    static /* synthetic */ boolean f() {
        c = true;
        return true;
    }

    static /* synthetic */ boolean j() {
        e = true;
        return true;
    }

    static /* synthetic */ boolean m() {
        i = true;
        return true;
    }

    static /* synthetic */ boolean n() {
        b = false;
        return false;
    }

    static /* synthetic */ void o() {
        if (e) {
            e = false;
            UnityAdsDeviceLog.debug("Starting delayed ad plan refresh");
            if (webdata != null) {
                webdata.initCampaigns();
                return;
            }
            return;
        }
        if (o > 0 && SystemClock.elapsedRealtime() > o) {
            t();
            UnityAdsDeviceLog.debug("Refreshing ad plan from server due to timer deadline");
            if (webdata != null) {
                webdata.initCampaigns();
                return;
            }
            return;
        }
        if (UnityAdsProperties.CAMPAIGN_REFRESH_VIEWS_MAX > 0 && UnityAdsProperties.CAMPAIGN_REFRESH_VIEWS_COUNT >= UnityAdsProperties.CAMPAIGN_REFRESH_VIEWS_MAX) {
            UnityAdsDeviceLog.debug("Refreshing ad plan from server due to endscreen limit");
            if (webdata != null) {
                webdata.initCampaigns();
                return;
            }
            return;
        }
        if (webdata == null || webdata.getVideoPlanCampaigns() == null) {
            UnityAdsDeviceLog.error("Unable to read video data to determine if ad plans should be refreshed");
        } else if (webdata.getViewableVideoPlanCampaigns().size() == 0) {
            UnityAdsDeviceLog.debug("All available videos watched, refreshing ad plan from server");
            if (webdata != null) {
                webdata.initCampaigns();
            }
        }
    }

    static /* synthetic */ void p() {
        k = new f();
        Timer timer = new Timer();
        l = timer;
        timer.scheduleAtFixedRate(k, 0L, 50L);
    }

    private UnityAds() {
    }

    public static boolean isSupported() {
        return Build.VERSION.SDK_INT >= 9;
    }

    public static void setDebugMode(boolean z) {
        if (z) {
            UnityAdsDeviceLog.setLogLevel(UnityAdsDeviceLog.LOGLEVEL_DEBUG);
        } else {
            UnityAdsDeviceLog.setLogLevel(UnityAdsDeviceLog.LOGLEVEL_INFO);
        }
    }

    public static void setTestMode(boolean z) {
        UnityAdsProperties.TESTMODE_ENABLED = Boolean.valueOf(z);
    }

    public static void setTestDeveloperId(String str) {
        UnityAdsProperties.TEST_DEVELOPER_ID = str;
    }

    public static void setTestOptionsId(String str) {
        UnityAdsProperties.TEST_OPTIONS_ID = str;
    }

    public static String getSDKVersion() {
        return UnityAdsConstants.UNITY_ADS_VERSION;
    }

    public static void enableUnityDeveloperInternalTestMode() {
        UnityAdsProperties.CAMPAIGN_DATA_URL = "https://impact.staging.applifier.com/mobile/campaigns";
        UnityAdsProperties.UNITY_DEVELOPER_INTERNAL_TEST = true;
    }

    public static void setListener(IUnityAdsListener iUnityAdsListener) {
        q = iUnityAdsListener;
    }

    public static void changeActivity(Activity activity) {
        String str;
        Boolean bool;
        if (activity != null && activity != null && !activity.equals(UnityAdsProperties.getCurrentActivity())) {
            UnityAdsProperties.CURRENT_ACTIVITY = new WeakReference(activity);
            if (activity != null && (activity instanceof UnityAdsFullscreenActivity)) {
                if (d) {
                    UnityAdsDeviceLog.debug("This open request is from the developer, setting start view");
                    str = "start";
                } else {
                    str = null;
                }
                if (str != null) {
                    if (mainview != null) {
                        UnityAdsDeviceLog.debug("View was not destroyed, trying to destroy it");
                        mainview.webview.destroy();
                        mainview = null;
                    }
                    if (mainview == null) {
                        mainview = new UnityAdsMainView(UnityAdsProperties.getCurrentActivity(), p, p);
                    }
                    JSONObject jSONObject = new JSONObject();
                    try {
                        UnityAdsZone currentZone = UnityAdsWebData.getZoneManager().getCurrentZone();
                        jSONObject.put("action", UnityAdsConstants.UNITY_ADS_WEBVIEW_API_OPEN);
                        jSONObject.put("zone", currentZone.getZoneId());
                        if (currentZone.isIncentivized()) {
                            jSONObject.put("itemKey", ((UnityAdsIncentivizedZone) currentZone).itemManager().getCurrentItem().getKey());
                        }
                        bool = true;
                    } catch (Exception e2) {
                        bool = false;
                    }
                    UnityAdsDeviceLog.debug("DataOk: " + bool);
                    if (bool.booleanValue()) {
                        UnityAdsDeviceLog.debug("Opening with view:" + str + " and data:" + jSONObject.toString());
                        if (mainview != null) {
                            new Thread(new c(str, jSONObject)).start();
                        }
                    }
                }
                d = false;
                return;
            }
            UnityAdsProperties.BASE_ACTIVITY = new WeakReference(activity);
        }
    }

    public static boolean hide() {
        if (!b) {
            return false;
        }
        q();
        return true;
    }

    public static boolean setZone(String str) {
        if (b) {
            return false;
        }
        if (UnityAdsWebData.getZoneManager() == null) {
            throw new IllegalStateException("Unable to set zone before campaigns are available");
        }
        return UnityAdsWebData.getZoneManager().setCurrentZone(str);
    }

    public static boolean setZone(String str, String str2) {
        if (!b && setZone(str)) {
            UnityAdsZone currentZone = UnityAdsWebData.getZoneManager().getCurrentZone();
            if (currentZone.isIncentivized()) {
                return ((UnityAdsIncentivizedZone) currentZone).itemManager().setCurrentItem(str2);
            }
        }
        return false;
    }

    public static boolean show(Map map) {
        UnityAdsZone currentZone;
        if (!canShow() || (currentZone = UnityAdsWebData.getZoneManager().getCurrentZone()) == null) {
            return false;
        }
        UnityAdsDownloader.stopAllDownloads();
        currentZone.mergeOptions(map);
        if (currentZone.noOfferScreen()) {
            ArrayList viewableVideoPlanCampaigns = webdata.getViewableVideoPlanCampaigns();
            if (viewableVideoPlanCampaigns.size() > 0) {
                UnityAdsCampaign unityAdsCampaign = (UnityAdsCampaign) viewableVideoPlanCampaigns.get(0);
                UnityAdsProperties.SELECTED_CAMPAIGN = unityAdsCampaign;
                if (viewableVideoPlanCampaigns.size() > 1) {
                    UnityAdsCampaign unityAdsCampaign2 = (UnityAdsCampaign) viewableVideoPlanCampaigns.get(1);
                    if (cachemanager.isCampaignCached(unityAdsCampaign) && !cachemanager.isCampaignCached(unityAdsCampaign2) && unityAdsCampaign2.allowCacheVideo().booleanValue()) {
                        cachemanager.cacheNextVideo(unityAdsCampaign2);
                    }
                }
            }
        }
        d = true;
        b = true;
        g = false;
        i = false;
        Intent intent = new Intent(UnityAdsProperties.getCurrentActivity(), (Class<?>) UnityAdsFullscreenActivity.class);
        intent.addFlags(UnityAdsWebData.getZoneManager().getCurrentZone().openAnimated() ? DriveFile.MODE_READ_ONLY : 268500992);
        try {
            UnityAdsProperties.getBaseActivity().startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            UnityAdsDeviceLog.error("Could not find activity: " + e2.getStackTrace());
        } catch (Exception e3) {
            UnityAdsDeviceLog.error("Weird error: " + e3.getStackTrace());
        }
        return b;
    }

    public static boolean show() {
        return show(null);
    }

    public static boolean canShowAds() {
        return (webdata == null || webdata.getViewableVideoPlanCampaigns() == null || webdata.getViewableVideoPlanCampaigns().size() <= 0) ? false : true;
    }

    public static boolean canShow() {
        boolean z;
        ConnectivityManager connectivityManager;
        Activity currentActivity = UnityAdsProperties.getCurrentActivity();
        if (currentActivity == null || (connectivityManager = (ConnectivityManager) currentActivity.getBaseContext().getSystemService("connectivity")) == null) {
            z = true;
        } else {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            z = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return (b || !z || webdata == null || webdata.getVideoPlanCampaigns() == null || webdata.getVideoPlanCampaigns().size() <= 0) ? false : true;
    }

    public static boolean hasMultipleRewardItems() {
        UnityAdsZone currentZone = UnityAdsWebData.getZoneManager().getCurrentZone();
        if (currentZone == null || !currentZone.isIncentivized()) {
            return false;
        }
        return ((UnityAdsIncentivizedZone) currentZone).itemManager().itemCount() > 1;
    }

    public static ArrayList getRewardItemKeys() {
        UnityAdsZone currentZone = UnityAdsWebData.getZoneManager().getCurrentZone();
        if (currentZone == null || !currentZone.isIncentivized()) {
            return null;
        }
        ArrayList arrayListAllItems = ((UnityAdsIncentivizedZone) currentZone).itemManager().allItems();
        ArrayList arrayList = new ArrayList();
        Iterator it = arrayListAllItems.iterator();
        while (it.hasNext()) {
            arrayList.add(((UnityAdsRewardItem) it.next()).getKey());
        }
        return arrayList;
    }

    public static String getDefaultRewardItemKey() {
        UnityAdsZone currentZone = UnityAdsWebData.getZoneManager().getCurrentZone();
        if (currentZone == null || !currentZone.isIncentivized()) {
            return null;
        }
        return ((UnityAdsIncentivizedZone) currentZone).itemManager().getDefaultItem().getKey();
    }

    public static String getCurrentRewardItemKey() {
        UnityAdsZone currentZone = UnityAdsWebData.getZoneManager().getCurrentZone();
        if (currentZone == null || !currentZone.isIncentivized()) {
            return null;
        }
        return ((UnityAdsIncentivizedZone) currentZone).itemManager().getCurrentItem().getKey();
    }

    public static boolean setRewardItemKey(String str) {
        UnityAdsZone currentZone;
        if (canShow() && (currentZone = UnityAdsWebData.getZoneManager().getCurrentZone()) != null && currentZone.isIncentivized()) {
            return ((UnityAdsIncentivizedZone) currentZone).itemManager().setCurrentItem(str);
        }
        return false;
    }

    public static void setDefaultRewardItemAsRewardItem() {
        UnityAdsZone currentZone;
        if (canShow() && (currentZone = UnityAdsWebData.getZoneManager().getCurrentZone()) != null && currentZone.isIncentivized()) {
            UnityAdsRewardItemManager unityAdsRewardItemManagerItemManager = ((UnityAdsIncentivizedZone) currentZone).itemManager();
            unityAdsRewardItemManagerItemManager.setCurrentItem(unityAdsRewardItemManagerItemManager.getDefaultItem().getKey());
        }
    }

    public static Map getRewardItemDetailsWithKey(String str) {
        UnityAdsZone currentZone = UnityAdsWebData.getZoneManager().getCurrentZone();
        if (currentZone != null && currentZone.isIncentivized()) {
            UnityAdsRewardItem item = ((UnityAdsIncentivizedZone) currentZone).itemManager().getItem(str);
            if (item != null) {
                return item.getDetails();
            }
            UnityAdsDeviceLog.info("Could not fetch reward item: " + str);
        }
        return null;
    }

    @Override // com.unity3d.ads.android.view.IUnityAdsMainViewListener
    public void onMainViewAction(UnityAdsMainView.UnityAdsMainViewAction unityAdsMainViewAction) {
        switch (i.a[unityAdsMainViewAction.ordinal()]) {
            case 1:
                if (b) {
                    q();
                }
                break;
            case 2:
                if (q != null) {
                    q.onVideoStarted();
                }
                s();
                break;
            case 3:
                UnityAdsProperties.CAMPAIGN_REFRESH_VIEWS_COUNT++;
                if (q != null && UnityAdsProperties.SELECTED_CAMPAIGN != null && !UnityAdsProperties.SELECTED_CAMPAIGN.isViewed().booleanValue()) {
                    UnityAdsProperties.SELECTED_CAMPAIGN.setCampaignStatus(UnityAdsCampaign.UnityAdsCampaignStatus.VIEWED);
                    q.onVideoCompleted(getCurrentRewardItemKey(), false);
                    break;
                }
                break;
            case 4:
                UnityAdsProperties.CAMPAIGN_REFRESH_VIEWS_COUNT++;
                if (q != null && UnityAdsProperties.SELECTED_CAMPAIGN != null && !UnityAdsProperties.SELECTED_CAMPAIGN.isViewed().booleanValue()) {
                    UnityAdsProperties.SELECTED_CAMPAIGN.setCampaignStatus(UnityAdsCampaign.UnityAdsCampaignStatus.VIEWED);
                    q.onVideoCompleted(getCurrentRewardItemKey(), true);
                    break;
                }
                break;
            case 5:
                UnityAdsDeviceLog.debug("Retrying video play, because something went wrong.");
                g = false;
                a(300L);
                break;
        }
    }

    @Override // com.unity3d.ads.android.cache.IUnityAdsCacheListener
    public void onCampaignUpdateStarted() {
        UnityAdsDeviceLog.debug("Campaign updates started.");
    }

    @Override // com.unity3d.ads.android.cache.IUnityAdsCacheListener
    public void onCampaignReady(UnityAdsCampaignHandler unityAdsCampaignHandler) {
        if (unityAdsCampaignHandler != null && unityAdsCampaignHandler.getCampaign() != null) {
            UnityAdsDeviceLog.debug(unityAdsCampaignHandler.getCampaign().toString());
            if (canShowAds()) {
                r();
            }
        }
    }

    @Override // com.unity3d.ads.android.cache.IUnityAdsCacheListener
    public void onAllCampaignsReady() {
        UnityAdsDeviceLog.entered();
    }

    @Override // com.unity3d.ads.android.webapp.IUnityAdsWebDataListener
    public void onWebDataCompleted() {
        boolean z = true;
        UnityAdsDeviceLog.entered();
        JSONObject jSONObject = null;
        boolean z2 = false;
        if (webdata.getData() != null && webdata.getData().has("data")) {
            try {
                jSONObject = webdata.getData().getJSONObject("data");
            } catch (Exception e2) {
                z2 = true;
            }
            if (!z2) {
                t();
                if (UnityAdsProperties.CAMPAIGN_REFRESH_SECONDS > 0) {
                    m = new g();
                    o = SystemClock.elapsedRealtime() + ((long) (UnityAdsProperties.CAMPAIGN_REFRESH_SECONDS * 1000));
                    Timer timer = new Timer();
                    n = timer;
                    timer.schedule(m, UnityAdsProperties.CAMPAIGN_REFRESH_SECONDS * 1000);
                }
                if (jSONObject.has(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_SDK_IS_CURRENT_KEY)) {
                    try {
                        z = jSONObject.getBoolean(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_SDK_IS_CURRENT_KEY);
                    } catch (Exception e3) {
                        z2 = true;
                    }
                }
            }
        }
        if (!z2 && !z && UnityAdsProperties.getCurrentActivity() != null && UnityAdsUtils.isDebuggable(UnityAdsProperties.getCurrentActivity())) {
            AlertDialog alertDialogCreate = new AlertDialog.Builder(UnityAdsProperties.getCurrentActivity()).create();
            j = alertDialogCreate;
            alertDialogCreate.setTitle("Unity Ads");
            j.setMessage("You are not running the latest version of Unity Ads android. Please update your version (this dialog won't appear in release builds).");
            j.setButton("OK", new a(this));
            j.show();
        }
        UnityAdsDeviceLog.entered();
        if (a) {
            cachemanager.updateCache(webdata.getVideoPlanCampaigns());
        }
    }

    @Override // com.unity3d.ads.android.webapp.IUnityAdsWebDataListener
    public void onWebDataFailed() {
        if (q != null && !c) {
            q.onFetchFailed();
        }
    }

    @Override // com.unity3d.ads.android.webapp.IUnityAdsWebBridgeListener
    public void onPlayVideo(JSONObject jSONObject) {
        UnityAdsDeviceLog.entered();
        if (jSONObject.has("campaignId")) {
            String string = null;
            try {
                string = jSONObject.getString("campaignId");
            } catch (Exception e2) {
                UnityAdsDeviceLog.error("Could not get campaignId");
            }
            if (string != null) {
                if (webdata != null && webdata.getCampaignById(string) != null) {
                    UnityAdsProperties.SELECTED_CAMPAIGN = webdata.getCampaignById(string);
                }
                if (UnityAdsProperties.SELECTED_CAMPAIGN != null && UnityAdsProperties.SELECTED_CAMPAIGN.getCampaignId() != null && UnityAdsProperties.SELECTED_CAMPAIGN.getCampaignId().equals(string)) {
                    Boolean boolValueOf = false;
                    try {
                        boolValueOf = Boolean.valueOf(jSONObject.getBoolean(UnityAdsConstants.UNITY_ADS_WEBVIEW_EVENTDATA_REWATCH_KEY));
                    } catch (Exception e3) {
                    }
                    UnityAdsDeviceLog.debug("Selected campaign=" + UnityAdsProperties.SELECTED_CAMPAIGN.getCampaignId() + " isViewed: " + UnityAdsProperties.SELECTED_CAMPAIGN.isViewed());
                    if (UnityAdsProperties.SELECTED_CAMPAIGN != null) {
                        if (boolValueOf.booleanValue() || !UnityAdsProperties.SELECTED_CAMPAIGN.isViewed().booleanValue()) {
                            if (boolValueOf.booleanValue()) {
                                g = false;
                            }
                            a(0L);
                        }
                    }
                }
            }
        }
    }

    @Override // com.unity3d.ads.android.webapp.IUnityAdsWebBridgeListener
    public void onPauseVideo(JSONObject jSONObject) {
    }

    @Override // com.unity3d.ads.android.webapp.IUnityAdsWebBridgeListener
    public void onCloseAdsView(JSONObject jSONObject) {
        hide();
    }

    @Override // com.unity3d.ads.android.webapp.IUnityAdsWebBridgeListener
    public void onWebAppLoadComplete(JSONObject jSONObject) {
        UnityAdsDeviceLog.entered();
        mainview.webview.setWebAppLoadComplete();
    }

    @Override // com.unity3d.ads.android.webapp.IUnityAdsWebBridgeListener
    public void onWebAppInitComplete(JSONObject jSONObject) {
        UnityAdsDeviceLog.entered();
        Boolean bool = true;
        if (canShowAds()) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("action", UnityAdsConstants.UNITY_ADS_WEBVIEW_API_INITCOMPLETE);
            } catch (Exception e2) {
                bool = false;
            }
            if (bool.booleanValue()) {
                mainview.webview.setWebViewCurrentView("start", jSONObject2);
                r();
            }
        }
    }

    @Override // com.unity3d.ads.android.webapp.IUnityAdsWebBridgeListener
    public void onOpenPlayStore(JSONObject jSONObject) {
        String string;
        String string2 = null;
        UnityAdsDeviceLog.entered();
        if (jSONObject != null) {
            UnityAdsDeviceLog.debug(jSONObject.toString());
            Boolean boolValueOf = false;
            if (jSONObject.has("iTunesId")) {
                try {
                    string = jSONObject.getString("iTunesId");
                } catch (Exception e2) {
                    UnityAdsDeviceLog.error("Could not fetch playStoreId");
                    string = null;
                }
            } else {
                string = null;
            }
            if (jSONObject.has("clickUrl")) {
                try {
                    string2 = jSONObject.getString("clickUrl");
                } catch (Exception e3) {
                    UnityAdsDeviceLog.error("Could not fetch clickUrl");
                }
            }
            if (jSONObject.has("bypassAppSheet")) {
                try {
                    boolValueOf = Boolean.valueOf(jSONObject.getBoolean("bypassAppSheet"));
                } catch (Exception e4) {
                    UnityAdsDeviceLog.error("Could not fetch bypassAppSheet");
                }
            }
            if (string != null && !boolValueOf.booleanValue()) {
                UnityAdsDeviceLog.debug("Opening playstore activity with storeId: " + string);
                if (UnityAdsProperties.getCurrentActivity() != null) {
                    try {
                        UnityAdsProperties.getCurrentActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + string)));
                        return;
                    } catch (Exception e5) {
                        UnityAdsDeviceLog.error("Couldn't start PlayStore intent!");
                        return;
                    }
                }
                return;
            }
            if (string2 != null) {
                UnityAdsDeviceLog.debug("Opening playStore in browser: " + string2);
                if (UnityAdsProperties.getCurrentActivity() != null) {
                    try {
                        UnityAdsProperties.getCurrentActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(string2)));
                    } catch (Exception e6) {
                        UnityAdsDeviceLog.error("Couldn't start browser intent!");
                    }
                }
            }
        }
    }

    public static void init(Activity activity, String str, IUnityAdsListener iUnityAdsListener) {
        if (p == null && !a) {
            if (str.length() == 0) {
                throw new IllegalArgumentException("gameId is empty");
            }
            try {
                if (Integer.parseInt(str) <= 0) {
                    throw new IllegalArgumentException("gameId is invalid");
                }
                String packageName = activity.getPackageName();
                PackageManager packageManager = activity.getPackageManager();
                if (packageName != null && packageManager != null) {
                    try {
                        PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 1);
                        for (int i2 = 0; i2 < packageInfo.activities.length; i2++) {
                            if (packageInfo.activities[i2].launchMode == 2) {
                                h = true;
                                UnityAdsDeviceLog.debug("Running in singleTask application mode");
                            }
                        }
                    } catch (Exception e2) {
                        UnityAdsDeviceLog.debug("Error while checking singleTask activities");
                    }
                }
                if (p == null) {
                    p = new UnityAds();
                }
                setListener(iUnityAdsListener);
                UnityAdsProperties.UNITY_ADS_GAME_ID = str;
                UnityAdsProperties.BASE_ACTIVITY = new WeakReference(activity);
                UnityAdsProperties.CURRENT_ACTIVITY = new WeakReference(activity);
                UnityAdsVideoPausedView.initScreenMetrics(activity);
                UnityAdsDeviceLog.debug("Is debuggable=" + UnityAdsUtils.isDebuggable(activity));
                UnityAdsCacheManager unityAdsCacheManager = new UnityAdsCacheManager();
                cachemanager = unityAdsCacheManager;
                unityAdsCacheManager.setDownloadListener(p);
                UnityAdsWebData unityAdsWebData = new UnityAdsWebData();
                webdata = unityAdsWebData;
                unityAdsWebData.setWebDataListener(p);
                new Thread(new b(activity)).start();
            } catch (NumberFormatException e3) {
                throw new IllegalArgumentException("gameId does not parse as an integer");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void q() {
        s();
        if (UnityAdsProperties.getCurrentActivity() != null && (UnityAdsProperties.getCurrentActivity() instanceof UnityAdsFullscreenActivity)) {
            new Handler(Looper.getMainLooper()).postDelayed(new j((byte) 0), 1L);
        } else {
            UnityAdsDeviceLog.debug("Did not close");
        }
    }

    private static void r() {
        if (!c && q != null) {
            UnityAdsUtils.runOnUiThread(new e());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(long j2) {
        if (g) {
            UnityAdsDeviceLog.debug("Prevent double start of video playback");
            return;
        }
        g = true;
        UnityAdsDeviceLog.debug("Running threaded");
        UnityAdsUtils.runOnUiThread(new k((byte) 0), j2);
    }

    public static void checkMainview() {
        if (f) {
            f = false;
            if (mainview != null) {
                mainview.fixActivityAttachment();
            }
        }
    }

    public static void handleFullscreenDestroy() {
        if (h) {
            u();
        } else if (b) {
            f = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void s() {
        if (k != null) {
            k.cancel();
        }
        if (l != null) {
            l.cancel();
            l.purge();
        }
        k = null;
        l = null;
    }

    private static void t() {
        o = 0L;
        if (n != null) {
            n.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void u() {
        if (!i) {
            int i2 = 30;
            if (h) {
                i2 = 250;
            }
            new Handler(Looper.getMainLooper()).postDelayed(new h(), i2);
        }
    }
}
