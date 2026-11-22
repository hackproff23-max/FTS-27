package com.unity3d.ads.android;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsDeviceLogLevel {
    private UnityAdsDeviceLog.UnityAdsLogLevel a;
    private String b;
    private String c;

    public UnityAdsDeviceLogLevel(UnityAdsDeviceLog.UnityAdsLogLevel unityAdsLogLevel, String str, String str2) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.a = unityAdsLogLevel;
        this.c = str;
        this.b = str2;
    }

    public UnityAdsDeviceLog.UnityAdsLogLevel getLevel() {
        return this.a;
    }

    public String getLogTag() {
        return this.c;
    }

    public String getReceivingMethodName() {
        return this.b;
    }
}
