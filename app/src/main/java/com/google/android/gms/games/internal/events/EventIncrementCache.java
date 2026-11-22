package com.google.android.gms.games.internal.events;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class EventIncrementCache {
    private Handler acl;
    private boolean acm;
    private int aco;
    final Object ack = new Object();
    private HashMap<String, AtomicInteger> acn = new HashMap<>();

    public EventIncrementCache(Looper looper, int flushIntervalMillis) {
        this.acl = new Handler(looper);
        this.aco = flushIntervalMillis;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lX() {
        synchronized (this.ack) {
            this.acm = false;
            flush();
        }
    }

    public void flush() {
        synchronized (this.ack) {
            for (Map.Entry<String, AtomicInteger> entry : this.acn.entrySet()) {
                r(entry.getKey(), entry.getValue().get());
            }
            this.acn.clear();
        }
    }

    protected abstract void r(String str, int i);

    public void y(String str, int i) {
        synchronized (this.ack) {
            if (!this.acm) {
                this.acm = true;
                this.acl.postDelayed(new Runnable() { // from class: com.google.android.gms.games.internal.events.EventIncrementCache.1
                    @Override // java.lang.Runnable
                    public void run() {
                        EventIncrementCache.this.lX();
                    }
                }, this.aco);
            }
            AtomicInteger atomicInteger = this.acn.get(str);
            if (atomicInteger == null) {
                atomicInteger = new AtomicInteger();
                this.acn.put(str, atomicInteger);
            }
            atomicInteger.addAndGet(i);
        }
    }
}
