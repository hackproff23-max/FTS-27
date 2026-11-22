package com.nuance.nmdp.speechkit;

import defpackage.bf;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class e extends a<DataUploadResult> {
    e(defpackage.m mVar, String str, List<defpackage.q> list) {
        super(mVar, str, list);
    }

    @Override // com.nuance.nmdp.speechkit.a
    protected final bf<DataUploadResult> a() {
        return new g().a();
    }

    @Override // com.nuance.nmdp.speechkit.a
    protected final defpackage.g a(defpackage.m mVar, String str, List<defpackage.q> list, bf<DataUploadResult> bfVar, defpackage.h hVar) {
        return mVar.a(str, list, bfVar, hVar);
    }
}
