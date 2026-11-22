package com.unity3d.ads.android.webapp;

import com.unity3d.ads.android.properties.UnityAdsConstants;

/* JADX INFO: compiled from: UnityAdsWebBridge.java */
/* JADX INFO: loaded from: classes.dex */
enum b {
    PlayVideo,
    PauseVideo,
    CloseView,
    LoadComplete,
    InitComplete,
    PlayStore,
    NavigateTo;

    @Override // java.lang.Enum
    public final String toString() {
        switch (this) {
            case PlayVideo:
                return UnityAdsConstants.UNITY_ADS_WEBVIEW_API_PLAYVIDEO;
            case PauseVideo:
                return "pauseVideo";
            case CloseView:
                return UnityAdsConstants.UNITY_ADS_WEBVIEW_API_CLOSE;
            case LoadComplete:
                return UnityAdsConstants.UNITY_ADS_WEBVIEW_API_LOADCOMPLETE;
            case InitComplete:
                return UnityAdsConstants.UNITY_ADS_WEBVIEW_API_INITCOMPLETE;
            case PlayStore:
                return UnityAdsConstants.UNITY_ADS_WEBVIEW_API_PLAYSTORE;
            case NavigateTo:
                return UnityAdsConstants.UNITY_ADS_WEBVIEW_API_NAVIGATETO;
            default:
                return null;
        }
    }
}
