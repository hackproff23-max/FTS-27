package com.flurry.sdk;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.flurry.android.impl.ads.protocol.v13.AdUnit;
import com.flurry.android.impl.ads.protocol.v13.AdViewType;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class q extends o implements s {
    private static final String a = q.class.getSimpleName();
    private a b;
    private WeakReference<RelativeLayout> c;
    private boolean d;
    private long e;
    private long f;

    enum a {
        INIT,
        READY,
        DISPLAY,
        NEXT
    }

    public q(Context context, ViewGroup viewGroup, String str) {
        super(context, viewGroup, str);
        this.b = a.INIT;
        this.c = new WeakReference<>(null);
    }

    @Override // com.flurry.sdk.o, com.flurry.sdk.r
    public void a() {
        fp.a().a(new hq() { // from class: com.flurry.sdk.q.1
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                q.this.t();
            }
        });
        super.a();
    }

    @Override // com.flurry.sdk.o, com.flurry.sdk.r
    public void b() {
        super.b();
    }

    @Override // com.flurry.sdk.o, com.flurry.sdk.r
    public void c() {
        super.c();
        this.f = this.e;
    }

    @Override // com.flurry.sdk.o
    public ci i() {
        return i.a().c().a(g(), cr.b(), l()).a();
    }

    @Override // com.flurry.sdk.o
    public x j() {
        return i.a().c().a(g(), cr.b(), l()).b();
    }

    @Override // com.flurry.sdk.o, com.flurry.sdk.r
    public void a(ap apVar, long j) {
        if (s() != null && s().getChildCount() > 0) {
            a(j);
        } else {
            h().a(this, i(), j());
        }
    }

    @Override // com.flurry.sdk.o
    protected void r() {
        super.r();
        if (this.e > 0) {
            this.f -= System.currentTimeMillis() - q();
            if (this.f <= 0) {
                if (A()) {
                    gd.a(3, a, "Rotating banner for adSpace: " + g());
                    h().a(this, i(), j());
                }
                this.f = this.e;
            }
        }
    }

    @Override // com.flurry.sdk.o
    protected void a(d dVar) {
        super.a(dVar);
        if (d.a.kOnFetched.equals(dVar.b)) {
            synchronized (this) {
                if (a.INIT.equals(this.b)) {
                    this.b = a.READY;
                } else if (a.DISPLAY.equals(this.b)) {
                    this.b = a.NEXT;
                }
            }
            if (this.d || a.NEXT.equals(this.b)) {
                fp.a().b(new hq() { // from class: com.flurry.sdk.q.2
                    @Override // com.flurry.sdk.hq
                    public void safeRun() {
                        q.this.y();
                    }
                });
            }
        }
    }

    @Override // com.flurry.sdk.s
    public RelativeLayout s() {
        return this.c.get();
    }

    @Override // com.flurry.sdk.s
    public void a(RelativeLayout relativeLayout) {
        this.c = new WeakReference<>(relativeLayout);
    }

    public void t() {
        hp.a();
        RelativeLayout relativeLayout = this.c.get();
        if (relativeLayout != null) {
            while (relativeLayout.getChildCount() > 0) {
                View childAt = relativeLayout.getChildAt(0);
                relativeLayout.removeView(childAt);
                if (childAt instanceof ec) {
                    ((ec) childAt).onActivityDestroy();
                }
            }
            ViewGroup viewGroupF = f();
            if (viewGroupF != null) {
                viewGroupF.removeView(relativeLayout);
                viewGroupF.setBackgroundColor(0);
            }
        }
        this.c.clear();
    }

    public boolean u() {
        boolean zEquals;
        synchronized (this) {
            zEquals = a.READY.equals(this.b);
        }
        return zEquals;
    }

    public void v() {
        this.d = false;
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

    public void w() {
        synchronized (this) {
            if (a.INIT.equals(this.b)) {
                cq.b(this, av.kNotReady);
            } else if (a.READY.equals(this.b)) {
                fp.a().b(new hq() { // from class: com.flurry.sdk.q.3
                    @Override // com.flurry.sdk.hq
                    public void safeRun() {
                        q.this.y();
                    }
                });
            } else if (a.DISPLAY.equals(this.b) || a.NEXT.equals(this.b)) {
                cq.b(this);
            }
        }
    }

    public void x() {
        this.d = true;
        synchronized (this) {
            if (a.INIT.equals(this.b)) {
                h().a(this, i(), j());
            } else if (a.READY.equals(this.b)) {
                fp.a().b(new hq() { // from class: com.flurry.sdk.q.4
                    @Override // com.flurry.sdk.hq
                    public void safeRun() {
                        q.this.y();
                    }
                });
            } else if (a.DISPLAY.equals(this.b) || a.NEXT.equals(this.b)) {
                cq.b(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        hp.b();
        synchronized (this) {
            if (a.READY.equals(this.b) || a.NEXT.equals(this.b)) {
                this.b = a.DISPLAY;
                gd.a(3, a, "render banner (" + this + ")");
                Context contextE = e();
                ViewGroup viewGroupF = f();
                if (!fj.a().c()) {
                    gd.a(5, a, "There is no network connectivity (ad will not display)");
                    cq.b(this, av.kNoNetworkConnectivity);
                    return;
                }
                if (contextE == null) {
                    cq.b(this, av.kNoContext);
                    return;
                }
                if (viewGroupF == null) {
                    cq.b(this, av.kNoViewGroup);
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
                if (!AdViewType.BANNER.equals(adUnitA.adViewType)) {
                    cq.a(this, av.kIncorrectClassForAdSpace);
                    return;
                }
                if (!ax.BANNER.equals(apVarN.d())) {
                    cq.a(this, av.kIncorrectClassForAdSpace);
                } else if (!cr.b().equals(adUnitA.screenOrientation)) {
                    cq.b(this, av.kWrongOrientation);
                } else {
                    o();
                    fp.a().a(new hq() { // from class: com.flurry.sdk.q.5
                        @Override // com.flurry.sdk.hq
                        public void safeRun() {
                            q.this.z();
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        hp.a();
        a(0L);
        p();
        eg.a(e(), this);
        cq.b(this);
    }

    private boolean A() {
        if (hn.a()) {
            gd.a(3, a, "Device is locked: banner will NOT rotate for adSpace: " + g());
            return false;
        }
        if (this.c.get() == null) {
            gd.a(3, a, "No banner holder: banner will NOT rotate for adSpace: " + g());
            return false;
        }
        return true;
    }

    private void a(long j) {
        gd.a(3, a, "Scheduled banner rotation for adSpace: " + g());
        this.e = j;
        this.f = j;
    }
}
