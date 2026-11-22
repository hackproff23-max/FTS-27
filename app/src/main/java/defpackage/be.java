package defpackage;

import com.nuance.nmdp.speechkit.recognitionresult.b;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public final class be {
    private static final ay a = new ay();
    private final ay.a b;
    private final bb c;
    private ca d;
    private final Object e;
    private final Object l;
    private final bz f = new bz() { // from class: be.2
        private int a = 0;
        private boolean b = false;

        private void a() {
            synchronized (be.this.k) {
                be.c(be.this);
                if (be.this.j) {
                    be.this.k.notify();
                    be.e(be.this);
                }
            }
        }

        @Override // defpackage.bz
        public final void a(ca caVar, String str, Object obj) {
            if (caVar != be.this.d) {
                b.b(be.this, "Event " + str + " received for invalid recorder");
                return;
            }
            if (str == "BUFFER_RECORDED") {
                if (obj instanceof Float) {
                    bb bbVar = be.this.c;
                    Object unused = be.this.e;
                    bbVar.a(((Float) obj).floatValue());
                    return;
                }
                return;
            }
            if (str == "STARTED") {
                this.b = true;
                bb bbVar2 = be.this.c;
                Object unused2 = be.this.e;
                bbVar2.a();
                return;
            }
            if (str == "STOPPED") {
                b.a(be.this, "Recorder stopped");
                a();
                bb bbVar3 = be.this.c;
                Object unused3 = be.this.e;
                bbVar3.a(this.a);
                return;
            }
            if (str == "RECORD_ERROR") {
                b.c(be.this, "Recorder error");
                this.a = 4;
                if (this.b) {
                    return;
                }
                a();
                bb bbVar4 = be.this.c;
                Object unused4 = be.this.e;
                bbVar4.a(this.a);
                return;
            }
            if (str == "END_OF_SPEECH") {
                b.a(be.this, "Recorder event (end of speech)");
                this.a = 2;
            } else if (str == "CAPTURE_TIMEOUT") {
                b.a(be.this, "Recorder event (timeout)");
                this.a = 3;
            }
        }
    };
    private boolean g = false;
    private boolean h = false;
    private boolean j = false;
    private boolean i = false;
    private final Object k = new Object();

    public be(df dfVar, boolean z, boolean z2, Object obj, Object obj2, bb bbVar) {
        this.c = bbVar;
        this.e = obj;
        this.l = obj2;
        this.b = a.c() == 0 ? null : a.e().d();
        ay ayVar = new ay();
        if (this.b == null) {
            if (z) {
                ayVar.a(new cz("ep.enable", "TRUE".getBytes(), cz.a.a));
            } else if (z2) {
                ayVar.a(new cz("ep.enable", "TRUE".getBytes(), cz.a.a));
                ayVar.a(new cz("ep.VadLongUtterance", "TRUE".getBytes(), cz.a.a));
            }
        }
        ayVar.a(new cz("USE_ENERGY_LEVEL", "TRUE".getBytes(), cz.a.a));
        ayVar.a(new cz("Android_Context", this.l, cz.a.a));
        try {
            bz bzVar = this.f;
            Vector vectorF = ayVar.f();
            bg bgVar = bg.a;
            if (bzVar == null) {
                throw new NullPointerException("NMSPAudioRecordListener can not be null!");
            }
            if (dfVar == null) {
                throw new NullPointerException("Manager can not be null!");
            }
            this.d = new de(bzVar, dfVar, vectorF, bgVar);
        } catch (Throwable th) {
            b.a(this, "Error creating recorder", th);
            this.d = null;
        }
    }

    static /* synthetic */ ay c() {
        return null;
    }

    static /* synthetic */ boolean c(be beVar) {
        beVar.i = true;
        return true;
    }

    static /* synthetic */ boolean e(be beVar) {
        beVar.j = false;
        return false;
    }

    public final void a() {
        if (this.g) {
            b.c(this, "Recorder already started");
        } else {
            this.g = true;
            if (this.d != null) {
                try {
                    b.a(this, "Starting recorder");
                    this.d.f();
                    return;
                } catch (Throwable th) {
                    b.a(this, "Error starting recorder", th);
                }
            }
        }
        bb bbVar = this.c;
        Object obj = this.e;
        bbVar.a(4);
    }

    public final void a(final cx cxVar) {
        b.a(this, "Capturing audio from recorder");
        if (this.b == null) {
            this.d.a(cxVar);
        } else {
            this.d.a(new cx() { // from class: be.1
                @Override // defpackage.cx
                public final void a(byte[] bArr, int i, int i2, boolean z) throws dc {
                    if (be.this.b == null) {
                        cxVar.a(bArr, i, i2, z);
                    } else if (be.this.b.a()) {
                        byte[] bArr2 = (byte[]) be.this.b.b();
                        boolean z2 = !be.this.b.a();
                        cxVar.a(bArr2, 0, bArr2.length, z2);
                        if (z2) {
                            ax.a(new Runnable() { // from class: be.1.1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    be.this.b();
                                }
                            });
                        }
                    }
                    be beVar = be.this;
                    be.c();
                }
            });
        }
    }

    public final void b() {
        if (!this.g || this.h) {
            return;
        }
        this.h = true;
        if (this.d != null) {
            synchronized (this.k) {
                try {
                    if (!this.i) {
                        b.a(this, "Stopping recorder");
                        this.d.g();
                        this.j = true;
                        while (!this.i) {
                            try {
                                this.k.wait();
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                } catch (Throwable th) {
                    b.a(this, "Error stopping recorder", th);
                    this.i = true;
                }
            }
            return;
        }
        b.c(this, "Can't stop recorder because it wasn't started");
        bb bbVar = this.c;
        Object obj = this.e;
        bbVar.a(4);
    }
}
