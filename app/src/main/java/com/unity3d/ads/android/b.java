package com.unity3d.ads.android;

import android.app.Activity;
import com.unity3d.ads.android.data.UnityAdsAdvertisingId;

/* JADX INFO: compiled from: UnityAds.java */
/* JADX INFO: loaded from: classes.dex */
final class b implements Runnable {
    final /* synthetic */ Activity a;

    b(Activity activity) {
        this.a = activity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        UnityAdsAdvertisingId.init(this.a);
        if (UnityAds.webdata.initCampaigns()) {
            UnityAds.b();
        }
    }
}
