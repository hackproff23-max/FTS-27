package com.facebook.ads.a;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class ab extends f {
    public ab(f.a aVar, long j, aa aaVar, Context context) {
        super(aVar, j, context);
        a(aaVar);
    }

    @Override // com.facebook.ads.a.f
    protected synchronized void e() {
        ((aa) this.b).a(this.a.d());
        if (this.a != null) {
            this.a.b();
        }
    }
}
