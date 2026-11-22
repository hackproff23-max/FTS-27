package com.nuance.nmsp.client.sdk.oem;

import android.os.Looper;
import defpackage.bm;
import defpackage.bn;
import defpackage.bq;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public class f implements bn {
    private static final bm a = defpackage.d.a(f.class);
    private final Hashtable<bq, b> d = new Hashtable<>();
    private final com.nuance.nmsp.client.sdk.oem.a b = new com.nuance.nmsp.client.sdk.oem.a();
    private final Thread c = new Thread(new Runnable() { // from class: com.nuance.nmsp.client.sdk.oem.f.1
        @Override // java.lang.Runnable
        public final void run() {
            Looper.prepare();
            f.this.b.a();
            Looper.loop();
        }
    });

    public static class a {
        public Thread a;
        public bn.b b;
        public Object c;

        public a(Object obj, bn.b bVar) {
            this.c = obj;
            this.b = bVar;
        }
    }

    public class b implements Runnable {
        private bq a;

        public b(bq bqVar) {
            this.a = bqVar;
            f.this.d.put(bqVar, this);
        }

        @Override // java.lang.Runnable
        public final void run() {
            b bVar = (b) f.this.d.remove(this.a);
            if (f.a.b()) {
                f.a.b("TIMER run() _pendingTimerTasks.size():" + f.this.d.size() + ", this:" + this + ", r:" + bVar);
            }
            if (bVar != null) {
                bVar.a.run();
            }
        }
    }

    public f() {
        this.c.start();
    }

    @Override // defpackage.bn
    public final void a(bq bqVar, long j) {
        b bVar = new b(bqVar);
        if (a.b()) {
            a.b("TIMER _handler.postDelayed(" + bVar + ")");
        }
        this.b.postDelayed(bVar, j);
    }

    @Override // defpackage.bn
    public final void a(Object obj, bn.b bVar, Object obj2) {
        final a aVar = new a(obj, bVar);
        aVar.a = (Thread) obj2;
        this.b.post(new Runnable() { // from class: com.nuance.nmsp.client.sdk.oem.f.2
            @Override // java.lang.Runnable
            public final void run() {
                if (f.a.a()) {
                    f.a.a("Executing Message");
                }
                aVar.b.a(aVar.c, aVar.a);
                if (f.a.a()) {
                    f.a.a("Done Executing Message");
                }
            }
        });
    }

    @Override // defpackage.bn
    public final boolean a(bq bqVar) {
        b bVarRemove = this.d.remove(bqVar);
        if (a.b()) {
            a.b("TIMER cancelTask() _pendingTimerTasks.size():" + this.d.size());
        }
        if (bVarRemove != null) {
            if (a.b()) {
                a.b("TIMER _handler.removeCallbacks(" + bVarRemove + ")");
            }
            this.b.removeCallbacks(bVarRemove);
        }
        return bVarRemove != null;
    }

    @Override // defpackage.bn
    public final Object[] a() {
        return new Object[]{Thread.currentThread()};
    }

    @Override // defpackage.bn
    public final Object b() {
        return Thread.currentThread();
    }

    @Override // defpackage.bn
    public final void c() {
        this.b.post(new Runnable() { // from class: com.nuance.nmsp.client.sdk.oem.f.3
            @Override // java.lang.Runnable
            public final void run() {
                Looper.myLooper().quit();
            }
        });
    }
}
