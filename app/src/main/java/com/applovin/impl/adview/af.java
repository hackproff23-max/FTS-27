package com.applovin.impl.adview;

/* JADX INFO: loaded from: classes.dex */
class af implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ boolean b;
    final /* synthetic */ ad c;

    af(ad adVar, boolean z, boolean z2) {
        this.c = adVar;
        this.a = z;
        this.b = z2;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.a && this.b) {
            this.c.i();
        } else {
            this.c.h();
        }
    }
}
