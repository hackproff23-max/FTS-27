package com.flurry.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import com.flurry.android.AdCreative;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class bq extends bj {
    @Override // com.flurry.sdk.bj
    protected String f() {
        return "Facebook Audience Network";
    }

    @Override // com.flurry.sdk.bj
    protected List<bf> g() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new bf("AudienceNetwork", "3.14.+", "com.facebook.ads.InterstitialAd"));
        return arrayList;
    }

    @Override // com.flurry.sdk.bj
    @TargetApi(13)
    protected List<ActivityInfo> j() {
        ArrayList arrayList = new ArrayList();
        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.name = "com.facebook.ads.InterstitialAdActivity";
        activityInfo.configChanges = 4016;
        arrayList.add(activityInfo);
        return arrayList;
    }

    @Override // com.flurry.sdk.bj
    protected List<bf> k() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new bf("AudienceNetwork", "3.14.+", "com.facebook.ads.Ad"));
        return arrayList;
    }

    @Override // com.flurry.sdk.bj
    protected List<String> n() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("com.flurry.fan.MY_APP_ID");
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
        return new br(context, rVar, bundle);
    }

    @Override // com.flurry.sdk.bj
    protected ec a(Context context, r rVar, AdCreative adCreative, Bundle bundle) {
        if (context == null || rVar == null || adCreative == null || bundle == null) {
            return null;
        }
        return new bp(context, rVar, adCreative, bundle);
    }
}
