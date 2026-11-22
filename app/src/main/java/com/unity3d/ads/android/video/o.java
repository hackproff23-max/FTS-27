package com.unity3d.ads.android.video;

/* JADX INFO: compiled from: UnityAdsVideoPlayView.java */
/* JADX INFO: loaded from: classes.dex */
final class o implements Runnable {
    final /* synthetic */ l a;

    o(l lVar) {
        this.a = lVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.a.a.d != null && !this.a.g) {
            this.a.a.c.setVisibility(0);
            this.a.a.d.setText("You can skip this video in " + Math.round(Math.ceil(((this.a.a.e * 1000) - this.a.b.floatValue()) / 1000.0f)) + " seconds");
        }
    }
}
