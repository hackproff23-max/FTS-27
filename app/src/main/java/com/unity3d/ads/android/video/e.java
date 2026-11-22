package com.unity3d.ads.android.video;

/* JADX INFO: compiled from: UnityAdsVideoPlayView.java */
/* JADX INFO: loaded from: classes.dex */
final class e implements Runnable {
    final /* synthetic */ UnityAdsVideoPlayView a;

    e(UnityAdsVideoPlayView unityAdsVideoPlayView) {
        this.a = unityAdsVideoPlayView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.n.start();
        this.a.setKeepScreenOn(true);
    }
}
