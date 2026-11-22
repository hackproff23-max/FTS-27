package com.google.android.gms.analytics;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ac {
    public static String a(ab abVar, long j) {
        StringBuilder sb = new StringBuilder();
        sb.append(abVar.fa());
        if (abVar.fc() > 0) {
            long jFc = j - abVar.fc();
            if (jFc >= 0) {
                sb.append("&qt").append("=").append(jFc);
            }
        }
        sb.append("&z").append("=").append(abVar.fb());
        return sb.toString();
    }

    public static String encode(String input) {
        try {
            return URLEncoder.encode(input, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError("URL encoding failed for: " + input);
        }
    }

    static Map<String, String> z(Map<String, String> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().startsWith("&") && entry.getValue() != null) {
                String strSubstring = entry.getKey().substring(1);
                if (!TextUtils.isEmpty(strSubstring)) {
                    map2.put(strSubstring, entry.getValue());
                }
            }
        }
        return map2;
    }
}
