package com.applovin.sdk;

/* JADX INFO: loaded from: classes.dex */
public interface AppLovinAdService {
    public static final String URI_AD_SERVICE = "/adservice";
    public static final String URI_API_SERVICE = "/api";
    public static final String URI_CLOSE_AD = "/adservice/close_ad";
    public static final String URI_LAUNCH_APP = "/launch";
    public static final String URI_NEXT_AD = "/adservice/next_ad";

    @Deprecated
    void addAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener);

    void addAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize appLovinAdSize);

    boolean hasPreloadedAd(AppLovinAdSize appLovinAdSize);

    void loadNextAd(AppLovinAdSize appLovinAdSize, AppLovinAdLoadListener appLovinAdLoadListener);

    void preloadAd(AppLovinAdSize appLovinAdSize);

    void removeAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize appLovinAdSize);
}
