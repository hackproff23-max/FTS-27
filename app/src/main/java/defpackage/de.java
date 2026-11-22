package defpackage;

import com.nuance.nmsp.client.sdk.oem.AudioSystemOEM;
import com.nuance.nmsp.client.sdk.oem.b;
import java.util.Enumeration;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class de implements bj.a, bj.d, bj.e, bj.f, bj.g, bj.j, bj.l, bj.m, bn.b, ca {
    private static final bm a = d.a(de.class);
    private bi b;
    private bj c;
    private bz d;
    private bn e;
    private cx f;
    private long g;
    private ce h;
    private int i;
    private bq j;
    private boolean k;
    private boolean l;
    private boolean m;
    private bj.h n;
    private Vector o;
    private int p;

    public de(bz bzVar, df dfVar, Vector vector, bg bgVar) {
        ce ceVarB;
        this.e = null;
        this.k = false;
        this.l = false;
        this.m = false;
        this.p = -1;
        a(vector);
        this.d = bzVar;
        this.b = ((dl) dfVar).e();
        this.j = null;
        this.e = ((dl) dfVar).c_();
        this.o = vector;
        this.k = a(vector, "ep.enable");
        this.l = a(vector, "NMSP_DEFINES_RECORDER_CONTINUES_ON_ENDPOINTER_AND_TIMER_STOPPING");
        this.m = a(vector, "NMSP_DEFINES_CAPTURING_CONTINUES_ON_ENDPOINTER");
        this.c = new AudioSystemOEM(this.e, this.b, vector);
        if (bgVar.equals(bg.c)) {
            this.n = bj.h.c;
        } else if (bgVar.equals(bg.a)) {
            this.n = bj.h.a;
        } else if (bgVar.equals(bg.b)) {
            this.n = bj.h.b;
        } else if (bgVar.equals(bg.d)) {
            this.n = bj.h.d;
        }
        ch chVar = (ch) dfVar.a();
        if (chVar != null && (ceVarB = chVar.b()) != null) {
            this.h = ceVarB.a("NMSPRecorder").a();
        }
        this.p = 0;
    }

    static /* synthetic */ bq a(de deVar) {
        deVar.j = null;
        return null;
    }

    private void a(cx cxVar, int i) {
        if (a.b()) {
            a.b("RecorderImpl.handleStartRecording(" + cxVar + ") _state:" + this.p);
        }
        if (new b(this.o).a()) {
            this.b = bx.c(this.b);
        }
        this.i = i;
        if (this.p == 0) {
            this.f = cxVar;
            if (!this.c.a(this.n, this.k && cxVar != null, this, this, this, this, this, this, this)) {
                if (a.e()) {
                    a.e("RecorderImpl.handleStartRecording() startRecording() failed!!!");
                }
                this.p = 8;
                a("RECORD_ERROR", (Object) null);
                return;
            }
            if (this.k) {
                if (cxVar == null) {
                    this.p = 1;
                } else {
                    this.p = 2;
                }
            } else if (cxVar == null) {
                this.p = 1;
            } else {
                h();
                this.p = 4;
            }
            a("STARTED", (Object) null);
        }
    }

    private void a(String str) {
        if (this.h != null) {
            this.h.a(str).a();
        }
    }

    private void a(String str, Object obj) {
        if (this.d != null) {
            try {
                this.d.a(this, str, obj);
            } catch (Throwable th) {
                if (a.e()) {
                    a.e("Got an exp while calling NMSPAudioRecordListener.recorderUpdate(" + str + ", " + obj + ")[" + th.getClass().getName() + "] msg [" + th.getMessage() + "]");
                }
            }
        }
    }

    private static void a(Vector vector) {
        if (vector != null) {
            Enumeration enumerationElements = vector.elements();
            while (enumerationElements.hasMoreElements()) {
                cz czVar = (cz) enumerationElements.nextElement();
                if (czVar.d() != cz.a.a) {
                    throw new IllegalArgumentException("Parameter type: " + czVar.d() + " not allowed. ");
                }
            }
        }
    }

    private static boolean a(Vector vector, String str) {
        if (vector != null) {
            Enumeration enumerationElements = vector.elements();
            while (enumerationElements.hasMoreElements()) {
                cz czVar = (cz) enumerationElements.nextElement();
                if (czVar.d() == cz.a.a && czVar.a().equals(str)) {
                    return new String(czVar.b()).equals("TRUE");
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (a.b()) {
            a.b("RecorderImpl.handleStopRecording() _state:" + this.p);
        }
        if (this.p == 2 || this.p == 4 || this.p == 5) {
            if (z) {
                a("CAPTURE_TIMEOUT", (Object) null);
            }
            if (this.p == 4) {
                i();
            }
            this.c.a((bj.m) this);
            this.p = 9;
            return;
        }
        if (this.p == 1 || this.p == 3 || this.p == 6) {
            this.c.a((bj.m) this);
            this.p = 7;
        }
    }

    private void h() {
        if (this.i > 0) {
            this.j = new bq() { // from class: de.1
                @Override // java.lang.Runnable
                public final void run() {
                    de.a(de.this);
                    if (de.this.l) {
                        de.this.a(true);
                    } else {
                        de.this.b(true);
                    }
                }
            };
            this.e.a(this.j, this.i);
        }
    }

    private void i() {
        if (this.j != null) {
            this.e.a(this.j);
            this.j = null;
        }
    }

    private void j() {
        try {
            this.f.a(null, 0, 0, true);
        } catch (dc e) {
            if (a.d()) {
                a.e("RecorderImpl.finishAudioSink() TransactionProcessingException:" + e);
            }
        }
        if (a.b()) {
            a.b("RecorderImpl::finishAudioSink send the last audio buffer from recorder");
        }
    }

    @Override // bj.d
    public final void a() {
        if (a.b()) {
            a.b("RecorderImpl::endOfSpeechCallback() _state:" + this.p);
        }
        if (this.p == 4) {
            a("END_OF_SPEECH", (Object) null);
            if (this.m) {
                return;
            }
            a(false);
        }
    }

    @Override // bj.m
    public final void a(bj.b bVar) {
        if (a.b()) {
            a.b("RecorderImpl.stopCallback() _state:" + this.p);
        }
        a("StreamStop");
        if (this.p == 1 || this.p == 3 || this.p == 7) {
            a("STOPPED", (Object) null);
            this.p = 8;
            return;
        }
        if (this.p == 2 || this.p == 4 || this.p == 5 || this.p == 6 || this.p == 9) {
            if (this.p == 4) {
                i();
            }
            j();
            a("STOPPED", (Object) null);
            this.p = 8;
        }
    }

    @Override // defpackage.ca
    public final void a(cx cxVar) {
        if (a.b()) {
            a.b("RecorderImpl.startCapturing(" + cxVar + ")");
        }
        if (cxVar == null) {
            throw new IllegalArgumentException("audioSink cannot be null.");
        }
        bn bnVar = this.e;
        bn.a aVar = new bn.a((byte) 4, new Object[]{cxVar, new Integer(60000)});
        Thread threadCurrentThread = Thread.currentThread();
        this.e.a();
        bnVar.a(aVar, this, threadCurrentThread);
    }

    @Override // bn.b
    public final void a(Object obj, Object obj2) {
        bn.a aVar = (bn.a) obj;
        switch (aVar.a) {
            case 1:
                a((cx) null, 0);
                break;
            case 2:
                Object[] objArr = (Object[]) aVar.b;
                a((cx) objArr[0], ((Integer) objArr[1]).intValue());
                break;
            case 3:
                b(false);
                break;
            case 4:
                Object[] objArr2 = (Object[]) aVar.b;
                cx cxVar = (cx) objArr2[0];
                int iIntValue = ((Integer) objArr2[1]).intValue();
                if (a.b()) {
                    a.b("RecorderImpl.handleStartCapturing(" + cxVar + ") _state:" + this.p);
                }
                this.i = iIntValue;
                this.f = cxVar;
                if (this.p != 1) {
                    if (this.p == 3) {
                        this.p = 2;
                    } else if (this.p == 6) {
                        this.p = 5;
                    }
                } else if (!this.k) {
                    h();
                    this.p = 4;
                } else {
                    this.c.a((bj.e) this);
                    this.p = 2;
                }
                break;
            case 5:
                a(false);
                break;
        }
    }

    public final void a(boolean z) {
        if (a.b()) {
            a.b("RecorderImpl.handleStopCapturing() _state:" + this.p);
        }
        if (this.p == 2) {
            j();
            if (z) {
                a("CAPTURE_TIMEOUT", (Object) null);
            }
            this.p = 3;
            return;
        }
        if (this.p != 4) {
            if (this.p == 5) {
                j();
                this.p = 6;
                return;
            }
            return;
        }
        j();
        if (z) {
            a("CAPTURE_TIMEOUT", (Object) null);
        }
        if (this.p == 4) {
            i();
        }
        if (!this.k) {
            this.p = 1;
        } else {
            this.c.a((bj.f) this);
            this.p = 6;
        }
    }

    @Override // bj.a
    public final void a(byte[] bArr, Object obj, bj.i iVar, bj.i iVar2, Float f) {
        byte[] bArr2;
        int i = 0;
        if (a.b()) {
            a.b("RecorderImpl.audioCallback() _state:" + this.p);
        }
        if (this.p == 4) {
            if (this.g == 0) {
                this.g = System.currentTimeMillis();
                a("StreamStart");
            }
            bi biVar = this.b;
            bx.a();
            if (bx.b(this.b)) {
                bArr2 = (byte[]) obj;
                i = iVar2.a;
            } else if (bx.a(this.b)) {
                i = iVar.a;
                bArr2 = bArr;
            } else {
                bArr2 = null;
            }
            if (a.b()) {
                a.b("========================= Recorder::audioCallback len[" + i + "] ======================");
            }
            try {
                this.f.a(bArr2, 0, i, false);
                a("BUFFER_RECORDED", (Object) f);
            } catch (dc e) {
                if (a.d()) {
                    a.d("RecorderImpl.audioCallback() TransactionProcessingException:" + e);
                }
                a("RECORD_ERROR", (Object) null);
                if (this.p == 4) {
                    i();
                }
                this.c.a((bj.m) this);
                this.p = 7;
            }
        }
    }

    @Override // bj.e
    public final void b() {
        if (a.b()) {
            a.b("RecorderImpl::endPointerStartedCallback() _state:" + this.p);
        }
        if (this.p == 2) {
            h();
            this.p = 4;
        } else if (this.p == 3) {
            this.c.a((bj.f) this);
            this.p = 6;
        }
    }

    @Override // bj.f
    public final void c() {
        if (a.b()) {
            a.b("RecorderImpl::endPointerStoppedCallback() _state:" + this.p);
        }
        if (this.p == 5) {
            this.c.a((bj.e) this);
            this.p = 2;
        } else if (this.p == 6) {
            this.p = 1;
        }
    }

    @Override // bj.g
    public final void d() {
        if (a.b()) {
            a.b("RecorderImpl.errorCallback() _state:" + this.p);
        }
        if (this.p == 1 || this.p == 2 || this.p == 3 || this.p == 4 || this.p == 5 || this.p == 6) {
            if (this.p == 4) {
                i();
            }
            a("RECORD_ERROR", (Object) null);
            this.p = 7;
        }
    }

    @Override // bj.l
    public final void e() {
        if (a.b()) {
            a.b("RecorderImpl::startOfSpeechCallback() _state:" + this.p);
        }
        if (this.p == 4) {
            a("START_OF_SPEECH", (Object) null);
        }
    }

    @Override // defpackage.ca
    public final void f() {
        if (a.b()) {
            a.b("RecorderImpl.startRecording()");
        }
        this.g = 0L;
        a("RecorderStart");
        bn bnVar = this.e;
        bn.a aVar = new bn.a((byte) 1, null);
        Thread threadCurrentThread = Thread.currentThread();
        this.e.a();
        bnVar.a(aVar, this, threadCurrentThread);
    }

    @Override // defpackage.ca
    public final void g() {
        if (a.b()) {
            a.b("RecorderImpl.stop()");
        }
        a("RecorderStop");
        bn bnVar = this.e;
        bn.a aVar = new bn.a((byte) 3, null);
        Thread threadCurrentThread = Thread.currentThread();
        this.e.a();
        bnVar.a(aVar, this, threadCurrentThread);
    }
}
