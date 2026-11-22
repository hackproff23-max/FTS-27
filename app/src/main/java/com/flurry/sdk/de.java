package com.flurry.sdk;

import com.unity3d.ads.android.properties.UnityAdsConstants;

/* JADX INFO: loaded from: classes.dex */
public enum de {
    Unknown("unknown"),
    CreativeView("creativeView"),
    Start("start"),
    Midpoint("midpoint"),
    FirstQuartile("firstQuartile"),
    ThirdQuartile("thirdQuartile"),
    Complete("complete"),
    Mute("mute"),
    UnMute("unmute"),
    Pause("pause"),
    Rewind("rewind"),
    Resume("resume"),
    FullScreen("fullscreen"),
    Expand("expand"),
    Collapse("collapse"),
    AcceptInvitation("acceptInvitation"),
    Close(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_CLOSE);

    private String r;

    de(String str) {
        this.r = str;
    }

    public String a() {
        return this.r;
    }

    public static de a(String str) {
        if (CreativeView.a().equals(str)) {
            return CreativeView;
        }
        if (Start.a().equals(str)) {
            return Start;
        }
        if (Midpoint.a().equals(str)) {
            return Midpoint;
        }
        if (FirstQuartile.a().equals(str)) {
            return FirstQuartile;
        }
        if (ThirdQuartile.a().equals(str)) {
            return ThirdQuartile;
        }
        if (Complete.a().equals(str)) {
            return Complete;
        }
        if (Mute.a().equals(str)) {
            return Mute;
        }
        if (UnMute.a().equals(str)) {
            return UnMute;
        }
        if (Pause.a().equals(str)) {
            return Pause;
        }
        if (Rewind.a().equals(str)) {
            return Rewind;
        }
        if (Resume.a().equals(str)) {
            return Resume;
        }
        if (FullScreen.a().equals(str)) {
            return FullScreen;
        }
        if (Expand.a().equals(str)) {
            return Expand;
        }
        if (Collapse.a().equals(str)) {
            return Collapse;
        }
        if (AcceptInvitation.a().equals(str)) {
            return AcceptInvitation;
        }
        if (Close.a().equals(str)) {
            return Close;
        }
        return Unknown;
    }
}
