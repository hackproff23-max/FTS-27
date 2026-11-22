package defpackage;

import com.flurry.android.Constants;

/* JADX INFO: loaded from: classes.dex */
public class du {
    private static final bm a = d.a(du.class);

    public static dt a(byte[] bArr) {
        short s = (short) (((bArr[1] & Constants.UNKNOWN) << 8) + (bArr[0] & Constants.UNKNOWN));
        byte[] bArr2 = new byte[bArr.length - 2];
        System.arraycopy(bArr, 2, bArr2, 0, bArr2.length);
        switch (s) {
            case 29185:
                return new ea(bArr2);
            case 29186:
                return new dy(bArr2);
            case 29187:
            case 29188:
            default:
                a.e("PDXMessageFactory.createMessage() Unknown command: " + ((int) s) + ". ");
                return null;
            case 29189:
                return new eb(bArr2);
        }
    }
}
