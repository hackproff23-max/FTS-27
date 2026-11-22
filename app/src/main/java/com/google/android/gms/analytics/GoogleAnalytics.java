package com.google.android.gms.analytics;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class GoogleAnalytics extends TrackerHandler {
    private static boolean Bm;
    private static GoogleAnalytics Bs;
    private aj Bn;
    private volatile Boolean Bo;
    private Logger Bp;
    private Set<a> Bq;
    private boolean Br;
    private Context mContext;
    private f yV;
    private String ya;
    private String yb;
    private boolean yt;

    interface a {
        void i(Activity activity);

        void j(Activity activity);
    }

    class b implements Application.ActivityLifecycleCallbacks {
        b() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            GoogleAnalytics.this.g(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            GoogleAnalytics.this.h(activity);
        }
    }

    protected GoogleAnalytics(Context context) {
        this(context, x.A(context), v.eu());
    }

    private GoogleAnalytics(Context context, f thread, aj serviceManager) {
        this.Bo = false;
        this.Br = false;
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }
        this.mContext = context.getApplicationContext();
        this.yV = thread;
        this.Bn = serviceManager;
        g.y(this.mContext);
        ai.y(this.mContext);
        k.y(this.mContext);
        this.Bp = new p();
        this.Bq = new HashSet();
        eZ();
    }

    private Tracker a(Tracker tracker) {
        if (this.ya != null) {
            tracker.set("&an", this.ya);
        }
        if (this.yb != null) {
            tracker.set("&av", this.yb);
        }
        return tracker;
    }

    private int ai(String str) {
        String lowerCase = str.toLowerCase();
        if ("verbose".equals(lowerCase)) {
            return 0;
        }
        if ("info".equals(lowerCase)) {
            return 1;
        }
        if ("warning".equals(lowerCase)) {
            return 2;
        }
        return "error".equals(lowerCase) ? 3 : -1;
    }

    static GoogleAnalytics eY() {
        GoogleAnalytics googleAnalytics;
        synchronized (GoogleAnalytics.class) {
            googleAnalytics = Bs;
        }
        return googleAnalytics;
    }

    private void eZ() {
        ApplicationInfo applicationInfo;
        int i;
        aa aaVarX;
        if (Bm) {
            return;
        }
        try {
            applicationInfo = this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 129);
        } catch (PackageManager.NameNotFoundException e) {
            ae.V("PackageManager doesn't know about package: " + e);
            applicationInfo = null;
        }
        if (applicationInfo == null) {
            ae.W("Couldn't get ApplicationInfo to load gloabl config.");
            return;
        }
        Bundle bundle = applicationInfo.metaData;
        if (bundle == null || (i = bundle.getInt("com.google.android.gms.analytics.globalConfigResource")) <= 0 || (aaVarX = new z(this.mContext).x(i)) == null) {
            return;
        }
        a(aaVarX);
    }

    public static GoogleAnalytics getInstance(Context context) {
        GoogleAnalytics googleAnalytics;
        synchronized (GoogleAnalytics.class) {
            if (Bs == null) {
                Bs = new GoogleAnalytics(context);
            }
            googleAnalytics = Bs;
        }
        return googleAnalytics;
    }

    void a(a aVar) {
        this.Bq.add(aVar);
        if (this.mContext instanceof Application) {
            enableAutoActivityReports((Application) this.mContext);
        }
    }

    void a(aa aaVar) {
        int iAi;
        ae.V("Loading global config values.");
        if (aaVar.eO()) {
            this.ya = aaVar.eP();
            ae.V("app name loaded: " + this.ya);
        }
        if (aaVar.eQ()) {
            this.yb = aaVar.eR();
            ae.V("app version loaded: " + this.yb);
        }
        if (aaVar.eS() && (iAi = ai(aaVar.eT())) >= 0) {
            ae.V("log level loaded: " + iAi);
            getLogger().setLogLevel(iAi);
        }
        if (aaVar.eU()) {
            this.Bn.setLocalDispatchPeriod(aaVar.eV());
        }
        if (aaVar.eW()) {
            setDryRun(aaVar.eX());
        }
    }

    void b(a aVar) {
        this.Bq.remove(aVar);
    }

    void dY() {
        this.yV.dY();
    }

    @Deprecated
    public void dispatchLocalHits() {
        this.Bn.dispatchLocalHits();
    }

    public void enableAutoActivityReports(Application application) {
        if (Build.VERSION.SDK_INT < 14 || this.Br) {
            return;
        }
        application.registerActivityLifecycleCallbacks(new b());
        this.Br = true;
    }

    void g(Activity activity) {
        Iterator<a> it = this.Bq.iterator();
        while (it.hasNext()) {
            it.next().i(activity);
        }
    }

    public boolean getAppOptOut() {
        y.eK().a(y.a.GET_APP_OPT_OUT);
        return this.Bo.booleanValue();
    }

    public Logger getLogger() {
        return this.Bp;
    }

    void h(Activity activity) {
        Iterator<a> it = this.Bq.iterator();
        while (it.hasNext()) {
            it.next().j(activity);
        }
    }

    public boolean isDryRunEnabled() {
        y.eK().a(y.a.GET_DRY_RUN);
        return this.yt;
    }

    public Tracker newTracker(int configResId) {
        Tracker trackerA;
        am amVarX;
        synchronized (this) {
            y.eK().a(y.a.GET_TRACKER);
            Tracker tracker = new Tracker(null, this, this.mContext);
            if (configResId > 0 && (amVarX = new al(this.mContext).x(configResId)) != null) {
                tracker.a(amVarX);
            }
            trackerA = a(tracker);
        }
        return trackerA;
    }

    public Tracker newTracker(String trackingId) {
        Tracker trackerA;
        synchronized (this) {
            y.eK().a(y.a.GET_TRACKER);
            trackerA = a(new Tracker(trackingId, this, this.mContext));
        }
        return trackerA;
    }

    public void reportActivityStart(Activity activity) {
        if (this.Br) {
            return;
        }
        g(activity);
    }

    public void reportActivityStop(Activity activity) {
        if (this.Br) {
            return;
        }
        h(activity);
    }

    public void setAppOptOut(boolean optOut) {
        y.eK().a(y.a.SET_APP_OPT_OUT);
        this.Bo = Boolean.valueOf(optOut);
        if (this.Bo.booleanValue()) {
            this.yV.dQ();
        }
    }

    public void setDryRun(boolean dryRun) {
        y.eK().a(y.a.SET_DRY_RUN);
        this.yt = dryRun;
    }

    @Deprecated
    public void setLocalDispatchPeriod(int dispatchPeriodInSeconds) {
        this.Bn.setLocalDispatchPeriod(dispatchPeriodInSeconds);
    }

    public void setLogger(Logger logger) {
        y.eK().a(y.a.SET_LOGGER);
        this.Bp = logger;
    }

    @Override // com.google.android.gms.analytics.TrackerHandler
    void u(Map<String, String> map) {
        synchronized (this) {
            try {
                if (map == null) {
                    throw new IllegalArgumentException("hit cannot be null");
                }
                an.a(map, "&ul", an.a(Locale.getDefault()));
                an.a(map, "&sr", ai.fl());
                map.put("&_u", y.eK().eM());
                y.eK().eL();
                this.yV.u(map);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
