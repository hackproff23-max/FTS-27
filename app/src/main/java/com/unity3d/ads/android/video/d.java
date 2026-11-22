package com.unity3d.ads.android.video;

/* JADX INFO: compiled from: UnityAdsVideoPlayView.java */
/* JADX INFO: loaded from: classes.dex */
final class d implements Runnable {
    final /* synthetic */ UnityAdsVideoPlayView a;

    d(UnityAdsVideoPlayView unityAdsVideoPlayView) {
        this.a = unityAdsVideoPlayView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.n.pause();
        this.a.setKeepScreenOn(false);
        UnityAdsVideoPlayView.c(this.a);
    }
}
