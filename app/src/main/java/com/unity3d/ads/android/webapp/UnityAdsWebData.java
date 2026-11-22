package com.unity3d.ads.android.webapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.campaign.UnityAdsCampaign;
import com.unity3d.ads.android.data.UnityAdsDevice;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import com.unity3d.ads.android.zone.UnityAdsIncentivizedZone;
import com.unity3d.ads.android.zone.UnityAdsZone;
import com.unity3d.ads.android.zone.UnityAdsZoneManager;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(9)
public class UnityAdsWebData {
    private static UnityAdsZoneManager h = null;
    private Object g;
    private JSONObject a = null;
    private ArrayList b = null;
    private IUnityAdsWebDataListener c = null;
    private ArrayList d = null;
    private ArrayList e = null;
    private f f = null;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private boolean l = false;
    private boolean m = false;

    static /* synthetic */ void a(UnityAdsWebData unityAdsWebData, f fVar) {
        synchronized (unityAdsWebData.g) {
            if (unityAdsWebData.d == null) {
                unityAdsWebData.d = new ArrayList();
            }
            unityAdsWebData.d.add(fVar);
        }
    }

    static /* synthetic */ int b(UnityAdsWebData unityAdsWebData) {
        int i = unityAdsWebData.j;
        unityAdsWebData.j = i + 1;
        return i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    static /* synthetic */ void b(UnityAdsWebData unityAdsWebData, f fVar) {
        if (fVar != null && fVar.getRequestType() != null) {
            switch (fVar.getRequestType()) {
                case VideoPlan:
                    unityAdsWebData.b();
                    fVar.clear();
                    break;
                case VideoViewed:
                case Unsent:
                case Analytics:
                    synchronized (unityAdsWebData.g) {
                        if (fVar != null) {
                            if (unityAdsWebData.e == null) {
                                unityAdsWebData.e = new ArrayList();
                            }
                            if (!unityAdsWebData.e.contains(fVar)) {
                                unityAdsWebData.e.add(fVar);
                            }
                            JSONObject jSONObject = new JSONObject();
                            JSONArray jSONArray = new JSONArray();
                            try {
                                for (f fVar2 : unityAdsWebData.e) {
                                    JSONObject jSONObject2 = new JSONObject();
                                    jSONObject2.put("url", fVar2.getBaseUrl());
                                    jSONObject2.put(UnityAdsConstants.UNITY_ADS_FAILED_URL_REQUESTTYPE_KEY, fVar2.getRequestType());
                                    jSONObject2.put(UnityAdsConstants.UNITY_ADS_FAILED_URL_METHODTYPE_KEY, fVar2.getHTTPMethod());
                                    jSONObject2.put(UnityAdsConstants.UNITY_ADS_FAILED_URL_BODY_KEY, fVar2.getQueryParams());
                                    jSONObject2.put(UnityAdsConstants.UNITY_ADS_FAILED_URL_RETRIES_KEY, fVar2.getRetries());
                                    jSONArray.put(jSONObject2);
                                }
                                jSONObject.put("data", jSONArray);
                            } catch (Exception e) {
                                UnityAdsDeviceLog.error("Error collecting failed urls");
                            }
                            if (unityAdsWebData.e != null && unityAdsWebData.e.size() > 0 && UnityAdsUtils.canUseExternalStorage()) {
                                UnityAdsUtils.writeFile(new File(UnityAdsUtils.getCacheDirectory() + "/pendingrequests.dat"), jSONObject.toString());
                            }
                            break;
                        }
                    }
                    fVar.clear();
                    break;
                default:
                    fVar.clear();
                    break;
            }
        } else {
            UnityAdsDeviceLog.error("Got broken urlLoader!");
        }
        unityAdsWebData.l = false;
        unityAdsWebData.a();
    }

    static /* synthetic */ void c(UnityAdsWebData unityAdsWebData, f fVar) {
        if (fVar == null || fVar.getRequestType() == null) {
            UnityAdsDeviceLog.error("Got broken urlLoader!");
        } else {
            switch (fVar.getRequestType()) {
                case VideoPlan:
                    unityAdsWebData.a(fVar.getData());
                    break;
            }
            fVar.clear();
        }
        unityAdsWebData.i++;
        UnityAdsDeviceLog.debug("Total urls sent: " + unityAdsWebData.i);
        unityAdsWebData.l = false;
        unityAdsWebData.a();
    }

    static /* synthetic */ int d(UnityAdsWebData unityAdsWebData) {
        int i = unityAdsWebData.k;
        unityAdsWebData.k = i + 1;
        return i;
    }

    public enum UnityAdsVideoPosition {
        Start,
        FirstQuartile,
        MidPoint,
        ThirdQuartile,
        End;

        @Override // java.lang.Enum
        @SuppressLint({"DefaultLocale"})
        public final String toString() {
            switch (this) {
                case FirstQuartile:
                    return "first_quartile";
                case MidPoint:
                    return "mid_point";
                case ThirdQuartile:
                    return "third_quartile";
                case End:
                    return "video_end";
                case Start:
                    return "video_start";
                default:
                    return name().toString().toLowerCase();
            }
        }
    }

    public UnityAdsWebData() {
        this.g = null;
        this.g = new Object();
    }

    public void setWebDataListener(IUnityAdsWebDataListener iUnityAdsWebDataListener) {
        this.c = iUnityAdsWebDataListener;
    }

    public ArrayList getVideoPlanCampaigns() {
        return this.b;
    }

    public UnityAdsCampaign getCampaignById(String str) {
        if (str != null && this.b != null) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.b.size()) {
                    break;
                }
                if (this.b.get(i2) == null || ((UnityAdsCampaign) this.b.get(i2)).getCampaignId() == null || !((UnityAdsCampaign) this.b.get(i2)).getCampaignId().equals(str)) {
                    i = i2 + 1;
                } else {
                    return (UnityAdsCampaign) this.b.get(i2);
                }
            }
        }
        return null;
    }

    public ArrayList getViewableVideoPlanCampaigns() {
        if (this.b == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.b.size()) {
                return arrayList;
            }
            UnityAdsCampaign unityAdsCampaign = (UnityAdsCampaign) this.b.get(i2);
            if (unityAdsCampaign != null && !unityAdsCampaign.getCampaignStatus().equals(UnityAdsCampaign.UnityAdsCampaignStatus.VIEWED)) {
                arrayList.add(unityAdsCampaign);
            }
            i = i2 + 1;
        }
    }

    public boolean initCampaigns() {
        int i = 0;
        if (!this.m) {
            if (UnityAdsProperties.getBaseActivity() != null && UnityAdsUtils.isDebuggable(UnityAdsProperties.getBaseActivity()) && UnityAdsProperties.TEST_DATA != null) {
                a(UnityAdsProperties.TEST_DATA);
            } else {
                this.m = true;
                String[] strArrSplit = UnityAdsProperties.getCampaignQueryUrl().split("\\?");
                UnityAdsUtils.runOnUiThread(new g(this, strArrSplit[0], strArrSplit[1], UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_GET, e.VideoPlan, 0));
                File file = new File(UnityAdsUtils.getCacheDirectory() + "/pendingrequests.dat");
                if (file.exists()) {
                    try {
                        JSONArray jSONArray = new JSONObject(UnityAdsUtils.readFile(file, true)).getJSONArray("data");
                        if (jSONArray != null && jSONArray.length() > 0) {
                            while (true) {
                                int i2 = i;
                                if (i2 >= jSONArray.length()) {
                                    break;
                                }
                                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                                UnityAdsUtils.runOnUiThread(new g(this, jSONObject.getString("url"), jSONObject.getString(UnityAdsConstants.UNITY_ADS_FAILED_URL_BODY_KEY), jSONObject.getString(UnityAdsConstants.UNITY_ADS_FAILED_URL_METHODTYPE_KEY), e.getValueOf(jSONObject.getString(UnityAdsConstants.UNITY_ADS_FAILED_URL_REQUESTTYPE_KEY)), jSONObject.getInt(UnityAdsConstants.UNITY_ADS_FAILED_URL_RETRIES_KEY) + 1));
                                i = i2 + 1;
                            }
                        }
                    } catch (Exception e) {
                        UnityAdsDeviceLog.error("Problems while sending some of the failed urls.");
                    }
                    UnityAdsUtils.removeFile(file.toString());
                }
                a();
            }
        }
        return true;
    }

    public boolean sendCampaignViewProgress(UnityAdsCampaign unityAdsCampaign, UnityAdsVideoPosition unityAdsVideoPosition) {
        String str;
        String str2;
        if (unityAdsCampaign == null) {
            return false;
        }
        UnityAdsDeviceLog.debug("VP: " + unityAdsVideoPosition.toString() + ", " + UnityAdsProperties.UNITY_ADS_GAMER_ID);
        if (unityAdsVideoPosition == null || UnityAdsProperties.UNITY_ADS_GAMER_ID == null) {
            return false;
        }
        String str3 = String.format("%s/%s", String.format("%s%s/video/%s/%s", String.format("%s%s", UnityAdsProperties.UNITY_ADS_BASE_URL, UnityAdsConstants.UNITY_ADS_ANALYTICS_TRACKING_PATH), UnityAdsProperties.UNITY_ADS_GAMER_ID, unityAdsVideoPosition.toString(), unityAdsCampaign.getCampaignId()), UnityAdsProperties.UNITY_ADS_GAME_ID);
        UnityAdsZone currentZone = getZoneManager().getCurrentZone();
        String str4 = String.format("%s=%s", "zone", currentZone.getZoneId());
        try {
            String str5 = String.format("%s&%s=%s", str4, "platform", "android");
            String advertisingTrackingId = UnityAdsDevice.getAdvertisingTrackingId();
            if (advertisingTrackingId != null) {
                Object[] objArr = new Object[3];
                objArr[0] = str5;
                objArr[1] = "trackingEnabled";
                objArr[2] = Integer.valueOf(UnityAdsDevice.isLimitAdTrackingEnabled() ? 0 : 1);
                str2 = String.format("%s&%s=%s", String.format("%s&%s=%s", String.format("%s&%s=%d", objArr), "advertisingTrackingId", URLEncoder.encode(UnityAdsUtils.Md5(advertisingTrackingId).toLowerCase(), "UTF-8")), "rawAdvertisingTrackingId", URLEncoder.encode(advertisingTrackingId, "UTF-8"));
            } else {
                str2 = String.format("%s&%s=%s", str5, "deviceId", URLEncoder.encode(UnityAdsDevice.getAndroidId(true), "UTF-8"));
                if (!UnityAdsDevice.getAndroidId(false).equals("unknown")) {
                    str2 = String.format("%s&%s=%s", String.format("%s&%s=%s", str2, "androidId", URLEncoder.encode(UnityAdsDevice.getAndroidId(true), "UTF-8")), "rawAndroidId", URLEncoder.encode(UnityAdsDevice.getAndroidId(false), "UTF-8"));
                }
                if (!UnityAdsDevice.getMacAddress().equals("unknown")) {
                    str2 = String.format("%s&%s=%s", str2, "macAddress", URLEncoder.encode(UnityAdsDevice.getMacAddress(), "UTF-8"));
                }
            }
            str4 = String.format("%s&%s=%s", String.format("%s&%s=%s", String.format("%s&%s=%s", String.format("%s&%s=%s", String.format("%s&%s=%s", String.format("%s&%s=%s", String.format("%s&%s=%s", str2, "gameId", URLEncoder.encode(UnityAdsProperties.UNITY_ADS_GAME_ID, "UTF-8")), "sdkVersion", URLEncoder.encode(UnityAdsConstants.UNITY_ADS_VERSION, "UTF-8")), "softwareVersion", URLEncoder.encode(UnityAdsDevice.getSoftwareVersion(), "UTF-8")), UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_HARDWAREVERSION_KEY, URLEncoder.encode(UnityAdsDevice.getHardwareVersion(), "UTF-8")), "deviceType", Integer.valueOf(UnityAdsDevice.getDeviceType())), "connectionType", URLEncoder.encode(UnityAdsDevice.getConnectionType(), "UTF-8")), "screenSize", Integer.valueOf(UnityAdsDevice.getScreenSize()));
            str = String.format("%s&%s=%s", str4, "screenDensity", Integer.valueOf(UnityAdsDevice.getScreenDensity()));
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Problems creating campaigns query: " + e.getMessage() + e.getStackTrace().toString());
            str = str4;
        }
        if (currentZone.isIncentivized()) {
            str = String.format("%s&%s=%s", str, UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_REWARDITEM_KEY, ((UnityAdsIncentivizedZone) currentZone).itemManager().getCurrentItem().getKey());
        }
        if (currentZone.getGamerSid() != null) {
            str = String.format("%s&%s=%s", str, "sid", currentZone.getGamerSid());
        }
        UnityAdsUtils.runOnUiThread(new g(this, str3, str, UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_POST, e.VideoViewed, 0));
        return true;
    }

    public void sendAnalyticsRequest(String str, UnityAdsCampaign unityAdsCampaign) {
        if (unityAdsCampaign != null) {
            String str2 = String.format("%s", UnityAdsProperties.ANALYTICS_BASE_URL);
            String str3 = String.format("%s&%s=%s", String.format("%s&%s=%s", String.format("%s&%s=%s", String.format("%s=%s", "gameId", UnityAdsProperties.UNITY_ADS_GAME_ID), UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, str), UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_TRACKINGID_KEY, UnityAdsProperties.UNITY_ADS_GAMER_ID), UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_PROVIDERID_KEY, unityAdsCampaign.getCampaignId());
            UnityAdsZone currentZone = getZoneManager().getCurrentZone();
            String str4 = String.format("%s&%s=%s", str3, "zone", currentZone.getZoneId());
            String str5 = currentZone.isIncentivized() ? String.format("%s&%s=%s", str4, UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_REWARDITEM_KEY, ((UnityAdsIncentivizedZone) currentZone).itemManager().getCurrentItem().getKey()) : str4;
            UnityAdsUtils.runOnUiThread(new g(this, str2, currentZone.getGamerSid() != null ? String.format("%s&%s=%s", str5, "sid", currentZone.getGamerSid()) : str5, UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_GET, e.Analytics, 0));
        }
    }

    public void clearData() {
        if (this.b != null) {
            this.b.clear();
            this.b = null;
        }
        if (h != null) {
            h.clear();
            h = null;
        }
        this.a = null;
    }

    public void stopAllRequests() {
        synchronized (this.g) {
            if (this.d != null) {
                this.d.clear();
                this.d = null;
            }
            if (this.e != null) {
                this.e.clear();
                this.e = null;
            }
            if (this.f != null) {
                this.f.cancel(true);
                this.f = null;
            }
        }
    }

    public JSONObject getData() {
        return this.a;
    }

    public String getVideoPlan() {
        if (this.a != null) {
            return this.a.toString();
        }
        return null;
    }

    public static UnityAdsZoneManager getZoneManager() {
        return h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        synchronized (this.g) {
            if (this.d != null && this.d.size() > 0 && !this.l) {
                UnityAdsDeviceLog.debug("Starting next URL loader");
                this.l = true;
                this.f = (f) ((f) this.d.remove(0)).execute(new String[0]);
            }
        }
    }

    private void a(String str) {
        JSONArray jSONArray;
        JSONObject jSONObject = null;
        this.m = false;
        try {
            this.a = new JSONObject(str);
            if (this.a.has("data")) {
                try {
                    jSONObject = this.a.getJSONObject("data");
                } catch (Exception e) {
                    UnityAdsDeviceLog.error("Malformed data JSON");
                }
                Boolean bool = jSONObject.has(UnityAdsConstants.UNITY_ADS_WEBVIEW_URL_KEY);
                if (!jSONObject.has(UnityAdsConstants.UNITY_ADS_ANALYTICS_URL_KEY)) {
                    bool = false;
                }
                if (!jSONObject.has(UnityAdsConstants.UNITY_ADS_URL_KEY)) {
                    bool = false;
                }
                if (!jSONObject.has(UnityAdsConstants.UNITY_ADS_GAMER_ID_KEY)) {
                    bool = false;
                }
                if (!jSONObject.has(UnityAdsConstants.UNITY_ADS_CAMPAIGNS_KEY)) {
                    bool = false;
                }
                if (!jSONObject.has("zones")) {
                    bool = false;
                }
                UnityAdsProperties.WEBVIEW_BASE_URL = jSONObject.getString(UnityAdsConstants.UNITY_ADS_WEBVIEW_URL_KEY);
                UnityAdsProperties.ANALYTICS_BASE_URL = jSONObject.getString(UnityAdsConstants.UNITY_ADS_ANALYTICS_URL_KEY);
                UnityAdsProperties.UNITY_ADS_BASE_URL = jSONObject.getString(UnityAdsConstants.UNITY_ADS_URL_KEY);
                UnityAdsProperties.UNITY_ADS_GAMER_ID = jSONObject.getString(UnityAdsConstants.UNITY_ADS_GAMER_ID_KEY);
                if (jSONObject.has(UnityAdsConstants.UNITY_ADS_CAMPAIGN_REFRESH_VIEWS_KEY)) {
                    UnityAdsProperties.CAMPAIGN_REFRESH_VIEWS_COUNT = 0;
                    UnityAdsProperties.CAMPAIGN_REFRESH_VIEWS_MAX = jSONObject.getInt(UnityAdsConstants.UNITY_ADS_CAMPAIGN_REFRESH_VIEWS_KEY);
                }
                if (jSONObject.has(UnityAdsConstants.UNITY_ADS_CAMPAIGN_REFRESH_SECONDS_KEY)) {
                    UnityAdsProperties.CAMPAIGN_REFRESH_SECONDS = jSONObject.getInt(UnityAdsConstants.UNITY_ADS_CAMPAIGN_REFRESH_SECONDS_KEY);
                }
                if (bool.booleanValue() && (jSONArray = jSONObject.getJSONArray(UnityAdsConstants.UNITY_ADS_CAMPAIGNS_KEY)) != null) {
                    this.b = a(jSONArray);
                }
                if (this.b == null) {
                    this.b = new ArrayList();
                }
                UnityAdsDeviceLog.debug("Parsed total of " + this.b.size() + " campaigns");
                if (bool.booleanValue()) {
                    if (h != null) {
                        h.clear();
                        h = null;
                    }
                    h = new UnityAdsZoneManager(jSONObject.getJSONArray("zones"));
                }
                if (this.c != null && bool.booleanValue() && this.b != null && this.b.size() > 0) {
                    UnityAdsDeviceLog.debug("WebDataCompleted: " + str);
                    this.c.onWebDataCompleted();
                    return;
                } else {
                    b();
                    return;
                }
            }
            b();
        } catch (Exception e2) {
            UnityAdsDeviceLog.error("Malformed JSON: " + e2.getMessage());
            if (e2.getStackTrace() != null) {
                StackTraceElement[] stackTrace = e2.getStackTrace();
                for (StackTraceElement stackTraceElement : stackTrace) {
                    UnityAdsDeviceLog.error("Malformed JSON: " + stackTraceElement.toString());
                }
            }
            b();
        }
    }

    private void b() {
        if (this.c != null) {
            this.c.onWebDataFailed();
        }
    }

    private static ArrayList a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                UnityAdsCampaign unityAdsCampaign = new UnityAdsCampaign(jSONArray.getJSONObject(i));
                if (unityAdsCampaign.hasValidData()) {
                    UnityAdsDeviceLog.debug("Adding campaign to cache");
                    arrayList.add(unityAdsCampaign);
                }
            } catch (Exception e) {
                UnityAdsDeviceLog.error("Problem with the campaign, skipping.");
            }
        }
        return arrayList;
    }
}
