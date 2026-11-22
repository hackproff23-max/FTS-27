package com.unity3d.ads.android;

/* JADX INFO: compiled from: UnityAds.java */
/* JADX INFO: loaded from: classes.dex */
final class e implements Runnable {
    e() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!UnityAds.c) {
            UnityAdsDeviceLog.debug("Unity Ads ready.");
            UnityAds.q.onFetchCompleted();
            UnityAds.f();
        }
    }
}
