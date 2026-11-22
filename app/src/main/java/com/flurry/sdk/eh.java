package com.flurry.sdk;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.facebook.share.internal.ShareConstants;
import java.util.Collections;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"SetJavaScriptEnabled"})
public final class eh extends ec {
    private final String a;
    private WebView b;
    private WebViewClient c;
    private WebChromeClient d;
    private boolean e;
    private dq f;
    private ImageButton g;
    private ImageButton h;
    private ImageButton i;
    private ProgressBar j;
    private LinearLayout k;
    private boolean l;
    private ec.a m;

    public enum c {
        WEB_RESULT_UNKNOWN,
        WEB_RESULT_BACK,
        WEB_RESULT_CLOSE
    }

    @Override // com.flurry.sdk.ec
    public void initLayout() {
        super.initLayout();
        setOrientation(4);
    }

    @Override // com.flurry.sdk.ec
    public boolean onBackKey() {
        if (a()) {
            b();
        } else {
            a(c.WEB_RESULT_BACK);
        }
        d();
        return true;
    }

    @Override // com.flurry.sdk.ec
    protected void onViewLoadTimeout() {
        co.a(aw.EV_AD_WILL_CLOSE, Collections.emptyMap(), getContext(), getAdObject(), getAdController(), 0);
    }

    final class b extends WebViewClient {
        private b() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            gd.a(3, eh.this.a, "shouldOverrideUrlLoading: url = " + str);
            if (str == null || webView == null || webView != eh.this.b) {
                return false;
            }
            boolean zA = eh.this.a(str, eh.this.l);
            eh.this.l = false;
            return zA;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            gd.a(3, eh.this.a, "onPageStarted: url = " + str);
            if (str != null && webView != null && webView == eh.this.b) {
                eh.this.dismissProgressDialog();
                if (Build.VERSION.SDK_INT < 11 && eh.this.l && eh.this.a(str, eh.this.l)) {
                    gd.a(3, eh.this.a, "onPageStarted: stopLoading is called");
                    webView.stopLoading();
                }
                eh.this.j.setVisibility(0);
                eh.this.l = true;
                eh.this.e();
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            gd.a(3, eh.this.a, "onPageFinished: url = " + str);
            if (str != null && webView != null && webView == eh.this.b) {
                eh.this.j.setVisibility(8);
                eh.this.l = false;
                eh.this.e();
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            gd.a(3, eh.this.a, "onReceivedError: error = " + i + " description= " + str + " failingUrl= " + str2);
            super.onReceivedError(webView, i, str, str2);
            webView.clearSslPreferences();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
            gd.a(3, eh.this.a, "onReceivedSslError: error = " + sslError.toString());
            webView.clearSslPreferences();
        }
    }

    @Override // com.flurry.sdk.ec
    @TargetApi(11)
    public void onActivityResume() {
        super.onActivityResume();
        if (this.b != null && Build.VERSION.SDK_INT >= 11) {
            this.b.onResume();
        }
    }

    @Override // com.flurry.sdk.ec
    @TargetApi(11)
    public void onActivityPause() {
        super.onActivityPause();
        if (this.b != null && Build.VERSION.SDK_INT >= 11) {
            this.b.onPause();
        }
    }

    @Override // com.flurry.sdk.ec
    @TargetApi(11)
    public void onActivityDestroy() {
        super.onActivityDestroy();
        c();
    }

    final class a extends WebChromeClient {
        private a() {
        }

        @Override // android.webkit.WebChromeClient
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            gd.a(3, eh.this.a, "onShowCustomView(7)");
            eh.this.e = true;
            eh.this.j.setVisibility(0);
            eh.this.e();
        }

        @Override // android.webkit.WebChromeClient
        public void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
            gd.a(3, eh.this.a, "onShowCustomView(14)");
            eh.this.e = true;
            eh.this.j.setVisibility(0);
            eh.this.e();
        }

