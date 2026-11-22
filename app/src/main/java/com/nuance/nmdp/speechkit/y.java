package com.nuance.nmdp.speechkit;

import com.nuance.nmdp.speechkit.util.pdx.PdxValue;
import defpackage.ac;
import defpackage.ba;
import defpackage.bf;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class y extends p<GenericRecognition> {
    private final PdxValue.Dictionary a;
    private List<defpackage.q> b;

    y(defpackage.m mVar, String str, String str2, PdxValue.Dictionary dictionary, ac acVar) {
        super(mVar, false, false, str, str2, acVar);
        this.a = dictionary;
        this.b = null;
    }

    @Override // com.nuance.nmdp.speechkit.p
    protected final bf<GenericRecognition> a() {
        return new h();
    }

    @Override // com.nuance.nmdp.speechkit.p
    protected final defpackage.z a(defpackage.m mVar, boolean z, boolean z2, String str, String str2, ac acVar, ba baVar, ba baVar2, ba baVar3, ba baVar4, bf<GenericRecognition> bfVar, defpackage.aa aaVar) {
        return mVar.a(str, str2, this.a, bfVar, acVar, aaVar);
    }
}
