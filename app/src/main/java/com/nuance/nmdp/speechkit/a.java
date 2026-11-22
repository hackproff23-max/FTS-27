package com.nuance.nmdp.speechkit;

import defpackage.bf;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class a<ResultType> {
    private final defpackage.m a;
    private final String b;
    private bf<ResultType> c;
    private final List<defpackage.q> d;
    private defpackage.g g;
    private final defpackage.h f = new defpackage.h() { // from class: com.nuance.nmdp.speechkit.a.1
        /* JADX WARN: Multi-variable type inference failed */
        @Override // defpackage.h
        public final void a(defpackage.g gVar) {
            if (a.this.g == gVar) {
                if (a.this.c != null) {
                    a.this.a(a.this.c.b());
                } else {
                    a.this.a((Object) null);
                }
                a.c(a.this);
            }
        }

        @Override // defpackage.h
        public final void a(defpackage.g gVar, int i, String str, String str2) {
            if (a.this.g == gVar) {
                a.this.a((SpeechError) new v(i, str, str2));
            }
        }
    };
    private boolean e = false;

    a(defpackage.m mVar, String str, List<defpackage.q> list) {
        this.a = mVar;
        this.b = str;
        this.d = list;
    }

    static /* synthetic */ defpackage.g c(a aVar) {
        aVar.g = null;
        return null;
    }

    protected abstract bf<ResultType> a();

    protected abstract defpackage.g a(defpackage.m mVar, String str, List<defpackage.q> list, bf<ResultType> bfVar, defpackage.h hVar);

    protected abstract void a(SpeechError speechError);

    protected abstract void a(ResultType resulttype);

    public final void b() {
        if (!this.a.b()) {
            com.nuance.nmdp.speechkit.recognitionresult.b.c(this, "Unable to create command transaction. Transaction runner is invalid.");
            a((SpeechError) new v(0, null, null));
        } else {
            if (this.e) {
                return;
            }
            this.c = a();
            this.g = a(this.a, this.b, this.d, this.c, this.f);
            if (this.g == null) {
                com.nuance.nmdp.speechkit.recognitionresult.b.c(this, "Unable to create command transaction");
                a((SpeechError) new v(0, null, null));
            } else {
                this.e = true;
                this.g.a();
            }
        }
    }

    public final void c() {
        if (this.g != null) {
            this.g.b();
            this.g = null;
        }
    }
}
