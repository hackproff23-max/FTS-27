package com.flurry.sdk;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public final class ja {
    private final Map<Type, ic<?>> a;

    public ja(Map<Type, ic<?>> map) {
        this.a = map;
    }

    public <T> jf<T> a(jw<T> jwVar) {
        final Type typeB = jwVar.b();
        Class<? super T> clsA = jwVar.a();
        final ic<?> icVar = this.a.get(typeB);
        if (icVar != null) {
            return new jf<T>() { // from class: com.flurry.sdk.ja.1
                @Override // com.flurry.sdk.jf
                public T a() {
                    return (T) icVar.a(typeB);
                }
            };
        }
        final ic<?> icVar2 = this.a.get(clsA);
        if (icVar2 != null) {
            return new jf<T>() { // from class: com.flurry.sdk.ja.5
                @Override // com.flurry.sdk.jf
                public T a() {
                    return (T) icVar2.a(typeB);
                }
            };
        }
        jf<T> jfVarA = a(clsA);
        if (jfVarA == null) {
            jf<T> jfVarA2 = a(typeB, clsA);
            return jfVarA2 == null ? b(typeB, clsA) : jfVarA2;
        }
        return jfVarA;
    }

    private <T> jf<T> a(Class<? super T> cls) {
        try {
            final Constructor<? super T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new jf<T>() { // from class: com.flurry.sdk.ja.6
                @Override // com.flurry.sdk.jf
                public T a() {
                    try {
                        return (T) declaredConstructor.newInstance(null);
                    } catch (IllegalAccessException e) {
                        throw new AssertionError(e);
                    } catch (InstantiationException e2) {
                        throw new RuntimeException("Failed to invoke " + declaredConstructor + " with no args", e2);
                    } catch (InvocationTargetException e3) {
                        throw new RuntimeException("Failed to invoke " + declaredConstructor + " with no args", e3.getTargetException());
                    }
                }
            };
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private <T> jf<T> a(final Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            if (SortedSet.class.isAssignableFrom(cls)) {
                return new jf<T>() { // from class: com.flurry.sdk.ja.7
                    @Override // com.flurry.sdk.jf
                    public T a() {
                        return (T) new TreeSet();
                    }
                };
            }
            if (EnumSet.class.isAssignableFrom(cls)) {
                return new jf<T>() { // from class: com.flurry.sdk.ja.8
                    @Override // com.flurry.sdk.jf
                    public T a() {
                        if (type instanceof ParameterizedType) {
                            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
                            if (type2 instanceof Class) {
                                return (T) EnumSet.noneOf((Class) type2);
                            }
                            throw new ih("Invalid EnumSet type: " + type.toString());
                        }
                        throw new ih("Invalid EnumSet type: " + type.toString());
                    }
                };
            }
            if (Set.class.isAssignableFrom(cls)) {
                return new jf<T>() { // from class: com.flurry.sdk.ja.9
                    @Override // com.flurry.sdk.jf
                    public T a() {
                        return (T) new LinkedHashSet();
                    }
                };
            }
            if (Queue.class.isAssignableFrom(cls)) {
                return new jf<T>() { // from class: com.flurry.sdk.ja.10
                    @Override // com.flurry.sdk.jf
                    public T a() {
                        return (T) new LinkedList();
                    }
                };
            }
            return new jf<T>() { // from class: com.flurry.sdk.ja.11
                @Override // com.flurry.sdk.jf
                public T a() {
                    return (T) new ArrayList();
                }
            };
        }
        if (Map.class.isAssignableFrom(cls)) {
            if (SortedMap.class.isAssignableFrom(cls)) {
                return new jf<T>() { // from class: com.flurry.sdk.ja.12
                    @Override // com.flurry.sdk.jf
                    public T a() {
                        return (T) new TreeMap();
                    }
                };
            }
            if ((type instanceof ParameterizedType) && !String.class.isAssignableFrom(jw.a(((ParameterizedType) type).getActualTypeArguments()[0]).a())) {
                return new jf<T>() { // from class: com.flurry.sdk.ja.2
                    @Override // com.flurry.sdk.jf
                    public T a() {
                        return (T) new LinkedHashMap();
                    }
                };
            }
            return new jf<T>() { // from class: com.flurry.sdk.ja.3
                @Override // com.flurry.sdk.jf
                public T a() {
                    return (T) new je();
                }
            };
        }
        return null;
    }

    private <T> jf<T> b(final Type type, final Class<? super T> cls) {
        return new jf<T>() { // from class: com.flurry.sdk.ja.4
            private final ji d = ji.a();

            @Override // com.flurry.sdk.jf
            public T a() {
                try {
                    return (T) this.d.a(cls);
                } catch (Exception e) {
                    throw new RuntimeException("Unable to invoke no-args constructor for " + type + ". Register an InstanceCreator with Gson for this type may fix this problem.", e);
                }
            }
        };
    }

    public String toString() {
        return this.a.toString();
    }
}
