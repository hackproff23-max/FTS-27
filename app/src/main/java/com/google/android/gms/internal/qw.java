package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class qw {
    protected volatile int azh = -1;

    public static final <T extends qw> T a(T t, byte[] bArr) throws qv {
        return (T) b(t, bArr, 0, bArr.length);
    }

    public static final void a(qw qwVar, byte[] bArr, int i, int i2) {
        try {
            qp qpVarB = qp.b(bArr, i, i2);
            qwVar.a(qpVarB);
            qpVarB.rP();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public static final <T extends qw> T b(T t, byte[] bArr, int i, int i2) throws qv {
        try {
            qo qoVarA = qo.a(bArr, i, i2);
            t.b(qoVarA);
            qoVarA.gP(0);
            return t;
        } catch (qv e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }

    public static final byte[] f(qw qwVar) {
        byte[] bArr = new byte[qwVar.rZ()];
        a(qwVar, bArr, 0, bArr.length);
        return bArr;
    }

    public void a(qp qpVar) throws IOException {
    }

    public abstract qw b(qo qoVar) throws IOException;

    protected int c() {
        return 0;
    }

    public int rY() {
        if (this.azh < 0) {
            rZ();
        }
        return this.azh;
    }

    public int rZ() {
        int iC = c();
        this.azh = iC;
        return iC;
    }

    public String toString() {
        return qx.g(this);
    }
}
