package com.flurry.sdk;

import com.google.android.vending.expansion.downloader.Constants;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class fn extends gc<gl> {
    private static fn a = null;

    public static synchronized fn a() {
        if (a == null) {
            a = new fn();
        }
        return a;
    }

    public static synchronized void b() {
        if (a != null) {
            a.c();
        }
        a = null;
    }

    protected fn() {
        super(fn.class.getName(), 0, 5, Constants.ACTIVE_THREAD_WATCHDOG, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(11, new ga()));
    }
}
