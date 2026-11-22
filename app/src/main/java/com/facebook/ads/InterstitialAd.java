package com.facebook.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.facebook.ads.a.j;
import com.facebook.ads.a.k;
import com.facebook.ads.a.l;
import com.facebook.ads.a.n;
import com.facebook.ads.a.o;
import com.facebook.ads.a.s;
import com.facebook.ads.a.y;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class InterstitialAd implements Ad {
    public static final String AD_HEIGHT_INTENT_EXTRA = "adHeight";
    public static final String AD_WIDTH_INTENT_EXTRA = "adWidth";
    public static final String DISPLAY_HEIGHT_INTENT_EXTRA = "displayHeight";
    public static final String DISPLAY_ROTATION_INTENT_EXTRA = "displayRotation";
    public static final String DISPLAY_WIDTH_INTENT_EXTRA = "displayWidth";
    public static final String IMPRESSION_WILL_LOG = "com.facebook.ads.interstitial.impression.logged";
    public static final String INTERSTITIAL_CLICKED = "com.facebook.ads.interstitial.clicked";
    public static final String INTERSTITIAL_DISMISSED = "com.facebook.ads.interstitial.dismissed";
    public static final String INTERSTITIAL_DISPLAYED = "com.facebook.ads.interstitial.displayed";
    public static final String INTERSTITIAL_UNIQUE_ID_EXTRA = "adInterstitialUniqueId";
    public static final String IS_TABLET_INTENT_EXTRA = "isTablet";
    public static final String SEPARATOR = ":";
    public static final String USE_NATIVE_CLOSE_BUTTON_EXTRA = "useNativeCloseButton";
    private int adHeight;
    private InterstitialAdListener adListener;
    private k adRequestController;
    private l adResponse;
    private WebView adWebView;
    private int adWidth;
    private final Context context;
    private ImpressionListener impListener;
    private boolean isTablet;
    private boolean useNativeCloseButton;
    private final String uniqueId = UUID.randomUUID().toString();
    private boolean adLoaded = false;
    private final a broadcastReceiver = new a();

    private class a extends BroadcastReceiver {
        private a() {
        }

        public void a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.facebook.ads.interstitial.displayed:" + InterstitialAd.this.uniqueId);
            intentFilter.addAction("com.facebook.ads.interstitial.dismissed:" + InterstitialAd.this.uniqueId);
            intentFilter.addAction("com.facebook.ads.interstitial.clicked:" + InterstitialAd.this.uniqueId);
            intentFilter.addAction("com.facebook.ads.interstitial.impression.logged:" + InterstitialAd.this.uniqueId);
            LocalBroadcastManager.getInstance(InterstitialAd.this.context).registerReceiver(this, intentFilter);
        }

        public void b() {
            try {
                LocalBroadcastManager.getInstance(InterstitialAd.this.context).unregisterReceiver(this);
            } catch (Exception e) {
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str = intent.getAction().split(InterstitialAd.SEPARATOR)[0];
            if (InterstitialAd.this.adListener != null || str.equals(InterstitialAd.IMPRESSION_WILL_LOG)) {
                if (InterstitialAd.INTERSTITIAL_CLICKED.equals(str)) {
                    InterstitialAd.this.adListener.onAdClicked(InterstitialAd.this);
                    return;
                }
                if (InterstitialAd.INTERSTITIAL_DISMISSED.equals(str)) {
                    InterstitialAd.this.adListener.onInterstitialDismissed(InterstitialAd.this);
                    return;
                }
                if (InterstitialAd.INTERSTITIAL_DISPLAYED.equals(str)) {
                    InterstitialAd.this.adListener.onInterstitialDisplayed(InterstitialAd.this);
                } else {
                    if (!InterstitialAd.IMPRESSION_WILL_LOG.equals(str) || InterstitialAd.this.impListener == null) {
                        return;
                    }
                    InterstitialAd.this.impListener.onLoggingImpression(InterstitialAd.this);
                }
            }
        }
    }

    public InterstitialAd(Context context, String str) {
        this.context = context;
        this.adRequestController = new k(this.context, str, AdSize.INTERSTITIAL, n.WEBVIEW_INTERSTITIAL_UNKNOWN, false, o.HTML, 1, new j.b() { // from class: com.facebook.ads.InterstitialAd.1
            @Override // com.facebook.ads.a.j.b
            public void a(AdError adError) {
                InterstitialAd.this.adLoaded = false;
                if (InterstitialAd.this.adListener != null) {
                    InterstitialAd.this.adListener.onError(InterstitialAd.this, adError);
                }
            }

            @Override // com.facebook.ads.a.j.b
            public void a(l lVar) {
                InterstitialAd.this.adResponse = lVar;
                if (lVar.d() == null || !(lVar.d() instanceof y)) {
                    if (lVar.d() == null) {
                        InterstitialAd.this.adLoaded = false;
                        if (InterstitialAd.this.adListener != null) {
                            InterstitialAd.this.adListener.onError(InterstitialAd.this, lVar.e() != null ? lVar.e() : AdError.INTERNAL_ERROR);
                            return;
                        }
                        return;
                    }
                    InterstitialAd.this.adLoaded = false;
                    if (InterstitialAd.this.adListener != null) {
                        InterstitialAd.this.adListener.onError(InterstitialAd.this, AdError.INTERNAL_ERROR);
                        return;
                    }
                    return;
                }
                InterstitialAd.this.adLoaded = true;
                y yVar = (y) lVar.d();
                Map mapH = yVar.h();
                if (mapH.containsKey("is_tablet")) {
                    InterstitialAd.this.isTablet = Boolean.parseBoolean((String) mapH.get("is_tablet"));
                }
                if (mapH.containsKey("ad_height")) {
                    InterstitialAd.this.adHeight = Integer.parseInt((String) mapH.get("ad_height"));
                }
                if (mapH.containsKey("ad_width")) {
                    InterstitialAd.this.adWidth = Integer.parseInt((String) mapH.get("ad_width"));
                }
                if (mapH.containsKey("native_close")) {
                    InterstitialAd.this.useNativeCloseButton = Boolean.valueOf((String) mapH.get("native_close")).booleanValue();
                }
                if (yVar.g()) {
                    InterstitialAd.this.preloadMarkup(yVar);
                } else if (InterstitialAd.this.adListener != null) {
                    InterstitialAd.this.adListener.onAdLoaded(InterstitialAd.this);
                }
            }
        });
        this.broadcastReceiver.a();
    }

    private void ensureAdRequestController() {
        if (this.adRequestController == null) {
            throw new RuntimeException("No request controller available, has the InterstitialAd been destroyed?");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void preloadMarkup(y yVar) {
        if (yVar == null) {
            return;
        }
        this.adWebView = new WebView(this.context);
        this.adWebView.setWebChromeClient(new WebChromeClient() { // from class: com.facebook.ads.InterstitialAd.2
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                if (i != 100 || InterstitialAd.this.adListener == null) {
                    return;
                }
                InterstitialAd.this.adListener.onAdLoaded(InterstitialAd.this);
            }
        });
        this.adWebView.loadDataWithBaseURL(s.a(), yVar.c(), "text/html", "utf-8", null);
    }

    @Override // com.facebook.ads.Ad
    public void destroy() {
        if (this.adRequestController != null) {
            this.adRequestController.c();
            this.adRequestController = null;
            this.broadcastReceiver.b();
        }
        if (this.adWebView != null) {
            s.a(this.adWebView);
            this.adWebView.destroy();
            this.adWebView = null;
        }
    }

    public boolean isAdLoaded() {
        return this.adLoaded;
    }

    @Override // com.facebook.ads.Ad
    public void loadAd() {
        ensureAdRequestController();
        this.adLoaded = false;
        this.adRequestController.b();
    }

    public void setAdListener(InterstitialAdListener interstitialAdListener) {
        this.adListener = interstitialAdListener;
    }

    public void setImpressionListener(ImpressionListener impressionListener) {
        this.impListener = impressionListener;
    }

    public boolean show() {
        if (!this.adLoaded) {
            if (this.adListener != null) {
                this.adListener.onError(this, AdError.INTERNAL_ERROR);
            }
            return false;
        }
        Intent intent = new Intent(this.context, (Class<?>) InterstitialAdActivity.class);
        ((y) this.adResponse.d()).a(intent);
        this.adLoaded = false;
        Display defaultDisplay = ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        intent.putExtra(DISPLAY_ROTATION_INTENT_EXTRA, defaultDisplay.getRotation());
        intent.putExtra(DISPLAY_WIDTH_INTENT_EXTRA, displayMetrics.widthPixels);
        intent.putExtra(DISPLAY_HEIGHT_INTENT_EXTRA, displayMetrics.heightPixels);
        intent.putExtra(IS_TABLET_INTENT_EXTRA, this.isTablet);
        intent.putExtra("adHeight", this.adHeight);
        intent.putExtra("adWidth", this.adWidth);
        intent.putExtra(INTERSTITIAL_UNIQUE_ID_EXTRA, this.uniqueId);
        intent.putExtra(USE_NATIVE_CLOSE_BUTTON_EXTRA, this.useNativeCloseButton);
        this.context.startActivity(intent);
        return true;
    }
}
