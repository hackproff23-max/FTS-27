package defpackage;

import com.nuance.nmdp.speechkit.recognitionresult.b;

/* JADX INFO: loaded from: classes.dex */
final class aj extends w {
    public aj(af afVar) {
        super(afVar);
    }

    @Override // defpackage.i
    public final void a() {
        try {
            this.a.f();
        } catch (l e) {
            b.a(this, "Error ending command", e);
            a(3);
        }
    }

    @Override // defpackage.i
    public final void a(cv cvVar) {
        bf<?> bfVarI = this.a.i();
        if (!bfVarI.a(cvVar)) {
            a(3);
            return;
        }
        m().d(this.a);
        if (bfVarI.a()) {
            return;
        }
        this.a.a(new y(this.a));
    }

    @Override // defpackage.i
    public final void d() {
        a(5);
    }

    @Override // defpackage.ag, defpackage.ab
    public final void g() {
        a(3);
    }
}
