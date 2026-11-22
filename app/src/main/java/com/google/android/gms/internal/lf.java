package com.google.android.gms.internal;

import android.os.SystemClock;

/* JADX INFO: loaded from: classes.dex */
public final class lf implements ld {
    private static lf Of;

    /* JADX INFO: renamed from: if, reason: not valid java name */
    public static synchronized ld m6if() {
        if (Of == null) {
            Of = new lf();
        }
        return Of;
    }

    @Override // com.google.android.gms.internal.ld
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override // com.google.android.gms.internal.ld
    public long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }
}
