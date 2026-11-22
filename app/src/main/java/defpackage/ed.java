package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class ed extends dv implements bj.j {
    private cq a;
    private cx b;
    private int c;

    public ed(String str, dm dmVar, cq cqVar, cx cxVar) {
        super(str, (byte) 127);
        this.a = cqVar;
        this.b = cxVar;
        this.c = dmVar.c();
        cqVar.b("audio_id", this.c);
    }

    protected final int a() {
        return this.c;
    }

    protected final byte[] d() {
        return this.a.b();
    }

    protected final cx e() {
        return this.b;
    }
}
