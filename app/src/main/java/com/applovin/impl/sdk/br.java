package com.applovin.impl.sdk;

/* JADX INFO: loaded from: classes.dex */
class br implements Runnable {
    final /* synthetic */ bn a;
    private final String b;
    private final ba c;
    private final bo d;

    br(bn bnVar, ba baVar, bo boVar) {
        this.a = bnVar;
        this.b = baVar.a();
        this.c = baVar;
        this.d = boVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            m.a();
            if (this.a.a.c()) {
                this.a.b.i(this.b, "Task re-scheduled...");
                this.a.a(this.c, this.d, 2000L);
                return;
            }
            if (!this.a.a.isEnabled()) {
                if (this.a.a.d()) {
                    this.a.a.e();
                } else {
                    this.a.b.w(this.b, "Task not executed, SDK is disabled");
                }
                this.c.b();
                return;
            }
            this.a.b.i(this.b, "Task started execution...");
            this.c.run();
            long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
            if (this.c instanceof cc) {
                bx.a().a(((cc) this.c).f(), jCurrentTimeMillis2, p.a(this.a.a));
            }
            this.a.b.i(this.b, "Task executed successfully in " + jCurrentTimeMillis2 + "ms.");
            bg bgVarB = this.a.a.b();
            bgVarB.a(this.b + "_count");
            bgVarB.a(this.b + "_time", jCurrentTimeMillis2);
        } catch (Throwable th) {
            this.a.b.e(this.b, "Task failed execution in " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms.", th);
        }
    }
}
