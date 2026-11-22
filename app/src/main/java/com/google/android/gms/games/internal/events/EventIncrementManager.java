package com.google.android.gms.games.internal.events;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class EventIncrementManager {
    private final AtomicReference<EventIncrementCache> acq = new AtomicReference<>();

    public void flush() {
        EventIncrementCache eventIncrementCache = this.acq.get();
        if (eventIncrementCache != null) {
            eventIncrementCache.flush();
        }
    }

    protected abstract EventIncrementCache lF();

    public void o(String str, int i) {
        EventIncrementCache eventIncrementCacheLF = this.acq.get();
        if (eventIncrementCacheLF == null) {
            eventIncrementCacheLF = lF();
            if (!this.acq.compareAndSet(null, eventIncrementCacheLF)) {
                eventIncrementCacheLF = this.acq.get();
            }
        }
        eventIncrementCacheLF.y(str, i);
    }
}
