package com.flurry.sdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.flurry.android.AdCreative;
import com.flurry.android.AdNetworkView;
import com.inmobi.commons.InMobi;
import com.inmobi.monetization.IMBanner;
import com.inmobi.monetization.IMBannerListener;
import com.inmobi.monetization.IMErrorCode;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class bw extends AdNetworkView {
    private static final String a = bw.class.getSimpleName();
    private final String b;
    private IMBanner c;
    private IMBannerListener d;

    bw(Context context, r rVar, AdCreative adCreative, Bundle bundle) {
        super(context, rVar, adCreative);
        this.b = bundle.getString("com.flurry.inmobi.MY_APP_ID");
        InMobi.initialize((Activity) getContext(), this.b);
        setFocusable(true);
    }

    IMBanner getAdView() {
        return this.c;
    }

    IMBannerListener getAdListener() {
        return this.d;
    }

    @Override // com.flurry.sdk.ec
    public void initLayout() {
        gd.a(3, a, "InMobi initLayout");
        int width = getAdCreative().getWidth();
        int height = getAdCreative().getHeight();
        int i = hn.i();
        int iH = hn.h();
        if (width > 0 && width <= iH) {
            iH = width;
        }
        if (height > 0 && height <= i) {
            i = height;
        }
        int iA = bx.a(new Point(iH, i));
        if (-1 != iA) {
            this.c = new IMBanner((Activity) getContext(), this.b, iA);
            int i2 = 320;
            int i3 = 50;
            Point pointA = bx.a(iA);
            if (pointA != null) {
                i2 = pointA.x;
                i3 = pointA.y;
            }
            gd.a(3, a, "Determined InMobi AdSize as " + i2 + "x" + i3);
            float f = hn.d().density;
            this.c.setLayoutParams(new LinearLayout.LayoutParams((int) ((i2 * f) + 0.5f), (int) ((i3 * f) + 0.5f)));
            this.d = new a();
            this.c.setIMBannerListener(this.d);
            setGravity(17);
            addView(this.c);
            this.c.setRefreshInterval(-1);
            this.c.loadBanner();
            return;
        }
        gd.a(3, a, "Could not find InMobi AdSize that matches size " + width + "x" + height);
        gd.a(3, a, "Could not load InMobi Ad");
    }

    final class a implements IMBannerListener {
        private a() {
        }

        public void onShowBannerScreen(IMBanner iMBanner) {
            bw.this.onAdClicked(Collections.emptyMap());
            gd.a(3, bw.a, "InMobi imAdView ad shown.");
        }

        public void onDismissBannerScreen(IMBanner iMBanner) {
            bw.this.onAdClosed(Collections.emptyMap());
            gd.a(3, bw.a, "InMobi imAdView dismiss ad.");
        }

        public void onBannerRequestFailed(IMBanner iMBanner, IMErrorCode iMErrorCode) {
            bw.this.onRenderFailed(Collections.emptyMap());
            gd.a(3, bw.a, "InMobi imAdView ad request failed. " + iMErrorCode.toString());
        }

        public void onBannerRequestSucceeded(IMBanner iMBanner) {
            bw.this.onAdShown(Collections.emptyMap());
            gd.a(3, bw.a, "InMobi imAdView ad request completed.");
        }

        public void onLeaveApplication(IMBanner iMBanner) {
            gd.a(3, bw.a, "InMobi onLeaveApplication");
        }

        public void onBannerInteraction(IMBanner iMBanner, Map<String, String> map) {
            bw.this.onAdClicked(Collections.emptyMap());
            gd.a(3, bw.a, "InMobi onBannerInteraction");
        }
    }

    @Override // com.flurry.sdk.ec
    public void onActivityDestroy() {
        gd.a(3, a, "InMobi onDestroy");
        if (this.c != null) {
            this.c.destroy();
            this.c = null;
        }
        super.onActivityDestroy();
    }
}
