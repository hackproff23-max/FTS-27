package com.flurry.sdk;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public class jw<T> {
    final Class<? super T> a;
    final Type b;
    final int c;

    protected jw() {
        this.b = a(getClass());
        this.a = (Class<? super T>) iz.e(this.b);
        this.c = this.b.hashCode();
    }

    jw(Type type) {
        this.b = iz.d((Type) iy.a(type));
        this.a = (Class<? super T>) iz.e(this.b);
        this.c = this.b.hashCode();
    }

    static Type a(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        return iz.d(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
    }

    public final Class<? super T> a() {
        return this.a;
    }

    public final Type b() {
        return this.b;
    }

    public final int hashCode() {
        return this.c;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof jw) && iz.a(this.b, ((jw) obj).b);
    }

    public final String toString() {
        return iz.f(this.b);
    }

    public static jw<?> a(Type type) {
        return new jw<>(type);
    }

    public static <T> jw<T> b(Class<T> cls) {
        return new jw<>(cls);
    }
}
