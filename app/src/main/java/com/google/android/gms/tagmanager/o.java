package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.ld;
import com.google.android.gms.internal.lf;
import com.google.android.gms.internal.pu;
import com.google.android.vending.expansion.downloader.Constants;

/* JADX INFO: loaded from: classes.dex */
class o extends BaseImplementation.AbstractPendingResult<ContainerHolder> {
    private final Looper JF;
    private final d aqB;
    private final cg aqC;
    private final int aqD;
    private f aqE;
    private volatile n aqF;
    private volatile boolean aqG;
    private com.google.android.gms.internal.c.j aqH;
    private String aqI;
    private e aqJ;
    private a aqK;
    private final String aqm;
    private long aqr;
    private final TagManager aqy;
    private final Context mContext;
    private final ld wb;

    interface a {
        boolean b(Container container);
    }

    private class b implements bg<pu.a> {
        private b() {
        }

        @Override // com.google.android.gms.tagmanager.bg
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void l(pu.a aVar) {
            com.google.android.gms.internal.c.j jVar;
            if (aVar.auC != null) {
                jVar = aVar.auC;
            } else {
                com.google.android.gms.internal.c.f fVar = aVar.gs;
                jVar = new com.google.android.gms.internal.c.j();
                jVar.gs = fVar;
                jVar.gr = null;
                jVar.gt = fVar.version;
            }
            o.this.a(jVar, aVar.auB, true);
        }

        @Override // com.google.android.gms.tagmanager.bg
        public void a(bg.a aVar) {
            if (o.this.aqG) {
                return;
            }
            o.this.w(0L);
        }

        @Override // com.google.android.gms.tagmanager.bg
        public void ps() {
        }
    }

    private class c implements bg<com.google.android.gms.internal.c.j> {
        private c() {
        }

        @Override // com.google.android.gms.tagmanager.bg
        public void a(bg.a aVar) {
            if (o.this.aqF != null) {
                o.this.b(o.this.aqF);
            } else {
                o.this.b(o.this.c(Status.Kz));
            }
            o.this.w(3600000L);
        }

        @Override // com.google.android.gms.tagmanager.bg
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void l(com.google.android.gms.internal.c.j jVar) {
            synchronized (o.this) {
                if (jVar.gs == null) {
                    if (o.this.aqH.gs == null) {
                        bh.T("Current resource is null; network resource is also null");
                        o.this.w(3600000L);
                        return;
                    }
                    jVar.gs = o.this.aqH.gs;
                }
                o.this.a(jVar, o.this.wb.currentTimeMillis(), false);
                bh.V("setting refresh time to current time: " + o.this.aqr);
                if (!o.this.pr()) {
                    o.this.a(jVar);
                }
            }
        }

        @Override // com.google.android.gms.tagmanager.bg
        public void ps() {
        }
    }

    private class d implements n.a {
        private d() {
        }

        @Override // com.google.android.gms.tagmanager.n.a
        public void ct(String str) {
            o.this.ct(str);
        }

        @Override // com.google.android.gms.tagmanager.n.a
        public String pl() {
            return o.this.pl();
        }

        @Override // com.google.android.gms.tagmanager.n.a
        public void pn() {
            if (o.this.aqC.fe()) {
                o.this.w(0L);
            }
        }
    }

    interface e extends Releasable {
        void a(bg<com.google.android.gms.internal.c.j> bgVar);

        void cw(String str);

        void e(long j, String str);
    }

    interface f extends Releasable {
        void a(bg<pu.a> bgVar);

        void b(pu.a aVar);

        cr.c fH(int i);

        void pt();
    }

    o(Context context, TagManager tagManager, Looper looper, String str, int i, f fVar, e eVar, ld ldVar, cg cgVar) {
        super(looper == null ? Looper.getMainLooper() : looper);
        this.mContext = context;
        this.aqy = tagManager;
        this.JF = looper == null ? Looper.getMainLooper() : looper;
        this.aqm = str;
        this.aqD = i;
        this.aqE = fVar;
        this.aqJ = eVar;
        this.aqB = new d();
        this.aqH = new com.google.android.gms.internal.c.j();
        this.wb = ldVar;
        this.aqC = cgVar;
        if (pr()) {
            ct(ce.qa().qc());
        }
    }

