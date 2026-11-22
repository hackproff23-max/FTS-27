package com.flurry.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import com.flurry.android.AdCreative;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class bv extends bj {
    @Override // com.flurry.sdk.bj
    protected String f() {
        return "InMobi";
    }

    @Override // com.flurry.sdk.bj
    protected List<bf> g() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new bf("InMobiAndroidSDK", "4.1.0", "com.inmobi.monetization.IMInterstitial"));
        return arrayList;
    }

    @Override // com.flurry.sdk.bj
    @TargetApi(13)
    protected List<ActivityInfo> j() {
        ArrayList arrayList = new ArrayList();
        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.name = "com.inmobi.androidsdk.IMBrowserActivity";
        activityInfo.configChanges = 3248;
        arrayList.add(activityInfo);
        return arrayList;
    }

    @Override // com.flurry.sdk.bj
    protected List<bf> k() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new bf("InMobiAndroidSDK", "4.1.0", "com.inmobi.monetization.IMBanner"));
        return arrayList;
    }

    @Override // com.flurry.sdk.bj
    protected List<String> n() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("com.flurry.inmobi.MY_APP_ID");
        return arrayList;
    }

    @Override // com.flurry.sdk.bj
    protected List<String> o() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("android.permission.INTERNET");
        return arrayList;
    }

    @Override // com.flurry.sdk.bj
    protected en a(Context context, r rVar, Bundle bundle) {
        if (context == null || rVar == null || bundle == null) {
            return null;
        }
        return new by(context, rVar, bundle);
    }

    @Override // com.flurry.sdk.bj
    protected ec a(Context context, r rVar, AdCreative adCreative, Bundle bundle) {
        if (context == null || rVar == null || adCreative == null || bundle == null) {
            return null;
        }
        return new bw(context, rVar, adCreative, bundle);
    }
}
