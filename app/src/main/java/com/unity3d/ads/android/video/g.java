package com.unity3d.ads.android.video;

import android.media.MediaPlayer;

/* JADX INFO: compiled from: UnityAdsVideoPlayView.java */
/* JADX INFO: loaded from: classes.dex */
final class g implements MediaPlayer.OnCompletionListener {
    final /* synthetic */ UnityAdsVideoPlayView a;

    g(UnityAdsVideoPlayView unityAdsVideoPlayView) {
        this.a = unityAdsVideoPlayView;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public final void onCompletion(MediaPlayer mediaPlayer) {
        UnityAdsVideoPlayView.f(this.a);
        this.a.l.onCompletion(mediaPlayer);
    }
}
