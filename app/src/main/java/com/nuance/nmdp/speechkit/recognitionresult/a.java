package com.nuance.nmdp.speechkit.recognitionresult;

import defpackage.ey;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class a implements AlternativeChoice {
    private ey a;

    a(ey eyVar) {
        this.a = eyVar;
    }

    @Override // com.nuance.nmdp.speechkit.recognitionresult.AlternativeChoice
    public final List<Token> getTokens() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.a.a(); i++) {
            arrayList.add(new d(this.a.a(i)));
        }
        return arrayList;
    }

    @Override // com.nuance.nmdp.speechkit.recognitionresult.AlternativeChoice
    public final String toString() {
        return this.a.toString();
    }
}
