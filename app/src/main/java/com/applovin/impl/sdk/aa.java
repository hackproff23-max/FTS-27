package com.applovin.impl.sdk;

/* JADX INFO: loaded from: classes.dex */
class aa implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ y b;

    aa(y yVar, int i) {
        this.b = yVar;
        this.a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.b.failedToReceiveAd(this.a);
    }
}
