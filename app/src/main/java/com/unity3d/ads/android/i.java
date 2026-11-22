package com.unity3d.ads.android;

import com.unity3d.ads.android.view.UnityAdsMainView;

/* JADX INFO: compiled from: UnityAds.java */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class i {
    static final /* synthetic */ int[] a = new int[UnityAdsMainView.UnityAdsMainViewAction.values().length];

    static {
        try {
            a[UnityAdsMainView.UnityAdsMainViewAction.BackButtonPressed.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[UnityAdsMainView.UnityAdsMainViewAction.VideoStart.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[UnityAdsMainView.UnityAdsMainViewAction.VideoEnd.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[UnityAdsMainView.UnityAdsMainViewAction.VideoSkipped.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[UnityAdsMainView.UnityAdsMainViewAction.RequestRetryVideoPlay.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
    }
}
