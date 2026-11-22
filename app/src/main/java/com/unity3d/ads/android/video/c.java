package com.unity3d.ads.android.video;

/* JADX INFO: compiled from: UnityAdsVideoPlayView.java */
/* JADX INFO: loaded from: classes.dex */
final class c implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ boolean b;
    final /* synthetic */ boolean c;
    final /* synthetic */ UnityAdsVideoPlayView d;

    c(UnityAdsVideoPlayView unityAdsVideoPlayView, int i, boolean z, boolean z2) {
        this.d = unityAdsVideoPlayView;
        this.a = i;
        this.b = z;
        this.c = z2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.d.h != null) {
            this.d.h.setVisibility(this.a);
        }
        if (this.a == 0) {
            if (this.d.c == null) {
                this.d.g();
            }
            UnityAdsVideoPlayView.t(this.d);
        } else if (!this.b) {
            this.d.k();
        } else if (!this.c) {
            this.d.h();
        } else {
            UnityAdsVideoPlayView.t(this.d);
        }
    }
}
