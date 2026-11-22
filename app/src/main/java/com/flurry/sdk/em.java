package com.flurry.sdk;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class em extends en {
    public em(Context context, r rVar) {
        super(context, rVar);
    }

    @Override // com.flurry.sdk.en
    public void a() {
        r rVarD = d();
        if (rVarD.k().r()) {
            a(rVarD, null, true);
        } else {
            cu.a(c(), rVarD, null, true, false);
        }
    }

    private void a(r rVar, String str, boolean z) {
        ea eaVar = new ea();
        eaVar.a = rVar;
        eaVar.b = str;
        eaVar.c = z;
        eaVar.b();
    }
}
