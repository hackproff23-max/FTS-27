package com.applovin.adview;

import android.view.animation.AlphaAnimation;

/* JADX INFO: loaded from: classes.dex */
class o implements Runnable {
    final /* synthetic */ AppLovinInterstitialActivity a;

    o(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.a.n) {
                this.a.u.setVisibility(0);
                return;
            }
            this.a.n = true;
            if (this.a.i()) {
                this.a.v.setVisibility(0);
                this.a.v.bringToFront();
            }
            this.a.u.setVisibility(0);
            this.a.u.bringToFront();
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(this.a.e.f());
            alphaAnimation.setRepeatCount(0);
            this.a.u.startAnimation(alphaAnimation);
        } catch (Throwable th) {
            this.a.dismiss();
        }
    }
}
