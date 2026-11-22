package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinLogger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
class aw implements u {
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private final Object c = new Object();
    private final Map d = a();
    private final Map e = new HashMap();
    private final Set f = new HashSet();

    aw(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
        this.b = appLovinSdkImpl.getLogger();
    }

    private bd a(AppLovinAdType appLovinAdType, AppLovinAdSize appLovinAdSize) {
        if (appLovinAdType.equals(AppLovinAdType.INCENTIVIZED)) {
            return bb.an;
        }
        if (appLovinAdSize.equals(AppLovinAdSize.BANNER)) {
            return bb.aj;
        }
        if (appLovinAdSize.equals(AppLovinAdSize.MREC)) {
            return bb.ak;
        }
        if (appLovinAdSize.equals(AppLovinAdSize.INTERSTITIAL)) {
            return bb.al;
        }
        return appLovinAdSize.equals(AppLovinAdSize.LEADER) ? bb.am : bb.aj;
    }

    private Map a() {
        HashMap map = new HashMap(5);
        for (AppLovinAdSize appLovinAdSize : AppLovinAdSize.allSizes()) {
            map.put(new c(appLovinAdSize, AppLovinAdType.REGULAR), new ax(((Integer) this.a.a(a(AppLovinAdType.REGULAR, appLovinAdSize))).intValue()));
        }
        map.put(new c(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.INCENTIVIZED), new ax(((Integer) this.a.a(a(AppLovinAdType.INCENTIVIZED, AppLovinAdSize.INTERSTITIAL))).intValue()));
        return map;
    }

    private ax g(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType) {
        return (ax) this.d.get(new c(appLovinAdSize, appLovinAdType));
    }

    @Override // com.applovin.impl.sdk.u
    public void a(c cVar, int i) {
        AppLovinAdLoadListener appLovinAdLoadListener;
        this.b.d("PreloadManager", "Failed to pre-load an ad of spec " + cVar + ", error code " + i);
        synchronized (this.c) {
            appLovinAdLoadListener = (AppLovinAdLoadListener) this.e.remove(cVar);
            this.f.add(cVar);
        }
        if (appLovinAdLoadListener instanceof u) {
            ((u) appLovinAdLoadListener).a(cVar, i);
        } else {
            appLovinAdLoadListener.failedToReceiveAd(i);
        }
    }

    public void a(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType) {
        if (!((Boolean) this.a.a(bb.G)).booleanValue() || d(appLovinAdSize, appLovinAdType)) {
            return;
        }
        this.b.d("PreloadManager", "Preloading ad for size " + appLovinAdSize + "...");
        bk bkVar = new bk(appLovinAdSize, appLovinAdType, this, this.a);
        bkVar.a(true);
        this.a.a().a(bkVar, bo.BACKGROUND, 500L);
    }

    public void a(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, AppLovinAdLoadListener appLovinAdLoadListener) {
        synchronized (this.c) {
            c cVar = new c(appLovinAdSize, appLovinAdType);
            if (this.e.containsKey(cVar)) {
                this.b.w("PreloadManager", "Possibly missing prior registered preload callback.");
            }
            this.e.put(cVar, appLovinAdLoadListener);
        }
    }

    @Override // com.applovin.sdk.AppLovinAdLoadListener
    public void adReceived(AppLovinAd appLovinAd) {
        AppLovinAdLoadListener appLovinAdLoadListener;
        synchronized (this.c) {
            c cVar = new c(appLovinAd);
            appLovinAdLoadListener = (AppLovinAdLoadListener) this.e.get(cVar);
            this.e.remove(cVar);
            this.f.add(cVar);
            if (appLovinAdLoadListener == null) {
                g(appLovinAd.getSize(), appLovinAd.getType()).a(appLovinAd);
                this.b.d("PreloadManager", "Ad enqueued: " + appLovinAd);
            } else {
                this.b.d("PreloadManager", "Additional callback found, skipping enqueue.");
            }
        }
        if (appLovinAdLoadListener != null) {
            appLovinAdLoadListener.adReceived(appLovinAd);
            this.b.d("PreloadManager", "Called additional callback regarding " + appLovinAd);
        }
        this.b.d("PreloadManager", "Pulled ad from network and saved to preload cache: " + appLovinAd);
    }

    public boolean b(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType) {
        boolean z;
        synchronized (this.c) {
            z = !g(appLovinAdSize, appLovinAdType).d();
        }
        return z;
    }

    public boolean b(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, AppLovinAdLoadListener appLovinAdLoadListener) {
        boolean z;
        synchronized (this.c) {
            if (f(appLovinAdSize, appLovinAdType)) {
                z = false;
            } else {
                a(appLovinAdSize, appLovinAdType, appLovinAdLoadListener);
                z = true;
            }
        }
        return z;
    }

    public void c(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType) {
        int iB;
        synchronized (this.c) {
            ax axVarG = g(appLovinAdSize, appLovinAdType);
            iB = axVarG.b() - axVarG.a();
        }
        if (iB > 0) {
            for (int i = 0; i < iB; i++) {
                a(appLovinAdSize, appLovinAdType);
            }
        }
    }

    public boolean d(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType) {
        boolean zC;
        synchronized (this.c) {
            zC = g(appLovinAdSize, appLovinAdType).c();
        }
        return zC;
    }

    public AppLovinAd e(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType) {
        AppLovinAd appLovinAdE;
        synchronized (this.c) {
            appLovinAdE = g(appLovinAdSize, appLovinAdType).e();
        }
        return appLovinAdE;
    }

    boolean f(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType) {
        boolean zContains;
        c cVar = new c(appLovinAdSize, appLovinAdType);
        synchronized (this.c) {
            zContains = this.f.contains(cVar);
        }
        return zContains;
    }

    @Override // com.applovin.sdk.AppLovinAdLoadListener
    public void failedToReceiveAd(int i) {
    }
}
