package com.google.android.gms.internal;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class fc {

    public interface a {
        void a(fy fyVar);
    }

    public static gf a(Context context, u uVar, fy.a aVar, gu guVar, cy cyVar, a aVar2) {
        gf fmVar = aVar.vK.ug ? new fm(context, uVar, new ai(), aVar, aVar2) : new fd(context, aVar, guVar, cyVar, aVar2);
        fmVar.start();
        return fmVar;
    }
}
