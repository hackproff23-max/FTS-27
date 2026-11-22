package com.flurry.sdk;

import android.content.Context;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class p {
    private final SparseArray<r> a = new SparseArray<>();
    private final fu<Context, r> b = new fu<>(new WeakHashMap());

    public synchronized void a(Context context, r rVar) {
        if (context != null && rVar != null) {
            this.a.put(rVar.d(), rVar);
            this.b.a(context, rVar);
        }
    }

    public synchronized boolean b(Context context, r rVar) {
        boolean zB;
        if (context == null || rVar == null) {
            zB = false;
        } else {
            this.a.remove(rVar.d());
            zB = this.b.b(context, rVar);
        }
        return zB;
    }

    public synchronized r a(int i) {
        return this.a.get(i);
    }

    public synchronized List<r> a(Context context) {
        try {
        } catch (Throwable th) {
            throw th;
        }
        return context == null ? Collections.emptyList() : new ArrayList<>(this.b.a(context));
    }

    public synchronized void b(Context context) {
        if (context != null) {
            Iterator<r> it = this.b.a(context).iterator();
            while (it.hasNext()) {
                it.next().b();
            }
        }
    }

    public synchronized void c(Context context) {
        if (context != null) {
            Iterator<r> it = this.b.a(context).iterator();
            while (it.hasNext()) {
                it.next().c();
            }
        }
    }

    public synchronized void d(Context context) {
        if (context != null) {
            Iterator<r> it = a(context).iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }
    }

    public synchronized void a() {
        Iterator<r> it = this.b.d().iterator();
        while (it.hasNext()) {
            it.next().m();
        }
    }
}
