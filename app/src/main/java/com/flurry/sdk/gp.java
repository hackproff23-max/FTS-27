package com.flurry.sdk;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class gp {
    protected final String c;
    gr e;
    Set<String> d = new HashSet();
    protected String f = "defaultDataKey_";
    private fy<fi> a = new fy<fi>() { // from class: com.flurry.sdk.gp.1
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(fi fiVar) {
            gd.a(4, gp.this.c, "onNetworkStateChanged : isNetworkEnable = " + fiVar.a);
            if (fiVar.a) {
                gp.this.e();
            }
        }
    };

    public interface a {
        void a();
    }

    protected abstract void a(byte[] bArr, String str, String str2);

    public gp(String str, String str2) {
        this.c = str2;
        fz.a().a("com.flurry.android.sdk.NetworkStateEvent", this.a);
        a(str);
    }

    protected void a(hq hqVar) {
        fp.a().b(hqVar);
    }

    protected void a(final String str) {
        a(new hq() { // from class: com.flurry.sdk.gp.2
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                gp.this.e = new gr(str);
            }
        });
    }

    public void b(byte[] bArr, String str, String str2) {
        a(bArr, str, str2, null);
    }

    public void a(byte[] bArr, String str, String str2, a aVar) {
        if (bArr == null || bArr.length == 0) {
            gd.a(6, this.c, "Report that has to be sent is EMPTY or NULL");
        } else {
            c(bArr, str, str2);
            a(aVar);
        }
    }

    public int d() {
        return this.d.size();
    }

    protected void c(final byte[] bArr, final String str, final String str2) {
        a(new hq() { // from class: com.flurry.sdk.gp.3
            @Override // com.flurry.sdk.hq
            public void safeRun() throws Throwable {
                gp.this.d(bArr, str, str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e() {
        a((a) null);
    }

    protected void a(final a aVar) {
        a(new hq() { // from class: com.flurry.sdk.gp.4
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                gp.this.g();
                if (aVar != null) {
                    aVar.a();
                }
            }
        });
    }

    protected boolean f() {
        return d() <= 5;
    }

    public String a(String str, String str2) {
        return this.f + str + "_" + str2;
    }

    protected void d(byte[] bArr, String str, String str2) throws Throwable {
        String strA = a(str, str2);
        gq gqVar = new gq(bArr);
        String strA2 = gqVar.a();
        new fw(fp.a().c().getFileStreamPath(gq.a(strA2)), ".yflurrydatasenderblock.", 1, new ha<gq>() { // from class: com.flurry.sdk.gp.5
            @Override // com.flurry.sdk.ha
            public gx<gq> a(int i) {
                return new gq.a();
            }
        }).a(gqVar);
        gd.a(5, this.c, "Saving Block File " + strA2 + " at " + fp.a().c().getFileStreamPath(gq.a(strA2)));
        this.e.a(gqVar, strA);
    }

    protected void g() {
        if (!fj.a().c()) {
            gd.a(5, this.c, "Reports were not sent! No Internet connection!");
            return;
        }
        List<String> listA = this.e.a();
        if (listA == null || listA.isEmpty()) {
            gd.a(4, this.c, "No more reports to send.");
            return;
        }
        for (String str : listA) {
            if (f()) {
                List<String> listC = this.e.c(str);
                gd.a(4, this.c, "Number of not sent blocks = " + listC.size());
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= listC.size()) {
                        break;
                    }
                    String str2 = listC.get(i2);
                    if (!this.d.contains(str2)) {
                        if (!f()) {
                            break;
                        }
                        gq gqVar = (gq) new fw(fp.a().c().getFileStreamPath(gq.a(str2)), ".yflurrydatasenderblock.", 1, new ha<gq>() { // from class: com.flurry.sdk.gp.6
                            @Override // com.flurry.sdk.ha
                            public gx<gq> a(int i3) {
                                return new gq.a();
                            }
                        }).a();
                        if (gqVar == null) {
                            gd.a(6, this.c, "Internal ERROR! Cannot read!");
                            this.e.a(str2, str);
                        } else {
                            byte[] bArrB = gqVar.b();
                            if (bArrB == null || bArrB.length == 0) {
                                gd.a(6, this.c, "Internal ERROR! Report is empty!");
                                this.e.a(str2, str);
                            } else {
                                gd.a(5, this.c, "Reading block info " + str2);
                                this.d.add(str2);
                                a(bArrB, str2, str);
                            }
                        }
                    }
                    i = i2 + 1;
                }
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(final String str, final String str2, int i) {
        a(new hq() { // from class: com.flurry.sdk.gp.7
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                if (!gp.this.e.a(str, str2)) {
                    gd.a(6, gp.this.c, "Internal error. Block wasn't deleted with id = " + str);
                }
                if (!gp.this.d.remove(str)) {
                    gd.a(6, gp.this.c, "Internal error. Block with id = " + str + " was not in progress state");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(final String str, String str2) {
        a(new hq() { // from class: com.flurry.sdk.gp.8
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                if (!gp.this.d.remove(str)) {
                    gd.a(6, gp.this.c, "Internal error. Block with id = " + str + " was not in progress state");
                }
            }
        });
    }

    protected void c(String str, String str2) {
        if (!this.e.a(str, str2)) {
            gd.a(6, this.c, "Internal error. Block wasn't deleted with id = " + str);
        }
        if (!this.d.remove(str)) {
            gd.a(6, this.c, "Internal error. Block with id = " + str + " was not in progress state");
        }
    }
}
