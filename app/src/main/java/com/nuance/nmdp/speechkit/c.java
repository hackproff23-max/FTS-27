package com.nuance.nmdp.speechkit;

import com.nuance.nmdp.speechkit.util.pdx.PdxValue;
import defpackage.ac;
import defpackage.ba;
import defpackage.bf;

/* JADX INFO: loaded from: classes.dex */
abstract class c extends p<Recognition> {
    private final String a;
    private final PdxValue.Sequence b;

    c(defpackage.m mVar, String str, boolean z, boolean z2, String str2, PdxValue.Sequence sequence, ac acVar) {
        super(mVar, z, z2, str2, null, acVar);
        this.a = str;
        this.b = sequence;
    }

    @Override // com.nuance.nmdp.speechkit.p
    protected final bf<Recognition> a() {
        return new o().a();
    }

    @Override // com.nuance.nmdp.speechkit.p
    protected final defpackage.z a(defpackage.m mVar, boolean z, boolean z2, String str, String str2, ac acVar, ba baVar, ba baVar2, ba baVar3, ba baVar4, bf<Recognition> bfVar, defpackage.aa aaVar) {
        return mVar.a(this.a, z, z2, str, this.b, baVar, baVar2, baVar3, baVar4, bfVar, acVar, aaVar);
    }
}
