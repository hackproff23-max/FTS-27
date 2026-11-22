package com.google.android.gms.internal;

import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.millennialmedia.android.MMException;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public interface ra {

    public static final class a extends qq<a> {
        public String[] azr;
        public String[] azs;
        public int[] azt;

        public a() {
            sa();
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        public void a(qp qpVar) throws IOException {
            if (this.azr != null && this.azr.length > 0) {
                for (int i = 0; i < this.azr.length; i++) {
                    String str = this.azr[i];
                    if (str != null) {
                        qpVar.b(1, str);
                    }
                }
            }
            if (this.azs != null && this.azs.length > 0) {
                for (int i2 = 0; i2 < this.azs.length; i2++) {
                    String str2 = this.azs[i2];
                    if (str2 != null) {
                        qpVar.b(2, str2);
                    }
                }
            }
            if (this.azt != null && this.azt.length > 0) {
                for (int i3 = 0; i3 < this.azt.length; i3++) {
                    qpVar.t(3, this.azt[i3]);
                }
            }
            super.a(qpVar);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        protected int c() {
            int i;
            int iC = super.c();
            if (this.azr == null || this.azr.length <= 0) {
                i = iC;
            } else {
                int iDk = 0;
                int i2 = 0;
                for (int i3 = 0; i3 < this.azr.length; i3++) {
                    String str = this.azr[i3];
                    if (str != null) {
                        i2++;
                        iDk += qp.dk(str);
                    }
                }
                i = iC + iDk + (i2 * 1);
            }
            if (this.azs != null && this.azs.length > 0) {
                int iDk2 = 0;
                int i4 = 0;
                for (int i5 = 0; i5 < this.azs.length; i5++) {
                    String str2 = this.azs[i5];
                    if (str2 != null) {
                        i4++;
                        iDk2 += qp.dk(str2);
                    }
                }
                i = i + iDk2 + (i4 * 1);
            }
            if (this.azt == null || this.azt.length <= 0) {
                return i;
            }
            int iGZ = 0;
            for (int i6 = 0; i6 < this.azt.length; i6++) {
                iGZ += qp.gZ(this.azt[i6]);
            }
            return i + iGZ + (this.azt.length * 1);
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof a)) {
                return false;
            }
            a aVar = (a) o;
            if (qu.equals(this.azr, aVar.azr) && qu.equals(this.azs, aVar.azs) && qu.equals(this.azt, aVar.azt)) {
                return a(aVar);
            }
            return false;
        }

        public int hashCode() {
            return ((((((qu.hashCode(this.azr) + 527) * 31) + qu.hashCode(this.azs)) * 31) + qu.hashCode(this.azt)) * 31) + rQ();
        }

        public a sa() {
            this.azr = qz.azo;
            this.azs = qz.azo;
            this.azt = qz.azj;
            this.ayW = null;
            this.azh = -1;
            return this;
        }

        @Override // com.google.android.gms.internal.qw
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public a b(qo qoVar) throws IOException {
            while (true) {
                int iRz = qoVar.rz();
                switch (iRz) {
                    case 0:
                        break;
                    case 10:
                        int iB = qz.b(qoVar, 10);
                        int length = this.azr == null ? 0 : this.azr.length;
                        String[] strArr = new String[iB + length];
                        if (length != 0) {
                            System.arraycopy(this.azr, 0, strArr, 0, length);
                        }
                        while (length < strArr.length - 1) {
                            strArr[length] = qoVar.readString();
                            qoVar.rz();
                            length++;
                        }
                        strArr[length] = qoVar.readString();
                        this.azr = strArr;
                        break;
                    case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                        int iB2 = qz.b(qoVar, 18);
                        int length2 = this.azs == null ? 0 : this.azs.length;
                        String[] strArr2 = new String[iB2 + length2];
                        if (length2 != 0) {
                            System.arraycopy(this.azs, 0, strArr2, 0, length2);
                        }
                        while (length2 < strArr2.length - 1) {
                            strArr2[length2] = qoVar.readString();
                            qoVar.rz();
                            length2++;
                        }
                        strArr2[length2] = qoVar.readString();
                        this.azs = strArr2;
                        break;
                    case MMException.DISPLAY_AD_NOT_PERMITTED /* 24 */:
                        int iB3 = qz.b(qoVar, 24);
                        int length3 = this.azt == null ? 0 : this.azt.length;
                        int[] iArr = new int[iB3 + length3];
                        if (length3 != 0) {
                            System.arraycopy(this.azt, 0, iArr, 0, length3);
                        }
                        while (length3 < iArr.length - 1) {
                            iArr[length3] = qoVar.rC();
                            qoVar.rz();
                            length3++;
                        }
                        iArr[length3] = qoVar.rC();
                        this.azt = iArr;
                        break;
                    case MMException.AD_NO_ACTIVITY /* 26 */:
                        int iGS = qoVar.gS(qoVar.rG());
                        int position = qoVar.getPosition();
                        int i = 0;
                        while (qoVar.rL() > 0) {
                            qoVar.rC();
                            i++;
                        }
                        qoVar.gU(position);
                        int length4 = this.azt == null ? 0 : this.azt.length;
                        int[] iArr2 = new int[i + length4];
                        if (length4 != 0) {
                            System.arraycopy(this.azt, 0, iArr2, 0, length4);
                        }
                        while (length4 < iArr2.length) {
                            iArr2[length4] = qoVar.rC();
                            length4++;
                        }
                        this.azt = iArr2;
                        qoVar.gT(iGS);
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

    public static final class b extends qq<b> {
        public int azu;
        public String azv;
        public String version;

        public b() {
            sb();
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        public void a(qp qpVar) throws IOException {
            if (this.azu != 0) {
                qpVar.t(1, this.azu);
            }
            if (!this.azv.equals("")) {
                qpVar.b(2, this.azv);
            }
            if (!this.version.equals("")) {
                qpVar.b(3, this.version);
            }
            super.a(qpVar);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        protected int c() {
            int iC = super.c();
            if (this.azu != 0) {
                iC += qp.v(1, this.azu);
            }
            if (!this.azv.equals("")) {
                iC += qp.j(2, this.azv);
            }
            return !this.version.equals("") ? iC + qp.j(3, this.version) : iC;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof b)) {
                return false;
            }
            b bVar = (b) o;
            if (this.azu != bVar.azu) {
                return false;
            }
            if (this.azv == null) {
                if (bVar.azv != null) {
                    return false;
                }
            } else if (!this.azv.equals(bVar.azv)) {
                return false;
            }
            if (this.version == null) {
                if (bVar.version != null) {
                    return false;
                }
            } else if (!this.version.equals(bVar.version)) {
                return false;
            }
            return a(bVar);
        }

        public int hashCode() {
            return (((((this.azv == null ? 0 : this.azv.hashCode()) + ((this.azu + 527) * 31)) * 31) + (this.version != null ? this.version.hashCode() : 0)) * 31) + rQ();
        }

        public b sb() {
            this.azu = 0;
            this.azv = "";
            this.version = "";
            this.ayW = null;
            this.azh = -1;
            return this;
        }

        @Override // com.google.android.gms.internal.qw
        /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
        public b b(qo qoVar) throws IOException {
            while (true) {
                int iRz = qoVar.rz();
                switch (iRz) {
                    case 0:
                        break;
                    case 8:
                        int iRC = qoVar.rC();
                        switch (iRC) {
                            case 0:
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
                            case 16:
                            case 17:
                            case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                            case 19:
                            case 20:
                            case MMException.DISPLAY_AD_EXPIRED /* 21 */:
                            case MMException.DISPLAY_AD_NOT_FOUND /* 22 */:
                                this.azu = iRC;
                                break;
                        }
                        break;
                    case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                        this.azv = qoVar.readString();
                        break;
                    case MMException.AD_NO_ACTIVITY /* 26 */:
                        this.version = qoVar.readString();
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

    public static final class c extends qq<c> {
        public d[] azA;
        public b azB;
        public byte[] azC;
        public byte[] azD;
        public byte[] azE;
        public a azF;
        public String azG;
        public long azH;
        public long azw;
        public int azx;
        public int azy;
        public boolean azz;
        public String tag;

        public c() {
            sc();
        }

        @Override // com.google.android.gms.internal.qw
        /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
        public c b(qo qoVar) throws IOException {
            while (true) {
                int iRz = qoVar.rz();
                switch (iRz) {
                    case 0:
                        break;
                    case 8:
                        this.azw = qoVar.rB();
                        break;
                    case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                        this.tag = qoVar.readString();
                        break;
                    case MMException.AD_NO_ACTIVITY /* 26 */:
                        int iB = qz.b(qoVar, 26);
                        int length = this.azA == null ? 0 : this.azA.length;
                        d[] dVarArr = new d[iB + length];
                        if (length != 0) {
                            System.arraycopy(this.azA, 0, dVarArr, 0, length);
                        }
                        while (length < dVarArr.length - 1) {
                            dVarArr[length] = new d();
                            qoVar.a(dVarArr[length]);
                            qoVar.rz();
                            length++;
                        }
                        dVarArr[length] = new d();
                        qoVar.a(dVarArr[length]);
                        this.azA = dVarArr;
                        break;
                    case 50:
                        this.azC = qoVar.readBytes();
                        break;
                    case 58:
                        if (this.azF == null) {
                            this.azF = new a();
                        }
                        qoVar.a(this.azF);
                        break;
                    case 66:
                        this.azD = qoVar.readBytes();
                        break;
                    case 74:
                        if (this.azB == null) {
                            this.azB = new b();
                        }
                        qoVar.a(this.azB);
                        break;
                    case 80:
                        this.azz = qoVar.rD();
                        break;
                    case 88:
                        this.azx = qoVar.rC();
                        break;
                    case 96:
                        this.azy = qoVar.rC();
                        break;
                    case 106:
                        this.azE = qoVar.readBytes();
                        break;
                    case 114:
                        this.azG = qoVar.readString();
                        break;
                    case 120:
                        this.azH = qoVar.rF();
                        break;
                    default:
                        if (!a(qoVar, iRz)) {
                        }
                        break;
                }
            }
            return this;
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        public void a(qp qpVar) throws IOException {
            if (this.azw != 0) {
                qpVar.b(1, this.azw);
            }
            if (!this.tag.equals("")) {
                qpVar.b(2, this.tag);
            }
            if (this.azA != null && this.azA.length > 0) {
                for (int i = 0; i < this.azA.length; i++) {
                    d dVar = this.azA[i];
                    if (dVar != null) {
                        qpVar.a(3, dVar);
                    }
                }
            }
            if (!Arrays.equals(this.azC, qz.azq)) {
                qpVar.a(6, this.azC);
            }
            if (this.azF != null) {
                qpVar.a(7, this.azF);
            }
            if (!Arrays.equals(this.azD, qz.azq)) {
                qpVar.a(8, this.azD);
            }
            if (this.azB != null) {
                qpVar.a(9, this.azB);
            }
            if (this.azz) {
                qpVar.b(10, this.azz);
            }
            if (this.azx != 0) {
                qpVar.t(11, this.azx);
            }
            if (this.azy != 0) {
                qpVar.t(12, this.azy);
            }
            if (!Arrays.equals(this.azE, qz.azq)) {
                qpVar.a(13, this.azE);
            }
            if (!this.azG.equals("")) {
                qpVar.b(14, this.azG);
            }
            if (this.azH != 0) {
                qpVar.c(15, this.azH);
            }
            super.a(qpVar);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        protected int c() {
            int iC = super.c();
            if (this.azw != 0) {
                iC += qp.d(1, this.azw);
            }
            if (!this.tag.equals("")) {
                iC += qp.j(2, this.tag);
            }
            if (this.azA != null && this.azA.length > 0) {
                int iC2 = iC;
                for (int i = 0; i < this.azA.length; i++) {
                    d dVar = this.azA[i];
                    if (dVar != null) {
                        iC2 += qp.c(3, dVar);
                    }
                }
                iC = iC2;
            }
            if (!Arrays.equals(this.azC, qz.azq)) {
                iC += qp.b(6, this.azC);
            }
            if (this.azF != null) {
                iC += qp.c(7, this.azF);
            }
            if (!Arrays.equals(this.azD, qz.azq)) {
                iC += qp.b(8, this.azD);
            }
            if (this.azB != null) {
                iC += qp.c(9, this.azB);
            }
            if (this.azz) {
                iC += qp.c(10, this.azz);
            }
            if (this.azx != 0) {
                iC += qp.v(11, this.azx);
            }
            if (this.azy != 0) {
                iC += qp.v(12, this.azy);
            }
            if (!Arrays.equals(this.azE, qz.azq)) {
                iC += qp.b(13, this.azE);
            }
            if (!this.azG.equals("")) {
                iC += qp.j(14, this.azG);
            }
            return this.azH != 0 ? iC + qp.e(15, this.azH) : iC;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof c)) {
                return false;
            }
            c cVar = (c) o;
            if (this.azw != cVar.azw) {
                return false;
            }
            if (this.tag == null) {
                if (cVar.tag != null) {
                    return false;
                }
            } else if (!this.tag.equals(cVar.tag)) {
                return false;
            }
            if (this.azx != cVar.azx || this.azy != cVar.azy || this.azz != cVar.azz || !qu.equals(this.azA, cVar.azA)) {
                return false;
            }
            if (this.azB == null) {
                if (cVar.azB != null) {
                    return false;
                }
            } else if (!this.azB.equals(cVar.azB)) {
                return false;
            }
            if (!Arrays.equals(this.azC, cVar.azC) || !Arrays.equals(this.azD, cVar.azD) || !Arrays.equals(this.azE, cVar.azE)) {
                return false;
            }
            if (this.azF == null) {
                if (cVar.azF != null) {
                    return false;
                }
            } else if (!this.azF.equals(cVar.azF)) {
                return false;
            }
            if (this.azG == null) {
                if (cVar.azG != null) {
                    return false;
                }
            } else if (!this.azG.equals(cVar.azG)) {
                return false;
            }
            if (this.azH == cVar.azH) {
                return a(cVar);
            }
            return false;
        }

        public int hashCode() {
            return (((((((this.azF == null ? 0 : this.azF.hashCode()) + (((((((((this.azB == null ? 0 : this.azB.hashCode()) + (((((this.azz ? 1231 : 1237) + (((((((this.tag == null ? 0 : this.tag.hashCode()) + ((((int) (this.azw ^ (this.azw >>> 32))) + 527) * 31)) * 31) + this.azx) * 31) + this.azy) * 31)) * 31) + qu.hashCode(this.azA)) * 31)) * 31) + Arrays.hashCode(this.azC)) * 31) + Arrays.hashCode(this.azD)) * 31) + Arrays.hashCode(this.azE)) * 31)) * 31) + (this.azG != null ? this.azG.hashCode() : 0)) * 31) + ((int) (this.azH ^ (this.azH >>> 32)))) * 31) + rQ();
        }

        public c sc() {
            this.azw = 0L;
            this.tag = "";
            this.azx = 0;
            this.azy = 0;
            this.azz = false;
            this.azA = d.sd();
            this.azB = null;
            this.azC = qz.azq;
            this.azD = qz.azq;
            this.azE = qz.azq;
            this.azF = null;
            this.azG = "";
            this.azH = 0L;
            this.ayW = null;
            this.azh = -1;
            return this;
        }
    }

    public static final class d extends qq<d> {
        private static volatile d[] azI;
        public String fv;
        public String value;

        public d() {
            se();
        }

        public static d[] sd() {
            if (azI == null) {
                synchronized (qu.azg) {
                    if (azI == null) {
                        azI = new d[0];
                    }
                }
            }
            return azI;
        }

        @Override // com.google.android.gms.internal.qw
        /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
        public d b(qo qoVar) throws IOException {
            while (true) {
                int iRz = qoVar.rz();
                switch (iRz) {
                    case 0:
                        break;
                    case 10:
                        this.fv = qoVar.readString();
                        break;
                    case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                        this.value = qoVar.readString();
                        break;
                    default:
                        if (!a(qoVar, iRz)) {
                        }
                        break;
                }
            }
            return this;
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        public void a(qp qpVar) throws IOException {
            if (!this.fv.equals("")) {
                qpVar.b(1, this.fv);
            }
            if (!this.value.equals("")) {
                qpVar.b(2, this.value);
            }
            super.a(qpVar);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        protected int c() {
            int iC = super.c();
            if (!this.fv.equals("")) {
                iC += qp.j(1, this.fv);
            }
            return !this.value.equals("") ? iC + qp.j(2, this.value) : iC;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof d)) {
                return false;
            }
            d dVar = (d) o;
            if (this.fv == null) {
                if (dVar.fv != null) {
                    return false;
                }
            } else if (!this.fv.equals(dVar.fv)) {
                return false;
            }
            if (this.value == null) {
                if (dVar.value != null) {
                    return false;
                }
            } else if (!this.value.equals(dVar.value)) {
                return false;
            }
            return a(dVar);
        }

        public int hashCode() {
            return (((((this.fv == null ? 0 : this.fv.hashCode()) + 527) * 31) + (this.value != null ? this.value.hashCode() : 0)) * 31) + rQ();
        }

        public d se() {
            this.fv = "";
            this.value = "";
            this.ayW = null;
            this.azh = -1;
            return this;
        }
    }
}
