package com.millennialmedia.android;

/* JADX INFO: loaded from: classes.dex */
class BannerExpandedWebViewClient extends MMWebViewClient {
    BannerExpandedWebViewClient(MMWebViewClient.MMWebViewClientListener mmWebViewClientListener, HttpRedirection.RedirectionListenerImpl redirListener) {
        super(mmWebViewClientListener, redirListener);
    }

    @Override // com.millennialmedia.android.MMWebViewClient
    void setMraidState(MMWebView mmWebView) {
        mmWebView.setMraidPlacementTypeInline();
        mmWebView.setMraidExpanded();
        mmWebView.setMraidReady();
    }
}
