package com.unity3d.ads.android.video;

import android.view.View;
import com.unity3d.ads.android.view.UnityAdsMuteVideoButton;

/* JADX INFO: compiled from: UnityAdsVideoPlayView.java */
/* JADX INFO: loaded from: classes.dex */
final class k implements View.OnClickListener {
    final /* synthetic */ UnityAdsVideoPlayView a;

    k(UnityAdsVideoPlayView unityAdsVideoPlayView) {
        this.a = unityAdsVideoPlayView;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (this.a.s && this.a.u) {
            if (this.a.y) {
                this.a.y = false;
                this.a.r.setState(UnityAdsMuteVideoButton.UnityAdsMuteVideoButtonState.UnMuted);
                this.a.x.setVolume(this.a.z, this.a.z);
            } else {
                this.a.y = true;
                this.a.r.setState(UnityAdsMuteVideoButton.UnityAdsMuteVideoButtonState.Muted);
                UnityAdsVideoPlayView.h(this.a);
                this.a.x.setVolume(0.0f, 0.0f);
            }
        }
    }
}
