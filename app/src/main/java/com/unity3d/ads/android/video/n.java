package com.unity3d.ads.android.video;

/* JADX INFO: compiled from: UnityAdsVideoPlayView.java */
/* JADX INFO: loaded from: classes.dex */
final class n implements Runnable {
    final /* synthetic */ l a;

    n(l lVar) {
        this.a = lVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        UnityAdsVideoPlayView.t(this.a.a);
    }
}
