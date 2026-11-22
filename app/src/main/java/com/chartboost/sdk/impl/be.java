package com.chartboost.sdk.impl;

import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError;
import com.facebook.internal.AnalyticsEvents;
import java.io.File;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
public class be {
    public static be a;
    private static com.chartboost.sdk.Libraries.h c;
    private static m d;
    private static ConcurrentHashMap<Integer, b> e;
    private static a f;
    private static a g;
    private static com.chartboost.sdk.Model.a k;
    private static final String b = be.class.getSimpleName();
    private static AtomicInteger h = new AtomicInteger();
    private static AtomicInteger i = new AtomicInteger();
    private static boolean j = true;
    private static boolean l = false;
    private static Observer m = new Observer() { // from class: com.chartboost.sdk.impl.be.1
        @Override // java.util.Observer
        public void update(Observable observable, Object data) {
            be.n();
        }
    };
    private static ba.c n = new ba.c() { // from class: com.chartboost.sdk.impl.be.2
        @Override // com.chartboost.sdk.impl.ba.c
        public void a(com.chartboost.sdk.Libraries.e.a aVar, ba baVar) {
            synchronized (be.class) {
                a unused = be.f = a.kCBIntial;
                if (aVar != null) {
                    CBLogging.a(be.b, "Got Video list from server :)" + aVar);
                    be.a(aVar.a("videos"));
                }
            }
        }

        @Override // com.chartboost.sdk.impl.ba.c
        public void a(com.chartboost.sdk.Libraries.e.a aVar, ba baVar, CBError cBError) {
        }
    };

    public enum a {
        kCBIntial,
        kCBInProgress
    }

    private be() {
    }

    public static synchronized be a() {
        if (a == null) {
            a = new be();
            m();
        }
        return a;
    }

    private static synchronized void m() {
        if (!l) {
            l = true;
            c = new com.chartboost.sdk.Libraries.h("CBVideoDirectory", true);
            e = new ConcurrentHashMap<>();
            f = a.kCBIntial;
            g = a.kCBIntial;
            d = bb.a(com.chartboost.sdk.b.l()).a();
            az.a().addObserver(m);
        }
    }

