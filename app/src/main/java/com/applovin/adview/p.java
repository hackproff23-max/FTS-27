package com.applovin.adview;

import android.view.animation.AlphaAnimation;

/* JADX INFO: loaded from: classes.dex */
class p implements Runnable {
    final /* synthetic */ AppLovinInterstitialActivity a;

    p(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.a.o || this.a.w == null) {
                return;
            }
            this.a.o = true;
            this.a.w.setVisibility(0);
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(this.a.e.f());
            alphaAnimation.setRepeatCount(0);
            this.a.w.startAnimation(alphaAnimation);
        } catch (Throwable th) {
            this.a.d.w("AppLovinInterstitialActivity", "Unable to show skip button: " + th);
        }
    }
}
