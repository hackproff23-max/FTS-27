package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class cj extends ci {
    protected Boolean a;
    private String e;

    cj(cm cmVar, String str) {
        super(cmVar, str);
        this.a = false;
    }

    @Override // defpackage.ci, defpackage.cm
    protected final cw a() {
        cw cwVarA = super.a();
        a(cwVarA, "RefId", this.e + ".1");
        if (this.a.booleanValue()) {
            a(cwVarA, "Cancel", new Boolean(true));
        }
        return cwVarA;
    }

    final String b() {
        return this.e;
    }

    @Override // defpackage.cm
    protected final void c() {
        super.c();
        this.e = this.b.b() + "." + this.d;
    }
}
