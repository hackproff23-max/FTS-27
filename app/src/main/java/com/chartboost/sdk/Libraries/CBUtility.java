package com.chartboost.sdk.Libraries;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.Signature;
import android.os.Handler;
import android.os.Looper;
import android.view.Display;
import android.view.WindowManager;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.security.auth.x500.X500Principal;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

/* JADX INFO: loaded from: classes.dex */
public final class CBUtility {
    private static Boolean a = null;
    private static final X500Principal b = new X500Principal("CN=Android Debug,O=Android,C=US");
    private static Handler c;

    private CBUtility() {
    }

    public static SharedPreferences a() {
        if (com.chartboost.sdk.b.l() == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before modifying or accessing preferences.");
        }
        return com.chartboost.sdk.b.l().getSharedPreferences("cbPrefs", 0);
    }

    public static boolean b() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static boolean a(Context context) {
        boolean zEquals;
        if (a == null) {
            try {
                Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
                int i = 0;
                boolean z = false;
                while (true) {
                    try {
                        if (i >= signatureArr.length) {
                            zEquals = z;
                            break;
                        }
                        zEquals = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[i].toByteArray()))).getSubjectX500Principal().equals(b);
                        if (zEquals) {
                            break;
                        }
                        i++;
                        z = zEquals;
                    } catch (Exception e) {
                        zEquals = z;
                    }
                }
            } catch (Exception e2) {
                zEquals = false;
            }
            a = Boolean.valueOf(zEquals | ((context.getApplicationInfo().flags & 2) != 0));
        }
        return a.booleanValue();
    }

    public static String a(Map<String, Object> map) {
        String strEncode;
        if (map == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (!map.keySet().isEmpty()) {
            sb.append("?");
        }
        for (String str : map.keySet()) {
            if (sb.length() > 1) {
                sb.append("&");
            }
            String string = map.get(str).toString();
            if (str == null) {
                strEncode = "";
            } else {
                try {
                    strEncode = URLEncoder.encode(str, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("This method requires UTF-8 encoding support", e);
                }
            }
            sb.append(strEncode);
            sb.append("=");
            sb.append(string != null ? URLEncoder.encode(string, "UTF-8") : "");
        }
        return sb.toString();
    }

    public static float b(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int a(int i, Context context) {
        return Math.round(i * b(context));
    }

    public static float a(float f, Context context) {
        return b(context) * f;
    }

    public static f c() {
        char c2;
        boolean z;
        Context contextL = com.chartboost.sdk.b.l();
        Display defaultDisplay = ((WindowManager) contextL.getSystemService("window")).getDefaultDisplay();
        int i = contextL.getResources().getConfiguration().orientation;
        int rotation = defaultDisplay.getRotation();
        if (defaultDisplay.getWidth() == defaultDisplay.getHeight()) {
            c2 = 3;
        } else {
            c2 = defaultDisplay.getWidth() < defaultDisplay.getHeight() ? (char) 1 : (char) 2;
        }
        if (c2 == 1) {
            z = true;
        } else if (c2 == 2) {
            z = false;
        } else {
            z = (c2 == 3 && i != 1 && i == 2) ? false : true;
        }
        if (rotation != 0 && rotation != 2) {
            z = !z;
        }
        if (z) {
            switch (rotation) {
                case 1:
                    return f.g;
                case 2:
                    return f.PORTRAIT_REVERSE;
                case 3:
                    return f.h;
                default:
                    return f.PORTRAIT;
            }
        }
        switch (rotation) {
            case 1:
                return f.e;
            case 2:
                return f.LANDSCAPE_REVERSE;
            case 3:
                return f.f;
            default:
                return f.LANDSCAPE;
        }
    }

    public static void throwProguardError(Exception ex) {
        if (ex instanceof NoSuchMethodException) {
            throw new IllegalStateException("Chartboost library error! Have you used proguard on your application? Make sure to add the line '-keep class com.chartboost.sdk.** { *; }' to your proguard config file.");
        }
        throw new RuntimeException(ex);
    }

    public static void a(HttpResponse httpResponse) {
        if (httpResponse != null) {
            try {
                if (httpResponse.getEntity() != null) {
                    a(httpResponse.getEntity());
                }
            } catch (Exception e) {
                CBLogging.b("CBUtility", "Exception raised calling consumeQuietly over http response", e);
            }
        }
    }

    public static void a(HttpEntity httpEntity) {
        try {
            httpEntity.consumeContent();
        } catch (Exception e) {
            CBLogging.b("CBUtility", "Exception raised calling entity.consumeContent()", e);
        }
    }

    public static String d() {
        Object[] objArr = new Object[3];
        objArr[0] = "Chartboost-Android-SDK";
        objArr[1] = com.chartboost.sdk.b.a() == null ? "" : com.chartboost.sdk.b.a();
        objArr[2] = "5.1.1";
        return String.format("%s %s %s", objArr);
    }

    public static Handler e() {
        return c;
    }

    public static void a(Handler handler) {
        c = handler;
    }
}
