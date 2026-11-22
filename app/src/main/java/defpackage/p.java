package defpackage;

import com.nuance.nmdp.speechkit.recognitionresult.b;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class p extends j {
    private final List<q> e;
    private final bf<?> f;
    private final String g;

    public p(df dfVar, k kVar, String str, List<q> list, bf<?> bfVar, h hVar) {
        super(dfVar, kVar, hVar);
        this.e = list;
        this.f = bfVar;
        this.g = str;
        this.a = new s(this);
    }

    @Override // defpackage.j
    protected void a(cv cvVar) {
        super.a(cvVar);
        cvVar.b("dictation_language", "eng-USA");
    }

    protected final void i() throws l {
        if (this.e != null) {
            for (q qVar : this.e) {
                String strA = qVar.a();
                r rVarB = qVar.b();
                switch (rVarB.f()) {
                    case 0:
                        a(strA, rVarB.c());
                        break;
                    case 1:
                        b.c(this, "INT is an unsupported param type");
                        break;
                    case 2:
                        a(strA, b.a(this, rVarB.a()));
                        break;
                    case 3:
                        b.c(this, "SEQ is an unsupported param type");
                        break;
                    case 5:
                        b(strA, b.a(this, rVarB.a()));
                        break;
                    case 6:
                        c(strA, b.a(this, rVarB.a()));
                        break;
                    case 7:
                        d(strA, b.a(this, rVarB.a()));
                        break;
                }
                b.a(this, "Send custom param " + strA);
            }
        }
    }

    final String j() {
        return this.g;
    }

    final bf<?> k() {
        return this.f;
    }
}
