package com.chartboost.sdk.impl;

import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class cu implements Serializable {
    final byte a;
    final byte[] b;

    public byte a() {
        return this.a;
    }

    public byte[] b() {
        return this.b;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof cu)) {
            return false;
        }
        cu cuVar = (cu) o;
        return this.a == cuVar.a && Arrays.equals(this.b, cuVar.b);
    }

    public int hashCode() {
        return (this.b != null ? Arrays.hashCode(this.b) : 0) + (this.a * 31);
    }
}
