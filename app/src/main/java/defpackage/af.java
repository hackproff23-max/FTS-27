package defpackage;

import com.nuance.nmdp.speechkit.recognitionresult.b;

/* JADX INFO: loaded from: classes.dex */
public abstract class af extends j implements z {
    private final ac e;
    private final bb f;
    private be g;
    private final bd h;
    private final bd i;
    private final bd j;
    private final bd k;
    private final boolean l;
    private final boolean m;
    private final bf<?> n;
    private final az o;

    public af(df dfVar, k kVar, boolean z, boolean z2, String str, ba baVar, ba baVar2, ba baVar3, ba baVar4, bf<?> bfVar, ac acVar, aa aaVar) {
        super(dfVar, kVar, str, aaVar);
        this.n = bfVar;
        this.e = acVar;
        this.f = new bb() { // from class: af.1
            @Override // defpackage.bb
            public final void a() {
                ax.a(new Runnable() { // from class: af.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        af.b(af.this).h();
                    }
                });
            }

            @Override // defpackage.bb
            public final void a(float f) {
                af.this.e.a(f);
            }

            @Override // defpackage.bb
            public final void a(final int i) {
                ax.a(new Runnable() { // from class: af.1.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (i != 4) {
                            af.b(af.this).i();
                        } else {
                            b.c(af.this, "Recorder error");
                            af.b(af.this).g();
                        }
                    }
                });
            }
        };
        this.o = new az() { // from class: af.2
            @Override // defpackage.az
            public final void a(Object obj) {
                b.c(af.this, "Prompt error");
                ax.a(new Runnable() { // from class: af.2.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        af.b(af.this).k();
                    }
                });
            }

            @Override // defpackage.az
            public final void b(Object obj) {
            }

            @Override // defpackage.az
            public final void c(Object obj) {
                ax.a(new Runnable() { // from class: af.2.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        af.b(af.this).l();
                    }
                });
            }
        };
        this.g = null;
        this.l = z;
        this.m = z2;
        this.h = baVar == null ? null : new bd(baVar, kVar.a(), this, this.o);
        this.i = baVar2 == null ? null : new bd(baVar2, kVar.a(), this, this.o);
        this.j = baVar3 == null ? null : new bd(baVar3, kVar.a(), this, this.o);
        this.k = baVar4 == null ? null : new bd(baVar4, kVar.a(), this, this.o);
        this.a = new ad(this);
    }

    static /* synthetic */ ab b(af afVar) {
        return (ab) afVar.a;
    }

    @Override // defpackage.z
    public final void a_() {
        ((ab) this.a).j();
    }

    final bf<?> i() {
        return this.n;
    }

    protected abstract void j() throws l;

    protected abstract String k();

    protected abstract String l();

    protected abstract boolean m();

    final boolean n() {
        if (this.h == null) {
            return false;
        }
        this.h.a();
        return true;
    }

    final void o() {
        if (this.h != null) {
            this.h.b();
        }
    }

    final void p() {
        if (this.i != null) {
            this.i.a();
        }
    }

    final void q() {
        if (this.j != null) {
            this.j.a();
        }
    }

    final void r() {
        if (this.k != null) {
            this.k.a();
        }
    }

    public final void s() {
        this.g = new be(this.c, this.l, this.m, this, this.d.a(), this.f);
    }

    public final void t() {
        this.g.a();
    }

    public final void u() throws l {
        this.g.a(b(l()));
    }

    public final void v() {
        if (this.g != null) {
            this.g.b();
            this.g = null;
        }
    }
}
