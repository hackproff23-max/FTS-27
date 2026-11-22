package com.unity3d.ads.android.zone;

import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsZoneManager {
    private Map a;
    private UnityAdsZone b;
    private UnityAdsZone c;

    public UnityAdsZoneManager(JSONArray jSONArray) {
        UnityAdsZone unityAdsZone;
        this.a = null;
        this.b = null;
        this.c = null;
        this.a = new HashMap();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.getBoolean(UnityAdsConstants.UNITY_ADS_ZONE_INCENTIVIZED_KEY)) {
                    unityAdsZone = new UnityAdsIncentivizedZone(jSONObject);
                } else {
                    unityAdsZone = new UnityAdsZone(jSONObject);
                }
                if (unityAdsZone.isDefault()) {
                    if (unityAdsZone.isIncentivized()) {
                        this.b = new UnityAdsIncentivizedZone(jSONObject);
                    } else {
                        this.b = new UnityAdsZone(jSONObject);
                    }
                }
                if (this.c == null && unityAdsZone.isDefault()) {
                    this.c = unityAdsZone;
                }
                this.a.put(unityAdsZone.getZoneId(), unityAdsZone);
            } catch (JSONException e) {
                UnityAdsDeviceLog.error("Failed to parse zone");
            }
        }
    }

    public UnityAdsZone getZone(String str) {
        if (this.a.containsKey(str)) {
            return (UnityAdsZone) this.a.get(str);
        }
        return null;
    }

    public UnityAdsZone getCurrentZone() {
        return this.c;
    }

    public boolean setCurrentZone(String str) {
        if (this.a.containsKey(str)) {
            this.c = (UnityAdsZone) this.a.get(str);
            return true;
        }
        this.c = null;
        return false;
    }

    public int zoneCount() {
        if (this.a != null) {
            return this.a.size();
        }
        return 0;
    }

    public JSONArray getZonesJson() {
        JSONArray jSONArray = new JSONArray();
        Iterator it = this.a.values().iterator();
        while (it.hasNext()) {
            jSONArray.put(((UnityAdsZone) it.next()).getZoneOptions());
        }
        return jSONArray;
    }

    public void clear() {
        this.c = null;
        this.a.clear();
        this.a = null;
    }
}
