package com.flurry.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;

/* JADX INFO: loaded from: classes.dex */
public final class cw {
    public static String a(Context context) {
        if (context == null) {
            return null;
        }
        String strB = b(context);
        if (TextUtils.isEmpty(strB)) {
            return c(context);
        }
        return strB;
    }

    @TargetApi(17)
    static String b(Context context) {
        if (context != null && Build.VERSION.SDK_INT >= 17) {
            return WebSettings.getDefaultUserAgent(context);
        }
        return null;
    }

    static String c(Context context) {
        if (context == null) {
            return null;
        }
        return new WebView(context).getSettings().getUserAgentString();
    }
}
