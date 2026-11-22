package com.millennialmedia.android;

/* JADX INFO: loaded from: classes.dex */
class InterstitialWebViewClient extends MMWebViewClient {
    InterstitialWebViewClient(MMWebViewClient.MMWebViewClientListener mmWebViewClientListener, HttpRedirection.RedirectionListenerImpl redirListener) {
        super(mmWebViewClientListener, redirListener);
    }

    @Override // com.millennialmedia.android.MMWebViewClient
    void setMraidState(MMWebView mmWebView) {
        mmWebView.setMraidPlacementTypeInterstitial();
        mmWebView.setMraidDefault();
        mmWebView.setMraidReady();
    }
}
