package com.applovin.impl.sdk;

import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class bg {
    private final AppLovinSdkImpl a;
    private final Map b = new HashMap();

    bg(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = appLovinSdkImpl;
    }

    void a() {
        synchronized (this.b) {
            this.b.clear();
        }
        d();
    }

    void a(String str) {
        a(str, 1L);
    }

    void a(String str, long j) {
        synchronized (this.b) {
            Long l = (Long) this.b.get(str);
            if (l == null) {
                l = 0L;
            }
            this.b.put(str, Long.valueOf(l.longValue() + j));
        }
        d();
    }

    long b(String str) {
        long jLongValue;
        synchronized (this.b) {
            Long l = (Long) this.b.get(str);
            if (l == null) {
                l = 0L;
            }
            jLongValue = l.longValue();
        }
        return jLongValue;
    }

    JSONObject b() {
        JSONObject jSONObject;
        synchronized (this.b) {
            jSONObject = new JSONObject();
            for (Map.Entry entry : this.b.entrySet()) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
        }
        return jSONObject;
    }

    void b(String str, long j) {
        synchronized (this.b) {
            this.b.put(str, Long.valueOf(j));
        }
        d();
    }

    void c() {
        try {
            JSONObject jSONObject = new JSONObject(this.a.getSettingsManager().a().getString("stats", "{}"));
            synchronized (this.b) {
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    try {
                        String next = itKeys.next();
                        this.b.put(next, Long.valueOf(jSONObject.getLong(next)));
                    } catch (JSONException e) {
                    }
                }
            }
        } catch (Throwable th) {
            this.a.getLogger().e("StatsManager", "Unable to load stats", th);
        }
    }

    void c(String str) {
        synchronized (this.b) {
            this.b.remove(str);
        }
        d();
    }

    void d() {
        try {
            SharedPreferences.Editor editorEdit = this.a.getSettingsManager().a().edit();
            editorEdit.putString("stats", b().toString());
            editorEdit.commit();
        } catch (JSONException e) {
            this.a.getLogger().e("StatsManager", "Unable to save stats", e);
        }
    }
}