    public o(Context context, TagManager tagManager, Looper looper, String str, int i, r rVar) {
        this(context, tagManager, looper, str, i, new cq(context, str), new cp(context, str, rVar), lf.m6if(), new bf(30, 900000L, Constants.ACTIVE_THREAD_WATCHDOG, "refreshing", lf.m6if()));
    }

    private void V(final boolean z) {
        this.aqE.a(new b());
        this.aqJ.a(new c());
        cr.c cVarFH = this.aqE.fH(this.aqD);
        if (cVarFH != null) {
            this.aqF = new n(this.aqy, this.JF, new Container(this.mContext, this.aqy.getDataLayer(), this.aqm, 0L, cVarFH), this.aqB);
        }
        this.aqK = new a() { // from class: com.google.android.gms.tagmanager.o.2
            @Override // com.google.android.gms.tagmanager.o.a
            public boolean b(Container container) {
                if (z) {
                    return container.getLastRefreshTime() + 43200000 >= o.this.wb.currentTimeMillis();
                }
                return !container.isDefault();
            }
        };
        if (pr()) {
            this.aqJ.e(0L, "");
        } else {
            this.aqE.pt();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(com.google.android.gms.internal.c.j jVar) {
        if (this.aqE != null) {
            pu.a aVar = new pu.a();
            aVar.auB = this.aqr;
            aVar.gs = new com.google.android.gms.internal.c.f();
            aVar.auC = jVar;
            this.aqE.b(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(com.google.android.gms.internal.c.j jVar, long j, boolean z) {
        if (z) {
            if (!this.aqG) {
            }
        }
        if (!isReady() || this.aqF == null) {
        }
        this.aqH = jVar;
        this.aqr = j;
        w(Math.max(0L, Math.min(43200000L, (this.aqr + 43200000) - this.wb.currentTimeMillis())));
        Container container = new Container(this.mContext, this.aqy.getDataLayer(), this.aqm, j, jVar);
        if (this.aqF == null) {
            this.aqF = new n(this.aqy, this.JF, container, this.aqB);
        } else {
            this.aqF.a(container);
        }
        if (!isReady() && this.aqK.b(container)) {
            b(this.aqF);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean pr() {
        ce ceVarQa = ce.qa();
        return (ceVarQa.qb() == ce.a.CONTAINER || ceVarQa.qb() == ce.a.CONTAINER_DEBUG) && this.aqm.equals(ceVarQa.getContainerId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void w(long j) {
        if (this.aqJ == null) {
            bh.W("Refresh requested, but no network load scheduler.");
        } else {
            this.aqJ.e(j, this.aqH.gt);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
    /* JADX INFO: renamed from: aD, reason: merged with bridge method [inline-methods] */
    public ContainerHolder c(Status status) {
        if (this.aqF != null) {
            return this.aqF;
        }
        if (status == Status.Kz) {
            bh.T("timer expired: setting result to failure");
        }
        return new n(status);
    }

    synchronized void ct(String str) {
        this.aqI = str;
        if (this.aqJ != null) {
            this.aqJ.cw(str);
        }
    }

    synchronized String pl() {
        return this.aqI;
    }

    public void po() {
        cr.c cVarFH = this.aqE.fH(this.aqD);
        if (cVarFH != null) {
            b(new n(this.aqy, this.JF, new Container(this.mContext, this.aqy.getDataLayer(), this.aqm, 0L, cVarFH), new n.a() { // from class: com.google.android.gms.tagmanager.o.1
                @Override // com.google.android.gms.tagmanager.n.a
                public void ct(String str) {
                    o.this.ct(str);
                }

                @Override // com.google.android.gms.tagmanager.n.a
                public String pl() {
                    return o.this.pl();
                }

                @Override // com.google.android.gms.tagmanager.n.a
                public void pn() {
                    bh.W("Refresh ignored: container loaded as default only.");
                }
            }));
        } else {
            bh.T("Default was requested, but no default container was found");
            b(c(new Status(10, "Default was requested, but no default container was found", null)));
        }
        this.aqJ = null;
        this.aqE = null;
    }

    public void pp() {
        V(false);
    }

    public void pq() {
        V(true);
    }
}
