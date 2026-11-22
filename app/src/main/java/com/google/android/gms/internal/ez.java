package com.google.android.gms.internal;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class ez {

    public interface a {
        void a(fy.a aVar);
    }

    public static gf a(Context context, fh.a aVar, k kVar, a aVar2) {
        fa faVar = new fa(context, aVar, kVar, aVar2);
        faVar.start();
        return faVar;
    }
}
