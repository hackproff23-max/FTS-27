package defpackage;

import android.content.Context;
import android.os.Vibrator;
import com.nuance.nmdp.speechkit.recognitionresult.b;

/* JADX INFO: loaded from: classes.dex */
public final class e implements ba {
    private final int b;
    private boolean a = false;
    private Vibrator c = null;

    public e(int i) {
        this.b = i;
    }

    static /* synthetic */ Vibrator a(e eVar) {
        eVar.c = null;
        return null;
    }

    @Override // defpackage.ba
    public final void a() {
        if (this.c == null) {
            return;
        }
        this.c.cancel();
        this.c = null;
    }

    @Override // defpackage.ba
    public final void a(Object obj, final az azVar, final Object obj2) {
        this.c = (Vibrator) ((Context) obj).getSystemService("vibrator");
        if (this.c == null) {
            b.c(this, "Unable to get vibrator service");
            azVar.a(obj2);
            return;
        }
        if (this.a) {
            b.c(this, "Can't start disposed vibration prompt.");
            azVar.a(obj2);
            return;
        }
        try {
            b.a(this, "Starting vibration (" + this.b + " ms)");
            this.c.vibrate(this.b);
            ax.a(new Runnable() { // from class: e.1
                @Override // java.lang.Runnable
                public final void run() {
                    b.a(e.this, "Vibration finished");
                    azVar.c(obj2);
                    e.a(e.this);
                }
            }, this.b);
        } catch (Throwable th) {
            b.a(this, "Unable to vibrate", th);
            azVar.a(obj2);
        }
    }

    @Override // defpackage.ba
    public final boolean b() {
        return this.a;
    }

    @Override // defpackage.ba
    public final void c() {
        this.a = true;
        if (this.c != null) {
            this.c.cancel();
            this.c = null;
        }
    }
}
