package com.flurry.sdk;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.flurry.android.FlurryAdListener;
import com.flurry.android.FlurryAdType;
import com.flurry.android.impl.ads.protocol.v13.AdUnit;
import com.flurry.android.impl.ads.protocol.v13.AdViewType;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class u implements r, s {
    private static final String a = u.class.getSimpleName();
    private final int b;
    private final WeakReference<Context> c;
    private final WeakReference<ViewGroup> d;
    private final String e;
    private a f;
    private final ch g;
    private ap h;
    private ap i;
    private long j;
    private final fy<hj> k = new fy<hj>() { // from class: com.flurry.sdk.u.1
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(hj hjVar) {
            u.this.y();
            u.this.j = System.currentTimeMillis();
        }
    };
    private final fy<d> l = new fy<d>() { // from class: com.flurry.sdk.u.2
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(final d dVar) {
            if (dVar.a == u.this && dVar.b != null) {
                u.this.a(dVar);
                final FlurryAdListener flurryAdListenerB = j.a().b();
                if (flurryAdListenerB != null) {
                    final String strG = dVar.a.g();
                    fp.a().a(new hq() { // from class: com.flurry.sdk.u.2.1
                        @Override // com.flurry.sdk.hq
                        public void safeRun() {
                            switch (AnonymousClass8.a[dVar.b.ordinal()]) {
                                case 1:
                                    flurryAdListenerB.spaceDidReceiveAd(strG);
                                    break;
                                case 2:
                                    flurryAdListenerB.spaceDidFailToReceiveAd(strG);
                                    break;
                                case 3:
                                    flurryAdListenerB.onRendered(strG);
                                    break;
                                case 4:
                                    flurryAdListenerB.onRenderFailed(strG);
                                    break;
                                case 5:
                                    flurryAdListenerB.onApplicationExit(strG);
                                    break;
                                case 6:
                                    flurryAdListenerB.onAdClicked(strG);
                                    break;
                                case 7:
                                    flurryAdListenerB.onVideoCompleted(strG);
                                    break;
                                case 8:
                                    flurryAdListenerB.onAdOpened(strG);
                                    break;
                                case 9:
                                    flurryAdListenerB.onAdClosed(strG);
                                    break;
                            }
                        }
                    });
                }
            }
        }
    };
    private WeakReference<RelativeLayout> m;
    private boolean n;
    private long o;
    private long p;

    enum a {
        INIT,
        READY,
        DISPLAY,
        NEXT
    }

    /* JADX INFO: renamed from: com.flurry.sdk.u$8, reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] a = new int[d.a.values().length];

        static {
            try {
                a[d.a.kOnFetched.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[d.a.kOnFetchFailed.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[d.a.kOnRendered.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[d.a.kOnRenderFailed.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[d.a.kOnAppExit.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[d.a.kOnClicked.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[d.a.kOnVideoCompleted.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[d.a.kOnOpen.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[d.a.kOnClose.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public u(Context context, ViewGroup viewGroup, String str) {
        i iVarA = i.a();
        if (iVarA == null) {
            throw new IllegalStateException("A session must be started before ad objects may be instantiated.");
        }
        this.b = cr.a();
        this.c = new WeakReference<>(context);
        this.d = new WeakReference<>(viewGroup);
        this.e = str;
        this.f = a.INIT;
        this.g = new ch(str);
        this.m = new WeakReference<>(null);
        iVarA.e().a(context, str, this);
        u();
        w();
    }

    protected void finalize() throws Throwable {
        super.finalize();
        a();
    }

    @Override // com.flurry.sdk.r
    public int d() {
        return this.b;
    }

    @Override // com.flurry.sdk.r
    public void a() {
        v();
        x();
        i.a().e().a(g(), this);
        aa aaVarL = i.a().l();
        if (aaVarL != null) {
            aaVarL.a(this);
        }
        fp.a().a(new hq() { // from class: com.flurry.sdk.u.3
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                u.this.n();
            }
        });
        if (this.g != null) {
            this.g.a();
        }
    }

    @Override // com.flurry.sdk.r
    public void b() {
        v();
    }

    @Override // com.flurry.sdk.r
    public void c() {
        u();
        this.p = this.o;
    }

    @Override // com.flurry.sdk.r
    public Context e() {
        return this.c.get();
    }

    @Override // com.flurry.sdk.r
    public ViewGroup f() {
        return this.d.get();
    }

    @Override // com.flurry.sdk.r
    public String g() {
        return this.e;
    }

    public ch h() {
        return this.g;
    }

    public ci i() {
        return i.a().c().a(g(), cr.b(), l()).a();
    }

    public x j() {
        return i.a().c().a(g(), cr.b(), l()).b();
    }

    @Override // com.flurry.sdk.r
    public ap k() {
        return this.i;
    }

    @Override // com.flurry.sdk.r
    public e l() {
        return j.a().d();
    }

    @Override // com.flurry.sdk.r
    public void a(ap apVar) {
        this.h = apVar;
    }

    @Override // com.flurry.sdk.r
    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            h().b();
            j().a(str);
        }
    }

    @Override // com.flurry.sdk.r
    public void a(ap apVar, long j) {
        if (apVar.d().equals(ax.BANNER)) {
            if (s() != null && s().getChildCount() > 0) {
                a(j);
                return;
            } else {
                h().a(this, i(), j());
                return;
            }
        }
        h().a(this, i(), j());
    }

    @Override // com.flurry.sdk.r
    public void m() {
        this.g.c();
    }

    @Override // com.flurry.sdk.s
    public RelativeLayout s() {
        return this.m.get();
    }

    @Override // com.flurry.sdk.s
    public void a(RelativeLayout relativeLayout) {
        this.m = new WeakReference<>(relativeLayout);
    }

    public void n() {
        RelativeLayout relativeLayout = this.m.get();
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
        this.m.clear();
    }

    public boolean o() {
        boolean zEquals;
        synchronized (this) {
            zEquals = a.READY.equals(this.f);
        }
        return zEquals;
    }

    public void p() {
        this.n = false;
        synchronized (this) {
            if (a.INIT.equals(this.f)) {
                h().a(this, i(), j());
            } else if (a.READY.equals(this.f)) {
                cq.a(this);
            } else if (a.DISPLAY.equals(this.f) || a.NEXT.equals(this.f)) {
                this.f = a.INIT;
                h().a(this, i(), j());
            }
        }
    }

    public void q() {
        synchronized (this) {
            if (a.INIT.equals(this.f) || a.NEXT.equals(this.f)) {
                cq.b(this, av.kNotReady);
            } else if (a.READY.equals(this.f)) {
                fp.a().b(new hq() { // from class: com.flurry.sdk.u.4
                    @Override // com.flurry.sdk.hq
                    public void safeRun() {
                        u.this.A();
                    }
                });
            } else if (a.DISPLAY.equals(this.f)) {
                cq.b(this);
            }
        }
    }

    public boolean r() {
        this.n = true;
        boolean z = false;
        synchronized (this) {
            if (a.INIT.equals(this.f)) {
                h().a(this, i(), j());
            } else if (a.READY.equals(this.f)) {
                fp.a().b(new hq() { // from class: com.flurry.sdk.u.5
                    @Override // com.flurry.sdk.hq
                    public void safeRun() {
                        u.this.A();
                    }
                });
                z = true;
            } else if (a.DISPLAY.equals(this.f) || a.NEXT.equals(this.f)) {
                h().a(this, i(), j());
            }
        }
        return z;
    }

    private void u() {
        this.j = System.currentTimeMillis();
        hk.a().a(this.k);
    }

    private void v() {
        hk.a().b(this.k);
    }

    private void w() {
        fz.a().a("com.flurry.android.impl.ads.AdStateEvent", this.l);
    }

    private void x() {
        fz.a().a(this.l);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        if (this.o > 0) {
            this.p -= System.currentTimeMillis() - this.j;
            if (this.p <= 0) {
                if (C()) {
                    gd.a(3, a, "Rotating banner for adSpace: " + g());
                    h().a(this, i(), j());
                }
                this.p = this.o;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(d dVar) {
        int iB;
        if ((d.a.kOnFetched.equals(dVar.b) || d.a.kOnFetchFailed.equals(dVar.b)) && (iB = j().b()) == 0) {
            gd.a(3, a, "Starting ad request from EnsureCacheNotEmpty size: " + iB);
            i().a(this, j(), (ap) null);
        }
        if (d.a.kOnFetched.equals(dVar.b)) {
            synchronized (this) {
                if (a.INIT.equals(this.f)) {
                    this.f = a.READY;
                } else if (a.DISPLAY.equals(this.f)) {
                    this.f = a.NEXT;
                }
            }
            if (this.n || a.NEXT.equals(this.f)) {
                fp.a().b(new hq() { // from class: com.flurry.sdk.u.6
                    @Override // com.flurry.sdk.hq
                    public void safeRun() {
                        u.this.A();
                    }
                });
            }
        }
    }

    protected void t() {
        hp.b();
        if (!this.h.o() && this.h.n()) {
            gd.a(3, a, "Precaching optional for ad, copying assets before display");
            i.a().l().a(this, this.h);
        }
    }

    private void z() {
        this.i = this.h;
        this.h = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A() {
        boolean zShouldDisplayAd;
        hp.b();
        synchronized (this) {
            if (a.READY.equals(this.f) || a.NEXT.equals(this.f)) {
                if (a.READY.equals(this.f) && this.h != null) {
                    FlurryAdListener flurryAdListenerB = j.a().b();
                    gd.a(3, a, "Firing shouldDisplayAd, listener=" + flurryAdListenerB);
                    if (flurryAdListenerB != null) {
                        try {
                            zShouldDisplayAd = flurryAdListenerB.shouldDisplayAd(this.e, ax.BANNER.equals(this.h.d()) ? FlurryAdType.WEB_BANNER : FlurryAdType.WEB_TAKEOVER);
                        } catch (Throwable th) {
                            gd.a(6, a, "AdListener.shouldDisplayAd", th);
                            zShouldDisplayAd = false;
                        }
                        if (!zShouldDisplayAd) {
                            return;
                        }
                    }
                }
                this.f = a.DISPLAY;
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
                ap apVar = this.h;
                if (apVar == null) {
                    cq.b(this, av.kMissingAdController);
                    return;
                }
                AdUnit adUnitA = apVar.a();
                if (adUnitA == null) {
                    cq.b(this, av.kInvalidAdUnit);
                    return;
                }
                if (adUnitA.combinable == 1) {
                    cq.b(this, av.kInvalidAdUnit);
                    return;
                }
                if (!AdViewType.LEGACY.equals(adUnitA.adViewType)) {
                    cq.b(this, av.kIncorrectClassForAdSpace);
                    return;
                }
                if (!ax.BANNER.equals(apVar.d()) && !ax.TAKEOVER.equals(apVar.d())) {
                    cq.a(this, av.kIncorrectClassForAdSpace);
                } else if (!cr.b().equals(adUnitA.screenOrientation)) {
                    cq.b(this, av.kWrongOrientation);
                } else {
                    t();
                    fp.a().a(new hq() { // from class: com.flurry.sdk.u.7
                        @Override // com.flurry.sdk.hq
                        public void safeRun() {
                            u.this.B();
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        hp.a();
        a(0L);
        z();
        if (ax.BANNER.equals(this.i.d())) {
            eg.a(e(), this);
        } else {
            en enVarA = i.a().q().a(e(), this);
            if (enVarA != null) {
                enVarA.a();
            }
        }
        cq.b(this);
    }

    private boolean C() {
        if (hn.a()) {
            gd.a(3, a, "Device is locked: banner will NOT rotate for adSpace: " + g());
            return false;
        }
        if (this.m.get() == null) {
            gd.a(3, a, "No banner holder: banner will NOT rotate for adSpace: " + g());
            return false;
        }
        return true;
    }

    private void a(long j) {
        gd.a(3, a, "Scheduled banner rotation for adSpace: " + g());
        this.o = j;
        this.p = j;
    }
}
