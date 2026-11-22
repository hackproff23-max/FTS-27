package com.flurry.sdk;

import android.content.Context;
import android.text.TextUtils;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public class dp extends dq {
    private static final String b = dp.class.getSimpleName();
    private int c;
    private boolean d;
    private float e;
    private float f;

    public dp(Context context, r rVar, ec.a aVar) {
        super(context, rVar, aVar);
        this.c = 0;
        this.d = false;
        this.e = 0.0f;
        this.f = 0.0f;
        setAutoPlay(rVar.k().a().videoAutoPlay);
        setVideoUri(c(a(rVar.k())));
        this.d = TextUtils.isEmpty(b(rVar.k())) ? false : true;
        this.e = rVar.k().a().videoPctCompletionForMoreInfo / 100.0f;
        this.f = rVar.k().a().videoPctCompletionForReward / 100.0f;
    }

    private String a(ap apVar) {
        cy cyVarG = apVar.g();
        if (cyVarG != null) {
            return cv.a(cyVarG.f());
        }
        return null;
    }

    private String b(ap apVar) {
        cy cyVarG = apVar.g();
        if (cyVarG != null) {
            String strI = cyVarG.i();
            if (!TextUtils.isEmpty(strI)) {
                return cv.a(strI);
            }
        }
        return null;
    }

    @Override // com.flurry.sdk.dq
    protected int getViewParams() {
        if (this.c == 0) {
            this.c = getAdController().m().i();
        }
        return this.c;
    }

    @Override // com.flurry.sdk.dq, com.flurry.sdk.dx
    public void a(String str, float f, float f2) {
        super.a(str, f, f2);
        if (f2 > 3000.0f) {
            this.c = this.d ? this.c | 4 : this.c;
        }
        if (f2 > 3.0f) {
            this.c |= 2;
            this.c &= -9;
        }
        long j = getAdController().a().closableTimeMillis15SecOrLess;
        if (f > 15000.0f) {
            j = getAdController().a().closableTimeMillisLongerThan15Sec;
        }
        if (f2 > j) {
            this.c |= 1;
        }
        dt dtVarM = getAdController().m();
        if (this.f > 0.0f && f2 >= this.f * f && !dtVarM.g()) {
            g();
            a(aw.EV_REWARD_GRANTED, Collections.emptyMap());
        }
    }

    @Override // com.flurry.sdk.dq
    public void setAutoPlay(boolean z) {
        super.setAutoPlay(z);
        if (getAdController().m().a() <= 3) {
            this.c = z ? this.c : this.c | 8;
        }
    }

    @Override // com.flurry.sdk.dq, com.flurry.sdk.dx
    public void b() {
        this.c &= -9;
        super.b();
    }

    @Override // com.flurry.sdk.dq, com.flurry.sdk.dx
    public void a(String str) {
        super.a(str);
        if (this.f == 0.0f) {
            a(aw.EV_REWARD_GRANTED, Collections.emptyMap());
        }
    }

    private void g() {
        gd.a(3, b, "Reward granted: ");
        getAdController().m().f(true);
    }
}
