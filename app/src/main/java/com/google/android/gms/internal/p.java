package com.google.android.gms.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
class p implements n {
    private qp lb;
    private byte[] lc;
    private final int ld;

    public p(int i) {
        this.ld = i;
        reset();
    }

    @Override // com.google.android.gms.internal.n
    public byte[] D() throws IOException {
        int iRO = this.lb.rO();
        if (iRO < 0) {
            throw new IOException();
        }
        if (iRO == 0) {
            return this.lc;
        }
        byte[] bArr = new byte[this.lc.length - iRO];
        System.arraycopy(this.lc, 0, bArr, 0, bArr.length);
        return bArr;
    }

    @Override // com.google.android.gms.internal.n
    public void b(int i, long j) throws IOException {
        this.lb.b(i, j);
    }

    @Override // com.google.android.gms.internal.n
    public void b(int i, String str) throws IOException {
        this.lb.b(i, str);
    }

    @Override // com.google.android.gms.internal.n
    public void reset() {
        this.lc = new byte[this.ld];
        this.lb = qp.q(this.lc);
    }
}
