package com.applovin.impl.adview;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewDatabase;
import android.widget.RelativeLayout;
import com.applovin.adview.AdViewController;
import com.applovin.impl.sdk.bh;
import com.applovin.impl.sdk.cd;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public class AdViewControllerImpl implements AdViewController {
    private Activity a;
    private AppLovinSdk b;
    private AppLovinAdService c;
    private AppLovinLogger d;
    private AppLovinAdSize e;
    private r f;
    private l g;
    private o h;
    private AppLovinAd i;
    private Runnable j;
    private Runnable k;
    private Runnable l;
    private volatile AppLovinAd m = null;
    private final AtomicReference n = new AtomicReference();
    private volatile boolean o = false;
    private volatile boolean p = true;
    private volatile boolean q = false;
    private volatile boolean r = false;
    private volatile AppLovinAdLoadListener s;
    private volatile AppLovinAdDisplayListener t;
    private volatile AppLovinAdVideoPlaybackListener u;
    private volatile AppLovinAdClickListener v;

    private void a(ViewGroup viewGroup, AppLovinSdk appLovinSdk, AppLovinAdSize appLovinAdSize, Context context) {
        a aVar = null;
        if (viewGroup == null) {
            throw new IllegalArgumentException("No parent view specified");
        }
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        if (appLovinAdSize == null) {
            throw new IllegalArgumentException("No ad size specified");
        }
        if (!(context instanceof Activity)) {
            throw new IllegalArgumentException("Specified context is not an activity");
        }
        this.b = appLovinSdk;
        this.c = appLovinSdk.getAdService();
        this.d = appLovinSdk.getLogger();
        this.e = appLovinAdSize;
        this.a = (Activity) context;
        this.i = cd.a();
        this.f = new r(this, appLovinSdk);
        this.l = new f(this, aVar);
        this.j = new k(this, aVar);
        this.k = new i(this, aVar);
        this.g = new l(this, appLovinSdk);
        if (!a(context)) {
            this.d.userError("AppLovinAdView", "Web view database is corrupt, AdView not loaded");
            return;
        }
        this.h = b();
        viewGroup.setBackgroundColor(0);
        viewGroup.addView(this.h);
        b(this.h, appLovinAdSize);
        this.h.setVisibility(8);
        a(new j(this, aVar));
        this.o = true;
    }

    private void a(Runnable runnable) {
        this.a.runOnUiThread(runnable);
    }

    private static boolean a(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                return true;
            }
            WebViewDatabase webViewDatabase = WebViewDatabase.getInstance(context);
            Method declaredMethod = WebViewDatabase.class.getDeclaredMethod("getCacheTotalSize", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(webViewDatabase, new Object[0]);
            return true;
        } catch (IllegalAccessException e) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", e);
            return true;
        } catch (IllegalArgumentException e2) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", e2);
            return true;
        } catch (NoSuchMethodException e3) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", e3);
            return true;
        } catch (InvocationTargetException e4) {
            Log.e("AppLovinAdView", "getCacheTotalSize() reported exception", e4);
            return false;
        } catch (Throwable th) {
            Log.e("AppLovinAdView", "Unexpected error while checking DB state", th);
            return false;
        }
    }

    private o b() {
        o oVar = new o(this.f, this.b, this.a);
        oVar.setBackgroundColor(0);
        oVar.setWillNotCacheDrawing(false);
        return oVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(View view, AppLovinAdSize appLovinAdSize) {
        int iApplyDimension;
        int iApplyDimension2;
        DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
        if (appLovinAdSize.getLabel().equals(AppLovinAdSize.INTERSTITIAL.getLabel())) {
            iApplyDimension = -1;
        } else {
            iApplyDimension = appLovinAdSize.getWidth() == -1 ? displayMetrics.widthPixels : (int) TypedValue.applyDimension(1, appLovinAdSize.getWidth(), displayMetrics);
        }
        if (appLovinAdSize.getLabel().equals(AppLovinAdSize.INTERSTITIAL.getLabel())) {
            iApplyDimension2 = -1;
        } else {
            iApplyDimension2 = appLovinAdSize.getHeight() == -1 ? displayMetrics.heightPixels : (int) TypedValue.applyDimension(1, appLovinAdSize.getHeight(), displayMetrics);
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        }
        layoutParams.width = iApplyDimension;
        layoutParams.height = iApplyDimension2;
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.addRule(10);
            layoutParams2.addRule(9);
        }
        view.setLayoutParams(layoutParams);
    }

    void a() {
        a(this.l);
        a(new h(this, this.m));
        this.m = null;
    }

    void a(int i) {
        if (!this.q) {
            this.c.addAdUpdateListener(this.g, this.e);
            a(this.l);
        }
        a(new b(this, i));
    }

    void a(AppLovinAd appLovinAd) {
        new com.applovin.impl.sdk.b(this.c).a(appLovinAd);
        a(new g(this, appLovinAd));
    }

    void b(AppLovinAd appLovinAd) {
        if (appLovinAd == null) {
            this.d.e("AppLovinAdView", "No provided when to the view controller");
            a(-1);
            return;
        }
        this.r = true;
        if (this.q) {
            this.n.set(appLovinAd);
            this.d.d("AppLovinAdView", "Ad view has paused when an ad was recieved, ad saved for later");
        } else {
            this.c.addAdUpdateListener(this.g, this.e);
            renderAd(appLovinAd);
        }
        a(new a(this, appLovinAd));
    }

    @Override // com.applovin.adview.AdViewController
    public void destroy() {
        if (this.c != null) {
            this.c.removeAdUpdateListener(this.g, getSize());
        }
        if (this.h != null) {
            try {
                this.h.removeAllViews();
                this.h.destroy();
            } catch (Throwable th) {
                this.d.w("AppLovinAdView", "Unable to destroy ad view", th);
            }
        }
        this.q = true;
    }

    @Override // com.applovin.adview.AdViewController
    public AppLovinAdSize getSize() {
        return this.e;
    }

    @Override // com.applovin.adview.AdViewController
    public void initializeAdView(ViewGroup viewGroup, Context context, AppLovinAdSize appLovinAdSize, AppLovinSdk appLovinSdk, AttributeSet attributeSet) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("No parent view specified");
        }
        if (context == null) {
            Log.e(AppLovinLogger.SDK_TAG, "Unable to create AppLovinAdView: no context provided. Please use a different constructor for this view.");
            return;
        }
        if (appLovinAdSize == null && (appLovinAdSize = m.a(attributeSet)) == null) {
            appLovinAdSize = AppLovinAdSize.BANNER;
        }
        if (appLovinSdk == null) {
            appLovinSdk = AppLovinSdk.getInstance(context);
        }
        if (appLovinSdk == null || appLovinSdk.hasCriticalErrors()) {
            return;
        }
        a(viewGroup, appLovinSdk, appLovinAdSize, context);
        if (m.b(attributeSet)) {
            loadNextAd();
        }
    }

    @Override // com.applovin.adview.AdViewController
    public boolean isAdReadyToDisplay() {
        return this.b.getAdService().hasPreloadedAd(this.e);
    }

    @Override // com.applovin.adview.AdViewController
    public boolean isAutoDestroy() {
        return this.p;
    }

    @Override // com.applovin.adview.AdViewController
    public void loadNextAd() {
        if (this.b == null || this.g == null || this.a == null || !this.o) {
            Log.i(AppLovinLogger.SDK_TAG, "Unable to load next ad: AppLovinAdView is not initialized.");
        } else {
            this.c.loadNextAd(this.e, this.g);
        }
    }

    public void onAdHtmlLoaded(WebView webView) {
        if (this.m != null) {
            webView.setVisibility(0);
            try {
                if (this.t != null) {
                    this.t.adDisplayed(this.m);
                }
            } catch (Throwable th) {
                this.d.userError("AppLovinAdView", "Exception while notifying ad display listener", th);
            }
        }
    }

    @Override // com.applovin.adview.AdViewController
    public void onDetachedFromWindow() {
        if (this.o) {
            a(new h(this, this.m));
            if (this.p) {
                destroy();
            }
        }
    }

    @Override // com.applovin.adview.AdViewController
    public void onVisibilityChanged(int i) {
        if (this.o && this.p) {
            if (i == 8 || i == 4) {
                pause();
            } else if (i == 0) {
                resume();
            }
        }
    }

    @Override // com.applovin.adview.AdViewController
    public void pause() {
        if (this.o) {
            this.c.removeAdUpdateListener(this.g, getSize());
            AppLovinAd appLovinAd = this.m;
            renderAd(this.i);
            if (appLovinAd != null) {
                this.n.set(appLovinAd);
            }
            this.q = true;
        }
    }

    @Override // com.applovin.adview.AdViewController
    public void renderAd(AppLovinAd appLovinAd) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        if (!this.o) {
            Log.i(AppLovinLogger.SDK_TAG, "Unable to render ad: AppLovinAdView is not initialized.");
            return;
        }
        if (appLovinAd == this.m) {
            this.d.w("AppLovinAdView", "Ad # " + appLovinAd.getAdIdNumber() + " is already showing, ignoring");
            return;
        }
        this.d.d("AppLovinAdView", "Rendering ad # " + appLovinAd.getAdIdNumber() + " (" + appLovinAd.getSize() + ")");
        a(new h(this, this.m));
        this.n.set(null);
        this.m = appLovinAd;
        if (appLovinAd.getSize() == this.e) {
            a(this.j);
        } else if (appLovinAd.getSize() == AppLovinAdSize.INTERSTITIAL) {
            a(this.l);
            a(this.k);
        }
        new bh(this.b).a();
    }

    @Override // com.applovin.adview.AdViewController
    public void resume() {
        if (this.o) {
            if (this.r) {
                this.c.addAdUpdateListener(this.g, this.e);
            }
            AppLovinAd appLovinAd = (AppLovinAd) this.n.getAndSet(null);
            if (appLovinAd != null) {
                renderAd(appLovinAd);
            }
            this.q = false;
        }
    }

    @Override // com.applovin.adview.AdViewController
    public void setAdClickListener(AppLovinAdClickListener appLovinAdClickListener) {
        this.v = appLovinAdClickListener;
    }

    @Override // com.applovin.adview.AdViewController
    public void setAdDisplayListener(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.t = appLovinAdDisplayListener;
    }

    @Override // com.applovin.adview.AdViewController
    public void setAdLoadListener(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.s = appLovinAdLoadListener;
    }

    @Override // com.applovin.adview.AdViewController
    public void setAdVideoPlaybackListener(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
        this.u = appLovinAdVideoPlaybackListener;
    }

    @Override // com.applovin.adview.AdViewController
    public void setAutoDestroy(boolean z) {
        this.p = z;
    }
}
