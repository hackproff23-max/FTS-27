package com.applovin.sdk;

import com.applovin.impl.sdk.cd;

/* JADX INFO: loaded from: classes.dex */
public class AppLovinAd {
    private final AppLovinAdSize a;
    private final AppLovinAdType b;
    private final long c;
    protected final String videoFilename;

    protected AppLovinAd(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, String str, long j) {
        if (appLovinAdSize == null) {
            throw new IllegalArgumentException("No size specified");
        }
        if (appLovinAdType == null) {
            throw new IllegalArgumentException("No type specified");
        }
        this.a = appLovinAdSize;
        this.b = appLovinAdType;
        this.videoFilename = str;
        this.c = j;
    }

    public long getAdIdNumber() {
        return this.c;
    }

    public AppLovinAdSize getSize() {
        return this.a;
    }

    public AppLovinAdType getType() {
        return this.b;
    }

    public boolean isVideoAd() {
        return cd.c(this.videoFilename);
    }
}
