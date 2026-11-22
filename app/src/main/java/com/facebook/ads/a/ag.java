package com.facebook.ads.a;

import com.flurry.android.Constants;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public class ag {
    public static boolean a(String str) {
        return str == null || str.length() <= 0;
    }

    public static String b(String str) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(str.getBytes("utf-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                sb.append(Integer.toString((b & Constants.UNKNOWN) + 256, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
