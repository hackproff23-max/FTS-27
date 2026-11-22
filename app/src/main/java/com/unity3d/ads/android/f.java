package com.unity3d.ads.android;

import android.app.Activity;
import android.os.PowerManager;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import java.util.TimerTask;
import org.json.JSONObject;

/* JADX INFO: compiled from: UnityAds.java */
/* JADX INFO: loaded from: classes.dex */
final class f extends TimerTask {
    f() {
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        Activity currentActivity = UnityAdsProperties.getCurrentActivity();
        if (currentActivity != null && !((PowerManager) currentActivity.getBaseContext().getSystemService("power")).isScreenOn()) {
            if (UnityAds.mainview != null && UnityAds.mainview.webview != null && UnityAds.mainview.webview.isWebAppLoaded()) {
                UnityAds.mainview.webview.sendNativeEventToWebApp(UnityAdsConstants.UNITY_ADS_NATIVEEVENT_HIDESPINNER, new JSONObject());
                UnityAds.q();
            }
            UnityAds.s();
        }
    }
}
