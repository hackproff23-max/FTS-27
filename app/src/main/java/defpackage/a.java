package defpackage;

import android.os.Handler;
import com.nuance.nmdp.speechkit.recognitionresult.b;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    protected final Object a = new Object();
    private final Handler b;

    public a(Object obj) {
        this.b = (Handler) obj;
    }

    protected final void a(Runnable runnable) {
        if (this.b == null) {
            try {
                runnable.run();
                return;
            } catch (Throwable th) {
                b.a(this, "Exception in application callback", th);
                return;
            }
        }
        try {
            if (this.b.post(runnable)) {
                return;
            }
            b.c(this, "Unable to post callback to handler");
        } catch (Throwable th2) {
            b.a(this, "Exception posting callback to handler", th2);
        }
    }
}
