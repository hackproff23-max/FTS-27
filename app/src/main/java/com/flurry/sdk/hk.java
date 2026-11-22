package com.flurry.sdk;

/* JADX INFO: loaded from: classes.dex */
public class hk {
    private static long a = 100;
    private static hk b = null;
    private final hl c = new hl();

    public static synchronized hk a() {
        if (b == null) {
            b = new hk();
        }
        return b;
    }

    public static synchronized void b() {
        if (b != null) {
            b.c();
            b = null;
        }
    }

    public hk() {
        this.c.a(a);
        this.c.a(true);
    }

    public synchronized void a(fy<hj> fyVar) {
        fz.a().a("com.flurry.android.sdk.TickEvent", fyVar);
        if (fz.a().b("com.flurry.android.sdk.TickEvent") > 0) {
            this.c.a();
        }
    }

    public synchronized void b(fy<hj> fyVar) {
        fz.a().b("com.flurry.android.sdk.TickEvent", fyVar);
        if (fz.a().b("com.flurry.android.sdk.TickEvent") == 0) {
            this.c.b();
        }
    }

    public synchronized void c() {
        fz.a().a("com.flurry.android.sdk.TickEvent");
        this.c.b();
    }
}
