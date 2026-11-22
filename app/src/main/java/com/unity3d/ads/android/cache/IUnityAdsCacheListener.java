package com.unity3d.ads.android.cache;

import com.unity3d.ads.android.campaign.UnityAdsCampaignHandler;

/* JADX INFO: loaded from: classes.dex */
public interface IUnityAdsCacheListener {
    void onAllCampaignsReady();

    void onCampaignReady(UnityAdsCampaignHandler unityAdsCampaignHandler);

    void onCampaignUpdateStarted();
}
