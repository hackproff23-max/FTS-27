package com.applovin.impl.sdk;

import android.content.Context;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinLogger;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.ServerProtocol;
import com.unity3d.ads.android.properties.UnityAdsConstants;

/* JADX INFO: loaded from: classes.dex */
class bm implements Runnable {
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private final Context c;

    bm(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
        this.c = appLovinSdkImpl.getApplicationContext();
        this.b = appLovinSdkImpl.getLogger();
    }

    private void c() {
        String str = (String) this.a.a(bb.J);
        e eVar = (e) this.a.getAdService();
        if (str.length() > 0) {
            String[] strArrSplit = str.split(",");
            for (String str2 : strArrSplit) {
                eVar.a(AppLovinAdSize.fromString(str2), AppLovinAdType.REGULAR);
            }
        }
        if (((Boolean) this.a.a(bb.K)).booleanValue()) {
            eVar.a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.INCENTIVIZED);
        }
    }

    boolean a() {
        if (q.a("android.permission.INTERNET", this.c)) {
            return true;
        }
        this.b.userError("TaskInitializeSdk", "Unable to enable AppLovin SDK: no android.permission.INTERNET");
        return false;
    }

    void b() {
        if (cd.a(bb.m, this.a)) {
            this.a.a().a(new bi(this.a), bo.BACKGROUND, 1500L);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.b.d("TaskInitializeSdk", "Initializing AppLovin SDK 5.4.3...");
        try {
            if (a()) {
                bg bgVarB = this.a.b();
                bgVarB.c();
                bgVarB.c("ad_imp_session");
                a.b(this.a);
                this.a.getFileManager().d(this.c);
                b();
                c();
                if (((String) this.a.a(bb.P)).equals("unknown")) {
                    this.a.getSettingsManager().a(bb.P, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                }
                this.a.a(true);
            } else {
                this.a.a(false);
            }
        } catch (Throwable th) {
            this.b.e("TaskInitializeSdk", "Unable to intialize SDK, disabling the SDK", th);
            this.a.a(false);
        } finally {
            this.b.d("TaskInitializeSdk", "AppLovin SDK 5.4.3 initialization " + (this.a.isEnabled() ? AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED : UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_VIDEOCACHING_FAILED) + " in " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms");
        }
    }
}
