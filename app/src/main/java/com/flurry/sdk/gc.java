package com.flurry.sdk;

import com.flurry.sdk.hr;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class gc<T extends hr> {
    private static final String a = gc.class.getSimpleName();
    private final fu<Object, T> b = new fu<>();
    private final HashMap<T, Object> c = new HashMap<>();
    private final HashMap<T, Future<?>> d = new HashMap<>();
    private final ThreadPoolExecutor e;

    public gc(String str, int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue) {
        this.e = new ThreadPoolExecutor(i, i2, j, timeUnit, blockingQueue) { // from class: com.flurry.sdk.gc.1
            @Override // java.util.concurrent.ThreadPoolExecutor
            protected void beforeExecute(Thread thread, Runnable runnable) {
                super.beforeExecute(thread, runnable);
                final hr hrVarA = gc.this.a(runnable);
                if (hrVarA != null) {
                    new hq() { // from class: com.flurry.sdk.gc.1.1
                        @Override // com.flurry.sdk.hq
                        public void safeRun() {
                            hrVarA.j();
                        }
                    }.run();
                }
            }

            @Override // java.util.concurrent.ThreadPoolExecutor
            protected void afterExecute(Runnable runnable, Throwable th) {
                super.afterExecute(runnable, th);
                final hr hrVarA = gc.this.a(runnable);
                if (hrVarA != null) {
                    synchronized (gc.this.d) {
                        gc.this.d.remove(hrVarA);
                    }
                    gc.this.b(hrVarA);
                    new hq() { // from class: com.flurry.sdk.gc.1.2
                        @Override // com.flurry.sdk.hq
                        public void safeRun() {
                            hrVarA.k();
                        }
                    }.run();
                }
            }

            @Override // java.util.concurrent.AbstractExecutorService
            protected <V> RunnableFuture<V> newTaskFor(Callable<V> callable) {
                throw new UnsupportedOperationException("Callable not supported");
            }

            @Override // java.util.concurrent.AbstractExecutorService
            protected <V> RunnableFuture<V> newTaskFor(Runnable runnable, V v) {
                gb gbVar = new gb(runnable, v);
                synchronized (gc.this.d) {
                    gc.this.d.put((hr) runnable, gbVar);
                }
                return gbVar;
            }
        };
        this.e.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy() { // from class: com.flurry.sdk.gc.2
            @Override // java.util.concurrent.ThreadPoolExecutor.DiscardPolicy, java.util.concurrent.RejectedExecutionHandler
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                super.rejectedExecution(runnable, threadPoolExecutor);
                final hr hrVarA = gc.this.a(runnable);
                if (hrVarA != null) {
                    synchronized (gc.this.d) {
                        gc.this.d.remove(hrVarA);
                    }
                    gc.this.b(hrVarA);
                    new hq() { // from class: com.flurry.sdk.gc.2.1
                        @Override // com.flurry.sdk.hq
                        public void safeRun() {
                            hrVarA.l();
                        }
                    }.run();
                }
            }
        });
        this.e.setThreadFactory(new hi(str, 1));
    }

    public synchronized void a(Object obj, T t) {
        if (obj != null && t != null) {
            b(obj, t);
            this.e.submit(t);
        }
    }

    public synchronized void a(Object obj) {
        if (obj != null) {
            HashSet hashSet = new HashSet();
            hashSet.addAll(this.b.a(obj));
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                a((hr) it.next());
            }
        }
    }

    public synchronized void a(final T t) {
        Future<?> futureRemove;
        if (t != null) {
            synchronized (this.d) {
                futureRemove = this.d.remove(t);
            }
            b((hr) t);
            if (futureRemove != null) {
                futureRemove.cancel(true);
            }
            new hq() { // from class: com.flurry.sdk.gc.3
                @Override // com.flurry.sdk.hq
                public void safeRun() {
                    t.h();
                }
            }.run();
        }
    }

    public synchronized void c() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.b.c());
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
    }

    public synchronized long b(Object obj) {
        return obj == null ? 0L : this.b.a(obj).size();
    }

    private synchronized void b(Object obj, T t) {
        this.b.a(obj, t);
        this.c.put(t, obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b(T t) {
        c(this.c.get(t), t);
    }

    private synchronized void c(Object obj, T t) {
        this.b.b(obj, t);
        this.c.remove(t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public T a(Runnable runnable) {
        if (runnable instanceof gb) {
            return (T) ((gb) runnable).a();
        }
        if (runnable instanceof hr) {
            return (T) runnable;
        }
        gd.a(6, a, "Unknown runnable class: " + runnable.getClass().getName());
        return null;
    }
}
