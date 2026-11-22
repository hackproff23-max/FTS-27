package com.nuance.nmdp.speechkit;

import defpackage.ac;
import defpackage.ba;
import defpackage.bf;

/* JADX INFO: loaded from: classes.dex */
abstract class p<ResultType> {
    private final defpackage.m a;
    private final boolean b;
    private final boolean c;
    private final String d;
    private final ac e;
    private defpackage.z g;
    private bf<ResultType> h;
    private Prompt j;
    private Prompt k;
    private Prompt l;
    private Prompt m;
    private String n;
    private final defpackage.aa f = new defpackage.aa() { // from class: com.nuance.nmdp.speechkit.p.1
        @Override // defpackage.h
        public final void a(defpackage.g gVar) {
            if (p.this.g == gVar) {
                p.b(p.this);
            }
        }

        @Override // defpackage.h
        public final void a(defpackage.g gVar, int i, String str, String str2) {
            if (p.this.g == gVar) {
                p.this.a((SpeechError) new v(i, str, str2));
            }
        }

        @Override // defpackage.aa
        public final void b(defpackage.g gVar) {
            if (p.this.g == gVar) {
                p.this.b();
            }
        }

        @Override // defpackage.aa
        public final void c(defpackage.g gVar) {
            if (p.this.g == gVar) {
                p.this.c();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // defpackage.aa
        public final void d(defpackage.g gVar) {
            if (p.this.g == gVar) {
                p.this.a(p.this.h.b());
            }
        }
    };
    private boolean i = false;

    p(defpackage.m mVar, boolean z, boolean z2, String str, String str2, ac acVar) {
        this.a = mVar;
        this.b = z;
        this.c = z2;
        this.d = str;
        this.e = acVar;
        this.n = str2;
    }

    static /* synthetic */ defpackage.z b(p pVar) {
        pVar.g = null;
        return null;
    }

    protected abstract bf<ResultType> a();

    protected abstract defpackage.z a(defpackage.m mVar, boolean z, boolean z2, String str, String str2, ac acVar, ba baVar, ba baVar2, ba baVar3, ba baVar4, bf<ResultType> bfVar, defpackage.aa aaVar);

    public final void a(int i, Prompt prompt) {
        switch (i) {
            case 0:
                this.j = prompt;
                break;
            case 1:
                this.k = prompt;
                break;
            case 2:
                this.l = prompt;
                break;
            case 3:
                this.m = prompt;
                break;
        }
    }

    protected abstract void a(SpeechError speechError);

    protected abstract void a(ResultType resulttype);

    protected abstract void b();

    protected abstract void c();

    public final void d() {
        if (!this.a.b()) {
            com.nuance.nmdp.speechkit.recognitionresult.b.c(this, "Unable to create recognition transaction. Transaction runner is invalid.");
            a((SpeechError) new v(0, null, null));
            return;
        }
        if (this.i) {
            return;
        }
        ba baVarA = this.j == null ? null : this.j.a();
        ba baVarA2 = this.k == null ? null : this.k.a();
        ba baVarA3 = this.l == null ? null : this.l.a();
        ba baVarA4 = this.m == null ? null : this.m.a();
        this.h = a();
        this.g = a(this.a, this.b, this.c, this.d, this.n, this.e, baVarA, baVarA2, baVarA3, baVarA4, this.h, this.f);
        if (this.g == null) {
            com.nuance.nmdp.speechkit.recognitionresult.b.c(this, "Unable to create recognition transaction");
            a((SpeechError) new v(0, null, null));
        } else {
            this.i = true;
            this.g.a();
        }
    }

    public final void e() {
        if (this.g != null) {
            this.g.a_();
        }
    }

    public final void f() {
        if (this.g != null) {
            this.g.b();
            this.g = null;
        }
    }
}
