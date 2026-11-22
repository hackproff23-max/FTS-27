package com.google.android.gms.internal;

import com.google.android.gms.internal.qq;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class qq<M extends qq<M>> extends qw {
    protected qs ayW;

    public final <T> T a(qr<M, T> qrVar) {
        qt qtVarHh;
        if (this.ayW == null || (qtVarHh = this.ayW.hh(qz.hl(qrVar.tag))) == null) {
            return null;
        }
        return (T) qtVarHh.b(qrVar);
    }

    @Override // com.google.android.gms.internal.qw
    public void a(qp qpVar) throws IOException {
        if (this.ayW == null) {
            return;
        }
        for (int i = 0; i < this.ayW.size(); i++) {
            this.ayW.hi(i).a(qpVar);
        }
    }

    protected final boolean a(qo qoVar, int i) throws IOException {
        int position = qoVar.getPosition();
        if (!qoVar.gQ(i)) {
            return false;
        }
        int iHl = qz.hl(i);
        qy qyVar = new qy(i, qoVar.s(position, qoVar.getPosition() - position));
        qt qtVarHh = null;
        if (this.ayW == null) {
            this.ayW = new qs();
        } else {
            qtVarHh = this.ayW.hh(iHl);
        }
        if (qtVarHh == null) {
            qtVarHh = new qt();
            this.ayW.a(iHl, qtVarHh);
        }
        qtVarHh.a(qyVar);
        return true;
    }

    protected final boolean a(M m) {
        if (this.ayW == null || this.ayW.isEmpty()) {
            return m.ayW == null || m.ayW.isEmpty();
        }
        return this.ayW.equals(m.ayW);
    }

    @Override // com.google.android.gms.internal.qw
    protected int c() {
        if (this.ayW == null) {
            return 0;
        }
        int iC = 0;
        for (int i = 0; i < this.ayW.size(); i++) {
            iC += this.ayW.hi(i).c();
        }
        return iC;
    }

    protected final int rQ() {
        if (this.ayW == null || this.ayW.isEmpty()) {
            return 0;
        }
        return this.ayW.hashCode();
    }
}
