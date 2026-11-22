package com.mopub.mobileads;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.drive.DriveFile;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.exceptions.UrlParseException;
import com.unity3d.ads.android.properties.UnityAdsConstants;

/* JADX INFO: loaded from: classes.dex */
class HtmlWebViewClient extends WebViewClient {
    static final String MOPUB_FAIL_LOAD = "mopub://failLoad";
    static final String MOPUB_FINISH_LOAD = "mopub://finishLoad";
    private final String mClickthroughUrl;
    private final Context mContext;
    private BaseHtmlWebView mHtmlWebView;
    private HtmlWebViewListener mHtmlWebViewListener;
    private final String mRedirectUrl;

    HtmlWebViewClient(HtmlWebViewListener htmlWebViewListener, BaseHtmlWebView htmlWebView, String clickthrough, String redirect) {
        this.mHtmlWebViewListener = htmlWebViewListener;
        this.mHtmlWebView = htmlWebView;
        this.mClickthroughUrl = clickthrough;
        this.mRedirectUrl = redirect;
        this.mContext = htmlWebView.getContext();
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        MoPubLog.d("Ad clicked. Click URL: " + url);
        if (!handleSpecialMoPubScheme(url) && !handlePhoneScheme(url)) {
            if (Intents.isNativeBrowserScheme(url)) {
                String errorMessage = "Unable to load mopub native browser url: " + url;
                try {
                    Intent intent = Intents.intentForNativeBrowserScheme(url);
                    launchIntentForUserClick(this.mContext, intent, errorMessage);
                } catch (UrlParseException e) {
                    MoPubLog.d(errorMessage + ". " + e.getMessage());
                }
            } else if (!Intents.isHttpUrl(url) && Intents.canHandleApplicationUrl(this.mContext, url)) {
                launchApplicationUrl(url);
            } else {
                showMoPubBrowserForUrl(url);
            }
        }
        return true;
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (this.mRedirectUrl != null && url.startsWith(this.mRedirectUrl)) {
            view.stopLoading();
            showMoPubBrowserForUrl(url);
        }
    }

    private boolean isSpecialMoPubScheme(String url) {
        return url.startsWith("mopub://");
    }

    private boolean handleSpecialMoPubScheme(String url) {
        if (!isSpecialMoPubScheme(url)) {
            return false;
        }
        Uri uri = Uri.parse(url);
        String host = uri.getHost();
        if ("finishLoad".equals(host)) {
            this.mHtmlWebViewListener.onLoaded(this.mHtmlWebView);
        } else if (UnityAdsConstants.UNITY_ADS_WEBVIEW_API_CLOSE.equals(host)) {
            this.mHtmlWebViewListener.onCollapsed();
        } else if ("failLoad".equals(host)) {
            this.mHtmlWebViewListener.onFailed(MoPubErrorCode.UNSPECIFIED);
        } else if ("custom".equals(host)) {
            handleCustomIntentFromUri(uri);
        }
        return true;
    }

    private boolean isPhoneScheme(String url) {
        return url.startsWith("tel:") || url.startsWith("voicemail:") || url.startsWith("sms:") || url.startsWith("mailto:") || url.startsWith("geo:") || url.startsWith("google.streetview:");
    }

    private boolean handlePhoneScheme(String url) {
        if (!isPhoneScheme(url)) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        String errorMessage = "Could not handle intent with URI: " + url + ". Is this intent supported on your phone?";
        launchIntentForUserClick(this.mContext, intent, errorMessage);
        return true;
    }

    private boolean launchApplicationUrl(String url) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        return launchIntentForUserClick(this.mContext, intent, "Unable to open intent.");
    }

    private void showMoPubBrowserForUrl(String url) {
        if (url == null || url.equals("")) {
            url = "about:blank";
        }
        MoPubLog.d("Final URI to show in browser: " + url);
        Bundle extras = new Bundle();
        extras.putString(MoPubBrowser.DESTINATION_URL_KEY, url);
        boolean handledByMoPubBrowser = launchIntentForUserClick(this.mContext, Intents.getStartActivityIntent(this.mContext, MoPubBrowser.class, extras), "Could not handle intent action. . Perhaps you forgot to declare com.mopub.common.MoPubBrowser in your Android manifest file.");
        if (!handledByMoPubBrowser) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("about:blank"));
            intent.setFlags(DriveFile.MODE_READ_ONLY);
            launchIntentForUserClick(this.mContext, intent, null);
        }
    }

    private void handleCustomIntentFromUri(Uri uri) {
        try {
            String action = uri.getQueryParameter("fnc");
            String adData = uri.getQueryParameter("data");
            Intent customIntent = new Intent(action);
            customIntent.addFlags(DriveFile.MODE_READ_ONLY);
            customIntent.putExtra(HtmlBannerWebView.EXTRA_AD_CLICK_DATA, adData);
            String errorMessage = "Could not handle custom intent: " + action + ". Is your intent spelled correctly?";
            launchIntentForUserClick(this.mContext, customIntent, errorMessage);
        } catch (UnsupportedOperationException e) {
            MoPubLog.w("Could not handle custom intent with uri: " + uri);
        }
    }

    boolean launchIntentForUserClick(@Nullable Context context, @NonNull Intent intent, @Nullable String errorMessage) {
        Preconditions.NoThrow.checkNotNull(intent);
        if (context == null) {
            MoPubLog.d(errorMessage);
            return false;
        }
        if (!this.mHtmlWebView.wasClicked()) {
            return false;
        }
        try {
            Intents.startActivity(context, intent);
            this.mHtmlWebViewListener.onClicked();
            this.mHtmlWebView.onResetUserClick();
            return true;
        } catch (IntentNotResolvableException e) {
            MoPubLog.d(errorMessage);
            return false;
        }
    }
}
