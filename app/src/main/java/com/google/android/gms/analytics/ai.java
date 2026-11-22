package com.google.android.gms.analytics;

import android.content.Context;
import android.util.DisplayMetrics;

/* JADX INFO: loaded from: classes.dex */
class ai implements q {
    private static ai Cc;
    private static Object xO = new Object();
    private final Context mContext;

    protected ai(Context context) {
        this.mContext = context;
    }

    public static ai fl() {
        ai aiVar;
        synchronized (xO) {
            aiVar = Cc;
        }
        return aiVar;
    }

    public static void y(Context context) {
        synchronized (xO) {
            if (Cc == null) {
                Cc = new ai(context);
            }
        }
    }

    public boolean ac(String str) {
        return "&sr".equals(str);
    }

    protected String fm() {
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
    }

    @Override // com.google.android.gms.analytics.q
    public String getValue(String field) {
        if (field != null && field.equals("&sr")) {
            return fm();
        }
        return null;
    }
}
