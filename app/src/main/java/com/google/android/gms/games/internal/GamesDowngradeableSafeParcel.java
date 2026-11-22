package com.google.android.gms.games.internal;

import com.google.android.gms.internal.ji;
import com.google.android.gms.internal.lg;

/* JADX INFO: loaded from: classes.dex */
public abstract class GamesDowngradeableSafeParcel extends ji {
    protected static boolean c(Integer num) {
        if (num == null) {
            return false;
        }
        return lg.aV(num.intValue());
    }
}
