package com.flurry.sdk;

import com.facebook.internal.AnalyticsEvents;
import com.flurry.android.AdCreative;
import com.unity3d.ads.android.properties.UnityAdsConstants;

/* JADX INFO: loaded from: classes.dex */
public enum ax {
    BANNER(AdCreative.kFormatBanner),
    TAKEOVER(AdCreative.kFormatTakeover),
    STREAM(UnityAdsConstants.UNITY_ADS_GOOGLE_ANALYTICS_EVENT_VIDEOPLAY_HLSL),
    NATIVE(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE),
    UNKNOWN("unknown");

    private final String f;

    ax(String str) {
        this.f = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.f;
    }
}
