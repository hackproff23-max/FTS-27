package defpackage;

import com.nuance.nmdp.speechkit.recognitionresult.b;

/* JADX INFO: loaded from: classes.dex */
public abstract class f extends i {
    protected final int a;
    private final String c;
    private final String d;
    private final boolean e;

    public f(j jVar, int i, String str, String str2, boolean z) {
        super(jVar);
        this.a = i;
        this.c = str;
        this.d = str2;
        this.e = z;
    }

    @Override // defpackage.i
    public void a() {
        b.a(this, "Transaction error code: " + this.a);
        if (this.c != null && this.c.length() > 0) {
            b.a(this, "Transaction error text: " + this.c);
        }
        if (this.d != null && this.d.length() > 0) {
            b.a(this, "Transaction suggestion: " + this.d);
        }
        this.b.e();
        final h hVarC = this.b.c();
        if (!this.e) {
            ax.a(new Runnable() { // from class: f.1
                @Override // java.lang.Runnable
                public final void run() {
                    hVarC.a(f.this.b, f.this.a, f.this.c, f.this.d);
                    hVarC.a(f.this.b);
                }
            });
        } else {
            hVarC.a(this.b, this.a, this.c, this.d);
            hVarC.a(this.b);
        }
    }
}
