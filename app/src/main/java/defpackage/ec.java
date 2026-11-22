package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class ec extends dv implements bj.j {
    private cq a;

    public ec(String str, cq cqVar, byte b) {
        super(str);
        if (b == 1) {
            super.a((byte) 6);
        } else if (b == 2) {
            super.a((byte) 7);
        } else {
            super.a((byte) 8);
        }
        this.a = cqVar;
    }

    protected final byte[] a() {
        return this.a.b();
    }
}
