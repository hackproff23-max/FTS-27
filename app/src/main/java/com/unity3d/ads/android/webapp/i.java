package com.unity3d.ads.android.webapp;

import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.unity3d.ads.android.UnityAdsDeviceLog;

/* JADX INFO: compiled from: UnityAdsWebView.java */
/* JADX INFO: loaded from: classes.dex */
final class i implements Runnable {
    final /* synthetic */ UnityAdsWebView a;
    private String b;
    private WebView c;

    public i(UnityAdsWebView unityAdsWebView, String str, WebView webView) {
        this.a = unityAdsWebView;
        this.b = null;
        this.c = null;
        this.b = str;
        this.c = webView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.b != null) {
            try {
                if (Build.VERSION.SDK_INT >= 19) {
                    try {
                        WebView.class.getMethod("evaluateJavascript", String.class, ValueCallback.class).invoke(this.c, this.b, null);
                    } catch (Exception e) {
                        UnityAdsDeviceLog.error("Could not invoke evaluateJavascript");
                    }
                } else {
                    this.a.loadUrl(this.b);
                }
                return;
            } catch (Exception e2) {
                UnityAdsDeviceLog.error("Error while processing JavaScriptString!");
                return;
            }
        }
        UnityAdsDeviceLog.error("Could not process JavaScript, the string is NULL");
    }
}
