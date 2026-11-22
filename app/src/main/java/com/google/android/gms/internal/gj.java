package com.google.android.gms.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
@ey
public class gj<T> implements Future<T> {
    private final Object mH = new Object();
    private T wF = null;
    private boolean wG = false;
    private boolean qj = false;

    public void a(T t) {
        synchronized (this.mH) {
            if (this.wG) {
                throw new IllegalStateException("Provided CallbackFuture with multiple values.");
            }
            this.wG = true;
            this.wF = t;
            this.mH.notifyAll();
        }
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean mayInterruptIfRunning) {
        boolean z = false;
        if (mayInterruptIfRunning) {
            synchronized (this.mH) {
                if (!this.wG) {
                    this.qj = true;
                    this.wG = true;
                    this.mH.notifyAll();
                    z = true;
                }
            }
        }
        return z;
    }

    @Override // java.util.concurrent.Future
    public T get() {
        T t;
        synchronized (this.mH) {
            if (!this.wG) {
                try {
                    this.mH.wait();
                } catch (InterruptedException e) {
                }
            }
            if (this.qj) {
                throw new CancellationException("CallbackFuture was cancelled.");
            }
            t = this.wF;
        }
        return t;
    }

    @Override // java.util.concurrent.Future
    public T get(long timeout, TimeUnit unit) throws TimeoutException {
        T t;
        synchronized (this.mH) {
            if (!this.wG) {
                try {
                    long millis = unit.toMillis(timeout);
                    if (millis != 0) {
                        this.mH.wait(millis);
                    }
                } catch (InterruptedException e) {
                }
            }
            if (!this.wG) {
                throw new TimeoutException("CallbackFuture timed out.");
            }
            if (this.qj) {
                throw new CancellationException("CallbackFuture was cancelled.");
            }
            t = this.wF;
        }
        return t;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        boolean z;
        synchronized (this.mH) {
            z = this.qj;
        }
        return z;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        boolean z;
        synchronized (this.mH) {
            z = this.wG;
        }
        return z;
    }
}