    public static synchronized void b() {
        synchronized (be.class) {
            if (!l) {
                m();
            }
            CBLogging.a(b, "Calling Prfetch Video");
            if (a.kCBInProgress != f && a.kCBInProgress != g) {
                if (e != null && !e.isEmpty()) {
                    e.clear();
                    d.a(Integer.valueOf(m.hashCode()));
                    g = a.kCBIntial;
                    CBLogging.a(b, "prefetchVideo: Clearing all volley request for new start");
                }
                f = a.kCBInProgress;
                JSONArray jSONArray = new JSONArray();
                if (c() != null) {
                    for (String str : c()) {
                        jSONArray.put(str);
                    }
                }
                com.chartboost.sdk.Tracking.a.d();
                i.set(0);
                h.set(0);
                ba baVar = new ba("/api/video-prefetch");
                baVar.a("local-videos", jSONArray);
                baVar.a(com.chartboost.sdk.Libraries.g.a(com.chartboost.sdk.Libraries.g.a("status", com.chartboost.sdk.Libraries.a.a), com.chartboost.sdk.Libraries.g.a("videos", com.chartboost.sdk.Libraries.g.b(com.chartboost.sdk.Libraries.g.a(com.chartboost.sdk.Libraries.g.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, com.chartboost.sdk.Libraries.g.a(com.chartboost.sdk.Libraries.g.a())), com.chartboost.sdk.Libraries.g.a("id", com.chartboost.sdk.Libraries.g.a()))))));
                baVar.b(true);
                baVar.a(n);
            }
        }
    }

    public static synchronized void a(com.chartboost.sdk.Libraries.e.a aVar) {
        synchronized (be.class) {
            com.chartboost.sdk.Tracking.a.e();
            if (aVar != null) {
                HashMap map = new HashMap();
                HashMap map2 = new HashMap();
                String[] strArrC = c();
                for (int i2 = 0; i2 < aVar.o(); i2++) {
                    com.chartboost.sdk.Libraries.e.a aVarC = aVar.c(i2);
                    if (!aVarC.b("id") && !aVarC.b(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO)) {
                        String strE = aVarC.e("id");
                        String strE2 = aVarC.e(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO);
                        if (!c.c(strE)) {
                            map2.put(strE, strE2);
                            i.incrementAndGet();
                        }
                        map.put(strE, strE2);
                    }
                }
                if (!map2.isEmpty()) {
                    g = a.kCBInProgress;
                }
                if (j) {
                    j = false;
                }
                CBLogging.a(b, "synchronizeVideos: Delete and Download new videos");
                a(map, strArrC);
                if (com.chartboost.sdk.b.t()) {
                    a((HashMap<String, String>) map2);
                }
            }
        }
    }

    private static synchronized void a(HashMap<String, String> map, String[] strArr) {
        File fileD;
        CBLogging.a(b, "deleteVideos: Deleteing videos in cache");
        if (map != null && strArr != null) {
            for (String str : strArr) {
                if (!map.containsKey(str) && (fileD = c.d(str)) != null) {
                    c.c(fileD);
                }
            }
        }
    }

    private static synchronized void a(HashMap<String, String> map) {
        CBLogging.a(b, "downloadVideos: Downloading videos from server");
        for (String str : map.keySet()) {
            c cVar = new c();
            b bVar = new b(0, map.get(str), cVar, str);
            bVar.a((p) new d(30000, 0, 0.0f));
            cVar.a = bVar;
            bVar.a(Integer.valueOf(m.hashCode()));
            com.chartboost.sdk.Tracking.a.a(map.get(str), str);
            d.a((l) bVar);
        }
    }

    public static String[] c() {
        if (c == null) {
            return null;
        }
        return c.a();
    }

    private static class b extends l<Object> {
        private String a;
        private long b;
        private String c;

        public b(int i, String str, c cVar, String str2) {
            super(i, str, cVar);
            this.a = str2;
            this.c = str;
            this.b = System.currentTimeMillis();
        }

        @Override // com.chartboost.sdk.impl.l
        protected void b(Object obj) {
        }

        @Override // com.chartboost.sdk.impl.l
        protected n<Object> a(i iVar) {
            if (iVar != null) {
                com.chartboost.sdk.Tracking.a.d(this.a, Long.valueOf((System.currentTimeMillis() - this.b) / 1000).toString(), this.c);
                CBLogging.a(be.b, "Video download Success. Storing video in cache" + this.a);
                be.c.a(this.a, iVar.b);
                com.chartboost.sdk.Tracking.a.e("cache", "hit", this.a);
            }
            if (be.i.get() > 0 && be.h.incrementAndGet() >= 1 && be.k != null) {
                final com.chartboost.sdk.Model.a aVar = be.k;
                com.chartboost.sdk.Model.a unused = be.k = null;
                CBUtility.e().post(new Runnable() { // from class: com.chartboost.sdk.impl.be.b.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (aVar != null) {
                            aVar.q().r(aVar);
                        }
                    }
                });
            }
            if (be.h.get() == be.i.get()) {
                be.h.set(0);
                be.i.set(0);
                a unused2 = be.g = a.kCBIntial;
                be.e.clear();
            }
            return n.a(null, null);
        }

        @Override // com.chartboost.sdk.impl.l
        public l.a s() {
            return l.a.LOW;
        }
    }

    private static class c implements n.a {
        private b a;

        private c() {
        }

        @Override // com.chartboost.sdk.impl.n.a
        public void a(s sVar) {
            if ((sVar instanceof r) || (sVar instanceof q) || (sVar instanceof h)) {
                if (this.a != null) {
                    com.chartboost.sdk.Tracking.a.a(this.a.a, Long.valueOf((System.currentTimeMillis() - this.a.b) / 1000).toString(), this.a.c, sVar.getMessage());
                    com.chartboost.sdk.Tracking.a.e("cache", "miss", this.a.a);
                }
                be.e.put(Integer.valueOf(this.a.hashCode()), this.a);
                CBLogging.b(be.b, "Error downloading video " + sVar.getMessage() + this.a.a);
            }
        }
    }

    public static synchronized void d() {
        d.a(Integer.valueOf(m.hashCode()));
    }

    public static String a(String str) {
        if (c.c(str)) {
            return c.d(str).getPath();
        }
        return null;
    }

    public static String b(com.chartboost.sdk.Libraries.e.a aVar) {
        if (aVar == null) {
            return null;
        }
        com.chartboost.sdk.Libraries.e.a aVarA = aVar.a("assets");
        if (aVarA.b()) {
            return null;
        }
        com.chartboost.sdk.Libraries.e.a aVarA2 = aVarA.a(CBUtility.c().b() ? "video-portrait" : "video-landscape");
        if (aVarA2.b()) {
            return null;
        }
        String strE = aVarA2.e("id");
        if (TextUtils.isEmpty(strE)) {
            return null;
        }
        return a(strE);
    }

    public static void b(String str) {
        if (c.c(str)) {
            c.b(str);
        }
    }

    public static boolean c(com.chartboost.sdk.Libraries.e.a aVar) {
        return !TextUtils.isEmpty(b(aVar));
    }

    public static synchronized boolean e() {
        return g == a.kCBInProgress;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void n() {
        CBLogging.a(b, "Process Request called");
        if (f != a.kCBInProgress && g != a.kCBInProgress && ((g == a.kCBIntial && e != null) || e.size() > 0)) {
            for (Integer num : e.keySet()) {
                g = a.kCBInProgress;
                d.a((l) e.get(num));
                e.remove(num);
            }
        }
    }

    public static synchronized void a(com.chartboost.sdk.Model.a aVar) {
        k = aVar;
    }

    public static synchronized boolean b(com.chartboost.sdk.Model.a aVar) {
        boolean z;
        if (e()) {
            CBLogging.e(b, "Saving video impression request for video prefetch completion");
            a(aVar);
            z = true;
        } else {
            z = false;
        }
        return z;
    }
}
