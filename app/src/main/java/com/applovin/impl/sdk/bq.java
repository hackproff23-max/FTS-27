package com.applovin.impl.sdk;

/* JADX INFO: loaded from: classes.dex */
class bq implements Thread.UncaughtExceptionHandler {
    final /* synthetic */ bp a;

    bq(bp bpVar) {
        this.a = bpVar;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        this.a.a.b.e("TaskManager", "Caught unhandled exception", th);
    }
}
