package com.flurry.sdk;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class hs implements gg, hh.a, Thread.UncaughtExceptionHandler {
    private static final String a = hs.class.getSimpleName();
    private boolean b;

    @Override // com.flurry.sdk.gg
    public void a(Context context) {
        hg hgVarA = hg.a();
        this.b = ((Boolean) hgVarA.a("CaptureUncaughtExceptions")).booleanValue();
        hgVarA.a("CaptureUncaughtExceptions", (hh.a) this);
        gd.a(4, a, "initSettings, CrashReportingEnabled = " + this.b);
        ht.a().a(this);
    }

    @Override // com.flurry.sdk.gg
    public void b() {
        ht.b();
        hg.a().b("CaptureUncaughtExceptions", (hh.a) this);
    }

    @Override // com.flurry.sdk.hh.a
    public void a(String str, Object obj) {
        if (str.equals("CaptureUncaughtExceptions")) {
            this.b = ((Boolean) obj).booleanValue();
            gd.a(4, a, "onSettingUpdate, CrashReportingEnabled = " + this.b);
        } else {
            gd.a(6, a, "onSettingUpdate internal error!");
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        th.printStackTrace();
        if (this.b) {
            String message = "";
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null && stackTrace.length > 0) {
                StringBuilder sb = new StringBuilder();
                if (th.getMessage() != null) {
                    sb.append(" (" + th.getMessage() + ")\n");
                }
                message = sb.toString();
            } else if (th.getMessage() != null) {
                message = th.getMessage();
            }
            eq.a().a("uncaught", message, th);
        }
        hc.a().g();
        fh.a().d();
    }
}