        @Override // android.webkit.WebChromeClient
        public void onHideCustomView() {
            gd.a(3, eh.this.a, "onHideCustomView()");
            eh.this.e = false;
            eh.this.j.setVisibility(8);
            eh.this.e();
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            eh.this.j.setProgress(i);
            super.onProgressChanged(webView, i);
            if (i == 100) {
                eh.this.j.setVisibility(8);
            }
        }
    }

    @TargetApi(11)
    public eh(Context context, String str, r rVar, ec.a aVar, boolean z) {
        super(context, rVar, aVar);
        this.a = getClass().getSimpleName();
        this.m = new ec.a() { // from class: com.flurry.sdk.eh.4
            @Override // com.flurry.sdk.ec.a
            public void a() {
                if (eh.this.f != null) {
                    eh.this.d();
                    eh.this.removeView(eh.this.f);
                    eh.this.f = null;
                }
            }

            @Override // com.flurry.sdk.ec.a
            public void b() {
                if (eh.this.f != null) {
                    eh.this.d();
                    eh.this.removeView(eh.this.f);
                    eh.this.f = null;
                }
            }

            @Override // com.flurry.sdk.ec.a
            public void c() {
                if (eh.this.f != null) {
                    eh.this.d();
                    eh.this.removeView(eh.this.f);
                    eh.this.f = null;
                }
            }
        };
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.k = new LinearLayout(context);
        this.k.setOrientation(1);
        this.k.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.b = new WebView(context);
        this.c = new b();
        this.d = new a();
        this.b.getSettings().setJavaScriptEnabled(true);
        this.b.getSettings().setUseWideViewPort(true);
        this.b.getSettings().setLoadWithOverviewMode(true);
        this.b.getSettings().setBuiltInZoomControls(true);
        if (Build.VERSION.SDK_INT >= 11) {
            this.b.getSettings().setDisplayZoomControls(false);
        }
        this.b.setWebViewClient(this.c);
        this.b.setWebChromeClient(this.d);
        this.b.setPadding(5, 5, 5, 5);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        String strD = i.a().h().d();
        if (z && !TextUtils.isEmpty(strD)) {
            HashMap map = new HashMap();
            map.put("Cookie", strD);
            this.b.loadUrl(str, map);
        } else {
            this.b.loadUrl(str);
        }
        this.j = new ProgressBar(context, null, R.attr.progressBarStyleHorizontal);
        this.j.setMax(100);
        this.j.setProgress(0);
        this.j.setLayoutParams(new RelativeLayout.LayoutParams(-1, hn.b(3)));
        this.g = new ImageButton(context);
        this.g.setPadding(5, 0, 5, 0);
        this.g.setImageBitmap(el.a());
        this.g.setBackgroundColor(getResources().getColor(R.color.transparent));
        this.g.setOnClickListener(new View.OnClickListener() { // from class: com.flurry.sdk.eh.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                eh.this.a(c.WEB_RESULT_CLOSE);
            }
        });
        this.h = new ImageButton(context);
        this.h.setId(1);
        this.h.setPadding(5, 0, 5, 0);
        this.h.setImageBitmap(el.b());
        this.h.setBackgroundColor(getResources().getColor(R.color.transparent));
        this.h.setVisibility(0);
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.flurry.sdk.eh.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (eh.this.b != null && eh.this.b.canGoBack()) {
                    eh.this.b.goBack();
                } else {
                    eh.this.a(c.WEB_RESULT_BACK);
                }
            }
        });
        this.i = new ImageButton(context);
        this.i.setPadding(5, 0, 5, 0);
        this.i.setImageBitmap(el.c());
        this.i.setBackgroundColor(getResources().getColor(R.color.transparent));
        this.i.setOnClickListener(new View.OnClickListener() { // from class: com.flurry.sdk.eh.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (eh.this.b != null && eh.this.b.canGoForward()) {
                    eh.this.b.goForward();
                }
            }
        });
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.darker_gray));
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        relativeLayout.setPadding(10, 10, 10, 10);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(hn.b(35), hn.b(35));
        layoutParams2.addRule(11);
        layoutParams2.addRule(13);
        layoutParams2.setMargins(10, 10, 10, 10);
        relativeLayout.addView(this.g, layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(hn.b(35), hn.b(35));
        layoutParams3.addRule(9);
        layoutParams2.addRule(13);
        layoutParams3.addRule(0, this.i.getId());
        layoutParams3.setMargins(10, 10, 10, 10);
        relativeLayout.addView(this.h, layoutParams3);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(hn.b(35), hn.b(35));
        layoutParams4.addRule(1, this.h.getId());
        layoutParams2.addRule(13);
        layoutParams4.setMargins(10, 10, 10, 10);
        relativeLayout.addView(this.i, layoutParams4);
        showProgressDialog();
        relativeLayout.setGravity(17);
        e();
        this.k.addView(relativeLayout);
        this.k.addView(this.j);
        this.k.addView(this.b, layoutParams);
        setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        addView(this.k);
    }

    public String getUrl() {
        if (this.b == null) {
            return null;
        }
        return this.b.getUrl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.b.canGoForward()) {
            this.i.setVisibility(0);
        } else {
            this.i.setVisibility(4);
        }
    }

    public boolean a() {
        return this.e || (this.b != null && this.b.canGoBack());
    }

    public void b() {
        if (this.e) {
            this.d.onHideCustomView();
        } else if (this.b != null) {
            this.b.goBack();
        }
    }

    @TargetApi(11)
    public void c() {
        if (this.b != null) {
            dismissProgressDialog();
            removeView(this.b);
            this.b.stopLoading();
            if (Build.VERSION.SDK_INT >= 11) {
                this.b.onPause();
            }
            this.b.destroy();
            this.b = null;
        }
    }

    public boolean a(String str, boolean z) {
        boolean zC;
        if (cv.g(str)) {
            if (cv.g(str)) {
                if (getAdController().q()) {
                    new dr();
                    this.f = dr.a(getContext(), ds.VIDEO_AD_TYPE_MRAID, getAdObject(), this.m);
                } else {
                    new dr();
                    this.f = dr.a(getContext(), ds.VIDEO_AD_TYPE_CLIPS, getAdObject(), this.m);
                }
                this.f.initLayout();
                addView(this.f);
            }
            return true;
        }
        if (cv.d(str)) {
            if (!z) {
                z = a(str, getUrl());
            }
            cu.a(getContext(), str);
            if (z) {
                g();
            }
            co.a(aw.INTERNAL_EV_APP_EXIT, Collections.emptyMap(), getContext(), getAdObject(), getAdController(), 0);
            return true;
        }
        if (cv.f(str)) {
            zC = cu.b(getContext(), str);
            if (zC) {
                if (!z) {
                    z = a(str, getUrl());
                }
                if (z) {
                    g();
                }
                co.a(aw.INTERNAL_EV_APP_EXIT, Collections.emptyMap(), getContext(), getAdObject(), getAdController(), 0);
                return zC;
            }
        } else {
            zC = cu.c(getContext(), str);
            if (zC) {
                if (!z) {
                    z = a(str, getUrl());
                }
                if (z) {
                    g();
                }
                co.a(aw.INTERNAL_EV_APP_EXIT, Collections.emptyMap(), getContext(), getAdObject(), getAdController(), 0);
            }
        }
        return zC;
    }

    public void a(c cVar) {
        if (cVar.equals(c.WEB_RESULT_CLOSE) || cVar.equals(c.WEB_RESULT_UNKNOWN)) {
            g();
        } else {
            h();
        }
    }

    private boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        String queryParameter = Uri.parse(str2).getQueryParameter(ShareConstants.WEB_DIALOG_PARAM_LINK);
        if (TextUtils.isEmpty(queryParameter) || !queryParameter.equalsIgnoreCase(str)) {
            return false;
        }
        return true;
    }

    private void g() {
        onViewClose();
    }

    private void h() {
        onViewBack();
    }

    public void d() {
        setVisibility(0);
        if (this.f != null) {
            this.f.d();
        }
    }
}
