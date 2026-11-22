package com.flurry.sdk;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ht {
    private static ht a;
    private final Map<Thread.UncaughtExceptionHandler, Void> c = new WeakHashMap();
    private final Thread.UncaughtExceptionHandler b = Thread.getDefaultUncaughtExceptionHandler();

    public static synchronized ht a() {
        if (a == null) {
            a = new ht();
        }
        return a;
    }

    public static synchronized void b() {
        if (a != null) {
            a.d();
        }
        a = null;
    }

    public void a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        synchronized (this.c) {
            this.c.put(uncaughtExceptionHandler, null);
        }
    }

    private Set<Thread.UncaughtExceptionHandler> c() {
        Set<Thread.UncaughtExceptionHandler> setKeySet;
        synchronized (this.c) {
            setKeySet = this.c.keySet();
        }
        return setKeySet;
    }

    private ht() {
        Thread.setDefaultUncaughtExceptionHandler(new a());
    }

    private void d() {
        Thread.setDefaultUncaughtExceptionHandler(this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Thread thread, Throwable th) {
        Iterator<Thread.UncaughtExceptionHandler> it = c().iterator();
        while (it.hasNext()) {
            it.next().uncaughtException(thread, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Thread thread, Throwable th) {
        if (this.b != null) {
            try {
                this.b.uncaughtException(thread, th);
            } catch (Throwable th2) {
            }
        }
    }

    final class a implements Thread.UncaughtExceptionHandler {
        private a() {
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            ht.this.a(thread, th);
            ht.this.b(thread, th);
        }
    }
}
