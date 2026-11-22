package com.flurry.sdk;

/* JADX INFO: loaded from: classes.dex */
public enum dd {
    Unknown("unknown"),
    Streaming("streaming"),
    Progressive("progressive");

    private String d;

    dd(String str) {
        this.d = str;
    }

    public String a() {
        return this.d;
    }

    public static dd a(String str) {
        if (Streaming.a().equals(str)) {
            return Streaming;
        }
        if (Progressive.a().equals(str)) {
            return Progressive;
        }
        return Unknown;
    }
}
