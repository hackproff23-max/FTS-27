package com.unity3d.ads.android.zone;

import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsZone {
    private JSONObject a;
    private JSONObject b;
    private String c;
    private String d;
    private boolean e;
    private String f = null;
    private ArrayList g = new ArrayList();

    public UnityAdsZone(JSONObject jSONObject) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = false;
        this.a = new JSONObject(jSONObject.toString());
        this.b = jSONObject;
        this.c = jSONObject.getString("id");
        this.d = jSONObject.getString("name");
        this.e = jSONObject.optBoolean(UnityAdsConstants.UNITY_ADS_ZONE_DEFAULT_KEY, true);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(UnityAdsConstants.UNITY_ADS_ZONE_ALLOW_CLIENT_OVERRIDES_KEY);
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                this.g.add(jSONArrayOptJSONArray.getString(i));
            }
        }
    }

    public String getZoneId() {
        return this.c;
    }

    public String getZoneName() {
        return this.d;
    }

    public JSONObject getZoneOptions() {
        return this.b;
    }

    public boolean isDefault() {
        return this.e;
    }

    public boolean isIncentivized() {
        return false;
    }

    public boolean muteVideoSounds() {
        return this.b.optBoolean("muteVideoSounds", false);
    }

    public boolean noOfferScreen() {
        return this.b.optBoolean("noOfferScreen", true);
    }

    public boolean openAnimated() {
        return this.b.optBoolean("openAnimated", false);
    }

    public boolean useDeviceOrientationForVideo() {
        return this.b.optBoolean("useDeviceOrientationForVideo", false);
    }

    public long allowVideoSkipInSeconds() {
        return this.b.optLong("allowSkipVideoInSeconds", 0L);
    }

    public long disableBackButtonForSeconds() {
        return this.b.optLong("disableBackButtonForSeconds", 0L);
    }

    public String getGamerSid() {
        return this.f;
    }

    public void setGamerSid(String str) {
        this.f = str;
    }

    public void mergeOptions(Map map) {
        try {
            this.b = new JSONObject(this.a.toString());
            setGamerSid(null);
        } catch (JSONException e) {
        }
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                if (allowsOverride((String) entry.getKey())) {
                    try {
                        this.b.put((String) entry.getKey(), entry.getValue());
                    } catch (JSONException e2) {
                        UnityAdsDeviceLog.error("Unable to set JSON value");
                    }
                }
            }
            if (map.containsKey("sid")) {
                setGamerSid((String) map.get("sid"));
            }
        }
    }

    public boolean allowsOverride(String str) {
        return this.g.contains(str);
    }
}
