package com.chartboost.sdk.impl;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError;
import com.facebook.GraphResponse;
import com.facebook.share.internal.ShareConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
import org.json.JSONTokener;

/* JADX INFO: loaded from: classes.dex */
public class bb implements Observer {
    private static bb b;
    private static ConcurrentHashMap<ba, File> e;
    private static ConcurrentHashMap<ba, File> f;
    private static List<Runnable> g = new ArrayList();
    private az a;
    private m c;
    private com.chartboost.sdk.Libraries.h d = new com.chartboost.sdk.Libraries.h("CBRequestManager", false);
    private CountDownTimer h;

    public static bb a(Context context) {
        if (b == null) {
            synchronized (bb.class) {
                if (b == null) {
                    b = new bb(context);
                }
            }
        }
        return b;
    }

    private bb(Context context) {
        this.a = null;
        this.c = ad.a(context.getApplicationContext());
        this.a = az.a();
        e = new ConcurrentHashMap<>();
        f = new ConcurrentHashMap<>();
        this.a.addObserver(this);
    }

    public m a() {
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ba baVar, i iVar, CBError cBError, boolean z) {
        if (baVar != null) {
            com.chartboost.sdk.Libraries.e.b[] bVarArr = new com.chartboost.sdk.Libraries.e.b[5];
            bVarArr[0] = com.chartboost.sdk.Libraries.e.a("endpoint", baVar.g());
            bVarArr[1] = com.chartboost.sdk.Libraries.e.a("statuscode", iVar == null ? "None" : Integer.valueOf(iVar.a));
            bVarArr[2] = com.chartboost.sdk.Libraries.e.a("error", cBError == null ? "None" : cBError.a());
            bVarArr[3] = com.chartboost.sdk.Libraries.e.a("errorDescription", cBError == null ? "None" : cBError.b());
            bVarArr[4] = com.chartboost.sdk.Libraries.e.a("retryCount", Integer.valueOf(baVar.o()));
            com.chartboost.sdk.Tracking.a.a("request_manager", ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, z ? GraphResponse.SUCCESS_KEY : "failure", (String) null, (String) null, (String) null, com.chartboost.sdk.Libraries.e.a(bVarArr).e());
        }
    }

    protected void a(ba baVar, ba.c cVar) {
        if (baVar != null) {
            if (!this.a.c()) {
                CBError cBError = new CBError(CBError.a.INTERNET_UNAVAILABLE, null);
                baVar.d(false);
                if (!baVar.h()) {
                    if (baVar.p()) {
                        baVar.c(false);
                        a(baVar);
                    }
                    a(baVar, null, cBError, false);
                    if (cVar != null) {
                        cVar.a(null, baVar, cBError);
                        return;
                    }
                    return;
                }
                return;
            }
            if (!baVar.h() && baVar.p()) {
                baVar.c(false);
                a(baVar);
            }
            a(new c(baVar));
        }
    }

    public void a(Runnable runnable) {
        boolean z = false;
        synchronized (com.chartboost.sdk.Libraries.c.class) {
            com.chartboost.sdk.Libraries.c.a aVarC = com.chartboost.sdk.Libraries.c.c();
            if (aVarC == com.chartboost.sdk.Libraries.c.a.PRELOAD || aVarC == com.chartboost.sdk.Libraries.c.a.LOADING) {
                g.add(runnable);
            } else {
                z = true;
            }
        }
        if (z) {
            ay.a().execute(runnable);
        }
    }

    public static void b() {
        ArrayList arrayList = new ArrayList();
        synchronized (com.chartboost.sdk.Libraries.c.class) {
            arrayList.addAll(g);
            g.clear();
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ay.a().execute((Runnable) it.next());
        }
    }

    private class c implements Runnable {
        private ba b;

        public c(ba baVar) {
            this.b = baVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.b.a(com.chartboost.sdk.b.l());
            this.b.c();
            String str = String.format("%s%s", "https://live.chartboost.com", this.b.e());
            this.b.d();
            this.b.a();
            a aVar = new a(1, str, this.b);
            aVar.a((p) new d(30000, 0, 0.0f));
            bb.this.c.a((l) aVar);
        }

        private class a extends l<b> {
            private ba b;

            public a(int i, String str, ba baVar) {
                super(i, str, null);
                this.b = baVar;
            }

            @Override // com.chartboost.sdk.impl.l
            public String p() {
                String strB = this.b.b();
                if (strB == null) {
                    return "application/json; charset=utf-8";
                }
                return strB;
            }

            @Override // com.chartboost.sdk.impl.l
            public byte[] q() {
                return (this.b.i() == null ? "" : this.b.i().toString()).getBytes();
            }

            @Override // com.chartboost.sdk.impl.l
            public l.a s() {
                return this.b.n();
            }

