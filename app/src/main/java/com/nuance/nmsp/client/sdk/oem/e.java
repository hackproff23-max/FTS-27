package com.nuance.nmsp.client.sdk.oem;

import android.util.Log;
import defpackage.bm;

/* JADX INFO: loaded from: classes.dex */
public final class e extends bm {
    private String a;

    public e(Class cls) {
        this.a = cls.getSimpleName();
    }

    @Override // defpackage.bm
    public final void a(Object obj) {
        Log.v("NMSP_", "[" + this.a + "] " + obj.toString());
    }

    @Override // defpackage.bm
    public final void a(Object obj, Throwable th) {
        th.printStackTrace();
        Log.e("NMSP", "[" + this.a + "] " + obj.toString(), th);
    }

    @Override // defpackage.bm
    public final boolean a() {
        return Log.isLoggable("NMSP_", 2);
    }

    @Override // defpackage.bm
    public final void b(Object obj) {
        Log.d("NMSP_", "[" + this.a + "] " + obj.toString());
    }

    @Override // defpackage.bm
    public final boolean b() {
        return Log.isLoggable("NMSP_", 3);
    }

    @Override // defpackage.bm
    public final void c(Object obj) {
        Log.i("NMSP", "[" + this.a + "] " + obj.toString());
    }

    @Override // defpackage.bm
    public final boolean c() {
        return Log.isLoggable("NMSP", 4);
    }

    @Override // defpackage.bm
    public final void d(Object obj) {
        Log.w("NMSP", "[" + this.a + "] " + obj.toString());
    }

    @Override // defpackage.bm
    public final boolean d() {
        return Log.isLoggable("NMSP", 5);
    }

    @Override // defpackage.bm
    public final void e(Object obj) {
        Log.e("NMSP", "[" + this.a + "] " + obj.toString());
    }

    @Override // defpackage.bm
    public final boolean e() {
        return Log.isLoggable("NMSP", 6);
    }

    @Override // defpackage.bm
    public final void f() {
    }

    @Override // defpackage.bm
    public final void g() {
    }
}
