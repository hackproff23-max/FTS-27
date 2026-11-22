package com.flurry.sdk;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class o implements r {
    private static final String a = o.class.getSimpleName();
    private final int b;
    private final WeakReference<Context> c;
    private final WeakReference<ViewGroup> d;
    private final String e;
    private final ch f;
    private long g;
    private final fy<hj> h = new fy<hj>() { // from class: com.flurry.sdk.o.1
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(hj hjVar) {
            o.this.r();
            o.this.g = System.currentTimeMillis();
        }
    };
    private final fy<d> i = new fy<d>() { // from class: com.flurry.sdk.o.2
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(d dVar) {
            if (dVar.a == o.this && dVar.b != null) {
                o.this.a(dVar);
            }
        }
    };
    private ap j;
    private ap k;
    private e l;

    protected o(Context context, ViewGroup viewGroup, String str) {
        i iVarA = i.a();
        if (iVarA == null) {
            throw new IllegalStateException("A session must be started before ad objects may be instantiated.");
        }
        this.b = cr.a();
        this.c = new WeakReference<>(context);
        this.d = new WeakReference<>(viewGroup);
        this.e = str;
        this.f = new ch(str);
        iVarA.d().a(context, this);
        s();
        u();
    }

    protected void finalize() throws Throwable {
        super.finalize();
        a();
    }

    @Override // com.flurry.sdk.r
    public void a() {
        t();
        v();
        i.a().d().b(e(), this);
        aa aaVarL = i.a().l();
        if (aaVarL != null) {
            aaVarL.a(this);
        }
        if (this.f != null) {
            this.f.a();
        }
        this.l = null;
    }

    @Override // com.flurry.sdk.r
    public void b() {
        t();
    }

    @Override // com.flurry.sdk.r
    public void c() {
        s();
    }

    @Override // com.flurry.sdk.r
    public int d() {
        return this.b;
    }

    @Override // com.flurry.sdk.r
    public Context e() {
        return this.c.get();
    }

    @Override // com.flurry.sdk.r
    public ViewGroup f() {
        return this.d.get();
    }

    @Override // com.flurry.sdk.r
    public String g() {
        return this.e;
    }

    public ch h() {
        return this.f;
    }

    public ci i() {
        return i.a().c().a(g(), null, l()).a();
    }

    public x j() {
        return i.a().c().a(g(), null, l()).b();
    }

    @Override // com.flurry.sdk.r
    public ap k() {
        return this.k;
    }

    @Override // com.flurry.sdk.r
    public e l() {
        return this.l;
    }

    @Override // com.flurry.sdk.r
    public void a(ap apVar) {
        this.j = apVar;
    }

    @Override // com.flurry.sdk.r
    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            h().b();
            j().a(str);
        }
    }

    @Override // com.flurry.sdk.r
    public void a(ap apVar, long j) {
        h().a(this, i(), j());
    }

    @Override // com.flurry.sdk.r
    public void m() {
        this.f.c();
    }

    public void a(e eVar) {
        this.l = eVar;
    }

    private void s() {
        this.g = System.currentTimeMillis();
        hk.a().a(this.h);
    }

    private void t() {
        hk.a().b(this.h);
    }

    private void u() {
        fz.a().a("com.flurry.android.impl.ads.AdStateEvent", this.i);
    }

    private void v() {
        fz.a().a(this.i);
    }

    protected ap n() {
        return this.j;
    }

    protected void o() {
        hp.b();
        if (!this.j.o() && this.j.n()) {
            gd.a(3, a, "Precaching optional for ad, copying assets before display");
            i.a().l().a(this, this.j);
        }
    }

    protected void p() {
        this.k = this.j;
        this.j = null;
    }

    protected long q() {
        return this.g;
    }

    protected void r() {
    }

    protected void a(d dVar) {
        int iB;
        if ((d.a.kOnFetched.equals(dVar.b) || d.a.kOnFetchFailed.equals(dVar.b)) && (iB = j().b()) == 0) {
            gd.a(3, a, "Starting ad request from EnsureCacheNotEmpty size: " + iB);
            i().a(this, j(), (ap) null);
        }
    }
}
