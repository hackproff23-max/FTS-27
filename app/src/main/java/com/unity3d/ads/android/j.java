package com.unity3d.ads.android;

import android.app.Activity;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import com.unity3d.ads.android.view.UnityAdsFullscreenActivity;
import com.unity3d.ads.android.webapp.UnityAdsWebData;

/* JADX INFO: compiled from: UnityAds.java */
/* JADX INFO: loaded from: classes.dex */
final class j implements Runnable {
    private j() {
    }

    /* synthetic */ j(byte b) {
        this();
    }

    @Override // java.lang.Runnable
    public final void run() {
        Activity currentActivity;
        if (UnityAdsProperties.getCurrentActivity() != null && (UnityAdsProperties.getCurrentActivity() instanceof UnityAdsFullscreenActivity) && (currentActivity = UnityAdsProperties.getCurrentActivity()) != null && (currentActivity instanceof UnityAdsFullscreenActivity) && !currentActivity.isFinishing() && !UnityAdsProperties.isActivityDestroyed(currentActivity)) {
            currentActivity.finish();
            if (UnityAdsWebData.getZoneManager() != null && !UnityAdsWebData.getZoneManager().getCurrentZone().openAnimated()) {
                currentActivity.overridePendingTransition(0, 0);
            }
        }
        UnityAds.u();
    }
}
