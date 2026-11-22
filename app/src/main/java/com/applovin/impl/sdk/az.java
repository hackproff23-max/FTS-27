package com.applovin.impl.sdk;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class az extends bt {
    final /* synthetic */ String a;
    final /* synthetic */ JSONObject b;
    final /* synthetic */ o c;
    final /* synthetic */ ay d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    az(ay ayVar, String str, bd bdVar, AppLovinSdkImpl appLovinSdkImpl, String str2, JSONObject jSONObject, o oVar) {
        super(str, bdVar, appLovinSdkImpl);
        this.d = ayVar;
        this.a = str2;
        this.b = jSONObject;
        this.c = oVar;
    }

    @Override // com.applovin.impl.sdk.bt, com.applovin.impl.sdk.o
    public void a(int i) {
        this.c.a(i);
    }

    @Override // com.applovin.impl.sdk.bt
    protected void a(n nVar, o oVar) throws Throwable {
        nVar.a(p.a(p.a(this.a, this.f)), this.b, oVar);
    }

    @Override // com.applovin.impl.sdk.bt, com.applovin.impl.sdk.o
    public void a(JSONObject jSONObject, int i) {
        this.c.a(jSONObject, i);
    }
}
