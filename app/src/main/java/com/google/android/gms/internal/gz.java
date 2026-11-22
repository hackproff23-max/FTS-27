package com.google.android.gms.internal;

import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.net.URI;
import java.net.URISyntaxException;

/* JADX INFO: loaded from: classes.dex */
@ey
public class gz extends WebViewClient {
    private final gu mo;
    private final String xr;
    private boolean xs = false;
    private final fb xt;

    public gz(fb fbVar, gu guVar, String str) {
        this.xr = Z(str);
        this.mo = guVar;
        this.xt = fbVar;
    }

    private String Z(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return str.endsWith("/") ? str.substring(0, str.length() - 1) : str;
        } catch (IndexOutOfBoundsException e) {
            gr.T(e.getMessage());
            return str;
        }
    }

    protected boolean Y(String str) {
        boolean z = false;
        String strZ = Z(str);
        if (!TextUtils.isEmpty(strZ)) {
            try {
                URI uri = new URI(strZ);
                if ("passback".equals(uri.getScheme())) {
                    gr.S("Passback received");
                    this.xt.cG();
                    z = true;
                } else if (!TextUtils.isEmpty(this.xr)) {
                    URI uri2 = new URI(this.xr);
                    String host = uri2.getHost();
                    String host2 = uri.getHost();
                    String path = uri2.getPath();
                    String path2 = uri.getPath();
                    if (jv.equal(host, host2) && jv.equal(path, path2)) {
                        gr.S("Passback received");
                        this.xt.cG();
                        z = true;
                    }
                }
            } catch (URISyntaxException e) {
                gr.T(e.getMessage());
            }
        }
        return z;
    }

    @Override // android.webkit.WebViewClient
    public void onLoadResource(WebView view, String url) {
        gr.S("JavascriptAdWebViewClient::onLoadResource: " + url);
        if (Y(url)) {
            return;
        }
        this.mo.dD().onLoadResource(this.mo, url);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView view, String url) {
        gr.S("JavascriptAdWebViewClient::onPageFinished: " + url);
        if (this.xs) {
            return;
        }
        this.xt.cF();
        this.xs = true;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        gr.S("JavascriptAdWebViewClient::shouldOverrideUrlLoading: " + url);
        if (!Y(url)) {
            return this.mo.dD().shouldOverrideUrlLoading(this.mo, url);
        }
        gr.S("shouldOverrideUrlLoading: received passback url");
        return true;
    }
}
