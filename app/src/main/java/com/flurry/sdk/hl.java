package com.flurry.sdk;

/* JADX INFO: loaded from: classes.dex */
public class hl {
    private static final String a = hl.class.getSimpleName();
    private long b = 1000;
    private boolean c = true;
    private boolean d = false;
    private hq e = new hq() { // from class: com.flurry.sdk.hl.1
        @Override // com.flurry.sdk.hq
        public void safeRun() {
            new hj().b();
            if (hl.this.c && hl.this.d) {
                fp.a().b(hl.this.e, hl.this.b);
            }
        }
    };

    public void a(long j) {
        this.b = j;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public synchronized void a() {
        if (!this.d) {
            fp.a().b(this.e, this.b);
            this.d = true;
        }
    }

    public synchronized void b() {
        if (this.d) {
            fp.a().c(this.e);
            this.d = false;
        }
    }
}
