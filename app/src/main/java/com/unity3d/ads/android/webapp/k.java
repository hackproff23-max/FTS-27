package com.unity3d.ads.android.webapp;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.unity3d.ads.android.UnityAdsDeviceLog;

/* JADX INFO: compiled from: UnityAdsWebView.java */
/* JADX INFO: loaded from: classes.dex */
final class k extends WebViewClient {
    final /* synthetic */ UnityAdsWebView a;

    private k(UnityAdsWebView unityAdsWebView) {
        this.a = unityAdsWebView;
    }

    /* synthetic */ k(UnityAdsWebView unityAdsWebView, byte b) {
        this(unityAdsWebView);
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        UnityAdsDeviceLog.debug("Finished url: " + str);
        if (this.a.b != null && !this.a.c) {
            UnityAdsWebView.c(this.a);
            this.a.b.onWebAppLoaded();
        }
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        UnityAdsDeviceLog.debug("Trying to load url: " + str);
        return false;
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        UnityAdsDeviceLog.error(i + " (" + str2 + ") " + str);
        super.onReceivedError(webView, i, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public final void onLoadResource(WebView webView, String str) {
        super.onLoadResource(webView, str);
    }
}
