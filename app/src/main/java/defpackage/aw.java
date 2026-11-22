package defpackage;

import com.nuance.nmdp.speechkit.w;

/* JADX INFO: loaded from: classes.dex */
public final class aw {
    public static final boolean a;

    static {
        a = !aw.class.getName().endsWith("Config");
    }

    public static String a() {
        return "1.0.0.0";
    }

    public static String b() {
        return w.VERSION;
    }
}
