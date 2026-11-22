package com.flurry.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import com.flurry.android.AdCreative;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class bz extends bj {
    @Override // com.flurry.sdk.bj
    protected String f() {
        return "Millennial Media";
    }

    @Override // com.flurry.sdk.bj
    protected List<bf> g() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new bf("MMAdView", "5.1.0", "com.millennialmedia.android.MMInterstitial"));
        return arrayList;
    }

    @Override // com.flurry.sdk.bj
    @TargetApi(13)
    protected List<ActivityInfo> j() {
        ArrayList arrayList = new ArrayList();
        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.name = "com.millennialmedia.android.MMActivity";
        activityInfo.configChanges = 3248;
        arrayList.add(activityInfo);
        return arrayList;
    }

    @Override // com.flurry.sdk.bj
    protected List<String> h() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("com.flurry.millennial.MYAPIDINTERSTITIAL");
        return arrayList;
    }

    @Override // com.flurry.sdk.bj
    protected List<bf> k() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new bf("MMAdView", "5.1.0", "com.millennialmedia.android.MMAdView"));
        return arrayList;
    }

    @Override // com.flurry.sdk.bj
    protected List<String> l() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("com.flurry.millennial.MYAPID");
        arrayList.add("com.flurry.millennial.MYAPIDRECTANGLE");
        return arrayList;
    }

    @Override // com.flurry.sdk.bj
    protected List<String> n() {
        return new ArrayList();
    }

    @Override // com.flurry.sdk.bj
    protected List<String> o() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("android.permission.INTERNET");
        arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        arrayList.add("android.permission.ACCESS_NETWORK_STATE");
        return arrayList;
    }

    @Override // com.flurry.sdk.bj
    protected en a(Context context, r rVar, Bundle bundle) {
        if (context == null || rVar == null || bundle == null) {
            return null;
        }
        return new cc(context, rVar, bundle);
    }

    @Override // com.flurry.sdk.bj
    protected ec a(Context context, r rVar, AdCreative adCreative, Bundle bundle) {
        if (context == null || rVar == null || adCreative == null || bundle == null) {
            return null;
        }
        return new ca(context, rVar, adCreative, bundle);
    }
}
