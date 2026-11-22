package com.unity3d.ads.android.webapp;

import com.unity3d.ads.android.UnityAdsDeviceLog;

/* JADX INFO: compiled from: UnityAdsWebData.java */
/* JADX INFO: loaded from: classes.dex */
final class g implements Runnable {
    final /* synthetic */ UnityAdsWebData a;
    private String b;
    private String c;
    private String d;
    private e e;
    private int f;

    public g(UnityAdsWebData unityAdsWebData, String str, String str2, String str3, e eVar, int i) {
        this.a = unityAdsWebData;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = 0;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = eVar;
        this.f = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        f fVar = new f(this.a, this.b, this.c, this.d, this.e, this.f);
        UnityAdsDeviceLog.debug("URL: " + fVar.getUrl());
        if (this.f <= 5) {
            UnityAdsWebData.a(this.a, fVar);
        }
        this.a.a();
    }
}
