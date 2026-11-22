package com.applovin.impl.sdk;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class bj extends bt {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ bi b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    bj(bi biVar, String str, bd bdVar, AppLovinSdkImpl appLovinSdkImpl, JSONObject jSONObject) {
        super(str, bdVar, appLovinSdkImpl);
        this.b = biVar;
        this.a = jSONObject;
    }

    @Override // com.applovin.impl.sdk.bt, com.applovin.impl.sdk.o
    public void a(int i) {
        p.a(i, this.f);
    }

    @Override // com.applovin.impl.sdk.bt
    protected void a(n nVar, o oVar) throws Throwable {
        nVar.a(p.a("device", this.f), this.a, oVar);
    }

    @Override // com.applovin.impl.sdk.bt, com.applovin.impl.sdk.o
    public void a(JSONObject jSONObject, int i) {
        this.b.a(jSONObject);
    }
}
