package defpackage;

import com.nuance.nmdp.speechkit.recognitionresult.b;

/* JADX INFO: loaded from: classes.dex */
public final class ax extends Thread {
    private static b a = null;
    private static final Object b = new Object();
    private static int c;

    public static void a() {
        synchronized (b) {
            if (a == null) {
                a = new b(b);
            }
            c++;
        }
    }

    public static void a(Runnable runnable) {
        a(runnable, 0);
    }

    public static void a(final Runnable runnable, int i) {
        synchronized (b) {
            if (a == null) {
                b.c(null, "Job added while JobRunner not active");
            } else {
                a.a(new Runnable() { // from class: ax.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            runnable.run();
                        } catch (Throwable th) {
                            b.a(ax.a, "Error running job", th);
                        }
                    }
                }, i);
            }
        }
    }

    public static void b() {
        synchronized (b) {
            if (a != null) {
                c--;
                if (c < 0) {
                    b.b(a, "JobRunner shutdown more often than initialized");
                    c = 0;
                }
                if (c <= 0) {
                    final b bVar = a;
                    a(new Runnable() { // from class: ax.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            b bVar2 = bVar;
                            b.a();
                        }
                    }, 0);
                    a = null;
                }
            } else {
                b.c(null, "JobRunner shutdown when not active");
            }
        }
    }
}
