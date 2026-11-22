package defpackage;

import com.nuance.nmsp.client.sdk.oem.AudioSystemOEM;
import com.nuance.nmsp.client.sdk.oem.b;
import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class dd implements bj.a, bj.c, bj.m, bn.b, cx, z {
    private static final bm a = d.a(dd.class);
    private bi b;
    private bj c;
    private by d;
    private LinkedList<byte[]> h;
    private Vector k;
    private bn l;
    private bj.k n;
    private ce o;
    private short j = -1;
    private Object m = new Object();
    private ByteArrayOutputStream e = new ByteArrayOutputStream();
    private Vector f = new Vector();
    private boolean i = false;
    private boolean g = false;

    public dd(by byVar, df dfVar, Vector vector, bh bhVar) {
        ce ceVarB;
        this.l = null;
        this.b = ((dl) dfVar).f();
        this.d = byVar;
        this.l = ((dl) dfVar).c_();
        this.k = vector;
        this.c = new AudioSystemOEM(this.l, this.b, vector);
        if (bhVar.equals(bh.d)) {
            this.n = bj.k.d;
        } else if (bhVar.equals(bh.c)) {
            this.n = bj.k.c;
        } else if (bhVar.equals(bh.b)) {
            this.n = bj.k.b;
        } else if (bhVar.equals(bh.a)) {
            this.n = bj.k.a;
        } else if (bhVar.equals(bh.e)) {
            this.n = bj.k.e;
        }
        if (bx.b(this.b)) {
            this.h = new LinkedList<>();
        }
        ch chVar = (ch) dfVar.a();
        if (chVar == null || (ceVarB = chVar.b()) == null) {
            return;
        }
        this.o = ceVarB.a("NMSPPlayer").a();
    }

    private void a(String str) {
        if (this.d != null) {
            try {
                this.d.a(str);
            } catch (Throwable th) {
                if (a.e()) {
                    a.e("Got an exp while calling NMSPAudioPlaybackListener.playerUpdate(" + str + ")[" + th.getClass().getName() + "] msg [" + th.getMessage() + "]");
                }
            }
        }
    }

    private void b(String str) {
        if (this.o != null) {
            this.o.a(str).a();
        }
    }

    @Override // defpackage.z, defpackage.g
    public final void a() {
        if (a.b()) {
            a.b("PlayerImpl.start()");
        }
        b("PlayerStart");
        bn bnVar = this.l;
        bn.a aVar = new bn.a((byte) 1, this);
        Thread threadCurrentThread = Thread.currentThread();
        this.l.a();
        bnVar.a(aVar, this, threadCurrentThread);
    }

    @Override // bj.m
    public final void a(bj.b bVar) {
        if (a.b()) {
            a.b("audio stop call back is called");
        }
        this.j = (short) 5;
        a(bVar.equals(bj.b.b) ? "PLAYBACK_ERROR" : "STOPPED");
    }

    @Override // bj.c
    public final void a(bj.b bVar, Object obj) {
        if (a.b()) {
            a.b("PlayerImpl.doneCallback()");
        }
        bn bnVar = this.l;
        bn.a aVar = new bn.a((byte) 7, new Object[]{bVar, obj});
        Thread threadCurrentThread = Thread.currentThread();
        this.l.a();
        bnVar.a(aVar, this, threadCurrentThread);
    }

    /* JADX WARN: Code duplicated, block: B:108:0x0262 A[Catch: all -> 0x0268, TryCatch #0 {, blocks: (B:104:0x024e, B:106:0x025b, B:114:0x026b, B:108:0x0262, B:109:0x0265), top: B:123:0x024e }] */
    @Override // bn.b
    public final void a(Object obj, Object obj2) {
        bn.a aVar = (bn.a) obj;
        if (a.b()) {
            a.b("\nXMode handleMessage - [" + ((int) aVar.a) + "]");
        }
        switch (aVar.a) {
            case 1:
                if (a.b()) {
                    a.b("PlayerImpl.handleStartPlayback()");
                }
                if (new b(this.k).a()) {
                    this.b = bx.c(this.b);
                }
                if (this.j == -1) {
                    this.j = (short) 0;
                } else {
                    if (this.j != 2) {
                        this.j = (short) 5;
                        if (a.e()) {
                            a.e("PlayImpl.handleStartPlayback() wrong state [state != STATE_INIT]!!!");
                        }
                        a("PLAYBACK_ERROR");
                        return;
                    }
                    this.j = (short) 3;
                }
                if (this.j != 0 && this.j != 3) {
                    this.j = (short) 5;
                    if (a.e()) {
                        a.e("PlayImpl.handleStartPlayback() wrong state [state != STATE_STARTING]!!!");
                    }
                    a("PLAYBACK_ERROR");
                    return;
                }
                if (this.j != 0) {
                    this.c.a();
                    this.j = (short) 5;
                    if (a.e()) {
                        a.e("PlayImpl.handleStartPlayback() audioSys.pausePlayback() return false!!!");
                    }
                    a("PLAYBACK_ERROR");
                    return;
                }
                if (this.c.a(this.n, this, this)) {
                    b("StreamStart");
                    return;
                }
                this.j = (short) 5;
                if (a.e()) {
                    a.e("PlayImpl.handleStartPlayback() audioSys.startPlayback() return false!!!");
                }
                a("PLAYBACK_ERROR");
                return;
            case 2:
                if (a.b()) {
                    a.b("PlayerImpl.handleStopPlayback()");
                }
                if (this.j == 5 || this.j == 4) {
                    return;
                }
                this.j = (short) 4;
                this.c.b(this);
                return;
            case 3:
                if (a.b()) {
                    a.b("PlayerImpl.handlePausePlayback()");
                }
                if (this.j == 5 || this.j == 4) {
                    this.j = (short) 5;
                    if (a.e()) {
                        a.e("PlayImpl.handlePausePlayback() wrong state [state == " + ((int) this.j) + "]!!!");
                    }
                    a("PLAYBACK_ERROR");
                    return;
                }
                this.j = (short) 2;
                this.c.a();
                this.j = (short) 5;
                if (a.e()) {
                    a.e("PlayImpl.handleStartPlayback() audioSys.pausePlayback() return false!!!");
                }
                a("PLAYBACK_ERROR");
                return;
            case 4:
                if (a.b()) {
                    a.b("PlayerImpl.handlePrevious()");
                }
                if (this.j == 5 || this.j == 4) {
                    this.j = (short) 5;
                    if (a.e()) {
                        a.e("PlayImpl.handlePausePlayback() wrong state [state == " + ((int) this.j) + "]!!!");
                    }
                    a("PLAYBACK_ERROR");
                    return;
                }
                this.c.b();
                this.j = (short) 5;
                if (a.e()) {
                    a.e("PlayImpl.handleStartPlayback() audioSys.previousPlayback() return false!!!");
                }
                a("PLAYBACK_ERROR");
                return;
            case 5:
                if (a.b()) {
                    a.b("PlayerImpl.handleNext()");
                }
                if (this.j == 5 || this.j == 4) {
                    this.j = (short) 5;
                    if (a.e()) {
                        a.e("PlayImpl.handlePausePlayback() wrong state [state == " + ((int) this.j) + "]!!!");
                    }
                    a("PLAYBACK_ERROR");
                    return;
                }
                this.c.c();
                this.j = (short) 5;
                if (a.e()) {
                    a.e("PlayImpl.handleStartPlayback() audioSys.nextPlayback() return false!!!");
                }
                a("PLAYBACK_ERROR");
                return;
            case 6:
                Object[] objArr = (Object[]) aVar.b;
                byte[] bArr = (byte[]) objArr[0];
                boolean zBooleanValue = ((Boolean) objArr[1]).booleanValue();
                if (a.b()) {
                    a.b("PlayerImpl.handleAddBuffer()");
                }
                synchronized (this.m) {
                    if (bArr == null) {
                        if (zBooleanValue) {
                            this.i = true;
                        }
                        break;
                    } else {
                        bi biVar = this.b;
                        bx.a();
                        if (bx.b(this.b)) {
                            this.h.add(bArr);
                        } else {
                            this.e.write(bArr, 0, bArr.length);
                            this.f.addElement(bArr);
                        }
                        if (zBooleanValue) {
                            this.i = true;
                        }
                        break;
                    }
                    throw th;
                }
                return;
            case 7:
                bj.b bVar = (bj.b) ((Object[]) aVar.b)[0];
                if (a.b()) {
                    a.b("audio done call back is called");
                }
                if (bVar.equals(bj.b.b)) {
                    this.j = (short) 5;
                    a("PLAYBACK_ERROR");
                    return;
                } else {
                    this.j = (short) 5;
                    b("StreamStop");
                    a("STOPPED");
                    return;
                }
            default:
                return;
        }
    }

    @Override // defpackage.cx
    public final void a(byte[] bArr, int i, int i2, boolean z) throws dc {
        if (a.c()) {
            a.c("addAudioBuf(" + bArr + ", " + z + ")");
        }
        if (bArr == null && !z) {
            throw new NullPointerException("buffer is null!");
        }
        if (bArr != null && i < 0) {
            throw new IllegalArgumentException("offset cannot be negative!!!");
        }
        if (bArr != null && i2 <= 0) {
            throw new IllegalArgumentException("length can only be positive!!!");
        }
        if ((this.b == bi.c || this.b == bi.b || this.b == bi.a) && i2 > 153600) {
            a("PLAYBACK_ERROR");
            a_();
            return;
        }
        if (this.g) {
            throw new dc("audio player is full, the last buffer has already apended!");
        }
        if (!this.g && z) {
            this.g = true;
        }
        byte[] bArr2 = null;
        if (bArr != null) {
            bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
        }
        Object[] objArr = {bArr2, new Boolean(z)};
        bn bnVar = this.l;
        bn.a aVar = new bn.a((byte) 6, objArr);
        Thread threadCurrentThread = Thread.currentThread();
        this.l.a();
        bnVar.a(aVar, this, threadCurrentThread);
    }

    /* JADX WARN: Code duplicated, block: B:74:0x0177  */
    @Override // bj.a
    public final void a(byte[] bArr, Object obj, bj.i iVar, bj.i iVar2, Float f) {
        int length;
        if (a.b()) {
            a.b("audio call back is called time: [" + System.currentTimeMillis() + "]");
        }
        if (this.j == 0) {
            this.j = (short) 1;
            a("STARTED");
        }
        bi biVar = this.b;
        bx.a();
        if (bx.b(this.b)) {
            bArr = (byte[]) obj;
        } else {
            bi biVar2 = this.b;
            bx.b();
            if (!bx.a(this.b)) {
                bArr = null;
            }
        }
        int length2 = bArr.length;
        if (!bx.b(this.b)) {
            synchronized (this.m) {
                if (this.e.size() <= 0) {
                    length = this.i ? -1 : 0;
                } else {
                    byte[] byteArray = this.e.toByteArray();
                    try {
                        this.e.close();
                    } catch (Exception e) {
                        System.out.println("got an exception [" + e.getClass().getName() + "] message [" + e.getMessage() + "]");
                    }
                    this.e = null;
                    this.e = new ByteArrayOutputStream();
                    if (length2 < byteArray.length) {
                        bi biVar3 = this.b;
                        bx.a();
                        if (bx.a(this.b)) {
                            length = length2;
                        } else {
                            length = 0;
                        }
                    } else {
                        bi biVar4 = this.b;
                        bx.a();
                        if (bx.a(this.b)) {
                            length = byteArray.length;
                        } else {
                            length = 0;
                        }
                    }
                    if (length < byteArray.length) {
                        this.e.write(byteArray, length, byteArray.length - length);
                    }
                    if (a.b()) {
                        a.b("feed oem audio data len [" + length + "] complete buffer size [" + byteArray.length + "]");
                    }
                    System.arraycopy(byteArray, 0, bArr, 0, length);
                }
            }
        } else if (this.h.isEmpty()) {
            length = this.i ? -1 : 0;
        } else {
            try {
                byte[] bArrRemoveFirst = this.h.removeFirst();
                if (length2 < bArrRemoveFirst.length) {
                    if (a.e()) {
                        a.e("Buffer is too small to contain the Speex buffer.");
                    }
                    length = 0;
                } else {
                    int length3 = bArrRemoveFirst.length;
                    System.arraycopy(bArrRemoveFirst, 0, bArr, 0, length3);
                    length = length3;
                }
            } catch (NoSuchElementException e2) {
                length = 0;
            }
        }
        if (length > 0) {
            a("BUFFER_PLAYED");
        }
        bi biVar5 = this.b;
        bx.a();
        if (bx.b(this.b)) {
            iVar2.a = length;
            return;
        }
        bi biVar6 = this.b;
        bx.b();
        if (bx.a(this.b)) {
            iVar.a = length;
        }
    }

    @Override // defpackage.z
    public final void a_() {
        if (a.b()) {
            a.b("PlayerImpl.start()");
        }
        b("PlayerStop");
        bn bnVar = this.l;
        bn.a aVar = new bn.a((byte) 2, this);
        Thread threadCurrentThread = Thread.currentThread();
        this.l.a();
        bnVar.a(aVar, this, threadCurrentThread);
    }
}
