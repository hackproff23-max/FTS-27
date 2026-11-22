package defpackage;

import com.nuance.nmdp.speechkit.recognitionresult.b;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public abstract class j implements g {
    protected i a;
    protected final String b;
    protected final df c;
    protected final k d;
    private h e;
    private eh f;
    private eg g;
    private final dk h;
    private final ej i;

    public j(df dfVar, k kVar, h hVar) {
        this(dfVar, kVar, kVar.j(), hVar);
    }

    public j(df dfVar, k kVar, String str, h hVar) {
        this.e = hVar;
        this.c = dfVar;
        this.d = kVar;
        this.b = str == null ? kVar.j() : str;
        this.h = new dk() { // from class: j.1
            @Override // defpackage.dk
            public final void a() {
                b.c(j.this, "PDX Create Command Failed");
                ax.a(new Runnable() { // from class: j.1.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        j.this.a.e();
                    }
                });
            }

            @Override // defpackage.dk
            public final void a(final String str2) {
                ax.a(new Runnable() { // from class: j.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        i iVar = j.this.a;
                        String str3 = str2;
                    }
                });
            }
        };
        this.i = new ej() { // from class: j.2
            @Override // defpackage.ej
            public final void a(final cv cvVar) {
                b.a(j.this, "PDX Query Result Returned");
                ax.a(new Runnable() { // from class: j.2.3
                    @Override // java.lang.Runnable
                    public final void run() {
                        j.this.a.a(cvVar);
                    }
                });
            }

            @Override // defpackage.ej
            public final void a(final ek ekVar) {
                b.a(j.this, "PDX Query Error Returned: " + ekVar.g() + "(" + ekVar.h() + ")");
                ax.a(new Runnable() { // from class: j.2.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        i iVar = j.this.a;
                        ekVar.g();
                        iVar.a(ekVar.h());
                    }
                });
            }

            @Override // defpackage.ej
            public final void a(el elVar) {
                b.a(j.this, "PDX Query Retry Returned: " + elVar.h() + "(" + elVar.g() + ")");
                final String strI = elVar.i();
                final String strG = elVar.g();
                ax.a(new Runnable() { // from class: j.2.4
                    @Override // java.lang.Runnable
                    public final void run() {
                        i iVar = j.this.a;
                        String str2 = strI;
                        String str3 = strG;
                        iVar.b(str2);
                    }
                });
            }

            @Override // defpackage.ej
            public final void a(final short s) {
                switch (s) {
                    case 1:
                    case 3:
                        b.a(j.this, "PDX Command Event: " + ((int) s));
                        ax.a(new Runnable() { // from class: j.2.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                i iVar = j.this.a;
                                short s2 = s;
                                iVar.f();
                            }
                        });
                        break;
                }
            }
        };
        this.f = null;
        this.g = null;
    }

    private void a(bj.j jVar) throws l {
        try {
            this.g.a(jVar);
        } catch (da e) {
            throw new l("Error sending parameter (TransactionAlreadyFinishedException)", e);
        } catch (db e2) {
            throw new l("Error sending parameter (TransactionExpiredException)", e2);
        }
    }

    @Override // defpackage.g
    public final void a() {
        this.a.c();
    }

    protected void a(cv cvVar) {
    }

    public final void a(i iVar) {
        this.a.b();
        this.a = iVar;
        this.a.a();
    }

    public final void a(String str) throws dj {
        if (this.f != null) {
            cv cvVarG = g();
            String strI = this.d.i();
            String strK = this.d.k();
            String strL = this.d.l();
            String strM = this.d.m();
            String strO = this.d.o();
            k kVar = this.d;
            String strQ = k.q();
            k kVar2 = this.d;
            String strN = k.n();
            String strB = this.d.b();
            String strG = this.d.g();
            cvVarG.b("ui_language", this.b.substring(0, 2).toLowerCase());
            cvVarG.b("phone_submodel", strK);
            cvVarG.b("phone_OS", strL);
            cvVarG.b("locale", strM);
            cvVarG.b("nmdp_version", strN);
            cvVarG.b("nmaid", strB);
            cvVarG.b("network_type", strO);
            if (strG != null) {
                cvVarG.b("subscription_id", strG);
            }
            byte[] bArrA = new byte[100];
            try {
                bArrA = this.d.a(bArrA);
            } catch (Throwable th) {
            }
            cvVarG.a("app_transaction_id", bArrA);
            a(cvVarG);
            this.g = this.f.a(this.i, str, strQ, this.b, strI, strK, cvVarG);
        }
    }

    public final void a(String str, cv cvVar) throws l {
        a(this.f.a(str, cvVar));
    }

    public final void a(String str, String str2) throws l {
        a(this.f.a(str, str2));
    }

    public final void a(String str, String str2, cx cxVar) throws l {
        cv cvVarG = g();
        cvVarG.b("tts_input", str2);
        cvVarG.b("tts_type", str);
        a(this.f.a("TEXT_TO_READ", cvVarG, cxVar));
    }

    public final cx b(String str) throws l {
        cx cxVarA = this.f.a(str);
        a(cxVarA);
        return cxVarA;
    }

    @Override // defpackage.g
    public final void b() {
        this.a.d();
    }

    public final void b(String str, cv cvVar) throws l {
        a(this.f.d(str, cvVar));
    }

    public final h c() {
        return this.e;
    }

    public final void c(String str, cv cvVar) throws l {
        a(this.f.b(str, cvVar));
    }

    public final void d() {
        Vector vector = new Vector();
        vector.add(new cz("Android_Context", this.d.a(), cz.a.a));
        df dfVar = this.c;
        dk dkVar = this.h;
        this.d.b();
        this.f = ei.a(dfVar, dkVar, vector);
    }

    public final void d(String str, cv cvVar) throws l {
        a(this.f.c(str, cvVar));
    }

    public final void e() {
        if (this.f != null) {
            try {
                this.f.a();
            } catch (dj e) {
            }
            this.f = null;
        }
    }

    public final void f() throws l {
        try {
            this.g.a();
        } catch (da e) {
            throw new l("Error ending PDX command (TransactionAlreadyFinishedException)", e);
        } catch (db e2) {
            throw new l("Error ending PDX command (TransactionExpiredException)", e2);
        }
    }

    public final cv g() {
        return this.f.j();
    }

    public final cw h() {
        return this.f.k();
    }
}
