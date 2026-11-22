package com.flurry.sdk;

import android.text.TextUtils;
import com.facebook.internal.ServerProtocol;
import com.flurry.android.impl.ads.protocol.v13.AdFrame;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public class ch {
    private static final String a = ch.class.getSimpleName();
    private final String b;
    private final ci c;
    private final TreeSet<ap> d;
    private final TreeSet<ap> e;
    private a f;
    private r g;
    private ci h;
    private x i;
    private ap j;
    private ap k;
    private int l;
    private long m;
    private long n;
    private long o;
    private long p;
    private long q;
    private final fy<hj> r = new fy<hj>() { // from class: com.flurry.sdk.ch.1
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(hj hjVar) {
            if (!a.REQUEST.equals(ch.this.f)) {
                if (!a.CSRTB_AWAIT_AUCTION.equals(ch.this.f)) {
                    if (!a.SELECT.equals(ch.this.f)) {
                        if (a.PRERENDER.equals(ch.this.f)) {
                            fp.a().b(new hq() { // from class: com.flurry.sdk.ch.1.4
                                @Override // com.flurry.sdk.hq
                                public void safeRun() {
                                    ch.this.j();
                                }
                            });
                            return;
                        }
                        return;
                    }
                    fp.a().b(new hq() { // from class: com.flurry.sdk.ch.1.3
                        @Override // com.flurry.sdk.hq
                        public void safeRun() {
                            ch.this.i();
                        }
                    });
                    return;
                }
                fp.a().b(new hq() { // from class: com.flurry.sdk.ch.1.2
                    @Override // com.flurry.sdk.hq
                    public void safeRun() {
                        ch.this.h();
                    }
                });
                return;
            }
            fp.a().b(new hq() { // from class: com.flurry.sdk.ch.1.1
                @Override // com.flurry.sdk.hq
                public void safeRun() {
                    ch.this.g();
                }
            });
        }
    };
    private final fy<ad> s = new fy<ad>() { // from class: com.flurry.sdk.ch.4
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(final ad adVar) {
            fp.a().b(new hq() { // from class: com.flurry.sdk.ch.4.1
                @Override // com.flurry.sdk.hq
                public void safeRun() {
                    ch.this.a(adVar.a, adVar.b);
                }
            });
        }
    };
    private final fy<cj> t = new fy<cj>() { // from class: com.flurry.sdk.ch.5
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(final cj cjVar) {
            if (ch.this.h != cjVar.a) {
                if (ch.this.c == cjVar.a) {
                    fp.a().b(new hq() { // from class: com.flurry.sdk.ch.5.2
                        @Override // com.flurry.sdk.hq
                        public void safeRun() {
                            ch.this.a(cjVar.c);
                        }
                    });
                    return;
                }
                return;
            }
            fp.a().b(new hq() { // from class: com.flurry.sdk.ch.5.1
                @Override // com.flurry.sdk.hq
                public void safeRun() {
                    ch.this.f();
                }
            });
        }
    };

    enum a {
        NONE,
        REQUEST,
        CSRTB_AUCTION_REQUIRED,
        CSRTB_AWAIT_AUCTION,
        SELECT,
        PREPARE,
        PRERENDER
    }

    public ch(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("adSpace cannot be null");
        }
        this.b = str;
        this.c = new ci(str);
        this.d = new TreeSet<>();
        this.e = new TreeSet<>();
        this.f = a.NONE;
        e();
    }

    public synchronized void a() {
        e();
        this.c.a();
        this.d.clear();
    }

    public synchronized void a(r rVar, ci ciVar, x xVar) {
        if (rVar != null && ciVar != null && xVar != null) {
            gd.a(3, a, "fetchAd: adObject=" + rVar);
            if (a.NONE.equals(this.f)) {
                this.g = rVar;
                this.i = xVar;
                this.h = ciVar;
                if (!fj.a().c()) {
                    gd.a(5, a, "There is no network connectivity (ad will not fetch)");
                    cq.a(this.g, av.kNoNetworkConnectivity);
                    e();
                } else {
                    i.a().l().g();
                    if (this.d.isEmpty()) {
                        this.d.addAll(this.i.c());
                    }
                    if (this.d.isEmpty()) {
                        a(a.REQUEST);
                        if (15000 > 0) {
                            gd.a(3, a, "Setting ad request timeout for 15000 ms");
                            this.m = 15000 + System.currentTimeMillis();
                        }
                        this.h.a(this.g, this.i, (ap) null);
                    } else {
                        this.j = this.d.pollFirst();
                        a(a.SELECT);
                        fp.a().b(new hq() { // from class: com.flurry.sdk.ch.6
                            @Override // com.flurry.sdk.hq
                            public void safeRun() {
                                ch.this.l();
                            }
                        });
                    }
                }
            }
        }
    }

    public synchronized void b() {
        this.d.clear();
    }

    public synchronized void c() {
        if (this.h != null) {
            this.h.b();
        }
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void e() {
        gd.a(3, a, "Fetch finished for adObject:" + this.g + " adSpace:" + this.b);
        this.c.b();
        fn.a().a(this);
        a(a.NONE);
        if (this.i != null) {
            this.i.a(this.e);
        }
        this.e.clear();
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = 0;
        this.m = 0L;
        this.n = 0L;
        this.o = 0L;
        this.p = 0L;
        this.q = 0L;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0086 A[Catch: all -> 0x00cf, TRY_ENTER, TryCatch #0 {, blocks: (B:4:0x0003, B:5:0x0005, B:7:0x0040, B:9:0x0048, B:10:0x0082, B:13:0x0086, B:15:0x008e, B:17:0x0098), top: B:22:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:15:0x008e A[Catch: all -> 0x00cf, TryCatch #0 {, blocks: (B:4:0x0003, B:5:0x0005, B:7:0x0040, B:9:0x0048, B:10:0x0082, B:13:0x0086, B:15:0x008e, B:17:0x0098), top: B:22:0x0003 }] */
    private synchronized void a(a aVar) {
        if (aVar == null) {
            aVar = a.NONE;
            gd.a(3, a, "Setting state from " + this.f + " to " + aVar + " for adspace: " + this.b);
            if (!a.NONE.equals(this.f) && !a.NONE.equals(aVar)) {
                gd.a(3, a, "Adding fetch listeners for adspace: " + this.b);
                hk.a().a(this.r);
                fz.a().a("com.flurry.android.sdk.AssetStatusEvent", this.s);
                fz.a().a("com.flurry.android.sdk.AdResponseEvent", this.t);
            } else if (a.NONE.equals(aVar) && !a.NONE.equals(this.f)) {
                gd.a(3, a, "Removing fetch listeners for adspace: " + this.b);
                hk.a().b(this.r);
                fz.a().a(this.s);
                fz.a().a(this.t);
            }
            this.f = aVar;
        } else {
            gd.a(3, a, "Setting state from " + this.f + " to " + aVar + " for adspace: " + this.b);
            if (!a.NONE.equals(this.f)) {
                if (a.NONE.equals(aVar)) {
                    gd.a(3, a, "Removing fetch listeners for adspace: " + this.b);
                    hk.a().b(this.r);
                    fz.a().a(this.s);
                    fz.a().a(this.t);
                }
            } else if (a.NONE.equals(aVar)) {
                gd.a(3, a, "Removing fetch listeners for adspace: " + this.b);
                hk.a().b(this.r);
                fz.a().a(this.s);
                fz.a().a(this.t);
            }
            this.f = aVar;
        }
        throw th;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void f() {
        if (a.REQUEST.equals(this.f)) {
            this.d.addAll(this.i.c());
            if (!this.d.isEmpty()) {
                this.j = this.d.pollFirst();
            }
            a(a.SELECT);
            fp.a().b(new hq() { // from class: com.flurry.sdk.ch.7
                @Override // com.flurry.sdk.hq
                public void safeRun() {
                    ch.this.l();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(List<ap> list) {
        List<AdFrame> list2;
        Map<String, String> map;
        if (a.CSRTB_AWAIT_AUCTION.equals(this.f)) {
            if (list == null || list.isEmpty() || list.size() > 1) {
                n();
            } else {
                ap apVar = list.get(0);
                if (!apVar.a().renderTime || (list2 = apVar.a().adFrames) == null || list2.isEmpty() || list2.get(0).binding == 6) {
                    n();
                } else {
                    String str = null;
                    if (apVar.a() != null && (map = apVar.a().clientSideRtbPayload) != null && map.containsKey("GROUP_ID")) {
                        str = map.get("GROUP_ID");
                    }
                    if (str == null) {
                        List<AdFrame> list3 = apVar.a().adFrames;
                        List<AdFrame> list4 = this.k.a().adFrames;
                        list4.clear();
                        list4.addAll(list3);
                        apVar.a().adFrames = list4;
                        apVar.a().groupId = this.k.a().groupId;
                        if (apVar.a().clientSideRtbPayload != null && apVar.a().clientSideRtbPayload.isEmpty()) {
                            apVar.a().clientSideRtbPayload = this.k.a().clientSideRtbPayload;
                        }
                        this.j = apVar;
                    } else {
                        this.j = apVar;
                    }
                    a(a.SELECT);
                    fp.a().b(new hq() { // from class: com.flurry.sdk.ch.8
                        @Override // com.flurry.sdk.hq
                        public void safeRun() {
                            ch.this.l();
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void g() {
        if (this.m > 0 && System.currentTimeMillis() > this.m) {
            cq.a(this.g, av.kUnfilled);
            e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void h() {
        if (this.n > 0 && System.currentTimeMillis() > this.n) {
            n();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void i() {
        if (this.p > 0 && System.currentTimeMillis() > this.p) {
            a(this.j, av.kVASTResolveTimeout);
            e();
        } else if (this.o > 0 && System.currentTimeMillis() > this.o) {
            m();
            l();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void j() {
        if (this.q > 0 && System.currentTimeMillis() > this.q) {
            fn.a().a(this);
            a(this.j, av.kPrerenderDownloadTimeout);
            e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(String str, ah ahVar) {
        if (a.SELECT.equals(this.f) && i.a().l().a(this.j, str)) {
            gd.a(3, a, "Detected asset status change for asset:" + str + " status:" + ahVar);
            if (ah.COMPLETE.equals(ahVar) || ah.ERROR.equals(ahVar)) {
                fp.a().b(new hq() { // from class: com.flurry.sdk.ch.9
                    @Override // com.flurry.sdk.hq
                    public void safeRun() {
                        ch.this.l();
                    }
                });
            }
        }
    }

    private synchronized void k() {
        if (a.CSRTB_AUCTION_REQUIRED.equals(this.f)) {
            if (this.j == null) {
                gd.a(6, a, "An auction is required, but there is no ad unit!");
                cq.a(this.g, av.kMissingAdController);
                e();
            } else {
                a(a.CSRTB_AWAIT_AUCTION);
                long j = this.j.a().preRenderTimeoutMillis;
                if (j > 0) {
                    gd.a(3, a, "Setting CSRTB auction timeout for " + j + " ms");
                    this.n = j + System.currentTimeMillis();
                }
                this.k = this.j;
                this.c.a(this.g, (x) null, this.j);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void l() {
        if (a.SELECT.equals(this.f)) {
            if (this.g.e() == null) {
                cq.a(this.g, av.kNoContext);
                e();
            } else if (hn.a()) {
                cq.a(this.g, av.kDeviceLocked);
                e();
            } else {
                aa aaVarL = i.a().l();
                while (this.j != null) {
                    List<AdFrame> list = this.j.a().adFrames;
                    if (list == null || list.isEmpty()) {
                        a(this.j, av.kInvalidAdUnit);
                        e();
                    } else {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).binding == 6) {
                                this.j.a().renderTime = true;
                                a(a.CSRTB_AUCTION_REQUIRED);
                                k();
                            } else {
                                cy cyVarD = this.j.d(i);
                                if (cyVarD != null && !cyVarD.c()) {
                                    if (cyVarD.d()) {
                                        break;
                                    }
                                    long j = this.j.a().preRenderTimeoutMillis;
                                    if (j > 0 && this.p == 0) {
                                        gd.a(3, a, "Setting VAST resolve timeout for " + j + " ms");
                                        this.p = j + System.currentTimeMillis();
                                    }
                                    a(this.j, i, cyVarD);
                                }
                            }
                        }
                        this.p = 0L;
                        if (this.j.o()) {
                            af afVarA = aaVarL.a(this.j);
                            long j2 = this.j.a().preCacheAdSkippableTimeLimitMillis;
                            gd.a(3, a, "Pre-caching required for ad, AdUnitCachedStatus: " + afVarA + ", skip time limit: " + j2);
                            if (!af.COMPLETE.equals(afVarA) && j2 > 0 && this.o == 0) {
                                gd.a(3, a, "Setting skip timer for " + j2 + " ms");
                                this.o = System.currentTimeMillis() + j2;
                            }
                            if (af.COMPLETE.equals(afVarA)) {
                                gd.a(3, a, "Pre-caching completed, ad may proceed");
                                break;
                            }
                            if (!af.IN_PROGRESS.equals(afVarA)) {
                                if (j2 == 0) {
                                    gd.a(3, a, "No skip timer");
                                    m();
                                } else {
                                    int i2 = this.l + 1;
                                    this.l = i2;
                                    if (i2 > 1) {
                                        gd.a(3, a, "Too many precaching attempts, precaching failed");
                                        a(this.j, av.kPrecachingDownloadFailed);
                                        e();
                                    } else {
                                        int iB = aaVarL.b(this.j);
                                        if (iB > 0) {
                                            gd.a(3, a, "Requesting " + iB + " asset(s), attempt #" + this.l);
                                        } else {
                                            gd.a(3, a, "No assets to cache");
                                            break;
                                        }
                                    }
                                }
                            } else if (j2 == 0) {
                                gd.a(3, a, "No skip timer");
                                m();
                            } else if (j2 <= 0) {
                                continue;
                            } else if (System.currentTimeMillis() > this.o) {
                                gd.a(3, a, "Skip timer expired");
                                m();
                            } else {
                                gd.a(3, a, "Waiting for skip timer");
                            }
                        } else {
                            gd.a(3, a, "Pre-caching not required for ad");
                            break;
                        }
                    }
                }
                if (this.j == null) {
                    i.a().a(null, aw.EV_UNFILLED, true, Collections.emptyMap());
                    cq.a(this.g, av.kUnfilled);
                    e();
                } else {
                    a(a.PREPARE);
                    fp.a().a(new hq() { // from class: com.flurry.sdk.ch.10
                        @Override // com.flurry.sdk.hq
                        public void safeRun() {
                            ch.this.o();
                        }
                    });
                }
            }
        }
    }

    private synchronized void a(final ap apVar, final int i, final cy cyVar) {
        final String strE = cyVar.e();
        gk gkVar = new gk();
        gkVar.a(strE);
        gkVar.a(20000);
        gkVar.b(new gy());
        gkVar.a((gk.a) new gk.a<Void, String>() { // from class: com.flurry.sdk.ch.11
            @Override // com.flurry.sdk.gk.a
            public void a(gk<Void, String> gkVar2, String str) {
                gd.a(3, ch.a, "VAST resolver: HTTP status code is:" + gkVar2.e() + " for url: " + strE);
                cy cyVarA = null;
                if (gkVar2.c()) {
                    gd.a(3, ch.a, "VAST resolver response:" + str + " for url: " + strE);
                    cyVarA = cy.a(cyVar, da.a(str));
                }
                if (cyVarA == null) {
                    gd.a(3, ch.a, "VAST resolver failed for frame: " + i);
                    apVar.a(i, new cy.a().a().b());
                } else {
                    gd.a(3, ch.a, "VAST resolver successful for frame: " + i);
                    apVar.a(i, cyVarA);
                }
                fp.a().b(new hq() { // from class: com.flurry.sdk.ch.11.1
                    @Override // com.flurry.sdk.hq
                    public void safeRun() {
                        ch.this.l();
                    }
                });
            }
        });
        fn.a().a(this, gkVar);
    }

    private synchronized void m() {
        if (a.SELECT.equals(this.f)) {
            gd.a(3, a, "Precaching required for incomplete ad unit, skipping ad group -- adspace: " + this.b + " groupId: " + ((Object) this.j.a().groupId));
            this.e.add(this.j);
            this.j = null;
            this.e.addAll(this.d);
            this.d.clear();
            this.d.addAll(this.i.c());
            if (!this.d.isEmpty()) {
                this.j = this.d.pollFirst();
            }
            f.a().a("precachingAdGroupSkipped", 1);
            this.l = 0;
            this.o = 0L;
        }
    }

    private synchronized void n() {
        boolean z;
        if (a.CSRTB_AUCTION_REQUIRED.equals(this.f) || a.CSRTB_AWAIT_AUCTION.equals(this.f)) {
            Iterator<com.flurry.sdk.a> it = cr.a(this.j.a().adFrames.get(0), new b(aw.EV_UNFILLED, null, null, null, null)).iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = true;
                    break;
                }
                if (au.AC_NEXT_AD_UNIT.equals(it.next().a())) {
                    z = false;
                    break;
                }
            }
            co.a(aw.EV_UNFILLED, Collections.emptyMap(), this.g.e(), this.g, this.j, 0);
            if (z) {
                a(this.j, av.kCSRTBAuctionTimeout);
            }
            e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void o() {
        hp.a();
        if (a.PREPARE.equals(this.f)) {
            gd.a(3, a, "Preparing ad");
            if (this.g.e() == null) {
                a(this.j, av.kNoContext);
                e();
            } else {
                co.a(aw.EV_FILLED, Collections.emptyMap(), this.g.e(), this.g, this.j, 1);
                this.g.a(this.j);
                a(a.PRERENDER);
                fp.a().b(new hq() { // from class: com.flurry.sdk.ch.2
                    @Override // com.flurry.sdk.hq
                    public void safeRun() {
                        ch.this.p();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void p() {
        synchronized (this) {
            if (a.PRERENDER.equals(this.f)) {
                gd.a(3, a, "Pre-rendering ad");
                List<AdFrame> list = this.j.a().adFrames;
                for (int i = 0; i < list.size(); i++) {
                    cy cyVarD = this.j.d(i);
                    if (cyVarD != null && (!cyVarD.c() || cyVarD.d())) {
                        a(this.j, av.kInvalidVASTAd);
                        e();
                    }
                }
                aa aaVarL = i.a().l();
                if (this.j.o()) {
                    gd.a(3, a, "Precaching required for ad, copying assets");
                    if (af.COMPLETE.equals(aaVarL.a(this.j))) {
                        f.a().a("precachingAdAssetsAvailable", 1);
                        if (!i.a().l().a(this.g, this.j)) {
                            gd.a(3, a, "Could not copy required ad assets");
                            f.a().a("precachingAdAssetCopyFailed", 1);
                            a(this.j, av.kPrecachingCopyFailed);
                            e();
                        }
                    } else {
                        gd.a(3, a, "Ad assets incomplete");
                        f.a().a("precachingAdAssetsIncomplete", 1);
                        a(this.j, av.kPrecachingMissingAssets);
                        e();
                    }
                } else if (this.j.n()) {
                    gd.a(3, a, "Precaching optional for ad, copying assets");
                    i.a().l().a(this.g, this.j);
                }
                co.a(aw.EV_PREPARED, Collections.emptyMap(), this.g.e(), this.g, this.j, 0);
                AdFrame adFrame = list.get(0);
                if (adFrame.binding == 1) {
                    gd.a(3, a, "Binding is HTML_URL, pre-render required");
                    long j = this.j.a().preRenderTimeoutMillis;
                    if (j > 0) {
                        gd.a(3, a, "Setting pre-render timeout for " + j + " ms");
                        this.q = j + System.currentTimeMillis();
                    }
                    a(this.j, adFrame.display);
                } else {
                    cq.a(this.g);
                    e();
                }
            }
        }
    }

    private synchronized void a(final ap apVar, final String str) {
        gd.a(3, a, "Pre-render: HTTP get for url: " + str);
        gk gkVar = new gk();
        gkVar.a(str);
        gkVar.a(20000);
        gkVar.b(new gy());
        gkVar.a((gk.a) new gk.a<Void, String>() { // from class: com.flurry.sdk.ch.3
            @Override // com.flurry.sdk.gk.a
            public void a(gk<Void, String> gkVar2, String str2) {
                gd.a(3, ch.a, "Prerender: HTTP status code is:" + gkVar2.e() + " for url: " + str);
                if (!gkVar2.c()) {
                    ch.this.a(apVar, av.kPrerenderDownloadFailed);
                    ch.this.e();
                } else {
                    apVar.b(str2);
                    cq.a(ch.this.g);
                    ch.this.e();
                }
            }
        });
        fn.a().a(this, gkVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(ap apVar, av avVar) {
        HashMap map = new HashMap();
        map.put("preRender", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        if (avVar == null) {
            avVar = av.kUnknown;
        }
        map.put("errorCode", Integer.toString(avVar.a()));
        co.a(aw.EV_RENDER_FAILED, map, this.g.e(), this.g, apVar, 0);
    }
}
