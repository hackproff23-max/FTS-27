package com.unity3d.ads.android.webapp;

import android.annotation.SuppressLint;

/* JADX INFO: compiled from: UnityAdsWebData.java */
/* JADX INFO: loaded from: classes.dex */
enum e {
    Analytics,
    VideoPlan,
    VideoViewed,
    Unsent;

    @Override // java.lang.Enum
    @SuppressLint({"DefaultLocale"})
    public final String toString() {
        return name().toString().toLowerCase();
    }

    @SuppressLint({"DefaultLocale"})
    public static e getValueOf(String str) {
        if (VideoPlan.toString().equals(str.toLowerCase())) {
            return VideoPlan;
        }
        if (VideoViewed.toString().equals(str.toLowerCase())) {
            return VideoViewed;
        }
        if (Unsent.toString().equals(str.toLowerCase())) {
            return Unsent;
        }
        return null;
    }
}
