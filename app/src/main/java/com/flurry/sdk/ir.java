package com.flurry.sdk;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class ir<T> {
    public abstract void a(jz jzVar, T t) throws IOException;

    public abstract T b(jx jxVar) throws IOException;

    public final ig a(T t) {
        try {
            jo joVar = new jo();
            a(joVar, t);
            return joVar.a();
        } catch (IOException e) {
            throw new ih(e);
        }
    }
}
