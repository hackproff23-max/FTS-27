package defpackage;

import com.flurry.android.Constants;

/* JADX INFO: loaded from: classes.dex */
public class bu {
    private static final bm a = d.a(bu.class);

    public static short a(byte[] bArr, int i) {
        short s = (short) ((bArr[i] & Constants.UNKNOWN) | ((bArr[i + 1] & Constants.UNKNOWN) << 8));
        if (a.a()) {
            a.a("Converted [" + ((int) bArr[i]) + ", " + ((int) bArr[i + 1]) + "] to short " + ((int) s));
        }
        return s;
    }

    public static void a(int i, byte[] bArr, int i2) {
        a((short) i, bArr, i2);
        a((short) (i >> 16), bArr, i2 + 2);
        if (a.a()) {
            a.a("Converted int " + i + " to [" + ((int) bArr[i2]) + ", " + ((int) bArr[i2 + 1]) + ", " + ((int) bArr[i2 + 2]) + ", " + ((int) bArr[i2 + 3]) + "]");
        }
    }

    public static void a(short s, byte[] bArr, int i) {
        bArr[i] = (byte) s;
        bArr[i + 1] = (byte) (s >> 8);
        if (a.a()) {
            a.a("Converted short " + ((int) s) + " to [" + ((int) bArr[i]) + ", " + ((int) bArr[i + 1]) + "]");
        }
    }

    public static int b(byte[] bArr, int i) {
        int iA = (a(bArr, i) & 65535) | ((a(bArr, i + 2) & 65535) << 16);
        if (a.a()) {
            a.a("Converted [" + ((int) bArr[i]) + ", " + ((int) bArr[i + 1]) + ", " + ((int) bArr[i + 2]) + ", " + ((int) bArr[i + 3]) + "] to int " + iA);
        }
        return iA;
    }
}
