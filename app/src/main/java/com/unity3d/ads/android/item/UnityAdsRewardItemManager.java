package com.unity3d.ads.android.item;

import com.unity3d.ads.android.UnityAdsDeviceLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsRewardItemManager {
    private Map a;
    private UnityAdsRewardItem b;
    private UnityAdsRewardItem c;

    public UnityAdsRewardItemManager(JSONArray jSONArray, String str) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.a = new HashMap();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                UnityAdsRewardItem unityAdsRewardItem = new UnityAdsRewardItem(jSONArray.getJSONObject(i));
                if (unityAdsRewardItem.hasValidData()) {
                    if (unityAdsRewardItem.getKey().equals(str)) {
                        this.b = unityAdsRewardItem;
                        this.c = unityAdsRewardItem;
                    }
                    this.a.put(unityAdsRewardItem.getKey(), unityAdsRewardItem);
                }
            } catch (JSONException e) {
                UnityAdsDeviceLog.error("Failed to parse reward item");
            }
        }
    }

    public UnityAdsRewardItem getItem(String str) {
        if (this.a.containsKey(str)) {
            return (UnityAdsRewardItem) this.a.get(str);
        }
        return null;
    }

    public UnityAdsRewardItem getCurrentItem() {
        return this.b;
    }

    public UnityAdsRewardItem getDefaultItem() {
        return this.c;
    }

    public boolean setCurrentItem(String str) {
        if (!this.a.containsKey(str)) {
            return false;
        }
        this.b = (UnityAdsRewardItem) this.a.get(str);
        return true;
    }

    public ArrayList allItems() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.a.values().iterator();
        while (it.hasNext()) {
            arrayList.add((UnityAdsRewardItem) it.next());
        }
        return arrayList;
    }

    public int itemCount() {
        return this.a.size();
    }
}
