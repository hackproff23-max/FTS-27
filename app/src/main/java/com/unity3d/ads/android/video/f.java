package com.unity3d.ads.android.video;

import android.content.Context;
import android.widget.VideoView;

/* JADX INFO: compiled from: UnityAdsVideoPlayView.java */
/* JADX INFO: loaded from: classes.dex */
final class f extends VideoView {
    final /* synthetic */ UnityAdsVideoPlayView a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    f(UnityAdsVideoPlayView unityAdsVideoPlayView, Context context) {
        super(context);
        this.a = unityAdsVideoPlayView;
    }

    @Override // android.view.SurfaceView, android.view.View
    public final void onWindowVisibilityChanged(int i) {
        if (i != 0) {
            if (!this.a.w) {
                this.a.l.onVideoHidden();
                return;
            }
            return;
        }
        super.onWindowVisibilityChanged(i);
    }
}
