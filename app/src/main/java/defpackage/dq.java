package defpackage;

import android.support.v4.media.TransportMediator;
import java.io.ByteArrayOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class dq extends dv implements bn.b, cx {
    private static final bm a = d.a(dq.class);
    private int b;
    private dm c;
    private boolean d;
    private bn e;

    public dq(String str, dm dmVar, bn bnVar) {
        super(str, (byte) 1);
        this.d = false;
        this.c = dmVar;
        this.e = bnVar;
        this.b = dmVar.c();
        bn.a aVar = new bn.a((byte) 1, null);
        Thread threadCurrentThread = Thread.currentThread();
        bnVar.a();
        bnVar.a(aVar, this, threadCurrentThread);
    }

    protected final int a() {
        return this.b;
    }

    @Override // bn.b
    public final void a(Object obj, Object obj2) {
        bn.a aVar = (bn.a) obj;
        switch (aVar.a) {
            case 1:
                this.c.a(this.b);
                break;
            case 2:
                Object[] objArr = (Object[]) aVar.b;
                byte[] bArr = (byte[]) objArr[0];
                boolean zBooleanValue = ((Boolean) objArr[1]).booleanValue();
                if (bArr != null) {
                    if (bx.b(this.c.i())) {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        if (bArr.length < 128) {
                            byteArrayOutputStream.write(bArr.length & TransportMediator.KEYCODE_MEDIA_PAUSE);
                        } else if (bArr.length < 16384) {
                            byteArrayOutputStream.write(((bArr.length >> 7) & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
                            byteArrayOutputStream.write(bArr.length & TransportMediator.KEYCODE_MEDIA_PAUSE);
                        } else if (bArr.length < 2097152) {
                            byteArrayOutputStream.write(((bArr.length >> 14) & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
                            byteArrayOutputStream.write(((bArr.length >> 7) & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
                            byteArrayOutputStream.write(bArr.length & TransportMediator.KEYCODE_MEDIA_PAUSE);
                        } else if (bArr.length >= 268435456) {
                            a.e("buffer size is too big!");
                        } else {
                            byteArrayOutputStream.write(((bArr.length >> 21) & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
                            byteArrayOutputStream.write(((bArr.length >> 14) & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
                            byteArrayOutputStream.write(((bArr.length >> 7) & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
                            byteArrayOutputStream.write(bArr.length & TransportMediator.KEYCODE_MEDIA_PAUSE);
                        }
                        byteArrayOutputStream.write(bArr, 0, bArr.length);
                        this.c.a(byteArrayOutputStream.toByteArray(), this.b);
                    } else {
                        this.c.a(bArr, this.b);
                    }
                }
                if (zBooleanValue) {
                    this.c.b(this.b);
                }
                break;
        }
    }

    @Override // defpackage.cx
    public final void a(byte[] bArr, int i, int i2, boolean z) throws dc {
        if (bArr == null && !z) {
            a.e("PDXAudioParam.addAudioBuf() in (NMSPAudioSink)PDXAudioParam.addAudioBuf(), the param \"buffer\" is null.");
            throw new NullPointerException("in (NMSPAudioSink)PDXAudioParam.addAudioBuf(), the param \"buffer\" is null.");
        }
        if (bArr != null && i < 0) {
            a.e("PDXAudioParam.addAudioBuf() the offset of the \"buffer\" is less than 0");
            throw new IllegalArgumentException("the offset of the \"buffer\" is less than 0");
        }
        if (bArr != null && i2 <= 0) {
            a.e("PDXAudioParam.addAudioBuf() the indicated length of the \"buffer\" is less than 1 byte");
            throw new IllegalArgumentException("the indicated length of the \"buffer\" is less than 1 byte");
        }
        if (this.d) {
            a.e("PDXAudioParam.addAudioBuf() last audio buffer already added!");
            throw new dc("last audio buffer already added!");
        }
        if (z) {
            this.d = true;
        }
        byte[] bArr2 = null;
        if (bArr != null) {
            bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
        }
        Object[] objArr = {bArr2, new Boolean(z)};
        bn bnVar = this.e;
        bn.a aVar = new bn.a((byte) 2, objArr);
        Thread threadCurrentThread = Thread.currentThread();
        this.e.a();
        bnVar.a(aVar, this, threadCurrentThread);
    }
}
