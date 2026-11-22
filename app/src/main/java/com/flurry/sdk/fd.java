package com.flurry.sdk;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class fd implements gg {
    private static final String a = fd.class.getSimpleName();

    public static synchronized fd a() {
        return (fd) fp.a().a(fd.class);
    }

    @Override // com.flurry.sdk.gg
    public void a(Context context) {
        hb.a((Class<? extends hf>) fo.class);
        fz.a();
        hk.a();
        hg.a();
        fr.a();
        fj.a();
        fe.a();
        fk.a();
        fh.a();
        fe.a();
        fm.a();
        fg.a();
        fn.a();
    }

    @Override // com.flurry.sdk.gg
    public void b() {
        fn.b();
        fg.b();
        fm.b();
        fe.b();
        fh.b();
        fk.b();
        fe.b();
        fj.b();
        fr.b();
        hg.b();
        hk.b();
        fz.b();
        hb.b((Class<? extends hf>) fo.class);
    }

    public long c() {
        fo foVarI = i();
        if (foVarI == null) {
            return 0L;
        }
        return foVarI.b();
    }

    public long d() {
        fo foVarI = i();
        if (foVarI == null) {
            return 0L;
        }
        return foVarI.c();
    }

    public long e() {
        fo foVarI = i();
        if (foVarI == null) {
            return -1L;
        }
        return foVarI.d();
    }

    public long f() {
        fo foVarI = i();
        if (foVarI == null) {
            return 0L;
        }
        return foVarI.f();
    }

    public long g() {
        fo foVarI = i();
        if (foVarI == null) {
            return 0L;
        }
        return foVarI.e();
    }

    public fj.a h() {
        return fj.a().d();
    }

    private fo i() {
        return a(hc.a().e());
    }

    private fo a(hb hbVar) {
        if (hbVar == null) {
            return null;
        }
        return (fo) hbVar.c(fo.class);
    }
}
