package com.applovin.impl.adview;

import com.applovin.impl.sdk.AppLovinAdInternal;
import com.applovin.sdk.AppLovinAd;

/* JADX INFO: loaded from: classes.dex */
class z implements Runnable {
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ v b;

    z(v vVar, AppLovinAd appLovinAd) {
        this.b = vVar;
        this.a = appLovinAd;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.e.renderAd(this.a);
        this.b.a(((AppLovinAdInternal) this.a).getCloseStyle());
    }
}
