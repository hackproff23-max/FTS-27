package com.applovin.impl.sdk;

import android.os.PowerManager;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdUpdateListener;
import com.applovin.sdk.AppLovinLogger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class e implements AppLovinAdService {
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private final aw c;
    private final Map d;

    e(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = appLovinSdkImpl;
        this.b = appLovinSdkImpl.getLogger();
        this.c = new aw(appLovinSdkImpl);
        this.d = new HashMap(2);
        Iterator it = AppLovinAdType.allTypes().iterator();
        while (it.hasNext()) {
            this.d.put((AppLovinAdType) it.next(), new HashMap());
        }
        ((Map) this.d.get(AppLovinAdType.REGULAR)).put(AppLovinAdSize.BANNER, new h(AppLovinAdSize.BANNER));
        ((Map) this.d.get(AppLovinAdType.REGULAR)).put(AppLovinAdSize.MREC, new h(AppLovinAdSize.MREC));
        ((Map) this.d.get(AppLovinAdType.REGULAR)).put(AppLovinAdSize.INTERSTITIAL, new h(AppLovinAdSize.INTERSTITIAL));
        ((Map) this.d.get(AppLovinAdType.REGULAR)).put(AppLovinAdSize.LEADER, new h(AppLovinAdSize.LEADER));
        ((Map) this.d.get(AppLovinAdType.INCENTIVIZED)).put(AppLovinAdSize.INTERSTITIAL, new h(AppLovinAdSize.INTERSTITIAL));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a() {
        return ((PowerManager) this.a.getApplicationContext().getSystemService("power")).isScreenOn();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(AppLovinAdSize appLovinAdSize) {
        if (appLovinAdSize == AppLovinAdSize.BANNER) {
            return ((Boolean) this.a.a(bb.z)).booleanValue();
        }
        if (appLovinAdSize == AppLovinAdSize.MREC) {
            return ((Boolean) this.a.a(bb.B)).booleanValue();
        }
        if (appLovinAdSize == AppLovinAdSize.LEADER) {
            return ((Boolean) this.a.a(bb.D)).booleanValue();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long b(AppLovinAdSize appLovinAdSize) {
        if (appLovinAdSize == AppLovinAdSize.BANNER) {
            return ((Long) this.a.a(bb.A)).longValue();
        }
        if (appLovinAdSize == AppLovinAdSize.MREC) {
            return ((Long) this.a.a(bb.C)).longValue();
        }
        if (appLovinAdSize == AppLovinAdSize.LEADER) {
            return ((Long) this.a.a(bb.E)).longValue();
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, AppLovinAdLoadListener appLovinAdLoadListener) {
        AppLovinAd appLovinAdE = this.c.e(appLovinAdSize, appLovinAdType);
        if (appLovinAdE != null) {
            this.b.d("AppLovinAdService", "Using pre-loaded ad: " + appLovinAdE + " for size " + appLovinAdSize + " and type " + appLovinAdType);
            appLovinAdLoadListener.adReceived(appLovinAdE);
        } else {
            this.a.a().a(new bk(appLovinAdSize, appLovinAdType, appLovinAdLoadListener, this.a), bo.MAIN);
        }
        this.c.a(appLovinAdSize, appLovinAdType);
    }

    private boolean b(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType) {
        if (!((Boolean) this.a.a(bb.G)).booleanValue()) {
            return false;
        }
        if (appLovinAdType.equals(AppLovinAdType.INCENTIVIZED)) {
            return ((Boolean) this.a.a(bb.aD)).booleanValue() && ((Boolean) this.a.a(bb.K)).booleanValue();
        }
        if (appLovinAdType.equals(AppLovinAdType.REGULAR)) {
            return ((Boolean) this.a.a(bb.aE)).booleanValue() && ((String) this.a.a(bb.J)).contains(AppLovinAdSize.INTERSTITIAL.getLabel());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(AppLovinAdSize appLovinAdSize) {
        long jB = b(appLovinAdSize);
        if (jB > 0) {
            this.a.a().a(new i(this, appLovinAdSize), bo.MAIN, (jB + 2) * 1000);
        }
    }

    public void a(AppLovinAd appLovinAd) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        AppLovinAdInternal appLovinAdInternal = (AppLovinAdInternal) appLovinAd;
        h hVar = (h) ((Map) this.d.get(appLovinAdInternal.getType())).get(appLovinAdInternal.getSize());
        synchronized (hVar.b) {
            hVar.c = null;
            hVar.d = 0L;
        }
    }

    public void a(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType) {
        this.c.c(appLovinAdSize, appLovinAdType);
    }

    public void a(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, AppLovinAdLoadListener appLovinAdLoadListener) {
        AppLovinAd appLovinAd;
        if (appLovinAdSize == null) {
            throw new IllegalArgumentException("No ad size specified");
        }
        if (appLovinAdLoadListener == null) {
            throw new IllegalArgumentException("No callback specified");
        }
        if (appLovinAdType == null) {
            throw new IllegalArgumentException("No ad type specificed");
        }
        h hVar = (h) ((Map) this.d.get(appLovinAdType)).get(appLovinAdSize);
        synchronized (hVar.b) {
            boolean z = System.currentTimeMillis() > hVar.d;
            if (hVar.c == null || z) {
                this.b.d("AppLovinAdService", "Loading next ad...");
                hVar.g.add(appLovinAdLoadListener);
                if (hVar.e) {
                    appLovinAd = null;
                } else {
                    hVar.e = true;
                    g gVar = new g(this, (h) ((Map) this.d.get(appLovinAdType)).get(appLovinAdSize));
                    if (!b(appLovinAdSize, appLovinAdType)) {
                        this.b.d("AppLovinAdService", "Task merge not necessary.");
                        b(appLovinAdSize, appLovinAdType, gVar);
                        appLovinAd = null;
                    } else if (this.c.b(appLovinAdSize, appLovinAdType, gVar)) {
                        this.b.d("AppLovinAdService", "Attaching load listener to initial preload task...");
                        appLovinAd = null;
                    } else {
                        this.b.d("AppLovinAdService", "Skipped attach of initial preload callback.");
                        b(appLovinAdSize, appLovinAdType, gVar);
                        appLovinAd = null;
                    }
                }
            } else {
                appLovinAd = hVar.c;
            }
        }
        if (appLovinAd != null) {
            appLovinAdLoadListener.adReceived(appLovinAd);
        }
    }

    @Override // com.applovin.sdk.AppLovinAdService
    public void addAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener) {
        addAdUpdateListener(appLovinAdUpdateListener, AppLovinAdSize.BANNER);
    }

    @Override // com.applovin.sdk.AppLovinAdService
    public void addAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize appLovinAdSize) {
        boolean z;
        if (appLovinAdUpdateListener == null) {
            throw new IllegalArgumentException("No ad listener specified");
        }
        h hVar = (h) ((Map) this.d.get(AppLovinAdType.REGULAR)).get(appLovinAdSize);
        synchronized (hVar.b) {
            if (hVar.f.contains(appLovinAdUpdateListener)) {
                z = false;
            } else {
                hVar.f.add(appLovinAdUpdateListener);
                z = true;
                this.b.d("AppLovinAdService", "Added update listener: " + appLovinAdUpdateListener);
            }
        }
        if (z) {
            this.a.a().a(new i(this, appLovinAdSize), bo.MAIN);
        }
    }

    @Override // com.applovin.sdk.AppLovinAdService
    public boolean hasPreloadedAd(AppLovinAdSize appLovinAdSize) {
        return this.c.b(appLovinAdSize, AppLovinAdType.REGULAR);
    }

    @Override // com.applovin.sdk.AppLovinAdService
    public void loadNextAd(AppLovinAdSize appLovinAdSize, AppLovinAdLoadListener appLovinAdLoadListener) {
        a(appLovinAdSize, AppLovinAdType.REGULAR, appLovinAdLoadListener);
    }

    @Override // com.applovin.sdk.AppLovinAdService
    public void preloadAd(AppLovinAdSize appLovinAdSize) {
        this.c.a(appLovinAdSize, AppLovinAdType.REGULAR);
    }

    @Override // com.applovin.sdk.AppLovinAdService
    public void removeAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize appLovinAdSize) {
        if (appLovinAdUpdateListener == null) {
            return;
        }
        h hVar = (h) ((Map) this.d.get(AppLovinAdType.REGULAR)).get(appLovinAdSize);
        synchronized (hVar.b) {
            hVar.f.remove(appLovinAdUpdateListener);
        }
        this.b.d("AppLovinAdService", "Removed update listener: " + appLovinAdUpdateListener);
    }
}
