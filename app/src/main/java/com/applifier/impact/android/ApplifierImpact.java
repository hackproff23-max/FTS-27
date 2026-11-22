package com.applifier.impact.android;

import android.app.Activity;
import android.os.Build;
import com.unity3d.ads.android.IUnityAdsListener;
import com.unity3d.ads.android.UnityAds;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ApplifierImpact implements IUnityAdsListener {
    public static final String APPLIFIER_IMPACT_OPTION_GAMERSID_KEY = "sid";
    public static final String APPLIFIER_IMPACT_OPTION_MUTE_VIDEO_SOUNDS = "muteVideoSounds";
    public static final String APPLIFIER_IMPACT_OPTION_NOOFFERSCREEN_KEY = "noOfferScreen";
    public static final String APPLIFIER_IMPACT_OPTION_OPENANIMATED_KEY = "openAnimated";
    public static final String APPLIFIER_IMPACT_OPTION_VIDEO_USES_DEVICE_ORIENTATION = "useDeviceOrientationForVideo";
    public static final String APPLIFIER_IMPACT_REWARDITEM_NAME_KEY = "name";
    public static final String APPLIFIER_IMPACT_REWARDITEM_PICTURE_KEY = "picture";
    public static ApplifierImpact instance = null;
    private IApplifierImpactListener a = null;

    public ApplifierImpact(Activity activity, String str) {
        a(activity, str, null);
    }

    public ApplifierImpact(Activity activity, String str, IApplifierImpactListener iApplifierImpactListener) {
        a(activity, str, iApplifierImpactListener);
    }

    private void a(Activity activity, String str, IApplifierImpactListener iApplifierImpactListener) {
        instance = this;
        setImpactListener(iApplifierImpactListener);
        UnityAds.init(activity, str, this);
    }

    public static boolean isSupported() {
        return Build.VERSION.SDK_INT >= 9;
    }

    public static void setDebugMode(boolean z) {
        UnityAds.setDebugMode(z);
    }

    public static void setTestMode(boolean z) {
        UnityAds.setTestMode(z);
    }

    public static void setTestDeveloperId(String str) {
        UnityAds.setTestDeveloperId(str);
    }

    public static void setTestOptionsId(String str) {
        UnityAds.setTestOptionsId(str);
    }

    public static String getSDKVersion() {
        return UnityAds.getSDKVersion();
    }

    public void setImpactListener(IApplifierImpactListener iApplifierImpactListener) {
        this.a = iApplifierImpactListener;
    }

    public void changeActivity(Activity activity) {
        UnityAds.changeActivity(activity);
    }

    public boolean hideImpact() {
        return UnityAds.hide();
    }

    public boolean setZone(String str) {
        return UnityAds.setZone(str);
    }

    public boolean setZone(String str, String str2) {
        return UnityAds.setZone(str, str2);
    }

    public boolean showImpact(Map map) {
        return UnityAds.show(map);
    }

    public boolean showImpact() {
        return UnityAds.show();
    }

    public boolean canShowCampaigns() {
        return UnityAds.canShowAds();
    }

    public boolean canShowImpact() {
        return UnityAds.canShow();
    }

    public void stopAll() {
    }

    public boolean hasMultipleRewardItems() {
        return UnityAds.hasMultipleRewardItems();
    }

    public ArrayList getRewardItemKeys() {
        return UnityAds.getRewardItemKeys();
    }

    public String getDefaultRewardItemKey() {
        return UnityAds.getDefaultRewardItemKey();
    }

    public String getCurrentRewardItemKey() {
        return UnityAds.getCurrentRewardItemKey();
    }

    public boolean setRewardItemKey(String str) {
        return UnityAds.setRewardItemKey(str);
    }

    public void setDefaultRewardItemAsRewardItem() {
        UnityAds.setDefaultRewardItemAsRewardItem();
    }

    public Map getRewardItemDetailsWithKey(String str) {
        return UnityAds.getRewardItemDetailsWithKey(str);
    }

    @Override // com.unity3d.ads.android.IUnityAdsListener
    public void onHide() {
        if (this.a != null) {
            this.a.onImpactClose();
        }
    }

    @Override // com.unity3d.ads.android.IUnityAdsListener
    public void onShow() {
        if (this.a != null) {
            this.a.onImpactOpen();
        }
    }

    @Override // com.unity3d.ads.android.IUnityAdsListener
    public void onVideoStarted() {
        if (this.a != null) {
            this.a.onVideoStarted();
        }
    }

    @Override // com.unity3d.ads.android.IUnityAdsListener
    public void onVideoCompleted(String str, boolean z) {
        if (this.a != null) {
            this.a.onVideoCompleted(str, z);
        }
    }

    @Override // com.unity3d.ads.android.IUnityAdsListener
    public void onFetchCompleted() {
        if (this.a != null) {
            this.a.onCampaignsAvailable();
        }
    }

    @Override // com.unity3d.ads.android.IUnityAdsListener
    public void onFetchFailed() {
        if (this.a != null) {
            this.a.onCampaignsFetchFailed();
        }
    }
}
