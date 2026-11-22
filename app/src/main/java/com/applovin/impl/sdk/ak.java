package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;

/* JADX INFO: loaded from: classes.dex */
class ak implements Runnable {
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ int b;
    final /* synthetic */ ab c;

    ak(ab abVar, AppLovinAd appLovinAd, int i) {
        this.c = abVar;
        this.a = appLovinAd;
        this.b = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.c.f.validationRequestFailed(this.a, this.b);
    }
}
