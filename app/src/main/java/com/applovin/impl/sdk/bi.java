package com.applovin.impl.sdk;

import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class bi extends ba {
    bi(AppLovinSdkImpl appLovinSdkImpl) {
        super("SubmitData", appLovinSdkImpl);
    }

    void a(JSONObject jSONObject) {
        try {
            JSONObject jSONObjectA = p.a(jSONObject);
            be settingsManager = this.f.getSettingsManager();
            settingsManager.a(bb.c, jSONObjectA.getString("device_id"));
            settingsManager.a(bb.e, jSONObjectA.getString("device_token"));
            settingsManager.a(bb.d, jSONObjectA.getString("publisher_id"));
            settingsManager.b();
            p.a(jSONObjectA, this.f);
            if (jSONObjectA.has("adserver_parameters")) {
                settingsManager.a(bb.y, jSONObjectA.getJSONObject("adserver_parameters").toString());
            }
        } catch (JSONException e) {
            this.g.e(this.e, "Unable to parse API response", e);
        }
    }

    void b(JSONObject jSONObject) throws JSONException {
        q qVarC = c();
        s sVarB = qVarC.b();
        t tVarA = qVarC.a();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("model", tVarA.a);
        jSONObject2.put("os", tVarA.b);
        jSONObject2.put("brand", tVarA.c);
        jSONObject2.put("sdk_version", tVarA.e);
        jSONObject2.put("revision", tVarA.d);
        jSONObject2.put("country_code", tVarA.f);
        jSONObject2.put("carrier", tVarA.g);
        jSONObject2.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, "android");
        r rVarC = qVarC.c();
        String str = rVarC.b;
        if (!rVarC.a && cd.c(str)) {
            jSONObject2.put("idfa", str);
        }
        Locale locale = tVarA.h;
        if (locale != null) {
            jSONObject2.put("locale", locale.toString());
        }
        jSONObject.put("device_info", jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("package_name", sVarB.c);
        jSONObject3.put(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, sVarB.a);
        jSONObject3.put("app_version", sVarB.b);
        jSONObject3.put("created_at", sVarB.d / 1000);
        jSONObject3.put("applovin_sdk_version", "5.4.3");
        String str2 = (String) this.f.a(bb.P);
        jSONObject3.put("first_install", str2);
        if (str2.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
            this.f.getSettingsManager().a(bb.P, "false");
        }
        String str3 = (String) this.f.a(bb.F);
        if (str3 != null && str3.length() > 0) {
            jSONObject3.put("plugin_version", str3);
        }
        jSONObject.put("app_info", jSONObject3);
        if (((Boolean) this.f.a(bb.N)).booleanValue()) {
            Map mapA = ((l) this.f.getTargetingData()).a();
            if (mapA != null && !mapA.isEmpty()) {
                jSONObject.put("targeting", au.a(mapA));
            }
            jSONObject.put("stats", this.f.b().b());
        }
    }

    void c(JSONObject jSONObject) {
        bj bjVar = new bj(this, "Repeat" + this.e, bb.g, this.f, jSONObject);
        bjVar.a(bb.k);
        bjVar.run();
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.g.i(this.e, "Submitting user data...");
            JSONObject jSONObject = new JSONObject();
            b(jSONObject);
            c(jSONObject);
        } catch (JSONException e) {
            this.g.e(this.e, "Unable to create JSON message with collected data", e);
        }
    }
}
