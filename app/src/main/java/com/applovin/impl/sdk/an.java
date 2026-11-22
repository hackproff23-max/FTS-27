package com.applovin.impl.sdk;

import android.app.Activity;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import java.util.Timer;

/* JADX INFO: loaded from: classes.dex */
class an {
    private final AppLovinSdkImpl a;
    private final w b;
    private Activity c;
    private AppLovinAdDisplayListener d;
    private AppLovinAdVideoPlaybackListener e;
    private AppLovinAdClickListener f;
    private AppLovinAdRewardListener g;
    private final Timer h = new Timer("IncentivizedAdLauncher");

    an(AppLovinSdkImpl appLovinSdkImpl, w wVar) {
        this.a = appLovinSdkImpl;
        this.b = wVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.b.b(this.c, this.g, this.e, this.d, this.f);
    }

    void a() {
        this.c.runOnUiThread(new ao(this));
    }

    public void a(Activity activity) {
        this.c = activity;
    }

    public void a(AppLovinAdClickListener appLovinAdClickListener) {
        this.f = appLovinAdClickListener;
    }

    public void a(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.d = appLovinAdDisplayListener;
    }

    public void a(AppLovinAdRewardListener appLovinAdRewardListener) {
        this.g = appLovinAdRewardListener;
    }

    public void a(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
        this.e = appLovinAdVideoPlaybackListener;
    }
}
