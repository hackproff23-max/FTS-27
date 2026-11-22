package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import java.util.LinkedList;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
class ax {
    private final int a;
    private final Queue b;
    private final Object c;

    ax(int i) {
        this.a = i > 4 ? 4 : i;
        this.b = new LinkedList();
        this.c = new Object();
    }

    int a() {
        int size;
        synchronized (this.c) {
            size = this.b.size();
        }
        return size;
    }

    void a(AppLovinAd appLovinAd) {
        synchronized (this.c) {
            if (!c()) {
                this.b.offer(appLovinAd);
            }
        }
    }

    int b() {
        return this.a;
    }

    boolean c() {
        boolean z;
        synchronized (this.c) {
            z = a() >= this.a;
        }
        return z;
    }

    boolean d() {
        boolean z;
        synchronized (this.c) {
            z = a() == 0;
        }
        return z;
    }

    AppLovinAd e() {
        AppLovinAd appLovinAd;
        try {
            synchronized (this.c) {
                try {
                    appLovinAd = !d() ? (AppLovinAd) this.b.poll() : null;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return appLovinAd;
        } catch (Exception e) {
            return null;
        }
    }
}
