package com.flurry.sdk;

import android.content.Context;
import com.flurry.android.impl.ads.protocol.v13.AdUnit;
import com.flurry.android.impl.ads.protocol.v13.AdViewType;

/* JADX INFO: loaded from: classes.dex */
public class t extends o {
    private static final String a = t.class.getSimpleName();
    private a b;

    enum a {
        INIT,
        READY,
        DISPLAY,
        NEXT
    }

    public t(Context context, String str) {
        super(context, null, str);
        this.b = a.INIT;
    }

    @Override // com.flurry.sdk.o, com.flurry.sdk.r
    public void a() {
        super.a();
    }

    @Override // com.flurry.sdk.o
    public ci i() {
        return i.a().c().a(g(), cr.b(), l()).a();
    }

    @Override // com.flurry.sdk.o
    public x j() {
        return i.a().c().a(g(), cr.b(), l()).b();
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0029  */
    /* JADX WARN: Code duplicated, block: B:28:? A[RETURN, SYNTHETIC] */
    @Override // com.flurry.sdk.o
    protected void a(d dVar) {
        super.a(dVar);
        if (d.a.kOnFetched.equals(dVar.b)) {
            synchronized (this) {
                synchronized (this) {
                    if (a.INIT.equals(this.b)) {
                        this.b = a.READY;
                    } else if (a.DISPLAY.equals(this.b)) {
                        this.b = a.NEXT;
                    }
                }
                if (a.NEXT.equals(this.b)) {
                    fp.a().b(new hq() { // from class: com.flurry.sdk.t.1
                        @Override // com.flurry.sdk.hq
                        public void safeRun() {
                            t.this.v();
                        }
                    });
                }
            }
            if (a.NEXT.equals(this.b)) {
                fp.a().b(new hq() { // from class: com.flurry.sdk.t.1
                    @Override // com.flurry.sdk.hq
                    public void safeRun() {
                        t.this.v();
                    }
                });
            }
        }
    }

    public boolean s() {
        boolean zEquals;
        synchronized (this) {
            zEquals = a.READY.equals(this.b);
        }
        return zEquals;
    }

    public void t() {
        synchronized (this) {
            if (a.INIT.equals(this.b)) {
                h().a(this, i(), j());
            } else if (a.READY.equals(this.b)) {
                cq.a(this);
            } else if (a.DISPLAY.equals(this.b) || a.NEXT.equals(this.b)) {
                cq.b(this);
            }
        }
    }

    public void u() {
        synchronized (this) {
            if (a.INIT.equals(this.b)) {
                cq.b(this, av.kNotReady);
            } else if (a.READY.equals(this.b)) {
                fp.a().b(new hq() { // from class: com.flurry.sdk.t.2
                    @Override // com.flurry.sdk.hq
                    public void safeRun() {
                        t.this.v();
                    }
                });
            } else if (a.DISPLAY.equals(this.b) || a.NEXT.equals(this.b)) {
                cq.b(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        hp.b();
        synchronized (this) {
            if (a.READY.equals(this.b) || a.NEXT.equals(this.b)) {
                this.b = a.DISPLAY;
                gd.a(3, a, "render interstitial (" + this + ")");
                Context contextE = e();
                if (!fj.a().c()) {
                    gd.a(5, a, "There is no network connectivity (ad will not display)");
                    cq.b(this, av.kNoNetworkConnectivity);
                    return;
                }
                if (contextE == null) {
                    cq.b(this, av.kNoContext);
                    return;
                }
                ap apVarN = n();
                if (apVarN == null) {
                    cq.b(this, av.kMissingAdController);
                    return;
                }
                AdUnit adUnitA = apVarN.a();
                if (adUnitA == null) {
                    cq.b(this, av.kInvalidAdUnit);
                    return;
                }
                if (adUnitA.combinable == 1) {
                    cq.b(this, av.kInvalidAdUnit);
                    return;
                }
                if (!AdViewType.INTERSTITIAL.equals(adUnitA.adViewType)) {
                    cq.a(this, av.kIncorrectClassForAdSpace);
                } else if (!cr.b().equals(adUnitA.screenOrientation)) {
                    cq.b(this, av.kWrongOrientation);
                } else {
                    o();
                    fp.a().a(new hq() { // from class: com.flurry.sdk.t.3
                        @Override // com.flurry.sdk.hq
                        public void safeRun() {
                            t.this.w();
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        hp.a();
        p();
        en enVarA = i.a().q().a(e(), this);
        if (enVarA != null) {
            enVarA.a();
        }
        cq.b(this);
    }
}
