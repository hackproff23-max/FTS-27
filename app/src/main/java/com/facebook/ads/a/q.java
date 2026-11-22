package com.facebook.ads.a;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.net.http.SslError;
import android.util.DisplayMetrics;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.ImpressionListener;
import com.facebook.ads.InterstitialAd;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class q extends RelativeLayout implements d {
    private static final String a = q.class.getSimpleName();
    private final AdView b;
    private final String c;
    private final AdSize d;
    private AdListener e;
    private ImpressionListener f;
    private WebView g;
    private k h;
    private z i;
    private final DisplayMetrics j;
    private int k;
    private long l;
    private b.a m;

    private class a extends WebViewClient {
        private a() {
        }

        @Override // android.webkit.WebViewClient
        public void onLoadResource(WebView webView, String str) {
            q.this.i.f();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (p.a()) {
                sslErrorHandler.proceed();
            } else {
                sslErrorHandler.cancel();
            }
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (q.this.e != null) {
                q.this.e.onAdClicked(q.this.b);
            }
            com.facebook.ads.a.a.a aVarA = com.facebook.ads.a.a.b.a(q.this.getContext(), Uri.parse(str));
            Map mapJ = ((y) q.this.i.a()).j();
            mapJ.put(InterstitialAd.INTERSTITIAL_UNIQUE_ID_EXTRA, UUID.randomUUID().toString());
            if (aVarA == null) {
                return true;
            }
            try {
                q.this.m = aVarA.a();
                q.this.l = System.currentTimeMillis();
                aVarA.a(mapJ);
                return true;
            } catch (Exception e) {
                Log.e(q.a, "Error executing action", e);
                return true;
            }
        }
    }

    public q(AdView adView, String str, AdSize adSize) {
        super(adView.getContext());
        this.e = null;
        this.f = null;
        if (adSize == null || adSize == AdSize.INTERSTITIAL) {
            throw new IllegalArgumentException("adSize");
        }
        this.b = adView;
        this.c = str;
        this.d = adSize;
        this.j = getContext().getResources().getDisplayMetrics();
        a(getContext());
        this.i = new z(this.g, new f.a() { // from class: com.facebook.ads.a.q.1
            @Override // com.facebook.ads.a.f.a
            public boolean a() {
                return q.this.g();
            }

            @Override // com.facebook.ads.a.f.a
            public void b() {
                if (q.this.h != null) {
                    q.this.h.a("on imp sent");
                }
            }

            @Override // com.facebook.ads.a.f.a
            public void c() {
                if (q.this.f != null) {
                    q.this.f.onLoggingImpression(q.this.b);
                }
            }

            @Override // com.facebook.ads.a.f.a
            public boolean d() {
                return false;
            }
        }, 1000L, getContext());
    }

    private void a(Context context) {
        n nVar;
        this.g = new WebView(context);
        this.g.setVisibility(8);
        s.a(this.g, new a(), new r());
        addView(this.g);
        h();
        if (this.d == AdSize.BANNER_HEIGHT_90) {
            nVar = n.WEBVIEW_BANNER_90;
        } else {
            nVar = this.d == AdSize.BANNER_HEIGHT_50 ? n.WEBVIEW_BANNER_50 : n.WEBVIEW_BANNER_LEGACY;
        }
        this.h = new k(getContext(), this.c, this.d, nVar, true, o.HTML, 1, e());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(y yVar) {
        if (this.g != null) {
            this.g.loadUrl("about:blank");
            this.g.clearCache(true);
            this.g.setVisibility(8);
            this.l = 0L;
            this.m = null;
            this.g.loadDataWithBaseURL(s.a(), yVar.c(), "text/html", "utf-8", null);
            this.g.setVisibility(0);
        }
    }

    private j.b e() {
        return new j.b() { // from class: com.facebook.ads.a.q.2
            @Override // com.facebook.ads.a.j.b
            public void a(AdError adError) {
                q.this.i.c();
                if (q.this.e != null) {
                    q.this.e.onError(q.this.b, adError);
                }
            }

            @Override // com.facebook.ads.a.j.b
            public void a(l lVar) {
                q.this.i.c();
                e eVarD = lVar.d();
                if (eVarD != null && (eVarD instanceof y)) {
                    q.this.i.a((y) eVarD);
                    q.this.a((y) eVarD);
                    if (q.this.e != null) {
                        q.this.e.onAdLoaded(q.this.b);
                    }
                    q.this.k = lVar.c();
                    return;
                }
                if (eVarD == null) {
                    if (q.this.e != null) {
                        q.this.e.onError(q.this.b, lVar.e() != null ? lVar.e() : AdError.INTERNAL_ERROR);
                    }
                    if (q.this.h != null) {
                        q.this.h.a("on no fill");
                        return;
                    }
                    return;
                }
                if (q.this.e != null) {
                    q.this.e.onError(q.this.b, AdError.INTERNAL_ERROR);
                }
                if (q.this.h != null) {
                    q.this.h.a("on internal error");
                }
            }
        };
    }

    private void f() {
        if (this.h == null) {
            throw new RuntimeException("No request controller available, has the AdView been destroyed?");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        return p.a(getContext(), this, (int) Math.ceil(this.d.getWidth() * this.j.density), (int) Math.ceil(this.d.getHeight() * this.j.density), this.k);
    }

    private void h() {
        if (this.g != null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(((int) (((float) this.j.widthPixels) / this.j.density)) >= this.d.getWidth() ? this.j.widthPixels : (int) Math.ceil(this.d.getWidth() * this.j.density), (int) Math.ceil(this.d.getHeight() * this.j.density));
            layoutParams.addRule(14);
            this.g.setLayoutParams(layoutParams);
        }
    }

    @Override // com.facebook.ads.a.d
    public void a() {
        this.h.a();
    }

    @Override // com.facebook.ads.a.d
    public void b() {
        f();
        this.h.b();
    }

    @Override // com.facebook.ads.a.d
    public void c() {
        if (this.h != null) {
            this.h.c();
            this.h = null;
        }
        this.i.c();
        this.i.g();
        if (this.g != null) {
            s.a(this.g);
            removeView(this.g);
            this.g.destroy();
            this.g = null;
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        h();
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (this.h != null) {
            this.h.a(i);
        }
        if (i != 0) {
            this.i.c();
            return;
        }
        this.i.b();
        if (this.l <= 0 || this.m == null) {
            return;
        }
        c.a(b.a(this.l, this.m));
    }

    @Override // com.facebook.ads.a.d
    public void setAdListener(AdListener adListener) {
        this.e = adListener;
    }

    @Override // com.facebook.ads.a.d
    public void setImpressionListener(ImpressionListener impressionListener) {
        this.f = impressionListener;
    }
}