            @Override // com.chartboost.sdk.impl.l
            public Map<String, String> i() throws com.chartboost.sdk.impl.a {
                HashMap map = new HashMap();
                for (Map.Entry<String, Object> entry : this.b.j().entrySet()) {
                    map.put(entry.getKey(), entry.getValue() != null ? entry.getValue().toString() : null);
                }
                return map;
            }

            @Override // com.chartboost.sdk.impl.l
            protected n<b> a(i iVar) {
                com.chartboost.sdk.Libraries.e.a aVarA;
                com.chartboost.sdk.Libraries.e.a aVar;
                CBError cBError;
                CBError cBError2;
                int i = iVar.a;
                if (i > 300 && i < 200) {
                    CBLogging.d("CBRequestManager", "Request " + this.b.g() + " failed. Response code: " + i);
                    cBError = new CBError(CBError.a.NETWORK_FAILURE, "Request failed. Response code: " + i + " is not valid ");
                    aVar = null;
                } else {
                    try {
                        byte[] bArr = iVar.b;
                        String str = bArr != null ? new String(bArr) : null;
                        if (str != null) {
                            aVarA = com.chartboost.sdk.Libraries.e.a.a(new JSONObject(new JSONTokener(str)));
                            try {
                                com.chartboost.sdk.Libraries.g.a aVarL = this.b.l();
                                CBLogging.c("CBRequestManager", "Request " + this.b.g() + " succeeded. Response code: " + i + ", body: " + str);
                                if (aVarA.f("status") == 404) {
                                    cBError2 = new CBError(CBError.a.HTTP_NOT_FOUND, "404 error from server");
                                } else {
                                    StringBuilder sb = new StringBuilder();
                                    if (aVarL == null || aVarL.a(aVarA, sb)) {
                                        cBError2 = null;
                                    } else {
                                        cBError2 = new CBError(CBError.a.UNEXPECTED_RESPONSE, "Json response failed validation");
                                        CBLogging.b("CBRequestManager", "Json response failed validation: " + sb.toString());
                                    }
                                }
                                cBError = cBError2;
                                aVar = aVarA;
                            } catch (Exception e) {
                                e = e;
                                CBError cBError3 = new CBError(CBError.a.MISCELLANEOUS, e.getLocalizedMessage());
                                aVar = aVarA;
                                cBError = cBError3;
                            }
                        } else {
                            cBError = new CBError(CBError.a.INVALID_RESPONSE, "Response is not a valid json object");
                            aVar = null;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        aVarA = null;
                    }
                }
                if (aVar != null && cBError == null) {
                    return n.a(new b(com.chartboost.sdk.Libraries.e.a.a(aVar), iVar), null);
                }
                return n.a(new a(cBError));
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chartboost.sdk.impl.l
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void b(b bVar) {
                if (c.this.b.r() != null && bVar != null) {
                    c.this.b.r().a(bVar.a, c.this.b);
                }
                if (!c.this.b.h()) {
                    bb.this.d.c((File) bb.e.get(c.this.b));
                    bb.e.remove(c.this.b);
                    bb.this.a(c.this.b, bVar.b, null, true);
                } else {
                    com.chartboost.sdk.Tracking.a.a().m().c((File) bb.f.get(c.this.b));
                    com.chartboost.sdk.Tracking.a.a().q();
                    CBLogging.a("CBRequestManager", "### Removing track events sent to server...");
                    bb.f.remove(c.this.b);
                }
            }

            @Override // com.chartboost.sdk.impl.l
            public void b(s sVar) {
                CBError cBError;
                if (sVar != null) {
                    if (!(sVar instanceof a)) {
                        cBError = new CBError(CBError.a.NETWORK_FAILURE, sVar.getMessage());
                    } else {
                        cBError = ((a) sVar).b;
                    }
                    com.chartboost.sdk.Libraries.e.a aVarJ = com.chartboost.sdk.Libraries.e.a.a;
                    if (sVar != null) {
                        try {
                            if (sVar.a != null && sVar.a.b != null && sVar.a.b.length > 0) {
                                aVarJ = com.chartboost.sdk.Libraries.e.a.j(new String(sVar.a.b));
                            }
                        } catch (Exception e) {
                            CBLogging.d("CBRequestManager", "unable to read error json", e);
                        }
                    }
                    if (sVar.a == null || sVar.a.a != 200) {
                        if (c.this.b.r() != null) {
                            c.this.b.r().a(aVarJ, c.this.b, cBError);
                        }
                        if (!c.this.b.h()) {
                            c.this.b.d(false);
                            bb.this.a(c.this.b, sVar.a, cBError, false);
                            return;
                        } else {
                            bb.f.remove(c.this.b);
                            return;
                        }
                    }
                    b(new b(aVarJ, sVar.a));
                }
            }
        }
    }

    public static class a extends s {
        private CBError b;

        public a(CBError cBError) {
            this.b = cBError;
        }
    }

