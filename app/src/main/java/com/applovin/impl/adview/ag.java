package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;

/* JADX INFO: loaded from: classes.dex */
class ag implements Runnable {
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ ad b;

    ag(ad adVar, AppLovinAd appLovinAd) {
        this.b = adVar;
        this.a = appLovinAd;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.b.g != null) {
            this.b.g.adReceived(this.a);
        }
    }
}
