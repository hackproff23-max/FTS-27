package com.flurry.sdk;

import com.flurry.sdk.gn;
import com.mopub.nativeads.MoPubNativeAdPositioning;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class go<ReportInfo extends gn> {
    private static final String a = go.class.getSimpleName();
    private boolean d;
    private int e;
    private long f;
    private final int b = MoPubNativeAdPositioning.MoPubClientPositioning.NO_REPEAT;
    private final List<ReportInfo> c = new ArrayList();
    private final Runnable g = new hq() { // from class: com.flurry.sdk.go.1
        @Override // com.flurry.sdk.hq
        public void safeRun() {
            go.this.a();
        }
    };
    private final fy<fi> h = new fy<fi>() { // from class: com.flurry.sdk.go.2
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(fi fiVar) {
            if (fiVar.a) {
                go.this.a();
            }
        }
    };

    protected abstract void a(ReportInfo reportinfo);

    protected abstract void a(List<ReportInfo> list);

    protected abstract void b(List<ReportInfo> list);

    public go() {
        fz.a().a("com.flurry.android.sdk.NetworkStateEvent", this.h);
        this.f = 10000L;
        this.e = -1;
        fp.a().b(new hq() { // from class: com.flurry.sdk.go.3
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                go.this.a(go.this.c);
                go.this.a();
            }
        });
    }

    public void b() {
        fp.a().c(this.g);
        h();
    }

    public void c() {
        this.d = true;
    }

    public void d() {
        this.d = false;
        fp.a().b(new hq() { // from class: com.flurry.sdk.go.4
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                go.this.a();
            }
        });
    }

    public synchronized void b(ReportInfo reportinfo) {
        if (reportinfo != null) {
            this.c.add(reportinfo);
            fp.a().b(new hq() { // from class: com.flurry.sdk.go.5
                @Override // com.flurry.sdk.hq
                public void safeRun() {
                    go.this.a();
                }
            });
        }
    }

    protected synchronized void c(ReportInfo reportinfo) {
        reportinfo.a(true);
        fp.a().b(new hq() { // from class: com.flurry.sdk.go.6
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                go.this.e();
            }
        });
    }

    protected synchronized void d(ReportInfo reportinfo) {
        reportinfo.i();
        fp.a().b(new hq() { // from class: com.flurry.sdk.go.7
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                go.this.e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a() {
        if (!this.d) {
            if (this.e >= 0) {
                gd.a(3, a, "Transmit is in progress");
            } else {
                g();
                if (this.c.isEmpty()) {
                    this.f = 10000L;
                    this.e = -1;
                } else {
                    this.e = 0;
                    fp.a().b(new hq() { // from class: com.flurry.sdk.go.8
                        @Override // com.flurry.sdk.hq
                        public void safeRun() {
                            go.this.e();
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:10:0x002f A[Catch: all -> 0x0042, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000f, B:7:0x0019, B:10:0x002f, B:16:0x003e, B:14:0x0035), top: B:21:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:16:0x003e A[Catch: all -> 0x0042, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000f, B:7:0x0019, B:10:0x002f, B:16:0x003e, B:14:0x0035), top: B:21:0x0001 }] */
    public synchronized void e() {
        ReportInfo reportinfo;
        hp.b();
        if (fj.a().c()) {
            do {
                if (this.e < this.c.size()) {
                    List<ReportInfo> list = this.c;
                    int i = this.e;
                    this.e = i + 1;
                    reportinfo = list.get(i);
                }
            } while (reportinfo.e());
            if (reportinfo == null) {
                f();
            } else {
                a(reportinfo);
            }
        } else {
            gd.a(3, a, "Network is not available, aborting transmission");
        }
        reportinfo = null;
        if (reportinfo == null) {
            f();
        } else {
            a(reportinfo);
        }
    }

    private synchronized void f() {
        g();
        b(this.c);
        if (this.d) {
            gd.a(3, a, "Reporter paused");
            this.f = 10000L;
        } else if (this.c.isEmpty()) {
            gd.a(3, a, "All reports sent successfully");
            this.f = 10000L;
        } else {
            this.f <<= 1;
            gd.a(3, a, "One or more reports failed to send, backing off: " + this.f + "ms");
            fp.a().b(this.g, this.f);
        }
        this.e = -1;
    }

    private synchronized void g() {
        Iterator<ReportInfo> it = this.c.iterator();
        while (it.hasNext()) {
            ReportInfo next = it.next();
            if (next.e() || next.f() >= Integer.MAX_VALUE || System.currentTimeMillis() > next.d()) {
                it.remove();
            }
        }
    }

    private void h() {
        fz.a().b("com.flurry.android.sdk.NetworkStateEvent", this.h);
    }
}
