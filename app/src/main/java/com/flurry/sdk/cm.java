package com.flurry.sdk;

import android.content.Context;
import com.flurry.android.impl.ads.protocol.v13.AdReportedId;
import com.flurry.android.impl.ads.protocol.v13.SdkAdLog;
import com.flurry.android.impl.ads.protocol.v13.SdkLogRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class cm implements hf {
    private static final String a = cm.class.getSimpleName();
    private final Map<String, at> b = Collections.synchronizedMap(new HashMap());
    private bc c;
    private h d;
    private g e;
    private ef f;
    private eo g;
    private File h;
    private fw<List<at>> i;
    private String j;

    @Override // com.flurry.sdk.hf
    public void a(Context context) {
        this.c = new bc();
        this.d = new h();
        this.e = new g();
        this.e.a();
        this.f = new ei();
        this.g = new ej();
        this.h = context.getFileStreamPath(f());
        this.i = new fw<>(context.getFileStreamPath(g()), ".yflurryadlog.", 1, new ha<List<at>>() { // from class: com.flurry.sdk.cm.1
            @Override // com.flurry.sdk.ha
            public gx<List<at>> a(int i) {
                return new gw(new at.a(new as.a()));
            }
        });
        this.j = cw.a(context);
        fp.a().b(new hq() { // from class: com.flurry.sdk.cm.4
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                cm.this.l();
            }
        });
    }

    @Override // com.flurry.sdk.hf
    public void b(Context context) {
        this.d.b();
        i.a().k().b();
        fp.a().b(new hq() { // from class: com.flurry.sdk.cm.5
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                i.a().l().e();
            }
        });
        fp.a().b(new hq() { // from class: com.flurry.sdk.cm.6
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                i.a().i().d();
            }
        });
    }

    @Override // com.flurry.sdk.hf
    public void c(Context context) {
        if (!ft.a().c()) {
            i.a().d().d(context);
            i.a().e().c(context);
        }
        fp.a().b(new hq() { // from class: com.flurry.sdk.cm.7
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                cm.this.k();
            }
        });
        fp.a().b(new hq() { // from class: com.flurry.sdk.cm.8
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                i.a().t();
            }
        });
        fp.a().b(new hq() { // from class: com.flurry.sdk.cm.9
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                i.a().u();
            }
        });
    }

    @Override // com.flurry.sdk.hf
    public void a() {
        this.d.a();
        i.a().d().a();
        i.a().e().a();
        fp.a().b(new hq() { // from class: com.flurry.sdk.cm.10
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                cm.this.j();
            }
        });
        fp.a().b(new hq() { // from class: com.flurry.sdk.cm.11
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                i.a().l().f();
            }
        });
        fp.a().b(new hq() { // from class: com.flurry.sdk.cm.2
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                i.a().i().c();
            }
        });
    }

    public bc b() {
        return this.c;
    }

    public g c() {
        return this.e;
    }

    public ef d() {
        return this.f;
    }

    public eo e() {
        return this.g;
    }

    String f() {
        return ".flurryadlog." + Integer.toString(fp.a().d().hashCode(), 16);
    }

    String g() {
        return ".yflurryadlog." + Long.toString(hp.i(fp.a().d()), 16);
    }

    public synchronized void h() {
        fp.a().b(new hq() { // from class: com.flurry.sdk.cm.3
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                cm.this.j();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void j() {
        SdkLogRequest sdkLogRequestA = a(new ArrayList(this.b.values()));
        if (sdkLogRequestA != null) {
            i.a().g().a(sdkLogRequestA, j.a().i(), fp.a().d(), "" + fq.a());
        }
        m();
    }

    private SdkLogRequest a(List<at> list) {
        List<SdkAdLog> listA = cr.a(list);
        if (listA.isEmpty()) {
            gd.a(3, a, "List of adLogs is empty");
            return null;
        }
        String strD = fp.a().d();
        List<AdReportedId> listE = cr.e();
        SdkLogRequest sdkLogRequest = new SdkLogRequest();
        sdkLogRequest.apiKey = strD;
        sdkLogRequest.adReportedIds = listE;
        sdkLogRequest.sdkAdLogs = listA;
        sdkLogRequest.testDevice = false;
        sdkLogRequest.agentTimestamp = System.currentTimeMillis();
        sdkLogRequest.agentVersion = Integer.toString(fq.a());
        gd.a(3, a, "Got ad log request:" + sdkLogRequest.toString());
        return sdkLogRequest;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void k() {
        gd.a(4, a, "Saving AdLog data.");
        this.i.a(new ArrayList(this.b.values()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void l() {
        gd.a(4, a, "Loading AdLog data.");
        List<at> listA = this.i.a();
        if (listA != null) {
            b(listA);
        } else if (this.h.exists()) {
            gd.a(4, a, "Legacy AdLog data found, converting.");
            List<at> listB = l.b(this.h);
            if (listB != null) {
                b(listB);
            }
            this.h.delete();
            k();
        }
    }

    public synchronized void a(String str, aw awVar, boolean z, Map<String, String> map) {
        if (awVar != null) {
            gd.a(3, a, "logAdEvent(" + str + ", " + awVar + ", " + z + ", " + map + ")");
            a(str).a(new as(awVar.a(), z, fd.a().f(), map));
        }
    }

    public String i() {
        return this.j;
    }

    private void b(List<at> list) {
        for (at atVar : list) {
            this.b.put(atVar.b(), atVar);
        }
    }

    public at a(String str) {
        at atVar = this.b.get(str);
        if (atVar == null) {
            atVar = new at(str);
            if (this.b.size() < 32767) {
                this.b.put(atVar.b(), atVar);
            }
        }
        return atVar;
    }

    private void m() {
        this.b.clear();
        this.i.b();
    }
}
