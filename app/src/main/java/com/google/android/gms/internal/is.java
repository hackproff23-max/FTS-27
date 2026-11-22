package com.google.android.gms.internal;

import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class is {
    private static boolean HN = false;
    private boolean HO;
    private boolean HP;
    private String HQ;
    protected final String mTag;

    public is(String str) {
        this(str, go());
    }

    public is(String str, boolean z) {
        this.mTag = str;
        this.HO = z;
        this.HP = false;
    }

    public static boolean go() {
        return HN;
    }

    public void a(String str, Object... objArr) {
        if (gn()) {
            Log.v(this.mTag, e(str, objArr));
        }
    }

    public void a(Throwable th, String str, Object... objArr) {
        if (gm() || HN) {
            Log.d(this.mTag, e(str, objArr), th);
        }
    }

    public void aK(String str) {
        this.HQ = TextUtils.isEmpty(str) ? null : String.format("[%s] ", str);
    }

    public void b(String str, Object... objArr) {
        if (gm() || HN) {
            Log.d(this.mTag, e(str, objArr));
        }
    }

    public void c(String str, Object... objArr) {
        Log.i(this.mTag, e(str, objArr));
    }

    public void d(String str, Object... objArr) {
        Log.w(this.mTag, e(str, objArr));
    }

    protected String e(String str, Object... objArr) {
        if (objArr.length != 0) {
            str = String.format(str, objArr);
        }
        return !TextUtils.isEmpty(this.HQ) ? this.HQ + str : str;
    }

    public boolean gm() {
        return this.HO;
    }

    public boolean gn() {
        return this.HP;
    }
}
