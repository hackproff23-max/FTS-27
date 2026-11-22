package com.facebook.ads.a;

import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
enum m {
    APP_AD(0),
    LINK_AD(1),
    APP_AD_V2(2),
    LINK_AD_V2(3),
    APP_ENGAGEMENT_AD(4),
    AD_CHOICES(5),
    JS_TRIGGER(6),
    JS_TRIGGER_NO_AUTO_IMP_LOGGING(7),
    VIDEO_AD(8),
    INLINE_VIDEO_AD(9),
    BANNER_TO_INTERSTITIAL(10),
    NATIVE_CLOSE_BUTTON(11);

    private static final String o;
    private final int m;
    private static final m[] n = {LINK_AD_V2, APP_ENGAGEMENT_AD, AD_CHOICES, JS_TRIGGER_NO_AUTO_IMP_LOGGING, NATIVE_CLOSE_BUTTON};

    static {
        JSONArray jSONArray = new JSONArray();
        for (m mVar : n) {
            jSONArray.put(mVar.a());
        }
        o = jSONArray.toString();
    }

    m(int i) {
        this.m = i;
    }

    public static String b() {
        return o;
    }

    int a() {
        return this.m;
    }
}
