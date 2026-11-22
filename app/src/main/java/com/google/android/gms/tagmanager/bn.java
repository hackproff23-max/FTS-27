package com.google.android.gms.tagmanager;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
class bn {
    bn() {
    }

    public bm pO() {
        return pg() < 8 ? new av() : new aw();
    }

    int pg() {
        return Build.VERSION.SDK_INT;
    }
}
