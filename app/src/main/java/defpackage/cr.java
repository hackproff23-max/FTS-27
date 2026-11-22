package defpackage;

import com.flurry.android.Constants;

/* JADX INFO: loaded from: classes.dex */
public final class cr extends cp {
    private int a;

    public cr(int i) {
        super((short) 192);
        this.a = i;
    }

    public cr(byte[] bArr) {
        super((short) 192);
        if (bArr.length == 1) {
            this.a = bArr[0] & Constants.UNKNOWN;
            return;
        }
        if (bArr.length == 2) {
            this.a = ((bArr[1] & Constants.UNKNOWN) << 8) + (bArr[0] & Constants.UNKNOWN);
        } else if (bArr.length == 3) {
            this.a = ((bArr[2] & Constants.UNKNOWN) << 16) + ((bArr[1] & Constants.UNKNOWN) << 8) + (bArr[0] & Constants.UNKNOWN);
        } else {
            this.a = ((bArr[3] & Constants.UNKNOWN) << 24) + ((bArr[2] & Constants.UNKNOWN) << 16) + ((bArr[1] & Constants.UNKNOWN) << 8) + (bArr[0] & Constants.UNKNOWN);
        }
    }

    public final int a() {
        return this.a;
    }

    public final byte[] b() {
        byte[] bArr;
        if (Math.abs(this.a) < 128) {
            bArr = new byte[]{(byte) this.a};
        } else {
            bArr = Math.abs(this.a) < 32768 ? new byte[]{(byte) this.a, (byte) (this.a >> 8)} : new byte[]{(byte) this.a, (byte) (this.a >> 8), (byte) (this.a >> 16), (byte) (this.a >>> 24)};
        }
        return super.a(bArr);
    }
}
