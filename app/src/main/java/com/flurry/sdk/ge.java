package com.flurry.sdk;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public final class ge {
    private final Class<? extends gg> a;
    private final int b;

    public ge(Class<? extends gg> cls, int i) {
        this.a = cls;
        this.b = i;
    }

    public Class<? extends gg> a() {
        return this.a;
    }

    public boolean b() {
        return this.a != null && Build.VERSION.SDK_INT >= this.b;
    }
}
