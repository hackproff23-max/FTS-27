package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;

/* JADX INFO: loaded from: classes.dex */
class y implements AppLovinAdVideoPlaybackListener {
    final /* synthetic */ ad a;
    final /* synthetic */ v b;

    y(v vVar, ad adVar) {
        this.b = vVar;
        this.a = adVar;
    }

    @Override // com.applovin.sdk.AppLovinAdVideoPlaybackListener
    public void videoPlaybackBegan(AppLovinAd appLovinAd) {
        AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListenerC = this.a.c();
        if (appLovinAdVideoPlaybackListenerC != null) {
            appLovinAdVideoPlaybackListenerC.videoPlaybackBegan(appLovinAd);
        }
    }

    @Override // com.applovin.sdk.AppLovinAdVideoPlaybackListener
    public void videoPlaybackEnded(AppLovinAd appLovinAd, double d, boolean z) {
        AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListenerC = this.a.c();
        if (appLovinAdVideoPlaybackListenerC != null) {
            appLovinAdVideoPlaybackListenerC.videoPlaybackEnded(appLovinAd, d, z);
        }
    }
}
