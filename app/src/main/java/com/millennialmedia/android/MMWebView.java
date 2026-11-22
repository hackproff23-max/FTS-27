package com.millennialmedia.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Build;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import com.facebook.internal.ServerProtocol;
import com.millennialmedia.android.MMAdView.BannerBounds;
import com.mopub.common.Constants;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class MMWebView extends WebView {
    static final String JS_INTERFACE_NAME = "interface";
    static final String PROPERTY_BANNER_TYPE = "PROPERTY_BANNER_TYPE";
    static final String PROPERTY_EXPANDING = "PROPERTY_EXPANDING";
    static final String PROPERTY_STATE = "PROPERTY_STATE";
    private static final String TAG = "MMWebView";
    private HttpMMHeaders _lastHeaders;
    long creatorAdImplId;
    int currentColor;
    String currentUrl;
    boolean hadFirstRecordingCreation;
    boolean hadFirstSpeechKitCreation;
    volatile boolean isExpanding;
    boolean isSendingSize;
    volatile boolean isUserClosedResize;
    volatile boolean isVisible;
    volatile String mraidState;
    int oldHeight;
    int oldWidth;
    volatile boolean requiresPreAdSizeFix;
    final GestureDetector tapDetector;
    final String userAgent;

    public MMWebView(Context context, long internalId) {
        super(context);
        this.isSendingSize = true;
        this.oldHeight = -50;
        this.oldWidth = -50;
        this.isVisible = false;
        this.hadFirstSpeechKitCreation = false;
        this.hadFirstRecordingCreation = false;
        setWillNotDraw(false);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        setOnTouchListener(new WebTouchListener(this));
        this.mraidState = UnityAdsConstants.UNITY_ADS_TEXTKEY_LOADING;
        this.creatorAdImplId = internalId;
        MMLog.v(TAG, String.format("Assigning WebView internal id: %d", Long.valueOf(this.creatorAdImplId)));
        setId((int) (15063 + this.creatorAdImplId));
        if (HandShake.sharedHandShake(context).hardwareAccelerationEnabled) {
            enableHardwareAcceleration();
        } else {
            disableAllAcceleration();
        }
        setWebChromeClient(new MyWebChromeClient(this));
        WebSettings webSettings = getSettings();
        this.userAgent = webSettings.getUserAgentString();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(-1);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setGeolocationEnabled(true);
        if (Build.VERSION.SDK_INT >= 17) {
            MMLog.i(TAG, "Disabling user gesture requirement for media playback");
            webSettings.setMediaPlaybackRequiresUserGesture(false);
        }
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        this.tapDetector = new GestureDetector(context.getApplicationContext(), new BannerGestureListener(this));
    }

    private static class WebTouchListener implements View.OnTouchListener {
        WeakReference<MMWebView> webRef;

        WebTouchListener(MMWebView webView) {
            this.webRef = new WeakReference<>(webView);
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View v, MotionEvent event) {
            MMWebView webView = this.webRef.get();
            boolean consume = event.getAction() == 2;
            if (webView != null) {
                return consume && webView.canScroll();
            }
            return consume;
        }
    }

    private static class BannerGestureListener extends GestureDetector.SimpleOnGestureListener {
        WeakReference<MMWebView> webRef;

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent e) {
            MMAdView adView;
            MMWebView webView = this.webRef.get();
            if (webView != null && (adView = webView.getMMAdView()) != null) {
                MMSDK.Event.adSingleTap(adView.adImpl);
                return false;
            }
            return false;
        }

        public BannerGestureListener(MMWebView webView) {
            this.webRef = new WeakReference<>(webView);
        }
    }

    @Override // android.webkit.WebView, android.widget.AbsoluteLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int measuredHeight = getMeasuredHeight();
        if (measuredHeight == 0) {
            measuredHeight = heightSize;
        }
        if (this.requiresPreAdSizeFix) {
            setMeasuredDimension(1, 1);
        } else {
            setMeasuredDimension(widthSize, measuredHeight);
        }
    }

    @Override // android.webkit.WebView, android.view.View
    public void setBackgroundColor(int color) {
        this.currentColor = color;
        if (color == 0) {
            enableSoftwareAcceleration();
        }
        super.setBackgroundColor(color);
    }

    void disableAllAcceleration() {
        if (Build.VERSION.SDK_INT >= 11) {
            MMLog.i(TAG, "Disabling acceleration");
            setLayerType(0, null);
        }
    }

    private boolean needsSamsungJBOpenGlFixNoAcceleration() {
        int version = Integer.parseInt(Build.VERSION.SDK);
        return "Nexus S".equals(Build.MODEL) && "samsung".equals(Build.MANUFACTURER) && (version == 16 || version == 17);
    }

    void enableSoftwareAcceleration() {
        if (!needsSamsungJBOpenGlFixNoAcceleration()) {
            try {
                Method method = WebView.class.getMethod("setLayerType", Integer.TYPE, Paint.class);
                method.invoke(this, 1, null);
                MMLog.d(TAG, "Enable softwareAcceleration");
            } catch (Exception e) {
            }
        }
    }

    void enableHardwareAcceleration() {
        if (!needsSamsungJBOpenGlFixNoAcceleration()) {
            try {
                Method method = WebView.class.getMethod("setLayerType", Integer.TYPE, Paint.class);
                method.invoke(this, 2, null);
                MMLog.d(TAG, "Enabled hardwareAcceleration");
            } catch (Exception e) {
            }
        }
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == 0) {
            requestFocus();
        }
        if (this.tapDetector != null) {
            this.tapDetector.onTouchEvent(e);
        }
        if (e.getAction() == 1) {
            MMLog.v(TAG, String.format("Ad clicked: action=%d x=%f y=%f", Integer.valueOf(e.getAction()), Float.valueOf(e.getX()), Float.valueOf(e.getY())));
        }
        return super.onTouchEvent(e);
    }

    void setWebViewContent(String webContent, String adUrl, final MMAdImpl adImpl) {
        String content;
        if (webContent != null && adUrl != null && adImpl != null) {
            unresizeToDefault(adImpl);
            resetSpeechKit();
            final String baseUrl = adUrl.substring(0, adUrl.lastIndexOf("/") + 1);
            if (MMSDK.logLevel >= 5) {
                MMLog.v(TAG, String.format("Received ad with base url %s.", baseUrl));
                MMLog.v(TAG, webContent);
            }
            if (adImpl.isTransitionAnimated()) {
                animateTransition(adImpl);
            }
            if (adImpl.ignoreDensityScaling) {
                content = "<head><meta name=\"viewport\" content=\"target-densitydpi=device-dpi\" /></head>" + webContent;
            } else {
                content = webContent;
            }
            if (MRaid.hasMraidLocally(adImpl.getContext())) {
                content = MRaid.injectMraidJs(adImpl.getContext(), content);
            } else {
                MMLog.e(TAG, "MMJS is not downloaded");
            }
            final String finalContent = content;
            MMSDK.runOnUiThread(new Runnable() { // from class: com.millennialmedia.android.MMWebView.1
                @Override // java.lang.Runnable
                public void run() {
                    if (HandShake.sharedHandShake(MMWebView.this.getContext()).hardwareAccelerationEnabled) {
                        MMWebView.this.enableHardwareAcceleration();
                    } else if (MMWebView.this.currentColor == 0) {
                        MMWebView.this.enableSoftwareAcceleration();
                    } else {
                        MMWebView.this.disableAllAcceleration();
                    }
                    MMAd ad = adImpl.getCallingAd();
                    if (ad != null && (ad instanceof MMLayout)) {
                        ((MMLayout) ad).removeVideo();
                    }
                    MMWebView.this.isSendingSize = true;
                    MMWebView.this.loadDataWithBaseURL(baseUrl, finalContent, "text/html", "UTF-8", null);
                }
            });
        }
    }

    @Override // android.webkit.WebView
    public void loadDataWithBaseURL(String baseUrl, String data, String mimeType, String encoding, String historyUrl) {
        this.currentUrl = baseUrl;
        try {
            super.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
        } catch (Exception e) {
            MMLog.e(TAG, "Error hit when calling through to loadDataWithBaseUrl", e);
        }
    }

    void setWebViewContent(String webContent, String baseUrl, Context context) {
        if (webContent != null && baseUrl != null) {
            final String finalBaseUrl = baseUrl.substring(0, baseUrl.lastIndexOf("/") + 1);
            resetSpeechKit();
            String content = webContent;
            if (MRaid.hasMraidLocally(context)) {
                content = MRaid.injectMraidJs(context, content);
            } else {
                MMLog.e(TAG, "MMJS is not downloaded");
            }
            final String finalContent = content;
            if (MMSDK.logLevel >= 5) {
                MMLog.v(TAG, String.format("Received ad with base url %s.", baseUrl));
                MMLog.v(TAG, webContent);
            }
            MMSDK.runOnUiThread(new Runnable() { // from class: com.millennialmedia.android.MMWebView.2
                @Override // java.lang.Runnable
                public void run() {
                    if (HandShake.sharedHandShake(MMWebView.this.getContext()).hardwareAccelerationEnabled) {
                        MMWebView.this.enableHardwareAcceleration();
                    } else if (MMWebView.this.currentColor == 0) {
                        MMWebView.this.enableSoftwareAcceleration();
                    } else {
                        MMWebView.this.disableAllAcceleration();
                    }
                    MMWebView.this.isSendingSize = true;
                    MMWebView.this.loadDataWithBaseURL(finalBaseUrl, finalContent, "text/html", "UTF-8", null);
                }
            });
        }
    }

    void resetSpeechKit() {
        BridgeMMSpeechkit.releaseSpeechKit();
        this.hadFirstSpeechKitCreation = false;
        this.hadFirstRecordingCreation = false;
    }

    void animateTransition(final MMAdImpl adImpl) {
        FutureTask<Void> future = new FutureTask<>(new Callable<Void>() { // from class: com.millennialmedia.android.MMWebView.3
            @Override // java.util.concurrent.Callable
            public Void call() {
                try {
                    MMWebView.this.buildDrawingCache();
                    Bitmap cache = MMWebView.this.getDrawingCache();
                    if (cache != null) {
                        Bitmap bitmap = Bitmap.createBitmap(cache);
                        adImpl.prepareTransition(bitmap);
                    }
                    MMWebView.this.destroyDrawingCache();
                    return null;
                } catch (Exception e) {
                    MMLog.e(MMWebView.TAG, "Animation exception: ", e);
                    return null;
                }
            }
        });
        MMSDK.runOnUiThread(future);
        try {
            future.get();
        } catch (InterruptedException e) {
        } catch (ExecutionException e2) {
        }
    }

    String getUserAgent() {
        return this.userAgent;
    }

    void updateArgumentsWithSettings(Map<String, String> arguments) {
        arguments.put(PROPERTY_BANNER_TYPE, isParentBannerAd() ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
        arguments.put(PROPERTY_STATE, this.mraidState);
        arguments.put(PROPERTY_EXPANDING, String.valueOf(this.creatorAdImplId));
    }

    boolean isParentBannerAd() {
        if (getParent() == null) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) getParent();
        return viewGroup instanceof MMAdView;
    }

    synchronized void setMraidResize(final DTOResizeParameters resizeParams) {
        if (MMSDK.hasSetTranslationMethod()) {
            final MMAdView adView = getMMAdView();
            this.isUserClosedResize = false;
            MMLog.d(TAG, "New DTOResizeParameters = " + resizeParams);
            if (adView != null) {
                MMSDK.runOnUiThread(new Runnable() { // from class: com.millennialmedia.android.MMWebView.4
                    @Override // java.lang.Runnable
                    public void run() {
                        synchronized (MMWebView.this) {
                            MMWebView.this.isSendingSize = true;
                            adView.handleMraidResize(resizeParams);
                            handleMraidResize(resizeParams);
                            MMWebView.this.loadUrl("javascript:MMJS.sdk.setState('resized');");
                            MMWebView.this.mraidState = "resized";
                        }
                    }

                    private void handleMraidResize(DTOResizeParameters resizeParams2) {
                        MMAdView mMAdView = adView;
                        mMAdView.getClass();
                        MMAdView.BannerBounds bounds = mMAdView.new BannerBounds(resizeParams2);
                        setUnresizeParameters();
                        ViewGroup.LayoutParams layoutParams = MMWebView.this.getLayoutParams();
                        bounds.modifyLayoutParams(layoutParams);
                    }

                    private void setUnresizeParameters() {
                        if (MMWebView.this.hasDefaultResizeParams()) {
                            ViewGroup.LayoutParams oldParams = MMWebView.this.getLayoutParams();
                            MMWebView.this.oldWidth = oldParams.width;
                            MMWebView.this.oldHeight = oldParams.height;
                            if (MMWebView.this.oldWidth <= 0) {
                                MMWebView.this.oldWidth = MMWebView.this.getWidth();
                            }
                            if (MMWebView.this.oldHeight <= 0) {
                                MMWebView.this.oldHeight = MMWebView.this.getHeight();
                            }
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasDefaultResizeParams() {
        return this.oldWidth == -50 && this.oldHeight == -50;
    }

    synchronized void unresizeToDefault(MMAdImpl adImpl) {
        if (MMSDK.hasSetTranslationMethod() && isMraidResized() && adImpl != null) {
            MMAd ad = adImpl.getCallingAd();
            if (ad instanceof MMAdView) {
                final MMAdView adView = (MMAdView) ad;
                this.isUserClosedResize = true;
                MMSDK.runOnUiThread(new Runnable() { // from class: com.millennialmedia.android.MMWebView.5
                    @Override // java.lang.Runnable
                    public void run() {
                        synchronized (MMWebView.this) {
                            adView.handleUnresize();
                            handleUnresize();
                            MMWebView.this.setMraidDefault();
                            MMWebView.this.isSendingSize = true;
                            MMWebView.this.invalidate();
                        }
                    }

                    void handleUnresize() {
                        if (MMSDK.hasSetTranslationMethod() && !MMWebView.this.hasDefaultResizeParams()) {
                            ViewGroup.LayoutParams params = MMWebView.this.getLayoutParams();
                            params.width = MMWebView.this.oldWidth;
                            params.height = MMWebView.this.oldHeight;
                            MMWebView.this.oldWidth = -50;
                            MMWebView.this.oldHeight = -50;
                            MMWebView.this.requestLayout();
                        }
                    }
                });
            }
        }
    }

    boolean canScroll() {
        return getParent() instanceof MMAdView;
    }

    MMAdView getMMAdView() {
        if (getParent() instanceof MMAdView) {
            return (MMAdView) getParent();
        }
        return null;
    }

    MMLayout getMMLayout() {
        if (getParent() instanceof MMLayout) {
            return (MMLayout) getParent();
        }
        return null;
    }

    synchronized Activity getActivity() {
        ViewParent parent;
        Context context;
        parent = getParent();
        return (parent == null || !(parent instanceof ViewGroup) || (context = ((ViewGroup) parent).getContext()) == null || !(context instanceof MMActivity)) ? null : (MMActivity) context;
    }

    @Override // android.webkit.WebView
    public void loadUrl(final String url) {
        if (!TextUtils.isEmpty(url)) {
            if (url.startsWith(Constants.HTTP)) {
                this.currentUrl = url;
            }
            MMLog.v(TAG, "loadUrl @@" + url);
            if (MMSDK.isUiThread()) {
                try {
                    super.loadUrl(url);
                } catch (Exception e) {
                }
            } else {
                MMSDK.runOnUiThread(new Runnable() { // from class: com.millennialmedia.android.MMWebView.6
                    @Override // java.lang.Runnable
                    public void run() {
                        MMWebView.this.loadUrl(url);
                    }
                });
            }
        }
    }

    synchronized AdViewOverlayView getAdViewOverlayView() {
        ViewParent parent;
        parent = getParent();
        return (parent == null || !(parent instanceof AdViewOverlayView)) ? null : (AdViewOverlayView) parent;
    }

    synchronized MMAdView getBanner() {
        ViewParent parent;
        parent = getParent();
        return (parent == null || !(parent instanceof MMAdView)) ? null : (MMAdView) parent;
    }

    public void onPauseWebView() {
        if (Build.VERSION.SDK_INT >= 11) {
            try {
                Method m = WebView.class.getMethod("onPause", new Class[0]);
                m.invoke(this, new Object[0]);
            } catch (Exception e) {
                MMLog.w(TAG, "No onPause()");
            }
        }
    }

    public void onResumeWebView() {
        Activity adActivity;
        if (!isParentBannerAd() && Build.VERSION.SDK_INT >= 19 && (adActivity = getActivity()) != null) {
            adActivity.setRequestedOrientation(14);
        }
        if (Build.VERSION.SDK_INT >= 11) {
            try {
                Method m = WebView.class.getMethod("onResume", new Class[0]);
                m.invoke(this, new Object[0]);
            } catch (Exception e) {
                MMLog.w(TAG, "No onResume()");
            }
        }
    }

    private static class MyWebChromeClient extends WebChromeClient {
        private static final String KEY_USE_GEO = "mm_use_geo_location";
        WeakReference<MMWebView> webRef;

        MyWebChromeClient(MMWebView webView) {
            this.webRef = new WeakReference<>(webView);
        }

        @Override // android.webkit.WebChromeClient
        public void onGeolocationPermissionsShowPrompt(final String origin, final GeolocationPermissions.Callback callback) {
            Activity activity;
            if (isFirstGeoRequest()) {
                if (retrieveUseGeo()) {
                    callback.invoke(origin, true, true);
                    return;
                }
                MMWebView webView = this.webRef.get();
                if (webView != null && (activity = webView.getActivity()) != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    String appName = getApplicationName(activity);
                    builder.setTitle(appName);
                    builder.setMessage("Would like to use your Current Location.").setPositiveButton("Allow", new DialogInterface.OnClickListener() { // from class: com.millennialmedia.android.MMWebView.MyWebChromeClient.2
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialog, int id) {
                            MyWebChromeClient.this.saveUseGeo(true);
                            callback.invoke(origin, true, true);
                        }
                    }).setNegativeButton("Don't Allow", new DialogInterface.OnClickListener() { // from class: com.millennialmedia.android.MMWebView.MyWebChromeClient.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialog, int id) {
                            MyWebChromeClient.this.saveUseGeo(false);
                            callback.invoke(origin, false, false);
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    return;
                }
                return;
            }
            callback.invoke(origin, false, false);
        }

        private boolean isFirstGeoRequest() {
            MMWebView webView = this.webRef.get();
            if (webView == null) {
                return false;
            }
            SharedPreferences settings = webView.getContext().getSharedPreferences("MillennialMediaSettings", 0);
            return !settings.contains(KEY_USE_GEO);
        }

        private boolean retrieveUseGeo() {
            MMWebView webView = this.webRef.get();
            if (webView == null) {
                return false;
            }
            SharedPreferences settings = webView.getContext().getSharedPreferences("MillennialMediaSettings", 0);
            return settings.getBoolean(KEY_USE_GEO, false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void saveUseGeo(boolean allow) {
            MMWebView webView = this.webRef.get();
            if (webView != null) {
                SharedPreferences settings = webView.getContext().getSharedPreferences("MillennialMediaSettings", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean(KEY_USE_GEO, allow);
                editor.commit();
            }
        }

        private String getApplicationName(Context context) {
            ApplicationInfo ai;
            PackageManager pm = context.getApplicationContext().getPackageManager();
            try {
                ai = pm.getApplicationInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                ai = null;
            }
            return (String) (ai != null ? pm.getApplicationLabel(ai) : "This app");
        }

        @Override // android.webkit.WebChromeClient
        public void onConsoleMessage(String message, int lineNumber, String sourceID) {
            super.onConsoleMessage(message, lineNumber, sourceID);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            MMWebView webView = this.webRef.get();
            if (webView != null) {
                if (webView.getContext() != webView.getContext().getApplicationContext()) {
                    return super.onJsAlert(view, url, message, result);
                }
                Toast.makeText(webView.getContext(), message, 0).show();
            }
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
            MMWebView webView = this.webRef.get();
            if (webView != null) {
                if (webView.getContext() != webView.getContext().getApplicationContext()) {
                    return super.onJsBeforeUnload(view, url, message, result);
                }
                Toast.makeText(webView.getContext(), message, 0).show();
            }
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            MMWebView webView = this.webRef.get();
            if (webView != null) {
                if (webView.getContext() != webView.getContext().getApplicationContext()) {
                    return super.onJsConfirm(view, url, message, result);
                }
                Toast.makeText(webView.getContext(), message, 0).show();
            }
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            MMWebView webView = this.webRef.get();
            if (webView != null) {
                if (webView.getContext() != webView.getContext().getApplicationContext()) {
                    return super.onJsPrompt(view, url, message, defaultValue, result);
                }
                Toast.makeText(webView.getContext(), message, 0).show();
            }
            return true;
        }
    }

    void setMraidPlacementTypeInterstitial() {
        loadUrl("javascript:MMJS.sdk.setPlacementType('interstitial');");
    }

    void setMraidPlacementTypeInline() {
        loadUrl("javascript:MMJS.sdk.setPlacementType('inline');");
    }

    void setMraidDefault() {
        loadUrl("javascript:MMJS.sdk.setState('default')");
        this.mraidState = UnityAdsConstants.UNITY_ADS_ZONE_DEFAULT_KEY;
        this.isSendingSize = true;
    }

    void setMraidHidden() {
        loadUrl("javascript:MMJS.sdk.setState('hidden')");
        this.mraidState = UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_VIDEOABORT_HIDDEN;
    }

    void setMraidViewableHidden() {
        loadUrl("javascript:MMJS.sdk.setViewable(false)");
        this.isVisible = false;
    }

    void setMraidViewableVisible() {
        loadUrl("javascript:MMJS.sdk.setViewable(true)");
        this.isVisible = true;
    }

    void setmicrophoneAudioLevelChange(double level) {
        loadUrl("javascript:MMJS.sdk.microphoneAudioLevelChange(" + level + ")");
    }

    void setmicrophoneStateChange(String state) {
        loadUrl("javascript:MMJS.sdk.microphoneStateChange('" + state + "')");
    }

    void setMraidExpanded() {
        loadUrl("javascript:MMJS.sdk.setState('expanded');");
        this.mraidState = "expanded";
        this.hadFirstSpeechKitCreation = false;
        this.hadFirstRecordingCreation = false;
        this.isSendingSize = true;
    }

    void setMraidReady() {
        loadUrl("javascript:MMJS.sdk.ready();");
    }

    void setAdProperties() {
        JSONObject adProperties = new AdProperties(getContext()).getAdProperties(this);
        loadUrl("javascript:MMJS.sdk.setAdProperties(" + adProperties + ");");
    }

    void setAdSize() {
        JSONObject adSize = Utils.getViewDimensions(this);
        loadUrl("javascript:MMJS.sdk.setAdSize(" + adSize + ");");
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (getContext().getResources().getDisplayMetrics() != null && this.isSendingSize) {
            setAdSize();
            if (getHeight() != 1 || getWidth() != 1) {
                MMSDK.runOnUiThreadDelayed(new Runnable() { // from class: com.millennialmedia.android.MMWebView.7
                    @Override // java.lang.Runnable
                    public void run() {
                        MMWebView.this.isSendingSize = false;
                    }
                }, 800L);
            }
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override // android.view.View
    public String toString() {
        return "MMWebView originally from(" + this.creatorAdImplId + ") MRaidState(" + this.mraidState + ")." + super.toString();
    }

    boolean isCurrentParent(long internalId) {
        ViewParent parent = getParent();
        if (parent == null) {
            return false;
        }
        MMLog.w(TAG, "Id check for parent: " + internalId + " versus " + ((MMLayout) parent).adImpl.internalId);
        return internalId == ((MMLayout) parent).adImpl.internalId;
    }

    void removeFromParent() {
        ViewParent parent = getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this);
        }
    }

    boolean isMraidResized() {
        return "resized".equals(this.mraidState);
    }

    boolean isOriginalUrl(String url) {
        return (!TextUtils.isEmpty(this.currentUrl) && url.equals(new StringBuilder().append(this.currentUrl).append("?").toString())) || url.equals(new StringBuilder().append(this.currentUrl).append("#").toString());
    }

    void setLastHeaders(HttpMMHeaders lastHeaders) {
        this._lastHeaders = lastHeaders;
    }

    HttpMMHeaders getLastHeaders() {
        return this._lastHeaders;
    }

    String getAdId() {
        return (this._lastHeaders == null || TextUtils.isEmpty(this._lastHeaders.acid)) ? "DEFAULT_AD_ID" : this._lastHeaders.acid;
    }

    boolean allowSpeechCreationCommands() {
        if (this.hadFirstSpeechKitCreation) {
            return allowRecordingCommands();
        }
        this.hadFirstSpeechKitCreation = true;
        return isInterstitial() && this.isVisible;
    }

    boolean allowMicrophoneCreationCommands() {
        if (this.hadFirstRecordingCreation) {
            return allowRecordingCommands();
        }
        this.hadFirstRecordingCreation = true;
        return isInterstitial() && this.isVisible;
    }

    boolean allowRecordingCommands() {
        return hasWindowFocus() && isInterstitial();
    }

    private boolean isInterstitial() {
        return getBanner() == null;
    }

    void enableSendingSize() {
        this.isSendingSize = true;
    }
}
