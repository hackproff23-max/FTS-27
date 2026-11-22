package defpackage;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class u extends p {
    private final String e;

    public u(df dfVar, k kVar, List<q> list, String str, bf<?> bfVar, h hVar) {
        super(dfVar, kVar, kVar.w(), list, bfVar, hVar);
        this.e = str;
    }

    @Override // defpackage.p, defpackage.j
    protected final void a(cv cvVar) {
        super.a(cvVar);
        String str = this.e;
        cvVar.b("application_session_id", this.e);
    }
}
