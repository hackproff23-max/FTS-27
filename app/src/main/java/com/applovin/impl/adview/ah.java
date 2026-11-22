package com.applovin.impl.adview;

/* JADX INFO: loaded from: classes.dex */
class ah implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ ad b;

    ah(ad adVar, int i) {
        this.b = adVar;
        this.a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.b.g != null) {
            this.b.g.failedToReceiveAd(this.a);
        }
    }
}
