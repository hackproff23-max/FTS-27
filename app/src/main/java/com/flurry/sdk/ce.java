package com.flurry.sdk;

import android.text.TextUtils;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ce extends go<cd> {
    private static final String a = ce.class.getSimpleName();
    private final fw<List<cd>> b = new fw<>(fp.a().c().getFileStreamPath(".yflurryreporter"), ".yflurryreporter", 2, new ha<List<cd>>() { // from class: com.flurry.sdk.ce.1
        @Override // com.flurry.sdk.ha
        public gx<List<cd>> a(int i) {
            return i > 1 ? new gw(new cd.a()) : new gw(new cd.b());
        }
    });

    @Override // com.flurry.sdk.go
    protected synchronized void a(List<cd> list) {
        hp.b();
        List<cd> listA = this.b.a();
        if (listA != null) {
            list.addAll(listA);
        }
    }

    @Override // com.flurry.sdk.go
    protected synchronized void b(List<cd> list) {
        hp.b();
        this.b.a(new ArrayList(list));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.flurry.sdk.go
    public void a(final cd cdVar) {
        gd.a(3, a, "Sending next report for original url: " + cdVar.g() + " to current url:" + cdVar.h());
        gk gkVar = new gk();
        gkVar.a(cdVar.h());
        gkVar.a(100000);
        gkVar.a(gl.a.kGet);
        gkVar.a(false);
        if (cdVar.c()) {
            gkVar.a("Cookie", i.a().h().d());
        }
        gkVar.a((gk.a) new gk.a<Void, Void>() { // from class: com.flurry.sdk.ce.2
            @Override // com.flurry.sdk.gk.a
            public void a(final gk<Void, Void> gkVar2, Void r7) {
                gd.a(3, ce.a, "AsyncReportInfo request: HTTP status code is:" + gkVar2.e());
                int iE = gkVar2.e();
                if (iE >= 200 && iE < 300) {
                    gd.a(3, ce.a, "Send report successful to url: " + gkVar2.a());
                    ce.this.c(cdVar);
                    if (gd.c() <= 3 && gd.d()) {
                        fp.a().a(new Runnable() { // from class: com.flurry.sdk.ce.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                Toast.makeText(fp.a().c(), "ADS AR HTTP Response Code: " + gkVar2.e() + " for url: " + gkVar2.a(), 1).show();
                            }
                        });
                    }
                    ce.this.a(cdVar, iE);
                    return;
                }
                if (iE < 300 || iE >= 400) {
                    gd.a(3, ce.a, "Send report failed to url: " + gkVar2.a());
                    ce.this.d(cdVar);
                    if (cdVar.f() == 0) {
                        ce.this.a(cdVar, iE);
                        return;
                    }
                    return;
                }
                String strB = null;
                List<String> listB = gkVar2.b("Location");
                if (listB != null && listB.size() > 0) {
                    strB = cv.b(listB.get(0), cdVar.h());
                }
                if (TextUtils.isEmpty(strB)) {
                    gd.a(3, ce.a, "Send report successful to url: " + gkVar2.a());
                    ce.this.c(cdVar);
                    if (gd.c() <= 3 && gd.d()) {
                        fp.a().a(new Runnable() { // from class: com.flurry.sdk.ce.2.2
                            @Override // java.lang.Runnable
                            public void run() {
                                Toast.makeText(fp.a().c(), "ADS AR HTTP Response Code: " + gkVar2.e() + " for url: " + gkVar2.a(), 1).show();
                            }
                        });
                    }
                    ce.this.a(cdVar, iE);
                    return;
                }
                gd.a(3, ce.a, "Send report redirecting to url: " + strB);
                cdVar.c(strB);
                ce.this.a(cdVar);
            }
        });
        fn.a().a(this, gkVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(cd cdVar, int i) {
        if (cdVar != null) {
            HashMap map = new HashMap();
            map.put("event", cdVar.a());
            map.put("url", cdVar.g());
            map.put("response", i + "");
            i.a().a(cdVar.b(), aw.EV_SEND_URL_STATUS_RESULT, true, map);
        }
    }
}
