package com.facebook.ads.a;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b {
    private String a;
    private Map b;
    private int c;

    public enum a {
        OPEN_STORE(0),
        OPEN_LINK(1),
        XOUT(2),
        OPEN_URL(3),
        SHOW_INTERSTITIAL(4);

        int f;

        a(int i) {
            this.f = i;
        }
    }

    public b(String str, Map map, int i) {
        this.a = str;
        this.b = map;
        this.c = i;
    }

    public static b a(long j, a aVar) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        HashMap map = new HashMap();
        map.put("Time", String.valueOf(jCurrentTimeMillis - j));
        map.put("AdAction", String.valueOf(aVar.f));
        return new b("bounceback", map, (int) (jCurrentTimeMillis / 1000));
    }

    public static b a(Exception exc) {
        HashMap map = new HashMap();
        map.put("ex", exc.getClass().getSimpleName());
        map.put("ex_msg", exc.getMessage());
        return new b("error", map, (int) (System.currentTimeMillis() / 1000));
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.a);
            jSONObject.put("data", new JSONObject(this.b));
            jSONObject.put("time", this.c);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
