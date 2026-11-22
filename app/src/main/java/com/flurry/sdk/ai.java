package com.flurry.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class ai {
    private static final String a = ai.class.getSimpleName();
    private a b;
    private String c;
    private al f;
    private long g;
    private boolean h;
    private int i;
    private int k;
    private boolean l;
    private boolean m;
    private long d = Long.MAX_VALUE;
    private int e = 40000;
    private long j = 102400;

    public interface a {
        void a(ai aiVar);
    }

    protected abstract OutputStream f() throws IOException;

    protected abstract void g();

    protected abstract void h();

    static /* synthetic */ int m(ai aiVar) {
        int i = aiVar.k;
        aiVar.k = i + 1;
        return i;
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void a(String str) {
        this.c = str;
    }

    public void a(int i) {
        this.e = i;
    }

    public void a(al alVar) {
        this.f = alVar;
    }

    public boolean a() {
        return this.l;
    }

    public boolean b() {
        return this.m;
    }

    public long c() {
        return this.g;
    }

    public void d() {
        fp.a().b(new hq() { // from class: com.flurry.sdk.ai.1
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                if (ai.this.p()) {
                    ai.this.k();
                } else {
                    ai.this.j();
                }
            }
        });
    }

    public void e() {
        this.m = true;
        fn.a().a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (!b()) {
            gd.a(3, a, "Downloader: Requesting file from url: " + this.c);
            gl glVar = new gl();
            glVar.a(this.c);
            glVar.a(gl.a.kGet);
            glVar.a(this.e);
            glVar.a(new gl.b() { // from class: com.flurry.sdk.ai.2
                @Override // com.flurry.sdk.gl.b, com.flurry.sdk.gl.c
                public void a(gl glVar2, InputStream inputStream) throws Exception {
                    ao aoVar;
                    if (ai.this.b()) {
                        throw new IOException("Downloader: request cancelled");
                    }
                    ai.this.g = ai.this.a(glVar2);
                    if (ai.this.g > ai.this.d) {
                        throw new IOException("Downloader: content length: " + ai.this.g + " exceeds size limit: " + ai.this.d);
                    }
                    try {
                        aoVar = new ao(inputStream, ai.this.d);
                        try {
                            hp.a(aoVar, ai.this.f());
                            ai.this.g();
                            hp.a(aoVar);
                        } catch (Throwable th) {
                            th = th;
                            ai.this.g();
                            hp.a(aoVar);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        aoVar = null;
                    }
                }

                @Override // com.flurry.sdk.gl.b, com.flurry.sdk.gl.c
                public void a(gl glVar2) {
                    if (!ai.this.b()) {
                        gd.a(3, ai.a, "Downloader: Download status code is:" + glVar2.e() + " for url: " + ai.this.c);
                        ai.this.l = glVar2.c();
                        fp.a().b(new hq() { // from class: com.flurry.sdk.ai.2.1
                            @Override // com.flurry.sdk.hq
                            public void safeRun() {
                                if (!ai.this.l) {
                                    ai.this.h();
                                }
                                ai.this.o();
                            }
                        });
                    }
                }
            });
            fn.a().a(this, glVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (!b()) {
            gk gkVar = new gk();
            gkVar.a(this.c);
            gkVar.a(gl.a.kHead);
            gkVar.a((gk.a) new gk.a<Void, Void>() { // from class: com.flurry.sdk.ai.3
                @Override // com.flurry.sdk.gk.a
                public void a(gk<Void, Void> gkVar2, Void r15) {
                    if (!ai.this.b()) {
                        gd.a(3, ai.a, "Downloader: HTTP HEAD status code is:" + gkVar2.e() + " for url: " + ai.this.c);
                        if (gkVar2.c()) {
                            ai.this.g = ai.this.a(gkVar2);
                            List<String> listB = gkVar2.b("Accept-Ranges");
                            if (ai.this.g <= 0 || listB == null || listB.isEmpty()) {
                                ai.this.i = 1;
                            } else {
                                ai.this.h = "bytes".equals(listB.get(0).trim());
                                ai.this.i = (int) (((long) (ai.this.g % ai.this.j > 0 ? 1 : 0)) + (ai.this.g / ai.this.j));
                            }
                            if (ai.this.d > 0 && ai.this.g > ai.this.d) {
                                gd.a(3, ai.a, "Downloader: Size limit exceeded -- limit: " + ai.this.d + ", content-length: " + ai.this.g + " bytes!");
                                fp.a().b(new hq() { // from class: com.flurry.sdk.ai.3.1
                                    @Override // com.flurry.sdk.hq
                                    public void safeRun() {
                                        ai.this.o();
                                    }
                                });
                                return;
                            } else {
                                fp.a().b(new hq() { // from class: com.flurry.sdk.ai.3.2
                                    @Override // com.flurry.sdk.hq
                                    public void safeRun() {
                                        ai.this.l();
                                    }
                                });
                                return;
                            }
                        }
                        fp.a().b(new hq() { // from class: com.flurry.sdk.ai.3.3
                            @Override // com.flurry.sdk.hq
                            public void safeRun() {
                                ai.this.o();
                            }
                        });
                    }
                }
            });
            gd.a(3, a, "Downloader: requesting HTTP HEAD for url: " + this.c);
            fn.a().a(this, gkVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (!b()) {
            if (q()) {
                for (int i = 0; i < this.i; i++) {
                    this.f.d(b(i));
                }
                m();
                return;
            }
            j();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        while (this.k < this.i) {
            if (!b()) {
                final String strB = b(this.k);
                final String strC = c(this.k);
                if (this.f.d(strB)) {
                    gd.a(3, a, "Downloader: Skipping chunk with range:" + strC + " for url: " + this.c + " chunk: " + this.k);
                    this.k++;
                } else {
                    gd.a(3, a, "Downloader: Requesting chunk with range:" + strC + " for url: " + this.c + " chunk: " + this.k);
                    gl glVar = new gl();
                    glVar.a(this.c);
                    glVar.a(gl.a.kGet);
                    glVar.a(this.e);
                    glVar.a("Range", strC);
                    glVar.a(new gl.b() { // from class: com.flurry.sdk.ai.4
                        @Override // com.flurry.sdk.gl.b, com.flurry.sdk.gl.c
                        public void a(gl glVar2, InputStream inputStream) throws Exception {
                            ao aoVar;
                            IOException iOException = null;
                            iOException = null;
                            ao aoVar2 = null;
                            if (ai.this.b()) {
                                throw new IOException("Downloader: request cancelled");
                            }
                            al.c cVarB = ai.this.f.b(strB);
                            if (cVarB != null) {
                                try {
                                    aoVar = new ao(inputStream, ai.this.d);
                                    try {
                                        hp.a(aoVar, cVarB.a());
                                        hp.a(aoVar);
                                        hp.a(cVarB);
                                    } catch (IOException e) {
                                        e = e;
                                        hp.a(aoVar);
                                        hp.a(cVarB);
                                        iOException = e;
                                    } catch (Throwable th) {
                                        th = th;
                                        aoVar2 = aoVar;
                                        hp.a(aoVar2);
                                        hp.a(cVarB);
                                        throw th;
                                    }
                                } catch (IOException e2) {
                                    e = e2;
                                    aoVar = null;
                                } catch (Throwable th2) {
                                    th = th2;
                                }
                            }
                            if (iOException != null) {
                                ai.this.f.c(strB);
                                throw iOException;
                            }
                        }

                        @Override // com.flurry.sdk.gl.b, com.flurry.sdk.gl.c
                        public void a(gl glVar2) {
                            if (!ai.this.b()) {
                                int iE = glVar2.e();
                                gd.a(3, ai.a, "Downloader: Download status code is:" + iE + " for url: " + ai.this.c + " chunk: " + ai.this.k);
                                String str = null;
                                List<String> listB = glVar2.b("Content-Range");
                                if (listB != null && !listB.isEmpty()) {
                                    str = listB.get(0);
                                    gd.a(3, ai.a, "Downloader: Content range is:" + str + " for url: " + ai.this.c + " chunk: " + ai.this.k);
                                }
                                if (glVar2.c() && iE == 206 && str != null && str.startsWith(strC.replaceAll("=", " "))) {
                                    ai.m(ai.this);
                                    fp.a().b(new hq() { // from class: com.flurry.sdk.ai.4.1
                                        @Override // com.flurry.sdk.hq
                                        public void safeRun() {
                                            ai.this.m();
                                        }
                                    });
                                } else {
                                    fp.a().b(new hq() { // from class: com.flurry.sdk.ai.4.2
                                        @Override // com.flurry.sdk.hq
                                        public void safeRun() {
                                            ai.this.o();
                                        }
                                    });
                                }
                            }
                        }
                    });
                    fn.a().a(this, glVar);
                    return;
                }
            } else {
                return;
            }
        }
        n();
    }

    private void n() {
        al.b bVarA;
        IOException iOException = null;
        if (!b()) {
            gd.a(3, a, "Downloader: assembling output file for url: " + this.c);
            try {
                OutputStream outputStreamF = f();
                for (int i = 0; i < this.i; i++) {
                    if (b()) {
                        throw new IOException("Download cancelled");
                    }
                    String strB = b(i);
                    try {
                        bVarA = this.f.a(strB);
                        if (bVarA == null) {
                            throw new IOException("Could not create reader for chunk key: " + strB);
                        }
                        try {
                            hp.a(bVarA.a(), outputStreamF);
                            hp.a(bVarA);
                            this.f.c(strB);
                        } catch (Throwable th) {
                            th = th;
                        }
                        th = th;
                    } catch (Throwable th2) {
                        th = th2;
                        bVarA = null;
                    }
                    hp.a(bVarA);
                    throw th;
                }
                g();
            } catch (IOException e) {
                g();
                iOException = e;
            } catch (Throwable th3) {
                g();
                throw th3;
            }
            if (iOException == null) {
                gd.a(3, a, "Downloader: assemble succeeded for url: " + this.c);
                this.l = true;
            } else {
                gd.a(3, a, "Downloader: assemble failed for url: " + this.c + " failed with exception: " + iOException);
                for (int i2 = 0; i2 < this.i; i2++) {
                    this.f.c(b(i2));
                }
                h();
            }
            o();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (!b() && this.b != null) {
            gd.a(3, a, "Downloader: finished -- success: " + this.l + " for url: " + this.c);
            this.b.a(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean p() {
        return this.f != null;
    }

    private boolean q() {
        return this.f != null && this.h && this.i > 1;
    }

    private String b(int i) {
        return String.format(Locale.US, "%s__%03d", this.c, Integer.valueOf(i));
    }

    private String c(int i) {
        return String.format("%s=%d-%d", "bytes", Long.valueOf(((long) i) * this.j), Long.valueOf(Math.min(this.g, ((long) (i + 1)) * this.j) - 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long a(gl glVar) {
        List<String> listB = glVar.b("Content-Length");
        if (listB != null && !listB.isEmpty()) {
            try {
                return Long.parseLong(listB.get(0));
            } catch (NumberFormatException e) {
                gd.a(3, a, "Downloader: could not determine content length for url: " + this.c);
            }
        }
        return -1L;
    }
}
