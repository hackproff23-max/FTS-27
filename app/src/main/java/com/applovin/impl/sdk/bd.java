package com.applovin.impl.sdk;

/* JADX INFO: loaded from: classes.dex */
public class bd {
    private static int a = 0;
    private final int b;
    private final String c;
    private final Object d;

    private bd(String str, Object obj) {
        if (str == null) {
            throw new IllegalArgumentException("No name specified");
        }
        if (obj == null) {
            throw new IllegalArgumentException("No default value specified");
        }
        this.c = str;
        this.d = obj;
        this.b = a;
        a++;
    }

    public int a() {
        return this.b;
    }

    Object a(Object obj) {
        return this.d.getClass().cast(obj);
    }

    public String b() {
        return this.c;
    }

    public Object c() {
        return this.d;
    }
}
