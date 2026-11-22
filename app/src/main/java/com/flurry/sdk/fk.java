package com.flurry.sdk;

import android.telephony.TelephonyManager;

/* JADX INFO: loaded from: classes.dex */
public class fk {
    private static fk a;
    private static final String b = fk.class.getSimpleName();

    public static synchronized fk a() {
        if (a == null) {
            a = new fk();
        }
        return a;
    }

    public static void b() {
        a = null;
    }

    private fk() {
    }

    public String c() {
        TelephonyManager telephonyManager = (TelephonyManager) fp.a().c().getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        return telephonyManager.getNetworkOperatorName();
    }

    public String d() {
        TelephonyManager telephonyManager = (TelephonyManager) fp.a().c().getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        return telephonyManager.getNetworkOperator();
    }
}
