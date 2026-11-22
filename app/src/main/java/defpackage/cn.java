package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class cn extends cp {
    private String a;

    public cn(String str) {
        super((short) 22);
        this.a = str;
    }

    public cn(byte[] bArr) {
        super((short) 22);
        this.a = new String(bArr);
    }

    public final String a() {
        return this.a;
    }

    public final byte[] b() {
        return super.a(this.a.getBytes());
    }
}
