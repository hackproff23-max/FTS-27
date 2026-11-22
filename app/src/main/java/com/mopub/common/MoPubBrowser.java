package com.mopub.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.google.android.gms.drive.DriveFile;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Drawables;
import com.mopub.common.util.Intents;
import com.mopub.mobileads.util.WebViews;

/* JADX INFO: loaded from: classes.dex */
public class MoPubBrowser extends Activity {
    public static final String DESTINATION_URL_KEY = "URL";
    private static final int INNER_LAYOUT_ID = 1;
    private ImageButton mBackButton;
    private ImageButton mCloseButton;
    private ImageButton mForwardButton;
    private ImageButton mRefreshButton;
    private WebView mWebView;

    public static void open(Context context, String url) {
        MoPubLog.d("Opening url in MoPubBrowser: " + url);
        Intent intent = new Intent(context, (Class<?>) MoPubBrowser.class);
        intent.putExtra(DESTINATION_URL_KEY, url);
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        context.startActivity(intent);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(-1);
        getWindow().requestFeature(2);
        getWindow().setFeatureInt(2, -1);
        setContentView(getMoPubBrowserView());
        initializeWebView();
        initializeButtons();
        enableCookies();
    }

    private void initializeWebView() {
        WebSettings webSettings = this.mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        this.mWebView.loadUrl(getIntent().getStringExtra(DESTINATION_URL_KEY));
        this.mWebView.setWebViewClient(new WebViewClient() { // from class: com.mopub.common.MoPubBrowser.1
            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                MoPubLog.d("MoPubBrowser error: " + description);
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null) {
                    return false;
                }
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
                if (!Intents.isDeepLink(url) || !Intents.deviceCanHandleIntent(MoPubBrowser.this, intent)) {
                    return false;
                }
                MoPubBrowser.this.startActivity(intent);
                MoPubBrowser.this.finish();
                return true;
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                MoPubBrowser.this.mForwardButton.setImageDrawable(Drawables.UNRIGHT_ARROW.createDrawable(MoPubBrowser.this));
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Drawable backImageDrawable = view.canGoBack() ? Drawables.LEFT_ARROW.createDrawable(MoPubBrowser.this) : Drawables.UNLEFT_ARROW.createDrawable(MoPubBrowser.this);
                MoPubBrowser.this.mBackButton.setImageDrawable(backImageDrawable);
                Drawable forwardImageDrawable = view.canGoForward() ? Drawables.RIGHT_ARROW.createDrawable(MoPubBrowser.this) : Drawables.UNRIGHT_ARROW.createDrawable(MoPubBrowser.this);
                MoPubBrowser.this.mForwardButton.setImageDrawable(forwardImageDrawable);
            }
        });
        this.mWebView.setWebChromeClient(new WebChromeClient() { // from class: com.mopub.common.MoPubBrowser.2
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView, int progress) {
                MoPubBrowser.this.setTitle("Loading...");
                MoPubBrowser.this.setProgress(progress * 100);
                if (progress == 100) {
                    MoPubBrowser.this.setTitle(webView.getUrl());
                }
            }
        });
    }

    private void initializeButtons() {
        this.mBackButton.setBackgroundColor(0);
        this.mBackButton.setOnClickListener(new View.OnClickListener() { // from class: com.mopub.common.MoPubBrowser.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (MoPubBrowser.this.mWebView.canGoBack()) {
                    MoPubBrowser.this.mWebView.goBack();
                }
            }
        });
        this.mForwardButton.setBackgroundColor(0);
        this.mForwardButton.setOnClickListener(new View.OnClickListener() { // from class: com.mopub.common.MoPubBrowser.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (MoPubBrowser.this.mWebView.canGoForward()) {
                    MoPubBrowser.this.mWebView.goForward();
                }
            }
        });
        this.mRefreshButton.setBackgroundColor(0);
        this.mRefreshButton.setOnClickListener(new View.OnClickListener() { // from class: com.mopub.common.MoPubBrowser.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MoPubBrowser.this.mWebView.reload();
            }
        });
        this.mCloseButton.setBackgroundColor(0);
        this.mCloseButton.setOnClickListener(new View.OnClickListener() { // from class: com.mopub.common.MoPubBrowser.6
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MoPubBrowser.this.finish();
            }
        });
    }

    private void enableCookies() {
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().startSync();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        CookieSyncManager.getInstance().stopSync();
        WebViews.onPause(this.mWebView, isFinishing());
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        CookieSyncManager.getInstance().startSync();
        WebViews.onResume(this.mWebView);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mWebView.destroy();
        this.mWebView = null;
    }

    private View getMoPubBrowserView() {
        LinearLayout linearLayout = new LinearLayout(this);
        ViewGroup.LayoutParams browserLayoutParams = new LinearLayout.LayoutParams(-1, -1);
        linearLayout.setLayoutParams(browserLayoutParams);
        linearLayout.setOrientation(1);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        LinearLayout.LayoutParams outerLayoutParams = new LinearLayout.LayoutParams(-1, -2);
        relativeLayout.setLayoutParams(outerLayoutParams);
        linearLayout.addView(relativeLayout);
        LinearLayout innerLayout = new LinearLayout(this);
        innerLayout.setId(1);
        RelativeLayout.LayoutParams innerLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
        innerLayoutParams.addRule(12);
        innerLayout.setLayoutParams(innerLayoutParams);
        innerLayout.setBackgroundDrawable(Drawables.BACKGROUND.createDrawable(this));
        relativeLayout.addView(innerLayout);
        this.mBackButton = getButton(Drawables.LEFT_ARROW.createDrawable(this));
        this.mForwardButton = getButton(Drawables.RIGHT_ARROW.createDrawable(this));
        this.mRefreshButton = getButton(Drawables.REFRESH.createDrawable(this));
        this.mCloseButton = getButton(Drawables.CLOSE.createDrawable(this));
        innerLayout.addView(this.mBackButton);
        innerLayout.addView(this.mForwardButton);
        innerLayout.addView(this.mRefreshButton);
        innerLayout.addView(this.mCloseButton);
        this.mWebView = new WebView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(2, 1);
        this.mWebView.setLayoutParams(layoutParams);
        relativeLayout.addView(this.mWebView);
        return linearLayout;
    }

    private ImageButton getButton(Drawable drawable) {
        ImageButton imageButton = new ImageButton(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2, 1.0f);
        layoutParams.gravity = 16;
        imageButton.setLayoutParams(layoutParams);
        imageButton.setImageDrawable(drawable);
        return imageButton;
    }

    @VisibleForTesting
    @Deprecated
    void setWebView(WebView webView) {
        this.mWebView = webView;
    }
}
