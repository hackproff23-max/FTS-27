package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class ay {
    private static String arH;
    static Map<String, String> arI = new HashMap();

    ay() {
    }

    static void cH(String str) {
        synchronized (ay.class) {
            arH = str;
        }
    }

    static void d(Context context, String str) {
        cz.a(context, "gtm_install_referrer", "referrer", str);
        f(context, str);
    }

    static String e(Context context, String str) {
        if (arH == null) {
            synchronized (ay.class) {
                if (arH == null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_install_referrer", 0);
                    if (sharedPreferences != null) {
                        arH = sharedPreferences.getString("referrer", "");
                    } else {
                        arH = "";
                    }
                }
            }
        }
        return w(arH, str);
    }

    static String f(Context context, String str, String str2) {
        String string = arI.get(str);
        if (string == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_click_referrers", 0);
            string = sharedPreferences != null ? sharedPreferences.getString(str, "") : "";
            arI.put(str, string);
        }
        return w(string, str2);
    }

    static void f(Context context, String str) {
        String strW = w(str, "conv");
        if (strW == null || strW.length() <= 0) {
            return;
        }
        arI.put(strW, str);
        cz.a(context, "gtm_click_referrers", strW, str);
    }

    static String w(String str, String str2) {
        if (str2 != null) {
            return Uri.parse("http://hostname/?" + str).getQueryParameter(str2);
        }
        if (str.length() > 0) {
            return str;
        }
        return null;
    }
}
