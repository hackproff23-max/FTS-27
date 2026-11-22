package com.google.android.gms.internal;

/* JADX INFO: loaded from: classes.dex */
@ey
public abstract class gf {
    private final Runnable mv = new Runnable() { // from class: com.google.android.gms.internal.gf.1
        @Override // java.lang.Runnable
        public final void run() {
            gf.this.wu = Thread.currentThread();
            gf.this.cx();
        }
    };
    private volatile Thread wu;

    public final void cancel() {
        onStop();
        if (this.wu != null) {
            this.wu.interrupt();
        }
    }

    public abstract void cx();

    public abstract void onStop();

    public final void start() {
        gh.a(this.mv);
    }
}
