package defpackage;

import com.nuance.nmdp.speechkit.recognitionresult.b;

/* JADX INFO: loaded from: classes.dex */
final class t extends i {
    private final p a;

    public t(p pVar) {
        super(pVar);
        this.a = pVar;
    }

    @Override // defpackage.i
    public final void a() {
        try {
            this.a.d();
            this.a.a(this.a.j());
            this.a.i();
            this.a.f();
        } catch (Throwable th) {
            b.a(this, "Error starting PendingState", th);
            a(0);
        }
    }

    @Override // defpackage.i
    protected final void a(int i, String str, String str2) {
        this.a.a(new n(this.a, i, str, str2));
    }

    @Override // defpackage.i
    public final void a(cv cvVar) {
        bf<?> bfVarK = this.a.k();
        if (bfVarK == null || !bfVarK.a(cvVar) || bfVarK.a()) {
            a(0);
        } else {
            this.a.a(new o(this.a));
        }
    }

    @Override // defpackage.i
    public final void a(String str) {
        a(0, str);
    }

    @Override // defpackage.i
    public final void b(String str) {
        a(2, null, str);
    }

    @Override // defpackage.i
    public final void d() {
        a(5);
    }

    @Override // defpackage.i
    public final void e() {
        a(0);
    }

    @Override // defpackage.i
    public final void f() {
        a(1);
    }
}
