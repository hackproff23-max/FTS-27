package com.google.android.gms.internal;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.firsttouchgames.util.IabHelper;
import com.google.android.gms.ads.purchase.InAppPurchaseActivity;
import com.google.android.gms.common.GooglePlayServicesUtil;

/* JADX INFO: loaded from: classes.dex */
@ey
public class ee extends eo.a implements ServiceConnection {
    private final Activity nB;
    private eb sF;
    private final eh sG;
    private ek sI;
    private Context sO;
    private em sP;
    private ef sQ;
    private ej sR;
    private String sS = null;

    public ee(Activity activity) {
        this.nB = activity;
        this.sG = eh.j(this.nB.getApplicationContext());
    }

    public static void a(Context context, boolean z, ea eaVar) {
        Intent intent = new Intent();
        intent.setClassName(context, InAppPurchaseActivity.CLASS_NAME);
        intent.putExtra("com.google.android.gms.ads.internal.purchase.useClientJar", z);
        ea.a(intent, eaVar);
        context.startActivity(intent);
    }

    private void a(String str, boolean z, int i, Intent intent) {
        if (this.sR != null) {
            this.sR.a(str, z, i, intent, this.sQ);
        }
    }

    @Override // com.google.android.gms.internal.eo
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1001) {
            boolean z = false;
            try {
                int iD = ei.d(data);
                if (resultCode != -1 || iD != 0) {
                    this.sG.a(this.sQ);
                } else if (this.sI.a(this.sS, resultCode, data)) {
                    z = true;
                }
                this.sP.recordPlayBillingResolution(iD);
                this.nB.finish();
                a(this.sP.getProductId(), z, resultCode, data);
            } catch (RemoteException e) {
                gr.W("Fail to process purchase result.");
                this.nB.finish();
            } finally {
                this.sS = null;
            }
        }
    }

    @Override // com.google.android.gms.internal.eo
    public void onCreate() {
        ea eaVarC = ea.c(this.nB.getIntent());
        this.sR = eaVarC.sB;
        this.sI = eaVarC.md;
        this.sP = eaVarC.sz;
        this.sF = new eb(this.nB.getApplicationContext());
        this.sO = eaVarC.sA;
        if (this.nB.getResources().getConfiguration().orientation == 2) {
            this.nB.setRequestedOrientation(6);
        } else {
            this.nB.setRequestedOrientation(7);
        }
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE);
        this.nB.bindService(intent, this, 1);
    }

    @Override // com.google.android.gms.internal.eo
    public void onDestroy() {
        this.nB.unbindService(this);
        this.sF.destroy();
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName name, IBinder service) {
        this.sF.t(service);
        try {
            this.sS = this.sI.cC();
            Bundle bundleA = this.sF.a(this.nB.getPackageName(), this.sP.getProductId(), this.sS);
            PendingIntent pendingIntent = (PendingIntent) bundleA.getParcelable(IabHelper.RESPONSE_BUY_INTENT);
            if (pendingIntent == null) {
                int iB = ei.b(bundleA);
                this.sP.recordPlayBillingResolution(iB);
                a(this.sP.getProductId(), false, iB, null);
                this.nB.finish();
            } else {
                this.sQ = new ef(this.sP.getProductId(), this.sS);
                this.sG.b(this.sQ);
                Integer num = 0;
                Integer num2 = 0;
                Integer num3 = 0;
                this.nB.startIntentSenderForResult(pendingIntent.getIntentSender(), 1001, new Intent(), num.intValue(), num2.intValue(), num3.intValue());
            }
        } catch (IntentSender.SendIntentException | RemoteException e) {
            gr.d("Error when connecting in-app billing service", e);
            this.nB.finish();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName name) {
        gr.U("In-app billing service disconnected.");
        this.sF.destroy();
    }
}
