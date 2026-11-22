package com.facebook.ads;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.facebook.ads.a.b;
import com.facebook.ads.a.c;
import com.facebook.ads.a.f;
import com.facebook.ads.a.p;
import com.facebook.ads.a.r;
import com.facebook.ads.a.s;
import com.facebook.ads.a.v;
import com.facebook.ads.a.y;
import com.facebook.ads.a.z;
import com.unity3d.ads.android.properties.UnityAdsConstants;

/* JADX INFO: loaded from: classes.dex */
public class InterstitialAdActivity extends Activity {
    private static final int AD_WEBVIEW_ID = 100001;
    private static final int CLOSE_BUTTON_ID = 100002;
    private static final int CLOSE_BUTTON_SIZE_DP = 60;
    private static final String DATA_MODEL_KEY = "dataModel";
    private static final String LAST_REQUESTED_ORIENTATION_KEY = "lastRequestedOrientation";
    private static final int ORIENTATION_REVERSE_LANDSCAPE = 8;
    private static final int ORIENTATION_REVERSE_PORTRAIT = 9;
    private static final String TAG = InterstitialAdActivity.class.getSimpleName();
    private long actionTime;
    private b.a actionType;
    private z adHandler;
    private WebView adWebView;
    private v closeButton;
    private long createTime;
    private y dataModel;
    private int displayHeight;
    private int displayWidth;
    private String interstitialID;
    private boolean isRestart = false;
    private int lastRequestedOrientation;

    private class a extends WebViewClient {
        private a() {
        }

        @Override // android.webkit.WebViewClient
        public void onLoadResource(WebView webView, String str) {
            InterstitialAdActivity.this.adHandler.f();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (p.a()) {
                sslErrorHandler.proceed();
            } else {
                sslErrorHandler.cancel();
            }
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Uri uri = Uri.parse(str);
            if ("fbad".equals(uri.getScheme()) && UnityAdsConstants.UNITY_ADS_WEBVIEW_API_CLOSE.equals(uri.getAuthority())) {
                InterstitialAdActivity.this.finish();
            } else {
                InterstitialAdActivity.this.sendBroadcastForEvent(InterstitialAd.INTERSTITIAL_CLICKED);
                com.facebook.ads.a.a.a aVarA = com.facebook.ads.a.a.b.a(InterstitialAdActivity.this, uri);
                if (aVarA != null) {
                    try {
                        InterstitialAdActivity.this.actionType = aVarA.a();
                        InterstitialAdActivity.this.actionTime = System.currentTimeMillis();
                        aVarA.a(null);
                    } catch (Exception e) {
                        Log.e(InterstitialAdActivity.TAG, "Error executing action", e);
                    }
                }
            }
            return true;
        }
    }

