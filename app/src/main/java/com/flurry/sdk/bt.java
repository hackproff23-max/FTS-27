package com.flurry.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import com.flurry.android.AdCreative;
import com.google.android.gms.ads.AdActivity;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class bt extends bj {
    @Override // com.flurry.sdk.bj
    protected String f() {
        return "AdMob";
    }

    @Override // com.flurry.sdk.bj
    protected List<bf> g() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new bf("GooglePlayServicesSdk", "4.0.+", "com.google.android.gms.ads.InterstitialAd"));
        return arrayList;
    }

    @Override // com.flurry.sdk.bj
    @TargetApi(13)
    protected List<ActivityInfo> j() {
        ArrayList arrayList = new ArrayList();
        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.name = AdActivity.CLASS_NAME;
        activityInfo.configChanges = 4016;
        arrayList.add(activityInfo);
        return arrayList;
    }

    @Override // com.flurry.sdk.bj
    protected List<bf> k() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new bf("GooglePlayServicesSdk", "4.0.+", "com.google.android.gms.ads.AdView"));
        return arrayList;
    }

    @Override // com.flurry.sdk.bj
    protected List<String> n() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("com.flurry.gms.ads.MY_AD_UNIT_ID");
        return arrayList;
    }

    @Override // com.flurry.sdk.bj
    protected List<String> o() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("android.permission.INTERNET");
        arrayList.add("android.permission.ACCESS_NETWORK_STATE");
        return arrayList;
    }

    @Override // com.flurry.sdk.bj
    protected en a(Context context, r rVar, Bundle bundle) {
        if (context == null || rVar == null || bundle == null) {
            return null;
        }
        return new bu(context, rVar, bundle);
    }

    @Override // com.flurry.sdk.bj
    protected ec a(Context context, r rVar, AdCreative adCreative, Bundle bundle) {
        if (context == null || rVar == null || adCreative == null || bundle == null) {
            return null;
        }
        return new bs(context, rVar, adCreative, bundle);
    }
}
