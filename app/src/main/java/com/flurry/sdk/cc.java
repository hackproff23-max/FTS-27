package com.flurry.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.millennialmedia.android.MMAd;
import com.millennialmedia.android.MMException;
import com.millennialmedia.android.MMInterstitial;
import com.millennialmedia.android.RequestListener;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public final class cc extends bd {
    private static final String b = cc.class.getSimpleName();
    private boolean c;
    private final String d;
    private MMInterstitial e;
    private RequestListener f;

    public cc(Context context, r rVar, Bundle bundle) {
        super(context, rVar);
        this.d = bundle.getString("com.flurry.millennial.MYAPIDINTERSTITIAL");
    }

    @Override // com.flurry.sdk.en
    public void a() {
        this.e = new MMInterstitial((Activity) c());
        this.e.setApid(this.d);
        this.f = new a();
        this.e.setListener(this.f);
        this.e.fetch();
        this.c = this.e.display();
        if (this.c) {
            gd.a(3, b, "Millennial MMAdView Interstitial ad displayed immediately:" + System.currentTimeMillis() + " " + this.c);
        } else {
            gd.a(3, b, "Millennial MMAdView Interstitial ad did not display immediately:" + System.currentTimeMillis() + " " + this.c);
        }
    }

    final class a implements RequestListener {
        private a() {
        }

        @Override // com.millennialmedia.android.RequestListener
        public void requestFailed(MMAd mMAd, MMException mMException) {
            cc.this.d(Collections.emptyMap());
            gd.a(3, cc.b, "Millennial MMAdView Interstitial failed to load ad.");
        }

        @Override // com.millennialmedia.android.RequestListener
        public void requestCompleted(MMAd mMAd) {
            gd.a(3, cc.b, "Millennial MMAdView returned interstitial ad: " + System.currentTimeMillis());
            if (!cc.this.c) {
                cc.this.e.display();
            }
        }

        @Override // com.millennialmedia.android.RequestListener
        public void MMAdOverlayLaunched(MMAd mMAd) {
            cc.this.a(Collections.emptyMap());
            gd.a(3, cc.b, "Millennial MMAdView Interstitial overlay launched." + System.currentTimeMillis());
        }

        @Override // com.millennialmedia.android.RequestListener
        public void MMAdRequestIsCaching(MMAd mMAd) {
            gd.a(3, cc.b, "Millennial MMAdView Interstitial request is caching.");
        }

        @Override // com.millennialmedia.android.RequestListener
        public void MMAdOverlayClosed(MMAd mMAd) {
            cc.this.c(Collections.emptyMap());
            gd.a(3, cc.b, "Millennial MMAdView Interstitial overlay closed.");
        }

        @Override // com.millennialmedia.android.RequestListener
        public void onSingleTap(MMAd mMAd) {
            cc.this.b(Collections.emptyMap());
            gd.a(3, cc.b, "Millennial MMAdView Interstitial tapped.");
        }
    }
}
