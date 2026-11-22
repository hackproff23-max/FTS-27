package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class i extends ba {
    final /* synthetic */ e a;
    private final AppLovinAdSize b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(e eVar, AppLovinAdSize appLovinAdSize) {
        super("UpdateAdTask", eVar.a);
        this.a = eVar;
        this.b = appLovinAdSize;
    }

    @Override // java.lang.Runnable
    public void run() {
        h hVar = (h) ((Map) this.a.d.get(AppLovinAdType.REGULAR)).get(this.b);
        synchronized (hVar.b) {
            boolean zA = this.a.a(this.b);
            boolean zA2 = this.a.a();
            boolean z = !hVar.f.isEmpty();
            boolean z2 = System.currentTimeMillis() > hVar.d;
            if (zA && z && z2 && zA2 && !hVar.e) {
                hVar.e = true;
                this.a.b(this.b, AppLovinAdType.REGULAR, new g(this.a, hVar));
            }
        }
    }
}
