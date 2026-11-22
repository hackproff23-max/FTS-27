package com.facebook.ads.a;

import com.google.android.gms.games.quest.Quests;

/* JADX INFO: loaded from: classes.dex */
public enum n {
    UNKNOWN(0),
    WEBVIEW_BANNER_LEGACY(4),
    WEBVIEW_BANNER_50(5),
    WEBVIEW_BANNER_90(6),
    WEBVIEW_INTERSTITIAL_UNKNOWN(100),
    WEBVIEW_INTERSTITIAL_HORIZONTAL(Quests.SELECT_COMPLETED_UNCLAIMED),
    WEBVIEW_INTERSTITIAL_VERTICAL(102),
    WEBVIEW_INTERSTITIAL_TABLET(Quests.SELECT_RECENTLY_FAILED),
    NATIVE_UNKNOWN(200),
    NATIVE_250(201);

    private final int k;

    n(int i) {
        this.k = i;
    }

    int a() {
        return this.k;
    }
}
