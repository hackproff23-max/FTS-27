package com.facebook.ads.a;

import android.content.Context;
import com.facebook.ads.AdError;
import com.facebook.share.internal.ShareConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class l {
    private final int a;
    private final int b;
    private final int c;
    private final List d;
    private final AdError e;

    private l(int i, int i2, int i3, List list, AdError adError) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = list;
        this.e = adError;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x00a5  */
    public static l a(Context context, JSONObject jSONObject) {
        AdError adError;
        int iOptInt = jSONObject.optInt("refresh", 0) * 1000;
        int iOptInt2 = jSONObject.optInt("refresh_threshold", 20) * 1000;
        int iOptInt3 = jSONObject.optInt("min_viewability_percentage", 10);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("reason");
        AdError adError2 = jSONObjectOptJSONObject != null ? new AdError(jSONObjectOptJSONObject.optInt("code", 2000), jSONObjectOptJSONObject.optString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, AdError.SERVER_ERROR.getErrorMessage())) : null;
        int iOptInt4 = jSONObject.optInt("ad_type");
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("ads");
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
            adError = adError2;
        } else {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject2 != null) {
                    e eVarA = iOptInt4 == o.HTML.a() ? y.a(jSONObjectOptJSONObject2.optJSONObject("data")) : iOptInt4 == o.NATIVE.a() ? aa.a(jSONObjectOptJSONObject2.optJSONObject("metadata")) : null;
                    if (eVarA != null && !h.a(context, eVarA)) {
                        arrayList.add(eVarA);
                    }
                }
            }
            if (arrayList.isEmpty()) {
                adError = AdError.NO_FILL;
            } else {
                adError = adError2;
            }
        }
        return new l(iOptInt, iOptInt2, iOptInt3, arrayList, adError);
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public e d() {
        if (this.d == null || this.d.isEmpty()) {
            return null;
        }
        return (e) this.d.get(0);
    }

    public AdError e() {
        return this.e;
    }
}
