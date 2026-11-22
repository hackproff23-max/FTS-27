package com.google.android.gms.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes.dex */
public class qs {
    private static final qt ayZ = new qt();
    private boolean aza;
    private int[] azb;
    private qt[] azc;
    private int mSize;

    public qs() {
        this(10);
    }

    public qs(int i) {
        this.aza = false;
        int iIdealIntArraySize = idealIntArraySize(i);
        this.azb = new int[iIdealIntArraySize];
        this.azc = new qt[iIdealIntArraySize];
        this.mSize = 0;
    }

    private boolean a(int[] iArr, int[] iArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private boolean a(qt[] qtVarArr, qt[] qtVarArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (!qtVarArr[i2].equals(qtVarArr2[i2])) {
                return false;
            }
        }
        return true;
    }

    private void gc() {
        int i = this.mSize;
        int[] iArr = this.azb;
        qt[] qtVarArr = this.azc;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            qt qtVar = qtVarArr[i3];
            if (qtVar != ayZ) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    qtVarArr[i2] = qtVar;
                    qtVarArr[i3] = null;
                }
                i2++;
            }
        }
        this.aza = false;
        this.mSize = i2;
    }

    private int hj(int i) {
        int i2 = 0;
        int i3 = this.mSize - 1;
        while (i2 <= i3) {
            int i4 = (i2 + i3) >>> 1;
            int i5 = this.azb[i4];
            if (i5 < i) {
                i2 = i4 + 1;
            } else {
                if (i5 <= i) {
                    return i4;
                }
                i3 = i4 - 1;
            }
        }
        return i2 ^ (-1);
    }

    private int idealByteArraySize(int need) {
        for (int i = 4; i < 32; i++) {
            if (need <= (1 << i) - 12) {
                return (1 << i) - 12;
            }
        }
        return need;
    }

    private int idealIntArraySize(int need) {
        return idealByteArraySize(need * 4) / 4;
    }

    public void a(int i, qt qtVar) {
        int iHj = hj(i);
        if (iHj >= 0) {
            this.azc[iHj] = qtVar;
            return;
        }
        int iHj2 = iHj ^ (-1);
        if (iHj2 < this.mSize && this.azc[iHj2] == ayZ) {
            this.azb[iHj2] = i;
            this.azc[iHj2] = qtVar;
            return;
        }
        if (this.aza && this.mSize >= this.azb.length) {
            gc();
            iHj2 = hj(i) ^ (-1);
        }
        if (this.mSize >= this.azb.length) {
            int iIdealIntArraySize = idealIntArraySize(this.mSize + 1);
            int[] iArr = new int[iIdealIntArraySize];
            qt[] qtVarArr = new qt[iIdealIntArraySize];
            System.arraycopy(this.azb, 0, iArr, 0, this.azb.length);
            System.arraycopy(this.azc, 0, qtVarArr, 0, this.azc.length);
            this.azb = iArr;
            this.azc = qtVarArr;
        }
        if (this.mSize - iHj2 != 0) {
            System.arraycopy(this.azb, iHj2, this.azb, iHj2 + 1, this.mSize - iHj2);
            System.arraycopy(this.azc, iHj2, this.azc, iHj2 + 1, this.mSize - iHj2);
        }
        this.azb[iHj2] = i;
        this.azc[iHj2] = qtVar;
        this.mSize++;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof qs)) {
            return false;
        }
        qs qsVar = (qs) o;
        if (size() != qsVar.size()) {
            return false;
        }
        return a(this.azb, qsVar.azb, this.mSize) && a(this.azc, qsVar.azc, this.mSize);
    }

    public int hashCode() {
        if (this.aza) {
            gc();
        }
        int iHashCode = 17;
        for (int i = 0; i < this.mSize; i++) {
            iHashCode = (((iHashCode * 31) + this.azb[i]) * 31) + this.azc[i].hashCode();
        }
        return iHashCode;
    }

    public qt hh(int i) {
        int iHj = hj(i);
        if (iHj < 0 || this.azc[iHj] == ayZ) {
            return null;
        }
        return this.azc[iHj];
    }

    public qt hi(int i) {
        if (this.aza) {
            gc();
        }
        return this.azc[i];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        if (this.aza) {
            gc();
        }
        return this.mSize;
    }
}
