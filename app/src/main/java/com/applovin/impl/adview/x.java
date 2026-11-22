package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

/* JADX INFO: loaded from: classes.dex */
class x implements AppLovinAdClickListener {
    final /* synthetic */ ad a;
    final /* synthetic */ v b;

    x(v vVar, ad adVar) {
        this.b = vVar;
        this.a = adVar;
    }

    @Override // com.applovin.sdk.AppLovinAdClickListener
    public void adClicked(AppLovinAd appLovinAd) {
        AppLovinAdClickListener appLovinAdClickListenerE = this.a.e();
        if (appLovinAdClickListenerE != null) {
            appLovinAdClickListenerE.adClicked(appLovinAd);
        }
    }
}
