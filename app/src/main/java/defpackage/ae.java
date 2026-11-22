package defpackage;

/* JADX INFO: loaded from: classes.dex */
final class ae extends w {
    public ae(af afVar) {
        super(afVar);
    }

    private void n() {
        ax.a(new Runnable() { // from class: ae.1
            @Override // java.lang.Runnable
            public final void run() {
                ae.this.a.a(new ai(ae.this.a));
            }
        }, 200);
    }

    @Override // defpackage.i
    public final void a() {
        if (this.a.n()) {
            return;
        }
        n();
    }

    @Override // defpackage.i
    public final void d() {
        a(5);
    }

    @Override // defpackage.ag, defpackage.ab
    public final void g() {
        a(3);
    }

    @Override // defpackage.ag, defpackage.ab
    public final void i() {
        a(3);
    }

    @Override // defpackage.ag, defpackage.ab
    public final void k() {
        n();
    }

    @Override // defpackage.ag, defpackage.ab
    public final void l() {
        n();
    }
}
