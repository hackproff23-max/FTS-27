package com.google.android.gms.tagmanager;

/* JADX INFO: loaded from: classes.dex */
class cw implements cg {
    private final long BF;
    private final int BG;
    private double BH;
    private final Object BJ;
    private long aty;

    public cw() {
        this(60, 2000L);
    }

    public cw(int i, long j) {
        this.BJ = new Object();
        this.BG = i;
        this.BH = this.BG;
        this.BF = j;
    }

    @Override // com.google.android.gms.tagmanager.cg
    public boolean fe() {
        boolean z;
        synchronized (this.BJ) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (this.BH < this.BG) {
                double d = (jCurrentTimeMillis - this.aty) / this.BF;
                if (d > 0.0d) {
                    this.BH = Math.min(this.BG, d + this.BH);
                }
            }
            this.aty = jCurrentTimeMillis;
            if (this.BH >= 1.0d) {
                this.BH -= 1.0d;
                z = true;
            } else {
                bh.W("No more tokens available.");
                z = false;
            }
        }
        return z;
    }
}
