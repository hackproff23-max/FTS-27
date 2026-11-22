package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class cc {
    public static final cd pQ = new cd() { // from class: com.google.android.gms.internal.cc.1
        @Override // com.google.android.gms.internal.cd
        public void a(gu guVar, Map<String, String> map) {
        }
    };
    public static final cd pR = new cd() { // from class: com.google.android.gms.internal.cc.2
        @Override // com.google.android.gms.internal.cd
        public void a(gu guVar, Map<String, String> map) {
            String str = map.get("urls");
            if (TextUtils.isEmpty(str)) {
                gr.W("URLs missing in canOpenURLs GMSG.");
                return;
            }
            String[] strArrSplit = str.split(",");
            HashMap map2 = new HashMap();
            PackageManager packageManager = guVar.getContext().getPackageManager();
            for (String str2 : strArrSplit) {
                String[] strArrSplit2 = str2.split(";", 2);
                map2.put(str2, Boolean.valueOf(packageManager.resolveActivity(new Intent(strArrSplit2.length > 1 ? strArrSplit2[1].trim() : "android.intent.action.VIEW", Uri.parse(strArrSplit2[0].trim())), 65536) != null));
            }
            guVar.a("openableURLs", map2);
        }
    };
    public static final cd pS = new cd() { // from class: com.google.android.gms.internal.cc.3
        @Override // com.google.android.gms.internal.cd
        public void a(gu guVar, Map<String, String> map) {
            PackageManager packageManager = guVar.getContext().getPackageManager();
            try {
                try {
                    JSONArray jSONArray = new JSONObject(map.get("data")).getJSONArray("intents");
                    JSONObject jSONObject = new JSONObject();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        try {
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                            String strOptString = jSONObject2.optString("id");
                            String strOptString2 = jSONObject2.optString("u");
                            String strOptString3 = jSONObject2.optString("i");
                            String strOptString4 = jSONObject2.optString("m");
                            String strOptString5 = jSONObject2.optString("p");
                            String strOptString6 = jSONObject2.optString("c");
                            jSONObject2.optString("f");
                            jSONObject2.optString("e");
                            Intent intent = new Intent();
                            if (!TextUtils.isEmpty(strOptString2)) {
                                intent.setData(Uri.parse(strOptString2));
                            }
                            if (!TextUtils.isEmpty(strOptString3)) {
                                intent.setAction(strOptString3);
                            }
                            if (!TextUtils.isEmpty(strOptString4)) {
                                intent.setType(strOptString4);
                            }
                            if (!TextUtils.isEmpty(strOptString5)) {
                                intent.setPackage(strOptString5);
                            }
                            if (!TextUtils.isEmpty(strOptString6)) {
                                String[] strArrSplit = strOptString6.split("/", 2);
                                if (strArrSplit.length == 2) {
                                    intent.setComponent(new ComponentName(strArrSplit[0], strArrSplit[1]));
                                }
                            }
                            try {
                                jSONObject.put(strOptString, packageManager.resolveActivity(intent, 65536) != null);
                            } catch (JSONException e) {
                                gr.b("Error constructing openable urls response.", e);
                            }
                        } catch (JSONException e2) {
                            gr.b("Error parsing the intent data.", e2);
                        }
                    }
                    guVar.b("openableIntents", jSONObject);
                } catch (JSONException e3) {
                    guVar.b("openableIntents", new JSONObject());
                }
            } catch (JSONException e4) {
                guVar.b("openableIntents", new JSONObject());
            }
        }
    };
    public static final cd pT = new cd() { // from class: com.google.android.gms.internal.cc.4
        @Override // com.google.android.gms.internal.cd
        public void a(gu guVar, Map<String, String> map) {
            Uri uriA;
            String str = map.get("u");
            if (str == null) {
                gr.W("URL missing from click GMSG.");
                return;
            }
            Uri uri = Uri.parse(str);
            try {
                k kVarDF = guVar.dF();
                uriA = (kVarDF == null || !kVarDF.b(uri)) ? uri : kVarDF.a(uri, guVar.getContext());
            } catch (l e) {
                gr.W("Unable to append parameter to URL: " + str);
            }
            new gp(guVar.getContext(), guVar.dG().wS, uriA.toString()).start();
        }
    };
    public static final cd pU = new cd() { // from class: com.google.android.gms.internal.cc.5
        @Override // com.google.android.gms.internal.cd
        public void a(gu guVar, Map<String, String> map) {
            dp dpVarDC = guVar.dC();
            if (dpVarDC == null) {
                gr.W("A GMSG tried to close something that wasn't an overlay.");
            } else {
                dpVarDC.close();
            }
        }
    };
    public static final cd pV = new cd() { // from class: com.google.android.gms.internal.cc.6
        @Override // com.google.android.gms.internal.cd
        public void a(gu guVar, Map<String, String> map) {
            guVar.q(AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("custom_close")));
        }
    };
    public static final cd pW = new cd() { // from class: com.google.android.gms.internal.cc.7
        @Override // com.google.android.gms.internal.cd
        public void a(gu guVar, Map<String, String> map) {
            String str = map.get("u");
            if (str == null) {
                gr.W("URL missing from httpTrack GMSG.");
            } else {
                new gp(guVar.getContext(), guVar.dG().wS, str).start();
            }
        }
    };
    public static final cd pX = new cd() { // from class: com.google.android.gms.internal.cc.8
        @Override // com.google.android.gms.internal.cd
        public void a(gu guVar, Map<String, String> map) {
            gr.U("Received log message: " + map.get("string"));
        }
    };
    public static final cd pY = new cd() { // from class: com.google.android.gms.internal.cc.9
        @Override // com.google.android.gms.internal.cd
        public void a(gu guVar, Map<String, String> map) {
            String str = map.get("tx");
            String str2 = map.get("ty");
            String str3 = map.get("td");
            try {
                int i = Integer.parseInt(str);
                int i2 = Integer.parseInt(str2);
                int i3 = Integer.parseInt(str3);
                k kVarDF = guVar.dF();
                if (kVarDF != null) {
                    kVarDF.C().a(i, i2, i3);
                }
            } catch (NumberFormatException e) {
                gr.W("Could not parse touch parameters from gmsg.");
            }
        }
    };
    public static final cd pZ = new cj();
}
