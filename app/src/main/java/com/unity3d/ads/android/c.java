package com.unity3d.ads.android;

import org.json.JSONObject;

/* JADX INFO: compiled from: UnityAds.java */
/* JADX INFO: loaded from: classes.dex */
final class c implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ JSONObject b;

    c(String str, JSONObject jSONObject) {
        this.a = str;
        this.b = jSONObject;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!UnityAds.mainview.webview.isWebAppLoadComplete()) {
            UnityAds.mainview.webview.waitForWebAppLoadComplete();
        }
        UnityAdsUtils.runOnUiThread(new d(this));
    }
}
