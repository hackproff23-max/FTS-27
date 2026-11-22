package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class cp {
    private final cy lA;
    private final Context mContext;
    private final fh qh;
    private final cr qi;
    private cu qk;
    private final Object mH = new Object();
    private boolean qj = false;

    public cp(Context context, fh fhVar, cy cyVar, cr crVar) {
        this.mContext = context;
        this.qh = fhVar;
        this.lA = cyVar;
        this.qi = crVar;
    }

    public cv a(long j, long j2) {
        gr.S("Starting mediation.");
        for (cq cqVar : this.qi.qu) {
            gr.U("Trying mediation network: " + cqVar.qo);
            for (String str : cqVar.qp) {
                synchronized (this.mH) {
                    if (this.qj) {
                        return new cv(-1);
                    }
                    this.qk = new cu(this.mContext, str, this.lA, this.qi, cqVar, this.qh.tL, this.qh.lS, this.qh.lO);
                    final cv cvVarB = this.qk.b(j, j2);
                    if (cvVarB.qO == 0) {
                        gr.S("Adapter succeeded.");
                        return cvVarB;
                    }
                    if (cvVarB.qQ != null) {
                        gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.cp.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    cvVarB.qQ.destroy();
                                } catch (RemoteException e) {
                                    gr.d("Could not destroy mediation adapter.", e);
                                }
                            }
                        });
                    }
                }
            }
        }
        return new cv(1);
    }

    public void cancel() {
        synchronized (this.mH) {
            this.qj = true;
            if (this.qk != null) {
                this.qk.cancel();
            }
        }
    }
}
