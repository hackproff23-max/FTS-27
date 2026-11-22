package com.flurry.sdk;

import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* JADX INFO: loaded from: classes.dex */
public class gb<V> extends FutureTask<V> {
    private final WeakReference<Callable<V>> a;
    private final WeakReference<Runnable> b;

    public gb(Runnable runnable, V v) {
        super(runnable, v);
        this.a = null;
        this.b = new WeakReference<>(runnable);
    }

    public Runnable a() {
        return this.b.get();
    }
}
