package com.facebook.ads;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.a.d;
import com.facebook.ads.a.i;
import com.facebook.ads.a.q;

/* JADX INFO: loaded from: classes.dex */
public class AdView extends RelativeLayout implements Ad {
    private static final String TAG = AdView.class.getSimpleName();
    private d adContentView;

    public AdView(Context context, String str, AdSize adSize) {
        super(context);
        if (adSize == null || adSize == AdSize.INTERSTITIAL) {
            throw new IllegalArgumentException("adSize");
        }
        switch (adSize) {
            case RECTANGLE_HEIGHT_250:
                this.adContentView = new i(this, str, adSize);
                break;
            default:
                this.adContentView = new q(this, str, adSize);
                break;
        }
        addView((View) this.adContentView);
    }

    @Override // com.facebook.ads.Ad
    public void destroy() {
        if (this.adContentView != null) {
            removeView((View) this.adContentView);
            this.adContentView.c();
            this.adContentView = null;
        }
    }

    public void disableAutoRefresh() {
        this.adContentView.a();
    }

    @Override // com.facebook.ads.Ad
    public void loadAd() {
        this.adContentView.b();
    }

    public void setAdListener(AdListener adListener) {
        this.adContentView.setAdListener(adListener);
    }

    public void setImpressionListener(ImpressionListener impressionListener) {
        this.adContentView.setImpressionListener(impressionListener);
    }
}
