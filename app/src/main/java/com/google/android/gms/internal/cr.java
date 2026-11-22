package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class cr {
    public final long qA;
    public int qB;
    public int qC;
    public final List<cq> qu;
    public final long qv;
    public final List<String> qw;
    public final List<String> qx;
    public final List<String> qy;
    public final String qz;

    public cr(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        if (gr.v(2)) {
            gr.V("Mediation Response JSON: " + jSONObject.toString(2));
        }
        JSONArray jSONArray = jSONObject.getJSONArray("ad_networks");
        ArrayList arrayList = new ArrayList(jSONArray.length());
        int i = -1;
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            cq cqVar = new cq(jSONArray.getJSONObject(i2));
            arrayList.add(cqVar);
            if (i < 0 && a(cqVar)) {
                i = i2;
            }
        }
        this.qB = i;
        this.qC = jSONArray.length();
        this.qu = Collections.unmodifiableList(arrayList);
        this.qz = jSONObject.getString("qdata");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("settings");
        if (jSONObjectOptJSONObject == null) {
            this.qv = -1L;
            this.qw = null;
            this.qx = null;
            this.qy = null;
            this.qA = -1L;
            return;
        }
        this.qv = jSONObjectOptJSONObject.optLong("ad_network_timeout_millis", -1L);
        this.qw = cw.a(jSONObjectOptJSONObject, "click_urls");
        this.qx = cw.a(jSONObjectOptJSONObject, "imp_urls");
        this.qy = cw.a(jSONObjectOptJSONObject, "nofill_urls");
        long jOptLong = jSONObjectOptJSONObject.optLong("refresh", -1L);
        this.qA = jOptLong > 0 ? jOptLong * 1000 : -1L;
    }

    private boolean a(cq cqVar) {
        Iterator<String> it = cqVar.qp.iterator();
        while (it.hasNext()) {
            if (it.next().equals("com.google.ads.mediation.admob.AdMobAdapter")) {
                return true;
            }
        }
        return false;
    }
}
