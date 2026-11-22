package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.facebook.AccessToken;
import com.facebook.internal.NativeProtocol;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class ca extends ay {
    private final AppLovinAdInternal a;
    private final AppLovinAdRewardListener b;
    private final Object c;
    private volatile boolean d;

    public ca(AppLovinSdkImpl appLovinSdkImpl, AppLovinAd appLovinAd, AppLovinAdRewardListener appLovinAdRewardListener) {
        super("TaskValidateReward", appLovinSdkImpl);
        this.c = new Object();
        this.d = false;
        this.a = (AppLovinAdInternal) appLovinAd;
        this.b = appLovinAdRewardListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (d()) {
            return;
        }
        String str = "network_timeout";
        if (i < 400 || i > 500) {
            this.b.validationRequestFailed(this.a, i);
        } else {
            this.b.userRewardRejected(this.a, new HashMap(0));
            str = "rejected";
        }
        av.a().a(this.a, str);
    }

    private void a(String str, Map map) {
        if (d()) {
            return;
        }
        av avVarA = av.a();
        avVarA.a(this.a, str);
        avVarA.a(this.a, map);
        if (str.equals("accepted")) {
            this.b.userRewardVerified(this.a, map);
            return;
        }
        if (str.equals("quota_exceeded")) {
            this.b.userOverQuota(this.a, map);
        } else if (str.equals("rejected")) {
            this.b.userRewardRejected(this.a, map);
        } else {
            this.b.validationRequestFailed(this.a, AppLovinErrorCodes.INCENTIVIZED_UNKNOWN_SERVER_ERROR);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        Map map;
        String string;
        if (d()) {
            return;
        }
        try {
            JSONObject jSONObjectA = p.a(jSONObject);
            p.a(jSONObjectA, this.f);
            try {
                map = au.a((JSONObject) jSONObjectA.get(NativeProtocol.WEB_DIALOG_PARAMS));
            } catch (Throwable th) {
                map = new HashMap(0);
            }
            try {
                string = jSONObjectA.getString("result");
            } catch (Throwable th2) {
                string = "network_timeout";
            }
            a(string, map);
        } catch (JSONException e) {
            this.g.e(this.e, "Unable to parse API response", e);
        }
    }

    public void a(boolean z) {
        synchronized (this.c) {
            this.d = z;
        }
    }

    boolean d() {
        boolean z;
        synchronized (this.c) {
            z = this.d;
        }
        return z;
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
        a("vr", new JSONObject(map), new cb(this));
    }
}
