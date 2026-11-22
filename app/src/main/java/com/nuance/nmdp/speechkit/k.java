package com.nuance.nmdp.speechkit;

import defpackage.bf;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class k extends a<GenericResult> {
    private final String a;

    k(defpackage.m mVar, String str, String str2, List<defpackage.q> list) {
        super(mVar, str, list);
        this.a = str2;
    }

    @Override // com.nuance.nmdp.speechkit.a
    protected final bf<GenericResult> a() {
        return new i().a();
    }

    @Override // com.nuance.nmdp.speechkit.a
    protected final defpackage.g a(defpackage.m mVar, String str, List<defpackage.q> list, bf<GenericResult> bfVar, defpackage.h hVar) {
        return mVar.a(list, this.a, bfVar, hVar);
    }
}
