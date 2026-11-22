package com.millennialmedia.android;

/* JADX INFO: loaded from: classes.dex */
class BannerWebViewClient extends MMWebViewClient {
    BannerWebViewClient(MMWebViewClient.MMWebViewClientListener mmWebViewClientListener, HttpRedirection.RedirectionListenerImpl redirListener) {
        super(mmWebViewClientListener, redirListener);
    }

    @Override // com.millennialmedia.android.MMWebViewClient
    void setMraidState(MMWebView mmWebView) {
        mmWebView.setMraidPlacementTypeInline();
        mmWebView.setMraidDefault();
        mmWebView.setMraidReady();
    }
}
