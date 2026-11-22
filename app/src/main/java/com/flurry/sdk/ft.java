package com.flurry.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class ft {
    private static ft a;
    private static final String b = ft.class.getSimpleName();
    private Object c;

    public static synchronized ft a() {
        if (a == null) {
            a = new ft();
        }
        return a;
    }

    public static synchronized void b() {
        if (a != null) {
            a.f();
        }
        a = null;
    }

    private ft() {
        e();
    }

    public boolean c() {
        return this.c != null;
    }

    @TargetApi(14)
    private void e() {
        if (Build.VERSION.SDK_INT >= 14 && this.c == null) {
            Context contextC = fp.a().c();
            if (contextC instanceof Application) {
                this.c = new Application.ActivityLifecycleCallbacks() { // from class: com.flurry.sdk.ft.1
                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityCreated(Activity activity, Bundle bundle) {
                        gd.a(3, ft.b, "onActivityCreated for activity:" + activity);
                        a(activity, fs.a.kCreated);
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityStarted(Activity activity) {
                        gd.a(3, ft.b, "onActivityStarted for activity:" + activity);
                        a(activity, fs.a.kStarted);
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityResumed(Activity activity) {
                        gd.a(3, ft.b, "onActivityResumed for activity:" + activity);
                        a(activity, fs.a.kResumed);
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityPaused(Activity activity) {
                        gd.a(3, ft.b, "onActivityPaused for activity:" + activity);
                        a(activity, fs.a.kPaused);
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityStopped(Activity activity) {
                        gd.a(3, ft.b, "onActivityStopped for activity:" + activity);
                        a(activity, fs.a.kStopped);
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                        gd.a(3, ft.b, "onActivitySaveInstanceState for activity:" + activity);
                        a(activity, fs.a.kSaveState);
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityDestroyed(Activity activity) {
                        gd.a(3, ft.b, "onActivityDestroyed for activity:" + activity);
                        a(activity, fs.a.kDestroyed);
                    }

                    protected void a(Activity activity, fs.a aVar) {
                        fs fsVar = new fs();
                        fsVar.a = activity;
                        fsVar.b = aVar;
                        fsVar.b();
                    }
                };
                ((Application) contextC).registerActivityLifecycleCallbacks((Application.ActivityLifecycleCallbacks) this.c);
            }
        }
    }

    @TargetApi(14)
    private void f() {
        if (Build.VERSION.SDK_INT >= 14 && this.c != null) {
            Context contextC = fp.a().c();
            if (contextC instanceof Application) {
                ((Application) contextC).unregisterActivityLifecycleCallbacks((Application.ActivityLifecycleCallbacks) this.c);
                this.c = null;
            }
        }
    }
}
