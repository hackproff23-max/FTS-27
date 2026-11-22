package defpackage;

import com.nuance.nmdp.speechkit.recognitionresult.b;

/* JADX INFO: loaded from: classes.dex */
public final class au extends j implements g {
    private final String e;
    private final String f;
    private final String g;
    private final az h;
    private bc i;

    public au(df dfVar, k kVar, String str, String str2, String str3, boolean z, aq aqVar) {
        super(dfVar, kVar, str3, aqVar);
        this.f = str;
        this.g = z ? "ssml" : "text";
        this.e = str2;
        this.h = new az() { // from class: au.1
            @Override // defpackage.az
            public final void a(Object obj) {
                b.c(au.this, "Player error");
                ax.a(new Runnable() { // from class: au.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        au.a(au.this).g();
                    }
                });
            }

            @Override // defpackage.az
            public final void b(Object obj) {
                ax.a(new Runnable() { // from class: au.1.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        au.a(au.this).h();
                    }
                });
            }

            @Override // defpackage.az
            public final void c(Object obj) {
                ax.a(new Runnable() { // from class: au.1.3
                    @Override // java.lang.Runnable
                    public final void run() {
                        au.a(au.this).i();
                    }
                });
            }
        };
        this.i = new bc(this.c, this, kVar.a(), this.h);
        this.a = new as(this);
    }

    static /* synthetic */ ar a(au auVar) {
        return (ar) auVar.a;
    }

    @Override // defpackage.j
    protected final void a(cv cvVar) {
        if (this.e != null) {
            cvVar.b("tts_voice", this.e);
        } else {
            cvVar.b("tts_language", this.b);
        }
    }

    protected final String i() {
        return this.d.z();
    }

    public final String j() {
        return this.f;
    }

    public final String k() {
        return this.g;
    }

    public final void l() {
        this.i.a();
    }

    public final void m() {
        this.i.b();
    }

    public final cx n() {
        return this.i.c();
    }
}
