package com.applovin.impl.sdk;

import com.facebook.ads.InterstitialAd;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes.dex */
class bp implements ThreadFactory {
    final /* synthetic */ bn a;
    private final String b;

    public bp(bn bnVar, String str) {
        this.a = bnVar;
        this.b = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "AppLovinSdk:" + this.b + InterstitialAd.SEPARATOR + cd.a(this.a.a.getSdkKey()));
        thread.setDaemon(true);
        thread.setPriority(10);
        thread.setUncaughtExceptionHandler(new bq(this));
        return thread;
    }
}
