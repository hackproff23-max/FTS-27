package com.facebook.ads.a;

import android.util.Log;
import android.webkit.JavascriptInterface;

/* JADX INFO: loaded from: classes.dex */
public class r {
    private static final String a = r.class.getSimpleName();

    @JavascriptInterface
    public void alert(String str) {
        Log.e(a, str);
    }

    @JavascriptInterface
    public String getAnalogInfo() {
        return p.a(a.a());
    }
}
