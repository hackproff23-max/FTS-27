package defpackage;

import com.nuance.nmdp.speechkit.recognitionresult.b;
import com.nuance.nmdp.speechkit.util.pdx.PdxValue;

/* JADX INFO: loaded from: classes.dex */
public final class am extends af {
    private final String e;
    private PdxValue.Sequence f;

    public am(df dfVar, k kVar, String str, boolean z, boolean z2, String str2, PdxValue.Sequence sequence, ba baVar, ba baVar2, ba baVar3, ba baVar4, bf<?> bfVar, ac acVar, aa aaVar) {
        super(dfVar, kVar, z, z2, str2, baVar, baVar2, baVar3, baVar4, bfVar, acVar, aaVar);
        this.e = str;
        this.f = sequence;
    }

    @Override // defpackage.j
    protected final void a(cv cvVar) {
        super.a(cvVar);
        cvVar.b("dictation_language", this.b);
        cvVar.b("dictation_type", this.e);
    }

    @Override // defpackage.af
    protected final void j() throws l {
        cv cvVarG = g();
        cvVarG.b("start", 0);
        cvVarG.b("end", 0);
        cvVarG.b("text", "");
        if (this.f != null) {
            cvVarG.a("grammar_list", b.a(this, new r(this.f).b()));
        }
        cvVarG.b("binary_results", 1);
        a("REQUEST_INFO", cvVarG);
    }

    @Override // defpackage.af
    protected final String k() {
        return this.d.u();
    }

    @Override // defpackage.af
    protected final String l() {
        return "AUDIO_INFO";
    }

    @Override // defpackage.af
    protected final boolean m() {
        return true;
    }
}
