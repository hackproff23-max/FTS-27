package com.facebook.ads.a;

import android.content.Context;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {
    protected final a a;
    protected e b;
    private final long c;
    private Context f;
    private volatile boolean g;
    private volatile boolean h;
    private final Runnable e = new Runnable() { // from class: com.facebook.ads.a.f.1
        @Override // java.lang.Runnable
        public void run() {
            f.this.h = false;
            f.this.d();
        }
    };
    private final Handler d = new Handler();

    public interface a {
        boolean a();

        void b();

        void c();

        boolean d();
    }

    public f(a aVar, long j, Context context) {
        this.f = context;
        this.a = aVar;
        this.c = j;
    }

    public e a() {
        return this.b;
    }

    public void a(e eVar) {
        this.b = eVar;
        this.g = false;
        this.h = false;
    }

    public synchronized void b() {
        if (!this.g && !this.h && this.b != null) {
            this.d.postDelayed(this.e, this.c);
            this.h = true;
        }
    }

    public synchronized void c() {
        if (this.h) {
            this.d.removeCallbacks(this.e);
            this.h = false;
        }
    }

    public synchronized void d() {
        if (this.b != null && !this.g) {
            if (this.a == null || this.a.a()) {
                if (this.a != null) {
                    this.a.c();
                }
                e();
                this.g = true;
                p.a(this.f, "Impression logged");
            } else {
                b();
            }
        }
    }

    protected abstract void e();
}
