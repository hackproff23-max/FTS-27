package com.applovin.adview;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class n implements View.OnClickListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    n(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.q();
    }
}
