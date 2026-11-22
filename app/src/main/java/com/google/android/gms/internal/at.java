package com.google.android.gms.internal;

import com.google.android.gms.ads.AdListener;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class at extends bc.a {
    private final AdListener ob;

    public at(AdListener adListener) {
        this.ob = adListener;
    }

    @Override // com.google.android.gms.internal.bc
    public void onAdClosed() {
        this.ob.onAdClosed();
    }

    @Override // com.google.android.gms.internal.bc
    public void onAdFailedToLoad(int errorCode) {
        this.ob.onAdFailedToLoad(errorCode);
    }

    @Override // com.google.android.gms.internal.bc
    public void onAdLeftApplication() {
        this.ob.onAdLeftApplication();
    }

    @Override // com.google.android.gms.internal.bc
    public void onAdLoaded() {
        this.ob.onAdLoaded();
    }

    @Override // com.google.android.gms.internal.bc
    public void onAdOpened() {
        this.ob.onAdOpened();
    }
}
