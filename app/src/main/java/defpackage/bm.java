package defpackage;

import com.facebook.appevents.AppEventsConstants;
import com.google.android.vending.expansion.downloader.Constants;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes.dex */
public abstract class bm {
    private static String[] a = {AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_YES, "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    private static String a(byte b) {
        return a[(byte) (((byte) (((byte) (b & 240)) >>> 4)) & 15)] + a[(byte) (b & 15)];
    }

    public static String b(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 16; i++) {
            stringBuffer.append(a(bArr[i]));
            if (i == 3 || i == 5 || i == 7 || i == 9) {
                stringBuffer.append(Constants.FILENAME_SEQUENCE_SEPARATOR);
            }
        }
        return stringBuffer.toString().toLowerCase();
    }

    private static String[] c(byte[] bArr) {
        String str;
        int length = bArr.length / 8;
        if (bArr.length % 8 != 0) {
            length++;
        }
        String[] strArr = new String[length];
        try {
            str = new String(bArr, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            str = new String(bArr);
        }
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char[] cArr = new char[41];
            for (int i3 = 0; i3 < cArr.length; i3++) {
                cArr[i3] = ' ';
            }
            for (int i4 = 0; i4 < 8 && i < bArr.length; i4++) {
                String strA = a(bArr[i]);
                cArr[i4 * 3] = strA.charAt(0);
                cArr[(i4 * 3) + 1] = strA.charAt(1);
                cArr[(i4 << 1) + 26] = str.charAt(i);
                i++;
            }
            strArr[i2] = new String(cArr);
        }
        return strArr;
    }

    public abstract void a(Object obj);

    public abstract void a(Object obj, Throwable th);

    public final void a(byte[] bArr) {
        if (a()) {
            a("Buffer dump:");
            for (String str : c(bArr)) {
                a(str);
            }
        }
    }

    public abstract boolean a();

    public abstract void b(Object obj);

    public abstract boolean b();

    public abstract void c(Object obj);

    public abstract boolean c();

    public abstract void d(Object obj);

    public abstract boolean d();

    public abstract void e(Object obj);

    public abstract boolean e();

    public abstract void f();

    public abstract void g();
}
