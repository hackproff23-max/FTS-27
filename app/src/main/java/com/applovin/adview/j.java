package com.applovin.adview;

import android.media.MediaPlayer;

/* JADX INFO: loaded from: classes.dex */
class j implements MediaPlayer.OnErrorListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    j(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.a.r.post(new k(this, i, i2));
        return true;
    }
}
