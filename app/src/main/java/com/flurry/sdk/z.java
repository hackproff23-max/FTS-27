package com.flurry.sdk;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class z {
    private static final String b = z.class.getSimpleName();
    final Map<String, ae> a = new HashMap();
    private final Map<String, ae> c = new LinkedHashMap();
    private final Map<String, ai> d = new LinkedHashMap();
    private a e = a.INIT;
    private final ak<byte[]> f;
    private final long g;
    private final int h;

    enum a {
        INIT,
        ACTIVE,
        PAUSED
    }

    z(String str, long j, long j2, boolean z) {
        this.f = new ak<>(new gt(), str, j, z);
        this.g = j2;
        this.h = Runtime.getRuntime().availableProcessors() > 1 ? 2 : 1;
    }

    public synchronized boolean a() {
        return a.ACTIVE.equals(this.e) || a.PAUSED.equals(this.e);
    }

    public synchronized boolean b() {
        return a.ACTIVE.equals(this.e);
    }

    public synchronized boolean c() {
        return a.PAUSED.equals(this.e);
    }

    public synchronized List<ae> d() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        synchronized (this.a) {
            arrayList.addAll(this.a.values());
        }
        return arrayList;
    }

    public synchronized void a(ae aeVar) {
        b(aeVar);
    }

    public synchronized void e() {
        if (!b()) {
            gd.a(3, b, "Precaching: Starting AssetCache");
            this.f.a();
            fp.a().b(new hq() { // from class: com.flurry.sdk.z.1
                @Override // com.flurry.sdk.hq
                public void safeRun() {
                    z.this.o();
                    z.this.m();
                }
            });
            this.e = a.ACTIVE;
        }
    }

    public synchronized void f() {
        if (a()) {
            gd.a(3, b, "Precaching: Stopping AssetCache");
            n();
            this.f.b();
            this.e = a.INIT;
        }
    }

    public synchronized void g() {
        if (a() && c()) {
            gd.a(3, b, "Precaching: Resuming AssetCache");
            fp.a().b(new hq() { // from class: com.flurry.sdk.z.2
                @Override // com.flurry.sdk.hq
                public void safeRun() {
                    z.this.o();
                    z.this.m();
                }
            });
            this.e = a.ACTIVE;
        }
    }

    public boolean a(String str, an anVar, long j) {
        if (!a() || TextUtils.isEmpty(str) || anVar == null) {
            return false;
        }
        if (!an.IMAGE.equals(anVar) && !an.VIDEO.equals(anVar)) {
            return false;
        }
        ae aeVarD = d(str);
        if (aeVarD == null) {
            ae aeVar = new ae(str, anVar, j);
            synchronized (this.a) {
                this.a.put(aeVar.a(), aeVar);
            }
            c(aeVar);
        } else if (!ah.COMPLETE.equals(d(aeVarD))) {
            c(aeVarD);
        }
        return true;
    }

    public void a(String str) {
        if (a() && !TextUtils.isEmpty(str)) {
            synchronized (this.a) {
                this.a.remove(str);
            }
            this.f.c(str);
        }
    }

    public void h() {
        if (a()) {
            Iterator<ae> it = j().iterator();
            while (it.hasNext()) {
                a(it.next().a());
            }
            this.f.c();
        }
    }

    public ah b(String str) {
        return !a() ? ah.NONE : d(d(str));
    }

    public al.b c(String str) {
        if (a() && !TextUtils.isEmpty(str)) {
            return this.f.a(str);
        }
        return null;
    }

    private ae d(String str) {
        ae aeVar;
        if (!a() || TextUtils.isEmpty(str)) {
            return null;
        }
        synchronized (this.a) {
            aeVar = this.a.get(str);
        }
        if (aeVar != null) {
            if (aeVar.d()) {
                gd.a(3, b, "Precaching: expiring cached asset: " + aeVar.a() + " asset exp: " + aeVar.c() + " device epoch" + System.currentTimeMillis());
                a(aeVar.a());
                aeVar = null;
            } else {
                d(aeVar);
                aeVar.e();
            }
        }
        return aeVar;
    }

    public void i() {
        if (a()) {
            Iterator<ae> it = j().iterator();
            while (it.hasNext()) {
                d(it.next());
            }
        }
    }

    public List<ae> j() {
        ArrayList arrayList;
        synchronized (this.a) {
            arrayList = new ArrayList(this.a.values());
        }
        return arrayList;
    }

    public List<ae> k() {
        i();
        return j();
    }

    private void b(ae aeVar) {
        if (aeVar != null && !TextUtils.isEmpty(aeVar.a()) && !this.a.containsKey(aeVar.a())) {
            gd.a(3, b, "Precaching: adding cached asset info from persisted storage: " + aeVar.a() + " asset exp: " + aeVar.c() + " saved time: " + aeVar.f());
            synchronized (this.a) {
                this.a.put(aeVar.a(), aeVar);
            }
        }
    }

    private void c(ae aeVar) {
        if (aeVar != null) {
            ah ahVarD = d(aeVar);
            if (!ah.COMPLETE.equals(ahVarD)) {
                if (ah.IN_PROGRESS.equals(ahVarD) || ah.QUEUED.equals(ahVarD)) {
                    synchronized (this.c) {
                        if (!this.c.containsKey(aeVar.a())) {
                            this.c.put(aeVar.a(), aeVar);
                        }
                    }
                } else {
                    gd.a(3, b, "Precaching: Queueing asset:" + aeVar.a());
                    f.a().a("precachingDownloadRequested", 1);
                    a(aeVar, ah.QUEUED);
                    synchronized (this.c) {
                        this.c.put(aeVar.a(), aeVar);
                    }
                }
                fp.a().b(new hq() { // from class: com.flurry.sdk.z.3
                    @Override // com.flurry.sdk.hq
                    public void safeRun() {
                        z.this.m();
                    }
                });
            }
        }
    }

    private ah d(ae aeVar) {
        if (aeVar == null) {
            return ah.NONE;
        }
        if (aeVar.d()) {
            return ah.NONE;
        }
        if (ah.COMPLETE.equals(aeVar.b()) && !this.f.d(aeVar.a())) {
            a(aeVar, ah.EVICTED);
        }
        return aeVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (b()) {
            gd.a(3, b, "Precaching: Download files");
            synchronized (this.c) {
                Iterator<ae> it = this.c.values().iterator();
                while (it.hasNext()) {
                    ae next = it.next();
                    if (this.f.d(next.a())) {
                        gd.a(3, b, "Precaching: Asset already cached.  Skipping download:" + next.a());
                        it.remove();
                        a(next, ah.COMPLETE);
                    } else if (ah.IN_PROGRESS.equals(d(next))) {
                        continue;
                    } else {
                        if (fn.a().b(this) >= this.h) {
                            gd.a(3, b, "Precaching: Download limit reached");
                            return;
                        }
                        e(next);
                    }
                }
                gd.a(3, b, "Precaching: No more files to download");
            }
        }
    }

    private void e(final ae aeVar) {
        f.a().a("precachingDownloadStarted", 1);
        gd.a(3, b, "Precaching: Submitting for download: " + aeVar.a());
        am amVar = new am(this.f, aeVar.a());
        amVar.a(aeVar.a());
        amVar.a(40000);
        amVar.a(this.f);
        amVar.a(new ai.a() { // from class: com.flurry.sdk.z.4
            @Override // com.flurry.sdk.ai.a
            public void a(ai aiVar) {
                synchronized (z.this.d) {
                    z.this.d.remove(aeVar.a());
                }
                z.this.f(aeVar);
                if (!aiVar.a()) {
                    gd.a(3, z.b, "Precaching: Download error: " + aeVar.a());
                    z.this.a(aeVar, ah.ERROR);
                    f.a().a("precachingDownloadError", 1);
                } else {
                    long jC = aiVar.c();
                    gd.a(3, z.b, "Precaching: Download success: " + aeVar.a() + " size: " + jC);
                    aeVar.a(jC);
                    z.this.a(aeVar, ah.COMPLETE);
                    f.a().a("precachingDownloadSuccess", 1);
                }
                fp.a().b(new hq() { // from class: com.flurry.sdk.z.4.1
                    @Override // com.flurry.sdk.hq
                    public void safeRun() {
                        z.this.m();
                    }
                });
            }
        });
        amVar.d();
        synchronized (this.d) {
            this.d.put(aeVar.a(), amVar);
        }
        a(aeVar, ah.IN_PROGRESS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(ae aeVar) {
        if (aeVar != null) {
            synchronized (this.c) {
                this.c.remove(aeVar.a());
            }
        }
    }

    private void n() {
        gd.a(3, b, "Precaching: Cancelling in-progress downloads");
        synchronized (this.d) {
            Iterator<Map.Entry<String, ai>> it = this.d.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().e();
            }
            this.d.clear();
        }
        synchronized (this.c) {
            Iterator<Map.Entry<String, ae>> it2 = this.c.entrySet().iterator();
            while (it2.hasNext()) {
                ae value = it2.next().getValue();
                if (!ah.COMPLETE.equals(d(value))) {
                    gd.a(3, b, "Precaching: Download cancelled: " + value.f());
                    a(value, ah.CANCELLED);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ae aeVar, ah ahVar) {
        if (aeVar != null && ahVar != null && !ahVar.equals(aeVar.b())) {
            gd.a(3, b, "Asset status changed for asset:" + aeVar.a() + " from:" + aeVar.b() + " to:" + ahVar);
            aeVar.a(ahVar);
            ad adVar = new ad();
            adVar.a = aeVar.a();
            adVar.b = ahVar;
            adVar.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        for (ae aeVar : j()) {
            if (!ah.COMPLETE.equals(d(aeVar))) {
                gd.a(3, b, "Precaching: expiring cached asset: " + aeVar.a() + " asset exp: " + aeVar.c() + " device epoch: " + System.currentTimeMillis());
                a(aeVar.a());
            }
        }
    }
}
