package com.flurry.sdk;

import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class fg {
    private static fg a;

    public static synchronized fg a() {
        if (a == null) {
            a = new fg();
        }
        return a;
    }

    public static void b() {
        a = null;
    }

    private fg() {
    }

    public String c() {
        return Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
    }

    public String d() {
        return TimeZone.getDefault().getID();
    }
}
