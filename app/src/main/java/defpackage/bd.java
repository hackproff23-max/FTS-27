package defpackage;

import com.nuance.nmdp.speechkit.recognitionresult.b;

/* JADX INFO: loaded from: classes.dex */
public final class bd {
    private final ba a;
    private final Object b;
    private final Object c;
    private final az d;
    private boolean f = false;
    private boolean g = false;
    private final Object e = new Object();

    public bd(ba baVar, Object obj, Object obj2, final az azVar) {
        this.a = baVar;
        this.b = obj2;
        this.c = obj;
        this.d = new az() { // from class: bd.1
            @Override // defpackage.az
            public final void a(Object obj3) {
                synchronized (bd.this.e) {
                    bd.b(bd.this);
                }
                azVar.a(obj3);
            }

            @Override // defpackage.az
            public final void b(Object obj3) {
                azVar.b(obj3);
            }

            @Override // defpackage.az
            public final void c(Object obj3) {
                synchronized (bd.this.e) {
                    bd.b(bd.this);
                }
                azVar.c(obj3);
            }
        };
    }

    static /* synthetic */ boolean b(bd bdVar) {
        bdVar.g = true;
        return true;
    }

    public final void a() {
        if (this.f) {
            b.c(this, "Prompt already started");
        } else {
            this.f = true;
            this.a.a(this.c, this.d, this.b);
        }
    }

    public final void b() {
        if (this.f) {
            synchronized (this.e) {
                if (!this.g) {
                    b.a(this, "Stopping prompt");
                    this.g = true;
                    this.a.a();
                }
            }
        }
    }
}
