package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;

/* JADX INFO: loaded from: classes.dex */
class ad implements Runnable {
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ ab b;

    ad(ab abVar, AppLovinAd appLovinAd) {
        this.b = abVar;
        this.a = appLovinAd;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.c.adHidden(this.a);
    }
}
