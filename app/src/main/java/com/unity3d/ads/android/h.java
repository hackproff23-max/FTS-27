package com.unity3d.ads.android;

import com.unity3d.ads.android.properties.UnityAdsConstants;
import org.json.JSONObject;

/* JADX INFO: compiled from: UnityAds.java */
/* JADX INFO: loaded from: classes.dex */
final class h implements Runnable {
    h() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!UnityAds.i) {
            UnityAds.m();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("action", UnityAdsConstants.UNITY_ADS_WEBVIEW_API_CLOSE);
                if (UnityAds.mainview != null && UnityAds.mainview.webview != null) {
                    UnityAds.mainview.webview.setWebViewCurrentView("none", jSONObject);
                }
                if (UnityAds.mainview != null) {
                    UnityAds.mainview.closeAds(jSONObject);
                    UnityAds.mainview = null;
                }
                UnityAds.n();
                if (UnityAds.q != null) {
                    UnityAds.q.onHide();
                }
                UnityAds.o();
            } catch (Exception e) {
            }
        }
    }
}
