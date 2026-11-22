package com.applovin.impl.adview;

/* JADX INFO: loaded from: classes.dex */
class ac implements Runnable {
    final /* synthetic */ v a;

    private ac(v vVar) {
        this.a = vVar;
    }

    /* synthetic */ ac(v vVar, w wVar) {
        this(vVar);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.dismiss();
        } catch (Throwable th) {
            if (this.a.c != null) {
                this.a.c.e("InterstitialAdDialog", "dismiss() threw exception", th);
            }
        }
    }
}
