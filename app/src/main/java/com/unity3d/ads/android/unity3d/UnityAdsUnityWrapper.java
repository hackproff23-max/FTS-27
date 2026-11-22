package com.unity3d.ads.android.unity3d;

import android.app.Activity;
import com.facebook.ads.InterstitialAd;
import com.facebook.internal.ServerProtocol;
import com.unity3d.ads.android.IUnityAdsListener;
import com.unity3d.ads.android.UnityAds;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import java.lang.reflect.Method;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsUnityWrapper implements IUnityAdsListener {
    private static Boolean f = false;
    private static Boolean g = false;
    private Method d;
    private Activity a = null;
    private String b = null;
    private String c = null;
    private boolean e = false;

    public UnityAdsUnityWrapper() {
        this.d = null;
        if (!f.booleanValue()) {
            f = true;
            try {
                this.d = Class.forName("com.unity3d.player.UnityPlayer").getDeclaredMethod("UnitySendMessage", String.class, String.class, String.class);
            } catch (Exception e) {
                UnityAdsDeviceLog.error("Error getting class or method of com.unity3d.player.UnityPlayer, method UnitySendMessage(string, string, string). " + e.getLocalizedMessage());
            }
        }
    }

    public boolean isSupported() {
        return UnityAds.isSupported();
    }

    public String getSDKVersion() {
        return UnityAds.getSDKVersion();
    }

    public void init(String str, Activity activity, boolean z, int i, String str2) {
        if (!g.booleanValue()) {
            g = true;
            this.c = str;
            this.b = str2;
            this.e = z;
            if (this.a == null) {
                this.a = activity;
            }
            try {
                UnityAdsUtils.runOnUiThread(new a(this, i, this));
            } catch (Exception e) {
                UnityAdsDeviceLog.error("Error occured while initializing Unity Ads");
            }
        }
    }

    public boolean show(String str, String str2, String str3) {
        if (!UnityAds.canShowAds() || !UnityAds.canShow()) {
            return false;
        }
        HashMap map = null;
        if (str3.length() > 0) {
            map = new HashMap();
            for (String str4 : str3.split(",")) {
                String[] strArrSplit = str4.split(InterstitialAd.SEPARATOR);
                map.put(strArrSplit[0], strArrSplit[1]);
            }
        }
        if (str2.length() > 0) {
            UnityAds.setZone(str, str2);
        } else {
            UnityAds.setZone(str);
        }
        return UnityAds.show(map);
    }

    public void hide() {
        UnityAds.hide();
    }

    public boolean canShowAds() {
        return UnityAds.canShowAds();
    }

    public boolean canShow() {
        return UnityAds.canShow();
    }

    public boolean hasMultipleRewardItems() {
        return UnityAds.hasMultipleRewardItems();
    }

    public String getRewardItemKeys() {
        String str = null;
        if (UnityAds.getRewardItemKeys() != null && UnityAds.getRewardItemKeys().size() > 0) {
            str = "";
            for (String str2 : UnityAds.getRewardItemKeys()) {
                if (UnityAds.getRewardItemKeys().indexOf(str2) > 0) {
                    str = str + ";";
                }
                str = str + str2;
            }
        }
        return str;
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

    public String getRewardItemDetailsWithKey(String str) {
        if (UnityAds.getRewardItemDetailsWithKey(str) != null) {
            UnityAdsDeviceLog.debug("Fetching reward data");
            HashMap map = (HashMap) UnityAds.getRewardItemDetailsWithKey(str);
            if (map != null) {
                return ((String) map.get("name")) + ";" + ((String) map.get("picture"));
            }
            UnityAdsDeviceLog.debug("Problems getting reward item details");
        } else {
            UnityAdsDeviceLog.debug("Could not find reward item details");
        }
        return "";
    }

    public String getRewardItemDetailsKeys() {
        return String.format("%s;%s", "name", "picture");
    }

    public void setLogLevel(int i) {
        UnityAdsDeviceLog.setLogLevel(i);
    }

    public void enableUnityDeveloperInternalTestMode() {
        UnityAds.enableUnityDeveloperInternalTestMode();
    }

    @Override // com.unity3d.ads.android.IUnityAdsListener
    public void onHide() {
        sendMessageToUnity3D("onHide", null);
    }

    @Override // com.unity3d.ads.android.IUnityAdsListener
    public void onShow() {
        sendMessageToUnity3D("onShow", null);
    }

    @Override // com.unity3d.ads.android.IUnityAdsListener
    public void onVideoStarted() {
        sendMessageToUnity3D("onVideoStarted", null);
    }

    @Override // com.unity3d.ads.android.IUnityAdsListener
    public void onVideoCompleted(String str, boolean z) {
        sendMessageToUnity3D("onVideoCompleted", str + ";" + (z ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false"));
    }

    @Override // com.unity3d.ads.android.IUnityAdsListener
    public void onFetchCompleted() {
        sendMessageToUnity3D("onFetchCompleted", null);
    }

    @Override // com.unity3d.ads.android.IUnityAdsListener
    public void onFetchFailed() {
        sendMessageToUnity3D("onFetchFailed", null);
    }

    public void sendMessageToUnity3D(String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        if (this.d == null) {
            UnityAdsDeviceLog.error("Cannot send message to Unity3D. Method is null");
            return;
        }
        try {
            UnityAdsDeviceLog.debug("Sending message (" + str + ", " + str2 + ") to Unity3D");
            this.d.invoke(null, this.b, str, str2);
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Can't invoke UnitySendMessage method. Error = " + e.getLocalizedMessage());
        }
    }
}
