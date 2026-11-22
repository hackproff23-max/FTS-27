package com.applovin.adview;

import com.applovin.impl.adview.s;

/* JADX INFO: loaded from: classes.dex */
class q implements Runnable {
    final /* synthetic */ s a;
    final /* synthetic */ AppLovinInterstitialActivity b;

    q(AppLovinInterstitialActivity appLovinInterstitialActivity, s sVar) {
        this.b = appLovinInterstitialActivity;
        this.a = sVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.a.equals(this.b.u)) {
            this.b.h();
        } else if (this.a.equals(this.b.w)) {
            this.b.j();
        }
    }
}
