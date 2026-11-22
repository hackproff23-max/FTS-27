package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.ld;

/* JADX INFO: loaded from: classes.dex */
class bf implements cg {
    private final long BF;
    private final int BG;
    private double BH;
    private long BI;
    private final Object BJ = new Object();
    private final String BK;
    private final long arU;
    private final ld wb;

    public bf(int i, long j, long j2, String str, ld ldVar) {
        this.BG = i;
        this.BH = this.BG;
        this.BF = j;
        this.arU = j2;
        this.BK = str;
        this.wb = ldVar;
    }

    @Override // com.google.android.gms.tagmanager.cg
    public boolean fe() {
        boolean z = false;
        synchronized (this.BJ) {
            long jCurrentTimeMillis = this.wb.currentTimeMillis();
            if (jCurrentTimeMillis - this.BI < this.arU) {
                bh.W("Excessive " + this.BK + " detected; call ignored.");
            } else {
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
                    bh.W("Excessive " + this.BK + " detected; call ignored.");
                }
            }
        }
        return z;
    }
}
