package defpackage;

import com.flurry.android.Constants;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes.dex */
public class fr {
    private static final bm a = d.a(fr.class);

    public static int a(byte[] bArr, int i) {
        return (bArr[i] & Constants.UNKNOWN) | ((bArr[i + 1] & Constants.UNKNOWN) << 8);
    }

    public static String a(String str) {
        int i = 0;
        int length = str.length();
        char[] charArray = str.toCharArray();
        while (i < length && charArray[i] == ' ') {
            i++;
        }
        while (i < length && charArray[length - 1] == ' ') {
            length--;
        }
        return str.substring(i, length);
    }

    public static String a(byte[] bArr, int i, int i2, String str) {
        try {
            return new String(bArr, i, i2, str);
        } catch (UnsupportedEncodingException e) {
            if (a.d()) {
                a.d(str + " character encoding is not available in your VM. Using the default one.");
            }
            return new String(bArr, i, i2);
        }
    }

    public static int b(byte[] bArr, int i) {
        return (a(bArr, i) & 65535) | ((a(bArr, i + 2) & 65535) << 16);
    }

    public static long c(byte[] bArr, int i) {
        return ((long) (((bArr[i + 3] & Constants.UNKNOWN) << 24) | ((bArr[i + 2] & Constants.UNKNOWN) << 16) | ((bArr[i + 1] & Constants.UNKNOWN) << 8) | (bArr[i] & Constants.UNKNOWN))) & 4294967295L;
    }

    public static int d(byte[] bArr, int i) {
        if (a.a()) {
            a.a("Computing string length");
        }
        int i2 = i;
        while (bArr[i2] != 0) {
            i2++;
        }
        int i3 = i2 - i;
        if (a.a()) {
            a.a("String length: [" + i3 + "]");
        }
        return i3;
    }
}
