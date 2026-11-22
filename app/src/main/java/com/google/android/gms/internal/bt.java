package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
@ey
public class bt extends bw.a implements bv.a {
    private final Object mH = new Object();
    private final String pB;
    private final Drawable pC;
    private final String pD;
    private final Drawable pE;
    private final String pF;
    private final double pG;
    private final String pH;
    private final String pI;
    private bv pJ;

    public bt(String str, Drawable drawable, String str2, Drawable drawable2, String str3, double d, String str4, String str5) {
        this.pB = str;
        this.pC = drawable;
        this.pD = str2;
        this.pE = drawable2;
        this.pF = str3;
        this.pG = d;
        this.pH = str4;
        this.pI = str5;
    }

    @Override // com.google.android.gms.internal.bv.a
    public void a(bv bvVar) {
        synchronized (this.mH) {
            this.pJ = bvVar;
        }
    }

    @Override // com.google.android.gms.internal.bw
    public void aw() {
        synchronized (this.mH) {
            if (this.pJ == null) {
                gr.T("Attempt to record impression before app install ad initialized.");
            } else {
                this.pJ.aw();
            }
        }
    }

    @Override // com.google.android.gms.internal.bw
    public String bB() {
        return this.pB;
    }

    @Override // com.google.android.gms.internal.bw
    public com.google.android.gms.dynamic.d bC() {
        return com.google.android.gms.dynamic.e.k(this.pC);
    }

    @Override // com.google.android.gms.internal.bw
    public com.google.android.gms.dynamic.d bD() {
        return com.google.android.gms.dynamic.e.k(this.pE);
    }

    @Override // com.google.android.gms.internal.bw
    public String bE() {
        return this.pF;
    }

    @Override // com.google.android.gms.internal.bw
    public double bF() {
        return this.pG;
    }

    @Override // com.google.android.gms.internal.bw
    public String bG() {
        return this.pH;
    }

    @Override // com.google.android.gms.internal.bw
    public String bH() {
        return this.pI;
    }

    @Override // com.google.android.gms.internal.bw
    public String getBody() {
        return this.pD;
    }

    @Override // com.google.android.gms.internal.bw
    public void j(int i) {
        synchronized (this.mH) {
            if (this.pJ == null) {
                gr.T("Attempt to perform click before app install ad initialized.");
            } else {
                this.pJ.b("2", i);
            }
        }
    }
}
