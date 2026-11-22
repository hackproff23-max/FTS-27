package defpackage;

import com.nuance.nmdp.speechkit.recognitionresult.b;

/* JADX INFO: loaded from: classes.dex */
final class s extends i {
    private final p a;

    public s(p pVar) {
        super(pVar);
        this.a = pVar;
    }

    @Override // defpackage.i
    public final void a() {
    }

    @Override // defpackage.i
    public final void c() {
        b.a(this, "Starting " + this.a.j() + " transaction");
        this.a.a(new t(this.a));
    }
}
