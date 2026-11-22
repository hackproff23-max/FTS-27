package com.flurry.sdk;

import android.content.Context;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class gf {
    private static final String a = gf.class.getSimpleName();
    private static final Map<Class<? extends gg>, ge> b = new LinkedHashMap();
    private final Map<Class<? extends gg>, gg> c = new LinkedHashMap();

    public static void a(Class<? extends gg> cls, int i) {
        if (cls != null) {
            synchronized (b) {
                b.put(cls, new ge(cls, i));
            }
        }
    }

    public synchronized void a(Context context) {
        ArrayList<ge> arrayList;
        try {
            synchronized (b) {
                arrayList = new ArrayList(b.values());
            }
            for (ge geVar : arrayList) {
                try {
                    if (geVar.b()) {
                        gg ggVarNewInstance = geVar.a().newInstance();
                        ggVarNewInstance.a(context);
                        this.c.put(geVar.a(), ggVarNewInstance);
                    }
                } catch (Exception e) {
                    gd.a(5, a, "Flurry Module for class " + geVar.a() + " is not available:", e);
                }
            }
            hc.a().a(context);
            ft.a();
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void a() {
        ft.b();
        hc.b();
        List<gg> listB = b();
        for (int size = listB.size() - 1; size >= 0; size--) {
            try {
                this.c.remove(listB.get(size).getClass()).b();
            } catch (Exception e) {
                gd.a(5, a, "Error destroying module:", e);
            }
        }
    }

    public gg a(Class<? extends gg> cls) {
        gg ggVar;
        if (cls == null) {
            return null;
        }
        synchronized (this.c) {
            ggVar = this.c.get(cls);
        }
        if (ggVar == null) {
            throw new IllegalStateException("Module was not registered/initialized. " + cls);
        }
        return ggVar;
    }

    private List<gg> b() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.c) {
            arrayList.addAll(this.c.values());
        }
        return arrayList;
    }
}
