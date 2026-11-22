package com.nuance.nmdp.speechkit;

import com.nuance.nmdp.speechkit.util.pdx.PdxValue;
import defpackage.ax;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class b<ResultType> extends defpackage.a {
    private final String b;
    private final List<defpackage.q> c;
    private final x d;
    private a<ResultType> e;
    private Runnable f;

    b(x xVar, String str, Object obj) {
        super(obj);
        this.b = str;
        this.c = new ArrayList();
        this.d = xVar;
        this.f = new Runnable() { // from class: com.nuance.nmdp.speechkit.b.1
            @Override // java.lang.Runnable
            public final void run() {
                b.this.e = b.this.a(b.this.d.c(), b.this.b, b.this.c);
            }
        };
    }

    protected abstract a<ResultType> a(defpackage.m mVar, String str, List<defpackage.q> list);

    protected final void a() {
        if (this.f != null) {
            ax.a(this.f);
            this.f = null;
        }
    }

    public void addParam(String str, PdxValue.Dictionary dictionary) {
        this.c.add(new defpackage.q(str, dictionary));
    }

    public void addParam(String str, PdxValue.String string) {
        this.c.add(new defpackage.q(str, string));
    }

    public void cancel() {
        this.d.e();
        ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.b.3
            @Override // java.lang.Runnable
            public final void run() {
                b.this.e.c();
            }
        });
    }

    public void start() {
        this.d.e();
        ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.b.2
            @Override // java.lang.Runnable
            public final void run() {
                b.this.e.b();
            }
        });
    }
}
