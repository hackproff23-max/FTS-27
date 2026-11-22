package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinLogger;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
class bn {
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private final ScheduledExecutorService c = a("main");
    private final ScheduledExecutorService d = a(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_VIDEOABORT_BACK);

    bn(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
        this.b = appLovinSdkImpl.getLogger();
    }

    private static void a(Runnable runnable, long j, ScheduledExecutorService scheduledExecutorService) {
        if (j > 0) {
            scheduledExecutorService.schedule(runnable, j, TimeUnit.MILLISECONDS);
        } else {
            scheduledExecutorService.submit(runnable);
        }
    }

    ScheduledExecutorService a(String str) {
        return Executors.newScheduledThreadPool(1, new bp(this, str));
    }

    void a(ba baVar, bo boVar) {
        a(baVar, boVar, 0L);
    }

    void a(ba baVar, bo boVar, long j) {
        if (baVar == null) {
            throw new IllegalArgumentException("No task specified");
        }
        if (j < 0) {
            throw new IllegalArgumentException("Invalid delay specified: " + j);
        }
        this.b.d(baVar.e, "Scheduling " + baVar.e + " on " + boVar + " queue in " + j + "ms.");
        br brVar = new br(this, baVar, boVar);
        if (boVar == bo.MAIN) {
            a(brVar, j, this.c);
        } else {
            a(brVar, j, this.d);
        }
    }

    void a(bm bmVar, long j) {
        if (bmVar == null) {
            throw new IllegalArgumentException("No task specified");
        }
        a(bmVar, j, this.c);
    }
}
