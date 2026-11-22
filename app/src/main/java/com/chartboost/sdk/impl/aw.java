package com.chartboost.sdk.impl;

import com.chartboost.sdk.Model.CBError;

/* JADX INFO: loaded from: classes.dex */
public class aw extends com.chartboost.sdk.d {
    private static aw c;
    protected int b;
    private com.chartboost.sdk.Model.a d = null;
    private boolean e;
    private boolean f;

    private aw() {
    }

    public static aw f() {
        if (c == null) {
            synchronized (aw.class) {
                if (c == null) {
                    c = new aw();
                }
            }
        }
        return c;
    }

    @Override // com.chartboost.sdk.d
    public void a(String str) {
        this.b = 0;
        g();
        super.a(str);
    }

    @Override // com.chartboost.sdk.d
    protected void a(com.chartboost.sdk.Model.a aVar, com.chartboost.sdk.Libraries.e.a aVar2) {
        if (!this.e && this.f) {
            this.f = false;
            this.b = aVar2.a("cells").o();
        }
        super.a(aVar, aVar2);
    }

    @Override // com.chartboost.sdk.d
    protected com.chartboost.sdk.Model.a a(String str, boolean z) {
        return new com.chartboost.sdk.Model.a(com.chartboost.sdk.Model.a.c.MORE_APPS, z, str, false);
    }

    @Override // com.chartboost.sdk.d
    protected ba e(com.chartboost.sdk.Model.a aVar) {
        ba baVar = new ba("/more/get");
        baVar.a(l.a.HIGH);
        baVar.a(com.chartboost.sdk.Model.b.c);
        return baVar;
    }

    @Override // com.chartboost.sdk.d
    protected ba l(com.chartboost.sdk.Model.a aVar) {
        ba baVar = new ba("/more/show");
        if (aVar.d != null) {
            baVar.a("location", (Object) aVar.d);
        }
        if (aVar.w().c("cells")) {
            baVar.a("cells", (Object) aVar.w().a("cells"));
        }
        return baVar;
    }

    @Override // com.chartboost.sdk.d
    public void a() {
        this.d = null;
    }

    @Override // com.chartboost.sdk.d
    protected com.chartboost.sdk.Model.a d(String str) {
        return this.d;
    }

    @Override // com.chartboost.sdk.d
    protected void e(String str) {
        this.d = null;
    }

    @Override // com.chartboost.sdk.d
    protected void q(com.chartboost.sdk.Model.a aVar) {
        this.d = aVar;
    }

    @Override // com.chartboost.sdk.d
    protected com.chartboost.sdk.d.a c() {
        return new com.chartboost.sdk.d.a() { // from class: com.chartboost.sdk.impl.aw.1
            @Override // com.chartboost.sdk.d.a
            public void a(com.chartboost.sdk.Model.a aVar) {
                if (com.chartboost.sdk.b.d() != null) {
                    com.chartboost.sdk.b.d().didClickMoreApps(aVar.d);
                }
            }

            @Override // com.chartboost.sdk.d.a
            public void b(com.chartboost.sdk.Model.a aVar) {
                if (com.chartboost.sdk.b.d() != null) {
                    com.chartboost.sdk.b.d().didCloseMoreApps(aVar.d);
                }
            }

            @Override // com.chartboost.sdk.d.a
            public void c(com.chartboost.sdk.Model.a aVar) {
                if (com.chartboost.sdk.b.d() != null) {
                    com.chartboost.sdk.b.d().didDismissMoreApps(aVar.d);
                }
            }

            @Override // com.chartboost.sdk.d.a
            public void d(com.chartboost.sdk.Model.a aVar) {
                if (com.chartboost.sdk.b.d() != null) {
                    com.chartboost.sdk.b.d().didCacheMoreApps(aVar.d);
                }
            }

            @Override // com.chartboost.sdk.d.a
            public void a(com.chartboost.sdk.Model.a aVar, CBError.CBImpressionError cBImpressionError) {
                if (com.chartboost.sdk.b.d() != null) {
                    com.chartboost.sdk.b.d().didFailToLoadMoreApps(aVar.d, cBImpressionError);
                }
            }

            @Override // com.chartboost.sdk.d.a
            public void e(com.chartboost.sdk.Model.a aVar) {
                aw.this.b = 0;
                aw.this.g();
                if (com.chartboost.sdk.b.d() != null) {
                    com.chartboost.sdk.b.d().didDisplayMoreApps(aVar.d);
                }
            }

            @Override // com.chartboost.sdk.d.a
            public boolean f(com.chartboost.sdk.Model.a aVar) {
                if (com.chartboost.sdk.b.d() != null) {
                    return com.chartboost.sdk.b.d().shouldDisplayMoreApps(aVar.d);
                }
                return true;
            }

            @Override // com.chartboost.sdk.d.a
            public boolean g(com.chartboost.sdk.Model.a aVar) {
                if (com.chartboost.sdk.b.d() != null) {
                    return com.chartboost.sdk.b.d().shouldRequestMoreApps(aVar.d);
                }
                return true;
            }

            @Override // com.chartboost.sdk.d.a
            public boolean h(com.chartboost.sdk.Model.a aVar) {
                return true;
            }
        };
    }

    protected void g() {
    }

    @Override // com.chartboost.sdk.d
    public String e() {
        return "more-apps";
    }
}
