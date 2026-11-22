package com.flurry.sdk;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class hb {
    private static final List<Class<? extends hf>> b = new ArrayList();
    private final String a = hb.class.getSimpleName();
    private final Map<Class<? extends hf>, hf> c = new LinkedHashMap();

    public static void a(Class<? extends hf> cls) {
        if (cls != null) {
            synchronized (b) {
                b.add(cls);
            }
        }
    }

    public static void b(Class<? extends hf> cls) {
        if (cls != null) {
            synchronized (b) {
                b.remove(cls);
            }
        }
    }

    public hb() {
        ArrayList<Class<? extends hf>> arrayList;
        synchronized (b) {
            arrayList = new ArrayList(b);
        }
        for (Class<? extends hf> cls : arrayList) {
            try {
                hf hfVar = (hf) cls.newInstance();
                synchronized (this.c) {
                    try {
                        this.c.put(cls, hfVar);
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            } catch (Exception e) {
                gd.a(5, this.a, "Module session " + cls + " is not available:", e);
            }
        }
    }

    public hf c(Class<? extends hf> cls) {
        hf hfVar;
        if (cls == null) {
            return null;
        }
        synchronized (this.c) {
            hfVar = this.c.get(cls);
        }
        return hfVar;
    }

    public synchronized void a(Context context) {
        Iterator<hf> it = b().iterator();
        while (it.hasNext()) {
            it.next().a(context);
        }
    }

    public synchronized void b(Context context) {
        Iterator<hf> it = b().iterator();
        while (it.hasNext()) {
            it.next().b(context);
        }
    }

    public synchronized void c(Context context) {
        Iterator<hf> it = b().iterator();
        while (it.hasNext()) {
            it.next().c(context);
        }
    }

    public synchronized void a() {
        Iterator<hf> it = b().iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }

    private List<hf> b() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.c) {
            arrayList.addAll(this.c.values());
        }
        return arrayList;
    }
}
