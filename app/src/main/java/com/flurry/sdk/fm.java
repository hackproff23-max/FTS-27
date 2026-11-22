package com.flurry.sdk;

import android.os.Build;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class fm implements hh.a {
    private static fm a;
    private static final String b = fm.class.getSimpleName();
    private String c;
    private String d;

    public static synchronized fm a() {
        if (a == null) {
            a = new fm();
        }
        return a;
    }

    public static void b() {
        if (a != null) {
            hg.a().b("VersionName", (hh.a) a);
        }
        a = null;
    }

    private fm() {
        hg hgVarA = hg.a();
        this.c = (String) hgVarA.a("VersionName");
        hgVarA.a("VersionName", (hh.a) this);
        gd.a(4, b, "initSettings, VersionName = " + this.c);
    }

    public String c() {
        return Build.VERSION.RELEASE;
    }

    public String d() {
        return Build.DEVICE;
    }

    public synchronized String e() {
        String str;
        if (!TextUtils.isEmpty(this.c)) {
            str = this.c;
        } else if (!TextUtils.isEmpty(this.d)) {
            str = this.d;
        } else {
            this.d = f();
            str = this.d;
        }
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x001b, code lost:
    
        r0 = com.facebook.internal.AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String f() {
        /*
            r4 = this;
            com.flurry.sdk.fp r0 = com.flurry.sdk.fp.a()     // Catch: java.lang.Throwable -> L27
            android.content.Context r0 = r0.c()     // Catch: java.lang.Throwable -> L27
            android.content.pm.PackageManager r1 = r0.getPackageManager()     // Catch: java.lang.Throwable -> L27
            java.lang.String r0 = r0.getPackageName()     // Catch: java.lang.Throwable -> L27
            r2 = 0
            android.content.pm.PackageInfo r0 = r1.getPackageInfo(r0, r2)     // Catch: java.lang.Throwable -> L27
            java.lang.String r1 = r0.versionName     // Catch: java.lang.Throwable -> L27
            if (r1 == 0) goto L1c
            java.lang.String r0 = r0.versionName     // Catch: java.lang.Throwable -> L27
        L1b:
            return r0
        L1c:
            int r1 = r0.versionCode     // Catch: java.lang.Throwable -> L27
            if (r1 == 0) goto L30
            int r0 = r0.versionCode     // Catch: java.lang.Throwable -> L27
            java.lang.String r0 = java.lang.Integer.toString(r0)     // Catch: java.lang.Throwable -> L27
            goto L1b
        L27:
            r0 = move-exception
            r1 = 6
            java.lang.String r2 = com.flurry.sdk.fm.b
            java.lang.String r3 = ""
            com.flurry.sdk.gd.a(r1, r2, r3, r0)
        L30:
            java.lang.String r0 = "Unknown"
            goto L1b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.fm.f():java.lang.String");
    }

    @Override // com.flurry.sdk.hh.a
    public void a(String str, Object obj) {
        if (str.equals("VersionName")) {
            this.c = (String) obj;
            gd.a(4, b, "onSettingUpdate, VersionName = " + this.c);
        } else {
            gd.a(6, b, "onSettingUpdate internal error!");
        }
    }
}
