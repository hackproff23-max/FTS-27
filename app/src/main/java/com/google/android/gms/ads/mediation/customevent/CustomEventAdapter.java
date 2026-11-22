package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.internal.gr;

/* JADX INFO: loaded from: classes.dex */
public final class CustomEventAdapter implements MediationBannerAdapter, MediationInterstitialAdapter {
    private View n;
    private CustomEventBanner xu;
    private CustomEventInterstitial xv;

    private static final class a implements CustomEventBannerListener {
        private final MediationBannerListener l;
        private final CustomEventAdapter xw;

        public a(CustomEventAdapter customEventAdapter, MediationBannerListener mediationBannerListener) {
            this.xw = customEventAdapter;
            this.l = mediationBannerListener;
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public void onAdClicked() {
            gr.S("Custom event adapter called onAdClicked.");
            this.l.onAdClicked(this.xw);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public void onAdClosed() {
            gr.S("Custom event adapter called onAdClosed.");
            this.l.onAdClosed(this.xw);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public void onAdFailedToLoad(int errorCode) {
            gr.S("Custom event adapter called onAdFailedToLoad.");
            this.l.onAdFailedToLoad(this.xw, errorCode);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public void onAdLeftApplication() {
            gr.S("Custom event adapter called onAdLeftApplication.");
            this.l.onAdLeftApplication(this.xw);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener
        public void onAdLoaded(View view) {
            gr.S("Custom event adapter called onAdLoaded.");
            this.xw.a(view);
            this.l.onAdLoaded(this.xw);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public void onAdOpened() {
            gr.S("Custom event adapter called onAdOpened.");
            this.l.onAdOpened(this.xw);
        }
    }

    private class b implements CustomEventInterstitialListener {
        private final MediationInterstitialListener m;
        private final CustomEventAdapter xw;

        public b(CustomEventAdapter customEventAdapter, MediationInterstitialListener mediationInterstitialListener) {
            this.xw = customEventAdapter;
            this.m = mediationInterstitialListener;
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public void onAdClicked() {
            gr.S("Custom event adapter called onAdClicked.");
            this.m.onAdClicked(this.xw);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public void onAdClosed() {
            gr.S("Custom event adapter called onAdClosed.");
            this.m.onAdClosed(this.xw);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public void onAdFailedToLoad(int errorCode) {
            gr.S("Custom event adapter called onFailedToReceiveAd.");
            this.m.onAdFailedToLoad(this.xw, errorCode);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public void onAdLeftApplication() {
            gr.S("Custom event adapter called onAdLeftApplication.");
            this.m.onAdLeftApplication(this.xw);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener
        public void onAdLoaded() {
            gr.S("Custom event adapter called onReceivedAd.");
            this.m.onAdLoaded(CustomEventAdapter.this);
        }

        @Override // com.google.android.gms.ads.mediation.customevent.CustomEventListener
        public void onAdOpened() {
            gr.S("Custom event adapter called onAdOpened.");
            this.m.onAdOpened(this.xw);
        }
    }

    private static <T> T a(String str) {
        try {
            return (T) Class.forName(str).newInstance();
        } catch (Throwable th) {
            gr.W("Could not instantiate custom event adapter: " + str + ". " + th.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(View view) {
        this.n = view;
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public View getBannerView() {
        return this.n;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onDestroy() {
        if (this.xu != null) {
            this.xu.onDestroy();
        }
        if (this.xv != null) {
            this.xv.onDestroy();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onPause() {
        if (this.xu != null) {
            this.xu.onPause();
        }
        if (this.xv != null) {
            this.xv.onPause();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onResume() {
        if (this.xu != null) {
            this.xu.onResume();
        }
        if (this.xv != null) {
            this.xv.onResume();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public void requestBannerAd(Context context, MediationBannerListener listener, Bundle serverParameters, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle customEventExtras) {
        this.xu = (CustomEventBanner) a(serverParameters.getString("class_name"));
        if (this.xu == null) {
            listener.onAdFailedToLoad(this, 0);
        } else {
            this.xu.requestBannerAd(context, new a(this, listener), serverParameters.getString("parameter"), adSize, mediationAdRequest, customEventExtras == null ? null : customEventExtras.getBundle(serverParameters.getString("class_name")));
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void requestInterstitialAd(Context context, MediationInterstitialListener listener, Bundle serverParameters, MediationAdRequest mediationAdRequest, Bundle customEventExtras) {
        this.xv = (CustomEventInterstitial) a(serverParameters.getString("class_name"));
        if (this.xv == null) {
            listener.onAdFailedToLoad(this, 0);
        } else {
            this.xv.requestInterstitialAd(context, new b(this, listener), serverParameters.getString("parameter"), mediationAdRequest, customEventExtras == null ? null : customEventExtras.getBundle(serverParameters.getString("class_name")));
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void showInterstitial() {
        this.xv.showInterstitial();
    }
}
