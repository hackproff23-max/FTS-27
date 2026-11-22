package com.flurry.sdk;

import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import java.net.HttpCookie;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class n {
    private static final String a = n.class.getSimpleName();
    private String c;
    private long b = 10000;
    private final Runnable d = new hq() { // from class: com.flurry.sdk.n.1
        @Override // com.flurry.sdk.hq
        public void safeRun() {
            n.this.g();
        }
    };
    private final fy<ff> e = new fy<ff>() { // from class: com.flurry.sdk.n.2
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(ff ffVar) {
            n.this.g();
        }
    };
    private final fy<fi> f = new fy<fi>() { // from class: com.flurry.sdk.n.3
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(fi fiVar) {
            if (fiVar.a) {
                n.this.g();
            }
        }
    };

    static /* synthetic */ long a(n nVar, int i) {
        long j = nVar.b << i;
        nVar.b = j;
        return j;
    }

    public n() {
        fz.a().a("com.flurry.android.sdk.IdProviderFinishedEvent", this.e);
        fz.a().a("com.flurry.android.sdk.NetworkStateEvent", this.f);
        g();
    }

    public void a() {
        fp.a().c(this.d);
        h();
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return fe.a().e() ? AppEventsConstants.EVENT_PARAM_VALUE_NO : AppEventsConstants.EVENT_PARAM_VALUE_YES;
    }

    public String d() {
        StringBuilder sb = new StringBuilder();
        HttpCookie httpCookie = new HttpCookie("B", b());
        httpCookie.setDomain(".yahoo.com");
        sb.append(httpCookie.toString());
        if (!fe.a().e()) {
            sb.append(";");
            HttpCookie httpCookie2 = new HttpCookie("AO", c());
            httpCookie.setDomain(".yahoo.com");
            sb.append(httpCookie2.toString());
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (TextUtils.isEmpty(this.c) && fe.a().c()) {
            String strD = fe.a().d();
            String strE = e();
            StringBuilder sb = new StringBuilder("select bid from data.utilities where _di='");
            if (!TextUtils.isEmpty(strD)) {
                String strA = hp.a(hp.f(strD));
                sb.append(strA).append("' and _diaid='").append(strA).append("' and _diaidu='").append(strD).append("'");
            } else {
                sb.append(hp.a(hp.f(strE))).append("'");
            }
            gj gjVar = new gj();
            gjVar.a("q", sb.toString());
            fn.a().a(this);
            gk gkVar = new gk();
            gkVar.a("https://analytics.query.yahoo.com/v1/public/yql?" + gjVar.b());
            gkVar.a(0);
            gkVar.a(gl.a.kGet);
            gkVar.a((gk.a) new gk.a<Void, Void>() { // from class: com.flurry.sdk.n.4
                @Override // com.flurry.sdk.gk.a
                public void a(gk<Void, Void> gkVar2, Void r8) {
                    List<String> listB;
                    gd.a(3, n.a, "BCookie request: HTTP status code is:" + gkVar2.e());
                    if (gkVar2.c() && (listB = gkVar2.b("Set-Cookie")) != null) {
                        Iterator<String> it = listB.iterator();
                        while (it.hasNext()) {
                            List<HttpCookie> list = HttpCookie.parse(it.next());
                            if (list != null) {
                                for (HttpCookie httpCookie : list) {
                                    if (HttpCookie.domainMatches(".yahoo.com", httpCookie.getDomain()) && "B".equalsIgnoreCase(httpCookie.getName())) {
                                        gd.a(3, n.a, "Found BCookie");
                                        n.this.c = httpCookie.getValue();
                                    }
                                }
                            }
                        }
                    }
                    if (!TextUtils.isEmpty(n.this.c)) {
                        n.this.h();
                        return;
                    }
                    n.a(n.this, 1);
                    gd.a(3, n.a, "BCookie request failed, backing off: " + n.this.b + "ms");
                    fp.a().b(n.this.d, n.this.b);
                }
            });
            fn.a().a(this, gkVar);
        }
    }

    public static String e() {
        String string = Build.SERIAL;
        try {
            if (TextUtils.isEmpty(string)) {
                string = Settings.Secure.getString(fp.a().c().getContentResolver(), "android_id");
            }
        } catch (Exception e) {
        }
        if (TextUtils.isEmpty(string)) {
            return fe.a().f();
        }
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        fz.a().b("com.flurry.android.sdk.NetworkStateEvent", this.f);
        fz.a().b("com.flurry.android.sdk.IdProviderFinishedEvent", this.e);
    }
}
