package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class dd implements MediationBannerListener, MediationInterstitialListener {
    private final da qW;

    public dd(da daVar) {
        this.qW = daVar;
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerListener
    public void onAdClicked(MediationBannerAdapter adapter) {
        jx.aU("onAdClicked must be called on the main UI thread.");
        gr.S("Adapter called onAdClicked.");
        try {
            this.qW.onAdClicked();
        } catch (RemoteException e) {
            gr.d("Could not call onAdClicked.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public void onAdClicked(MediationInterstitialAdapter adapter) {
        jx.aU("onAdClicked must be called on the main UI thread.");
        gr.S("Adapter called onAdClicked.");
        try {
            this.qW.onAdClicked();
        } catch (RemoteException e) {
            gr.d("Could not call onAdClicked.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerListener
    public void onAdClosed(MediationBannerAdapter adapter) {
        jx.aU("onAdClosed must be called on the main UI thread.");
        gr.S("Adapter called onAdClosed.");
        try {
            this.qW.onAdClosed();
        } catch (RemoteException e) {
            gr.d("Could not call onAdClosed.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public void onAdClosed(MediationInterstitialAdapter adapter) {
        jx.aU("onAdClosed must be called on the main UI thread.");
        gr.S("Adapter called onAdClosed.");
        try {
            this.qW.onAdClosed();
        } catch (RemoteException e) {
            gr.d("Could not call onAdClosed.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerListener
    public void onAdFailedToLoad(MediationBannerAdapter adapter, int errorCode) {
        jx.aU("onAdFailedToLoad must be called on the main UI thread.");
        gr.S("Adapter called onAdFailedToLoad with error. " + errorCode);
        try {
            this.qW.onAdFailedToLoad(errorCode);
        } catch (RemoteException e) {
            gr.d("Could not call onAdFailedToLoad.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public void onAdFailedToLoad(MediationInterstitialAdapter adapter, int errorCode) {
        jx.aU("onAdFailedToLoad must be called on the main UI thread.");
        gr.S("Adapter called onAdFailedToLoad with error " + errorCode + ".");
        try {
            this.qW.onAdFailedToLoad(errorCode);
        } catch (RemoteException e) {
            gr.d("Could not call onAdFailedToLoad.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerListener
    public void onAdLeftApplication(MediationBannerAdapter adapter) {
        jx.aU("onAdLeftApplication must be called on the main UI thread.");
        gr.S("Adapter called onAdLeftApplication.");
        try {
            this.qW.onAdLeftApplication();
        } catch (RemoteException e) {
            gr.d("Could not call onAdLeftApplication.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public void onAdLeftApplication(MediationInterstitialAdapter adapter) {
        jx.aU("onAdLeftApplication must be called on the main UI thread.");
        gr.S("Adapter called onAdLeftApplication.");
        try {
            this.qW.onAdLeftApplication();
        } catch (RemoteException e) {
            gr.d("Could not call onAdLeftApplication.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerListener
    public void onAdLoaded(MediationBannerAdapter adapter) {
        jx.aU("onAdLoaded must be called on the main UI thread.");
        gr.S("Adapter called onAdLoaded.");
        try {
            this.qW.onAdLoaded();
        } catch (RemoteException e) {
            gr.d("Could not call onAdLoaded.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public void onAdLoaded(MediationInterstitialAdapter adapter) {
        jx.aU("onAdLoaded must be called on the main UI thread.");
        gr.S("Adapter called onAdLoaded.");
        try {
            this.qW.onAdLoaded();
        } catch (RemoteException e) {
            gr.d("Could not call onAdLoaded.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerListener
    public void onAdOpened(MediationBannerAdapter adapter) {
        jx.aU("onAdOpened must be called on the main UI thread.");
        gr.S("Adapter called onAdOpened.");
        try {
            this.qW.onAdOpened();
        } catch (RemoteException e) {
            gr.d("Could not call onAdOpened.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public void onAdOpened(MediationInterstitialAdapter adapter) {
        jx.aU("onAdOpened must be called on the main UI thread.");
        gr.S("Adapter called onAdOpened.");
        try {
            this.qW.onAdOpened();
        } catch (RemoteException e) {
            gr.d("Could not call onAdOpened.", e);
        }
    }
}
