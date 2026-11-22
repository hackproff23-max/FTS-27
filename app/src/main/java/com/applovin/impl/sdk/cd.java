package com.applovin.impl.sdk;

import android.content.Context;
import android.util.TypedValue;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdkUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class cd extends AppLovinSdkUtils {
    private static final char[] a = "0123456789abcdef".toCharArray();
    private static final char[] b = "-'".toCharArray();

    public static double a(long j) {
        return j / 1000.0d;
    }

    public static float a(float f) {
        return 1000.0f * f;
    }

    public static int a(Context context, int i) {
        return (int) TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
    }

    public static AppLovinAdInternal a() {
        return new AppLovinAdInternal.Builder().setHtml("").setSize(AppLovinAdSize.BANNER).setType(AppLovinAdType.REGULAR).setVideoFilename("").setTarget(AppLovinAdInternal.AdTarget.DEFAULT).setCloseStyle(com.applovin.impl.adview.t.WhiteXOnOpaqueBlack).setVideoCloseDelay(0.0f).setPoststitialCloseDelay(0.0f).setCountdownLength(0).setCurrentAdIdNumber(-1L).setClCode("").create();
    }

    public static String a(String str) {
        return (str == null || str.length() <= 4) ? "NOKEY" : str.substring(str.length() - 4);
    }

    public static String a(String str, AppLovinSdkImpl appLovinSdkImpl) {
        return a(str, (Integer) appLovinSdkImpl.a(bb.s), (String) appLovinSdkImpl.a(bb.r));
    }

    private static String a(String str, Integer num, String str2) {
        if (str2 == null) {
            throw new IllegalArgumentException("No algorithm specified");
        }
        if (str == null || str.length() < 1) {
            return "";
        }
        if (str2.length() < 1 || "none".equals(str2)) {
            return str;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(str.getBytes("UTF-8"));
            String strA = a(messageDigest.digest());
            return (strA == null || num.intValue() <= 0) ? strA : strA.substring(0, Math.min(num.intValue(), strA.length()));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Programming error: UTF-8 is not know encoding", e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException("Unknown algorithm \"" + str2 + "\"", e2);
        }
    }

    static String a(Map map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(entry.getKey()).append('=').append(entry.getValue());
        }
        return sb.toString();
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("No data specified");
        }
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            cArr[i * 2] = a[(bArr[i] & 240) >>> 4];
            cArr[(i * 2) + 1] = a[bArr[i] & 15];
        }
        return new String(cArr);
    }

    public static boolean a(bd bdVar, AppLovinSdkImpl appLovinSdkImpl) {
        return a(bdVar, appLovinSdkImpl.getSettingsManager());
    }

    public static boolean a(bd bdVar, be beVar) {
        return ((float) System.currentTimeMillis()) > a((float) ((Long) beVar.a(bdVar)).longValue());
    }

    public static long b(float f) {
        return Math.round(f);
    }

    static String b(String str) {
        if (!c(str)) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public static long c(float f) {
        return b(a(f));
    }

    public static boolean c(String str) {
        return str != null && str.length() > 1;
    }
}
