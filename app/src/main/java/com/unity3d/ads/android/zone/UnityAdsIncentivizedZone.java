package com.unity3d.ads.android.zone;

import com.unity3d.ads.android.item.UnityAdsRewardItem;
import com.unity3d.ads.android.item.UnityAdsRewardItemManager;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsIncentivizedZone extends UnityAdsZone {
    private UnityAdsRewardItemManager a;

    public UnityAdsIncentivizedZone(JSONObject jSONObject) {
        super(jSONObject);
        this.a = null;
        this.a = new UnityAdsRewardItemManager(jSONObject.getJSONArray(UnityAdsConstants.UNITY_ADS_ZONE_REWARD_ITEMS_KEY), new UnityAdsRewardItem(jSONObject.getJSONObject(UnityAdsConstants.UNITY_ADS_ZONE_DEFAULT_REWARD_ITEM_KEY)).getKey());
    }

    @Override // com.unity3d.ads.android.zone.UnityAdsZone
    public boolean isIncentivized() {
        return true;
    }

    public UnityAdsRewardItemManager itemManager() {
        return this.a;
    }
}
