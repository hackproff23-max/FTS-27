package com.google.android.gms.internal;

import android.content.Context;
import android.os.SystemClock;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.vending.expansion.downloader.Constants;

/* JADX INFO: loaded from: classes.dex */
@ey
public class fd extends gf implements gv.a {
    private final cy lA;
    private final Context mContext;
    private final gu mo;
    private cr qi;
    private final fc.a tA;
    private final fy.a tB;
    private cp tD;
    private cv tE;
    private fj tn;
    private final Object tj = new Object();
    private final Object mH = new Object();
    private boolean tC = false;

    private static final class a extends Exception {
        private final int tq;

        public a(String str, int i) {
            super(str);
            this.tq = i;
        }

        public int getErrorCode() {
            return this.tq;
        }
    }

    public fd(Context context, fy.a aVar, gu guVar, cy cyVar, fc.a aVar2) {
        this.mContext = context;
        this.tB = aVar;
        this.tn = aVar.vK;
        this.mo = guVar;
        this.lA = cyVar;
        this.tA = aVar2;
        this.qi = aVar.vE;
    }

    private void a(fh fhVar, long j) throws a {
        synchronized (this.tj) {
            this.tD = new cp(this.mContext, fhVar, this.lA, this.qi);
        }
        this.tE = this.tD.a(j, Constants.WATCHDOG_WAKE_TIMER);
        switch (this.tE.qO) {
            case 0:
                return;
            case 1:
                throw new a("No fill from any mediation ad networks.", 3);
            default:
                throw new a("Unexpected mediation result: " + this.tE.qO, 0);
        }
    }

    private boolean c(long j) throws a {
        long jElapsedRealtime = Constants.WATCHDOG_WAKE_TIMER - (SystemClock.elapsedRealtime() - j);
        if (jElapsedRealtime <= 0) {
            return false;
        }
        try {
            this.mH.wait(jElapsedRealtime);
            return true;
        } catch (InterruptedException e) {
            throw new a("Ad request cancelled.", -1);
        }
    }

    private void f(long j) throws a {
        gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.fd.3
            @Override // java.lang.Runnable
            public void run() {
                synchronized (fd.this.mH) {
                    if (fd.this.tn.errorCode != -2) {
                        return;
                    }
                    fd.this.mo.dD().a(fd.this);
                    if (fd.this.tn.errorCode == -3) {
                        gr.V("Loading URL in WebView: " + fd.this.tn.sg);
                        fd.this.mo.loadUrl(fd.this.tn.sg);
                    } else {
                        gr.V("Loading HTML in WebView.");
                        fd.this.mo.loadDataWithBaseURL(gi.L(fd.this.tn.sg), fd.this.tn.tU, "text/html", "UTF-8", null);
                    }
                }
            }
        });
        h(j);
    }

    private void h(long j) throws a {
        while (c(j)) {
            if (this.tC) {
                return;
            }
        }
        throw new a("Timed out waiting for WebView to finish loading.", 2);
    }

    @Override // com.google.android.gms.internal.gv.a
    public void a(gu guVar) {
        synchronized (this.mH) {
            gr.S("WebView finished loading.");
            this.tC = true;
            this.mH.notify();
        }
    }

    @Override // com.google.android.gms.internal.gf
    public void cx() {
        synchronized (this.mH) {
            gr.S("AdRendererBackgroundTask started.");
            fh fhVar = this.tB.vJ;
            int errorCode = this.tB.errorCode;
            try {
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                if (this.tn.tW) {
                    a(fhVar, jElapsedRealtime);
                } else if (this.tn.uc) {
                    g(jElapsedRealtime);
                } else {
                    f(jElapsedRealtime);
                }
            } catch (a e) {
                errorCode = e.getErrorCode();
                if (errorCode == 3 || errorCode == -1) {
                    gr.U(e.getMessage());
                } else {
                    gr.W(e.getMessage());
                }
                if (this.tn == null) {
                    this.tn = new fj(errorCode);
                } else {
                    this.tn = new fj(errorCode, this.tn.qA);
                }
                gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.fd.1
                    @Override // java.lang.Runnable
                    public void run() {
                        fd.this.onStop();
                    }
                });
            }
            final fy fyVar = new fy(fhVar.tL, this.mo, this.tn.qw, errorCode, this.tn.qx, this.tn.tY, this.tn.orientation, this.tn.qA, fhVar.tO, this.tn.tW, this.tE != null ? this.tE.qP : null, this.tE != null ? this.tE.qQ : null, this.tE != null ? this.tE.qR : AdMobAdapter.class.getName(), this.qi, this.tE != null ? this.tE.qS : null, this.tn.tX, this.tB.lS, this.tn.tV, this.tB.vG, this.tn.ua, this.tn.ub, this.tB.vD, null);
            gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.fd.2
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (fd.this.mH) {
                        fd.this.tA.a(fyVar);
                    }
                }
            });
        }
    }

    protected void g(long j) throws a {
        int i;
        int i2;
        ay ayVarAc = this.mo.ac();
        if (ayVarAc.oq) {
            i = this.mContext.getResources().getDisplayMetrics().widthPixels;
            i2 = this.mContext.getResources().getDisplayMetrics().heightPixels;
        } else {
            i = ayVarAc.widthPixels;
            i2 = ayVarAc.heightPixels;
        }
        final fb fbVar = new fb(this, this.mo, i, i2);
        gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.fd.4
            @Override // java.lang.Runnable
            public void run() {
                synchronized (fd.this.mH) {
                    if (fd.this.tn.errorCode != -2) {
                        return;
                    }
                    fd.this.mo.dD().a(fd.this);
                    fbVar.b(fd.this.tn);
                }
            }
        });
        h(j);
        if (fbVar.cH()) {
            gr.S("Ad-Network indicated no fill with passback URL.");
            throw new a("AdNetwork sent passback url", 3);
        }
        if (!fbVar.cI()) {
            throw new a("AdNetwork timed out", 2);
        }
    }

    @Override // com.google.android.gms.internal.gf
    public void onStop() {
        synchronized (this.tj) {
            this.mo.stopLoading();
            gi.a(this.mo);
            if (this.tD != null) {
                this.tD.cancel();
            }
        }
    }
}
