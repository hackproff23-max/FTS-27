package com.flurry.sdk;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class gm<T> extends WeakReference<T> {
    public gm(T t) {
        super(t);
    }

    public boolean equals(Object obj) {
        Object obj2 = get();
        if (obj instanceof Reference) {
            obj = ((Reference) obj).get();
        }
        if (obj2 == null) {
            if (obj != null) {
                return false;
            }
            return true;
        }
        return obj2.equals(obj);
    }

    public int hashCode() {
        Object obj = get();
        return obj == null ? super.hashCode() : obj.hashCode();
    }
}
