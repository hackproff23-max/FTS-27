package defpackage;

import com.nuance.nmdp.speechkit.recognitionresult.b;

/* JADX INFO: loaded from: classes.dex */
final class ai extends w {
    public ai(af afVar) {
        super(afVar);
    }

    @Override // defpackage.i
    public final void a() {
        try {
            this.a.u();
            m().b(this.a);
        } catch (l e) {
            b.a(this, "Error starting RecordingState", e);
            a(3);
        }
    }

    @Override // defpackage.i
    public final void b() {
        this.a.v();
        m().c(this.a);
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
        this.a.p();
        this.a.a(new aj(this.a));
    }

    @Override // defpackage.ag, defpackage.ab
    public final void j() {
        this.a.v();
    }
}
