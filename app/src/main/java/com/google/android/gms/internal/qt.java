package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class qt {
    private qr<?, ?> azd;
    private Object aze;
    private List<qy> azf = new ArrayList();

    qt() {
    }

    private byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[c()];
        a(qp.q(bArr));
        return bArr;
    }

    void a(qp qpVar) throws IOException {
        if (this.aze != null) {
            this.azd.a(this.aze, qpVar);
            return;
        }
        Iterator<qy> it = this.azf.iterator();
        while (it.hasNext()) {
            it.next().a(qpVar);
        }
    }

    void a(qy qyVar) {
        this.azf.add(qyVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    <T> T b(qr<?, T> qrVar) {
        if (this.aze == null) {
            this.azd = qrVar;
            this.aze = qrVar.m(this.azf);
            this.azf = null;
        } else if (this.azd != qrVar) {
            throw new IllegalStateException("Tried to getExtension with a differernt Extension.");
        }
        return (T) this.aze;
    }

    int c() {
        int iC = 0;
        if (this.aze != null) {
            return this.azd.B(this.aze);
        }
        Iterator<qy> it = this.azf.iterator();
        while (true) {
            int i = iC;
            if (!it.hasNext()) {
                return i;
            }
            iC = it.next().c() + i;
        }
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof qt)) {
            return false;
        }
        qt qtVar = (qt) o;
        if (this.aze == null || qtVar.aze == null) {
            if (this.azf != null && qtVar.azf != null) {
                return this.azf.equals(qtVar.azf);
            }
            try {
                return Arrays.equals(toByteArray(), qtVar.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        if (this.azd != qtVar.azd) {
            return false;
        }
        if (!this.azd.ayX.isArray()) {
            return this.aze.equals(qtVar.aze);
        }
        if (this.aze instanceof byte[]) {
            return Arrays.equals((byte[]) this.aze, (byte[]) qtVar.aze);
        }
        if (this.aze instanceof int[]) {
            return Arrays.equals((int[]) this.aze, (int[]) qtVar.aze);
        }
        if (this.aze instanceof long[]) {
            return Arrays.equals((long[]) this.aze, (long[]) qtVar.aze);
        }
        if (this.aze instanceof float[]) {
            return Arrays.equals((float[]) this.aze, (float[]) qtVar.aze);
        }
        if (this.aze instanceof double[]) {
            return Arrays.equals((double[]) this.aze, (double[]) qtVar.aze);
        }
        return this.aze instanceof boolean[] ? Arrays.equals((boolean[]) this.aze, (boolean[]) qtVar.aze) : Arrays.deepEquals((Object[]) this.aze, (Object[]) qtVar.aze);
    }

    public int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
