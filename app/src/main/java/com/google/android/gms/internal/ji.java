package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public abstract class ji implements SafeParcelable {
    private boolean MC = false;
    private static final Object Mz = new Object();
    private static ClassLoader MA = null;
    private static Integer MB = null;

    private static boolean a(Class<?> cls) {
        try {
            return SafeParcelable.NULL.equals(cls.getField("NULL").get(null));
        } catch (IllegalAccessException e) {
            return false;
        } catch (NoSuchFieldException e2) {
            return false;
        }
    }

    protected static boolean aW(String str) {
        ClassLoader classLoaderHs = hs();
        if (classLoaderHs == null) {
            return true;
        }
        try {
            return a(classLoaderHs.loadClass(str));
        } catch (Exception e) {
            return false;
        }
    }

    protected static ClassLoader hs() {
        ClassLoader classLoader;
        synchronized (Mz) {
            classLoader = MA;
        }
        return classLoader;
    }

    protected static Integer ht() {
        Integer num;
        synchronized (Mz) {
            num = MB;
        }
        return num;
    }

    protected boolean hu() {
        return this.MC;
    }
}
