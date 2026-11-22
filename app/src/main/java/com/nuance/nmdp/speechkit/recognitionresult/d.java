package com.nuance.nmdp.speechkit.recognitionresult;

import defpackage.fd;

/* JADX INFO: loaded from: classes.dex */
final class d implements Token {
    private fd a;

    d(fd fdVar) {
        this.a = fdVar;
    }

    @Override // com.nuance.nmdp.speechkit.recognitionresult.Token
    public final double getConfidenceScore() {
        return this.a.a();
    }

    @Override // com.nuance.nmdp.speechkit.recognitionresult.Token
    public final String toString() {
        return this.a.toString();
    }
}
