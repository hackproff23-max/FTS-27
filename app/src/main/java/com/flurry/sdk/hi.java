package com.flurry.sdk;

import com.facebook.ads.InterstitialAd;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes.dex */
public class hi implements ThreadFactory {
    private final ThreadGroup a;
    private final int b;

    public hi(String str, int i) {
        this.a = new ThreadGroup(str);
        this.b = i;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(this.a, runnable);
        thread.setName(this.a.getName() + InterstitialAd.SEPARATOR + thread.getId());
        thread.setPriority(this.b);
        return thread;
    }
}
