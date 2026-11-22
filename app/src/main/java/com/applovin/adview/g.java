package com.applovin.adview;

import com.applovin.impl.sdk.AppLovinAdInternal;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

/* JADX INFO: loaded from: classes.dex */
class g implements AppLovinAdDisplayListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    g(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    @Override // com.applovin.sdk.AppLovinAdDisplayListener
    public void adDisplayed(AppLovinAd appLovinAd) {
        this.a.g = (AppLovinAdInternal) appLovinAd;
        if (this.a.i) {
            return;
        }
        this.a.a(appLovinAd);
    }

    @Override // com.applovin.sdk.AppLovinAdDisplayListener
    public void adHidden(AppLovinAd appLovinAd) {
        this.a.b(appLovinAd);
    }
}
