package com.flurry.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class hc implements hh.a {
    private static hc a;
    private static final String b = hc.class.getSimpleName();
    private long f;
    private long g;
    private hb h;
    private final Map<Context, hb> c = new WeakHashMap();
    private final hd d = new hd();
    private final Object e = new Object();
    private fy<he> i = new fy<he>() { // from class: com.flurry.sdk.hc.1
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(he heVar) {
            hc.this.h();
        }
    };
    private fy<fs> j = new fy<fs>() { // from class: com.flurry.sdk.hc.2
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(fs fsVar) {
            switch (AnonymousClass4.a[fsVar.b.ordinal()]) {
                case 1:
                    gd.a(3, hc.b, "Automatic onStartSession for context:" + fsVar.a);
                    hc.this.d(fsVar.a);
                    break;
                case 2:
                    gd.a(3, hc.b, "Automatic onEndSession for context:" + fsVar.a);
                    hc.this.e(fsVar.a);
                    break;
                case 3:
                    gd.a(3, hc.b, "Automatic onEndSession (destroyed) for context:" + fsVar.a);
                    hc.this.e(fsVar.a);
                    break;
            }
        }
    };

    public static synchronized hc a() {
        if (a == null) {
            a = new hc();
        }
        return a;
    }

    public static synchronized void b() {
        if (a != null) {
            fz.a().a(a.i);
            fz.a().a(a.j);
            hg.a().b("ContinueSessionMillis", (hh.a) a);
        }
        a = null;
    }

    /* JADX INFO: renamed from: com.flurry.sdk.hc$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[fs.a.values().length];

        static {
            try {
                a[fs.a.kStarted.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[fs.a.kStopped.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[fs.a.kDestroyed.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private hc() {
        hg hgVarA = hg.a();
        this.f = 0L;
        this.g = ((Long) hgVarA.a("ContinueSessionMillis")).longValue();
        hgVarA.a("ContinueSessionMillis", (hh.a) this);
        gd.a(4, b, "initSettings, ContinueSessionMillis = " + this.g);
        fz.a().a("com.flurry.android.sdk.ActivityLifecycleEvent", this.j);
        fz.a().a("com.flurry.android.sdk.FlurrySessionTimerEvent", this.i);
    }

    public long c() {
        return this.f;
    }

    public synchronized int d() {
        return this.c.size();
    }

    public hb e() {
        hb hbVar;
        synchronized (this.e) {
            hbVar = this.h;
        }
        return hbVar;
    }

    private void a(hb hbVar) {
        synchronized (this.e) {
            this.h = hbVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(hb hbVar) {
        synchronized (this.e) {
            if (this.h == hbVar) {
                this.h = null;
            }
        }
    }

    public synchronized void a(Context context) {
        if (!(context instanceof Application) && ft.a().c() && (context instanceof Activity)) {
            gd.a(3, b, "bootstrap for context:" + context);
            d(context);
        }
    }

    public synchronized void b(Context context) {
        if (!(context instanceof Application) && (!ft.a().c() || !(context instanceof Activity))) {
            gd.a(3, b, "Manual onStartSession for context:" + context);
            d(context);
        }
    }

    public synchronized void c(Context context) {
        if (!(context instanceof Application) && (!ft.a().c() || !(context instanceof Activity))) {
            gd.a(3, b, "Manual onEndSession for context:" + context);
            e(context);
        }
    }

    public synchronized boolean f() {
        boolean z;
        if (e() == null) {
            gd.a(2, b, "Session not found. No active session");
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    public synchronized void g() {
        for (Map.Entry<Context, hb> entry : this.c.entrySet()) {
            entry.getValue().c(entry.getKey());
        }
        this.c.clear();
        h();
    }

    synchronized void d(Context context) {
        if (this.c.get(context) != null) {
            if (ft.a().c()) {
                gd.a(3, b, "Session already started with context:" + context);
            } else {
                gd.e(b, "Session already started with context:" + context);
            }
        } else {
            this.d.a();
            hb hbVarE = e();
            if (hbVarE == null) {
                hbVarE = new hb();
                gd.e(b, "Flurry session created for context:" + context);
                hbVarE.a(context);
            }
            this.c.put(context, hbVarE);
            a(hbVarE);
            gd.e(b, "Flurry session started for context:" + context);
            hbVarE.b(context);
            this.f = 0L;
        }
    }

    synchronized void e(Context context) {
        hb hbVarRemove = this.c.remove(context);
        if (hbVarRemove == null) {
            if (ft.a().c()) {
                gd.a(3, b, "Session cannot be ended, session not found for context:" + context);
            } else {
                gd.e(b, "Session cannot be ended, session not found for context:" + context);
            }
        } else {
            gd.e(b, "Flurry session ended for context:" + context);
            hbVarRemove.c(context);
            if (d() == 0) {
                this.d.a(this.g);
                this.f = System.currentTimeMillis();
            } else {
                this.f = 0L;
            }
        }
    }

    synchronized void h() {
        int iD = d();
        if (iD > 0) {
            gd.a(5, b, "Session cannot be finalized, sessionContextCount:" + iD);
        } else {
            final hb hbVarE = e();
            if (hbVarE == null) {
                gd.a(5, b, "Session cannot be finalized, current session not found");
            } else {
                gd.e(b, "Flurry session finalized");
                hbVarE.a();
                fp.a().b(new hq() { // from class: com.flurry.sdk.hc.3
                    @Override // com.flurry.sdk.hq
                    public void safeRun() {
                        hc.this.b(hbVarE);
                    }
                });
            }
        }
    }

    @Override // com.flurry.sdk.hh.a
    public void a(String str, Object obj) {
        if (str.equals("ContinueSessionMillis")) {
            this.g = ((Long) obj).longValue();
            gd.a(4, b, "onSettingUpdate, ContinueSessionMillis = " + this.g);
        } else {
            gd.a(6, b, "onSettingUpdate internal error!");
        }
    }
}
