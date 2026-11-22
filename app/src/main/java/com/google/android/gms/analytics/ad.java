package com.google.android.gms.analytics;

/* JADX INFO: loaded from: classes.dex */
class ad implements ah {
    private final long BF;
    private final int BG;
    private double BH;
    private long BI;
    private final Object BJ;
    private final String BK;

    public ad(int i, long j, String str) {
        this.BJ = new Object();
        this.BG = i;
        this.BH = this.BG;
        this.BF = j;
        this.BK = str;
    }

    public ad(String str) {
        this(60, 2000L, str);
    }

    @Override // com.google.android.gms.analytics.ah
    public boolean fe() {
        boolean z;
        synchronized (this.BJ) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (this.BH < this.BG) {
                double d = (jCurrentTimeMillis - this.BI) / this.BF;
                if (d > 0.0d) {
                    this.BH = Math.min(this.BG, d + this.BH);
                }
            }
            this.BI = jCurrentTimeMillis;
            if (this.BH >= 1.0d) {
                this.BH -= 1.0d;
                z = true;
            } else {
                ae.W("Excessive " + this.BK + " detected; call ignored.");
                z = false;
            }
        }
        return z;
    }
}
