package com.flurry.sdk;

/* JADX INFO: loaded from: classes.dex */
public class cq {
    private static final String a = cq.class.getSimpleName();

    public static void a(r rVar) {
        if (rVar != null) {
            gd.a(3, a, "Firing onFetched, adObject=" + rVar);
            d dVar = new d();
            dVar.a = rVar;
            dVar.b = d.a.kOnFetched;
            dVar.b();
        }
    }

    public static void a(r rVar, av avVar) {
        if (rVar != null) {
            gd.a(3, a, "Firing onFetchFailed, adObject=" + rVar + ", errorCode=" + avVar);
            d dVar = new d();
            dVar.a = rVar;
            dVar.b = d.a.kOnFetchFailed;
            dVar.c = avVar;
            dVar.b();
        }
    }

    public static void b(r rVar) {
        if (rVar != null) {
            gd.a(3, a, "Firing onRendered, adObject=" + rVar);
            d dVar = new d();
            dVar.a = rVar;
            dVar.b = d.a.kOnRendered;
            dVar.b();
        }
    }

    public static void b(r rVar, av avVar) {
        if (rVar != null && avVar != null) {
            gd.a(3, a, "Firing onRenderFailed, adObject=" + rVar + ", errorCode=" + avVar);
            d dVar = new d();
            dVar.a = rVar;
            dVar.b = d.a.kOnRenderFailed;
            dVar.c = avVar;
            dVar.b();
        }
    }
}
