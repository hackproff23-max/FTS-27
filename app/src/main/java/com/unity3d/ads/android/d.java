package com.unity3d.ads.android;

import com.unity3d.ads.android.webapp.UnityAdsWebData;

/* JADX INFO: compiled from: UnityAds.java */
/* JADX INFO: loaded from: classes.dex */
final class d implements Runnable {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        UnityAds.mainview.openAds(this.a.a, this.a.b);
        if (UnityAdsWebData.getZoneManager().getCurrentZone().noOfferScreen()) {
            UnityAds.a(0L);
        }
        if (UnityAds.q != null) {
            UnityAds.q.onShow();
        }
    }
}
