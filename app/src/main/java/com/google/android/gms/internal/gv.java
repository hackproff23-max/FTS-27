package com.google.android.gms.internal;

import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.mopub.common.Constants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@ey
public class gv extends WebViewClient {
    private final Object mH;
    protected final gu mo;
    private cb pP;
    private cg qa;
    private ce qc;
    private v qd;
    private a tu;
    private final HashMap<String, cd> xe;
    private t xf;
    private ds xg;
    private boolean xh;
    private boolean xi;
    private dv xj;
    private final dl xk;

    public interface a {
        void a(gu guVar);
    }

    public gv(gu guVar, boolean z) {
        this(guVar, z, new dl(guVar, guVar.getContext(), new bl(guVar.getContext())));
    }

    gv(gu guVar, boolean z, dl dlVar) {
        this.xe = new HashMap<>();
        this.mH = new Object();
        this.xh = false;
        this.mo = guVar;
        this.xi = z;
        this.xk = dlVar;
    }

    private static boolean d(Uri uri) {
        String scheme = uri.getScheme();
        return Constants.HTTP.equalsIgnoreCase(scheme) || Constants.HTTPS.equalsIgnoreCase(scheme);
    }

    private void e(Uri uri) {
        String path = uri.getPath();
        cd cdVar = this.xe.get(path);
        if (cdVar == null) {
            gr.V("No GMSG handler found for GMSG: " + uri);
            return;
        }
        Map<String, String> mapC = gi.c(uri);
        if (gr.v(2)) {
            gr.V("Received GMSG: " + path);
            for (String str : mapC.keySet()) {
                gr.V("  " + str + ": " + mapC.get(str));
            }
        }
        cdVar.a(this.mo, mapC);
    }

    public final void A(boolean z) {
        this.xh = z;
    }

    public final void a(Cdo cdo) {
        boolean zDH = this.mo.dH();
        a(new dr(cdo, (!zDH || this.mo.ac().oq) ? this.xf : null, zDH ? null : this.xg, this.xj, this.mo.dG()));
    }

    protected void a(dr drVar) {
        dp.a(this.mo.getContext(), drVar);
    }

    public final void a(a aVar) {
        this.tu = aVar;
    }

    public void a(t tVar, ds dsVar, cb cbVar, dv dvVar, boolean z, ce ceVar, cg cgVar, v vVar) {
        a(tVar, dsVar, cbVar, dvVar, z, ceVar, vVar);
        a("/setInterstitialProperties", new cf(cgVar));
        this.qa = cgVar;
    }

    public void a(t tVar, ds dsVar, cb cbVar, dv dvVar, boolean z, ce ceVar, v vVar) {
        if (vVar == null) {
            vVar = new v(false);
        }
        a("/appEvent", new ca(cbVar));
        a("/canOpenURLs", cc.pR);
        a("/canOpenIntents", cc.pS);
        a("/click", cc.pT);
        a("/close", cc.pU);
        a("/customClose", cc.pV);
        a("/httpTrack", cc.pW);
        a("/log", cc.pX);
        a("/open", new ci(ceVar, vVar));
        a("/touch", cc.pY);
        a("/video", cc.pZ);
        a("/mraid", new ch());
        this.xf = tVar;
        this.xg = dsVar;
        this.pP = cbVar;
        this.qc = ceVar;
        this.xj = dvVar;
        this.qd = vVar;
        A(z);
    }

    public final void a(String str, cd cdVar) {
        this.xe.put(str, cdVar);
    }

    public final void a(boolean z, int i) {
        a(new dr((!this.mo.dH() || this.mo.ac().oq) ? this.xf : null, this.xg, this.xj, this.mo, z, i, this.mo.dG()));
    }

    public final void a(boolean z, int i, String str) {
        boolean zDH = this.mo.dH();
        a(new dr((!zDH || this.mo.ac().oq) ? this.xf : null, zDH ? null : this.xg, this.pP, this.xj, this.mo, z, i, str, this.mo.dG(), this.qc));
    }

    public final void a(boolean z, int i, String str, String str2) {
        boolean zDH = this.mo.dH();
        a(new dr((!zDH || this.mo.ac().oq) ? this.xf : null, zDH ? null : this.xg, this.pP, this.xj, this.mo, z, i, str, str2, this.mo.dG(), this.qc));
    }

    public final void cg() {
        synchronized (this.mH) {
            this.xh = false;
            this.xi = true;
            final dp dpVarDC = this.mo.dC();
            if (dpVarDC != null) {
                if (gq.dB()) {
                    dpVarDC.cg();
                } else {
                    gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.gv.1
                        @Override // java.lang.Runnable
                        public void run() {
                            dpVarDC.cg();
                        }
                    });
                }
            }
        }
    }

    public v dM() {
        return this.qd;
    }

    public boolean dN() {
        boolean z;
        synchronized (this.mH) {
            z = this.xi;
        }
        return z;
    }

    public void dO() {
        if (dN()) {
            this.xk.bY();
        }
    }

    @Override // android.webkit.WebViewClient
    public final void onLoadResource(WebView webView, String url) {
        gr.V("Loading resource: " + url);
        Uri uri = Uri.parse(url);
        if ("gmsg".equalsIgnoreCase(uri.getScheme()) && "mobileads.google.com".equalsIgnoreCase(uri.getHost())) {
            e(uri);
        }
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String url) {
        if (this.tu != null) {
            this.tu.a(this.mo);
            this.tu = null;
        }
    }

    public final void reset() {
        synchronized (this.mH) {
            this.xe.clear();
            this.xf = null;
            this.xg = null;
            this.tu = null;
            this.pP = null;
            this.xh = false;
            this.xi = false;
            this.qc = null;
            this.xj = null;
        }
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String url) {
        Uri uri;
        gr.V("AdWebView shouldOverrideUrlLoading: " + url);
        Uri uriA = Uri.parse(url);
        if ("gmsg".equalsIgnoreCase(uriA.getScheme()) && "mobileads.google.com".equalsIgnoreCase(uriA.getHost())) {
            e(uriA);
        } else {
            if (this.xh && webView == this.mo && d(uriA)) {
                return super.shouldOverrideUrlLoading(webView, url);
            }
            if (this.mo.willNotDraw()) {
                gr.W("AdWebView unable to handle URL: " + url);
            } else {
                try {
                    k kVarDF = this.mo.dF();
                    if (kVarDF != null && kVarDF.b(uriA)) {
                        uriA = kVarDF.a(uriA, this.mo.getContext());
                    }
                    uri = uriA;
                } catch (l e) {
                    gr.W("Unable to append parameter to URL: " + url);
                    uri = uriA;
                }
                if (this.qd == null || this.qd.az()) {
                    a(new Cdo("android.intent.action.VIEW", uri.toString(), null, null, null, null, null));
                } else {
                    this.qd.d(url);
                }
            }
        }
        return true;
    }
}
