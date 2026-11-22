package com.chartboost.sdk;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.SurfaceView;
import android.view.ViewGroup;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.g;
import com.chartboost.sdk.Libraries.k;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.impl.ae;
import com.chartboost.sdk.impl.af;
import com.chartboost.sdk.impl.aw;
import com.chartboost.sdk.impl.az;
import com.chartboost.sdk.impl.ba;
import com.chartboost.sdk.impl.bb;
import com.chartboost.sdk.impl.bd;
import com.chartboost.sdk.impl.be;
import com.chartboost.sdk.impl.bh;
import com.chartboost.sdk.impl.m;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class Chartboost {
    protected static Handler a;
    private static Runnable o;
    private static volatile Chartboost c = null;
    private static CBImpressionActivity d = null;
    private static com.chartboost.sdk.Model.a e = null;
    private static az f = null;
    private static bb g = null;
    private static m h = null;
    private static com.chartboost.sdk.Tracking.a i = null;
    private static boolean j = false;
    private static SparseBooleanArray k = new SparseBooleanArray();
    private static e l = null;
    private static c m = null;
    private static boolean n = false;
    protected static k b = null;
    private static boolean p = false;
    private static boolean q = false;
    private static Runnable r = new Runnable() { // from class: com.chartboost.sdk.Chartboost.7
        @Override // java.lang.Runnable
        public void run() {
            if (!Chartboost.q) {
                Chartboost.c();
            }
            boolean unused = Chartboost.q = false;
        }
    };

    public enum CBFramework {
        CBFrameworkUnity("Unity"),
        CBFrameworkCorona("Corona"),
        CBFrameworkAir("AIR"),
        CBFrameworkGameSalad("GameSalad"),
        CBFrameworkCordova("Cordova"),
        CBFrameworkCocoonJS("CocoonJS");

        private final String a;

        CBFramework(String s) {
            this.a = s;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.a;
        }
    }

    private Chartboost(Activity app, String appId, String appSignature) {
        b.b = app.getApplication();
        b.a = app.getApplicationContext();
        c = this;
        a = new Handler();
        CBUtility.a(a);
        f = az.a();
        l = e.a();
        g = bb.a(b.a);
        h = g.a();
        m = c.a();
        i = com.chartboost.sdk.Tracking.a.a();
        f.a(b.a);
        be.a();
        o = new a();
        b.a(appId);
        b.b(appSignature);
        com.chartboost.sdk.Libraries.c.a();
    }

    public static void startWithAppId(final Activity activity, final String appId, final String appSignature) {
        if (c == null) {
            synchronized (Chartboost.class) {
                if (c == null) {
                    if (activity == null && !(activity instanceof Activity)) {
                        throw new RuntimeException("Activity object is null. Please pass a valid activity object");
                    }
                    if (appId == null || appSignature == null) {
                        throw new RuntimeException("appId or appSignature is null. Please pass a valid id's");
                    }
                    a(new Runnable() { // from class: com.chartboost.sdk.Chartboost.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Chartboost unused = Chartboost.c = new Chartboost(activity, appId, appSignature);
                        }
                    });
                }
            }
        }
    }

    public static void onCreate(final Activity activity) {
        b.p();
        a(new Runnable() { // from class: com.chartboost.sdk.Chartboost.5
            @Override // java.lang.Runnable
            public void run() {
                Chartboost.d(activity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(Activity activity) {
        if (b != null && !b.b(activity) && p()) {
            f(b);
            c(b, false);
        }
        a.removeCallbacks(o);
        b = k.a(activity);
        if (!getImpressionsUseActivities()) {
            createSurfaceView(activity);
        }
        bb.d();
    }

    public static void onStart(final Activity activity) {
        b.p();
        a(new Runnable() { // from class: com.chartboost.sdk.Chartboost.6
            @Override // java.lang.Runnable
            public void run() {
                Chartboost.a.removeCallbacks(Chartboost.o);
                if (Chartboost.b != null && !Chartboost.b.b(activity) && Chartboost.p()) {
                    Chartboost.f(Chartboost.b);
                    Chartboost.c(Chartboost.b, false);
                }
                Chartboost.b(activity, true);
                Chartboost.b = k.a(activity);
                Chartboost.a();
                Chartboost.a(activity);
                Chartboost.f.b(b.a);
                if (!b.e()) {
                    Chartboost.h.a();
                }
                Chartboost.g.f();
            }
        });
    }

    protected static void a(Activity activity) {
        b.a = activity.getApplicationContext();
        if (!(activity instanceof CBImpressionActivity)) {
            b = k.a(activity);
            c(b, true);
        } else {
            a((CBImpressionActivity) activity);
        }
        a.removeCallbacks(o);
        boolean z = b.a() == CBFramework.CBFrameworkAir;
        if (activity != null) {
            if (z || e(activity)) {
                b(k.a(activity), true);
                if (activity instanceof CBImpressionActivity) {
                    n = false;
                }
                if (m.a(activity, e)) {
                    e = null;
                }
                com.chartboost.sdk.Model.a aVarC = m.c();
                if (aVarC != null) {
                    aVarC.u();
                }
            }
        }
    }

    protected static void a() {
        if (b.a == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling startSession().");
        }
        if (b.e() && b.m()) {
            if (!p) {
                q = false;
                o();
                return;
            } else {
                q = true;
                return;
            }
        }
        o();
    }

    protected static void b() {
        if (b.e()) {
            a.postDelayed(r, 500L);
        } else {
            c();
        }
    }

    private static void o() {
        p = true;
        b.c(true);
        i.h();
        com.chartboost.sdk.Tracking.a.b();
        final boolean zI = com.chartboost.sdk.Tracking.a.i();
        b.a(new b.a() { // from class: com.chartboost.sdk.Chartboost.8
            @Override // com.chartboost.sdk.b.a
            public void a() {
                if (zI) {
                    be.b();
                    ba baVar = new ba("api/install");
                    baVar.b(Chartboost.getValidContext());
                    baVar.a(true);
                    baVar.a(g.a("status", com.chartboost.sdk.Libraries.a.a));
                    baVar.a(new ba.d() { // from class: com.chartboost.sdk.Chartboost.8.1
                        @Override // com.chartboost.sdk.impl.ba.c
                        public void a(com.chartboost.sdk.Libraries.e.a aVar, ba baVar2) {
                            if (CBUtility.a(b.l())) {
                                String strE = aVar.e("latest-sdk-version");
                                if (TextUtils.isEmpty(strE) || strE.equals("5.1.1")) {
                                    return;
                                }
                                CBLogging.a(String.format(Locale.US, "Chartboost SDK is not up to date. (Current: %s, Latest: %s)\n Download latest SDK at:\n\thttps://www.chartboost.com/support/sdk_download/?os=ios", "5.1.1", strE));
                            }
                        }
                    });
                }
            }
        });
    }

    protected static void c() {
        p = false;
        if (i == null) {
            i = com.chartboost.sdk.Tracking.a.a();
        }
        i.c();
    }

    public static void onResume(final Activity activity) {
        b.p();
        a(new Runnable() { // from class: com.chartboost.sdk.Chartboost.9
            @Override // java.lang.Runnable
            public void run() {
                k kVarA = k.a(activity);
                if (Chartboost.d(kVarA)) {
                    Chartboost.a(kVarA);
                } else if (b.a() != null && b.a().ordinal() == CBFramework.CBFrameworkUnity.ordinal()) {
                    Chartboost.a();
                }
            }
        });
    }

    protected static void a(k kVar) {
        com.chartboost.sdk.Model.a aVarC = c.a().c();
        if (b.a() != null && b.a().ordinal() == CBFramework.CBFrameworkUnity.ordinal()) {
            a();
        }
        if (aVarC != null) {
            aVarC.t();
        }
    }

    public static void onPause(final Activity activity) {
        b.p();
        a(new Runnable() { // from class: com.chartboost.sdk.Chartboost.10
            @Override // java.lang.Runnable
            public void run() {
                k kVarA = k.a(activity);
                if (Chartboost.d(kVarA)) {
                    Chartboost.b(kVarA);
                }
            }
        });
    }

    protected static void b(k kVar) {
        com.chartboost.sdk.Model.a aVarC = c.a().c();
        if (aVarC != null) {
            aVarC.v();
        }
    }

    public static void onStop(final Activity activity) {
        b.p();
        a(new Runnable() { // from class: com.chartboost.sdk.Chartboost.11
            @Override // java.lang.Runnable
            public void run() {
                k kVarA = k.a(activity);
                if (Chartboost.d(kVarA)) {
                    Chartboost.f(kVarA);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f(k kVar) {
        if (!b.e()) {
            c(kVar);
        }
        if (!(kVar.get() instanceof CBImpressionActivity)) {
            c(kVar, false);
        }
        f.c(b.a);
        if (!b.e()) {
            h.b();
        }
        g.g();
        b();
    }

    protected static void c(k kVar) {
        e eVarG = g();
        if (g(kVar) && eVarG != null) {
            com.chartboost.sdk.Model.a aVarC = c.a().c();
            if (aVarC != null) {
                eVarG.c(aVarC);
                e = aVarC;
            }
            b(kVar, false);
            if (kVar.get() instanceof CBImpressionActivity) {
                f();
            }
        }
        if (!(kVar.get() instanceof CBImpressionActivity)) {
            c(kVar, false);
        }
    }

    public static boolean onBackPressed() {
        b.p();
        if (b == null) {
            throw new IllegalStateException("The Chartboost methods onCreate(), onStart(), onStop(), and onDestroy() must be called in the corresponding methods of your activity in order for Chartboost to function properly.");
        }
        if (!b.e()) {
            return d();
        }
        if (!n) {
            return false;
        }
        n = false;
        d();
        return true;
    }

    protected static boolean d() {
        final c cVarA = c.a();
        com.chartboost.sdk.Model.a aVarC = cVarA.c();
        if (aVarC != null && aVarC.b == com.chartboost.sdk.Model.a.b.DISPLAYED) {
            if (aVarC.s()) {
                return true;
            }
            a(new Runnable() { // from class: com.chartboost.sdk.Chartboost.12
                @Override // java.lang.Runnable
                public void run() {
                    cVarA.b();
                }
            });
            return true;
        }
        final e eVarG = g();
        if (eVarG != null && eVarG.b()) {
            a(new Runnable() { // from class: com.chartboost.sdk.Chartboost.2
                @Override // java.lang.Runnable
                public void run() {
                    eVarG.a(cVarA.c(), true);
                }
            });
            return true;
        }
        return false;
    }

    public static void onDestroy(final Activity activity) {
        b.p();
        a(new Runnable() { // from class: com.chartboost.sdk.Chartboost.3
            @Override // java.lang.Runnable
            public void run() {
                if (Chartboost.b == null || Chartboost.b.b(activity)) {
                    Chartboost.a.removeCallbacks(Chartboost.o);
                    Runnable unused = Chartboost.o = new a();
                    Chartboost.a.postDelayed(Chartboost.o, 10000L);
                }
                Chartboost.b(activity);
            }
        });
    }

    protected static void b(Activity activity) {
        b(k.a(activity), false);
    }

    public static void didPassAgeGate(boolean pass) {
        b.d(pass);
    }

    public static void setShouldPauseClickForConfirmation(boolean shouldPause) {
        b.e(shouldPause);
    }

    public static void clearCache() {
        b.p();
        b.o();
        b.n();
        if (b.a == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling clearImageCache().");
        }
        bd.a().b();
        af.h().a();
        ae.f().a();
        aw.f().a();
        com.chartboost.sdk.InPlay.a.b();
    }

    public static boolean hasRewardedVideo(String location) {
        b.p();
        b.o();
        b.n();
        return af.h().c(location);
    }

    public static void cacheRewardedVideo(String location) {
        b.p();
        b.o();
        b.n();
        if (TextUtils.isEmpty(location)) {
            CBLogging.b("Chartboost", "cacheRewardedVideo location cannot be empty");
            if (b.d() != null) {
                b.d().didFailToLoadRewardedVideo(location, CBError.CBImpressionError.INVALID_LOCATION);
                return;
            }
            return;
        }
        af.h().b(location);
    }

    public static void showRewardedVideo(String location) {
        b.p();
        b.o();
        b.n();
        if (TextUtils.isEmpty(location)) {
            CBLogging.b("Chartboost", "showRewardedVideo location cannot be empty");
            if (b.d() != null) {
                b.d().didFailToLoadRewardedVideo(location, CBError.CBImpressionError.INVALID_LOCATION);
                return;
            }
            return;
        }
        af.h().a(location);
    }

    public static boolean hasInterstitial(String location) {
        b.p();
        b.o();
        b.n();
        return ae.f().c(location);
    }

    public static void cacheInterstitial(String location) {
        b.p();
        b.o();
        b.n();
        if (TextUtils.isEmpty(location)) {
            CBLogging.b("Chartboost", "cacheInterstitial location cannot be empty");
            if (b.d() != null) {
                b.d().didFailToLoadInterstitial(location, CBError.CBImpressionError.INVALID_LOCATION);
                return;
            }
            return;
        }
        ae.f().b(location);
    }

    public static void showInterstitial(String location) {
        b.p();
        b.o();
        b.n();
        if (TextUtils.isEmpty(location)) {
            CBLogging.b("Chartboost", "showInterstitial location cannot be empty");
            if (b.d() != null) {
                b.d().didFailToLoadInterstitial(location, CBError.CBImpressionError.INVALID_LOCATION);
                return;
            }
            return;
        }
        ae.f().a(location);
    }

    public static boolean hasMoreApps(String location) {
        b.p();
        b.o();
        b.n();
        return aw.f().c(location);
    }

    public static void cacheMoreApps(String location) {
        b.p();
        b.o();
        b.n();
        if (TextUtils.isEmpty(location)) {
            CBLogging.b("Chartboost", "cacheMoreApps location cannot be empty");
            if (b.d() != null) {
                b.d().didFailToLoadMoreApps(location, CBError.CBImpressionError.INVALID_LOCATION);
                return;
            }
            return;
        }
        aw.f().b(location);
    }

    public static void showMoreApps(String location) {
        b.p();
        b.o();
        b.n();
        if (TextUtils.isEmpty(location)) {
            CBLogging.b("Chartboost", "showMoreApps location cannot be empty");
            if (b.d() != null) {
                b.d().didFailToLoadMoreApps(location, CBError.CBImpressionError.INVALID_LOCATION);
                return;
            }
            return;
        }
        aw.f().a(location);
    }

    public static boolean isAnyViewVisible() {
        e eVarG = g();
        if (eVarG == null) {
            return false;
        }
        return eVarG.c();
    }

    public static void setFramework(CBFramework framework) {
        b.a(framework);
    }

    public static String getCustomId() {
        return b.k();
    }

    public static void setCustomId(String customID) {
        b.c(customID);
    }

    public static void setLoggingLevel(CBLogging.Level lvl) {
        b.a(lvl);
    }

    public static CBLogging.Level getLoggingLevel() {
        return b.j();
    }

    public static com.chartboost.sdk.a getDelegate() {
        return b.d();
    }

    public static void setDelegate(ChartboostDelegate delegate) {
        b.a(delegate);
    }

    public static boolean getAutoCacheAds() {
        return b.g();
    }

    public static void setAutoCacheAds(boolean autoCacheAds) {
        b.b(autoCacheAds);
    }

    public static void setShouldRequestInterstitialsInFirstSession(boolean shouldRequest) {
        b.f(shouldRequest);
    }

    public static void setShouldDisplayLoadingViewForMoreApps(boolean shouldDisplay) {
        b.g(shouldDisplay);
    }

    public static void setShouldPrefetchVideoContent(boolean shouldPrefetch) {
        b.h(shouldPrefetch);
        if (shouldPrefetch) {
            be.b();
        } else {
            be.d();
        }
    }

    protected static Activity e() {
        return b.e() ? d : getHostActivity();
    }

    private static boolean e(Activity activity) {
        if (b.e()) {
            return d == activity;
        }
        if (b == null) {
            return activity == null;
        }
        return b.b(activity);
    }

    private static boolean g(k kVar) {
        if (b.e()) {
            if (kVar == null) {
                return d == null;
            }
            return kVar.b(d);
        }
        if (b == null) {
            return kVar == null;
        }
        return b.a(kVar);
    }

    protected static void a(CBImpressionActivity cBImpressionActivity) {
        if (!j) {
            b.a = cBImpressionActivity.getApplicationContext();
            d = cBImpressionActivity;
            j = true;
        }
        a.removeCallbacks(o);
    }

    protected static void f() {
        if (j) {
            d = null;
            j = false;
        }
    }

    protected static void a(com.chartboost.sdk.Model.a aVar) {
        e eVarG = g();
        if (eVarG != null && eVarG.c() && eVarG.d().h() != aVar) {
            aVar.a(CBError.CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
            return;
        }
        if (b.e()) {
            if (j) {
                if (e() != null && eVarG != null) {
                    eVarG.a(aVar);
                    return;
                }
                if (e() == null) {
                    CBLogging.b("Chartboost", "Activity not found to display the view");
                    aVar.a(CBError.CBImpressionError.NO_HOST_ACTIVITY);
                } else {
                    CBLogging.b("Chartboost", "Missing view controller to manage the open impression activity");
                }
                aVar.a(CBError.CBImpressionError.ERROR_DISPLAYING_VIEW);
                return;
            }
            if (!p()) {
                aVar.a(CBError.CBImpressionError.NO_HOST_ACTIVITY);
                return;
            }
            Activity hostActivity = getHostActivity();
            if (hostActivity == null) {
                CBLogging.b("Chartboost", "Failed to display impression as the host activity reference has been lost!");
                aVar.a(CBError.CBImpressionError.NO_HOST_ACTIVITY);
                return;
            } else {
                if (e != null && e != aVar) {
                    aVar.a(CBError.CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
                    return;
                }
                e = aVar;
                Intent intent = new Intent(hostActivity, (Class<?>) CBImpressionActivity.class);
                intent.putExtra("paramFullscreen", ((hostActivity.getWindow().getAttributes().flags & 1024) != 0) && !((hostActivity.getWindow().getAttributes().flags & 2048) != 0));
                try {
                    hostActivity.startActivity(intent);
                    n = true;
                    return;
                } catch (ActivityNotFoundException e2) {
                    throw new RuntimeException("Chartboost impression activity not declared in manifest. Please add the following inside your manifest's <application> tag: \n<activity android:name=\"com.chartboost.sdk.CBImpressionActivity\" android:theme=\"@android:style/Theme.Translucent.NoTitleBar\" android:excludeFromRecents=\"true\" />");
                }
            }
        }
        e eVarG2 = g();
        if (eVarG2 != null && p()) {
            eVarG2.a(aVar);
        } else {
            aVar.a(CBError.CBImpressionError.NO_HOST_ACTIVITY);
        }
    }

    protected static Activity getHostActivity() {
        if (b != null) {
            return (Activity) b.get();
        }
        return null;
    }

    protected static void a(Runnable runnable) {
        if (!CBUtility.b()) {
            a.post(runnable);
        } else {
            runnable.run();
        }
    }

    protected static Context getValidContext() {
        return b != null ? b.b() : b.l();
    }

    private static void b(k kVar, boolean z) {
    }

    private static class a implements Runnable {
        private int a;
        private int b;
        private int c;

        private com.chartboost.sdk.a a() {
            return b.d();
        }

        private a() {
            com.chartboost.sdk.a aVarA = a();
            this.a = Chartboost.d == null ? -1 : Chartboost.d.hashCode();
            this.b = Chartboost.b == null ? -1 : Chartboost.b.hashCode();
            this.c = aVarA != null ? aVarA.hashCode() : -1;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.chartboost.sdk.a aVarA = a();
            if (b.l() != null) {
                Chartboost.clearCache();
            }
            if (Chartboost.b != null && Chartboost.b.hashCode() == this.b) {
                Chartboost.b = null;
            }
            if (Chartboost.d != null && Chartboost.d.hashCode() == this.a) {
                CBImpressionActivity unused = Chartboost.d = null;
            }
            if (aVarA != null && aVarA.hashCode() == this.c) {
                b.a((com.chartboost.sdk.a) null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean p() {
        return d(b);
    }

    protected static boolean d(k kVar) {
        Boolean boolValueOf;
        if (kVar == null || (boolValueOf = Boolean.valueOf(k.get(kVar.a()))) == null) {
            return false;
        }
        return boolValueOf.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Activity activity, boolean z) {
        if (activity != null) {
            a(activity.hashCode(), z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(k kVar, boolean z) {
        if (kVar != null) {
            a(kVar.a(), z);
        }
    }

    private static void a(int i2, boolean z) {
        k.put(i2, z);
    }

    protected static e g() {
        if (e() == null) {
            return null;
        }
        return l;
    }

    public static boolean getImpressionsUseActivities() {
        return b.e();
    }

    public static void setImpressionsUseActivities(boolean impressionsUseActivities) {
        b.a(impressionsUseActivities);
    }

    public static void createSurfaceView(final Activity activity) {
        if (!bh.a(activity)) {
            a.post(new Runnable() { // from class: com.chartboost.sdk.Chartboost.4
                @Override // java.lang.Runnable
                public void run() {
                    CBLogging.e("VideoInit", "preparing activity for video surface");
                    activity.addContentView(new SurfaceView(activity), new ViewGroup.LayoutParams(0, 0));
                }
            });
        }
    }

    private static void showInterstitialAIR(String location, boolean show) {
        ae.f().b(location, show);
    }

    private static void showMoreAppsAIR(String location, boolean show) {
        aw.f().b(location, show);
    }

    private static void showRewardedVideoAIR(String location, boolean show) {
        af.h().b(location, show);
    }
}
