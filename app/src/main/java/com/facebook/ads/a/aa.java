package com.facebook.ads.a;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.facebook.ads.NativeAd;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class aa implements e {
    private static final String a = aa.class.getSimpleName();
    private final Uri b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final NativeAd.Image g;
    private final NativeAd.Image h;
    private final NativeAd.Rating i;
    private final String j;
    private final String k;
    private final g l;
    private final Collection m;
    private final boolean n;
    private final NativeAd.Image o;
    private final String p;
    private boolean q;
    private boolean r;

    private aa(Uri uri, String str, String str2, String str3, String str4, NativeAd.Image image, NativeAd.Image image2, NativeAd.Rating rating, String str5, String str6, g gVar, Collection collection, boolean z, NativeAd.Image image3, String str7) {
        this.b = uri;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = image;
        this.h = image2;
        this.i = rating;
        this.j = str5;
        this.k = str6;
        this.l = gVar;
        this.m = collection;
        this.n = z;
        this.o = image3;
        this.p = str7;
    }

    public static aa a(JSONObject jSONObject) {
        JSONArray jSONArray;
        if (jSONObject == null) {
            return null;
        }
        Uri uri = Uri.parse(jSONObject.optString("fbad_command"));
        String strOptString = jSONObject.optString("title");
        String strOptString2 = jSONObject.optString(UnityAdsConstants.UNITY_ADS_FAILED_URL_BODY_KEY);
        String strOptString3 = jSONObject.optString("call_to_action");
        String strOptString4 = jSONObject.optString("social_context");
        NativeAd.Image imageFromJSONObject = NativeAd.Image.fromJSONObject(jSONObject.optJSONObject("icon"));
        NativeAd.Image imageFromJSONObject2 = NativeAd.Image.fromJSONObject(jSONObject.optJSONObject("image"));
        NativeAd.Rating ratingFromJSONObject = NativeAd.Rating.fromJSONObject(jSONObject.optJSONObject("star_rating"));
        String strOptString5 = jSONObject.optString("impression_report_url");
        String strOptString6 = jSONObject.optString("click_report_url");
        boolean zOptBoolean = jSONObject.optBoolean("manual_imp");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("ad_choices_icon");
        NativeAd.Image imageFromJSONObject3 = jSONObjectOptJSONObject != null ? NativeAd.Image.fromJSONObject(jSONObjectOptJSONObject) : null;
        String strOptString7 = jSONObject.optString("ad_choices_link_url");
        g gVarA = g.a(jSONObject.optString("invalidation_behavior"));
        try {
            jSONArray = new JSONArray(jSONObject.optString("detection_strings"));
        } catch (JSONException e) {
            e.printStackTrace();
            jSONArray = null;
        }
        aa aaVar = new aa(uri, strOptString, strOptString2, strOptString3, strOptString4, imageFromJSONObject, imageFromJSONObject2, ratingFromJSONObject, strOptString5, strOptString6, gVarA, h.a(jSONArray), zOptBoolean, imageFromJSONObject3, strOptString7);
        if (aaVar.n()) {
            return aaVar;
        }
        return null;
    }

    private Map m() {
        return Collections.singletonMap("mil", String.valueOf(true));
    }

    private boolean n() {
        return (this.c == null || this.c.length() <= 0 || this.e == null || this.e.length() <= 0 || this.g == null || this.h == null) ? false : true;
    }

    @Override // com.facebook.ads.a.e
    public g a() {
        return this.l;
    }

    public void a(Context context, Map map, boolean z) {
        if (!this.r) {
            HashMap map2 = new HashMap();
            if (map != null) {
                map2.put("touch", p.a(map));
            }
            if (z) {
                map2.putAll(m());
            }
            new ad(map2).execute(this.k);
            this.r = true;
            p.a(context, "Click logged");
        }
        com.facebook.ads.a.a.a aVarA = com.facebook.ads.a.a.b.a(context, this.b);
        if (aVarA != null) {
            try {
                aVarA.a(null);
            } catch (Exception e) {
                Log.e(a, "Error executing action", e);
            }
        }
    }

    public void a(boolean z) {
        if (this.q) {
            return;
        }
        new ad(z ? m() : null).execute(this.j);
        this.q = true;
    }

    @Override // com.facebook.ads.a.e
    public Collection b() {
        return this.m;
    }

    public NativeAd.Image c() {
        return this.g;
    }

    public NativeAd.Image d() {
        return this.h;
    }

    public String e() {
        return this.c;
    }

    public String f() {
        return this.d;
    }

    public String g() {
        return this.e;
    }

    public String h() {
        return this.f;
    }

    public NativeAd.Rating i() {
        return this.i;
    }

    public boolean j() {
        return this.n;
    }

    public NativeAd.Image k() {
        return this.o;
    }

    public String l() {
        return this.p;
    }
}