    public static class b {
        private com.chartboost.sdk.Libraries.e.a a;
        private i b;

        public b(com.chartboost.sdk.Libraries.e.a aVar, i iVar) {
            this.a = aVar;
            this.b = iVar;
        }
    }

    public synchronized void c() {
        synchronized (this) {
            if (e != null && !e.isEmpty()) {
                for (ba baVar : e.keySet()) {
                    if (baVar != null && !baVar.q()) {
                        baVar.a(baVar.o() + 1);
                        baVar.a(baVar.r());
                    }
                }
            } else {
                String[] strArrA = this.d.a();
                if (strArrA != null) {
                    for (String str : strArrA) {
                        ba baVarA = a(str);
                        if (baVarA != null) {
                            e.put(baVarA, this.d.d(str));
                            baVarA.c(false);
                            baVarA.a(baVarA.o() + 1);
                            baVarA.a(baVarA.r());
                        }
                    }
                }
            }
            e();
            f();
        }
    }

    public static synchronized void d() {
        String[] strArrA;
        try {
            com.chartboost.sdk.Libraries.h hVarC = com.chartboost.sdk.Libraries.h.c();
            if (hVarC == null) {
                strArrA = null;
            } else {
                strArrA = hVarC.a();
            }
            if (strArrA != null && strArrA.length > 0) {
                for (String str : strArrA) {
                    com.chartboost.sdk.Libraries.e.a aVarA = hVarC.a(str);
                    if (aVarA.c()) {
                        hVarC.b(str);
                        ba baVarA = ba.a(aVarA);
                        if (baVarA != null) {
                            baVarA.a(true);
                            baVarA.s();
                        } else {
                            CBLogging.b("CBRequestManager", "Error processing video completeion event");
                        }
                    }
                }
            }
        } catch (Exception e2) {
            CBLogging.b("CBRequestManager", "Error executing saved requests", e2);
        }
    }

    public void e() {
        String[] strArrA;
        com.chartboost.sdk.Tracking.a aVarA = com.chartboost.sdk.Tracking.a.a();
        if (!com.chartboost.sdk.b.i()) {
            if (f != null && !f.isEmpty()) {
                f.clear();
            }
            aVarA.m().b();
            return;
        }
        synchronized (this) {
            if (f.isEmpty()) {
                try {
                    com.chartboost.sdk.Libraries.h hVarM = aVarA.m();
                    if (hVarM == null) {
                        strArrA = null;
                    } else {
                        strArrA = hVarM.a();
                    }
                    if (strArrA != null) {
                        for (String str : strArrA) {
                            if (!aVarA.b(str)) {
                                com.chartboost.sdk.Libraries.e.a aVarA2 = hVarM.a(str);
                                if (aVarA2.c()) {
                                    CBLogging.a("CBRequestManager", "### Flushing out " + str + "track events from cache to server...");
                                    ba baVarA = aVarA.a(aVarA2);
                                    f.put(baVarA, hVarM.d(str));
                                    baVarA.s();
                                }
                            }
                        }
                    }
                } catch (Exception e2) {
                    CBLogging.b("CBRequestManager", "Error executing saved requests", e2);
                }
            } else {
                for (ba baVar : f.keySet()) {
                    if (baVar != null && !baVar.q()) {
                        baVar.a(baVar.o() + 1);
                        baVar.a(baVar.r());
                    }
                }
            }
        }
    }

    private void a(ba baVar) {
        if (baVar != null) {
            File fileA = null;
            if (baVar.k() && (baVar instanceof ba)) {
                fileA = this.d.a((File) null, baVar.t());
            }
            if ((baVar.k() || baVar.m()) && fileA != null) {
                e.put(baVar, fileA);
            }
        }
    }

    private ba a(String str) {
        com.chartboost.sdk.Libraries.e.a aVarA;
        if (TextUtils.isEmpty(str) || (aVarA = this.d.a(str)) == null) {
            return null;
        }
        return ba.a(aVarA);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.chartboost.sdk.impl.bb$1] */
    public void f() {
        if (this.h == null) {
            this.h = new CountDownTimer(240000L, 1000L) { // from class: com.chartboost.sdk.impl.bb.1
                @Override // android.os.CountDownTimer
                public void onTick(long millisUntilFinished) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    bb.this.c();
                }
            }.start();
        }
    }

    public void g() {
        CBLogging.a("CBRequestManager", "Timer stopped:");
        if (this.h != null) {
            this.h.cancel();
            this.h = null;
        }
    }

    @Override // java.util.Observer
    public void update(Observable observable, Object data) {
        if (this.h != null) {
            g();
        }
        c();
    }

    public static synchronized boolean h() {
        return (f == null || f.isEmpty()) ? false : true;
    }

    public ConcurrentHashMap<ba, File> i() {
        return e;
    }

    public com.chartboost.sdk.Libraries.h j() {
        return this.d;
    }
}
