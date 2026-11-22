package defpackage;

import com.nuance.nmdp.speechkit.recognitionresult.b;

/* JADX INFO: loaded from: classes.dex */
public final class ak extends w {
    public ak(af afVar) {
        super(afVar);
    }

    @Override // defpackage.i
    public final void a() {
        try {
            this.a.d();
            this.a.a(this.a.k());
            this.a.j();
            this.a.f();
        } catch (Throwable th) {
            b.a(this, "Error starting WaitingWithoutRecordingState", th);
            this.a.a(new x(this.a, 3, null, null, false));
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

    @Override // defpackage.ag, defpackage.ab
    public final /* bridge */ /* synthetic */ void h() {
        super.h();
    }

    @Override // defpackage.ag, defpackage.ab
    public final /* bridge */ /* synthetic */ void i() {
        super.i();
    }

    @Override // defpackage.ag, defpackage.ab
    public final /* bridge */ /* synthetic */ void j() {
        super.j();
    }

    @Override // defpackage.ag, defpackage.ab
    public final /* bridge */ /* synthetic */ void k() {
        super.k();
    }

    @Override // defpackage.ag, defpackage.ab
    public final /* bridge */ /* synthetic */ void l() {
        super.l();
    }
}
