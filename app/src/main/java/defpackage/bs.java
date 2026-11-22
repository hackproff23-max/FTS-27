package defpackage;

/* JADX INFO: loaded from: classes.dex */
public class bs {
    private static final bm d = d.a(bs.class);
    public byte a;
    public short b;
    public int c;
    private byte e;

    public bs(byte b, byte b2, short s, int i) {
        if (d.b()) {
            d.b("Constructing XModeMsgHeader(protocol=" + ((int) b) + ", version=" + ((int) b2) + ", cmd=" + ((int) s) + ", len=" + i + ")");
        }
        this.a = b;
        this.e = b2;
        this.b = s;
        this.c = i;
    }

    public bs(byte[] bArr) {
        if (d.b()) {
            d.b("Constructing XModeMsgHeader(byte[])");
            d.a(bArr);
        }
        this.a = bArr[0];
        this.e = bArr[1];
        this.b = bu.a(bArr, 2);
        this.c = bu.b(bArr, 4);
    }

    public final byte[] a() {
        if (d.b()) {
            d.b("XModeMsgHeader.getBytes()");
        }
        byte[] bArr = new byte[8];
        bArr[0] = this.a;
        bArr[1] = this.e;
        bu.a(this.b, bArr, 2);
        bu.a(this.c, bArr, 4);
        if (d.b()) {
            d.a("Generated: ");
            d.a(bArr);
        }
        return bArr;
    }
}
