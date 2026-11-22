package defpackage;

import android.support.v4.view.MotionEventCompat;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class dt extends cq {
    private static final bm a = d.a(dt.class);
    private short b;

    public dt(short s) {
        this.b = s;
    }

    public dt(short s, byte[] bArr) {
        super(bArr);
        this.b = s;
    }

    @Override // defpackage.cq
    public final byte[] d() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(this.b & 255);
        byteArrayOutputStream.write((this.b >> 8) & MotionEventCompat.ACTION_MASK);
        try {
            byteArrayOutputStream.write(super.d());
        } catch (IOException e) {
            a.e("PDXMessage.toByteArray() " + e.toString() + ". ");
        }
        return byteArrayOutputStream.toByteArray();
    }

    public final short e() {
        return this.b;
    }

    public final byte[] f() {
        return super.d();
    }
}
