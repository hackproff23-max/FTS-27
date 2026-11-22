package com.applovin.impl.adview;

/* JADX INFO: loaded from: classes.dex */
class ai implements Runnable {
    final /* synthetic */ ad a;

    ai(ad adVar) {
        this.a = adVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.m.dismiss();
    }
}
