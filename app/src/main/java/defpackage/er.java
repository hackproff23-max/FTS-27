package defpackage;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class er {
    public er() {
        d.a(getClass());
    }

    public static Class<?> a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("getClassForName failed", e);
        }
    }

    public static Object a(Class<?> cls, String str) {
        try {
            return cls.getField(str).get(null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("getFieldValue failed", e);
        }
    }

    public static Object a(Class<?> cls, String str, Object obj) {
        try {
            return cls.getField(str).get(null);
        } catch (Exception e) {
            return obj;
        }
    }

    public static Constructor<?> a(Class<?> cls, Class<?>... clsArr) {
        try {
            return cls.getConstructor(clsArr);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("getConstructor failed", e);
        }
    }

    public static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("getMethod failed", e);
        }
    }

    public static Method b(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (Exception e) {
            return null;
        }
    }
}
