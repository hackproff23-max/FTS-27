package com.google.android.gms.internal;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.ads.purchase.InAppPurchaseResult;

/* JADX INFO: loaded from: classes.dex */
@ey
public class eu implements InAppPurchaseResult {
    private final eq te;

    public eu(eq eqVar) {
        this.te = eqVar;
    }

    @Override // com.google.android.gms.ads.purchase.InAppPurchaseResult
    public void finishPurchase() {
        try {
            this.te.finishPurchase();
        } catch (RemoteException e) {
            gr.d("Could not forward finishPurchase to InAppPurchaseResult", e);
        }
    }

    @Override // com.google.android.gms.ads.purchase.InAppPurchaseResult
    public String getProductId() {
        try {
            return this.te.getProductId();
        } catch (RemoteException e) {
            gr.d("Could not forward getProductId to InAppPurchaseResult", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.purchase.InAppPurchaseResult
    public Intent getPurchaseData() {
        try {
            return this.te.getPurchaseData();
        } catch (RemoteException e) {
            gr.d("Could not forward getPurchaseData to InAppPurchaseResult", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.purchase.InAppPurchaseResult
    public int getResultCode() {
        try {
            return this.te.getResultCode();
        } catch (RemoteException e) {
            gr.d("Could not forward getPurchaseData to InAppPurchaseResult", e);
            return 0;
        }
    }

    @Override // com.google.android.gms.ads.purchase.InAppPurchaseResult
    public boolean isVerified() {
        try {
            return this.te.isVerified();
        } catch (RemoteException e) {
            gr.d("Could not forward isVerified to InAppPurchaseResult", e);
            return false;
        }
    }
}
