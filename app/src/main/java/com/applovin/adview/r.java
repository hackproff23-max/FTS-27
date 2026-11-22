package com.applovin.adview;

import android.app.Activity;
import com.applovin.impl.adview.InterstitialAdDialogCreatorImpl;
import com.applovin.sdk.AppLovinSdk;

/* JADX INFO: loaded from: classes.dex */
final class r implements Runnable {
    final /* synthetic */ AppLovinSdk a;
    final /* synthetic */ Activity b;

    r(AppLovinSdk appLovinSdk, Activity activity) {
        this.a = appLovinSdk;
        this.b = activity;
    }

    @Override // java.lang.Runnable
    public void run() {
        new InterstitialAdDialogCreatorImpl().createInterstitialAdDialog(this.a, this.b).show();
    }
}
