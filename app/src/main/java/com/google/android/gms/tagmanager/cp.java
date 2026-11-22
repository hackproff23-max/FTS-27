package com.google.android.gms.tagmanager;

import android.content.Context;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
class cp implements o.e {
    private String aqI;
    private final String aqm;
    private bg<com.google.android.gms.internal.c.j> asC;
    private r asD;
    private final ScheduledExecutorService asF;
    private final a asG;
    private ScheduledFuture<?> asH;
    private boolean mClosed;
    private final Context mContext;

    interface a {
        co a(r rVar);
    }

    interface b {
        ScheduledExecutorService qh();
    }

    public cp(Context context, String str, r rVar) {
        this(context, str, rVar, null, null);
    }

    cp(Context context, String str, r rVar, b bVar, a aVar) {
        this.asD = rVar;
        this.mContext = context;
        this.aqm = str;
        this.asF = (bVar == null ? new b() { // from class: com.google.android.gms.tagmanager.cp.1
            @Override // com.google.android.gms.tagmanager.cp.b
            public ScheduledExecutorService qh() {
                return Executors.newSingleThreadScheduledExecutor();
            }
        } : bVar).qh();
        if (aVar == null) {
            this.asG = new a() { // from class: com.google.android.gms.tagmanager.cp.2
                @Override // com.google.android.gms.tagmanager.cp.a
                public co a(r rVar2) {
                    return new co(cp.this.mContext, cp.this.aqm, rVar2);
                }
            };
        } else {
            this.asG = aVar;
        }
    }

    private co cM(String str) {
        co coVarA = this.asG.a(this.asD);
        coVarA.a(this.asC);
        coVarA.cw(this.aqI);
        coVarA.cL(str);
        return coVarA;
    }

    private synchronized void qg() {
        if (this.mClosed) {
            throw new IllegalStateException("called method after closed");
        }
    }

    @Override // com.google.android.gms.tagmanager.o.e
    public synchronized void a(bg<com.google.android.gms.internal.c.j> bgVar) {
        qg();
        this.asC = bgVar;
    }

    @Override // com.google.android.gms.tagmanager.o.e
    public synchronized void cw(String str) {
        qg();
        this.aqI = str;
    }

    @Override // com.google.android.gms.tagmanager.o.e
    public synchronized void e(long j, String str) {
        bh.V("loadAfterDelay: containerId=" + this.aqm + " delay=" + j);
        qg();
        if (this.asC == null) {
            throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
        }
        if (this.asH != null) {
            this.asH.cancel(false);
        }
        this.asH = this.asF.schedule(cM(str), j, TimeUnit.MILLISECONDS);
    }

    @Override // com.google.android.gms.common.api.Releasable
    public synchronized void release() {
        qg();
        if (this.asH != null) {
            this.asH.cancel(false);
        }
        this.asF.shutdown();
        this.mClosed = true;
    }
}
