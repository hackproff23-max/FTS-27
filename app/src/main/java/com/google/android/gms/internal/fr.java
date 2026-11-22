package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class fr {
    private static final SimpleDateFormat uD = new SimpleDateFormat("yyyyMMdd");

    public static fj a(Context context, fh fhVar, String str) {
        fj fjVar;
        List<String> list;
        List<String> list2;
        List<String> list3;
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("ad_base_url", null);
            String strOptString2 = jSONObject.optString("ad_url", null);
            String strOptString3 = jSONObject.optString("ad_size", null);
            String strOptString4 = jSONObject.optString("ad_html", null);
            long j = -1;
            String strOptString5 = jSONObject.optString("debug_dialog", null);
            long j2 = jSONObject.has("interstitial_timeout") ? (long) (jSONObject.getDouble("interstitial_timeout") * 1000.0d) : -1L;
            String strOptString6 = jSONObject.optString("orientation", null);
            int iDu = -1;
            if ("portrait".equals(strOptString6)) {
                iDu = gi.dv();
            } else if ("landscape".equals(strOptString6)) {
                iDu = gi.du();
            }
            if (TextUtils.isEmpty(strOptString4)) {
                if (TextUtils.isEmpty(strOptString2)) {
                    gr.W("Could not parse the mediation config: Missing required ad_html or ad_url field.");
                    return new fj(0);
                }
                fj fjVarA = fq.a(context, fhVar.lO.wS, strOptString2, (String) null, (fu) null);
                strOptString = fjVarA.sg;
                strOptString4 = fjVarA.tU;
                j = fjVarA.ua;
                fjVar = fjVarA;
            } else {
                if (TextUtils.isEmpty(strOptString)) {
                    gr.W("Could not parse the mediation config: Missing required ad_base_url field");
                    return new fj(0);
                }
                fjVar = null;
            }
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("click_urls");
            List<String> linkedList = fjVar == null ? null : fjVar.qw;
            if (jSONArrayOptJSONArray != null) {
                if (linkedList == null) {
                    linkedList = new LinkedList<>();
                }
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    linkedList.add(jSONArrayOptJSONArray.getString(i));
                }
                list = linkedList;
            } else {
                list = linkedList;
            }
            JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("impression_urls");
            List<String> linkedList2 = fjVar == null ? null : fjVar.qx;
            if (jSONArrayOptJSONArray2 != null) {
                if (linkedList2 == null) {
                    linkedList2 = new LinkedList<>();
                }
                for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                    linkedList2.add(jSONArrayOptJSONArray2.getString(i2));
                }
                list2 = linkedList2;
            } else {
                list2 = linkedList2;
            }
            JSONArray jSONArrayOptJSONArray3 = jSONObject.optJSONArray("manual_impression_urls");
            List<String> linkedList3 = fjVar == null ? null : fjVar.tY;
            if (jSONArrayOptJSONArray3 != null) {
                if (linkedList3 == null) {
                    linkedList3 = new LinkedList<>();
                }
                for (int i3 = 0; i3 < jSONArrayOptJSONArray3.length(); i3++) {
                    linkedList3.add(jSONArrayOptJSONArray3.getString(i3));
                }
                list3 = linkedList3;
            } else {
                list3 = linkedList3;
            }
            if (fjVar != null) {
                if (fjVar.orientation != -1) {
                    iDu = fjVar.orientation;
                }
                if (fjVar.tV > 0) {
                    j2 = fjVar.tV;
                }
            }
            String strOptString7 = jSONObject.optString("active_view");
            boolean zOptBoolean = jSONObject.optBoolean("ad_is_javascript", false);
            return new fj(strOptString, strOptString4, list, list2, j2, false, -1L, list3, -1L, iDu, strOptString3, j, strOptString5, zOptBoolean, zOptBoolean ? jSONObject.optString("ad_passback_url", null) : null, strOptString7, false, false, fhVar.tT, false);
        } catch (JSONException e) {
            gr.W("Could not parse the mediation config: " + e.getMessage());
            return new fj(0);
        }
    }

    public static String a(fh fhVar, fv fvVar, Location location, String str, String str2, String str3) {
        try {
            HashMap map = new HashMap();
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(str);
            }
            if (!TextUtils.isEmpty(str2)) {
                arrayList.add(str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                arrayList.add(str3);
            }
            if (arrayList.size() > 0) {
                map.put("eid", TextUtils.join(",", arrayList));
            }
            if (fhVar.tK != null) {
                map.put("ad_pos", fhVar.tK);
            }
            a((HashMap<String, Object>) map, fhVar.tL);
            map.put("format", fhVar.lS.op);
            if (fhVar.lS.width == -1) {
                map.put("smart_w", "full");
            }
            if (fhVar.lS.height == -2) {
                map.put("smart_h", "auto");
            }
            if (fhVar.lS.or != null) {
                StringBuilder sb = new StringBuilder();
                for (ay ayVar : fhVar.lS.or) {
                    if (sb.length() != 0) {
                        sb.append("|");
                    }
                    sb.append(ayVar.width == -1 ? (int) (ayVar.widthPixels / fvVar.vw) : ayVar.width);
                    sb.append("x");
                    sb.append(ayVar.height == -2 ? (int) (ayVar.heightPixels / fvVar.vw) : ayVar.height);
                }
                map.put("sz", sb);
            }
            if (fhVar.tR != 0) {
                map.put("native_version", Integer.valueOf(fhVar.tR));
                map.put("native_templates", fhVar.mc);
            }
            map.put("slotname", fhVar.lL);
            map.put("pn", fhVar.applicationInfo.packageName);
            if (fhVar.tM != null) {
                map.put("vc", Integer.valueOf(fhVar.tM.versionCode));
            }
            map.put("ms", fhVar.tN);
            map.put("seq_num", fhVar.tO);
            map.put("session_id", fhVar.tP);
            map.put("js", fhVar.lO.wS);
            a((HashMap<String, Object>) map, fvVar);
            if (fhVar.tL.versionCode >= 2 && fhVar.tL.ol != null) {
                a((HashMap<String, Object>) map, fhVar.tL.ol);
            }
            if (fhVar.versionCode >= 2) {
                map.put("quality_signals", fhVar.tQ);
            }
            if (fhVar.versionCode >= 4 && fhVar.tT) {
                map.put("forceHttps", Boolean.valueOf(fhVar.tT));
            }
            if (fhVar.versionCode >= 3 && fhVar.tS != null) {
                map.put("content_info", fhVar.tS);
            }
            if (gr.v(2)) {
                gr.V("Ad Request JSON: " + gi.t(map).toString(2));
            }
            return gi.t(map).toString();
        } catch (JSONException e) {
            gr.W("Problem serializing ad request to JSON: " + e.getMessage());
            return null;
        }
    }

    private static void a(HashMap<String, Object> map, Location location) {
        HashMap map2 = new HashMap();
        Float fValueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
        Long lValueOf = Long.valueOf(location.getTime() * 1000);
        Long lValueOf2 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
        Long lValueOf3 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
        map2.put("radius", fValueOf);
        map2.put("lat", lValueOf2);
        map2.put("long", lValueOf3);
        map2.put("time", lValueOf);
        map.put("uule", map2);
    }

    private static void a(HashMap<String, Object> map, av avVar) {
        String strDr = ge.dr();
        if (strDr != null) {
            map.put("abf", strDr);
        }
        if (avVar.od != -1) {
            map.put("cust_age", uD.format(new Date(avVar.od)));
        }
        if (avVar.extras != null) {
            map.put("extras", avVar.extras);
        }
        if (avVar.oe != -1) {
            map.put("cust_gender", Integer.valueOf(avVar.oe));
        }
        if (avVar.of != null) {
            map.put("kw", avVar.of);
        }
        if (avVar.oh != -1) {
            map.put("tag_for_child_directed_treatment", Integer.valueOf(avVar.oh));
        }
        if (avVar.og) {
            map.put("adtest", "on");
        }
        if (avVar.versionCode >= 2) {
            if (avVar.oi) {
                map.put("d_imp_hdr", 1);
            }
            if (!TextUtils.isEmpty(avVar.oj)) {
                map.put("ppid", avVar.oj);
            }
            if (avVar.ok != null) {
                a(map, avVar.ok);
            }
        }
        if (avVar.versionCode < 3 || avVar.om == null) {
            return;
        }
        map.put("url", avVar.om);
    }

    private static void a(HashMap<String, Object> map, bj bjVar) {
        String str;
        String str2 = null;
        if (Color.alpha(bjVar.oQ) != 0) {
            map.put("acolor", u(bjVar.oQ));
        }
        if (Color.alpha(bjVar.backgroundColor) != 0) {
            map.put("bgcolor", u(bjVar.backgroundColor));
        }
        if (Color.alpha(bjVar.oR) != 0 && Color.alpha(bjVar.oS) != 0) {
            map.put("gradientto", u(bjVar.oR));
            map.put("gradientfrom", u(bjVar.oS));
        }
        if (Color.alpha(bjVar.oT) != 0) {
            map.put("bcolor", u(bjVar.oT));
        }
        map.put("bthick", Integer.toString(bjVar.oU));
        switch (bjVar.oV) {
            case 0:
                str = "none";
                break;
            case 1:
                str = "dashed";
                break;
            case 2:
                str = "dotted";
                break;
            case 3:
                str = "solid";
                break;
            default:
                str = null;
                break;
        }
        if (str != null) {
            map.put("btype", str);
        }
        switch (bjVar.oW) {
            case 0:
                str2 = "light";
                break;
            case 1:
                str2 = "medium";
                break;
            case 2:
                str2 = "dark";
                break;
        }
        if (str2 != null) {
            map.put("callbuttoncolor", str2);
        }
        if (bjVar.oX != null) {
            map.put("channel", bjVar.oX);
        }
        if (Color.alpha(bjVar.oY) != 0) {
            map.put("dcolor", u(bjVar.oY));
        }
        if (bjVar.oZ != null) {
            map.put("font", bjVar.oZ);
        }
        if (Color.alpha(bjVar.pa) != 0) {
            map.put("hcolor", u(bjVar.pa));
        }
        map.put("headersize", Integer.toString(bjVar.pb));
        if (bjVar.pc != null) {
            map.put("q", bjVar.pc);
        }
    }

    private static void a(HashMap<String, Object> map, fv fvVar) {
        map.put("am", Integer.valueOf(fvVar.vg));
        map.put("cog", u(fvVar.vh));
        map.put("coh", u(fvVar.vi));
        if (!TextUtils.isEmpty(fvVar.vj)) {
            map.put("carrier", fvVar.vj);
        }
        map.put("gl", fvVar.vk);
        if (fvVar.vl) {
            map.put("simulator", 1);
        }
        map.put("ma", u(fvVar.vm));
        map.put("sp", u(fvVar.vn));
        map.put("hl", fvVar.vo);
        if (!TextUtils.isEmpty(fvVar.vp)) {
            map.put("mv", fvVar.vp);
        }
        map.put("muv", Integer.valueOf(fvVar.vq));
        if (fvVar.vr != -2) {
            map.put("cnt", Integer.valueOf(fvVar.vr));
        }
        map.put("gnt", Integer.valueOf(fvVar.vs));
        map.put("pt", Integer.valueOf(fvVar.vt));
        map.put("rm", Integer.valueOf(fvVar.vu));
        map.put("riv", Integer.valueOf(fvVar.vv));
        map.put("u_sd", Float.valueOf(fvVar.vw));
        map.put("sh", Integer.valueOf(fvVar.vy));
        map.put("sw", Integer.valueOf(fvVar.vx));
        Bundle bundle = new Bundle();
        bundle.putInt("active_network_state", fvVar.vC);
        bundle.putBoolean("active_network_metered", fvVar.vB);
        map.put("connectivity", bundle);
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean("is_charging", fvVar.vA);
        bundle2.putDouble("battery_level", fvVar.vz);
        map.put("battery", bundle2);
    }

    private static Integer u(boolean z) {
        return Integer.valueOf(z ? 1 : 0);
    }

    private static String u(int i) {
        return String.format(Locale.US, "#%06x", Integer.valueOf(16777215 & i));
    }
}
