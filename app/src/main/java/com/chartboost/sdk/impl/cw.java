package com.chartboost.sdk.impl;

/* JADX INFO: loaded from: classes.dex */
public class cw extends cv {
    final ck b;

    public ck b() {
        return this.b;
    }

    @Override // com.chartboost.sdk.impl.cv
    public boolean equals(Object o) {
        if (!(o instanceof cw)) {
            return false;
        }
        cw cwVar = (cw) o;
        return this.a.equals(cwVar.a) && this.b.equals(cwVar.b);
    }

    @Override // com.chartboost.sdk.impl.cv
    public int hashCode() {
        return this.a.hashCode() ^ this.b.hashCode();
    }
}
