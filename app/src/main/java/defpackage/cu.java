package defpackage;

/* JADX INFO: loaded from: classes.dex */
public class cu extends cp {
    private static final bm a = d.a(cu.class);
    private String b;

    public cu(String str) {
        super((short) 193);
        this.b = str;
    }

    public cu(byte[] bArr) {
        super((short) 193);
        try {
            this.b = new String(bArr, "UTF-8");
        } catch (Exception e) {
            a.e("PDXUTF8String() " + e.getMessage());
            this.b = new String(bArr);
        }
    }

    public final String a() {
        return this.b;
    }

    public final byte[] b() {
        try {
            return super.a(this.b.getBytes("UTF-8"));
        } catch (Exception e) {
            a.e("PDXUTF8String().toByteArray() " + e.getMessage());
            return super.a(this.b.getBytes());
        }
    }
}
