package com.unity3d.ads.android.webapp;

import com.unity3d.ads.android.UnityAdsDeviceLog;

/* JADX INFO: compiled from: UnityAdsWebData.java */
/* JADX INFO: loaded from: classes.dex */
final class d implements Runnable {
    final /* synthetic */ UnityAdsWebData a;
    private f b;

    public d(UnityAdsWebData unityAdsWebData, f fVar) {
        this.a = unityAdsWebData;
        this.b = null;
        this.b = fVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.b.cancel(true);
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Cancelling urlLoader got exception: " + e.getMessage());
        }
    }
}
