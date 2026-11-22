package com.flurry.sdk;

import android.location.Criteria;
import android.location.Location;
import com.google.android.vending.expansion.downloader.impl.DownloaderService;

/* JADX INFO: loaded from: classes.dex */
public class hg extends hh {
    public static final Integer a = Integer.valueOf(DownloaderService.STATUS_WAITING_FOR_NETWORK);
    public static final Integer b = 5;
    public static final Integer c = 3;
    public static final Integer d = 0;
    public static final String e = null;
    public static final Boolean f = true;
    public static final Boolean g = true;
    public static final String h = null;
    public static final Boolean i = true;
    public static final Criteria j = null;
    public static final Location k = null;
    public static final Long l = 10000L;
    public static final Boolean m = true;
    public static final Long n = null;
    public static final Byte o = (byte) -1;
    private static hg p;

    public static synchronized hg a() {
        if (p == null) {
            p = new hg();
        }
        return p;
    }

    public static synchronized void b() {
        if (p != null) {
            p.d();
        }
        p = null;
    }

    private hg() {
        c();
    }

    public void c() {
        a("AgentVersion", (Object) a);
        a("ReleaseMajorVersion", (Object) b);
        a("ReleaseMinorVersion", (Object) c);
        a("ReleasePatchVersion", (Object) d);
        a("ReleaseBetaVersion", "");
        a("VersionName", (Object) e);
        a("CaptureUncaughtExceptions", (Object) f);
        a("UseHttps", (Object) g);
        a("ReportUrl", (Object) h);
        a("ReportLocation", (Object) i);
        a("ExplicitLocation", (Object) k);
        a("ContinueSessionMillis", (Object) l);
        a("LogEvents", (Object) m);
        a("Age", (Object) n);
        a("Gender", (Object) o);
        a("UserId", "");
    }
}
