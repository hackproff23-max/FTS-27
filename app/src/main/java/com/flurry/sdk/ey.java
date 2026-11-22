package com.flurry.sdk;

import android.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public class ey extends gp implements hh.a {
    private String h;
    private boolean i;
    private static final String g = ey.class.getSimpleName();
    static String a = "http://data.flurry.com/aap.do";
    static String b = "https://data.flurry.com/aap.do";

    public ey() {
        this(null);
    }

    public ey(gp.a aVar) {
        super("Analytics", ey.class.getSimpleName());
        this.f = "AnalyticsData_";
        h();
        a(aVar);
    }

    private void h() {
        hg hgVarA = hg.a();
        this.i = ((Boolean) hgVarA.a("UseHttps")).booleanValue();
        hgVarA.a("UseHttps", (hh.a) this);
        gd.a(4, g, "initSettings, UseHttps = " + this.i);
        String str = (String) hgVarA.a("ReportUrl");
        hgVarA.a("ReportUrl", (hh.a) this);
        b(str);
        gd.a(4, g, "initSettings, ReportUrl = " + str);
    }

    public void a() {
        hg.a().b("UseHttps", (hh.a) this);
        hg.a().b("ReportUrl", (hh.a) this);
    }

    @Override // com.flurry.sdk.hh.a
    public void a(String str, Object obj) {
        switch (str) {
            case "UseHttps":
                this.i = ((Boolean) obj).booleanValue();
                gd.a(4, g, "onSettingUpdate, UseHttps = " + this.i);
                break;
            case "ReportUrl":
                String str2 = (String) obj;
                b(str2);
                gd.a(4, g, "onSettingUpdate, ReportUrl = " + str2);
                break;
            default:
                gd.a(6, g, "onSettingUpdate internal error!");
                break;
        }
    }

    private void b(String str) {
        if (str != null && !str.endsWith(".do")) {
            gd.a(5, g, "overriding analytics agent report URL without an endpoint, are you sure?");
        }
        this.h = str;
    }

    String b() {
        if (this.h != null) {
            return this.h;
        }
        if (this.i) {
            return b;
        }
        return a;
    }

    @Override // com.flurry.sdk.gp
    protected void a(byte[] bArr, final String str, final String str2) {
        String strB = b();
        gd.a(4, g, "FlurryDataSender: start upload data " + bArr + " with id = " + str + " to " + strB);
        gk gkVar = new gk();
        gkVar.a(strB);
        gkVar.a(100000);
        gkVar.a(gl.a.kPost);
        gkVar.a("Content-Type", "application/octet-stream");
        gkVar.a((gx) new gt());
        gkVar.a(bArr);
        gkVar.a((gk.a) new gk.a<byte[], Void>() { // from class: com.flurry.sdk.ey.1
            @Override // com.flurry.sdk.gk.a
            public void a(gk<byte[], Void> gkVar2, Void r7) {
                final int iE = gkVar2.e();
                if (iE > 0) {
                    gd.e(ey.g, "Analytics report sent.");
                    gd.a(3, ey.g, "FlurryDataSender: report " + str + " sent. HTTP response: " + iE);
                    if (gd.c() <= 3 && gd.d()) {
                        fp.a().a(new Runnable() { // from class: com.flurry.sdk.ey.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                Toast.makeText(fp.a().c(), "SD HTTP Response Code: " + iE, 0).show();
                            }
                        });
                    }
                    ey.this.a(str, str2, iE);
                    ey.this.e();
                    return;
                }
                ey.this.b(str, str2);
            }
        });
        fn.a().a(this, gkVar);
    }

    @Override // com.flurry.sdk.gp
    protected void a(String str, String str2, final int i) {
        a(new hq() { // from class: com.flurry.sdk.ey.2
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                if (i == 200) {
                    eq.a().d();
                }
            }
        });
        super.a(str, str2, i);
    }
}
