package com.applovin.impl.sdk;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class bx {
    private static final bx a = new bx();
    private final Map c = new HashMap(2);
    private final Object b = new Object();

    private bx() {
    }

    static bx a() {
        return a;
    }

    bz a(String str) {
        bz bzVar;
        synchronized (this.b) {
            bzVar = (bz) this.c.remove(str);
        }
        return bzVar;
    }

    void a(String str, long j, String str2) {
        bz bzVar = new bz(this, str2, j);
        synchronized (this.b) {
            this.c.put(str, bzVar);
        }
    }
}