    private void loadAdFromIntentOrSavedState(Intent intent, Bundle bundle) {
        if (bundle != null && bundle.containsKey(DATA_MODEL_KEY)) {
            this.dataModel = y.a(bundle.getBundle(DATA_MODEL_KEY));
            if (this.dataModel != null) {
                this.adWebView.loadDataWithBaseURL(s.a(), this.dataModel.c(), "text/html", "utf-8", null);
            }
            this.lastRequestedOrientation = bundle.getInt(LAST_REQUESTED_ORIENTATION_KEY, -1);
            this.interstitialID = bundle.getString(InterstitialAd.INTERSTITIAL_UNIQUE_ID_EXTRA);
            this.isRestart = true;
            return;
        }
        this.displayWidth = intent.getIntExtra(InterstitialAd.DISPLAY_WIDTH_INTENT_EXTRA, 0);
        this.displayHeight = intent.getIntExtra(InterstitialAd.DISPLAY_HEIGHT_INTENT_EXTRA, 0);
        this.interstitialID = intent.getStringExtra(InterstitialAd.INTERSTITIAL_UNIQUE_ID_EXTRA);
        this.dataModel = y.b(intent);
        if (this.dataModel != null) {
            this.adHandler.a(this.dataModel);
            this.adWebView.loadDataWithBaseURL(s.a(), this.dataModel.c(), "text/html", "utf-8", null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBroadcastForEvent(String str) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(str + InterstitialAd.SEPARATOR + this.interstitialID));
    }

    private void setScreenOrientation(int i, int i2) {
        boolean z = i2 >= i;
        int rotation = ((WindowManager) getSystemService("window")).getDefaultDisplay().getRotation();
        if (z) {
            switch (rotation) {
                case 1:
                case 2:
                    setRequestedOrientation(9);
                    break;
                default:
                    setRequestedOrientation(1);
                    break;
            }
        }
        switch (rotation) {
            case 2:
            case 3:
                setRequestedOrientation(8);
                break;
            default:
                setRequestedOrientation(0);
                break;
        }
    }

    @Override // android.app.Activity
    public void finish() {
        this.adHandler.c();
        this.adWebView.loadUrl("about:blank");
        this.adWebView.clearCache(true);
        sendBroadcastForEvent(InterstitialAd.INTERSTITIAL_DISMISSED);
        c.a(b.a(this.createTime, b.a.XOUT));
        super.finish();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.createTime = System.currentTimeMillis();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        this.adWebView = new WebView(this);
        this.adWebView.setId(AD_WEBVIEW_ID);
        this.adWebView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        s.a(this.adWebView, new a(), new r());
        relativeLayout.addView(this.adWebView);
        Intent intent = getIntent();
        if (intent.getBooleanExtra(InterstitialAd.USE_NATIVE_CLOSE_BUTTON_EXTRA, false)) {
            this.closeButton = new v(this);
            this.closeButton.setId(CLOSE_BUTTON_ID);
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (displayMetrics.density * 60.0f), (int) (displayMetrics.density * 60.0f));
            layoutParams.addRule(10);
            layoutParams.addRule(11);
            this.closeButton.setLayoutParams(layoutParams);
            this.closeButton.setOnClickListener(new View.OnClickListener() { // from class: com.facebook.ads.InterstitialAdActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    InterstitialAdActivity.this.finish();
                }
            });
            relativeLayout.addView(this.closeButton);
        }
        this.adHandler = new z(this.adWebView, new f.a() { // from class: com.facebook.ads.InterstitialAdActivity.2
            @Override // com.facebook.ads.a.f.a
            public boolean a() {
                return true;
            }

            @Override // com.facebook.ads.a.f.a
            public void b() {
            }

            @Override // com.facebook.ads.a.f.a
            public void c() {
                InterstitialAdActivity.this.sendBroadcastForEvent(InterstitialAd.IMPRESSION_WILL_LOG);
            }

            @Override // com.facebook.ads.a.f.a
            public boolean d() {
                return false;
            }
        }, 1000L, this);
        setContentView(relativeLayout, new RelativeLayout.LayoutParams(-1, -1));
        loadAdFromIntentOrSavedState(intent, bundle);
        sendBroadcastForEvent(InterstitialAd.INTERSTITIAL_DISPLAYED);
        this.adHandler.h();
    }

    @Override // android.app.Activity
    public void onRestart() {
        super.onRestart();
        this.isRestart = true;
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.actionTime <= 0 || this.actionType == null) {
            return;
        }
        c.a(b.a(this.actionTime, this.actionType));
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.dataModel != null) {
            bundle.putBundle(DATA_MODEL_KEY, this.dataModel.i());
        }
        bundle.putInt(LAST_REQUESTED_ORIENTATION_KEY, this.lastRequestedOrientation);
        bundle.putString(InterstitialAd.INTERSTITIAL_UNIQUE_ID_EXTRA, this.interstitialID);
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        if (!this.isRestart) {
            setScreenOrientation(this.displayWidth, this.displayHeight);
        } else if (this.lastRequestedOrientation >= 0) {
            setRequestedOrientation(this.lastRequestedOrientation);
            this.lastRequestedOrientation = -1;
        }
        this.isRestart = false;
    }

    @Override // android.app.Activity
    public void setRequestedOrientation(int i) {
        this.lastRequestedOrientation = i;
        super.setRequestedOrientation(i);
    }
}
