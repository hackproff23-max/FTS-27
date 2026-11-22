package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.InAppPurchaseListener;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class es extends en.a {
    private final InAppPurchaseListener oM;

    public es(InAppPurchaseListener inAppPurchaseListener) {
        this.oM = inAppPurchaseListener;
    }

    @Override // com.google.android.gms.internal.en
    public void a(em emVar) {
        this.oM.onInAppPurchaseRequested(new ev(emVar));
    }
}
