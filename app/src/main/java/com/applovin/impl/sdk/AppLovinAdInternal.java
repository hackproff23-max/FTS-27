package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;

/* JADX INFO: loaded from: classes.dex */
public class AppLovinAdInternal extends AppLovinAd {
    private final AdTarget a;
    private final String b;
    private final String c;
    private final float d;
    private final float e;
    private final int f;
    private final com.applovin.impl.adview.t g;

    public enum AdTarget {
        DEFAULT,
        ACTIVITY_PORTRAIT,
        ACTIVITY_LANDSCAPE
    }

    public class Builder {
        private String a;
        private AppLovinAdSize b;
        private AppLovinAdType c;
        private String d;
        private AdTarget e;
        private com.applovin.impl.adview.t f;
        private float g;
        private float h;
        private int i;
        private long j;
        private String k;

        public AppLovinAdInternal create() {
            return new AppLovinAdInternal(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k);
        }

        public Builder setClCode(String str) {
            this.k = str;
            return this;
        }

        public Builder setCloseStyle(com.applovin.impl.adview.t tVar) {
            this.f = tVar;
            return this;
        }

        public Builder setCountdownLength(int i) {
            this.i = i;
            return this;
        }

        public Builder setCurrentAdIdNumber(long j) {
            this.j = j;
            return this;
        }

        public Builder setHtml(String str) {
            this.a = str;
            return this;
        }

        public Builder setPoststitialCloseDelay(float f) {
            this.h = f;
            return this;
        }

        public Builder setSize(AppLovinAdSize appLovinAdSize) {
            this.b = appLovinAdSize;
            return this;
        }

        public Builder setTarget(AdTarget adTarget) {
            this.e = adTarget;
            return this;
        }

        public Builder setType(AppLovinAdType appLovinAdType) {
            this.c = appLovinAdType;
            return this;
        }

        public Builder setVideoCloseDelay(float f) {
            this.g = f;
            return this;
        }

        public Builder setVideoFilename(String str) {
            this.d = str;
            return this;
        }
    }

    private AppLovinAdInternal(String str, AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, String str2, AdTarget adTarget, com.applovin.impl.adview.t tVar, float f, float f2, int i, long j, String str3) {
        super(appLovinAdSize, appLovinAdType, str2, j);
        this.c = str;
        this.a = adTarget;
        this.d = f;
        this.f = i;
        this.b = str3;
        this.g = tVar;
        this.e = f2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AppLovinAdInternal appLovinAdInternal = (AppLovinAdInternal) obj;
        return getAdIdNumber() == appLovinAdInternal.getAdIdNumber() && getType().equals(appLovinAdInternal.getType()) && getSize().equals(appLovinAdInternal.getSize()) && this.c.equals(appLovinAdInternal.getHtmlSource());
    }

    public String getClCode() {
        return this.b;
    }

    public com.applovin.impl.adview.t getCloseStyle() {
        return this.g;
    }

    public int getCountdownLength() {
        return this.f;
    }

    public String getHtmlSource() {
        return this.c;
    }

    public float getPoststitialCloseDelay() {
        return this.e;
    }

    public AdTarget getTarget() {
        return this.a;
    }

    public float getVideoCloseDelay() {
        return this.d;
    }

    public String getVideoFilename() {
        return this.videoFilename;
    }

    public int hashCode() {
        return (int) getAdIdNumber();
    }
}
