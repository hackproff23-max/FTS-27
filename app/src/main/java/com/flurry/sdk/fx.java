package com.flurry.sdk;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class fx {
    protected String f;

    public fx(String str) {
        this.f = "com.flurry.android.sdk.ReplaceMeWithAProperEventName";
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Event must have a name!");
        }
        this.f = str;
    }

    public String a() {
        return this.f;
    }

    public void b() {
        fz.a().a(this);
    }
}
