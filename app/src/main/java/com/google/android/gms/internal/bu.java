package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import com.facebook.appevents.AppEventsConstants;

/* JADX INFO: loaded from: classes.dex */
@ey
public class bu extends bx.a implements bv.a {
    private final Object mH = new Object();
    private final String pB;
    private final Drawable pC;
    private final String pD;
    private final String pF;
    private bv pJ;
    private final Drawable pK;
    private final String pL;

    public bu(String str, Drawable drawable, String str2, Drawable drawable2, String str3, String str4) {
        this.pB = str;
        this.pC = drawable;
        this.pD = str2;
        this.pK = drawable2;
        this.pF = str3;
        this.pL = str4;
    }

    @Override // com.google.android.gms.internal.bv.a
    public void a(bv bvVar) {
        synchronized (this.mH) {
            this.pJ = bvVar;
        }
    }

    @Override // com.google.android.gms.internal.bx
    public void aw() {
        synchronized (this.mH) {
            if (this.pJ == null) {
                gr.T("Attempt to record impression before content ad initialized.");
            } else {
                this.pJ.aw();
            }
        }
    }

    @Override // com.google.android.gms.internal.bx
    public String bB() {
        return this.pB;
    }

    @Override // com.google.android.gms.internal.bx
    public com.google.android.gms.dynamic.d bC() {
        return com.google.android.gms.dynamic.e.k(this.pC);
    }

    @Override // com.google.android.gms.internal.bx
    public String bE() {
        return this.pF;
    }

    @Override // com.google.android.gms.internal.bx
    public com.google.android.gms.dynamic.d bI() {
        return com.google.android.gms.dynamic.e.k(this.pK);
    }

    @Override // com.google.android.gms.internal.bx
    public String bJ() {
        return this.pL;
    }

    @Override // com.google.android.gms.internal.bx
    public String getBody() {
        return this.pD;
    }

    @Override // com.google.android.gms.internal.bx
    public void j(int i) {
        synchronized (this.mH) {
            if (this.pJ == null) {
                gr.T("Attempt to perform click before content ad initialized.");
            } else {
                this.pJ.b(AppEventsConstants.EVENT_PARAM_VALUE_YES, i);
            }
        }
    }
}
