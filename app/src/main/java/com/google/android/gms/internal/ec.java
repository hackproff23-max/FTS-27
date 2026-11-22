package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import com.firsttouchgames.util.IabHelper;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.vending.expansion.downloader.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@ey
public class ec extends gf implements ServiceConnection {
    private Context mContext;
    private final Object mH = new Object();
    private boolean sD = false;
    private er sE;
    private eb sF;
    private eh sG;
    private List<ef> sH;
    private ek sI;

    public ec(Context context, er erVar, ek ekVar) {
        this.sH = null;
        this.mContext = context;
        this.sE = erVar;
        this.sI = ekVar;
        this.sF = new eb(context);
        this.sG = eh.j(this.mContext);
        this.sH = this.sG.d(10L);
    }

    private void a(final ef efVar, String str, String str2) {
        final Intent intent = new Intent();
        intent.putExtra(IabHelper.RESPONSE_CODE, 0);
        intent.putExtra(IabHelper.RESPONSE_INAPP_PURCHASE_DATA, str);
        intent.putExtra(IabHelper.RESPONSE_INAPP_SIGNATURE, str2);
        gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.ec.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (ec.this.sI.a(efVar.sU, -1, intent)) {
                        ec.this.sE.a(new eg(ec.this.mContext, efVar.sV, true, -1, intent, efVar));
                    } else {
                        ec.this.sE.a(new eg(ec.this.mContext, efVar.sV, false, -1, intent, efVar));
                    }
                } catch (RemoteException e) {
                    gr.W("Fail to verify and dispatch pending transaction");
                }
            }
        });
    }

    private void b(long j) {
        do {
            if (!c(j)) {
                gr.W("Timeout waiting for pending transaction to be processed.");
            }
        } while (!this.sD);
    }

    private boolean c(long j) {
        long jElapsedRealtime = Constants.WATCHDOG_WAKE_TIMER - (SystemClock.elapsedRealtime() - j);
        if (jElapsedRealtime <= 0) {
            return false;
        }
        try {
            this.mH.wait(jElapsedRealtime);
        } catch (InterruptedException e) {
            gr.W("waitWithTimeout_lock interrupted");
        }
        return true;
    }

    private void cy() {
        if (this.sH.isEmpty()) {
            return;
        }
        HashMap map = new HashMap();
        for (ef efVar : this.sH) {
            map.put(efVar.sV, efVar);
        }
        String str = null;
        while (true) {
            Bundle bundleD = this.sF.d(this.mContext.getPackageName(), str);
            if (bundleD == null || ei.b(bundleD) != 0) {
                break;
            }
            ArrayList<String> stringArrayList = bundleD.getStringArrayList(IabHelper.RESPONSE_INAPP_ITEM_LIST);
            ArrayList<String> stringArrayList2 = bundleD.getStringArrayList(IabHelper.RESPONSE_INAPP_PURCHASE_DATA_LIST);
            ArrayList<String> stringArrayList3 = bundleD.getStringArrayList(IabHelper.RESPONSE_INAPP_SIGNATURE_LIST);
            String string = bundleD.getString(IabHelper.INAPP_CONTINUATION_TOKEN);
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= stringArrayList.size()) {
                    break;
                }
                if (map.containsKey(stringArrayList.get(i2))) {
                    String str2 = stringArrayList.get(i2);
                    String str3 = stringArrayList2.get(i2);
                    String str4 = stringArrayList3.get(i2);
                    ef efVar2 = (ef) map.get(str2);
                    if (efVar2.sU.equals(ei.D(str3))) {
                        a(efVar2, str3, str4);
                        map.remove(str2);
                    }
                }
                i = i2 + 1;
            }
            if (string == null || map.isEmpty()) {
                break;
            } else {
                str = string;
            }
        }
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            this.sG.a((ef) map.get((String) it.next()));
        }
    }

    @Override // com.google.android.gms.internal.gf
    public void cx() {
        synchronized (this.mH) {
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE);
            this.mContext.bindService(intent, this, 1);
            b(SystemClock.elapsedRealtime());
            this.mContext.unbindService(this);
            this.sF.destroy();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName name, IBinder service) {
        synchronized (this.mH) {
            this.sF.t(service);
            cy();
            this.sD = true;
            this.mH.notify();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName name) {
        gr.U("In-app billing service disconnected.");
        this.sF.destroy();
    }

    @Override // com.google.android.gms.internal.gf
    public void onStop() {
        synchronized (this.mH) {
            this.mContext.unbindService(this);
            this.sF.destroy();
        }
    }
}
