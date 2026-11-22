package defpackage;

import com.nuance.nmdp.speechkit.recognitionresult.b;

/* JADX INFO: loaded from: classes.dex */
final class at extends av {
    private boolean c;
    private boolean d;

    public at(au auVar) {
        super(auVar);
        this.c = false;
        this.d = false;
    }

    @Override // defpackage.i
    public final void a() {
        try {
            this.a.d();
            this.a.a(this.a.i());
            this.a.a(this.a.k(), this.a.j(), this.a.n());
            this.a.f();
            this.a.l();
        } catch (Throwable th) {
            b.a(this, "Error starting PlayingState", th);
            a(4);
        }
    }

    @Override // defpackage.i
    public final void a(cv cvVar) {
        this.c = true;
        if (this.d) {
            this.a.a(new ap(this.a));
        }
    }

    @Override // defpackage.i
    public final void a(String str) {
        a(4, str);
    }

    @Override // defpackage.i
    public final void b() {
        this.a.m();
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
        a(4, "createCommandFailed");
    }

    @Override // defpackage.i
    public final void f() {
        a(1);
    }

    @Override // defpackage.av, defpackage.ar
    public final void g() {
        a(4, "audioError");
    }

    @Override // defpackage.av, defpackage.ar
    public final void h() {
        j().b(this.a);
    }

    @Override // defpackage.av, defpackage.ar
    public final void i() {
        this.d = true;
        if (this.c) {
            this.a.a(new ap(this.a));
        }
    }
}
