package com.applovin.impl.adview;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.sdk.bf;
import com.applovin.impl.sdk.cd;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;

/* JADX INFO: loaded from: classes.dex */
class v extends Dialog implements u {
    private final Activity a;
    private final AppLovinSdk b;
    private final AppLovinLogger c;
    private RelativeLayout d;
    private AppLovinAdView e;
    private Runnable f;
    private ad g;
    private volatile boolean h;
    private volatile boolean i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    v(AppLovinSdk appLovinSdk, Activity activity) {
        super(activity, 16973840);
        w wVar = null;
        this.g = null;
        this.h = false;
        this.i = false;
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        this.b = appLovinSdk;
        this.c = appLovinSdk.getLogger();
        this.a = activity;
        this.f = new ac(this, wVar);
        this.e = new AppLovinAdView(appLovinSdk, AppLovinAdSize.INTERSTITIAL, activity);
        this.e.setAutoDestroy(false);
        requestWindowFeature(1);
        try {
            getWindow().setFlags(activity.getWindow().getAttributes().flags, activity.getWindow().getAttributes().flags);
        } catch (Exception e) {
            this.c.e("InterstitialAdDialog", "Set window flags failed.", e);
        }
    }

    private int a(int i) {
        return cd.a(this.a, i);
    }

    private void a() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        this.e.setLayoutParams(layoutParams);
        this.d = new RelativeLayout(this.a);
        this.d.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.d.setBackgroundColor(-1157627904);
        this.d.addView(this.e);
        setContentView(this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(t tVar) {
        s sVarA = s.a(this.b, getContext(), tVar);
        sVarA.setOnClickListener(new aa(this));
        bf bfVar = new bf(this.b);
        int iA = a(bfVar.l());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(iA, iA);
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        sVarA.a(iA);
        int iA2 = a(bfVar.n());
        int iA3 = a(bfVar.p());
        layoutParams.setMargins(0, iA2, iA3, 0);
        this.e.addView(sVarA, layoutParams);
        sVarA.bringToFront();
        int iA4 = a(new bf(this.b).r());
        View view = new View(this.a);
        view.setBackgroundColor(0);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(iA + iA4, iA + iA4);
        layoutParams2.addRule(10);
        layoutParams2.addRule(11);
        layoutParams2.setMargins(0, iA2 - a(5), iA3 - a(5), 0);
        view.setOnClickListener(new ab(this, sVarA));
        this.e.addView(view, layoutParams2);
        view.bringToFront();
    }

    public void a(ad adVar) {
        this.e.setAdDisplayListener(new w(this, adVar));
        this.e.setAdClickListener(new x(this, adVar));
        this.e.setAdVideoPlaybackListener(new y(this, adVar));
        this.g = adVar;
        adVar.a(true);
    }

    public void a(AppLovinAd appLovinAd) {
        this.a.runOnUiThread(new z(this, appLovinAd));
    }

    @Override // android.app.Dialog, android.content.DialogInterface, com.applovin.impl.adview.u
    public void dismiss() {
        if (this.g != null) {
            this.g.g();
        }
        if (this.e != null) {
            this.e.destroy();
        }
        this.g = null;
        this.e = null;
        super.dismiss();
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a();
    }
}
