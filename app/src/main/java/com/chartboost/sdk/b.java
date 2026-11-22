package com.chartboost.sdk;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.g;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.impl.ba;
import com.chartboost.sdk.impl.l;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static c.a c;
    private static String d;
    private static String e;
    private static com.chartboost.sdk.a f;
    private static boolean g = false;
    private static boolean h = false;
    private static boolean i = false;
    private static Chartboost.CBFramework j = null;
    private static String k = null;
    private static SharedPreferences l = null;
    private static boolean m = true;
    private static volatile boolean n = false;
    protected static Context a = null;
    protected static Application b = null;
    private static boolean o = false;
    private static boolean p = true;
    private static boolean q = false;
    private static boolean r = true;

    public interface a {
        void a();
    }

    private b() {
    }

    private static SharedPreferences u() {
        if (l == null) {
            l = CBUtility.a();
        }
        return l;
    }

    public static void a(Chartboost.CBFramework cBFramework) {
        p();
        if (cBFramework == null) {
            throw new RuntimeException("Pass a valid CBFramework enum value");
        }
        j = cBFramework;
    }

    public static Chartboost.CBFramework a() {
        p();
        if (j == null) {
            return null;
        }
        return j;
    }

    public static String b() {
        p();
        d = u().getString("appId", d);
        return d;
    }

    public static void a(String str) {
        p();
        d = str;
        u().edit().putString("appId", str).commit();
    }

    public static String c() {
        p();
        e = u().getString("appSignature", e);
        return e;
    }

    public static void b(String str) {
        p();
        e = str;
        u().edit().putString("appSignature", str).commit();
    }

    public static com.chartboost.sdk.a d() {
        p();
        return f;
    }

    public static void a(com.chartboost.sdk.a aVar) {
        p();
        f = aVar;
    }

    public static boolean e() {
        p();
        return g;
    }

    public static void a(boolean z) {
        p();
        g = z;
    }

    public static boolean f() {
        p();
        return i;
    }

    public static boolean g() {
        return m;
    }

    public static void b(boolean z) {
        m = z;
    }

    public static JSONObject h() {
        p();
        String string = u().getString("trackingLevels", "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        com.chartboost.sdk.Libraries.e.a aVarJ = com.chartboost.sdk.Libraries.e.a.j(string);
        if (!aVarJ.c()) {
            return null;
        }
        return aVarJ.e();
    }

    public static boolean i() {
        p();
        JSONObject jSONObjectH = h();
        return jSONObjectH != null && (jSONObjectH.optBoolean("debug") || jSONObjectH.optBoolean("session") || jSONObjectH.optBoolean("system") || jSONObjectH.optBoolean("user"));
    }

    public static void a(CBLogging.Level level) {
        p();
        CBLogging.a = level;
    }

    public static CBLogging.Level j() {
        p();
        return CBLogging.a;
    }

    public static String k() {
        p();
        return k;
    }

    public static void c(String str) {
        p();
        k = str;
    }

    public static void a(com.chartboost.sdk.Libraries.e.a aVar) {
        Map<String, Object> mapF;
        if (aVar != null && (mapF = aVar.f()) != null) {
            SharedPreferences.Editor editorEdit = u().edit();
            for (String str : mapF.keySet()) {
                Object obj = mapF.get(str);
                if (obj instanceof String) {
                    editorEdit.putString(str, (String) obj);
                } else if (obj instanceof Integer) {
                    editorEdit.putInt(str, ((Integer) obj).intValue());
                } else if (obj instanceof Float) {
                    editorEdit.putFloat(str, ((Float) obj).floatValue());
                } else if (obj instanceof Long) {
                    editorEdit.putLong(str, ((Long) obj).longValue());
                } else if (obj instanceof Boolean) {
                    editorEdit.putBoolean(str, ((Boolean) obj).booleanValue());
                } else if (obj != null) {
                    editorEdit.putString(str, obj.toString());
                }
            }
            editorEdit.commit();
        }
    }

    public static void a(final a aVar) {
        ba baVar = new ba("/api/config");
        baVar.a(false);
        baVar.b(false);
        baVar.a(l.a.HIGH);
        baVar.a(g.a(g.a("status", com.chartboost.sdk.Libraries.a.a)));
        baVar.a(new ba.c() { // from class: com.chartboost.sdk.b.1
            @Override // com.chartboost.sdk.impl.ba.c
            public void a(com.chartboost.sdk.Libraries.e.a aVar2, ba baVar2) {
                if (aVar2 != null) {
                    b.a(aVar2.a("response"));
                }
                if (aVar != null) {
                    aVar.a();
                }
            }

            @Override // com.chartboost.sdk.impl.ba.c
            public void a(com.chartboost.sdk.Libraries.e.a aVar2, ba baVar2, CBError cBError) {
                if (aVar != null) {
                    aVar.a();
                }
            }
        });
    }

    public static Context l() {
        return a;
    }

    public static boolean m() {
        return n;
    }

    protected static void c(boolean z) {
        n = z;
    }

    public static void n() {
        if (!m()) {
            throw new IllegalStateException("You need call Chartboost.onStart() before calling any public APIs ");
        }
    }

    public static void o() {
        if (Chartboost.b == null) {
            throw new IllegalStateException("The activity context must be set through the Chartboost onCreate method");
        }
    }

    public static void p() {
        if (b == null) {
            throw new IllegalStateException("Need to intialize chartboost using Chartboost.init() as the application object is null");
        }
    }

    public static void d(boolean z) {
        if (c != null) {
            c.a(z);
        }
    }

    protected static void e(boolean z) {
        o = z;
    }

    public static boolean q() {
        return o;
    }

    public static void f(boolean z) {
        p = z;
    }

    public static boolean r() {
        return p;
    }

    public static void g(boolean z) {
        q = z;
    }

    public static boolean s() {
        return q;
    }

    public static void h(boolean z) {
        r = z;
    }

    public static boolean t() {
        return r;
    }
}
