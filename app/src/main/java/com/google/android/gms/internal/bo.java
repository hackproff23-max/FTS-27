package com.google.android.gms.internal;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class bo extends bq.a {
    private final w px;
    private final String py;
    private final String pz;

    public bo(w wVar, String str, String str2) {
        this.px = wVar;
        this.py = str;
        this.pz = str2;
    }

    @Override // com.google.android.gms.internal.bq
    public void a(com.google.android.gms.dynamic.d dVar) {
        if (dVar == null) {
            return;
        }
        this.px.b((View) com.google.android.gms.dynamic.e.f(dVar));
    }

    @Override // com.google.android.gms.internal.bq
    public void av() {
        this.px.av();
    }

    @Override // com.google.android.gms.internal.bq
    public void aw() {
        this.px.aw();
    }

    @Override // com.google.android.gms.internal.bq
    public String bA() {
        return this.pz;
    }

    @Override // com.google.android.gms.internal.bq
    public String bz() {
        return this.py;
    }
}
