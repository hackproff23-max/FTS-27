package com.applovin.adview;

/* JADX INFO: loaded from: classes.dex */
class k implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ j c;

    k(j jVar, int i, int i2) {
        this.c = jVar;
        this.a = i;
        this.b = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.c.a.d.e("AppLovinInterstitialActivity", "Video view error (" + this.a + "," + this.b + ") - showing close button.");
        this.c.a.e();
    }
}
