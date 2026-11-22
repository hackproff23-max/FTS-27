package com.google.android.gms.internal;

import com.flurry.android.Constants;
import com.mopub.nativeads.MoPubNativeAdPositioning;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class qo {
    private int ayM;
    private int ayN;
    private int ayO;
    private int ayP;
    private int ayQ;
    private int ayS;
    private final byte[] buffer;
    private int ayR = MoPubNativeAdPositioning.MoPubClientPositioning.NO_REPEAT;
    private int ayT = 64;
    private int ayU = 67108864;

    private qo(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.ayM = i;
        this.ayN = i + i2;
        this.ayP = i;
    }

    public static long A(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    public static qo a(byte[] bArr, int i, int i2) {
        return new qo(bArr, i, i2);
    }

    public static int gR(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    public static qo p(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    private void rK() {
        this.ayN += this.ayO;
        int i = this.ayN;
        if (i <= this.ayR) {
            this.ayO = 0;
        } else {
            this.ayO = i - this.ayR;
            this.ayN -= this.ayO;
        }
    }

    public void a(qw qwVar) throws IOException {
        int iRG = rG();
        if (this.ayS >= this.ayT) {
            throw qv.rX();
        }
        int iGS = gS(iRG);
        this.ayS++;
        qwVar.b(this);
        gP(0);
        this.ayS--;
        gT(iGS);
    }

    public void a(qw qwVar, int i) throws IOException {
        if (this.ayS >= this.ayT) {
            throw qv.rX();
        }
        this.ayS++;
        qwVar.b(this);
        gP(qz.y(i, 4));
        this.ayS--;
    }

    public void gP(int i) throws qv {
        if (this.ayQ != i) {
            throw qv.rV();
        }
    }

    public boolean gQ(int i) throws IOException {
        switch (qz.hk(i)) {
            case 0:
                rC();
                return true;
            case 1:
                rJ();
                return true;
            case 2:
                gW(rG());
                return true;
            case 3:
                rA();
                gP(qz.y(qz.hl(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                rI();
                return true;
            default:
                throw qv.rW();
        }
    }

    public int gS(int i) throws qv {
        if (i < 0) {
            throw qv.rS();
        }
        int i2 = this.ayP + i;
        int i3 = this.ayR;
        if (i2 > i3) {
            throw qv.rR();
        }
        this.ayR = i2;
        rK();
        return i3;
    }

    public void gT(int i) {
        this.ayR = i;
        rK();
    }

    public void gU(int i) {
        if (i > this.ayP - this.ayM) {
            throw new IllegalArgumentException("Position " + i + " is beyond current " + (this.ayP - this.ayM));
        }
        if (i < 0) {
            throw new IllegalArgumentException("Bad position " + i);
        }
        this.ayP = this.ayM + i;
    }

    public byte[] gV(int i) throws IOException {
        if (i < 0) {
            throw qv.rS();
        }
        if (this.ayP + i > this.ayR) {
            gW(this.ayR - this.ayP);
            throw qv.rR();
        }
        if (i > this.ayN - this.ayP) {
            throw qv.rR();
        }
        byte[] bArr = new byte[i];
        System.arraycopy(this.buffer, this.ayP, bArr, 0, i);
        this.ayP += i;
        return bArr;
    }

    public void gW(int i) throws IOException {
        if (i < 0) {
            throw qv.rS();
        }
        if (this.ayP + i > this.ayR) {
            gW(this.ayR - this.ayP);
            throw qv.rR();
        }
        if (i > this.ayN - this.ayP) {
            throw qv.rR();
        }
        this.ayP += i;
    }

    public int getPosition() {
        return this.ayP - this.ayM;
    }

    public void rA() throws IOException {
        int iRz;
        do {
            iRz = rz();
            if (iRz == 0) {
                return;
            }
        } while (gQ(iRz));
    }

    public long rB() throws IOException {
        return rH();
    }

    public int rC() throws IOException {
        return rG();
    }

    public boolean rD() throws IOException {
        return rG() != 0;
    }

    public int rE() throws IOException {
        return gR(rG());
    }

    public long rF() throws IOException {
        return A(rH());
    }

    public int rG() throws IOException {
        byte bRN = rN();
        if (bRN >= 0) {
            return bRN;
        }
        int i = bRN & 127;
        byte bRN2 = rN();
        if (bRN2 >= 0) {
            return i | (bRN2 << 7);
        }
        int i2 = i | ((bRN2 & 127) << 7);
        byte bRN3 = rN();
        if (bRN3 >= 0) {
            return i2 | (bRN3 << 14);
        }
        int i3 = i2 | ((bRN3 & 127) << 14);
        byte bRN4 = rN();
        if (bRN4 >= 0) {
            return i3 | (bRN4 << 21);
        }
        int i4 = i3 | ((bRN4 & 127) << 21);
        byte bRN5 = rN();
        int i5 = i4 | (bRN5 << 28);
        if (bRN5 >= 0) {
            return i5;
        }
        for (int i6 = 0; i6 < 5; i6++) {
            if (rN() >= 0) {
                return i5;
            }
        }
        throw qv.rT();
    }

    public long rH() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte bRN = rN();
            j |= ((long) (bRN & 127)) << i;
            if ((bRN & 128) == 0) {
                return j;
            }
        }
        throw qv.rT();
    }

    public int rI() throws IOException {
        return (rN() & Constants.UNKNOWN) | ((rN() & Constants.UNKNOWN) << 8) | ((rN() & Constants.UNKNOWN) << 16) | ((rN() & Constants.UNKNOWN) << 24);
    }

    public long rJ() throws IOException {
        byte bRN = rN();
        return ((((long) rN()) & 255) << 8) | (((long) bRN) & 255) | ((((long) rN()) & 255) << 16) | ((((long) rN()) & 255) << 24) | ((((long) rN()) & 255) << 32) | ((((long) rN()) & 255) << 40) | ((((long) rN()) & 255) << 48) | ((((long) rN()) & 255) << 56);
    }

    public int rL() {
        if (this.ayR == Integer.MAX_VALUE) {
            return -1;
        }
        return this.ayR - this.ayP;
    }

    public boolean rM() {
        return this.ayP == this.ayN;
    }

    public byte rN() throws IOException {
        if (this.ayP == this.ayN) {
            throw qv.rR();
        }
        byte[] bArr = this.buffer;
        int i = this.ayP;
        this.ayP = i + 1;
        return bArr[i];
    }

    public byte[] readBytes() throws IOException {
        int iRG = rG();
        if (iRG > this.ayN - this.ayP || iRG <= 0) {
            return gV(iRG);
        }
        byte[] bArr = new byte[iRG];
        System.arraycopy(this.buffer, this.ayP, bArr, 0, iRG);
        this.ayP = iRG + this.ayP;
        return bArr;
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(rJ());
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(rI());
    }

    public String readString() throws IOException {
        int iRG = rG();
        if (iRG > this.ayN - this.ayP || iRG <= 0) {
            return new String(gV(iRG), "UTF-8");
        }
        String str = new String(this.buffer, this.ayP, iRG, "UTF-8");
        this.ayP = iRG + this.ayP;
        return str;
    }

    public int rz() throws IOException {
        if (rM()) {
            this.ayQ = 0;
            return 0;
        }
        this.ayQ = rG();
        if (this.ayQ == 0) {
            throw qv.rU();
        }
        return this.ayQ;
    }

    public byte[] s(int i, int i2) {
        if (i2 == 0) {
            return qz.azq;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.ayM + i, bArr, 0, i2);
        return bArr;
    }
}
