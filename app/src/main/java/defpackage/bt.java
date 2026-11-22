package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class bt {
    private static String b = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private static byte[] a = new byte[64];

    static {
        for (int i = 0; i < 64; i++) {
            a[i] = (byte) b.charAt(i);
        }
    }

    public static String a(byte[] bArr) {
        int i;
        int length = bArr.length;
        byte[] bArr2 = new byte[((length + 2) / 3) << 2];
        int i2 = length + 0;
        byte b2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i5 < i2) {
            byte b3 = bArr[i5];
            i3++;
            switch (i3) {
                case 1:
                    i = i4 + 1;
                    bArr2[i4] = a[(b3 >> 2) & 63];
                    break;
                case 2:
                    i = i4 + 1;
                    bArr2[i4] = a[((b2 << 4) & 48) | ((b3 >> 4) & 15)];
                    break;
                case 3:
                    int i6 = i4 + 1;
                    bArr2[i4] = a[((b2 << 2) & 60) | ((b3 >> 6) & 3)];
                    bArr2[i6] = a[b3 & 63];
                    i = i6 + 1;
                    i3 = 0;
                    break;
                default:
                    i = i4;
                    break;
            }
            b2 = b3;
            i5++;
            i4 = i;
        }
        switch (i3) {
            case 1:
                int i7 = i4 + 1;
                bArr2[i4] = a[(b2 << 4) & 48];
                bArr2[i7] = 61;
                bArr2[i7 + 1] = 61;
                break;
            case 2:
                bArr2[i4] = a[(b2 << 2) & 60];
                bArr2[i4 + 1] = 61;
                break;
        }
        return new String(bArr2);
    }
}
