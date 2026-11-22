package com.applifier.impact.android;

/* JADX INFO: loaded from: classes.dex */
public interface IApplifierImpactListener {
    void onCampaignsAvailable();

    void onCampaignsFetchFailed();

    void onImpactClose();

    void onImpactOpen();

    void onVideoCompleted(String str, boolean z);

    void onVideoStarted();
}
