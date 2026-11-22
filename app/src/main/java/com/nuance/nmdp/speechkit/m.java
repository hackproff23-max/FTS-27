package com.nuance.nmdp.speechkit;

import com.nuance.nmdp.speechkit.util.pdx.PdxValue;
import defpackage.ac;
import defpackage.ba;
import defpackage.bf;

/* JADX INFO: loaded from: classes.dex */
abstract class m extends p<GenericRecognition> {
    private final String a;
    private final PdxValue.Dictionary b;

    m(defpackage.m mVar, String str, boolean z, boolean z2, String str2, String str3, PdxValue.Dictionary dictionary, ac acVar) {
        super(mVar, z, z2, str2, str3, acVar);
        this.a = str;
        this.b = dictionary;
    }

    @Override // com.nuance.nmdp.speechkit.p
    protected final bf<GenericRecognition> a() {
        return new h();
    }

    @Override // com.nuance.nmdp.speechkit.p
    protected final defpackage.z a(defpackage.m mVar, boolean z, boolean z2, String str, String str2, ac acVar, ba baVar, ba baVar2, ba baVar3, ba baVar4, bf<GenericRecognition> bfVar, defpackage.aa aaVar) {
        return mVar.a(this.a, z, z2, str, str2, this.b, baVar, baVar2, baVar3, baVar4, bfVar, acVar, aaVar);
    }
}
