package com.unity3d.ads.android.video;

import android.media.MediaPlayer;
import com.unity3d.ads.android.UnityAdsDeviceLog;

/* JADX INFO: compiled from: UnityAdsVideoPlayView.java */
/* JADX INFO: loaded from: classes.dex */
final class h implements MediaPlayer.OnPreparedListener {
    final /* synthetic */ UnityAdsVideoPlayView a;

    h(UnityAdsVideoPlayView unityAdsVideoPlayView) {
        this.a = unityAdsVideoPlayView;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public final void onPrepared(MediaPlayer mediaPlayer) {
        UnityAdsDeviceLog.entered();
        this.a.x = mediaPlayer;
        if (this.a.y) {
            UnityAdsVideoPlayView.h(this.a);
            this.a.x.setVolume(0.0f, 0.0f);
        }
        UnityAdsVideoPlayView.j(this.a);
    }
}
