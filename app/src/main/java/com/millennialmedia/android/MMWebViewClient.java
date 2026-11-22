package com.millennialmedia.android;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
abstract class MMWebViewClient extends WebViewClient {
    private static final String TAG = "MMWebViewClient";
    private ExecutorService cachedExecutor = Executors.newCachedThreadPool();
    boolean isLastMMCommandResize;
    MMWebViewClientListener mmWebViewClientListener;
    HttpRedirection.RedirectionListenerImpl redirectListenerImpl;

    abstract void setMraidState(MMWebView mMWebView);

    MMWebViewClient(MMWebViewClientListener mmWebViewClientListener, HttpRedirection.RedirectionListenerImpl redirListener) {
        this.mmWebViewClientListener = mmWebViewClientListener;
        this.redirectListenerImpl = redirListener;
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String urlString, Bitmap favicon) {
        MMLog.d(TAG, String.format("onPageStarted: %s", urlString));
        this.mmWebViewClientListener.onPageStarted(urlString);
        MMWebView mmWebView = (MMWebView) webView;
        mmWebView.mraidState = UnityAdsConstants.UNITY_ADS_TEXTKEY_LOADING;
        mmWebView.requiresPreAdSizeFix = false;
        super.onPageStarted(webView, urlString, favicon);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        MMWebView mmWebView = (MMWebView) webView;
        if (mmWebView.isOriginalUrl(url)) {
            return true;
        }
        MMLog.v(TAG, "@@@@@@@@@@SHOULDOVERRIDELOADING@@@@@@@@@@@@@ Url is " + url + " for " + webView);
        if (url.substring(0, 6).equalsIgnoreCase("mmsdk:")) {
            MMLog.v(TAG, "Running JS bridge command: " + url);
            MMCommand command = new MMCommand((MMWebView) webView, url);
            this.isLastMMCommandResize = command.isResizeCommand();
            this.cachedExecutor.execute(command);
            return true;
        }
        if (this.redirectListenerImpl.isExpandingToUrl()) {
            return false;
        }
        this.redirectListenerImpl.url = url;
        this.redirectListenerImpl.weakContext = new WeakReference<>(webView.getContext());
        this.redirectListenerImpl.creatorAdImplInternalId = mmWebView.creatorAdImplId;
        HttpRedirection.startActivityFromUri(this.redirectListenerImpl);
        return true;
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
        MMLog.e(TAG, String.format("Error: %s %s %s", Integer.valueOf(errorCode), description, failingUrl));
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String url) {
        MMWebView mmWebView = (MMWebView) webView;
        if (!mmWebView.isOriginalUrl(url)) {
            this.mmWebViewClientListener.onPageFinished(url);
            mmWebView.setAdProperties();
            setMraidState(mmWebView);
            MMLog.d(TAG, "onPageFinished webview: " + mmWebView.toString() + "url is " + url);
        }
        super.onPageFinished(webView, url);
    }

    static abstract class MMWebViewClientListener {
        MMWebViewClientListener() {
        }

        void onPageFinished(String url) {
        }

        void onPageStarted(String url) {
        }
    }
}
