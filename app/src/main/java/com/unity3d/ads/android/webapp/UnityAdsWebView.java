package com.unity3d.ads.android.webapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.data.UnityAdsDevice;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(9)
public class UnityAdsWebView extends WebView {
    private String a;
    private IUnityAdsWebViewListener b;
    private boolean c;
    private Lock d;
    private Condition e;
    private boolean f;
    private UnityAdsWebBridge g;
    private String h;

    static /* synthetic */ boolean c(UnityAdsWebView unityAdsWebView) {
        unityAdsWebView.c = true;
        return true;
    }

    public UnityAdsWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = null;
        this.b = null;
        this.c = false;
        this.d = new ReentrantLock();
        this.e = this.d.newCondition();
        this.f = false;
        this.g = null;
        this.h = "start";
    }

    public UnityAdsWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = null;
        this.b = null;
        this.c = false;
        this.d = new ReentrantLock();
        this.e = this.d.newCondition();
        this.f = false;
        this.g = null;
        this.h = "start";
    }

    public UnityAdsWebView(Context context) {
        super(context);
        this.a = null;
        this.b = null;
        this.c = false;
        this.d = new ReentrantLock();
        this.e = this.d.newCondition();
        this.f = false;
        this.g = null;
        this.h = "start";
    }

    public UnityAdsWebView(Activity activity, IUnityAdsWebViewListener iUnityAdsWebViewListener, UnityAdsWebBridge unityAdsWebBridge) {
        super(activity);
        this.a = null;
        this.b = null;
        this.c = false;
        this.d = new ReentrantLock();
        this.e = this.d.newCondition();
        this.f = false;
        this.g = null;
        this.h = "start";
        UnityAdsDeviceLog.debug("Loading WebView from URL: " + UnityAdsProperties.WEBVIEW_BASE_URL);
        a(UnityAdsProperties.WEBVIEW_BASE_URL, iUnityAdsWebViewListener, unityAdsWebBridge);
    }

    public UnityAdsWebView(Activity activity, String str, IUnityAdsWebViewListener iUnityAdsWebViewListener, UnityAdsWebBridge unityAdsWebBridge) {
        super(activity);
        this.a = null;
        this.b = null;
        this.c = false;
        this.d = new ReentrantLock();
        this.e = this.d.newCondition();
        this.f = false;
        this.g = null;
        this.h = "start";
        a(str, iUnityAdsWebViewListener, unityAdsWebBridge);
    }

    public void clearWebView() {
        this.c = false;
        this.b = null;
        setWebViewClient(null);
        setWebChromeClient(null);
    }

    public boolean isWebAppLoaded() {
        return this.c;
    }

    public boolean isWebAppLoadComplete() {
        return this.f;
    }

    public void waitForWebAppLoadComplete() {
        this.d.lock();
        try {
            this.e.await();
        } catch (InterruptedException e) {
        } finally {
            this.d.unlock();
        }
    }

    public void setWebAppLoadComplete() {
        this.d.lock();
        try {
            this.f = true;
            this.e.signalAll();
        } finally {
            this.d.unlock();
        }
    }

    public String getWebViewCurrentView() {
        return this.h;
    }

    public void setWebViewCurrentView(String str) {
        setWebViewCurrentView(str, null);
    }

    public void setWebViewCurrentView(String str, JSONObject jSONObject) {
        if (isWebAppLoaded()) {
            String string = "{}";
            if (jSONObject != null) {
                string = jSONObject.toString();
            }
            String str2 = String.format("%s%s(\"%s\", %s);", UnityAdsConstants.UNITY_ADS_WEBVIEW_JS_PREFIX, UnityAdsConstants.UNITY_ADS_WEBVIEW_JS_CHANGE_VIEW, str, string);
            this.h = str;
            UnityAdsUtils.runOnUiThread(new i(this, str2, this));
            UnityAdsDeviceLog.debug("Send change view to WebApp: " + str2);
            if (jSONObject != null && UnityAdsProperties.getBaseActivity() != null) {
                String string2 = UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_TEST_KEY;
                try {
                    string2 = jSONObject.getString("action");
                } catch (Exception e) {
                }
                UnityAdsDeviceLog.debug("dataHasApiActionKey=" + jSONObject.has("action"));
                UnityAdsDeviceLog.debug("actionEqualsWebViewApiOpen=" + string2.equals(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_OPEN));
                UnityAdsDeviceLog.debug("isDebuggable=" + UnityAdsUtils.isDebuggable(UnityAdsProperties.getBaseActivity()));
                UnityAdsDeviceLog.debug("runWebViewTests=" + UnityAdsProperties.RUN_WEBVIEW_TESTS);
                UnityAdsDeviceLog.debug("testJavaScriptContents=" + UnityAdsProperties.TEST_JAVASCRIPT);
                if (jSONObject.has("action") && string2 != null && string2.equals(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_OPEN) && UnityAdsUtils.isDebuggable(UnityAdsProperties.getBaseActivity()) && UnityAdsProperties.RUN_WEBVIEW_TESTS.booleanValue() && UnityAdsProperties.TEST_JAVASCRIPT != null) {
                    UnityAdsDeviceLog.debug("Running test-javascript: " + UnityAdsProperties.TEST_JAVASCRIPT);
                    UnityAdsUtils.runOnUiThread(new i(this, UnityAdsProperties.TEST_JAVASCRIPT, this));
                    UnityAdsProperties.RUN_WEBVIEW_TESTS = false;
                }
            }
        }
    }

    public void sendNativeEventToWebApp(String str, JSONObject jSONObject) {
        if (isWebAppLoaded()) {
            String string = "{}";
            if (jSONObject != null) {
                string = jSONObject.toString();
            }
            String str2 = String.format("%s%s(\"%s\", %s);", UnityAdsConstants.UNITY_ADS_WEBVIEW_JS_PREFIX, UnityAdsConstants.UNITY_ADS_WEBVIEW_JS_HANDLE_NATIVE_EVENT, str, string);
            UnityAdsDeviceLog.debug("Send native event to WebApp: " + str2);
            UnityAdsUtils.runOnUiThread(new i(this, str2, this));
        }
    }

    public void initWebApp(JSONObject jSONObject) {
        if (isWebAppLoaded()) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_CAMPAIGNDATA_KEY, jSONObject);
                jSONObject2.put("platform", "android");
                String advertisingTrackingId = UnityAdsDevice.getAdvertisingTrackingId();
                if (advertisingTrackingId != null) {
                    jSONObject2.put("trackingEnabled", UnityAdsDevice.isLimitAdTrackingEnabled() ? 0 : 1);
                    jSONObject2.put("advertisingTrackingId", UnityAdsUtils.Md5(advertisingTrackingId).toLowerCase());
                    jSONObject2.put("rawAdvertisingTrackingId", advertisingTrackingId);
                } else {
                    jSONObject2.put("deviceId", UnityAdsDevice.getAndroidId(true));
                    if (!"unknown".equals(UnityAdsDevice.getAndroidId(false))) {
                        jSONObject2.put("androidId", UnityAdsDevice.getAndroidId(true));
                        jSONObject2.put("rawAndroidId", UnityAdsDevice.getAndroidId(false));
                    }
                    if (!"unknown".equals(UnityAdsDevice.getAndroidSerial())) {
                        jSONObject2.put("serialId", UnityAdsDevice.getAndroidSerial());
                    }
                    jSONObject2.put("macAddress", UnityAdsDevice.getMacAddress());
                }
                jSONObject2.put("sdkVersion", UnityAdsConstants.UNITY_ADS_VERSION);
                jSONObject2.put("gameId", UnityAdsProperties.UNITY_ADS_GAME_ID);
                jSONObject2.put("screenDensity", UnityAdsDevice.getScreenDensity());
                jSONObject2.put("screenSize", UnityAdsDevice.getScreenSize());
                jSONObject2.put("zones", UnityAdsWebData.getZoneManager().getZonesJson());
                jSONObject2.put("softwareVersion", UnityAdsDevice.getSoftwareVersion());
                jSONObject2.put("deviceType", UnityAdsDevice.getDeviceType());
                String str = String.format("%s%s(%s);", UnityAdsConstants.UNITY_ADS_WEBVIEW_JS_PREFIX, UnityAdsConstants.UNITY_ADS_WEBVIEW_JS_INIT, jSONObject2.toString());
                UnityAdsDeviceLog.debug("Initializing WebView with JS call: " + str);
                UnityAdsUtils.runOnUiThread(new i(this, str, this));
            } catch (Exception e) {
                UnityAdsDeviceLog.debug("Error creating webview init params");
            }
        }
    }

    private void a(String str, IUnityAdsWebViewListener iUnityAdsWebViewListener, UnityAdsWebBridge unityAdsWebBridge) {
        byte b = 0;
        this.b = iUnityAdsWebViewListener;
        this.a = str;
        this.g = unityAdsWebBridge;
        getSettings().setJavaScriptEnabled(true);
        if (this.a != null && this.a.indexOf("_raw.html") != -1) {
            getSettings().setCacheMode(2);
            UnityAdsDeviceLog.debug("LOAD_NO_CACHE");
        } else if (Build.VERSION.SDK_INT < 17) {
            getSettings().setCacheMode(0);
        } else {
            getSettings().setCacheMode(-1);
        }
        String string = (getContext() == null || getContext().getCacheDir() == null) ? null : getContext().getCacheDir().toString();
        getSettings().setSupportZoom(false);
        getSettings().setBuiltInZoomControls(false);
        getSettings().setLightTouchEnabled(false);
        getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        getSettings().setSupportMultipleWindows(false);
        getSettings().setAllowFileAccess(false);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        setClickable(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setInitialScale(0);
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        setBackgroundDrawable(null);
        setBackgroundResource(0);
        setWebViewClient(new k(this, b));
        setWebChromeClient(new j(this, (byte) 0));
        if (string != null) {
            getSettings().setAppCacheEnabled(Build.VERSION.SDK_INT > 7);
            getSettings().setDomStorageEnabled(true);
            getSettings().setAppCacheMaxSize(10485760L);
            getSettings().setAppCachePath(string);
        }
        UnityAdsDeviceLog.debug("Adding javascript interface");
        addJavascriptInterface(this.g, "unityadsnativebridge");
        loadUrl(this.a);
        if (Build.VERSION.SDK_INT > 8) {
            setOnLongClickListener(new h(this));
            setLongClickable(false);
        }
    }

    public void setRenderMode(int i) {
        try {
            View.class.getMethod("setLayerType", Integer.TYPE, Paint.class).invoke(this, Integer.valueOf(i), null);
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Could not invoke setLayerType");
        }
    }

    @Override // android.webkit.WebView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
                UnityAdsDeviceLog.entered();
                if (this.b != null) {
                    this.b.onBackButtonClicked(this);
                }
                return true;
            default:
                return false;
        }
    }
}
