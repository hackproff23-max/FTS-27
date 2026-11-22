package com.google.android.gms.analytics;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/* JADX INFO: loaded from: classes.dex */
class g implements q {
    private static Object xO = new Object();
    private static g ye;
    protected String ya;
    protected String yb;
    protected String yc;
    protected String yd;

    protected g() {
    }

    private g(Context context) {
        PackageManager packageManager = context.getPackageManager();
        this.yc = context.getPackageName();
        this.yd = packageManager.getInstallerPackageName(this.yc);
        String string = this.yc;
        String str = null;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            if (packageInfo != null) {
                string = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
                str = packageInfo.versionName;
            }
        } catch (PackageManager.NameNotFoundException e) {
            ae.T("Error retrieving package info: appName set to " + string);
        }
        this.ya = string;
        this.yb = str;
    }

    public static g dZ() {
        return ye;
    }

    public static void y(Context context) {
        synchronized (xO) {
            if (ye == null) {
                ye = new g(context);
            }
        }
    }

    public boolean ac(String str) {
        return "&an".equals(str) || "&av".equals(str) || "&aid".equals(str) || "&aiid".equals(str);
    }

    @Override // com.google.android.gms.analytics.q
    public String getValue(String field) {
        if (field == null) {
            return null;
        }
        if (field.equals("&an")) {
            return this.ya;
        }
        if (field.equals("&av")) {
            return this.yb;
        }
        if (field.equals("&aid")) {
            return this.yc;
        }
        if (field.equals("&aiid")) {
            return this.yd;
        }
        return null;
    }
}
