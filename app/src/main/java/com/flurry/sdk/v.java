package com.flurry.sdk;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class v {
    private final SparseArray<u> a = new SparseArray<>();
    private final fu<String, u> b = new fu<>();

    public synchronized void a(Context context, String str, u uVar) {
        if (context != null) {
            if (!TextUtils.isEmpty(str) && uVar != null) {
                this.a.put(uVar.d(), uVar);
                this.b.a(str, uVar);
            }
        }
    }

    public synchronized boolean a(String str, u uVar) {
        boolean zB;
        if (TextUtils.isEmpty(str) || uVar == null) {
            zB = false;
        } else {
            this.a.remove(uVar.d());
            zB = this.b.b(str, uVar);
        }
        return zB;
    }

    public synchronized u a(int i) {
        return this.a.get(i);
    }

    public synchronized u a(String str) {
        u uVar = null;
        synchronized (this) {
            if (!TextUtils.isEmpty(str)) {
                List<u> listA = this.b.a(str);
                if (!listA.isEmpty()) {
                    uVar = listA.get(listA.size() - 1);
                }
            }
        }
        return uVar;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x000a  */
    public synchronized u a(Context context, String str) {
        u next;
        if (context == null) {
            next = null;
        } else if (TextUtils.isEmpty(str)) {
            next = null;
        } else {
            Iterator<u> it = this.b.a(str).iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!context.equals(next.e()));
            if (next != null) {
                this.b.b(str, next);
                this.b.a(str, next);
            }
        }
        return next;
    }

    public synchronized void b(Context context, String str) {
        u uVarA;
        if (context != null) {
            if (!TextUtils.isEmpty(str) && (uVarA = a(context, str)) != null) {
                this.b.b(str, uVarA);
                uVarA.a();
            }
        }
    }

    public synchronized void a(Context context) {
        if (context != null) {
            for (u uVar : this.b.d()) {
                if (context.equals(uVar.e())) {
                    uVar.b();
                }
            }
        }
    }

    public synchronized void b(Context context) {
        if (context != null) {
            for (u uVar : this.b.d()) {
                if (context.equals(uVar.e())) {
                    uVar.c();
                }
            }
        }
    }

    public synchronized void c(Context context) {
        if (context != null) {
            Iterator<u> it = this.b.d().iterator();
            while (it.hasNext()) {
                u next = it.next();
                if (context.equals(next.e())) {
                    it.remove();
                    next.a();
                }
            }
        }
    }

    public synchronized void a() {
        Iterator<u> it = this.b.d().iterator();
        while (it.hasNext()) {
            it.next().m();
        }
    }
}
