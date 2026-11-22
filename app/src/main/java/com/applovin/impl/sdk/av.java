package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class av {
    private static av d;
    private final Map a = new HashMap(1);
    private final Map b = new HashMap(1);
    private final Object c = new Object();

    private av() {
    }

    public static synchronized av a() {
        if (d == null) {
            d = new av();
        }
        return d;
    }

    public Map a(AppLovinAd appLovinAd) {
        Map map;
        AppLovinAdInternal appLovinAdInternal = (AppLovinAdInternal) appLovinAd;
        synchronized (this.c) {
            map = (Map) this.b.remove(appLovinAdInternal);
        }
        return map;
    }

    public void a(AppLovinAd appLovinAd, String str) {
        AppLovinAdInternal appLovinAdInternal = (AppLovinAdInternal) appLovinAd;
        synchronized (this.c) {
            this.a.put(appLovinAdInternal, str);
        }
    }

    public void a(AppLovinAd appLovinAd, Map map) {
        AppLovinAdInternal appLovinAdInternal = (AppLovinAdInternal) appLovinAd;
        synchronized (this.c) {
            this.b.put(appLovinAdInternal, map);
        }
    }

    public String b(AppLovinAd appLovinAd) {
        String str;
        AppLovinAdInternal appLovinAdInternal = (AppLovinAdInternal) appLovinAd;
        synchronized (this.c) {
            str = (String) this.a.remove(appLovinAdInternal);
        }
        return str;
    }
}
