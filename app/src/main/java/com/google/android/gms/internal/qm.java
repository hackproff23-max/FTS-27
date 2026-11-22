package com.google.android.gms.internal;

import com.google.ads.AdSize;
import com.google.android.gms.location.LocationRequest;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.millennialmedia.android.MMException;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class qm extends qq<qm> {
    public a[] ayq;

    public static final class a extends qq<a> {
        private static volatile a[] ayr;
        public C0126a ays;
        public String name;

        /* JADX INFO: renamed from: com.google.android.gms.internal.qm$a$a, reason: collision with other inner class name */
        public static final class C0126a extends qq<C0126a> {
            private static volatile C0126a[] ayt;
            public C0127a ayu;
            public int type;

            /* JADX INFO: renamed from: com.google.android.gms.internal.qm$a$a$a, reason: collision with other inner class name */
            public static final class C0127a extends qq<C0127a> {
                public int ayA;
                public int ayB;
                public boolean ayC;
                public a[] ayD;
                public C0126a[] ayE;
                public String[] ayF;
                public long[] ayG;
                public float[] ayH;
                public long ayI;
                public byte[] ayv;
                public String ayw;
                public double ayx;
                public float ayy;
                public long ayz;

                public C0127a() {
                    ry();
                }

                @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
                public void a(qp qpVar) throws IOException {
                    if (!Arrays.equals(this.ayv, qz.azq)) {
                        qpVar.a(1, this.ayv);
                    }
                    if (!this.ayw.equals("")) {
                        qpVar.b(2, this.ayw);
                    }
                    if (Double.doubleToLongBits(this.ayx) != Double.doubleToLongBits(0.0d)) {
                        qpVar.a(3, this.ayx);
                    }
                    if (Float.floatToIntBits(this.ayy) != Float.floatToIntBits(0.0f)) {
                        qpVar.b(4, this.ayy);
                    }
                    if (this.ayz != 0) {
                        qpVar.b(5, this.ayz);
                    }
                    if (this.ayA != 0) {
                        qpVar.t(6, this.ayA);
                    }
                    if (this.ayB != 0) {
                        qpVar.u(7, this.ayB);
                    }
                    if (this.ayC) {
                        qpVar.b(8, this.ayC);
                    }
                    if (this.ayD != null && this.ayD.length > 0) {
                        for (int i = 0; i < this.ayD.length; i++) {
                            a aVar = this.ayD[i];
                            if (aVar != null) {
                                qpVar.a(9, aVar);
                            }
                        }
                    }
                    if (this.ayE != null && this.ayE.length > 0) {
                        for (int i2 = 0; i2 < this.ayE.length; i2++) {
                            C0126a c0126a = this.ayE[i2];
                            if (c0126a != null) {
                                qpVar.a(10, c0126a);
                            }
                        }
                    }
                    if (this.ayF != null && this.ayF.length > 0) {
                        for (int i3 = 0; i3 < this.ayF.length; i3++) {
                            String str = this.ayF[i3];
                            if (str != null) {
                                qpVar.b(11, str);
                            }
                        }
                    }
                    if (this.ayG != null && this.ayG.length > 0) {
                        for (int i4 = 0; i4 < this.ayG.length; i4++) {
                            qpVar.b(12, this.ayG[i4]);
                        }
                    }
                    if (this.ayI != 0) {
                        qpVar.b(13, this.ayI);
                    }
                    if (this.ayH != null && this.ayH.length > 0) {
                        for (int i5 = 0; i5 < this.ayH.length; i5++) {
                            qpVar.b(14, this.ayH[i5]);
                        }
                    }
                    super.a(qpVar);
                }

                @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
                protected int c() {
                    int iC = super.c();
                    if (!Arrays.equals(this.ayv, qz.azq)) {
                        iC += qp.b(1, this.ayv);
                    }
                    if (!this.ayw.equals("")) {
                        iC += qp.j(2, this.ayw);
                    }
                    if (Double.doubleToLongBits(this.ayx) != Double.doubleToLongBits(0.0d)) {
                        iC += qp.b(3, this.ayx);
                    }
                    if (Float.floatToIntBits(this.ayy) != Float.floatToIntBits(0.0f)) {
                        iC += qp.c(4, this.ayy);
                    }
                    if (this.ayz != 0) {
                        iC += qp.d(5, this.ayz);
                    }
                    if (this.ayA != 0) {
                        iC += qp.v(6, this.ayA);
                    }
                    if (this.ayB != 0) {
                        iC += qp.w(7, this.ayB);
                    }
                    if (this.ayC) {
                        iC += qp.c(8, this.ayC);
                    }
                    if (this.ayD != null && this.ayD.length > 0) {
                        int iC2 = iC;
                        for (int i = 0; i < this.ayD.length; i++) {
                            a aVar = this.ayD[i];
                            if (aVar != null) {
                                iC2 += qp.c(9, aVar);
                            }
                        }
                        iC = iC2;
                    }
                    if (this.ayE != null && this.ayE.length > 0) {
                        int iC3 = iC;
                        for (int i2 = 0; i2 < this.ayE.length; i2++) {
                            C0126a c0126a = this.ayE[i2];
                            if (c0126a != null) {
                                iC3 += qp.c(10, c0126a);
                            }
                        }
                        iC = iC3;
                    }
                    if (this.ayF != null && this.ayF.length > 0) {
                        int iDk = 0;
                        int i3 = 0;
                        for (int i4 = 0; i4 < this.ayF.length; i4++) {
                            String str = this.ayF[i4];
                            if (str != null) {
                                i3++;
                                iDk += qp.dk(str);
                            }
                        }
                        iC = iC + iDk + (i3 * 1);
                    }
                    if (this.ayG != null && this.ayG.length > 0) {
                        int iD = 0;
                        for (int i5 = 0; i5 < this.ayG.length; i5++) {
                            iD += qp.D(this.ayG[i5]);
                        }
                        iC = iC + iD + (this.ayG.length * 1);
                    }
                    if (this.ayI != 0) {
                        iC += qp.d(13, this.ayI);
                    }
                    return (this.ayH == null || this.ayH.length <= 0) ? iC : iC + (this.ayH.length * 4) + (this.ayH.length * 1);
                }

                public boolean equals(Object o) {
                    if (o == this) {
                        return true;
                    }
                    if (!(o instanceof C0127a)) {
                        return false;
                    }
                    C0127a c0127a = (C0127a) o;
                    if (!Arrays.equals(this.ayv, c0127a.ayv)) {
                        return false;
                    }
                    if (this.ayw == null) {
                        if (c0127a.ayw != null) {
                            return false;
                        }
                    } else if (!this.ayw.equals(c0127a.ayw)) {
                        return false;
                    }
                    if (Double.doubleToLongBits(this.ayx) == Double.doubleToLongBits(c0127a.ayx) && Float.floatToIntBits(this.ayy) == Float.floatToIntBits(c0127a.ayy) && this.ayz == c0127a.ayz && this.ayA == c0127a.ayA && this.ayB == c0127a.ayB && this.ayC == c0127a.ayC && qu.equals(this.ayD, c0127a.ayD) && qu.equals(this.ayE, c0127a.ayE) && qu.equals(this.ayF, c0127a.ayF) && qu.equals(this.ayG, c0127a.ayG) && qu.equals(this.ayH, c0127a.ayH) && this.ayI == c0127a.ayI) {
                        return a(c0127a);
                    }
                    return false;
                }

                public int hashCode() {
                    int iHashCode = (Arrays.hashCode(this.ayv) + 527) * 31;
                    int iHashCode2 = this.ayw == null ? 0 : this.ayw.hashCode();
                    long jDoubleToLongBits = Double.doubleToLongBits(this.ayx);
                    return (((((((((((((((this.ayC ? 1231 : 1237) + ((((((((((((iHashCode2 + iHashCode) * 31) + ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32)))) * 31) + Float.floatToIntBits(this.ayy)) * 31) + ((int) (this.ayz ^ (this.ayz >>> 32)))) * 31) + this.ayA) * 31) + this.ayB) * 31)) * 31) + qu.hashCode(this.ayD)) * 31) + qu.hashCode(this.ayE)) * 31) + qu.hashCode(this.ayF)) * 31) + qu.hashCode(this.ayG)) * 31) + qu.hashCode(this.ayH)) * 31) + ((int) (this.ayI ^ (this.ayI >>> 32)))) * 31) + rQ();
                }

                public C0127a ry() {
                    this.ayv = qz.azq;
                    this.ayw = "";
                    this.ayx = 0.0d;
                    this.ayy = 0.0f;
                    this.ayz = 0L;
                    this.ayA = 0;
                    this.ayB = 0;
                    this.ayC = false;
                    this.ayD = a.ru();
                    this.ayE = C0126a.rw();
                    this.ayF = qz.azo;
                    this.ayG = qz.azk;
                    this.ayH = qz.azl;
                    this.ayI = 0L;
                    this.ayW = null;
                    this.azh = -1;
                    return this;
                }

                @Override // com.google.android.gms.internal.qw
                /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
                public C0127a b(qo qoVar) throws IOException {
                    while (true) {
                        int iRz = qoVar.rz();
                        switch (iRz) {
                            case 0:
                                break;
                            case 10:
                                this.ayv = qoVar.readBytes();
                                break;
                            case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                                this.ayw = qoVar.readString();
                                break;
                            case MMException.AD_BROKEN_REFERENCE /* 25 */:
                                this.ayx = qoVar.readDouble();
                                break;
                            case 37:
                                this.ayy = qoVar.readFloat();
                                break;
                            case 40:
                                this.ayz = qoVar.rB();
                                break;
                            case 48:
                                this.ayA = qoVar.rC();
                                break;
                            case 56:
                                this.ayB = qoVar.rE();
                                break;
                            case 64:
                                this.ayC = qoVar.rD();
                                break;
                            case 74:
                                int iB = qz.b(qoVar, 74);
                                int length = this.ayD == null ? 0 : this.ayD.length;
                                a[] aVarArr = new a[iB + length];
                                if (length != 0) {
                                    System.arraycopy(this.ayD, 0, aVarArr, 0, length);
                                }
                                while (length < aVarArr.length - 1) {
                                    aVarArr[length] = new a();
                                    qoVar.a(aVarArr[length]);
                                    qoVar.rz();
                                    length++;
                                }
                                aVarArr[length] = new a();
                                qoVar.a(aVarArr[length]);
                                this.ayD = aVarArr;
                                break;
                            case 82:
                                int iB2 = qz.b(qoVar, 82);
                                int length2 = this.ayE == null ? 0 : this.ayE.length;
                                C0126a[] c0126aArr = new C0126a[iB2 + length2];
                                if (length2 != 0) {
                                    System.arraycopy(this.ayE, 0, c0126aArr, 0, length2);
                                }
                                while (length2 < c0126aArr.length - 1) {
                                    c0126aArr[length2] = new C0126a();
                                    qoVar.a(c0126aArr[length2]);
                                    qoVar.rz();
                                    length2++;
                                }
                                c0126aArr[length2] = new C0126a();
                                qoVar.a(c0126aArr[length2]);
                                this.ayE = c0126aArr;
                                break;
                            case AdSize.LARGE_AD_HEIGHT /* 90 */:
                                int iB3 = qz.b(qoVar, 90);
                                int length3 = this.ayF == null ? 0 : this.ayF.length;
                                String[] strArr = new String[iB3 + length3];
                                if (length3 != 0) {
                                    System.arraycopy(this.ayF, 0, strArr, 0, length3);
                                }
                                while (length3 < strArr.length - 1) {
                                    strArr[length3] = qoVar.readString();
                                    qoVar.rz();
                                    length3++;
                                }
                                strArr[length3] = qoVar.readString();
                                this.ayF = strArr;
                                break;
                            case 96:
                                int iB4 = qz.b(qoVar, 96);
                                int length4 = this.ayG == null ? 0 : this.ayG.length;
                                long[] jArr = new long[iB4 + length4];
                                if (length4 != 0) {
                                    System.arraycopy(this.ayG, 0, jArr, 0, length4);
                                }
                                while (length4 < jArr.length - 1) {
                                    jArr[length4] = qoVar.rB();
                                    qoVar.rz();
                                    length4++;
                                }
                                jArr[length4] = qoVar.rB();
                                this.ayG = jArr;
                                break;
                            case 98:
                                int iGS = qoVar.gS(qoVar.rG());
                                int position = qoVar.getPosition();
                                int i = 0;
                                while (qoVar.rL() > 0) {
                                    qoVar.rB();
                                    i++;
                                }
                                qoVar.gU(position);
                                int length5 = this.ayG == null ? 0 : this.ayG.length;
                                long[] jArr2 = new long[i + length5];
                                if (length5 != 0) {
                                    System.arraycopy(this.ayG, 0, jArr2, 0, length5);
                                }
                                while (length5 < jArr2.length) {
                                    jArr2[length5] = qoVar.rB();
                                    length5++;
                                }
                                this.ayG = jArr2;
                                qoVar.gT(iGS);
                                break;
                            case LocationRequest.PRIORITY_LOW_POWER /* 104 */:
                                this.ayI = qoVar.rB();
                                break;
                            case 114:
                                int iRG = qoVar.rG();
                                int iGS2 = qoVar.gS(iRG);
                                int i2 = iRG / 4;
                                int length6 = this.ayH == null ? 0 : this.ayH.length;
                                float[] fArr = new float[i2 + length6];
                                if (length6 != 0) {
                                    System.arraycopy(this.ayH, 0, fArr, 0, length6);
                                }
                                while (length6 < fArr.length) {
                                    fArr[length6] = qoVar.readFloat();
                                    length6++;
                                }
                                this.ayH = fArr;
                                qoVar.gT(iGS2);
                                break;
                            case 117:
                                int iB5 = qz.b(qoVar, 117);
                                int length7 = this.ayH == null ? 0 : this.ayH.length;
                                float[] fArr2 = new float[iB5 + length7];
                                if (length7 != 0) {
                                    System.arraycopy(this.ayH, 0, fArr2, 0, length7);
                                }
                                while (length7 < fArr2.length - 1) {
                                    fArr2[length7] = qoVar.readFloat();
                                    qoVar.rz();
                                    length7++;
                                }
                                fArr2[length7] = qoVar.readFloat();
                                this.ayH = fArr2;
                                break;
                            default:
                                if (!a(qoVar, iRz)) {
                                }
                                break;
                        }
                    }
                    return this;
                }
            }

            public C0126a() {
                rx();
            }

            public static C0126a[] rw() {
                if (ayt == null) {
                    synchronized (qu.azg) {
                        if (ayt == null) {
                            ayt = new C0126a[0];
                        }
                    }
                }
                return ayt;
            }

            @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
            public void a(qp qpVar) throws IOException {
                qpVar.t(1, this.type);
                if (this.ayu != null) {
                    qpVar.a(2, this.ayu);
                }
                super.a(qpVar);
            }

            @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
            protected int c() {
                int iC = super.c() + qp.v(1, this.type);
                return this.ayu != null ? iC + qp.c(2, this.ayu) : iC;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof C0126a)) {
                    return false;
                }
                C0126a c0126a = (C0126a) o;
                if (this.type != c0126a.type) {
                    return false;
                }
                if (this.ayu == null) {
                    if (c0126a.ayu != null) {
                        return false;
                    }
                } else if (!this.ayu.equals(c0126a.ayu)) {
                    return false;
                }
                return a(c0126a);
            }

            public int hashCode() {
                return (((this.ayu == null ? 0 : this.ayu.hashCode()) + ((this.type + 527) * 31)) * 31) + rQ();
            }

            public C0126a rx() {
                this.type = 1;
                this.ayu = null;
                this.ayW = null;
                this.azh = -1;
                return this;
            }

            @Override // com.google.android.gms.internal.qw
            /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
            public C0126a b(qo qoVar) throws IOException {
                while (true) {
                    int iRz = qoVar.rz();
                    switch (iRz) {
                        case 0:
                            break;
                        case 8:
                            int iRC = qoVar.rC();
                            switch (iRC) {
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                case 10:
                                case 11:
                                case 12:
                                case 13:
                                case 14:
                                case 15:
                                    this.type = iRC;
                                    break;
                            }
                            break;
                        case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                            if (this.ayu == null) {
                                this.ayu = new C0127a();
                            }
                            qoVar.a(this.ayu);
                            break;
                        default:
                            if (!a(qoVar, iRz)) {
                            }
                            break;
                    }
                }
                return this;
            }
        }

        public a() {
            rv();
        }

        public static a[] ru() {
            if (ayr == null) {
                synchronized (qu.azg) {
                    if (ayr == null) {
                        ayr = new a[0];
                    }
                }
            }
            return ayr;
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        public void a(qp qpVar) throws IOException {
            qpVar.b(1, this.name);
            if (this.ays != null) {
                qpVar.a(2, this.ays);
            }
            super.a(qpVar);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        protected int c() {
            int iC = super.c() + qp.j(1, this.name);
            return this.ays != null ? iC + qp.c(2, this.ays) : iC;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof a)) {
                return false;
            }
            a aVar = (a) o;
            if (this.name == null) {
                if (aVar.name != null) {
                    return false;
                }
            } else if (!this.name.equals(aVar.name)) {
                return false;
            }
            if (this.ays == null) {
                if (aVar.ays != null) {
                    return false;
                }
            } else if (!this.ays.equals(aVar.ays)) {
                return false;
            }
            return a(aVar);
        }

        public int hashCode() {
            return (((((this.name == null ? 0 : this.name.hashCode()) + 527) * 31) + (this.ays != null ? this.ays.hashCode() : 0)) * 31) + rQ();
        }

        public a rv() {
            this.name = "";
            this.ays = null;
            this.ayW = null;
            this.azh = -1;
            return this;
        }

        @Override // com.google.android.gms.internal.qw
        /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
        public a b(qo qoVar) throws IOException {
            while (true) {
                int iRz = qoVar.rz();
                switch (iRz) {
                    case 0:
                        break;
                    case 10:
                        this.name = qoVar.readString();
                        break;
                    case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                        if (this.ays == null) {
                            this.ays = new C0126a();
                        }
                        qoVar.a(this.ays);
                        break;
                    default:
                        if (!a(qoVar, iRz)) {
                        }
                        break;
                }
            }
            return this;
        }
    }

    public qm() {
        rt();
    }

    public static qm n(byte[] bArr) throws qv {
        return (qm) qw.a(new qm(), bArr);
    }

    @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
    public void a(qp qpVar) throws IOException {
        if (this.ayq != null && this.ayq.length > 0) {
            for (int i = 0; i < this.ayq.length; i++) {
                a aVar = this.ayq[i];
                if (aVar != null) {
                    qpVar.a(1, aVar);
                }
            }
        }
        super.a(qpVar);
    }

    @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
    protected int c() {
        int iC = super.c();
        if (this.ayq != null && this.ayq.length > 0) {
            for (int i = 0; i < this.ayq.length; i++) {
                a aVar = this.ayq[i];
                if (aVar != null) {
                    iC += qp.c(1, aVar);
                }
            }
        }
        return iC;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof qm)) {
            return false;
        }
        qm qmVar = (qm) o;
        if (qu.equals(this.ayq, qmVar.ayq)) {
            return a(qmVar);
        }
        return false;
    }

    public int hashCode() {
        return ((qu.hashCode(this.ayq) + 527) * 31) + rQ();
    }

    public qm rt() {
        this.ayq = a.ru();
        this.ayW = null;
        this.azh = -1;
        return this;
    }

    @Override // com.google.android.gms.internal.qw
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public qm b(qo qoVar) throws IOException {
        while (true) {
            int iRz = qoVar.rz();
            switch (iRz) {
                case 0:
                    break;
                case 10:
                    int iB = qz.b(qoVar, 10);
                    int length = this.ayq == null ? 0 : this.ayq.length;
                    a[] aVarArr = new a[iB + length];
                    if (length != 0) {
                        System.arraycopy(this.ayq, 0, aVarArr, 0, length);
                    }
                    while (length < aVarArr.length - 1) {
                        aVarArr[length] = new a();
                        qoVar.a(aVarArr[length]);
                        qoVar.rz();
                        length++;
                    }
                    aVarArr[length] = new a();
                    qoVar.a(aVarArr[length]);
                    this.ayq = aVarArr;
                    break;
                default:
                    if (!a(qoVar, iRz)) {
                    }
                    break;
            }
        }
        return this;
    }
}
