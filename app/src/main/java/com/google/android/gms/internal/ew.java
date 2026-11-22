package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class ew extends er.a {
    private final PlayStorePurchaseListener oN;

    public ew(PlayStorePurchaseListener playStorePurchaseListener) {
        this.oN = playStorePurchaseListener;
    }

    @Override // com.google.android.gms.internal.er
    public void a(eq eqVar) {
        this.oN.onInAppPurchaseFinished(new eu(eqVar));
    }

    @Override // com.google.android.gms.internal.er
    public boolean isValidPurchase(String productId) {
        return this.oN.isValidPurchase(productId);
    }
}
