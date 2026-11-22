package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class cu implements cv.a {
    private final cy lA;
    private final Context mContext;
    private final av mw;
    private final String qF;
    private final long qG;
    private final cq qH;
    private final ay qI;
    private final gs qJ;
    private cz qK;
    private final Object mH = new Object();
    private int qL = -2;

    public cu(Context context, String str, cy cyVar, cr crVar, cq cqVar, av avVar, ay ayVar, gs gsVar) {
        this.mContext = context;
        this.lA = cyVar;
        this.qH = cqVar;
        if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
            this.qF = bO();
        } else {
            this.qF = str;
        }
        this.qG = crVar.qv != -1 ? crVar.qv : 10000L;
        this.mw = avVar;
        this.qI = ayVar;
        this.qJ = gsVar;
    }

    private void a(long j, long j2, long j3, long j4) {
        while (this.qL == -2) {
            b(j, j2, j3, j4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ct ctVar) {
        try {
            if (this.qJ.wU < 4100000) {
                if (this.qI.oq) {
                    this.qK.a(com.google.android.gms.dynamic.e.k(this.mContext), this.mw, this.qH.qt, ctVar);
                } else {
                    this.qK.a(com.google.android.gms.dynamic.e.k(this.mContext), this.qI, this.mw, this.qH.qt, ctVar);
                }
            } else if (this.qI.oq) {
                this.qK.a(com.google.android.gms.dynamic.e.k(this.mContext), this.mw, this.qH.qt, this.qH.qn, ctVar);
            } else {
                this.qK.a(com.google.android.gms.dynamic.e.k(this.mContext), this.qI, this.mw, this.qH.qt, this.qH.qn, ctVar);
            }
        } catch (RemoteException e) {
            gr.d("Could not request ad from mediation adapter.", e);
            k(5);
        }
    }

    private void b(long j, long j2, long j3, long j4) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j5 = j2 - (jElapsedRealtime - j);
        long j6 = j4 - (jElapsedRealtime - j3);
        if (j5 <= 0 || j6 <= 0) {
            gr.U("Timed out waiting for adapter.");
            this.qL = 3;
        } else {
            try {
                this.mH.wait(Math.min(j5, j6));
            } catch (InterruptedException e) {
                this.qL = -1;
            }
        }
    }

    private String bO() {
        try {
            return (TextUtils.isEmpty(this.qH.qr) || !this.lA.y(this.qH.qr)) ? "com.google.ads.mediation.customevent.CustomEventAdapter" : "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter";
        } catch (RemoteException e) {
            gr.W("Fail to determine the custom event's version, assuming the old one.");
        }
        return "com.google.ads.mediation.customevent.CustomEventAdapter";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public cz bP() {
        gr.U("Instantiating mediation adapter: " + this.qF);
        try {
            return this.lA.x(this.qF);
        } catch (RemoteException e) {
            gr.a("Could not instantiate mediation adapter: " + this.qF, e);
            return null;
        }
    }

    public cv b(long j, long j2) {
        cv cvVar;
        synchronized (this.mH) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            final ct ctVar = new ct();
            gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.cu.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (cu.this.mH) {
                        if (cu.this.qL != -2) {
                            return;
                        }
                        cu.this.qK = cu.this.bP();
                        if (cu.this.qK == null) {
                            cu.this.k(4);
                        } else {
                            ctVar.a(cu.this);
                            cu.this.a(ctVar);
                        }
                    }
                }
            });
            a(jElapsedRealtime, this.qG, j, j2);
            cvVar = new cv(this.qH, this.qK, this.qF, ctVar, this.qL);
        }
        return cvVar;
    }

    public void cancel() {
        synchronized (this.mH) {
            try {
                if (this.qK != null) {
                    this.qK.destroy();
                }
            } catch (RemoteException e) {
                gr.d("Could not destroy mediation adapter.", e);
            }
            this.qL = -1;
            this.mH.notify();
        }
    }

    @Override // com.google.android.gms.internal.cv.a
    public void k(int i) {
        synchronized (this.mH) {
            this.qL = i;
            this.mH.notify();
        }
    }
}
