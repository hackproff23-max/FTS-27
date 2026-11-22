package com.unity3d.ads.android.video;

import android.view.View;

/* JADX INFO: compiled from: UnityAdsVideoPlayView.java */
/* JADX INFO: loaded from: classes.dex */
final class j implements View.OnFocusChangeListener {
    final /* synthetic */ UnityAdsVideoPlayView a;

    j(UnityAdsVideoPlayView unityAdsVideoPlayView) {
        this.a = unityAdsVideoPlayView;
    }

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z) {
        if (!z) {
            this.a.pauseVideo();
        }
    }
}
