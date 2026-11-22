package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.AdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class df<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> implements MediationBannerListener, MediationInterstitialListener {
    private final da qW;

    public df(da daVar) {
        this.qW = daVar;
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public void onClick(MediationBannerAdapter<?, ?> adapter) {
        gr.S("Adapter called onClick.");
        if (!gq.dB()) {
            gr.W("onClick must be called on the main UI thread.");
            gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.df.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        df.this.qW.onAdClicked();
                    } catch (RemoteException e) {
                        gr.d("Could not call onAdClicked.", e);
                    }
                }
            });
        } else {
            try {
                this.qW.onAdClicked();
            } catch (RemoteException e) {
                gr.d("Could not call onAdClicked.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public void onDismissScreen(MediationBannerAdapter<?, ?> adapter) {
        gr.S("Adapter called onDismissScreen.");
        if (!gq.dB()) {
            gr.W("onDismissScreen must be called on the main UI thread.");
            gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.df.4
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        df.this.qW.onAdClosed();
                    } catch (RemoteException e) {
                        gr.d("Could not call onAdClosed.", e);
                    }
                }
            });
        } else {
            try {
                this.qW.onAdClosed();
            } catch (RemoteException e) {
                gr.d("Could not call onAdClosed.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public void onDismissScreen(MediationInterstitialAdapter<?, ?> adapter) {
        gr.S("Adapter called onDismissScreen.");
        if (!gq.dB()) {
            gr.W("onDismissScreen must be called on the main UI thread.");
            gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.df.9
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        df.this.qW.onAdClosed();
                    } catch (RemoteException e) {
                        gr.d("Could not call onAdClosed.", e);
                    }
                }
            });
        } else {
            try {
                this.qW.onAdClosed();
            } catch (RemoteException e) {
                gr.d("Could not call onAdClosed.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public void onFailedToReceiveAd(MediationBannerAdapter<?, ?> adapter, final AdRequest.ErrorCode errorCode) {
        gr.S("Adapter called onFailedToReceiveAd with error. " + errorCode);
        if (!gq.dB()) {
            gr.W("onFailedToReceiveAd must be called on the main UI thread.");
            gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.df.5
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        df.this.qW.onAdFailedToLoad(dg.a(errorCode));
                    } catch (RemoteException e) {
                        gr.d("Could not call onAdFailedToLoad.", e);
                    }
                }
            });
        } else {
            try {
                this.qW.onAdFailedToLoad(dg.a(errorCode));
            } catch (RemoteException e) {
                gr.d("Could not call onAdFailedToLoad.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public void onFailedToReceiveAd(MediationInterstitialAdapter<?, ?> adapter, final AdRequest.ErrorCode errorCode) {
        gr.S("Adapter called onFailedToReceiveAd with error " + errorCode + ".");
        if (!gq.dB()) {
            gr.W("onFailedToReceiveAd must be called on the main UI thread.");
            gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.df.10
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        df.this.qW.onAdFailedToLoad(dg.a(errorCode));
                    } catch (RemoteException e) {
                        gr.d("Could not call onAdFailedToLoad.", e);
                    }
                }
            });
        } else {
            try {
                this.qW.onAdFailedToLoad(dg.a(errorCode));
            } catch (RemoteException e) {
                gr.d("Could not call onAdFailedToLoad.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public void onLeaveApplication(MediationBannerAdapter<?, ?> adapter) {
        gr.S("Adapter called onLeaveApplication.");
        if (!gq.dB()) {
            gr.W("onLeaveApplication must be called on the main UI thread.");
            gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.df.6
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        df.this.qW.onAdLeftApplication();
                    } catch (RemoteException e) {
                        gr.d("Could not call onAdLeftApplication.", e);
                    }
                }
            });
        } else {
            try {
                this.qW.onAdLeftApplication();
            } catch (RemoteException e) {
                gr.d("Could not call onAdLeftApplication.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public void onLeaveApplication(MediationInterstitialAdapter<?, ?> adapter) {
        gr.S("Adapter called onLeaveApplication.");
        if (!gq.dB()) {
            gr.W("onLeaveApplication must be called on the main UI thread.");
            gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.df.11
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        df.this.qW.onAdLeftApplication();
                    } catch (RemoteException e) {
                        gr.d("Could not call onAdLeftApplication.", e);
                    }
                }
            });
        } else {
            try {
                this.qW.onAdLeftApplication();
            } catch (RemoteException e) {
                gr.d("Could not call onAdLeftApplication.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public void onPresentScreen(MediationBannerAdapter<?, ?> adapter) {
        gr.S("Adapter called onPresentScreen.");
        if (!gq.dB()) {
            gr.W("onPresentScreen must be called on the main UI thread.");
            gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.df.7
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        df.this.qW.onAdOpened();
                    } catch (RemoteException e) {
                        gr.d("Could not call onAdOpened.", e);
                    }
                }
            });
        } else {
            try {
                this.qW.onAdOpened();
            } catch (RemoteException e) {
                gr.d("Could not call onAdOpened.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public void onPresentScreen(MediationInterstitialAdapter<?, ?> adapter) {
        gr.S("Adapter called onPresentScreen.");
        if (!gq.dB()) {
            gr.W("onPresentScreen must be called on the main UI thread.");
            gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.df.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        df.this.qW.onAdOpened();
                    } catch (RemoteException e) {
                        gr.d("Could not call onAdOpened.", e);
                    }
                }
            });
        } else {
            try {
                this.qW.onAdOpened();
            } catch (RemoteException e) {
                gr.d("Could not call onAdOpened.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public void onReceivedAd(MediationBannerAdapter<?, ?> adapter) {
        gr.S("Adapter called onReceivedAd.");
        if (!gq.dB()) {
            gr.W("onReceivedAd must be called on the main UI thread.");
            gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.df.8
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        df.this.qW.onAdLoaded();
                    } catch (RemoteException e) {
                        gr.d("Could not call onAdLoaded.", e);
                    }
                }
            });
        } else {
            try {
                this.qW.onAdLoaded();
            } catch (RemoteException e) {
                gr.d("Could not call onAdLoaded.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public void onReceivedAd(MediationInterstitialAdapter<?, ?> adapter) {
        gr.S("Adapter called onReceivedAd.");
        if (!gq.dB()) {
            gr.W("onReceivedAd must be called on the main UI thread.");
            gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.df.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        df.this.qW.onAdLoaded();
                    } catch (RemoteException e) {
                        gr.d("Could not call onAdLoaded.", e);
                    }
                }
            });
        } else {
            try {
                this.qW.onAdLoaded();
            } catch (RemoteException e) {
                gr.d("Could not call onAdLoaded.", e);
            }
        }
    }
}
