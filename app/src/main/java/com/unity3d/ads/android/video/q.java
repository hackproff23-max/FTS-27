package com.unity3d.ads.android.video;

import com.unity3d.ads.android.UnityAdsDeviceLog;

/* JADX INFO: compiled from: UnityAdsVideoPlayView.java */
/* JADX INFO: loaded from: classes.dex */
final class q implements Runnable {
    final /* synthetic */ l a;

    q(l lVar) {
        this.a = lVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        UnityAdsDeviceLog.error("Buffering taking too long.. cancelling video play");
        this.a.a.b();
    }
}
