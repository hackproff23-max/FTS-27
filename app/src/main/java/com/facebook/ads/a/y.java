package com.facebook.ads.a;

import android.content.Intent;
import android.os.Bundle;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class y implements e {
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final g f;
    private final Collection g;
    private final Map h;

    private y(String str, String str2, String str3, String str4, String str5, g gVar, Collection collection, Map map) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = gVar;
        this.g = collection;
        this.h = map;
    }

    public static y a(Bundle bundle) {
        return new y(bundle.getString("markup"), null, bundle.getString("activation_command"), null, bundle.getString("native_impression_report_url"), g.NONE, null, null);
    }

    public static y a(JSONObject jSONObject) {
        JSONArray jSONArray;
        if (jSONObject == null) {
            return null;
        }
        String strOptString = jSONObject.optString("markup");
        String strOptString2 = jSONObject.optString("secondary_markup");
        String strOptString3 = jSONObject.optString("activation_command");
        String strOptString4 = jSONObject.optString("secondary_activation_command");
        String strOptString5 = jSONObject.optString("native_impression_report_url");
        g gVarA = g.a(jSONObject.optString("invalidation_behavior"));
        try {
            jSONArray = new JSONArray(jSONObject.optString("detection_strings"));
        } catch (JSONException e) {
            e.printStackTrace();
            jSONArray = null;
        }
        Collection collectionA = h.a(jSONArray);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("metadata");
        HashMap map = new HashMap();
        if (jSONObjectOptJSONObject != null) {
            Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                map.put(next, jSONObjectOptJSONObject.optString(next));
            }
        }
        return new y(strOptString, strOptString2, strOptString3, strOptString4, strOptString5, gVarA, collectionA, map);
    }

    public static y b(Intent intent) {
        return new y(intent.getStringExtra("markup"), null, intent.getStringExtra("activation_command"), null, intent.getStringExtra("native_impression_report_url"), g.NONE, null, null);
    }

    @Override // com.facebook.ads.a.e
    public g a() {
        return this.f;
    }

    public void a(Intent intent) {
        intent.putExtra("markup", this.a);
        intent.putExtra("activation_command", this.c);
        intent.putExtra("native_impression_report_url", this.e);
    }

    @Override // com.facebook.ads.a.e
    public Collection b() {
        return this.g;
    }

    public String c() {
        return this.a;
    }

    public String d() {
        return this.c;
    }

    public String e() {
        return this.e;
    }

    public String f() {
        return "facebookAd.sendImpression();";
    }

    public boolean g() {
        return this.h.containsKey("preload") && Boolean.valueOf((String) this.h.get("preload")).booleanValue();
    }

    public Map h() {
        return this.h;
    }

    public Bundle i() {
        Bundle bundle = new Bundle();
        bundle.putString("markup", this.a);
        bundle.putString("native_impression_report_url", this.e);
        return bundle;
    }

    public Map j() {
        HashMap map = new HashMap();
        map.put("markup", this.b);
        map.put("activation_command", this.d);
        map.put("native_impression_report_url", this.e);
        return map;
    }
}
