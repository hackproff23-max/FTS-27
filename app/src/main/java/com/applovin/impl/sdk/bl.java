package com.applovin.impl.sdk;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class bl extends bt {
    final /* synthetic */ bk a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    bl(bk bkVar, String str, bd bdVar, AppLovinSdkImpl appLovinSdkImpl) {
        super(str, bdVar, appLovinSdkImpl);
        this.a = bkVar;
    }

    @Override // com.applovin.impl.sdk.bt, com.applovin.impl.sdk.o
    public void a(int i) {
        this.a.a(i);
    }

    @Override // com.applovin.impl.sdk.bt
    protected void a(n nVar, o oVar) throws Throwable {
        nVar.a(this.a.d(), ((Integer) this.f.a(bb.u)).intValue(), oVar);
    }

    @Override // com.applovin.impl.sdk.bt, com.applovin.impl.sdk.o
    public void a(JSONObject jSONObject, int i) {
        if (i == 200) {
            this.a.a(jSONObject);
        } else {
            this.a.a(i);
        }
    }
}
