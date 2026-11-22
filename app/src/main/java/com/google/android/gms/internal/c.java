package com.google.android.gms.internal;

import android.support.v4.media.TransportMediator;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.millennialmedia.android.MMException;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public interface c {

    public static final class a extends qq<a> {
        public int fn;
        public int fo;
        public int level;

        public a() {
            b();
        }

        @Override // com.google.android.gms.internal.qw
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b(qo qoVar) throws IOException {
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
                                this.level = iRC;
                                break;
                        }
                        break;
                    case 16:
                        this.fn = qoVar.rC();
                        break;
                    case MMException.DISPLAY_AD_NOT_PERMITTED /* 24 */:
                        this.fo = qoVar.rC();
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
            if (this.level != 1) {
                qpVar.t(1, this.level);
            }
            if (this.fn != 0) {
                qpVar.t(2, this.fn);
            }
            if (this.fo != 0) {
                qpVar.t(3, this.fo);
            }
            super.a(qpVar);
        }

        public a b() {
            this.level = 1;
            this.fn = 0;
            this.fo = 0;
            this.ayW = null;
            this.azh = -1;
            return this;
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        protected int c() {
            int iC = super.c();
            if (this.level != 1) {
                iC += qp.v(1, this.level);
            }
            if (this.fn != 0) {
                iC += qp.v(2, this.fn);
            }
            return this.fo != 0 ? iC + qp.v(3, this.fo) : iC;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof a)) {
                return false;
            }
            a aVar = (a) o;
            if (this.level == aVar.level && this.fn == aVar.fn && this.fo == aVar.fo) {
                return a(aVar);
            }
            return false;
        }

        public int hashCode() {
            return ((((((this.level + 527) * 31) + this.fn) * 31) + this.fo) * 31) + rQ();
        }
    }

    public static final class b extends qq<b> {
        private static volatile b[] fp;
        public int[] fq;
        public int fr;
        public boolean fs;
        public boolean ft;
        public int name;

        public b() {
            e();
        }

        public static b[] d() {
            if (fp == null) {
                synchronized (qu.azg) {
                    if (fp == null) {
                        fp = new b[0];
                    }
                }
            }
            return fp;
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        public void a(qp qpVar) throws IOException {
            if (this.ft) {
                qpVar.b(1, this.ft);
            }
            qpVar.t(2, this.fr);
            if (this.fq != null && this.fq.length > 0) {
                for (int i = 0; i < this.fq.length; i++) {
                    qpVar.t(3, this.fq[i]);
                }
            }
            if (this.name != 0) {
                qpVar.t(4, this.name);
            }
            if (this.fs) {
                qpVar.b(6, this.fs);
            }
            super.a(qpVar);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        protected int c() {
            int iV;
            int iGZ = 0;
            int iC = super.c();
            if (this.ft) {
                iC += qp.c(1, this.ft);
            }
            int iV2 = qp.v(2, this.fr) + iC;
            if (this.fq == null || this.fq.length <= 0) {
                iV = iV2;
            } else {
                for (int i = 0; i < this.fq.length; i++) {
                    iGZ += qp.gZ(this.fq[i]);
                }
                iV = iV2 + iGZ + (this.fq.length * 1);
            }
            if (this.name != 0) {
                iV += qp.v(4, this.name);
            }
            return this.fs ? iV + qp.c(6, this.fs) : iV;
        }

        @Override // com.google.android.gms.internal.qw
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public b b(qo qoVar) throws IOException {
            while (true) {
                int iRz = qoVar.rz();
                switch (iRz) {
                    case 0:
                        break;
                    case 8:
                        this.ft = qoVar.rD();
                        break;
                    case 16:
                        this.fr = qoVar.rC();
                        break;
                    case MMException.DISPLAY_AD_NOT_PERMITTED /* 24 */:
                        int iB = qz.b(qoVar, 24);
                        int length = this.fq == null ? 0 : this.fq.length;
                        int[] iArr = new int[iB + length];
                        if (length != 0) {
                            System.arraycopy(this.fq, 0, iArr, 0, length);
                        }
                        while (length < iArr.length - 1) {
                            iArr[length] = qoVar.rC();
                            qoVar.rz();
                            length++;
                        }
                        iArr[length] = qoVar.rC();
                        this.fq = iArr;
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
                        int length2 = this.fq == null ? 0 : this.fq.length;
                        int[] iArr2 = new int[i + length2];
                        if (length2 != 0) {
                            System.arraycopy(this.fq, 0, iArr2, 0, length2);
                        }
                        while (length2 < iArr2.length) {
                            iArr2[length2] = qoVar.rC();
                            length2++;
                        }
                        this.fq = iArr2;
                        qoVar.gT(iGS);
                        break;
                    case 32:
                        this.name = qoVar.rC();
                        break;
                    case 48:
                        this.fs = qoVar.rD();
                        break;
                    default:
                        if (!a(qoVar, iRz)) {
                        }
                        break;
                }
            }
            return this;
        }

        public b e() {
            this.fq = qz.azj;
            this.fr = 0;
            this.name = 0;
            this.fs = false;
            this.ft = false;
            this.ayW = null;
            this.azh = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof b)) {
                return false;
            }
            b bVar = (b) o;
            if (qu.equals(this.fq, bVar.fq) && this.fr == bVar.fr && this.name == bVar.name && this.fs == bVar.fs && this.ft == bVar.ft) {
                return a(bVar);
            }
            return false;
        }

        public int hashCode() {
            return (((((this.fs ? 1231 : 1237) + ((((((qu.hashCode(this.fq) + 527) * 31) + this.fr) * 31) + this.name) * 31)) * 31) + (this.ft ? 1231 : 1237)) * 31) + rQ();
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.internal.c$c, reason: collision with other inner class name */
    public static final class C0070c extends qq<C0070c> {
        private static volatile C0070c[] fu;
        public String fv;
        public long fw;
        public long fx;
        public boolean fy;
        public long fz;

        public C0070c() {
            g();
        }

        public static C0070c[] f() {
            if (fu == null) {
                synchronized (qu.azg) {
                    if (fu == null) {
                        fu = new C0070c[0];
                    }
                }
            }
            return fu;
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        public void a(qp qpVar) throws IOException {
            if (!this.fv.equals("")) {
                qpVar.b(1, this.fv);
            }
            if (this.fw != 0) {
                qpVar.b(2, this.fw);
            }
            if (this.fx != 2147483647L) {
                qpVar.b(3, this.fx);
            }
            if (this.fy) {
                qpVar.b(4, this.fy);
            }
            if (this.fz != 0) {
                qpVar.b(5, this.fz);
            }
            super.a(qpVar);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        protected int c() {
            int iC = super.c();
            if (!this.fv.equals("")) {
                iC += qp.j(1, this.fv);
            }
            if (this.fw != 0) {
                iC += qp.d(2, this.fw);
            }
            if (this.fx != 2147483647L) {
                iC += qp.d(3, this.fx);
            }
            if (this.fy) {
                iC += qp.c(4, this.fy);
            }
            return this.fz != 0 ? iC + qp.d(5, this.fz) : iC;
        }

        @Override // com.google.android.gms.internal.qw
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public C0070c b(qo qoVar) throws IOException {
            while (true) {
                int iRz = qoVar.rz();
                switch (iRz) {
                    case 0:
                        break;
                    case 10:
                        this.fv = qoVar.readString();
                        break;
                    case 16:
                        this.fw = qoVar.rB();
                        break;
                    case MMException.DISPLAY_AD_NOT_PERMITTED /* 24 */:
                        this.fx = qoVar.rB();
                        break;
                    case 32:
                        this.fy = qoVar.rD();
                        break;
                    case 40:
                        this.fz = qoVar.rB();
                        break;
                    default:
                        if (!a(qoVar, iRz)) {
                        }
                        break;
                }
            }
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof C0070c)) {
                return false;
            }
            C0070c c0070c = (C0070c) o;
            if (this.fv == null) {
                if (c0070c.fv != null) {
                    return false;
                }
            } else if (!this.fv.equals(c0070c.fv)) {
                return false;
            }
            if (this.fw == c0070c.fw && this.fx == c0070c.fx && this.fy == c0070c.fy && this.fz == c0070c.fz) {
                return a(c0070c);
            }
            return false;
        }

        public C0070c g() {
            this.fv = "";
            this.fw = 0L;
            this.fx = 2147483647L;
            this.fy = false;
            this.fz = 0L;
            this.ayW = null;
            this.azh = -1;
            return this;
        }

        public int hashCode() {
            return (((((this.fy ? 1231 : 1237) + (((((((this.fv == null ? 0 : this.fv.hashCode()) + 527) * 31) + ((int) (this.fw ^ (this.fw >>> 32)))) * 31) + ((int) (this.fx ^ (this.fx >>> 32)))) * 31)) * 31) + ((int) (this.fz ^ (this.fz >>> 32)))) * 31) + rQ();
        }
    }

    public static final class d extends qq<d> {
        public com.google.android.gms.internal.d.a[] fA;
        public com.google.android.gms.internal.d.a[] fB;
        public C0070c[] fC;

        public d() {
            h();
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        public void a(qp qpVar) throws IOException {
            if (this.fA != null && this.fA.length > 0) {
                for (int i = 0; i < this.fA.length; i++) {
                    com.google.android.gms.internal.d.a aVar = this.fA[i];
                    if (aVar != null) {
                        qpVar.a(1, aVar);
                    }
                }
            }
            if (this.fB != null && this.fB.length > 0) {
                for (int i2 = 0; i2 < this.fB.length; i2++) {
                    com.google.android.gms.internal.d.a aVar2 = this.fB[i2];
                    if (aVar2 != null) {
                        qpVar.a(2, aVar2);
                    }
                }
            }
            if (this.fC != null && this.fC.length > 0) {
                for (int i3 = 0; i3 < this.fC.length; i3++) {
                    C0070c c0070c = this.fC[i3];
                    if (c0070c != null) {
                        qpVar.a(3, c0070c);
                    }
                }
            }
            super.a(qpVar);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        protected int c() {
            int iC = super.c();
            if (this.fA != null && this.fA.length > 0) {
                int iC2 = iC;
                for (int i = 0; i < this.fA.length; i++) {
                    com.google.android.gms.internal.d.a aVar = this.fA[i];
                    if (aVar != null) {
                        iC2 += qp.c(1, aVar);
                    }
                }
                iC = iC2;
            }
            if (this.fB != null && this.fB.length > 0) {
                int iC3 = iC;
                for (int i2 = 0; i2 < this.fB.length; i2++) {
                    com.google.android.gms.internal.d.a aVar2 = this.fB[i2];
                    if (aVar2 != null) {
                        iC3 += qp.c(2, aVar2);
                    }
                }
                iC = iC3;
            }
            if (this.fC != null && this.fC.length > 0) {
                for (int i3 = 0; i3 < this.fC.length; i3++) {
                    C0070c c0070c = this.fC[i3];
                    if (c0070c != null) {
                        iC += qp.c(3, c0070c);
                    }
                }
            }
            return iC;
        }

        @Override // com.google.android.gms.internal.qw
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public d b(qo qoVar) throws IOException {
            while (true) {
                int iRz = qoVar.rz();
                switch (iRz) {
                    case 0:
                        break;
                    case 10:
                        int iB = qz.b(qoVar, 10);
                        int length = this.fA == null ? 0 : this.fA.length;
                        com.google.android.gms.internal.d.a[] aVarArr = new com.google.android.gms.internal.d.a[iB + length];
                        if (length != 0) {
                            System.arraycopy(this.fA, 0, aVarArr, 0, length);
                        }
                        while (length < aVarArr.length - 1) {
                            aVarArr[length] = new com.google.android.gms.internal.d.a();
                            qoVar.a(aVarArr[length]);
                            qoVar.rz();
                            length++;
                        }
                        aVarArr[length] = new com.google.android.gms.internal.d.a();
                        qoVar.a(aVarArr[length]);
                        this.fA = aVarArr;
                        break;
                    case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                        int iB2 = qz.b(qoVar, 18);
                        int length2 = this.fB == null ? 0 : this.fB.length;
                        com.google.android.gms.internal.d.a[] aVarArr2 = new com.google.android.gms.internal.d.a[iB2 + length2];
                        if (length2 != 0) {
                            System.arraycopy(this.fB, 0, aVarArr2, 0, length2);
                        }
                        while (length2 < aVarArr2.length - 1) {
                            aVarArr2[length2] = new com.google.android.gms.internal.d.a();
                            qoVar.a(aVarArr2[length2]);
                            qoVar.rz();
                            length2++;
                        }
                        aVarArr2[length2] = new com.google.android.gms.internal.d.a();
                        qoVar.a(aVarArr2[length2]);
                        this.fB = aVarArr2;
                        break;
                    case MMException.AD_NO_ACTIVITY /* 26 */:
                        int iB3 = qz.b(qoVar, 26);
                        int length3 = this.fC == null ? 0 : this.fC.length;
                        C0070c[] c0070cArr = new C0070c[iB3 + length3];
                        if (length3 != 0) {
                            System.arraycopy(this.fC, 0, c0070cArr, 0, length3);
                        }
                        while (length3 < c0070cArr.length - 1) {
                            c0070cArr[length3] = new C0070c();
                            qoVar.a(c0070cArr[length3]);
                            qoVar.rz();
                            length3++;
                        }
                        c0070cArr[length3] = new C0070c();
                        qoVar.a(c0070cArr[length3]);
                        this.fC = c0070cArr;
                        break;
                    default:
                        if (!a(qoVar, iRz)) {
                        }
                        break;
                }
            }
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof d)) {
                return false;
            }
            d dVar = (d) o;
            if (qu.equals(this.fA, dVar.fA) && qu.equals(this.fB, dVar.fB) && qu.equals(this.fC, dVar.fC)) {
                return a(dVar);
            }
            return false;
        }

        public d h() {
            this.fA = com.google.android.gms.internal.d.a.r();
            this.fB = com.google.android.gms.internal.d.a.r();
            this.fC = C0070c.f();
            this.ayW = null;
            this.azh = -1;
            return this;
        }

        public int hashCode() {
            return ((((((qu.hashCode(this.fA) + 527) * 31) + qu.hashCode(this.fB)) * 31) + qu.hashCode(this.fC)) * 31) + rQ();
        }
    }

    public static final class e extends qq<e> {
        private static volatile e[] fD;
        public int key;
        public int value;

        public e() {
            j();
        }

        public static e[] i() {
            if (fD == null) {
                synchronized (qu.azg) {
                    if (fD == null) {
                        fD = new e[0];
                    }
                }
            }
            return fD;
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        public void a(qp qpVar) throws IOException {
            qpVar.t(1, this.key);
            qpVar.t(2, this.value);
            super.a(qpVar);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        protected int c() {
            return super.c() + qp.v(1, this.key) + qp.v(2, this.value);
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof e)) {
                return false;
            }
            e eVar = (e) o;
            if (this.key == eVar.key && this.value == eVar.value) {
                return a(eVar);
            }
            return false;
        }

        @Override // com.google.android.gms.internal.qw
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public e b(qo qoVar) throws IOException {
            while (true) {
                int iRz = qoVar.rz();
                switch (iRz) {
                    case 0:
                        break;
                    case 8:
                        this.key = qoVar.rC();
                        break;
                    case 16:
                        this.value = qoVar.rC();
                        break;
                    default:
                        if (!a(qoVar, iRz)) {
                        }
                        break;
                }
            }
            return this;
        }

        public int hashCode() {
            return ((((this.key + 527) * 31) + this.value) * 31) + rQ();
        }

        public e j() {
            this.key = 0;
            this.value = 0;
            this.ayW = null;
            this.azh = -1;
            return this;
        }
    }

    public static final class f extends qq<f> {
        public String[] fE;
        public String[] fF;
        public com.google.android.gms.internal.d.a[] fG;
        public e[] fH;
        public b[] fI;
        public b[] fJ;
        public b[] fK;
        public g[] fL;
        public String fM;
        public String fN;
        public String fO;
        public a fP;
        public float fQ;
        public boolean fR;
        public String[] fS;
        public int fT;
        public String version;

        public f() {
            k();
        }

        public static f a(byte[] bArr) throws qv {
            return (f) qw.a(new f(), bArr);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        public void a(qp qpVar) throws IOException {
            if (this.fF != null && this.fF.length > 0) {
                for (int i = 0; i < this.fF.length; i++) {
                    String str = this.fF[i];
                    if (str != null) {
                        qpVar.b(1, str);
                    }
                }
            }
            if (this.fG != null && this.fG.length > 0) {
                for (int i2 = 0; i2 < this.fG.length; i2++) {
                    com.google.android.gms.internal.d.a aVar = this.fG[i2];
                    if (aVar != null) {
                        qpVar.a(2, aVar);
                    }
                }
            }
            if (this.fH != null && this.fH.length > 0) {
                for (int i3 = 0; i3 < this.fH.length; i3++) {
                    e eVar = this.fH[i3];
                    if (eVar != null) {
                        qpVar.a(3, eVar);
                    }
                }
            }
            if (this.fI != null && this.fI.length > 0) {
                for (int i4 = 0; i4 < this.fI.length; i4++) {
                    b bVar = this.fI[i4];
                    if (bVar != null) {
                        qpVar.a(4, bVar);
                    }
                }
            }
            if (this.fJ != null && this.fJ.length > 0) {
                for (int i5 = 0; i5 < this.fJ.length; i5++) {
                    b bVar2 = this.fJ[i5];
                    if (bVar2 != null) {
                        qpVar.a(5, bVar2);
                    }
                }
            }
            if (this.fK != null && this.fK.length > 0) {
                for (int i6 = 0; i6 < this.fK.length; i6++) {
                    b bVar3 = this.fK[i6];
                    if (bVar3 != null) {
                        qpVar.a(6, bVar3);
                    }
                }
            }
            if (this.fL != null && this.fL.length > 0) {
                for (int i7 = 0; i7 < this.fL.length; i7++) {
                    g gVar = this.fL[i7];
                    if (gVar != null) {
                        qpVar.a(7, gVar);
                    }
                }
            }
            if (!this.fM.equals("")) {
                qpVar.b(9, this.fM);
            }
            if (!this.fN.equals("")) {
                qpVar.b(10, this.fN);
            }
            if (!this.fO.equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                qpVar.b(12, this.fO);
            }
            if (!this.version.equals("")) {
                qpVar.b(13, this.version);
            }
            if (this.fP != null) {
                qpVar.a(14, this.fP);
            }
            if (Float.floatToIntBits(this.fQ) != Float.floatToIntBits(0.0f)) {
                qpVar.b(15, this.fQ);
            }
            if (this.fS != null && this.fS.length > 0) {
                for (int i8 = 0; i8 < this.fS.length; i8++) {
                    String str2 = this.fS[i8];
                    if (str2 != null) {
                        qpVar.b(16, str2);
                    }
                }
            }
            if (this.fT != 0) {
                qpVar.t(17, this.fT);
            }
            if (this.fR) {
                qpVar.b(18, this.fR);
            }
            if (this.fE != null && this.fE.length > 0) {
                for (int i9 = 0; i9 < this.fE.length; i9++) {
                    String str3 = this.fE[i9];
                    if (str3 != null) {
                        qpVar.b(19, str3);
                    }
                }
            }
            super.a(qpVar);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        protected int c() {
            int iC;
            int iC2 = super.c();
            if (this.fF == null || this.fF.length <= 0) {
                iC = iC2;
            } else {
                int iDk = 0;
                int i = 0;
                for (int i2 = 0; i2 < this.fF.length; i2++) {
                    String str = this.fF[i2];
                    if (str != null) {
                        i++;
                        iDk += qp.dk(str);
                    }
                }
                iC = iC2 + iDk + (i * 1);
            }
            if (this.fG != null && this.fG.length > 0) {
                int iC3 = iC;
                for (int i3 = 0; i3 < this.fG.length; i3++) {
                    com.google.android.gms.internal.d.a aVar = this.fG[i3];
                    if (aVar != null) {
                        iC3 += qp.c(2, aVar);
                    }
                }
                iC = iC3;
            }
            if (this.fH != null && this.fH.length > 0) {
                int iC4 = iC;
                for (int i4 = 0; i4 < this.fH.length; i4++) {
                    e eVar = this.fH[i4];
                    if (eVar != null) {
                        iC4 += qp.c(3, eVar);
                    }
                }
                iC = iC4;
            }
            if (this.fI != null && this.fI.length > 0) {
                int iC5 = iC;
                for (int i5 = 0; i5 < this.fI.length; i5++) {
                    b bVar = this.fI[i5];
                    if (bVar != null) {
                        iC5 += qp.c(4, bVar);
                    }
                }
                iC = iC5;
            }
            if (this.fJ != null && this.fJ.length > 0) {
                int iC6 = iC;
                for (int i6 = 0; i6 < this.fJ.length; i6++) {
                    b bVar2 = this.fJ[i6];
                    if (bVar2 != null) {
                        iC6 += qp.c(5, bVar2);
                    }
                }
                iC = iC6;
            }
            if (this.fK != null && this.fK.length > 0) {
                int iC7 = iC;
                for (int i7 = 0; i7 < this.fK.length; i7++) {
                    b bVar3 = this.fK[i7];
                    if (bVar3 != null) {
                        iC7 += qp.c(6, bVar3);
                    }
                }
                iC = iC7;
            }
            if (this.fL != null && this.fL.length > 0) {
                int iC8 = iC;
                for (int i8 = 0; i8 < this.fL.length; i8++) {
                    g gVar = this.fL[i8];
                    if (gVar != null) {
                        iC8 += qp.c(7, gVar);
                    }
                }
                iC = iC8;
            }
            if (!this.fM.equals("")) {
                iC += qp.j(9, this.fM);
            }
            if (!this.fN.equals("")) {
                iC += qp.j(10, this.fN);
            }
            if (!this.fO.equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                iC += qp.j(12, this.fO);
            }
            if (!this.version.equals("")) {
                iC += qp.j(13, this.version);
            }
            if (this.fP != null) {
                iC += qp.c(14, this.fP);
            }
            if (Float.floatToIntBits(this.fQ) != Float.floatToIntBits(0.0f)) {
                iC += qp.c(15, this.fQ);
            }
            if (this.fS != null && this.fS.length > 0) {
                int iDk2 = 0;
                int i9 = 0;
                for (int i10 = 0; i10 < this.fS.length; i10++) {
                    String str2 = this.fS[i10];
                    if (str2 != null) {
                        i9++;
                        iDk2 += qp.dk(str2);
                    }
                }
                iC = iC + iDk2 + (i9 * 2);
            }
            if (this.fT != 0) {
                iC += qp.v(17, this.fT);
            }
            if (this.fR) {
                iC += qp.c(18, this.fR);
            }
            if (this.fE == null || this.fE.length <= 0) {
                return iC;
            }
            int iDk3 = 0;
            int i11 = 0;
            for (int i12 = 0; i12 < this.fE.length; i12++) {
                String str3 = this.fE[i12];
                if (str3 != null) {
                    i11++;
                    iDk3 += qp.dk(str3);
                }
            }
            return iC + iDk3 + (i11 * 2);
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof f)) {
                return false;
            }
            f fVar = (f) o;
            if (!qu.equals(this.fE, fVar.fE) || !qu.equals(this.fF, fVar.fF) || !qu.equals(this.fG, fVar.fG) || !qu.equals(this.fH, fVar.fH) || !qu.equals(this.fI, fVar.fI) || !qu.equals(this.fJ, fVar.fJ) || !qu.equals(this.fK, fVar.fK) || !qu.equals(this.fL, fVar.fL)) {
                return false;
            }
            if (this.fM == null) {
                if (fVar.fM != null) {
                    return false;
                }
            } else if (!this.fM.equals(fVar.fM)) {
                return false;
            }
            if (this.fN == null) {
                if (fVar.fN != null) {
                    return false;
                }
            } else if (!this.fN.equals(fVar.fN)) {
                return false;
            }
            if (this.fO == null) {
                if (fVar.fO != null) {
                    return false;
                }
            } else if (!this.fO.equals(fVar.fO)) {
                return false;
            }
            if (this.version == null) {
                if (fVar.version != null) {
                    return false;
                }
            } else if (!this.version.equals(fVar.version)) {
                return false;
            }
            if (this.fP == null) {
                if (fVar.fP != null) {
                    return false;
                }
            } else if (!this.fP.equals(fVar.fP)) {
                return false;
            }
            if (Float.floatToIntBits(this.fQ) == Float.floatToIntBits(fVar.fQ) && this.fR == fVar.fR && qu.equals(this.fS, fVar.fS) && this.fT == fVar.fT) {
                return a(fVar);
            }
            return false;
        }

        @Override // com.google.android.gms.internal.qw
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public f b(qo qoVar) throws IOException {
            while (true) {
                int iRz = qoVar.rz();
                switch (iRz) {
                    case 0:
                        break;
                    case 10:
                        int iB = qz.b(qoVar, 10);
                        int length = this.fF == null ? 0 : this.fF.length;
                        String[] strArr = new String[iB + length];
                        if (length != 0) {
                            System.arraycopy(this.fF, 0, strArr, 0, length);
                        }
                        while (length < strArr.length - 1) {
                            strArr[length] = qoVar.readString();
                            qoVar.rz();
                            length++;
                        }
                        strArr[length] = qoVar.readString();
                        this.fF = strArr;
                        break;
                    case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                        int iB2 = qz.b(qoVar, 18);
                        int length2 = this.fG == null ? 0 : this.fG.length;
                        com.google.android.gms.internal.d.a[] aVarArr = new com.google.android.gms.internal.d.a[iB2 + length2];
                        if (length2 != 0) {
                            System.arraycopy(this.fG, 0, aVarArr, 0, length2);
                        }
                        while (length2 < aVarArr.length - 1) {
                            aVarArr[length2] = new com.google.android.gms.internal.d.a();
                            qoVar.a(aVarArr[length2]);
                            qoVar.rz();
                            length2++;
                        }
                        aVarArr[length2] = new com.google.android.gms.internal.d.a();
                        qoVar.a(aVarArr[length2]);
                        this.fG = aVarArr;
                        break;
                    case MMException.AD_NO_ACTIVITY /* 26 */:
                        int iB3 = qz.b(qoVar, 26);
                        int length3 = this.fH == null ? 0 : this.fH.length;
                        e[] eVarArr = new e[iB3 + length3];
                        if (length3 != 0) {
                            System.arraycopy(this.fH, 0, eVarArr, 0, length3);
                        }
                        while (length3 < eVarArr.length - 1) {
                            eVarArr[length3] = new e();
                            qoVar.a(eVarArr[length3]);
                            qoVar.rz();
                            length3++;
                        }
                        eVarArr[length3] = new e();
                        qoVar.a(eVarArr[length3]);
                        this.fH = eVarArr;
                        break;
                    case 34:
                        int iB4 = qz.b(qoVar, 34);
                        int length4 = this.fI == null ? 0 : this.fI.length;
                        b[] bVarArr = new b[iB4 + length4];
                        if (length4 != 0) {
                            System.arraycopy(this.fI, 0, bVarArr, 0, length4);
                        }
                        while (length4 < bVarArr.length - 1) {
                            bVarArr[length4] = new b();
                            qoVar.a(bVarArr[length4]);
                            qoVar.rz();
                            length4++;
                        }
                        bVarArr[length4] = new b();
                        qoVar.a(bVarArr[length4]);
                        this.fI = bVarArr;
                        break;
                    case 42:
                        int iB5 = qz.b(qoVar, 42);
                        int length5 = this.fJ == null ? 0 : this.fJ.length;
                        b[] bVarArr2 = new b[iB5 + length5];
                        if (length5 != 0) {
                            System.arraycopy(this.fJ, 0, bVarArr2, 0, length5);
                        }
                        while (length5 < bVarArr2.length - 1) {
                            bVarArr2[length5] = new b();
                            qoVar.a(bVarArr2[length5]);
                            qoVar.rz();
                            length5++;
                        }
                        bVarArr2[length5] = new b();
                        qoVar.a(bVarArr2[length5]);
                        this.fJ = bVarArr2;
                        break;
                    case 50:
                        int iB6 = qz.b(qoVar, 50);
                        int length6 = this.fK == null ? 0 : this.fK.length;
                        b[] bVarArr3 = new b[iB6 + length6];
                        if (length6 != 0) {
                            System.arraycopy(this.fK, 0, bVarArr3, 0, length6);
                        }
                        while (length6 < bVarArr3.length - 1) {
                            bVarArr3[length6] = new b();
                            qoVar.a(bVarArr3[length6]);
                            qoVar.rz();
                            length6++;
                        }
                        bVarArr3[length6] = new b();
                        qoVar.a(bVarArr3[length6]);
                        this.fK = bVarArr3;
                        break;
                    case 58:
                        int iB7 = qz.b(qoVar, 58);
                        int length7 = this.fL == null ? 0 : this.fL.length;
                        g[] gVarArr = new g[iB7 + length7];
                        if (length7 != 0) {
                            System.arraycopy(this.fL, 0, gVarArr, 0, length7);
                        }
                        while (length7 < gVarArr.length - 1) {
                            gVarArr[length7] = new g();
                            qoVar.a(gVarArr[length7]);
                            qoVar.rz();
                            length7++;
                        }
                        gVarArr[length7] = new g();
                        qoVar.a(gVarArr[length7]);
                        this.fL = gVarArr;
                        break;
                    case 74:
                        this.fM = qoVar.readString();
                        break;
                    case 82:
                        this.fN = qoVar.readString();
                        break;
                    case 98:
                        this.fO = qoVar.readString();
                        break;
                    case 106:
                        this.version = qoVar.readString();
                        break;
                    case 114:
                        if (this.fP == null) {
                            this.fP = new a();
                        }
                        qoVar.a(this.fP);
                        break;
                    case 125:
                        this.fQ = qoVar.readFloat();
                        break;
                    case TransportMediator.KEYCODE_MEDIA_RECORD /* 130 */:
                        int iB8 = qz.b(qoVar, TransportMediator.KEYCODE_MEDIA_RECORD);
                        int length8 = this.fS == null ? 0 : this.fS.length;
                        String[] strArr2 = new String[iB8 + length8];
                        if (length8 != 0) {
                            System.arraycopy(this.fS, 0, strArr2, 0, length8);
                        }
                        while (length8 < strArr2.length - 1) {
                            strArr2[length8] = qoVar.readString();
                            qoVar.rz();
                            length8++;
                        }
                        strArr2[length8] = qoVar.readString();
                        this.fS = strArr2;
                        break;
                    case 136:
                        this.fT = qoVar.rC();
                        break;
                    case 144:
                        this.fR = qoVar.rD();
                        break;
                    case 154:
                        int iB9 = qz.b(qoVar, 154);
                        int length9 = this.fE == null ? 0 : this.fE.length;
                        String[] strArr3 = new String[iB9 + length9];
                        if (length9 != 0) {
                            System.arraycopy(this.fE, 0, strArr3, 0, length9);
                        }
                        while (length9 < strArr3.length - 1) {
                            strArr3[length9] = qoVar.readString();
                            qoVar.rz();
                            length9++;
                        }
                        strArr3[length9] = qoVar.readString();
                        this.fE = strArr3;
                        break;
                    default:
                        if (!a(qoVar, iRz)) {
                        }
                        break;
                }
            }
            return this;
        }

        public int hashCode() {
            return (((((((this.fR ? 1231 : 1237) + (((((((this.version == null ? 0 : this.version.hashCode()) + (((this.fO == null ? 0 : this.fO.hashCode()) + (((this.fN == null ? 0 : this.fN.hashCode()) + (((this.fM == null ? 0 : this.fM.hashCode()) + ((((((((((((((((qu.hashCode(this.fE) + 527) * 31) + qu.hashCode(this.fF)) * 31) + qu.hashCode(this.fG)) * 31) + qu.hashCode(this.fH)) * 31) + qu.hashCode(this.fI)) * 31) + qu.hashCode(this.fJ)) * 31) + qu.hashCode(this.fK)) * 31) + qu.hashCode(this.fL)) * 31)) * 31)) * 31)) * 31)) * 31) + (this.fP != null ? this.fP.hashCode() : 0)) * 31) + Float.floatToIntBits(this.fQ)) * 31)) * 31) + qu.hashCode(this.fS)) * 31) + this.fT) * 31) + rQ();
        }

        public f k() {
            this.fE = qz.azo;
            this.fF = qz.azo;
            this.fG = com.google.android.gms.internal.d.a.r();
            this.fH = e.i();
            this.fI = b.d();
            this.fJ = b.d();
            this.fK = b.d();
            this.fL = g.l();
            this.fM = "";
            this.fN = "";
            this.fO = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            this.version = "";
            this.fP = null;
            this.fQ = 0.0f;
            this.fR = false;
            this.fS = qz.azo;
            this.fT = 0;
            this.ayW = null;
            this.azh = -1;
            return this;
        }
    }

    public static final class g extends qq<g> {
        private static volatile g[] fU;
        public int[] fV;
        public int[] fW;
        public int[] fX;
        public int[] fY;
        public int[] fZ;
        public int[] ga;
        public int[] gb;
        public int[] gc;
        public int[] gd;
        public int[] ge;

        public g() {
            m();
        }

        public static g[] l() {
            if (fU == null) {
                synchronized (qu.azg) {
                    if (fU == null) {
                        fU = new g[0];
                    }
                }
            }
            return fU;
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        public void a(qp qpVar) throws IOException {
            if (this.fV != null && this.fV.length > 0) {
                for (int i = 0; i < this.fV.length; i++) {
                    qpVar.t(1, this.fV[i]);
                }
            }
            if (this.fW != null && this.fW.length > 0) {
                for (int i2 = 0; i2 < this.fW.length; i2++) {
                    qpVar.t(2, this.fW[i2]);
                }
            }
            if (this.fX != null && this.fX.length > 0) {
                for (int i3 = 0; i3 < this.fX.length; i3++) {
                    qpVar.t(3, this.fX[i3]);
                }
            }
            if (this.fY != null && this.fY.length > 0) {
                for (int i4 = 0; i4 < this.fY.length; i4++) {
                    qpVar.t(4, this.fY[i4]);
                }
            }
            if (this.fZ != null && this.fZ.length > 0) {
                for (int i5 = 0; i5 < this.fZ.length; i5++) {
                    qpVar.t(5, this.fZ[i5]);
                }
            }
            if (this.ga != null && this.ga.length > 0) {
                for (int i6 = 0; i6 < this.ga.length; i6++) {
                    qpVar.t(6, this.ga[i6]);
                }
            }
            if (this.gb != null && this.gb.length > 0) {
                for (int i7 = 0; i7 < this.gb.length; i7++) {
                    qpVar.t(7, this.gb[i7]);
                }
            }
            if (this.gc != null && this.gc.length > 0) {
                for (int i8 = 0; i8 < this.gc.length; i8++) {
                    qpVar.t(8, this.gc[i8]);
                }
            }
            if (this.gd != null && this.gd.length > 0) {
                for (int i9 = 0; i9 < this.gd.length; i9++) {
                    qpVar.t(9, this.gd[i9]);
                }
            }
            if (this.ge != null && this.ge.length > 0) {
                for (int i10 = 0; i10 < this.ge.length; i10++) {
                    qpVar.t(10, this.ge[i10]);
                }
            }
            super.a(qpVar);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        protected int c() {
            int length;
            int iC = super.c();
            if (this.fV == null || this.fV.length <= 0) {
                length = iC;
            } else {
                int iGZ = 0;
                for (int i = 0; i < this.fV.length; i++) {
                    iGZ += qp.gZ(this.fV[i]);
                }
                length = iC + iGZ + (this.fV.length * 1);
            }
            if (this.fW != null && this.fW.length > 0) {
                int iGZ2 = 0;
                for (int i2 = 0; i2 < this.fW.length; i2++) {
                    iGZ2 += qp.gZ(this.fW[i2]);
                }
                length = length + iGZ2 + (this.fW.length * 1);
            }
            if (this.fX != null && this.fX.length > 0) {
                int iGZ3 = 0;
                for (int i3 = 0; i3 < this.fX.length; i3++) {
                    iGZ3 += qp.gZ(this.fX[i3]);
                }
                length = length + iGZ3 + (this.fX.length * 1);
            }
            if (this.fY != null && this.fY.length > 0) {
                int iGZ4 = 0;
                for (int i4 = 0; i4 < this.fY.length; i4++) {
                    iGZ4 += qp.gZ(this.fY[i4]);
                }
                length = length + iGZ4 + (this.fY.length * 1);
            }
            if (this.fZ != null && this.fZ.length > 0) {
                int iGZ5 = 0;
                for (int i5 = 0; i5 < this.fZ.length; i5++) {
                    iGZ5 += qp.gZ(this.fZ[i5]);
                }
                length = length + iGZ5 + (this.fZ.length * 1);
            }
            if (this.ga != null && this.ga.length > 0) {
                int iGZ6 = 0;
                for (int i6 = 0; i6 < this.ga.length; i6++) {
                    iGZ6 += qp.gZ(this.ga[i6]);
                }
                length = length + iGZ6 + (this.ga.length * 1);
            }
            if (this.gb != null && this.gb.length > 0) {
                int iGZ7 = 0;
                for (int i7 = 0; i7 < this.gb.length; i7++) {
                    iGZ7 += qp.gZ(this.gb[i7]);
                }
                length = length + iGZ7 + (this.gb.length * 1);
            }
            if (this.gc != null && this.gc.length > 0) {
                int iGZ8 = 0;
                for (int i8 = 0; i8 < this.gc.length; i8++) {
                    iGZ8 += qp.gZ(this.gc[i8]);
                }
                length = length + iGZ8 + (this.gc.length * 1);
            }
            if (this.gd != null && this.gd.length > 0) {
                int iGZ9 = 0;
                for (int i9 = 0; i9 < this.gd.length; i9++) {
                    iGZ9 += qp.gZ(this.gd[i9]);
                }
                length = length + iGZ9 + (this.gd.length * 1);
            }
            if (this.ge == null || this.ge.length <= 0) {
                return length;
            }
            int iGZ10 = 0;
            for (int i10 = 0; i10 < this.ge.length; i10++) {
                iGZ10 += qp.gZ(this.ge[i10]);
            }
            return length + iGZ10 + (this.ge.length * 1);
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof g)) {
                return false;
            }
            g gVar = (g) o;
            if (qu.equals(this.fV, gVar.fV) && qu.equals(this.fW, gVar.fW) && qu.equals(this.fX, gVar.fX) && qu.equals(this.fY, gVar.fY) && qu.equals(this.fZ, gVar.fZ) && qu.equals(this.ga, gVar.ga) && qu.equals(this.gb, gVar.gb) && qu.equals(this.gc, gVar.gc) && qu.equals(this.gd, gVar.gd) && qu.equals(this.ge, gVar.ge)) {
                return a(gVar);
            }
            return false;
        }

        @Override // com.google.android.gms.internal.qw
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public g b(qo qoVar) throws IOException {
            while (true) {
                int iRz = qoVar.rz();
                switch (iRz) {
                    case 0:
                        break;
                    case 8:
                        int iB = qz.b(qoVar, 8);
                        int length = this.fV == null ? 0 : this.fV.length;
                        int[] iArr = new int[iB + length];
                        if (length != 0) {
                            System.arraycopy(this.fV, 0, iArr, 0, length);
                        }
                        while (length < iArr.length - 1) {
                            iArr[length] = qoVar.rC();
                            qoVar.rz();
                            length++;
                        }
                        iArr[length] = qoVar.rC();
                        this.fV = iArr;
                        break;
                    case 10:
                        int iGS = qoVar.gS(qoVar.rG());
                        int position = qoVar.getPosition();
                        int i = 0;
                        while (qoVar.rL() > 0) {
                            qoVar.rC();
                            i++;
                        }
                        qoVar.gU(position);
                        int length2 = this.fV == null ? 0 : this.fV.length;
                        int[] iArr2 = new int[i + length2];
                        if (length2 != 0) {
                            System.arraycopy(this.fV, 0, iArr2, 0, length2);
                        }
                        while (length2 < iArr2.length) {
                            iArr2[length2] = qoVar.rC();
                            length2++;
                        }
                        this.fV = iArr2;
                        qoVar.gT(iGS);
                        break;
                    case 16:
                        int iB2 = qz.b(qoVar, 16);
                        int length3 = this.fW == null ? 0 : this.fW.length;
                        int[] iArr3 = new int[iB2 + length3];
                        if (length3 != 0) {
                            System.arraycopy(this.fW, 0, iArr3, 0, length3);
                        }
                        while (length3 < iArr3.length - 1) {
                            iArr3[length3] = qoVar.rC();
                            qoVar.rz();
                            length3++;
                        }
                        iArr3[length3] = qoVar.rC();
                        this.fW = iArr3;
                        break;
                    case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                        int iGS2 = qoVar.gS(qoVar.rG());
                        int position2 = qoVar.getPosition();
                        int i2 = 0;
                        while (qoVar.rL() > 0) {
                            qoVar.rC();
                            i2++;
                        }
                        qoVar.gU(position2);
                        int length4 = this.fW == null ? 0 : this.fW.length;
                        int[] iArr4 = new int[i2 + length4];
                        if (length4 != 0) {
                            System.arraycopy(this.fW, 0, iArr4, 0, length4);
                        }
                        while (length4 < iArr4.length) {
                            iArr4[length4] = qoVar.rC();
                            length4++;
                        }
                        this.fW = iArr4;
                        qoVar.gT(iGS2);
                        break;
                    case MMException.DISPLAY_AD_NOT_PERMITTED /* 24 */:
                        int iB3 = qz.b(qoVar, 24);
                        int length5 = this.fX == null ? 0 : this.fX.length;
                        int[] iArr5 = new int[iB3 + length5];
                        if (length5 != 0) {
                            System.arraycopy(this.fX, 0, iArr5, 0, length5);
                        }
                        while (length5 < iArr5.length - 1) {
                            iArr5[length5] = qoVar.rC();
                            qoVar.rz();
                            length5++;
                        }
                        iArr5[length5] = qoVar.rC();
                        this.fX = iArr5;
                        break;
                    case MMException.AD_NO_ACTIVITY /* 26 */:
                        int iGS3 = qoVar.gS(qoVar.rG());
                        int position3 = qoVar.getPosition();
                        int i3 = 0;
                        while (qoVar.rL() > 0) {
                            qoVar.rC();
                            i3++;
                        }
                        qoVar.gU(position3);
                        int length6 = this.fX == null ? 0 : this.fX.length;
                        int[] iArr6 = new int[i3 + length6];
                        if (length6 != 0) {
                            System.arraycopy(this.fX, 0, iArr6, 0, length6);
                        }
                        while (length6 < iArr6.length) {
                            iArr6[length6] = qoVar.rC();
                            length6++;
                        }
                        this.fX = iArr6;
                        qoVar.gT(iGS3);
                        break;
                    case 32:
                        int iB4 = qz.b(qoVar, 32);
                        int length7 = this.fY == null ? 0 : this.fY.length;
                        int[] iArr7 = new int[iB4 + length7];
                        if (length7 != 0) {
                            System.arraycopy(this.fY, 0, iArr7, 0, length7);
                        }
                        while (length7 < iArr7.length - 1) {
                            iArr7[length7] = qoVar.rC();
                            qoVar.rz();
                            length7++;
                        }
                        iArr7[length7] = qoVar.rC();
                        this.fY = iArr7;
                        break;
                    case 34:
                        int iGS4 = qoVar.gS(qoVar.rG());
                        int position4 = qoVar.getPosition();
                        int i4 = 0;
                        while (qoVar.rL() > 0) {
                            qoVar.rC();
                            i4++;
                        }
                        qoVar.gU(position4);
                        int length8 = this.fY == null ? 0 : this.fY.length;
                        int[] iArr8 = new int[i4 + length8];
                        if (length8 != 0) {
                            System.arraycopy(this.fY, 0, iArr8, 0, length8);
                        }
                        while (length8 < iArr8.length) {
                            iArr8[length8] = qoVar.rC();
                            length8++;
                        }
                        this.fY = iArr8;
                        qoVar.gT(iGS4);
                        break;
                    case 40:
                        int iB5 = qz.b(qoVar, 40);
                        int length9 = this.fZ == null ? 0 : this.fZ.length;
                        int[] iArr9 = new int[iB5 + length9];
                        if (length9 != 0) {
                            System.arraycopy(this.fZ, 0, iArr9, 0, length9);
                        }
                        while (length9 < iArr9.length - 1) {
                            iArr9[length9] = qoVar.rC();
                            qoVar.rz();
                            length9++;
                        }
                        iArr9[length9] = qoVar.rC();
                        this.fZ = iArr9;
                        break;
                    case 42:
                        int iGS5 = qoVar.gS(qoVar.rG());
                        int position5 = qoVar.getPosition();
                        int i5 = 0;
                        while (qoVar.rL() > 0) {
                            qoVar.rC();
                            i5++;
                        }
                        qoVar.gU(position5);
                        int length10 = this.fZ == null ? 0 : this.fZ.length;
                        int[] iArr10 = new int[i5 + length10];
                        if (length10 != 0) {
                            System.arraycopy(this.fZ, 0, iArr10, 0, length10);
                        }
                        while (length10 < iArr10.length) {
                            iArr10[length10] = qoVar.rC();
                            length10++;
                        }
                        this.fZ = iArr10;
                        qoVar.gT(iGS5);
                        break;
                    case 48:
                        int iB6 = qz.b(qoVar, 48);
                        int length11 = this.ga == null ? 0 : this.ga.length;
                        int[] iArr11 = new int[iB6 + length11];
                        if (length11 != 0) {
                            System.arraycopy(this.ga, 0, iArr11, 0, length11);
                        }
                        while (length11 < iArr11.length - 1) {
                            iArr11[length11] = qoVar.rC();
                            qoVar.rz();
                            length11++;
                        }
                        iArr11[length11] = qoVar.rC();
                        this.ga = iArr11;
                        break;
                    case 50:
                        int iGS6 = qoVar.gS(qoVar.rG());
                        int position6 = qoVar.getPosition();
                        int i6 = 0;
                        while (qoVar.rL() > 0) {
                            qoVar.rC();
                            i6++;
                        }
                        qoVar.gU(position6);
                        int length12 = this.ga == null ? 0 : this.ga.length;
                        int[] iArr12 = new int[i6 + length12];
                        if (length12 != 0) {
                            System.arraycopy(this.ga, 0, iArr12, 0, length12);
                        }
                        while (length12 < iArr12.length) {
                            iArr12[length12] = qoVar.rC();
                            length12++;
                        }
                        this.ga = iArr12;
                        qoVar.gT(iGS6);
                        break;
                    case 56:
                        int iB7 = qz.b(qoVar, 56);
                        int length13 = this.gb == null ? 0 : this.gb.length;
                        int[] iArr13 = new int[iB7 + length13];
                        if (length13 != 0) {
                            System.arraycopy(this.gb, 0, iArr13, 0, length13);
                        }
                        while (length13 < iArr13.length - 1) {
                            iArr13[length13] = qoVar.rC();
                            qoVar.rz();
                            length13++;
                        }
                        iArr13[length13] = qoVar.rC();
                        this.gb = iArr13;
                        break;
                    case 58:
                        int iGS7 = qoVar.gS(qoVar.rG());
                        int position7 = qoVar.getPosition();
                        int i7 = 0;
                        while (qoVar.rL() > 0) {
                            qoVar.rC();
                            i7++;
                        }
                        qoVar.gU(position7);
                        int length14 = this.gb == null ? 0 : this.gb.length;
                        int[] iArr14 = new int[i7 + length14];
                        if (length14 != 0) {
                            System.arraycopy(this.gb, 0, iArr14, 0, length14);
                        }
                        while (length14 < iArr14.length) {
                            iArr14[length14] = qoVar.rC();
                            length14++;
                        }
                        this.gb = iArr14;
                        qoVar.gT(iGS7);
                        break;
                    case 64:
                        int iB8 = qz.b(qoVar, 64);
                        int length15 = this.gc == null ? 0 : this.gc.length;
                        int[] iArr15 = new int[iB8 + length15];
                        if (length15 != 0) {
                            System.arraycopy(this.gc, 0, iArr15, 0, length15);
                        }
                        while (length15 < iArr15.length - 1) {
                            iArr15[length15] = qoVar.rC();
                            qoVar.rz();
                            length15++;
                        }
                        iArr15[length15] = qoVar.rC();
                        this.gc = iArr15;
                        break;
                    case 66:
                        int iGS8 = qoVar.gS(qoVar.rG());
                        int position8 = qoVar.getPosition();
                        int i8 = 0;
                        while (qoVar.rL() > 0) {
                            qoVar.rC();
                            i8++;
                        }
                        qoVar.gU(position8);
                        int length16 = this.gc == null ? 0 : this.gc.length;
                        int[] iArr16 = new int[i8 + length16];
                        if (length16 != 0) {
                            System.arraycopy(this.gc, 0, iArr16, 0, length16);
                        }
                        while (length16 < iArr16.length) {
                            iArr16[length16] = qoVar.rC();
                            length16++;
                        }
                        this.gc = iArr16;
                        qoVar.gT(iGS8);
                        break;
                    case 72:
                        int iB9 = qz.b(qoVar, 72);
                        int length17 = this.gd == null ? 0 : this.gd.length;
                        int[] iArr17 = new int[iB9 + length17];
                        if (length17 != 0) {
                            System.arraycopy(this.gd, 0, iArr17, 0, length17);
                        }
                        while (length17 < iArr17.length - 1) {
                            iArr17[length17] = qoVar.rC();
                            qoVar.rz();
                            length17++;
                        }
                        iArr17[length17] = qoVar.rC();
                        this.gd = iArr17;
                        break;
                    case 74:
                        int iGS9 = qoVar.gS(qoVar.rG());
                        int position9 = qoVar.getPosition();
                        int i9 = 0;
                        while (qoVar.rL() > 0) {
                            qoVar.rC();
                            i9++;
                        }
                        qoVar.gU(position9);
                        int length18 = this.gd == null ? 0 : this.gd.length;
                        int[] iArr18 = new int[i9 + length18];
                        if (length18 != 0) {
                            System.arraycopy(this.gd, 0, iArr18, 0, length18);
                        }
                        while (length18 < iArr18.length) {
                            iArr18[length18] = qoVar.rC();
                            length18++;
                        }
                        this.gd = iArr18;
                        qoVar.gT(iGS9);
                        break;
                    case 80:
                        int iB10 = qz.b(qoVar, 80);
                        int length19 = this.ge == null ? 0 : this.ge.length;
                        int[] iArr19 = new int[iB10 + length19];
                        if (length19 != 0) {
                            System.arraycopy(this.ge, 0, iArr19, 0, length19);
                        }
                        while (length19 < iArr19.length - 1) {
                            iArr19[length19] = qoVar.rC();
                            qoVar.rz();
                            length19++;
                        }
                        iArr19[length19] = qoVar.rC();
                        this.ge = iArr19;
                        break;
                    case 82:
                        int iGS10 = qoVar.gS(qoVar.rG());
                        int position10 = qoVar.getPosition();
                        int i10 = 0;
                        while (qoVar.rL() > 0) {
                            qoVar.rC();
                            i10++;
                        }
                        qoVar.gU(position10);
                        int length20 = this.ge == null ? 0 : this.ge.length;
                        int[] iArr20 = new int[i10 + length20];
                        if (length20 != 0) {
                            System.arraycopy(this.ge, 0, iArr20, 0, length20);
                        }
                        while (length20 < iArr20.length) {
                            iArr20[length20] = qoVar.rC();
                            length20++;
                        }
                        this.ge = iArr20;
                        qoVar.gT(iGS10);
                        break;
                    default:
                        if (!a(qoVar, iRz)) {
                        }
                        break;
                }
            }
            return this;
        }

        public int hashCode() {
            return ((((((((((((((((((((qu.hashCode(this.fV) + 527) * 31) + qu.hashCode(this.fW)) * 31) + qu.hashCode(this.fX)) * 31) + qu.hashCode(this.fY)) * 31) + qu.hashCode(this.fZ)) * 31) + qu.hashCode(this.ga)) * 31) + qu.hashCode(this.gb)) * 31) + qu.hashCode(this.gc)) * 31) + qu.hashCode(this.gd)) * 31) + qu.hashCode(this.ge)) * 31) + rQ();
        }

        public g m() {
            this.fV = qz.azj;
            this.fW = qz.azj;
            this.fX = qz.azj;
            this.fY = qz.azj;
            this.fZ = qz.azj;
            this.ga = qz.azj;
            this.gb = qz.azj;
            this.gc = qz.azj;
            this.gd = qz.azj;
            this.ge = qz.azj;
            this.ayW = null;
            this.azh = -1;
            return this;
        }
    }

    public static final class h extends qq<h> {
        public static final qr<com.google.android.gms.internal.d.a, h> gf = qr.a(11, h.class, 810);
        private static final h[] gg = new h[0];
        public int[] gh;
        public int[] gi;
        public int[] gj;
        public int gk;
        public int[] gl;
        public int gm;
        public int gn;

        public h() {
            n();
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        public void a(qp qpVar) throws IOException {
            if (this.gh != null && this.gh.length > 0) {
                for (int i = 0; i < this.gh.length; i++) {
                    qpVar.t(1, this.gh[i]);
                }
            }
            if (this.gi != null && this.gi.length > 0) {
                for (int i2 = 0; i2 < this.gi.length; i2++) {
                    qpVar.t(2, this.gi[i2]);
                }
            }
            if (this.gj != null && this.gj.length > 0) {
                for (int i3 = 0; i3 < this.gj.length; i3++) {
                    qpVar.t(3, this.gj[i3]);
                }
            }
            if (this.gk != 0) {
                qpVar.t(4, this.gk);
            }
            if (this.gl != null && this.gl.length > 0) {
                for (int i4 = 0; i4 < this.gl.length; i4++) {
                    qpVar.t(5, this.gl[i4]);
                }
            }
            if (this.gm != 0) {
                qpVar.t(6, this.gm);
            }
            if (this.gn != 0) {
                qpVar.t(7, this.gn);
            }
            super.a(qpVar);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        protected int c() {
            int iV;
            int iC = super.c();
            if (this.gh == null || this.gh.length <= 0) {
                iV = iC;
            } else {
                int iGZ = 0;
                for (int i = 0; i < this.gh.length; i++) {
                    iGZ += qp.gZ(this.gh[i]);
                }
                iV = iC + iGZ + (this.gh.length * 1);
            }
            if (this.gi != null && this.gi.length > 0) {
                int iGZ2 = 0;
                for (int i2 = 0; i2 < this.gi.length; i2++) {
                    iGZ2 += qp.gZ(this.gi[i2]);
                }
                iV = iV + iGZ2 + (this.gi.length * 1);
            }
            if (this.gj != null && this.gj.length > 0) {
                int iGZ3 = 0;
                for (int i3 = 0; i3 < this.gj.length; i3++) {
                    iGZ3 += qp.gZ(this.gj[i3]);
                }
                iV = iV + iGZ3 + (this.gj.length * 1);
            }
            if (this.gk != 0) {
                iV += qp.v(4, this.gk);
            }
            if (this.gl != null && this.gl.length > 0) {
                int iGZ4 = 0;
                for (int i4 = 0; i4 < this.gl.length; i4++) {
                    iGZ4 += qp.gZ(this.gl[i4]);
                }
                iV = iV + iGZ4 + (this.gl.length * 1);
            }
            if (this.gm != 0) {
                iV += qp.v(6, this.gm);
            }
            return this.gn != 0 ? iV + qp.v(7, this.gn) : iV;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof h)) {
                return false;
            }
            h hVar = (h) o;
            if (qu.equals(this.gh, hVar.gh) && qu.equals(this.gi, hVar.gi) && qu.equals(this.gj, hVar.gj) && this.gk == hVar.gk && qu.equals(this.gl, hVar.gl) && this.gm == hVar.gm && this.gn == hVar.gn) {
                return a(hVar);
            }
            return false;
        }

        public int hashCode() {
            return ((((((((((((((qu.hashCode(this.gh) + 527) * 31) + qu.hashCode(this.gi)) * 31) + qu.hashCode(this.gj)) * 31) + this.gk) * 31) + qu.hashCode(this.gl)) * 31) + this.gm) * 31) + this.gn) * 31) + rQ();
        }

        @Override // com.google.android.gms.internal.qw
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public h b(qo qoVar) throws IOException {
            while (true) {
                int iRz = qoVar.rz();
                switch (iRz) {
                    case 0:
                        break;
                    case 8:
                        int iB = qz.b(qoVar, 8);
                        int length = this.gh == null ? 0 : this.gh.length;
                        int[] iArr = new int[iB + length];
                        if (length != 0) {
                            System.arraycopy(this.gh, 0, iArr, 0, length);
                        }
                        while (length < iArr.length - 1) {
                            iArr[length] = qoVar.rC();
                            qoVar.rz();
                            length++;
                        }
                        iArr[length] = qoVar.rC();
                        this.gh = iArr;
                        break;
                    case 10:
                        int iGS = qoVar.gS(qoVar.rG());
                        int position = qoVar.getPosition();
                        int i = 0;
                        while (qoVar.rL() > 0) {
                            qoVar.rC();
                            i++;
                        }
                        qoVar.gU(position);
                        int length2 = this.gh == null ? 0 : this.gh.length;
                        int[] iArr2 = new int[i + length2];
                        if (length2 != 0) {
                            System.arraycopy(this.gh, 0, iArr2, 0, length2);
                        }
                        while (length2 < iArr2.length) {
                            iArr2[length2] = qoVar.rC();
                            length2++;
                        }
                        this.gh = iArr2;
                        qoVar.gT(iGS);
                        break;
                    case 16:
                        int iB2 = qz.b(qoVar, 16);
                        int length3 = this.gi == null ? 0 : this.gi.length;
                        int[] iArr3 = new int[iB2 + length3];
                        if (length3 != 0) {
                            System.arraycopy(this.gi, 0, iArr3, 0, length3);
                        }
                        while (length3 < iArr3.length - 1) {
                            iArr3[length3] = qoVar.rC();
                            qoVar.rz();
                            length3++;
                        }
                        iArr3[length3] = qoVar.rC();
                        this.gi = iArr3;
                        break;
                    case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                        int iGS2 = qoVar.gS(qoVar.rG());
                        int position2 = qoVar.getPosition();
                        int i2 = 0;
                        while (qoVar.rL() > 0) {
                            qoVar.rC();
                            i2++;
                        }
                        qoVar.gU(position2);
                        int length4 = this.gi == null ? 0 : this.gi.length;
                        int[] iArr4 = new int[i2 + length4];
                        if (length4 != 0) {
                            System.arraycopy(this.gi, 0, iArr4, 0, length4);
                        }
                        while (length4 < iArr4.length) {
                            iArr4[length4] = qoVar.rC();
                            length4++;
                        }
                        this.gi = iArr4;
                        qoVar.gT(iGS2);
                        break;
                    case MMException.DISPLAY_AD_NOT_PERMITTED /* 24 */:
                        int iB3 = qz.b(qoVar, 24);
                        int length5 = this.gj == null ? 0 : this.gj.length;
                        int[] iArr5 = new int[iB3 + length5];
                        if (length5 != 0) {
                            System.arraycopy(this.gj, 0, iArr5, 0, length5);
                        }
                        while (length5 < iArr5.length - 1) {
                            iArr5[length5] = qoVar.rC();
                            qoVar.rz();
                            length5++;
                        }
                        iArr5[length5] = qoVar.rC();
                        this.gj = iArr5;
                        break;
                    case MMException.AD_NO_ACTIVITY /* 26 */:
                        int iGS3 = qoVar.gS(qoVar.rG());
                        int position3 = qoVar.getPosition();
                        int i3 = 0;
                        while (qoVar.rL() > 0) {
                            qoVar.rC();
                            i3++;
                        }
                        qoVar.gU(position3);
                        int length6 = this.gj == null ? 0 : this.gj.length;
                        int[] iArr6 = new int[i3 + length6];
                        if (length6 != 0) {
                            System.arraycopy(this.gj, 0, iArr6, 0, length6);
                        }
                        while (length6 < iArr6.length) {
                            iArr6[length6] = qoVar.rC();
                            length6++;
                        }
                        this.gj = iArr6;
                        qoVar.gT(iGS3);
                        break;
                    case 32:
                        this.gk = qoVar.rC();
                        break;
                    case 40:
                        int iB4 = qz.b(qoVar, 40);
                        int length7 = this.gl == null ? 0 : this.gl.length;
                        int[] iArr7 = new int[iB4 + length7];
                        if (length7 != 0) {
                            System.arraycopy(this.gl, 0, iArr7, 0, length7);
                        }
                        while (length7 < iArr7.length - 1) {
                            iArr7[length7] = qoVar.rC();
                            qoVar.rz();
                            length7++;
                        }
                        iArr7[length7] = qoVar.rC();
                        this.gl = iArr7;
                        break;
                    case 42:
                        int iGS4 = qoVar.gS(qoVar.rG());
                        int position4 = qoVar.getPosition();
                        int i4 = 0;
                        while (qoVar.rL() > 0) {
                            qoVar.rC();
                            i4++;
                        }
                        qoVar.gU(position4);
                        int length8 = this.gl == null ? 0 : this.gl.length;
                        int[] iArr8 = new int[i4 + length8];
                        if (length8 != 0) {
                            System.arraycopy(this.gl, 0, iArr8, 0, length8);
                        }
                        while (length8 < iArr8.length) {
                            iArr8[length8] = qoVar.rC();
                            length8++;
                        }
                        this.gl = iArr8;
                        qoVar.gT(iGS4);
                        break;
                    case 48:
                        this.gm = qoVar.rC();
                        break;
                    case 56:
                        this.gn = qoVar.rC();
                        break;
                    default:
                        if (!a(qoVar, iRz)) {
                        }
                        break;
                }
            }
            return this;
        }

        public h n() {
            this.gh = qz.azj;
            this.gi = qz.azj;
            this.gj = qz.azj;
            this.gk = 0;
            this.gl = qz.azj;
            this.gm = 0;
            this.gn = 0;
            this.ayW = null;
            this.azh = -1;
            return this;
        }
    }

    public static final class i extends qq<i> {
        private static volatile i[] go;
        public com.google.android.gms.internal.d.a gp;
        public d gq;
        public String name;

        public i() {
            p();
        }

        public static i[] o() {
            if (go == null) {
                synchronized (qu.azg) {
                    if (go == null) {
                        go = new i[0];
                    }
                }
            }
            return go;
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        public void a(qp qpVar) throws IOException {
            if (!this.name.equals("")) {
                qpVar.b(1, this.name);
            }
            if (this.gp != null) {
                qpVar.a(2, this.gp);
            }
            if (this.gq != null) {
                qpVar.a(3, this.gq);
            }
            super.a(qpVar);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        protected int c() {
            int iC = super.c();
            if (!this.name.equals("")) {
                iC += qp.j(1, this.name);
            }
            if (this.gp != null) {
                iC += qp.c(2, this.gp);
            }
            return this.gq != null ? iC + qp.c(3, this.gq) : iC;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof i)) {
                return false;
            }
            i iVar = (i) o;
            if (this.name == null) {
                if (iVar.name != null) {
                    return false;
                }
            } else if (!this.name.equals(iVar.name)) {
                return false;
            }
            if (this.gp == null) {
                if (iVar.gp != null) {
                    return false;
                }
            } else if (!this.gp.equals(iVar.gp)) {
                return false;
            }
            if (this.gq == null) {
                if (iVar.gq != null) {
                    return false;
                }
            } else if (!this.gq.equals(iVar.gq)) {
                return false;
            }
            return a(iVar);
        }

        public int hashCode() {
            return (((((this.gp == null ? 0 : this.gp.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + 527) * 31)) * 31) + (this.gq != null ? this.gq.hashCode() : 0)) * 31) + rQ();
        }

        @Override // com.google.android.gms.internal.qw
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public i b(qo qoVar) throws IOException {
            while (true) {
                int iRz = qoVar.rz();
                switch (iRz) {
                    case 0:
                        break;
                    case 10:
                        this.name = qoVar.readString();
                        break;
                    case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                        if (this.gp == null) {
                            this.gp = new com.google.android.gms.internal.d.a();
                        }
                        qoVar.a(this.gp);
                        break;
                    case MMException.AD_NO_ACTIVITY /* 26 */:
                        if (this.gq == null) {
                            this.gq = new d();
                        }
                        qoVar.a(this.gq);
                        break;
                    default:
                        if (!a(qoVar, iRz)) {
                        }
                        break;
                }
            }
            return this;
        }

        public i p() {
            this.name = "";
            this.gp = null;
            this.gq = null;
            this.ayW = null;
            this.azh = -1;
            return this;
        }
    }

    public static final class j extends qq<j> {
        public i[] gr;
        public f gs;
        public String gt;

        public j() {
            q();
        }

        public static j b(byte[] bArr) throws qv {
            return (j) qw.a(new j(), bArr);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        public void a(qp qpVar) throws IOException {
            if (this.gr != null && this.gr.length > 0) {
                for (int i = 0; i < this.gr.length; i++) {
                    i iVar = this.gr[i];
                    if (iVar != null) {
                        qpVar.a(1, iVar);
                    }
                }
            }
            if (this.gs != null) {
                qpVar.a(2, this.gs);
            }
            if (!this.gt.equals("")) {
                qpVar.b(3, this.gt);
            }
            super.a(qpVar);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        protected int c() {
            int iC = super.c();
            if (this.gr != null && this.gr.length > 0) {
                for (int i = 0; i < this.gr.length; i++) {
                    i iVar = this.gr[i];
                    if (iVar != null) {
                        iC += qp.c(1, iVar);
                    }
                }
            }
            if (this.gs != null) {
                iC += qp.c(2, this.gs);
            }
            return !this.gt.equals("") ? iC + qp.j(3, this.gt) : iC;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof j)) {
                return false;
            }
            j jVar = (j) o;
            if (!qu.equals(this.gr, jVar.gr)) {
                return false;
            }
            if (this.gs == null) {
                if (jVar.gs != null) {
                    return false;
                }
            } else if (!this.gs.equals(jVar.gs)) {
                return false;
            }
            if (this.gt == null) {
                if (jVar.gt != null) {
                    return false;
                }
            } else if (!this.gt.equals(jVar.gt)) {
                return false;
            }
            return a(jVar);
        }

        public int hashCode() {
            return (((((this.gs == null ? 0 : this.gs.hashCode()) + ((qu.hashCode(this.gr) + 527) * 31)) * 31) + (this.gt != null ? this.gt.hashCode() : 0)) * 31) + rQ();
        }

        @Override // com.google.android.gms.internal.qw
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public j b(qo qoVar) throws IOException {
            while (true) {
                int iRz = qoVar.rz();
                switch (iRz) {
                    case 0:
                        break;
                    case 10:
                        int iB = qz.b(qoVar, 10);
                        int length = this.gr == null ? 0 : this.gr.length;
                        i[] iVarArr = new i[iB + length];
                        if (length != 0) {
                            System.arraycopy(this.gr, 0, iVarArr, 0, length);
                        }
                        while (length < iVarArr.length - 1) {
                            iVarArr[length] = new i();
                            qoVar.a(iVarArr[length]);
                            qoVar.rz();
                            length++;
                        }
                        iVarArr[length] = new i();
                        qoVar.a(iVarArr[length]);
                        this.gr = iVarArr;
                        break;
                    case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                        if (this.gs == null) {
                            this.gs = new f();
                        }
                        qoVar.a(this.gs);
                        break;
                    case MMException.AD_NO_ACTIVITY /* 26 */:
                        this.gt = qoVar.readString();
                        break;
                    default:
                        if (!a(qoVar, iRz)) {
                        }
                        break;
                }
            }
            return this;
        }

        public j q() {
            this.gr = i.o();
            this.gs = null;
            this.gt = "";
            this.ayW = null;
            this.azh = -1;
            return this;
        }
    }
}
