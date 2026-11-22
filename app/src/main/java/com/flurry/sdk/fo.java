package com.flurry.sdk;

import android.content.Context;
import android.os.SystemClock;

/* JADX INFO: loaded from: classes.dex */
public class fo implements hf {
    private static final String a = fo.class.getSimpleName();
    private long b = 0;
    private long c = 0;
    private long d = -1;
    private long e = 0;
    private long f = 0;

    @Override // com.flurry.sdk.hf
    public void a(Context context) {
        this.b = System.currentTimeMillis();
        this.c = SystemClock.elapsedRealtime();
        fp.a().b(new hq() { // from class: com.flurry.sdk.fo.1
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                fh.a().c();
            }
        });
    }

    @Override // com.flurry.sdk.hf
    public void b(Context context) {
        long jC = hc.a().c();
        if (jC > 0) {
            this.e = (System.currentTimeMillis() - jC) + this.e;
        }
    }

    @Override // com.flurry.sdk.hf
    public void c(Context context) {
        this.d = SystemClock.elapsedRealtime() - this.c;
    }

    @Override // com.flurry.sdk.hf
    public void a() {
    }

    public long b() {
        return this.b;
    }

    public long c() {
        return this.c;
    }

    public long d() {
        return this.d;
    }

    public long e() {
        return this.e;
    }

    public synchronized long f() {
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.c;
        if (jElapsedRealtime <= this.f) {
            jElapsedRealtime = this.f + 1;
            this.f = jElapsedRealtime;
        }
        this.f = jElapsedRealtime;
        return this.f;
    }
}
