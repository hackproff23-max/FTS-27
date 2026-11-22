package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class ct extends da.a {
    private final Object mH = new Object();
    private cv.a qD;
    private cs qE;

    public void a(cs csVar) {
        synchronized (this.mH) {
            this.qE = csVar;
        }
    }

    public void a(cv.a aVar) {
        synchronized (this.mH) {
            this.qD = aVar;
        }
    }

    @Override // com.google.android.gms.internal.da
    public void onAdClicked() {
        synchronized (this.mH) {
            if (this.qE != null) {
                this.qE.ai();
            }
        }
    }

    @Override // com.google.android.gms.internal.da
    public void onAdClosed() {
        synchronized (this.mH) {
            if (this.qE != null) {
                this.qE.aj();
            }
        }
    }

    @Override // com.google.android.gms.internal.da
    public void onAdFailedToLoad(int error) {
        synchronized (this.mH) {
            if (this.qD != null) {
                this.qD.k(error == 3 ? 1 : 2);
                this.qD = null;
            }
        }
    }

    @Override // com.google.android.gms.internal.da
    public void onAdLeftApplication() {
        synchronized (this.mH) {
            if (this.qE != null) {
                this.qE.ak();
            }
        }
    }

    @Override // com.google.android.gms.internal.da
    public void onAdLoaded() {
        synchronized (this.mH) {
            if (this.qD != null) {
                this.qD.k(0);
                this.qD = null;
            } else {
                if (this.qE != null) {
                    this.qE.am();
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.da
    public void onAdOpened() {
        synchronized (this.mH) {
            if (this.qE != null) {
                this.qE.al();
            }
        }
    }
}
