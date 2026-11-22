package com.unity3d.ads.android.video;

/* JADX INFO: compiled from: UnityAdsVideoPlayView.java */
/* JADX INFO: loaded from: classes.dex */
final class m implements Runnable {
    final /* synthetic */ l a;

    m(l lVar) {
        this.a = lVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.a.a.b != null) {
            this.a.a.b.setText(new StringBuilder().append(Math.round(Math.ceil((this.a.e - this.a.b.floatValue()) / 1000.0f))).toString());
        }
    }
}
