package com.unity3d.ads.android.video;

import android.view.View;

/* JADX INFO: compiled from: UnityAdsVideoPlayView.java */
/* JADX INFO: loaded from: classes.dex */
final class b implements View.OnClickListener {
    final /* synthetic */ UnityAdsVideoPlayView a;

    b(UnityAdsVideoPlayView unityAdsVideoPlayView) {
        this.a = unityAdsVideoPlayView;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.a.l.onVideoSkip();
    }
}
