package com.google.android.gms.internal;

import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.concurrent.ExecutionException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@ey
public class fp implements fn.a<bu> {
    @Override // com.google.android.gms.internal.fn.a
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public bu a(fn fnVar, JSONObject jSONObject) throws ExecutionException, JSONException, InterruptedException {
        return new bu(jSONObject.getString("headline"), fnVar.a(jSONObject, "image", true).get(), jSONObject.getString(UnityAdsConstants.UNITY_ADS_FAILED_URL_BODY_KEY), fnVar.a(jSONObject, "secondary_image", false).get(), jSONObject.getString("call_to_action"), jSONObject.getString("attribution"));
    }
}
