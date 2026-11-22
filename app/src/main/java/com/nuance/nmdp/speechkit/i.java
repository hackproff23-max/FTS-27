package com.nuance.nmdp.speechkit;

import defpackage.bf;
import defpackage.cv;

/* JADX INFO: loaded from: classes.dex */
final class i implements GenericResult {
    private String a;

    i() {
    }

    final bf<GenericResult> a() {
        return new bf<GenericResult>() { // from class: com.nuance.nmdp.speechkit.i.1
            @Override // defpackage.bf
            public final boolean a() {
                return false;
            }

            @Override // defpackage.bf
            public final /* synthetic */ boolean a(Object obj) {
                i.this.a = ((cv) obj).f("status");
                return i.this.a != null;
            }

            @Override // defpackage.bf
            public final /* bridge */ /* synthetic */ GenericResult b() {
                return i.this;
            }
        };
    }

    @Override // com.nuance.nmdp.speechkit.GenericResult
    public final String getQueryResult() {
        return this.a;
    }
}
