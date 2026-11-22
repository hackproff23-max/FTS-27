package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.purchase.InAppPurchase;

/* JADX INFO: loaded from: classes.dex */
@ey
public class ev implements InAppPurchase {
    private final em sP;

    public ev(em emVar) {
        this.sP = emVar;
    }

    @Override // com.google.android.gms.ads.purchase.InAppPurchase
    public String getProductId() {
        try {
            return this.sP.getProductId();
        } catch (RemoteException e) {
            gr.d("Could not forward getProductId to InAppPurchase", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.purchase.InAppPurchase
    public void recordPlayBillingResolution(int billingResponseCode) {
        try {
            this.sP.recordPlayBillingResolution(billingResponseCode);
        } catch (RemoteException e) {
            gr.d("Could not forward recordPlayBillingResolution to InAppPurchase", e);
        }
    }

    @Override // com.google.android.gms.ads.purchase.InAppPurchase
    public void recordResolution(int resolution) {
        try {
            this.sP.recordResolution(resolution);
        } catch (RemoteException e) {
            gr.d("Could not forward recordResolution to InAppPurchase", e);
        }
    }
}
