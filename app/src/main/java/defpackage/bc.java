package defpackage;

import com.nuance.nmdp.speechkit.recognitionresult.b;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public final class bc {
    private final az b;
    private final df c;
    private z d;
    private final Object e;
    private final Object k;
    private final by a = new by() { // from class: bc.1
        private boolean a = false;

        private void a() {
            synchronized (bc.this.j) {
                bc.b(bc.this);
                if (bc.this.i) {
                    bc.this.j.notify();
                    bc.d(bc.this);
                }
            }
        }

        @Override // defpackage.by
        public final void a(String str) {
            if (str == "BUFFER_PLAYED") {
                if (this.a) {
                    return;
                }
                b.a(bc.this, "First audio buffer played");
                bc.this.b.b(bc.this.e);
                this.a = true;
                return;
            }
            if (str != "BUFFERING") {
                if (str == "STARTED") {
                    b.a(bc.this, "Audio playback started");
                    return;
                }
                if (str == "STOPPED") {
                    b.a(bc.this, "Audio playback stopped");
                    a();
                    bc.this.b.c(bc.this.e);
                } else if (str == "PLAYBACK_ERROR") {
                    b.c(bc.this, "Audio playback error");
                    a();
                    bc.this.b.a(bc.this.e);
                }
            }
        }
    };
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;
    private Object j = new Object();

    public bc(df dfVar, Object obj, Object obj2, az azVar) {
        this.b = azVar;
        this.c = dfVar;
        this.e = obj;
        this.k = obj2;
        ay ayVar = new ay();
        ayVar.a(new cz("Android_Context", this.k, cz.a.a));
        try {
            by byVar = this.a;
            df dfVar2 = this.c;
            Vector vectorF = ayVar.f();
            bh bhVar = bh.a;
            if (byVar == null) {
                throw new NullPointerException("NMSPAudioPlaybackListener can not be null!");
            }
            if (dfVar2 == null) {
                throw new NullPointerException("Manager can not be null!");
            }
            this.d = new dd(byVar, dfVar2, vectorF, bhVar);
        } catch (Throwable th) {
            b.a(this, "Error creating player", th);
            this.d = null;
        }
    }

    static /* synthetic */ boolean b(bc bcVar) {
        bcVar.h = true;
        return true;
    }

    static /* synthetic */ boolean d(bc bcVar) {
        bcVar.i = false;
        return false;
    }

    public final void a() {
        if (this.f) {
            b.c(this, "Player already started");
        } else if (this.d != null) {
            this.f = true;
            try {
                b.a(this, "Starting audio player");
                this.d.a();
                return;
            } catch (Throwable th) {
                b.a(this, "Error starting player", th);
            }
        }
        this.b.a(this.e);
    }

    public final void b() {
        if (!this.f || this.g) {
            return;
        }
        if (this.d == null) {
            this.b.a(this.e);
            return;
        }
        this.g = true;
        synchronized (this.j) {
            try {
                if (!this.h) {
                    b.a(this, "Stopping audio player");
                    this.d.a_();
                    this.i = true;
                    while (!this.h) {
                        try {
                            this.j.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                }
            } catch (Throwable th) {
                b.a(this, "Error stopping player", th);
                this.h = true;
            }
        }
    }

    public final z c() {
        return this.d;
    }
}
