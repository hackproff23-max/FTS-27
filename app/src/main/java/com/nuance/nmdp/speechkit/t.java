package com.nuance.nmdp.speechkit;

import defpackage.bf;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class t extends a<GenericResult> {
    t(defpackage.m mVar, String str) {
        super(mVar, str, null);
    }

    @Override // com.nuance.nmdp.speechkit.a
    protected final bf<GenericResult> a() {
        return new i().a();
    }

    @Override // com.nuance.nmdp.speechkit.a
    protected final defpackage.g a(defpackage.m mVar, String str, List<defpackage.q> list, bf<GenericResult> bfVar, defpackage.h hVar) {
        return mVar.a(str, list, bfVar, hVar);
    }
}
