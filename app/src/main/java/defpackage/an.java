package defpackage;

import com.nuance.nmdp.speechkit.recognitionresult.b;
import com.nuance.nmdp.speechkit.util.pdx.PdxValue;

/* JADX INFO: loaded from: classes.dex */
public final class an extends af {
    private PdxValue.Dictionary e;
    private String f;

    public an(df dfVar, k kVar, String str, String str2, PdxValue.Dictionary dictionary, bf<?> bfVar, ac acVar, aa aaVar) {
        super(dfVar, kVar, false, false, str, null, null, null, null, bfVar, acVar, aaVar);
        this.e = dictionary;
        this.f = str2;
    }

    @Override // defpackage.j
    protected final void a(cv cvVar) {
        super.a(cvVar);
        cvVar.b("dictation_language", this.b);
        if (this.f != null) {
            cvVar.b("application_session_id", this.f);
        }
    }

    @Override // defpackage.af
    protected final void j() throws l {
        cv cvVarG = g();
        cvVarG.a("appserver_data", b.a(this, new r(this.e).a()));
        b.a(this, "REQUEST_INFO: " + cvVarG);
        a("REQUEST_INFO", cvVarG);
    }

    @Override // defpackage.af
    protected final String k() {
        return this.d.y();
    }

    @Override // defpackage.af
    protected final String l() {
        return "AUDIO_INFO";
    }

    @Override // defpackage.af
    protected final boolean m() {
        return false;
    }
}
