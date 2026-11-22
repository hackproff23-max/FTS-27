package defpackage;

/* JADX INFO: loaded from: classes.dex */
public class br {
    private static final bm a = d.a(br.class);

    public static byte[] a(byte b, byte b2, short s, byte[] bArr) {
        if (a.b()) {
            a.b("Building XMode buffer: protocol=" + ((int) b) + " version=" + ((int) b2) + " cmd=" + ((int) s) + " payload len:" + bArr.length);
            a.a(bArr);
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[length + 8];
        System.arraycopy(new bs(b, b2, s, length).a(), 0, bArr2, 0, 8);
        System.arraycopy(bArr, 0, bArr2, 8, length);
        return bArr2;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        if (a.b()) {
            a.b("Appending session ID");
            a.a(bArr2);
            a.a(bArr);
        }
        byte[] bArr3 = new byte[bArr.length + 16];
        System.arraycopy(bArr2, 0, bArr3, 0, 16);
        System.arraycopy(bArr, 0, bArr3, 16, bArr.length);
        return bArr3;
    }
}
