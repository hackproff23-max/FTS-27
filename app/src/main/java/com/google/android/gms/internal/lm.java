package com.google.android.gms.internal;

import com.google.android.gms.common.api.Scope;

/* JADX INFO: loaded from: classes.dex */
public class lm {
    public static Scope[] d(String[] strArr) {
        jx.b(strArr, "scopeStrings can't be null.");
        Scope[] scopeArr = new Scope[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            scopeArr[i] = new Scope(strArr[i]);
        }
        return scopeArr;
    }
}
