package com.applovin.impl.sdk;

import android.app.Activity;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinErrorCodes;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class ab implements AppLovinAdClickListener, AppLovinAdDisplayListener, AppLovinAdRewardListener, AppLovinAdVideoPlaybackListener {
    final /* synthetic */ w a;
    private final Activity b;
    private final AppLovinAdDisplayListener c;
    private final AppLovinAdClickListener d;
    private final AppLovinAdVideoPlaybackListener e;
    private final AppLovinAdRewardListener f;

    private ab(w wVar, Activity activity, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        this.a = wVar;
        this.c = appLovinAdDisplayListener;
        this.d = appLovinAdClickListener;
        this.e = appLovinAdVideoPlaybackListener;
        this.f = appLovinAdRewardListener;
        this.b = activity;
    }

    @Override // com.applovin.sdk.AppLovinAdClickListener
    public void adClicked(AppLovinAd appLovinAd) {
        if (this.d != null) {
            this.a.e.post(new ae(this, appLovinAd));
        }
    }

    @Override // com.applovin.sdk.AppLovinAdDisplayListener
    public void adDisplayed(AppLovinAd appLovinAd) {
        if (this.c != null) {
            this.c.adDisplayed(appLovinAd);
        }
    }

    @Override // com.applovin.sdk.AppLovinAdDisplayListener
    public void adHidden(AppLovinAd appLovinAd) {
        String str;
        int i;
        String strC = this.a.c();
        if (strC == null || !this.a.i) {
            this.a.h.a(true);
            if (this.a.i) {
                str = "network_timeout";
                i = AppLovinErrorCodes.INCENTIVIZED_SERVER_TIMEOUT;
            } else {
                str = "user_closed_video";
                i = AppLovinErrorCodes.INCENTIVIZED_USER_CLOSED_VIDEO;
            }
            av.a().a(appLovinAd, str);
            if (this.a.i) {
                this.a.a(strC, this.b);
            }
            this.a.e.post(new ac(this, appLovinAd, i));
        } else {
            this.a.a(strC, this.b);
        }
        if (this.c != null) {
            this.a.e.post(new ad(this, appLovinAd));
        }
        this.a.a.a().a(new bv(this.a.a, appLovinAd), bo.BACKGROUND);
        this.a.c = null;
    }

    @Override // com.applovin.sdk.AppLovinAdRewardListener
    public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
    }

    @Override // com.applovin.sdk.AppLovinAdRewardListener
    public void userOverQuota(AppLovinAd appLovinAd, Map map) {
        this.a.b("quota_exceeded");
        if (this.f != null) {
            this.a.e.post(new ai(this, appLovinAd, map));
        }
    }

    @Override // com.applovin.sdk.AppLovinAdRewardListener
    public void userRewardRejected(AppLovinAd appLovinAd, Map map) {
        this.a.b("rejected");
        if (this.f != null) {
            this.a.e.post(new aj(this, appLovinAd, map));
        }
    }

    @Override // com.applovin.sdk.AppLovinAdRewardListener
    public void userRewardVerified(AppLovinAd appLovinAd, Map map) {
        this.a.b("accepted");
        if (this.f != null) {
            this.a.e.post(new ah(this, appLovinAd, map));
        }
    }

    @Override // com.applovin.sdk.AppLovinAdRewardListener
    public void validationRequestFailed(AppLovinAd appLovinAd, int i) {
        this.a.b("network_timeout");
        if (this.f != null) {
            this.a.e.post(new ak(this, appLovinAd, i));
        }
    }

    @Override // com.applovin.sdk.AppLovinAdVideoPlaybackListener
    public void videoPlaybackBegan(AppLovinAd appLovinAd) {
        if (this.e != null) {
            this.a.e.post(new af(this, appLovinAd));
        }
    }

    @Override // com.applovin.sdk.AppLovinAdVideoPlaybackListener
    public void videoPlaybackEnded(AppLovinAd appLovinAd, double d, boolean z) {
        if (this.e != null) {
            this.a.e.post(new ag(this, appLovinAd, d, z));
        }
        this.a.i = z;
    }
}
