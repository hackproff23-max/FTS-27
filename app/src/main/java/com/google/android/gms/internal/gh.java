package com.google.android.gms.internal;

import android.os.Process;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class gh {
    private static final ThreadFactory ww = new ThreadFactory() { // from class: com.google.android.gms.internal.gh.3
        private final AtomicInteger wA = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AdWorker #" + this.wA.getAndIncrement());
        }
    };
    private static final ExecutorService wx = Executors.newFixedThreadPool(10, ww);

    public static Future<Void> a(final Runnable runnable) {
        return submit(new Callable<Void>() { // from class: com.google.android.gms.internal.gh.1
            @Override // java.util.concurrent.Callable
            /* JADX INFO: renamed from: ds, reason: merged with bridge method [inline-methods] */
            public Void call() {
                runnable.run();
                return null;
            }
        });
    }

    public static <T> Future<T> submit(final Callable<T> callable) {
        try {
            return wx.submit(new Callable<T>() { // from class: com.google.android.gms.internal.gh.2
                @Override // java.util.concurrent.Callable
                public T call() throws Exception {
                    try {
                        Process.setThreadPriority(10);
                        return (T) callable.call();
                    } catch (Exception e) {
                        ga.e(e);
                        return null;
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            gr.d("Thread execution is rejected.", e);
            return new gk(null);
        }
    }
}
