package com.applovin.adview;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class l implements View.OnClickListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    l(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.dismiss();
    }
}
