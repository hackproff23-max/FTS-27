package com.applovin.impl.sdk;

import android.util.Log;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class bu implements o {
    final /* synthetic */ AppLovinSdkImpl a;
    final /* synthetic */ String b;
    final /* synthetic */ bt c;

    bu(bt btVar, AppLovinSdkImpl appLovinSdkImpl, String str) {
        this.c = btVar;
        this.a = appLovinSdkImpl;
        this.b = str;
    }

    @Override // com.applovin.impl.sdk.o
    public void a(int i) {
        boolean z = i < 200 || i >= 500;
        boolean z2 = i != -103;
        if (!z || !z2 || this.c.a <= 0) {
            this.c.a(i);
            return;
        }
        long jLongValue = ((Long) this.a.a(bb.n)).longValue();
        Log.w(this.b, "Unable to send requset due to server failure (code " + i + "). " + this.c.a + " attempts left, retrying in " + (jLongValue / 1000.0d) + " seconds...");
        bt.b(this.c, 1);
        if (this.c.a == 0) {
            this.c.d();
        }
        this.a.a().a(this.c, bo.BACKGROUND, jLongValue);
    }

    @Override // com.applovin.impl.sdk.o
    public void a(JSONObject jSONObject, int i) {
        this.c.a = 0;
        this.c.a(jSONObject, i);
    }
}
