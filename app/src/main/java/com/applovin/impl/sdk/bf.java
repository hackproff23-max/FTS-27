package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdk;

/* JADX INFO: loaded from: classes.dex */
public class bf {
    private final be a;

    public bf(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl.getSettingsManager();
    }

    public bf(AppLovinSdk appLovinSdk) {
        this.a = ((AppLovinSdkImpl) appLovinSdk).getSettingsManager();
    }

    public boolean a() {
        return ((Boolean) this.a.a(bb.O)).booleanValue();
    }

    public boolean b() {
        return ((Boolean) this.a.a(bb.aw)).booleanValue();
    }

    public boolean c() {
        return ((Boolean) this.a.a(bb.ax)).booleanValue();
    }

    public String d() {
        return (String) this.a.a(bb.Q);
    }

    public String e() {
        return (String) this.a.a(bb.R);
    }

    public int f() {
        return ((Integer) this.a.a(bb.T)).intValue();
    }

    public int g() {
        return ((Integer) this.a.a(bb.S)).intValue();
    }

    public boolean h() {
        return ((Boolean) this.a.a(bb.V)).booleanValue();
    }

    public boolean i() {
        return ((Boolean) this.a.a(bb.U)).booleanValue();
    }

    public boolean j() {
        return ((Boolean) this.a.a(bb.W)).booleanValue();
    }

    public boolean k() {
        return ((Boolean) this.a.a(bb.ao)).booleanValue();
    }

    public int l() {
        return ((Integer) this.a.a(bb.aq)).intValue();
    }

    public int m() {
        return ((Integer) this.a.a(bb.ar)).intValue();
    }

    public int n() {
        return ((Integer) this.a.a(bb.as)).intValue();
    }

    public int o() {
        return ((Integer) this.a.a(bb.au)).intValue();
    }

    public int p() {
        return ((Integer) this.a.a(bb.at)).intValue();
    }

    public int q() {
        return ((Integer) this.a.a(bb.av)).intValue();
    }

    public int r() {
        return ((Integer) this.a.a(bb.ay)).intValue();
    }

    public boolean s() {
        return ((Boolean) this.a.a(bb.az)).booleanValue();
    }
}
