package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;

/* JADX INFO: loaded from: classes.dex */
class z implements Runnable {
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ y b;

    z(y yVar, AppLovinAd appLovinAd) {
        this.b = yVar;
        this.a = appLovinAd;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.b.adReceived(this.a);
    }
}
