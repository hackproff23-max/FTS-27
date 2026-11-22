package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;

/* JADX INFO: loaded from: classes.dex */
class y implements AppLovinAdLoadListener {
    final /* synthetic */ w a;
    private final AppLovinAdLoadListener b;

    y(w wVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        this.a = wVar;
        this.b = appLovinAdLoadListener;
    }

    @Override // com.applovin.sdk.AppLovinAdLoadListener
    public void adReceived(AppLovinAd appLovinAd) {
        this.a.c = (AppLovinAdInternal) appLovinAd;
        if (this.b != null) {
            this.a.e.post(new z(this, appLovinAd));
        }
    }

    @Override // com.applovin.sdk.AppLovinAdLoadListener
    public void failedToReceiveAd(int i) {
        if (this.b != null) {
            this.a.e.post(new aa(this, i));
        }
    }
}
