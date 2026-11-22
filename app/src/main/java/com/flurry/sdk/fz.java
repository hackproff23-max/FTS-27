package com.flurry.sdk;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class fz {
    private static fz a = null;
    private static final String b = fz.class.getSimpleName();
    private final fu<String, gm<fy<?>>> c = new fu<>();
    private final fu<gm<fy<?>>, String> d = new fu<>();

    public static synchronized fz a() {
        if (a == null) {
            a = new fz();
        }
        return a;
    }

    public static synchronized void b() {
        if (a != null) {
            a.c();
            a = null;
        }
    }

    private fz() {
    }

    public synchronized void a(String str, fy<?> fyVar) {
        if (!TextUtils.isEmpty(str) && fyVar != null) {
            gm<fy<?>> gmVar = new gm<>(fyVar);
            if (!this.c.c(str, gmVar)) {
                this.c.a(str, gmVar);
                this.d.a(gmVar, str);
            }
        }
    }

    public synchronized void b(String str, fy<?> fyVar) {
        if (!TextUtils.isEmpty(str)) {
            gm<fy<?>> gmVar = new gm<>(fyVar);
            this.c.b(str, gmVar);
            this.d.b(gmVar, str);
        }
    }

    public synchronized void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            Iterator<gm<fy<?>>> it = this.c.a(str).iterator();
            while (it.hasNext()) {
                this.d.b(it.next(), str);
            }
            this.c.b(str);
        }
    }

    public synchronized void a(fy<?> fyVar) {
        if (fyVar != null) {
            gm<fy<?>> gmVar = new gm<>(fyVar);
            Iterator<String> it = this.d.a(gmVar).iterator();
            while (it.hasNext()) {
                this.c.b(it.next(), gmVar);
            }
            this.d.b(gmVar);
        }
    }

    public synchronized void c() {
        this.c.a();
        this.d.a();
    }

    public synchronized int b(String str) {
        return TextUtils.isEmpty(str) ? 0 : this.c.a(str).size();
    }

    public synchronized List<fy<?>> c(String str) {
        List<fy<?>> listEmptyList;
        if (TextUtils.isEmpty(str)) {
            listEmptyList = Collections.emptyList();
        } else {
            ArrayList arrayList = new ArrayList();
            Iterator<gm<fy<?>>> it = this.c.a(str).iterator();
            while (it.hasNext()) {
                fy fyVar = (fy) it.next().get();
                if (fyVar == null) {
                    it.remove();
                } else {
                    arrayList.add(fyVar);
                }
            }
            listEmptyList = arrayList;
        }
        return listEmptyList;
    }

    public void a(final fx fxVar) {
        if (fxVar != null) {
            for (final fy<?> fyVar : c(fxVar.a())) {
                fp.a().b(new hq() { // from class: com.flurry.sdk.fz.1
                    @Override // com.flurry.sdk.hq
                    public void safeRun() {
                        fyVar.notify(fxVar);
                    }
                });
            }
        }
    }
}
