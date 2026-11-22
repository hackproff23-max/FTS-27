package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;

/* JADX INFO: loaded from: classes.dex */
class ae implements AppLovinAdLoadListener {
    final /* synthetic */ ad a;

    ae(ad adVar) {
        this.a = adVar;
    }

    @Override // com.applovin.sdk.AppLovinAdLoadListener
    public void adReceived(AppLovinAd appLovinAd) {
        this.a.a(appLovinAd);
        this.a.showAndRender(appLovinAd);
    }

    @Override // com.applovin.sdk.AppLovinAdLoadListener
    public void failedToReceiveAd(int i) {
        this.a.a(i);
    }
}
