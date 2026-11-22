package com.flurry.sdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import com.flurry.android.AdCreative;
import com.flurry.android.AdNetworkView;
import com.millennialmedia.android.MMAd;
import com.millennialmedia.android.MMAdView;
import com.millennialmedia.android.MMException;
import com.millennialmedia.android.MMSDK;
import com.millennialmedia.android.RequestListener;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public final class ca extends AdNetworkView {
    private static final String a = ca.class.getSimpleName();
    private final String b;
    private final String c;
    private MMAdView d;
    private RequestListener e;

    public ca(Context context, r rVar, AdCreative adCreative, Bundle bundle) {
        super(context, rVar, adCreative);
        this.b = bundle.getString("com.flurry.millennial.MYAPID");
        this.c = bundle.getString("com.flurry.millennial.MYAPIDRECTANGLE");
        setFocusable(true);
    }

    MMAdView getAdView() {
        return this.d;
    }

    RequestListener getAdListener() {
        return this.e;
    }

    @Override // com.flurry.sdk.ec
    public void initLayout() {
        gd.a(3, a, "Millennial initLayout");
        int width = getAdCreative().getWidth();
        int height = getAdCreative().getHeight();
        int iA = cb.a(new Point(width, height));
        if (-1 == iA) {
            gd.a(3, a, "Could not find Millennial AdSize that matches size " + width + "x" + height);
            gd.a(3, a, "Could not load Millennial Ad");
            return;
        }
        Point pointA = cb.a(iA);
        if (pointA == null) {
            gd.a(3, a, "Could not find Millennial AdSize that matches size " + width + "x" + height);
            gd.a(3, a, "Could not load Millennial Ad");
            return;
        }
        int i = pointA.x;
        int i2 = pointA.y;
        gd.a(3, a, "Determined Millennial AdSize as " + i + "x" + i2);
        this.d = new MMAdView((Activity) getContext());
        setId(MMSDK.getDefaultAdId());
        this.d.setApid(this.b);
        if (2 == iA) {
            this.d.setApid(this.c);
        }
        this.d.setWidth(i);
        this.d.setHeight(i2);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        setGravity(17);
        this.e = new a();
        this.d.setListener(this.e);
        addView(this.d);
        this.d.getAd();
    }

    final class a implements RequestListener {
        private a() {
        }

        @Override // com.millennialmedia.android.RequestListener
        public void requestFailed(MMAd mMAd, MMException mMException) {
            ca.this.onRenderFailed(Collections.emptyMap());
            gd.a(3, ca.a, "Millennial MMAdView failed to load ad with error: " + mMException);
        }

        @Override // com.millennialmedia.android.RequestListener
        public void requestCompleted(MMAd mMAd) {
            ca.this.onAdShown(Collections.emptyMap());
            gd.a(3, ca.a, "Millennial MMAdView returned ad." + System.currentTimeMillis());
        }

        @Override // com.millennialmedia.android.RequestListener
        public void MMAdOverlayLaunched(MMAd mMAd) {
            gd.a(3, ca.a, "Millennial MMAdView banner overlay launched.");
        }

        @Override // com.millennialmedia.android.RequestListener
        public void MMAdRequestIsCaching(MMAd mMAd) {
            gd.a(3, ca.a, "Millennial MMAdView banner request is caching.");
        }

        @Override // com.millennialmedia.android.RequestListener
        public void MMAdOverlayClosed(MMAd mMAd) {
            gd.a(3, ca.a, "Millennial MMAdView banner overlay closed.");
        }

        @Override // com.millennialmedia.android.RequestListener
        public void onSingleTap(MMAd mMAd) {
            ca.this.onAdClicked(Collections.emptyMap());
            gd.a(3, ca.a, "Millennial MMAdView banner tapped.");
        }
    }

    @Override // com.flurry.sdk.ec
    public void onActivityDestroy() {
        gd.a(3, a, "Millennial onDestroy");
        if (this.d != null) {
            this.d = null;
        }
        super.onActivityDestroy();
    }
}
