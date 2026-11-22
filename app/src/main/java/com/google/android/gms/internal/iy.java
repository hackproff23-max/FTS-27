package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public abstract class iy<T> {
    protected final String KP;
    protected final T KQ;
    private T KR = null;
    private static final Object mH = new Object();
    private static a KO = null;

    private interface a {
    }

    private static class b implements a {
        private final ContentResolver mContentResolver;

        public b(ContentResolver contentResolver) {
            this.mContentResolver = contentResolver;
        }
    }

    protected iy(String str, T t) {
        this.KP = str;
        this.KQ = t;
    }

    public static void H(Context context) {
        synchronized (mH) {
            if (KO == null) {
                KO = new b(context.getContentResolver());
            }
        }
    }

    public static iy<Integer> a(String str, Integer num) {
        return new iy<Integer>(str, num) { // from class: com.google.android.gms.internal.iy.2
        };
    }

    public static iy<Boolean> h(String str, boolean z) {
        return new iy<Boolean>(str, Boolean.valueOf(z)) { // from class: com.google.android.gms.internal.iy.1
        };
    }

    public static iy<String> l(String str, String str2) {
        return new iy<String>(str, str2) { // from class: com.google.android.gms.internal.iy.3
        };
    }

    public String getKey() {
        return this.KP;
    }
}
