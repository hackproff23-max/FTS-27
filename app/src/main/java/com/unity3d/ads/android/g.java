package com.unity3d.ads.android;

import java.util.TimerTask;

/* JADX INFO: compiled from: UnityAds.java */
/* JADX INFO: loaded from: classes.dex */
final class g extends TimerTask {
    g() {
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        if (!UnityAds.b) {
            UnityAdsDeviceLog.debug("Refreshing ad plan to get new data");
            UnityAds.webdata.initCampaigns();
        } else {
            UnityAdsDeviceLog.debug("Refreshing ad plan after current ad");
            UnityAds.j();
        }
    }
}
