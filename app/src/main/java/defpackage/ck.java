package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class ck extends cm {
    protected ce a;
    private String e;
    private int f;
    private cj g;
    private String h;
    private String i;
    private int j;

    ck(cj cjVar, String str) {
        super(str, null, null, cjVar.c);
        this.e = null;
        this.f = 1;
        this.h = cjVar.b.h;
        this.i = cjVar.b.i;
        this.j = cjVar.b.j;
        this.a = null;
        this.b = this;
        this.g = cjVar;
    }

    ck(String str, String str2, String str3, int i, ch chVar, String str4) {
        super(str4, null, null, chVar);
        this.e = null;
        this.f = 1;
        this.h = str2;
        this.i = str3;
        this.j = i;
        this.a = null;
        this.e = str;
        this.b = this;
    }

    @Override // defpackage.cm
    protected final cw a() {
        cw cwVarA = super.a();
        a(cwVarA, "Application", this.h);
        a(cwVarA, "SchemaVersion", this.i);
        a(cwVarA, "RetentionDays", new Integer(this.j));
        return cwVarA;
    }

    public final String b() {
        return this.e;
    }

    @Override // defpackage.cm
    protected final void c() {
        super.c();
        if (this.g != null) {
            this.e = this.g.b();
        }
    }

    public final int d() {
        int i = this.f;
        this.f = i + 1;
        return i;
    }
}
