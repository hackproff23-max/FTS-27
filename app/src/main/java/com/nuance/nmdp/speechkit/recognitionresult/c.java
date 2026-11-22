package com.nuance.nmdp.speechkit.recognitionresult;

import defpackage.ez;
import defpackage.fc;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class c implements DetailedResult {
    private fc a;

    c(fc fcVar) {
        this.a = fcVar;
    }

    @Override // com.nuance.nmdp.speechkit.recognitionresult.DetailedResult
    public final List<AlternativeChoice> getAlternatives(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        ez ezVarA = this.a.a(i, i2);
        for (int i3 = 0; i3 < ezVarA.a(); i3++) {
            arrayList.add(new a(ezVarA.a(i3)));
        }
        return arrayList;
    }

    @Override // com.nuance.nmdp.speechkit.recognitionresult.DetailedResult
    public final double getConfidenceScore() {
        return this.a.b();
    }

    @Override // com.nuance.nmdp.speechkit.recognitionresult.DetailedResult
    public final Token getTokenAtCursorPosition(int i) {
        return new d(this.a.b(i));
    }

    @Override // com.nuance.nmdp.speechkit.recognitionresult.DetailedResult
    public final List<Token> getTokens() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.a.a(); i++) {
            arrayList.add(new d(this.a.a(i)));
        }
        return arrayList;
    }

    @Override // com.nuance.nmdp.speechkit.recognitionresult.DetailedResult
    public final String toString() {
        return this.a.toString();
    }
}
