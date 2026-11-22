package com.unity3d.ads.android.unity3d;

import com.unity3d.ads.android.UnityAds;
import com.unity3d.ads.android.UnityAdsDeviceLog;

/* JADX INFO: compiled from: UnityAdsUnityWrapper.java */
/* JADX INFO: loaded from: classes.dex */
final class a implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ UnityAdsUnityWrapper b;
    final /* synthetic */ UnityAdsUnityWrapper c;

    a(UnityAdsUnityWrapper unityAdsUnityWrapper, int i, UnityAdsUnityWrapper unityAdsUnityWrapper2) {
        this.c = unityAdsUnityWrapper;
        this.a = i;
        this.b = unityAdsUnityWrapper2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        UnityAdsDeviceLog.setLogLevel(this.a);
        UnityAds.setTestMode(this.c.e);
        UnityAds.init(this.c.a, this.c.c, this.b);
    }
}
