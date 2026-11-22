package defpackage;

import com.nuance.nmdp.speechkit.recognitionresult.b;
import com.nuance.nmdp.speechkit.util.pdx.PdxValue;
import com.nuance.nmdp.speechkit.w;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class m {
    private cd c;
    private ce d;
    private ce e;
    private bi f;
    private bi g;
    private k i;
    private boolean k;
    private g a = null;
    private final dh h = new dh() { // from class: m.2
        @Override // defpackage.dh
        public final void a() {
            b.a(m.this, "Shutdown Complte");
        }

        @Override // defpackage.dh
        public final void a(String str) {
            synchronized (m.this.l) {
                b.a(m.this, "Connected with session ID " + str);
                m.this.j = str;
            }
        }

        @Override // defpackage.dh
        public final void a(short s) {
            b.b(m.this, "Connection failed reasonCode [" + ((int) s) + "]");
        }

        @Override // defpackage.dh
        public final void b(short s) {
            b.a(m.this, "Disconnected reasoncode [" + ((int) s) + "]");
        }
    };
    private String j = null;
    private final Object l = new Object();
    private df b = e();

    public m(k kVar) {
        this.c = null;
        this.d = null;
        this.e = null;
        this.i = kVar;
        this.k = true;
        this.f = this.i.r();
        this.g = this.i.s();
        if (this.b == null) {
            this.k = false;
            this.i = null;
            return;
        }
        this.c = this.b.a();
        if (this.c != null) {
            try {
                cg cgVarC = this.c.c("SpeechKit");
                cgVarC.a("Version", w.VERSION);
                cgVarC.a("DnsAddress", this.i.d());
                this.d = cgVarC.a();
                cg cgVarA = this.d.a("SpeechKit App");
                cgVarA.a("Version", this.i.e());
                this.e = cgVarA.a();
            } catch (cc e) {
                b.b(this, "Application Session is already open");
            } catch (cf e2) {
                b.b(this, "Session event already committed");
            }
        }
    }

    static /* synthetic */ void a(m mVar) {
        b.b(mVar, "Restarting NMSP manager");
        mVar.f = mVar.i.r();
        mVar.g = mVar.i.s();
        mVar.a = null;
        mVar.b.b_();
        mVar.b = mVar.e();
        if (mVar.b == null) {
            mVar.i = null;
            mVar.k = false;
        }
    }

    static /* synthetic */ void a(m mVar, g gVar) {
        if (gVar == mVar.a) {
            mVar.a = null;
        }
    }

    private df e() {
        try {
            short sF = (short) this.i.f();
            String strD = this.i.d();
            String strT = this.i.t();
            boolean zH = this.i.h();
            ay ayVar = new ay();
            ayVar.a(new cz("Android_Context", this.i.a(), cz.a.a));
            if (strT != null) {
                ayVar.a(new cz("SocketConnectionSetting", strT.getBytes(), cz.a.a));
            }
            if (zH) {
                ayVar.a(new cz("SSL_Socket_Enable", "TRUE".getBytes(), cz.a.a));
            }
            ayVar.a(new cz("Calllog_Disable", "TRUE".getBytes(), cz.a.a));
            return dg.a(strD, sF, this.i.b(), this.i.c(), this.i.p(), this.g, this.f, "calllog.data", ayVar.f(), this.h);
        } catch (Throwable th) {
            b.a(this, "Unable to create NMSP manager", th);
            return null;
        }
    }

    private void f() {
        bi biVarR = this.i.r();
        bi biVarS = this.i.s();
        if (this.f == biVarR && this.g == biVarS) {
            return;
        }
        b.b(this, "Supported codecs changed, restarting NMSP manager");
        this.a = null;
        this.f = biVarR;
        this.g = biVarS;
        this.b.b_();
        this.b = e();
        if (this.b == null) {
            this.i = null;
            this.k = false;
        }
    }

    public final g a(final h hVar) {
        if (!this.k || this.a != null) {
            return null;
        }
        f();
        v vVar = new v(this.b, this.i, new h() { // from class: m.9
            @Override // defpackage.h
            public final void a(g gVar) {
                m.a(m.this, gVar);
                hVar.a(gVar);
            }

            @Override // defpackage.h
            public final void a(g gVar, int i, String str, String str2) {
                hVar.a(gVar, i, str, str2);
                if (i == 1) {
                    m.a(m.this);
                }
            }
        });
        this.a = vVar;
        return vVar;
    }

    public final g a(String str, String str2, String str3, boolean z, final aq aqVar) {
        if (!this.k) {
            return null;
        }
        if (this.a != null) {
            this.a.b();
        }
        f();
        df dfVar = this.b;
        k kVar = this.i;
        bi biVar = this.f;
        au auVar = new au(dfVar, kVar, str, str2, str3, z, new aq() { // from class: m.8
            @Override // defpackage.h
            public final void a(g gVar) {
                m.a(m.this, gVar);
                aqVar.a(gVar);
            }

            @Override // defpackage.h
            public final void a(g gVar, int i, String str4, String str5) {
                aqVar.a(gVar, i, str4, str5);
                if (i == 1) {
                    m.a(m.this);
                }
            }

            @Override // defpackage.aq
            public final void b(g gVar) {
                aqVar.b(gVar);
            }
        });
        this.a = auVar;
        return auVar;
    }

    public final g a(String str, List<q> list, bf<?> bfVar, final h hVar) {
        if (!this.k) {
            return null;
        }
        if (this.a != null) {
            this.a.b();
        }
        f();
        p pVar = new p(this.b, this.i, str, list, bfVar, new h() { // from class: m.6
            @Override // defpackage.h
            public final void a(g gVar) {
                m.a(m.this, gVar);
                hVar.a(gVar);
            }

            @Override // defpackage.h
            public final void a(g gVar, int i, String str2, String str3) {
                hVar.a(gVar, i, str2, str3);
                if (i == 1) {
                    m.a(m.this);
                }
            }
        });
        this.a = pVar;
        return pVar;
    }

    public final g a(List<q> list, String str, bf<?> bfVar, final h hVar) {
        if (!this.k) {
            return null;
        }
        if (this.a != null) {
            this.a.b();
        }
        f();
        u uVar = new u(this.b, this.i, list, str, bfVar, new h() { // from class: m.7
            @Override // defpackage.h
            public final void a(g gVar) {
                m.a(m.this, gVar);
                hVar.a(gVar);
            }

            @Override // defpackage.h
            public final void a(g gVar, int i, String str2, String str3) {
                hVar.a(gVar, i, str2, str3);
                if (i == 1) {
                    m.a(m.this);
                }
            }
        });
        this.a = uVar;
        return uVar;
    }

    public final z a(String str, String str2, PdxValue.Dictionary dictionary, bf<?> bfVar, ac acVar, final aa aaVar) {
        if (!this.k) {
            return null;
        }
        if (this.a != null) {
            this.a.b();
        }
        f();
        an anVar = new an(this.b, this.i, str, str2, dictionary, bfVar, acVar, new aa() { // from class: m.5
            @Override // defpackage.h
            public final void a(g gVar) {
                m.a(m.this, gVar);
                aaVar.a(gVar);
            }

            @Override // defpackage.h
            public final void a(g gVar, int i, String str3, String str4) {
                aaVar.a(gVar, i, str3, str4);
                if (i == 1) {
                    m.a(m.this);
                }
            }

            @Override // defpackage.aa
            public final void b(g gVar) {
                aaVar.b(gVar);
            }

            @Override // defpackage.aa
            public final void c(g gVar) {
                aaVar.c(gVar);
            }

            @Override // defpackage.aa
            public final void d(g gVar) {
                aaVar.d(gVar);
            }
        });
        this.a = anVar;
        return anVar;
    }

    public final z a(String str, boolean z, boolean z2, String str2, ba baVar, ba baVar2, ba baVar3, ba baVar4, bf<?> bfVar, ac acVar, final aa aaVar) {
        if (!this.k) {
            return null;
        }
        if (this.a != null) {
            this.a.b();
        }
        f();
        am amVar = new am(this.b, this.i, str, z, z2, str2, null, baVar, baVar2, baVar3, baVar4, bfVar, acVar, new aa() { // from class: m.1
            @Override // defpackage.h
            public final void a(g gVar) {
                m.a(m.this, gVar);
                aaVar.a(gVar);
            }

            @Override // defpackage.h
            public final void a(g gVar, int i, String str3, String str4) {
                aaVar.a(gVar, i, str3, str4);
                if (i == 1) {
                    m.a(m.this);
                }
            }

            @Override // defpackage.aa
            public final void b(g gVar) {
                aaVar.b(gVar);
            }

            @Override // defpackage.aa
            public final void c(g gVar) {
                aaVar.c(gVar);
            }

            @Override // defpackage.aa
            public final void d(g gVar) {
                aaVar.d(gVar);
            }
        });
        this.a = amVar;
        return amVar;
    }

    public final z a(String str, boolean z, boolean z2, String str2, PdxValue.Sequence sequence, ba baVar, ba baVar2, ba baVar3, ba baVar4, bf<?> bfVar, ac acVar, final aa aaVar) {
        if (!this.k) {
            return null;
        }
        if (this.a != null) {
            this.a.b();
        }
        f();
        am amVar = new am(this.b, this.i, str, z, z2, str2, sequence, baVar, baVar2, baVar3, baVar4, bfVar, acVar, new aa() { // from class: m.3
            @Override // defpackage.h
            public final void a(g gVar) {
                m.a(m.this, gVar);
                aaVar.a(gVar);
            }

            @Override // defpackage.h
            public final void a(g gVar, int i, String str3, String str4) {
                aaVar.a(gVar, i, str3, str4);
                if (i == 1) {
                    m.a(m.this);
                }
            }

            @Override // defpackage.aa
            public final void b(g gVar) {
                aaVar.b(gVar);
            }

            @Override // defpackage.aa
            public final void c(g gVar) {
                aaVar.c(gVar);
            }

            @Override // defpackage.aa
            public final void d(g gVar) {
                aaVar.d(gVar);
            }
        });
        this.a = amVar;
        return amVar;
    }

    public final z a(String str, boolean z, boolean z2, String str2, String str3, PdxValue.Dictionary dictionary, ba baVar, ba baVar2, ba baVar3, ba baVar4, bf<?> bfVar, ac acVar, final aa aaVar) {
        if (!this.k) {
            return null;
        }
        if (this.a != null) {
            this.a.b();
        }
        f();
        al alVar = new al(this.b, this.i, str, z, z2, str2, str3, dictionary, baVar, baVar2, baVar3, baVar4, bfVar, acVar, new aa() { // from class: m.4
            @Override // defpackage.h
            public final void a(g gVar) {
                m.a(m.this, gVar);
                aaVar.a(gVar);
            }

            @Override // defpackage.h
            public final void a(g gVar, int i, String str4, String str5) {
                aaVar.a(gVar, i, str4, str5);
                if (i == 1) {
                    m.a(m.this);
                }
            }

            @Override // defpackage.aa
            public final void b(g gVar) {
                aaVar.b(gVar);
            }

            @Override // defpackage.aa
            public final void c(g gVar) {
                aaVar.c(gVar);
            }

            @Override // defpackage.aa
            public final void d(g gVar) {
                aaVar.d(gVar);
            }
        });
        this.a = alVar;
        return alVar;
    }

    public final void a() {
        this.k = false;
        if (this.a != null) {
            this.a.b();
            this.a = null;
        }
        if (this.b != null) {
            this.b.b_();
            this.b = null;
        }
        this.i = null;
    }

    public final void a(String str, String str2) {
        if (this.e == null) {
            b.b(this, "Application log attempt is ignored. Call logging is not active.");
            return;
        }
        cg cgVarA = this.e.a("SK App Log");
        try {
            cgVarA.a(str, str2);
            synchronized (this.l) {
                if (this.j != null) {
                    cgVarA.a("SessionId", this.j);
                }
            }
            cgVarA.a();
        } catch (cf e) {
            b.b(this, "Application Session is already committed");
        }
    }

    public final boolean b() {
        return this.k;
    }

    public final String c() {
        String str;
        synchronized (this.l) {
            str = this.j;
        }
        return str;
    }

    public final void d() {
        if (this.a != null) {
            this.a.b();
            this.a = null;
        }
    }
}
