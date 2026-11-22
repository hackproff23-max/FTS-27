package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
@ey
public class ai {
    protected ah a(Context context, gs gsVar, final gj<ah> gjVar) {
        final aj ajVar = new aj(context, gsVar);
        ajVar.a(new ah.a() { // from class: com.google.android.gms.internal.ai.2
            @Override // com.google.android.gms.internal.ah.a
            public void aR() {
                gjVar.a(ajVar);
            }
        });
        return ajVar;
    }

    public Future<ah> a(final Context context, final gs gsVar, final String str) {
        final gj gjVar = new gj();
        gq.wR.post(new Runnable() { // from class: com.google.android.gms.internal.ai.1
            @Override // java.lang.Runnable
            public void run() {
                ai.this.a(context, gsVar, gjVar).f(str);
            }
        });
        return gjVar;
    }
}
