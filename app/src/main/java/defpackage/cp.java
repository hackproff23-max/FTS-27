package defpackage;

import android.support.v4.media.TransportMediator;
import android.support.v4.view.MotionEventCompat;
import com.flurry.android.Constants;
import java.io.ByteArrayOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class cp {
    private short a;

    public cp(short s) {
        this.a = s;
    }

    public static int a(int i) {
        if (i <= 127) {
            return 1;
        }
        if (i <= 255) {
            return 2;
        }
        return i <= 65535 ? 3 : 5;
    }

    public static int a(byte[] bArr, int i) {
        int i2 = bArr[i] & Constants.UNKNOWN;
        if (i2 == 129) {
            return bArr[i + 1] & Constants.UNKNOWN;
        }
        if (i2 == 130) {
            return ((bArr[i + 1] & Constants.UNKNOWN) << 8) + (bArr[i + 2] & Constants.UNKNOWN);
        }
        return i2 == 132 ? ((bArr[i + 1] & Constants.UNKNOWN) << 24) + ((bArr[i + 2] & Constants.UNKNOWN) << 16) + ((bArr[i + 3] & Constants.UNKNOWN) << 8) + (bArr[i + 4] & Constants.UNKNOWN) : i2;
    }

    public final byte[] a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(this.a & 255);
        if (bArr.length > 65535) {
            byteArrayOutputStream.write(132);
            byteArrayOutputStream.write(bArr.length >>> 24);
            byteArrayOutputStream.write((bArr.length >> 16) & MotionEventCompat.ACTION_MASK);
            byteArrayOutputStream.write((bArr.length >> 8) & MotionEventCompat.ACTION_MASK);
            byteArrayOutputStream.write(bArr.length & MotionEventCompat.ACTION_MASK);
        } else if (bArr.length > 255) {
            byteArrayOutputStream.write(TransportMediator.KEYCODE_MEDIA_RECORD);
            byteArrayOutputStream.write((bArr.length >> 8) & MotionEventCompat.ACTION_MASK);
            byteArrayOutputStream.write(bArr.length & MotionEventCompat.ACTION_MASK);
        } else if (bArr.length > 127) {
            byteArrayOutputStream.write(129);
            byteArrayOutputStream.write(bArr.length);
        } else if (bArr.length >= 0) {
            byteArrayOutputStream.write(bArr.length);
        }
        byteArrayOutputStream.write(bArr, 0, bArr.length);
        return byteArrayOutputStream.toByteArray();
    }

    public final short c() {
        return this.a;
    }
}
