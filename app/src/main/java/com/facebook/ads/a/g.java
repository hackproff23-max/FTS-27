package com.facebook.ads.a;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public enum g {
    NONE,
    INSTALLED,
    NOT_INSTALLED;

    public static g a(String str) {
        if (ag.a(str)) {
            return NONE;
        }
        try {
            return valueOf(str.toUpperCase(Locale.US));
        } catch (IllegalArgumentException e) {
            return NONE;
        }
    }
}
