package com.facebook.ads.a;

import android.content.Context;
import android.webkit.WebView;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class z extends f {
    private WebView c;
    private volatile boolean d;

    public z(WebView webView, f.a aVar, long j, Context context) {
        super(aVar, j, context);
        this.c = webView;
    }

    private void a(Map map) {
        if (this.b == null) {
            return;
        }
        String strE = ((y) this.b).e();
        if (ag.a(strE)) {
            return;
        }
        new ad(map).execute(strE);
    }

    public void a(y yVar) {
        super.a((e) yVar);
        this.d = false;
    }

    @Override // com.facebook.ads.a.f
    protected synchronized void e() {
        if (this.b != null) {
            y yVar = (y) this.b;
            if (this.c != null && !ag.a(yVar.f())) {
                this.c.loadUrl("javascript:" + yVar.f());
            }
            a(Collections.singletonMap("evt", "native_imp"));
            if (this.a != null) {
                this.a.b();
            }
        }
    }

    public synchronized void f() {
        if (!this.d && this.b != null) {
            y yVar = (y) this.b;
            if (this.c != null && !ag.a(yVar.d())) {
                this.c.loadUrl("javascript:" + yVar.d());
            }
            b();
            this.d = true;
        }
    }

    public synchronized void g() {
        this.c = null;
    }

    public void h() {
        a(Collections.singletonMap("evt", "interstitial_displayed"));
    }
}
