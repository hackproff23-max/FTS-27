package com.unity3d.ads.android.video;

import android.media.MediaPlayer;
import com.unity3d.ads.android.UnityAdsDeviceLog;

/* JADX INFO: compiled from: UnityAdsVideoPlayView.java */
/* JADX INFO: loaded from: classes.dex */
final class a implements MediaPlayer.OnErrorListener {
    final /* synthetic */ UnityAdsVideoPlayView a;

    a(UnityAdsVideoPlayView unityAdsVideoPlayView) {
        this.a = unityAdsVideoPlayView;
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        UnityAdsDeviceLog.error("For some reason the device failed to play the video (error: " + i + ", " + i2 + "), a crash was prevented.");
        this.a.b();
        return true;
    }
}
