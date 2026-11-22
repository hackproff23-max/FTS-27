package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.vending.expansion.downloader.Constants;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
@ey
public class fm extends gf {
    private final Object mH;
    private final fc.a tA;
    private final fy.a tB;
    private final fj tn;
    private final fn ui;
    private Future<fy> uj;

    public fm(Context context, u uVar, ai aiVar, fy.a aVar, fc.a aVar2) {
        this(aVar, aVar2, new fn(context, uVar, aiVar, new gn(), aVar));
    }

    fm(fy.a aVar, fc.a aVar2, fn fnVar) {
        this.mH = new Object();
        this.tB = aVar;
        this.tn = aVar.vK;
        this.tA = aVar2;
        this.ui = fnVar;
    }

    private fy s(int i) {
        return new fy(this.tB.vJ.tL, null, null, i, null, null, this.tn.orientation, this.tn.qA, this.tB.vJ.tO, false, null, null, null, null, null, this.tn.tX, this.tB.lS, this.tn.tV, this.tB.vG, this.tn.ua, this.tn.ub, this.tB.vD, null);
    }

    @Override // com.google.android.gms.internal.gf
    public void cx() {
        int i;
        final fy fyVarS;
        try {
            synchronized (this.mH) {
                this.uj = gh.submit(this.ui);
            }
            fyVarS = this.uj.get(Constants.WATCHDOG_WAKE_TIMER, TimeUnit.MILLISECONDS);
            i = -2;
        } catch (InterruptedException e) {
            fyVarS = null;
            i = -1;
        } catch (CancellationException e2) {
            fyVarS = null;
            i = -1;
        } catch (ExecutionException e3) {
            i = 0;
            fyVarS = null;
        } catch (TimeoutException e4) {
            gr.W("Timed out waiting for native ad.");
            i = 2;
            fyVarS = null;
        }
        if (fyVarS == null) {
            fyVarS = s(i);
        }
        gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.fm.1
            @Override // java.lang.Runnable
            public void run() {
                fm.this.tA.a(fyVarS);
            }
        });
    }

    @Override // com.google.android.gms.internal.gf
    public void onStop() {
        synchronized (this.mH) {
            if (this.uj != null) {
                this.uj.cancel(true);
            }
        }
    }
}
