package com.unity3d.ads.android;

import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import com.unity3d.ads.android.view.UnityAdsMainView;
import org.json.JSONObject;

/* JADX INFO: compiled from: UnityAds.java */
/* JADX INFO: loaded from: classes.dex */
final class k implements Runnable {
    private k() {
    }

    /* synthetic */ k(byte b) {
        this();
    }

    @Override // java.lang.Runnable
    public final void run() {
        UnityAdsDeviceLog.entered();
        if (UnityAdsProperties.SELECTED_CAMPAIGN != null) {
            UnityAdsDeviceLog.debug("Selected campaign found");
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(UnityAdsConstants.UNITY_ADS_TEXTKEY_KEY, UnityAdsConstants.UNITY_ADS_TEXTKEY_BUFFERING);
                UnityAds.mainview.webview.sendNativeEventToWebApp(UnityAdsConstants.UNITY_ADS_NATIVEEVENT_SHOWSPINNER, jSONObject);
                UnityAds.p();
                String videoStreamUrl = UnityAdsUtils.getCacheDirectory() + "/" + UnityAdsProperties.SELECTED_CAMPAIGN.getVideoFilename();
                if (!UnityAdsUtils.isFileInCache(UnityAdsProperties.SELECTED_CAMPAIGN.getVideoFilename())) {
                    videoStreamUrl = UnityAdsProperties.SELECTED_CAMPAIGN.getVideoStreamUrl();
                }
                UnityAds.mainview.setViewState(UnityAdsMainView.UnityAdsMainViewState.VideoPlayer);
                UnityAdsDeviceLog.debug("Start videoplayback with: " + videoStreamUrl);
                UnityAds.mainview.videoplayerview.playVideo(videoStreamUrl);
                return;
            } catch (Exception e) {
                UnityAdsDeviceLog.error("Couldn't create data JSON");
                return;
            }
        }
        UnityAdsDeviceLog.error("Campaign is null");
    }
}
