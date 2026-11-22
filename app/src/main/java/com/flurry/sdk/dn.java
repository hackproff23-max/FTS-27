package com.flurry.sdk;

import android.content.Context;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public class dn extends dq {
    private int b;

    public dn(Context context, r rVar, ec.a aVar) {
        super(context, rVar, aVar);
        this.b = 0;
        setAutoPlay(rVar.k().a().videoAutoPlay);
        setVideoUri(c(a(rVar.k())));
    }

    private String a(ap apVar) {
        return apVar.j().display;
    }

    @Override // com.flurry.sdk.dq
    protected int getViewParams() {
        if (this.b == 0) {
            this.b = getAdController().m().i();
        }
        return this.b;
    }

    @Override // com.flurry.sdk.dq, com.flurry.sdk.dx
    public void a() {
        a(aw.EV_AD_WILL_CLOSE, Collections.emptyMap());
    }

    @Override // com.flurry.sdk.dq
    public void setAutoPlay(boolean z) {
        super.setAutoPlay(z);
        if (getAdController().m().a() <= 3) {
            this.b = z ? this.b : this.b | 8;
        }
    }

    @Override // com.flurry.sdk.dq, com.flurry.sdk.dx
    public void b() {
        this.b &= -9;
        super.b();
    }

    @Override // com.flurry.sdk.dq, com.flurry.sdk.dx
    public void a(String str, float f, float f2) {
        super.a(str, f, f2);
        if (f2 > 3.0f) {
            this.b |= 2;
            this.b &= -9;
        }
        long j = getAdController().a().closableTimeMillis15SecOrLess;
        if (f > 15000.0f) {
            j = getAdController().a().closableTimeMillisLongerThan15Sec;
        }
        if (f2 > j) {
            this.b |= 1;
        }
    }
}
