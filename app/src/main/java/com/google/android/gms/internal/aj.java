package com.google.android.gms.internal;

import android.content.Context;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@ey
public class aj implements ah {
    private final gu mo;

    public aj(Context context, gs gsVar) {
        this.mo = gu.a(context, new ay(), false, false, null, gsVar);
    }

    private void runOnUiThread(Runnable runnable) {
        if (gq.dB()) {
            runnable.run();
        } else {
            gq.wR.post(runnable);
        }
    }

    @Override // com.google.android.gms.internal.ah
    public void a(final ah.a aVar) {
        this.mo.dD().a(new gv.a() { // from class: com.google.android.gms.internal.aj.3
            @Override // com.google.android.gms.internal.gv.a
            public void a(gu guVar) {
                aVar.aR();
            }
        });
    }

    @Override // com.google.android.gms.internal.ah
    public void a(t tVar, ds dsVar, cb cbVar, dv dvVar, boolean z, ce ceVar) {
        this.mo.dD().a(tVar, dsVar, cbVar, dvVar, z, ceVar, new v(false));
    }

    @Override // com.google.android.gms.internal.ah
    public void a(String str, cd cdVar) {
        this.mo.dD().a(str, cdVar);
    }

    @Override // com.google.android.gms.internal.ah
    public void a(final String str, final JSONObject jSONObject) {
        runOnUiThread(new Runnable() { // from class: com.google.android.gms.internal.aj.1
            @Override // java.lang.Runnable
            public void run() {
                aj.this.mo.a(str, jSONObject);
            }
        });
    }

    @Override // com.google.android.gms.internal.ah
    public void destroy() {
        this.mo.destroy();
    }

    @Override // com.google.android.gms.internal.ah
    public void f(final String str) {
        runOnUiThread(new Runnable() { // from class: com.google.android.gms.internal.aj.2
            @Override // java.lang.Runnable
            public void run() {
                aj.this.mo.loadUrl(str);
            }
        });
    }

    @Override // com.google.android.gms.internal.ah
    public void g(String str) {
        this.mo.dD().a(str, (cd) null);
    }

    @Override // com.google.android.gms.internal.ah
    public void pause() {
        gi.a(this.mo);
    }

    @Override // com.google.android.gms.internal.ah
    public void resume() {
        gi.b(this.mo);
    }
}
