package com.google.android.gms.internal;

import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.concurrent.ExecutionException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@ey
public class fo implements fn.a<bt> {
    @Override // com.google.android.gms.internal.fn.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public bt a(fn fnVar, JSONObject jSONObject) throws ExecutionException, JSONException, InterruptedException {
        return new bt(jSONObject.getString("headline"), fnVar.a(jSONObject, "image", true).get(), jSONObject.getString(UnityAdsConstants.UNITY_ADS_FAILED_URL_BODY_KEY), fnVar.a(jSONObject, "app_icon", true).get(), jSONObject.getString("call_to_action"), jSONObject.optDouble("rating", -1.0d), jSONObject.optString("store"), jSONObject.optString("price"));
    }
}
