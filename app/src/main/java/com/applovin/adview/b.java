package com.applovin.adview;

import android.media.MediaPlayer;

/* JADX INFO: loaded from: classes.dex */
class b implements MediaPlayer.OnPreparedListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    b(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        this.a.t.setVideoSize(mediaPlayer.getVideoWidth(), mediaPlayer.getVideoHeight());
        mediaPlayer.setDisplay(this.a.t.getHolder());
        mediaPlayer.setOnErrorListener(new c(this));
    }
}
