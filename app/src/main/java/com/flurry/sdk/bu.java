package com.flurry.sdk;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public final class bu extends bd {
    private static final String b = bu.class.getSimpleName();
    private final String c;
    private final String d;
    private final boolean e;
    private final InterstitialAd f;
    private final AdListener g;

    public bu(Context context, r rVar, Bundle bundle) {
        super(context, rVar);
        this.c = bundle.getString("com.flurry.gms.ads.MY_AD_UNIT_ID");
        this.d = bundle.getString("com.flurry.gms.ads.MYTEST_AD_DEVICE_ID");
        this.e = bundle.getBoolean("com.flurry.gms.ads.test");
        this.g = new a();
        this.f = new InterstitialAd(c());
        this.f.setAdUnitId(this.c);
        this.f.setAdListener(this.g);
    }

    @Override // com.flurry.sdk.en
    public void a() {
        AdRequest.Builder builder = new AdRequest.Builder();
        if (this.e) {
            gd.a(3, b, "GMS AdView set to Test Mode.");
            builder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
            if (!TextUtils.isEmpty(this.d)) {
                builder.addTestDevice(this.d);
            }
        }
        this.f.loadAd(builder.build());
    }

    final class a extends AdListener {
        private a() {
        }

        @Override // com.google.android.gms.ads.AdListener
        public void onAdLoaded() {
            bu.this.a(Collections.emptyMap());
            gd.a(4, bu.b, "GMS AdView onAdLoaded.");
            bu.this.f.show();
        }

        @Override // com.google.android.gms.ads.AdListener
        public void onAdFailedToLoad(int i) {
            bu.this.d(Collections.emptyMap());
            gd.a(5, bu.b, "GMS AdView onAdFailedToLoad: " + i + ".");
        }

        @Override // com.google.android.gms.ads.AdListener
        public void onAdOpened() {
            gd.a(4, bu.b, "GMS AdView onAdOpened.");
        }

        @Override // com.google.android.gms.ads.AdListener
        public void onAdClosed() {
            bu.this.c(Collections.emptyMap());
            gd.a(4, bu.b, "GMS AdView onAdClosed.");
        }

        @Override // com.google.android.gms.ads.AdListener
        public void onAdLeftApplication() {
            bu.this.b(Collections.emptyMap());
            gd.a(4, bu.b, "GMS AdView onAdLeftApplication.");
        }
    }
}
