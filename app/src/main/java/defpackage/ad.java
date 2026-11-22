package defpackage;

import com.nuance.nmdp.speechkit.recognitionresult.b;

/* JADX INFO: loaded from: classes.dex */
final class ad extends ag {
    public ad(af afVar) {
        super(afVar);
    }

    @Override // defpackage.i
    public final void a() {
    }

    @Override // defpackage.i
    public final void c() {
        b.a(this, "Starting recognize transaction");
        this.a.a(this.a.m() ? new ah(this.a) : new ak(this.a));
    }
}
