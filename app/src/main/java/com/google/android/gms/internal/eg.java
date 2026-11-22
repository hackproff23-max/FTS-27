package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesUtil;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class eg extends eq.a implements ServiceConnection {
    private Context mContext;
    private eb sF;
    private String sM;
    private ef sQ;
    private boolean sW;
    private int sX;
    private Intent sY;

    public eg(Context context, String str, boolean z, int i, Intent intent, ef efVar) {
        this.sW = false;
        this.sM = str;
        this.sX = i;
        this.sY = intent;
        this.sW = z;
        this.mContext = context;
        this.sQ = efVar;
    }

    @Override // com.google.android.gms.internal.eq
    public void finishPurchase() {
        int iD = ei.d(this.sY);
        if (this.sX == -1 && iD == 0) {
            this.sF = new eb(this.mContext);
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE);
            this.mContext.bindService(intent, this, 1);
        }
    }

    @Override // com.google.android.gms.internal.eq
    public String getProductId() {
        return this.sM;
    }

    @Override // com.google.android.gms.internal.eq
    public Intent getPurchaseData() {
        return this.sY;
    }

    @Override // com.google.android.gms.internal.eq
    public int getResultCode() {
        return this.sX;
    }

    @Override // com.google.android.gms.internal.eq
    public boolean isVerified() {
        return this.sW;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName name, IBinder service) {
        gr.U("In-app billing service connected.");
        this.sF.t(service);
        String strE = ei.E(ei.e(this.sY));
        if (strE == null) {
            return;
        }
        if (this.sF.c(this.mContext.getPackageName(), strE) == 0) {
            eh.j(this.mContext).a(this.sQ);
        }
        this.mContext.unbindService(this);
        this.sF.destroy();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName name) {
        gr.U("In-app billing service disconnected.");
        this.sF.destroy();
    }
}
