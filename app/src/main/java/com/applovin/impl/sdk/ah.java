package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class ah implements Runnable {
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ Map b;
    final /* synthetic */ ab c;

    ah(ab abVar, AppLovinAd appLovinAd, Map map) {
        this.c = abVar;
        this.a = appLovinAd;
        this.b = map;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.c.f.userRewardVerified(this.a, this.b);
    }
}
