package com.applovin.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

/* JADX INFO: loaded from: classes.dex */
class h implements AppLovinAdClickListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    h(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    @Override // com.applovin.sdk.AppLovinAdClickListener
    public void adClicked(AppLovinAd appLovinAd) {
        AppLovinAdClickListener appLovinAdClickListenerE = this.a.b.e();
        if (appLovinAdClickListenerE != null) {
            appLovinAdClickListenerE.adClicked(appLovinAd);
        }
    }
}
