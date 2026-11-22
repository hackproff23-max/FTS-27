package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import com.mopub.common.Constants;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
@ey
public class ex implements Thread.UncaughtExceptionHandler {
    private Context mContext;
    private Thread.UncaughtExceptionHandler tf;
    private Thread.UncaughtExceptionHandler tg;
    private gs th;

    public ex(Context context, gs gsVar, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, Thread.UncaughtExceptionHandler uncaughtExceptionHandler2) {
        this.tf = uncaughtExceptionHandler;
        this.tg = uncaughtExceptionHandler2;
        this.mContext = context;
        this.th = gsVar;
    }

    public static ex a(Context context, Thread thread, gs gsVar) {
        if (context == null || thread == null || gsVar == null) {
            return null;
        }
        ga.bN();
        if (!k(context)) {
            return null;
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = thread.getUncaughtExceptionHandler();
        ex exVar = new ex(context, gsVar, uncaughtExceptionHandler, Thread.getDefaultUncaughtExceptionHandler());
        if (uncaughtExceptionHandler != null && (uncaughtExceptionHandler instanceof ex)) {
            return (ex) uncaughtExceptionHandler;
        }
        try {
            thread.setUncaughtExceptionHandler(exVar);
            return exVar;
        } catch (SecurityException e) {
            gr.c("Fail to set UncaughtExceptionHandler.", e);
            return null;
        }
    }

    private String cD() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        return str2.startsWith(str) ? str2 : str + " " + str2;
    }

    private Throwable d(Throwable th) {
        Throwable th2;
        Bundle bundleBN = ga.bN();
        if (bundleBN != null && bundleBN.getBoolean("gads:sdk_crash_report_full_stacktrace", false)) {
            return th;
        }
        LinkedList linkedList = new LinkedList();
        while (th != null) {
            linkedList.push(th);
            th = th.getCause();
        }
        Throwable th3 = null;
        while (!linkedList.isEmpty()) {
            Throwable th4 = (Throwable) linkedList.pop();
            StackTraceElement[] stackTrace = th4.getStackTrace();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new StackTraceElement(th4.getClass().getName(), "<filtered>", "<filtered>", 1));
            boolean z = false;
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (G(stackTraceElement.getClassName())) {
                    arrayList.add(stackTraceElement);
                    z = true;
                } else if (H(stackTraceElement.getClassName())) {
                    arrayList.add(stackTraceElement);
                } else {
                    arrayList.add(new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1));
                }
            }
            if (z) {
                th2 = th3 == null ? new Throwable(th4.getMessage()) : new Throwable(th4.getMessage(), th3);
                th2.setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]));
            } else {
                th2 = th3;
            }
            th3 = th2;
        }
        return th3;
    }

    private static boolean k(Context context) {
        Bundle bundleBN = ga.bN();
        return bundleBN != null && bundleBN.getBoolean("gads:sdk_crash_report_enabled", false);
    }

    protected boolean G(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!str.startsWith("com.google.android.gms.ads") && !str.startsWith("com.google.ads")) {
            try {
                return Class.forName(str).isAnnotationPresent(ey.class);
            } catch (Exception e) {
                gr.a("Fail to check class type for class " + str, e);
                return false;
            }
        }
        return true;
    }

    protected boolean H(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("android.") || str.startsWith("java.");
    }

    protected boolean a(Throwable th) {
        if (th == null) {
            return false;
        }
        boolean z = false;
        boolean z2 = false;
        while (th != null) {
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                if (G(stackTraceElement.getClassName())) {
                    z2 = true;
                }
                if (getClass().getName().equals(stackTraceElement.getClassName())) {
                    z = true;
                }
            }
            th = th.getCause();
        }
        return z2 && !z;
    }

    public void b(Throwable th) {
        Throwable thD;
        if (k(this.mContext) && (thD = d(th)) != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(c(thD));
            gi.a(this.mContext, this.th.wS, arrayList, ga.dn());
        }
    }

    protected String c(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return new Uri.Builder().scheme(Constants.HTTPS).path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter("os", Build.VERSION.RELEASE).appendQueryParameter("api", String.valueOf(Build.VERSION.SDK_INT)).appendQueryParameter("device", cD()).appendQueryParameter("js", this.th.wS).appendQueryParameter("appid", this.mContext.getApplicationContext().getPackageName()).appendQueryParameter("stacktrace", stringWriter.toString()).toString();
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable exception) {
        if (a(exception)) {
            b(exception);
            if (Looper.getMainLooper().getThread() != thread) {
                return;
            }
        }
        if (this.tf != null) {
            this.tf.uncaughtException(thread, exception);
        } else if (this.tg != null) {
            this.tg.uncaughtException(thread, exception);
        }
    }
}
