package defpackage;

import com.nuance.nmdp.speechkit.recognitionresult.b;

/* JADX INFO: loaded from: classes.dex */
final class ah extends w {
    public ah(af afVar) {
        super(afVar);
    }

    @Override // defpackage.i
    public final void a() {
        try {
            this.a.d();
            this.a.a(this.a.k());
            this.a.j();
            this.a.s();
            this.a.t();
        } catch (Throwable th) {
            b.a(this, "Error starting RecordStartingState", th);
            this.a.a(new x(this.a, 3, null, null, false));
        }
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
    public final void h() {
        this.a.a(new ae(this.a));
    }

    @Override // defpackage.ag, defpackage.ab
    public final void i() {
        a(3);
    }

    @Override // defpackage.ag, defpackage.ab
    public final void j() {
        a(3);
    }
}
