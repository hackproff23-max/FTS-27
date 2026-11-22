package defpackage;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public final class em {
    public static final int a = Integer.parseInt(Build.VERSION.SDK);
    public static final boolean b;
    public static final boolean c;
    private static boolean d;
    private static boolean e;
    private static final boolean f;

    static {
        String str;
        try {
            str = (String) Build.class.getField("MANUFACTURER").get(null);
        } catch (Exception e2) {
            str = "";
        }
        String str2 = Build.MODEL;
        d = str.equalsIgnoreCase("samsung") && (str2.equalsIgnoreCase("SGH-T959") || str2.equalsIgnoreCase("SAMSUNG-SGH-I897") || str2.equalsIgnoreCase("SGH-I897") || str2.equalsIgnoreCase("GT-I9000"));
        b = str.equalsIgnoreCase("htc") && (str2.equalsIgnoreCase("PC36100") || str2.equalsIgnoreCase("ADR6300") || str2.equalsIgnoreCase("HTC Glacier") || str2.equalsIgnoreCase("T-Mobile myTouch 4G") || str2.equalsIgnoreCase("T-Mobile G2"));
        e = str2.equalsIgnoreCase("Motorola_i1");
        f = str.equalsIgnoreCase("Motorola") && str2.equalsIgnoreCase("MB860");
        if (str.equalsIgnoreCase("samsung")) {
            int i = a;
        }
        c = d || e;
        boolean z = d;
        boolean z2 = f;
    }
}
