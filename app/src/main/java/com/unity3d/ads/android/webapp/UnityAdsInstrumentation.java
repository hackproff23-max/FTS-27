package com.unity3d.ads.android.webapp;

import com.unity3d.ads.android.UnityAds;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.campaign.UnityAdsCampaign;
import com.unity3d.ads.android.data.UnityAdsDevice;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsInstrumentation {
    private static ArrayList a = null;

    private static JSONObject a(Map map) {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : map.keySet()) {
            if (map.containsKey(str) && map.get(str) != null) {
                try {
                    jSONObject.put(str, map.get(str));
                } catch (Exception e) {
                    UnityAdsDeviceLog.error("Could not add value: " + str);
                }
            }
        }
        return jSONObject;
    }

    private static JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject != null && jSONObject2 != null) {
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                try {
                    String string = itKeys.next().toString();
                    jSONObject.put(string, jSONObject2.get(string));
                } catch (Exception e) {
                    UnityAdsDeviceLog.error("Problems creating JSON");
                }
            }
            return jSONObject;
        }
        if (jSONObject == null) {
            if (jSONObject2 != null) {
                return jSONObject2;
            }
            return null;
        }
        return jSONObject;
    }

    private static JSONObject a(UnityAdsCampaign unityAdsCampaign) {
        if (unityAdsCampaign == null) {
            return null;
        }
        String str = UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_VIDEOPLAY_HLSL;
        if (unityAdsCampaign.shouldCacheVideo().booleanValue() && UnityAdsUtils.canUseExternalStorage()) {
            str = UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_VIDEOPLAY_CACHED;
        }
        String connectionType = UnityAdsDevice.getConnectionType();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_VIDEOPLAYBACKTYPE_KEY, str);
            jSONObject.put("connectionType", connectionType);
            jSONObject.put("campaignId", unityAdsCampaign.getCampaignId());
            return jSONObject;
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Could not create instrumentation JSON");
            return null;
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private static void a() {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        if (a != null) {
            for (Map map : a) {
                JSONObject jSONObject2 = new JSONObject();
                String str = (String) map.keySet().iterator().next();
                JSONObject jSONObject3 = (JSONObject) map.get(str);
                try {
                    jSONObject2.put(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_TYPE_KEY, str);
                    jSONObject2.put("data", jSONObject3);
                    jSONArray.put(jSONObject2);
                    try {
                        jSONObject.put("events", jSONArray);
                    } catch (Exception e) {
                    }
                } catch (Exception e2) {
                }
            }
            if (UnityAds.mainview == null || UnityAds.mainview.webview == null || !UnityAds.mainview.webview.isWebAppLoaded()) {
                return;
            }
            UnityAdsDeviceLog.debug("Sending to webapp");
            UnityAds.mainview.webview.sendNativeEventToWebApp(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_KEY, jSONObject);
            a.clear();
            a = null;
        }
    }

    private static void a(String str, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject2.put(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_TYPE_KEY, str);
            jSONObject2.put("data", jSONObject);
            jSONArray.put(jSONObject2);
            jSONObject3.put("events", jSONArray);
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Couldn't create final data");
        }
        if (UnityAds.mainview != null && UnityAds.mainview.webview != null && UnityAds.mainview.webview.isWebAppLoaded()) {
            UnityAdsDeviceLog.debug("Sending to webapp");
            UnityAds.mainview.webview.sendNativeEventToWebApp(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_KEY, jSONObject3);
            return;
        }
        UnityAdsDeviceLog.debug("WebApp not initialized, could not send event!");
        if (a == null) {
            a = new ArrayList();
        }
        HashMap map = new HashMap();
        map.put(str, jSONObject);
        a.add(map);
    }

    public static void gaInstrumentationVideoPlay(UnityAdsCampaign unityAdsCampaign, Map map) {
        JSONObject jSONObjectA = a(a(unityAdsCampaign), a(map));
        a();
        a(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_TYPE_VIDEOPLAY, jSONObjectA);
    }

    public static void gaInstrumentationVideoError(UnityAdsCampaign unityAdsCampaign, Map map) {
        JSONObject jSONObjectA = a(a(unityAdsCampaign), a(map));
        a();
        a(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_TYPE_VIDEOERROR, jSONObjectA);
    }

    public static void gaInstrumentationVideoAbort(UnityAdsCampaign unityAdsCampaign, Map map) {
        JSONObject jSONObjectA = a(a(unityAdsCampaign), a(map));
        a();
        a(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_TYPE_VIDEOABORT, jSONObjectA);
    }

    public static void gaInstrumentationVideoCaching(UnityAdsCampaign unityAdsCampaign, Map map) {
        JSONObject jSONObjectA = a(a(unityAdsCampaign), a(map));
        a();
        a(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_TYPE_VIDEOCACHING, jSONObjectA);
    }
}
