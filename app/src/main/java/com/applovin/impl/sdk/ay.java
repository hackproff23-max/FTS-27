package com.applovin.impl.sdk;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
abstract class ay extends ba {
    protected ay(String str, AppLovinSdkImpl appLovinSdkImpl) {
        super(str, appLovinSdkImpl);
    }

    protected void a(String str, JSONObject jSONObject, o oVar) {
        az azVar = new az(this, "Repeat" + this.e, bb.h, this.f, str, jSONObject, oVar);
        azVar.a(bb.k);
        azVar.run();
    }
}
