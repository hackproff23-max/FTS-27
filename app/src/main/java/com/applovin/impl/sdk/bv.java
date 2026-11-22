package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.facebook.AccessToken;
import com.facebook.internal.NativeProtocol;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class bv extends ay {
    private final AppLovinAdInternal a;

    public bv(AppLovinSdkImpl appLovinSdkImpl, AppLovinAd appLovinAd) {
        super("TaskReportReward", appLovinSdkImpl);
        this.a = (AppLovinAdInternal) appLovinAd;
    }

    @Override // java.lang.Runnable
    public void run() {
        String strB = w.b();
        String clCode = this.a.getClCode();
        HashMap map = new HashMap(2);
        if (cd.c(clCode)) {
            map.put("clcode", clCode);
        } else {
            map.put("clcode", "NO_CLCODE");
        }
        if (strB != null) {
            map.put(AccessToken.USER_ID_KEY, strB);
        }
        av avVarA = av.a();
        String strB2 = avVarA.b(this.a);
        if (strB2 == null) {
            this.g.d("TaskReportReward", "No reward result was found for ad: " + this.a);
            return;
        }
        map.put("result", strB2);
        Map mapA = avVarA.a(this.a);
        if (mapA != null) {
            map.put(NativeProtocol.WEB_DIALOG_PARAMS, mapA);
        }
        a("cr", new JSONObject(map), new bw(this));
    }
}
