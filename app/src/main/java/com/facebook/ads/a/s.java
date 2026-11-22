package com.facebook.ads.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.AdSettings;
import java.lang.reflect.Constructor;

/* JADX INFO: loaded from: classes.dex */
public class s {
    private static String a = null;

    public static String a() {
        String urlPrefix = AdSettings.getUrlPrefix();
        return ag.a(urlPrefix) ? "https://www.facebook.com/" : String.format("https://www.%s.facebook.com", urlPrefix);
    }

    @TargetApi(17)
    private static String a(Context context) {
        return WebSettings.getDefaultUserAgent(context);
    }

    public static String a(Context context, o oVar) {
        if (o.NATIVE == oVar) {
            return System.getProperty("http.agent");
        }
        if (a == null) {
            if (Build.VERSION.SDK_INT >= 17) {
                a = a(context);
            } else {
                try {
                    a = a(context, "android.webkit.WebSettings", "android.webkit.WebView");
                } catch (Exception e) {
                    try {
                        a = a(context, "android.webkit.WebSettingsClassic", "android.webkit.WebViewClassic");
                    } catch (Exception e2) {
                        WebView webView = new WebView(context.getApplicationContext());
                        a = webView.getSettings().getUserAgentString();
                        webView.destroy();
                    }
                }
            }
        }
        return a;
    }

    private static String a(Context context, String str, String str2) throws NoSuchMethodException, ClassNotFoundException {
        Class<?> cls = Class.forName(str);
        Constructor<?> declaredConstructor = cls.getDeclaredConstructor(Context.class, Class.forName(str2));
        declaredConstructor.setAccessible(true);
        try {
            return (String) cls.getMethod("getUserAgentString", new Class[0]).invoke(declaredConstructor.newInstance(context, null), new Object[0]);
        } finally {
            declaredConstructor.setAccessible(false);
        }
    }

    public static void a(WebView webView) {
        if (Build.VERSION.SDK_INT > 11) {
            webView.onPause();
        } else {
            try {
                WebView.class.getMethod("onPause", new Class[0]).invoke(webView, new Object[0]);
            } catch (Exception e) {
            }
        }
    }

    public static void a(WebView webView, WebViewClient webViewClient, r rVar) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setHorizontalScrollbarOverlay(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setVerticalScrollbarOverlay(false);
        webView.addJavascriptInterface(rVar, "AdControl");
        webView.setWebViewClient(webViewClient);
    }
}
