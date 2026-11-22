package com.unity3d.ads.android.item;

import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsRewardItem {
    private String a;
    private String b;
    private String c;
    private JSONObject d;
    private String[] e = {UnityAdsConstants.UNITY_ADS_REWARD_ITEMKEY_KEY, "name", "picture"};

    public UnityAdsRewardItem(JSONObject jSONObject) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.d = jSONObject;
        try {
            this.a = this.d.getString(UnityAdsConstants.UNITY_ADS_REWARD_ITEMKEY_KEY);
            this.b = this.d.getString("name");
            this.c = this.d.getString("picture");
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Problem parsing campaign values");
        }
    }

    public String getKey() {
        return this.a;
    }

    public String getName() {
        return this.b;
    }

    public String getPictureUrl() {
        return this.c;
    }

    public boolean hasValidData() {
        if (this.d == null) {
            return false;
        }
        for (String str : this.e) {
            if (!this.d.has(str)) {
                return false;
            }
        }
        return true;
    }

    public void clearData() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
    }

    public Map getDetails() {
        HashMap map = new HashMap();
        map.put("name", getName());
        map.put("picture", getPictureUrl());
        return map;
    }
}
