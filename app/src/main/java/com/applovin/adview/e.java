package com.applovin.adview;

import android.view.animation.Animation;

/* JADX INFO: loaded from: classes.dex */
class e implements Animation.AnimationListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    e(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        this.a.x.setVisibility(8);
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationStart(Animation animation) {
    }
}
