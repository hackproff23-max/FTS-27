package com.flurry.sdk;

import android.content.Context;

/* JADX INFO: renamed from: com.flurry.sdk.do, reason: invalid class name */
/* JADX INFO: loaded from: classes.dex */
public class Cdo extends dq {
    private int b;

    public Cdo(Context context, r rVar, ec.a aVar) {
        super(context, rVar, aVar);
        this.b = 0;
        setAutoPlay(rVar.k().a().videoAutoPlay);
    }

    @Override // com.flurry.sdk.dq
    protected int getViewParams() {
        if (this.b == 0) {
            this.b = getAdController().m().i();
        }
        return this.b;
    }

    @Override // com.flurry.sdk.dq
    public void setAutoPlay(boolean z) {
        super.setAutoPlay(true);
    }

    @Override // com.flurry.sdk.dq, com.flurry.sdk.dx
    public void a(String str, float f, float f2) {
        super.a(str, f, f2);
        if (f2 > 0.0f) {
            this.b |= 1;
        }
    }
}
