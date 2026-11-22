package com.applovin.adview;

import android.media.MediaPlayer;

/* JADX INFO: loaded from: classes.dex */
class i implements MediaPlayer.OnCompletionListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    i(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        this.a.m = true;
        this.a.s();
    }
}
