package com.chartboost.sdk.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class de<T> {
    private final Map<Class<?>, T> a = dg.c();
    private final Map<Class<?>, T> b = df.a((dh) new a());

    public static <T> List<Class<?>> a(Class<T> cls) {
        return dd.a(cls);
    }

    private final class a implements dh<Class<?>, T> {
        private a() {
        }

        @Override // com.chartboost.sdk.impl.dh
        public T a(Class<?> cls) {
            Iterator<Class<?>> it = de.a((Class) cls).iterator();
            while (it.hasNext()) {
                T t = (T) de.this.a.get(it.next());
                if (t != null) {
                    return t;
                }
            }
            return null;
        }
    }

    public T a(Object obj) {
        return this.b.get(obj);
    }

    public T a(Class<?> cls, T t) {
        try {
            return this.a.put(cls, t);
        } finally {
            this.b.clear();
        }
    }

    public int a() {
        return this.a.size();
    }
}
