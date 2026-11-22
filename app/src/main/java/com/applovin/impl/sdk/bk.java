package com.applovin.impl.sdk;

import android.graphics.Point;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.google.android.vending.expansion.downloader.Constants;
import com.mopub.common.AdType;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class bk extends ba implements cc {
    private final AppLovinAdSize a;
    private final AppLovinAdType b;
    private final AppLovinAdLoadListener c;
    private boolean d;

    bk(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("FetchNextAd", appLovinSdkImpl);
        this.d = false;
        this.a = appLovinAdSize;
        this.b = appLovinAdType;
        this.c = appLovinAdLoadListener;
    }

    private void a(bg bgVar) {
        if (System.currentTimeMillis() - bgVar.b("ad_session_start") > ((long) ((Integer) this.f.a(bb.x)).intValue()) * Constants.WATCHDOG_WAKE_TIMER) {
            bgVar.b("ad_session_start", System.currentTimeMillis());
            bgVar.c("ad_imp_session");
        }
    }

    private void b(Map map) {
        bz bzVarA = bx.a().a("tFNA");
        if (bzVarA != null) {
            map.put("etf", Long.toString(bzVarA.b()));
            map.put("ntf", bzVarA.a());
        }
        bz bzVarA2 = bx.a().a("tRA");
        if (bzVarA2 != null) {
            map.put("etr", Long.toString(bzVarA2.b()));
            map.put("ntr", bzVarA2.a());
        }
    }

    private void c(Map map) {
        map.put("api_did", this.f.a(bb.c));
        map.put("sdk_key", this.f.getSdkKey());
        map.put("sdk_version", "5.4.3");
        String str = (String) this.f.a(bb.F);
        if (str != null && str.length() > 0) {
            map.put("plugin_version", str);
        }
        map.put("accept", g());
        map.put("preloading", String.valueOf(this.d));
        map.put("size", this.a.getLabel());
        map.put("format", AdType.NATIVE);
    }

    private void d(Map map) {
        if (((Boolean) this.f.a(bb.N)).booleanValue()) {
            bg bgVarB = this.f.b();
            map.put("li", String.valueOf(bgVarB.b("ad_imp")));
            map.put("si", String.valueOf(bgVarB.b("ad_imp_session")));
        }
    }

    private void e(Map map) {
        Map mapA;
        if (!((Boolean) this.f.a(bb.N)).booleanValue() || (mapA = ((l) this.f.getTargetingData()).a()) == null || mapA.isEmpty()) {
            return;
        }
        map.putAll(mapA);
    }

    private void f(Map map) {
        Map mapA = a.a(this.f);
        if (mapA.isEmpty()) {
            try {
                g(mapA);
                a.a(mapA, this.f);
            } catch (Exception e) {
                this.g.e(this.e, "Unable to populate device information", e);
            }
        }
        map.putAll(mapA);
        map.put("network", p.a(this.f));
        i(map);
        map.put("vz", cd.a(this.f.getApplicationContext().getPackageName(), this.f));
    }

    private String g() {
        return (m.b() && m.a(AppLovinInterstitialActivity.class, this.h) && m.a("android.permission.WRITE_EXTERNAL_STORAGE", this.h)) ? "custom_size,launch_app,video" : "custom_size,launch_app";
    }

    private void g(Map map) {
        t tVarA = c().a();
        map.put("brand", cd.b(tVarA.c));
        map.put("carrier", cd.b(tVarA.g));
        map.put("locale", tVarA.h.toString());
        map.put("model", cd.b(tVarA.a));
        map.put("os", cd.b(tVarA.b));
        map.put("platform", "android");
        map.put("revision", cd.b(tVarA.d));
        h(map);
    }

    private void h(Map map) {
        Point pointA = m.a(this.f.getApplicationContext());
        map.put("dx", Integer.toString(pointA.x));
        map.put("dy", Integer.toString(pointA.y));
    }

    private void i(Map map) {
        r rVarC = c().c();
        String str = rVarC.b;
        if (rVarC.a || !cd.c(str)) {
            return;
        }
        map.put("idfa", str);
    }

    private void j(Map map) {
        if (this.b != null) {
            map.put("require", this.b.getLabel());
        }
    }

    void a(int i) {
        this.g.e(this.e, "Unable to fetch " + this.a + " ad: server returned " + i);
        try {
            if (this.c != null) {
                if (this.c instanceof u) {
                    ((u) this.c).a(new c(this.a, this.b), i);
                } else {
                    this.c.failedToReceiveAd(i);
                }
            }
        } catch (Throwable th) {
            this.g.e(this.e, "Unable process a failure to recieve an ad", th);
        }
        p.b(i, this.f);
    }

    protected void a(Map map) {
        e(map);
        f(map);
        d(map);
        c(map);
        j(map);
        b(map);
    }

    void a(JSONObject jSONObject) {
        this.f.a().a(new bs(jSONObject, this.c, this.f), bo.MAIN);
        p.a(jSONObject, this.f);
    }

    public void a(boolean z) {
        this.d = z;
    }

    @Override // com.applovin.impl.sdk.ba
    void b() {
        super.b();
        a(-410);
    }

    String d() {
        HashMap map = new HashMap();
        a(map);
        StringBuffer stringBuffer = new StringBuffer(e());
        stringBuffer.append("?").append(cd.a(map));
        return stringBuffer.toString();
    }

    String e() {
        return p.b("ad", this.f);
    }

    @Override // com.applovin.impl.sdk.cc
    public String f() {
        return "tFNA";
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.d) {
            this.g.d(this.e, "Preloading next ad...");
        } else {
            this.g.d(this.e, "Fetching next ad...");
        }
        bg bgVarB = this.f.b();
        bgVarB.a("ad_req");
        a(bgVarB);
        try {
            bl blVar = new bl(this, "RepeatFetchNextAd", bb.i, this.f);
            blVar.a(bb.l);
            blVar.run();
        } catch (Throwable th) {
            this.g.e(this.e, "Unable to fetch " + this.a + " ad", th);
            a(0);
        }
    }
}
