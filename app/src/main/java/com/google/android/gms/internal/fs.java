package com.google.android.gms.internal;

import java.util.Map;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class fs {
    gu mo;
    private String uE;
    private final Object mH = new Object();
    private gj<fu> uF = new gj<>();
    public final cd uG = new cd() { // from class: com.google.android.gms.internal.fs.1
        @Override // com.google.android.gms.internal.cd
        public void a(gu guVar, Map<String, String> map) {
            synchronized (fs.this.mH) {
                if (fs.this.uF.isDone()) {
                    return;
                }
                fu fuVar = new fu(1, map);
                gr.W("Invalid " + fuVar.getType() + " request error: " + fuVar.cT());
                fs.this.uF.a(fuVar);
            }
        }
    };
    public final cd uH = new cd() { // from class: com.google.android.gms.internal.fs.2
        @Override // com.google.android.gms.internal.cd
        public void a(gu guVar, Map<String, String> map) {
            synchronized (fs.this.mH) {
                if (fs.this.uF.isDone()) {
                    return;
                }
                fu fuVar = new fu(-2, map);
                String url = fuVar.getUrl();
                if (url == null) {
                    gr.W("URL missing in loadAdUrl GMSG.");
                    return;
                }
                if (url.contains("%40mediation_adapters%40")) {
                    String strReplaceAll = url.replaceAll("%40mediation_adapters%40", ge.a(guVar.getContext(), map.get("check_adapters"), fs.this.uE));
                    fuVar.setUrl(strReplaceAll);
                    gr.V("Ad request URL modified to " + strReplaceAll);
                }
                fs.this.uF.a(fuVar);
            }
        }
    };

    public fs(String str) {
        this.uE = str;
    }

    public void b(gu guVar) {
        jx.aU("setAdWebView must be called on the main thread.");
        this.mo = guVar;
    }

    public Future<fu> cR() {
        return this.uF;
    }

    public void cS() {
        jx.aU("destroyAdWebView must be called on the main thread.");
        if (this.mo != null) {
            this.mo.destroy();
            this.mo = null;
        }
    }
}
