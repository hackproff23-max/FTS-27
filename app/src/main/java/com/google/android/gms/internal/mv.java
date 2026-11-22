package com.google.android.gms.internal;

import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.millennialmedia.android.MMException;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public interface mv {

    public static final class a extends qq<a> {
        public C0109a[] afu;

        /* JADX INFO: renamed from: com.google.android.gms.internal.mv$a$a, reason: collision with other inner class name */
        public static final class C0109a extends qq<C0109a> {
            private static volatile C0109a[] afv;
            public String afw;
            public String afx;
            public int viewId;

            public C0109a() {
                mY();
            }

            public static C0109a[] mX() {
                if (afv == null) {
                    synchronized (qu.azg) {
                        if (afv == null) {
                            afv = new C0109a[0];
                        }
                    }
                }
                return afv;
            }

            @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
            public void a(qp qpVar) throws IOException {
                if (!this.afw.equals("")) {
                    qpVar.b(1, this.afw);
                }
                if (!this.afx.equals("")) {
                    qpVar.b(2, this.afx);
                }
                if (this.viewId != 0) {
                    qpVar.t(3, this.viewId);
                }
                super.a(qpVar);
            }

            @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
            protected int c() {
                int iC = super.c();
                if (!this.afw.equals("")) {
                    iC += qp.j(1, this.afw);
                }
                if (!this.afx.equals("")) {
                    iC += qp.j(2, this.afx);
                }
                return this.viewId != 0 ? iC + qp.v(3, this.viewId) : iC;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof C0109a)) {
                    return false;
                }
                C0109a c0109a = (C0109a) o;
                if (this.afw == null) {
                    if (c0109a.afw != null) {
                        return false;
                    }
                } else if (!this.afw.equals(c0109a.afw)) {
                    return false;
                }
                if (this.afx == null) {
                    if (c0109a.afx != null) {
                        return false;
                    }
                } else if (!this.afx.equals(c0109a.afx)) {
                    return false;
                }
                if (this.viewId == c0109a.viewId) {
                    return a(c0109a);
                }
                return false;
            }

            public int hashCode() {
                return (((((((this.afw == null ? 0 : this.afw.hashCode()) + 527) * 31) + (this.afx != null ? this.afx.hashCode() : 0)) * 31) + this.viewId) * 31) + rQ();
            }

            public C0109a mY() {
                this.afw = "";
                this.afx = "";
                this.viewId = 0;
                this.ayW = null;
                this.azh = -1;
                return this;
            }

            @Override // com.google.android.gms.internal.qw
            /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
            public C0109a b(qo qoVar) throws IOException {
                while (true) {
                    int iRz = qoVar.rz();
                    switch (iRz) {
                        case 0:
                            break;
                        case 10:
                            this.afw = qoVar.readString();
                            break;
                        case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                            this.afx = qoVar.readString();
                            break;
                        case MMException.DISPLAY_AD_NOT_PERMITTED /* 24 */:
                            this.viewId = qoVar.rC();
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
            mW();
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        public void a(qp qpVar) throws IOException {
            if (this.afu != null && this.afu.length > 0) {
                for (int i = 0; i < this.afu.length; i++) {
                    C0109a c0109a = this.afu[i];
                    if (c0109a != null) {
                        qpVar.a(1, c0109a);
                    }
                }
            }
            super.a(qpVar);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        protected int c() {
            int iC = super.c();
            if (this.afu != null && this.afu.length > 0) {
                for (int i = 0; i < this.afu.length; i++) {
                    C0109a c0109a = this.afu[i];
                    if (c0109a != null) {
                        iC += qp.c(1, c0109a);
                    }
                }
            }
            return iC;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof a)) {
                return false;
            }
            a aVar = (a) o;
            if (qu.equals(this.afu, aVar.afu)) {
                return a(aVar);
            }
            return false;
        }

        public int hashCode() {
            return ((qu.hashCode(this.afu) + 527) * 31) + rQ();
        }

        public a mW() {
            this.afu = C0109a.mX();
            this.ayW = null;
            this.azh = -1;
            return this;
        }

        @Override // com.google.android.gms.internal.qw
        /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
        public a b(qo qoVar) throws IOException {
            while (true) {
                int iRz = qoVar.rz();
                switch (iRz) {
                    case 0:
                        break;
                    case 10:
                        int iB = qz.b(qoVar, 10);
                        int length = this.afu == null ? 0 : this.afu.length;
                        C0109a[] c0109aArr = new C0109a[iB + length];
                        if (length != 0) {
                            System.arraycopy(this.afu, 0, c0109aArr, 0, length);
                        }
                        while (length < c0109aArr.length - 1) {
                            c0109aArr[length] = new C0109a();
                            qoVar.a(c0109aArr[length]);
                            qoVar.rz();
                            length++;
                        }
                        c0109aArr[length] = new C0109a();
                        qoVar.a(c0109aArr[length]);
                        this.afu = c0109aArr;
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
        private static volatile b[] afy;
        public d afz;
        public String name;

        public b() {
            na();
        }

        public static b[] mZ() {
            if (afy == null) {
                synchronized (qu.azg) {
                    if (afy == null) {
                        afy = new b[0];
                    }
                }
            }
            return afy;
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        public void a(qp qpVar) throws IOException {
            if (!this.name.equals("")) {
                qpVar.b(1, this.name);
            }
            if (this.afz != null) {
                qpVar.a(2, this.afz);
            }
            super.a(qpVar);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        protected int c() {
            int iC = super.c();
            if (!this.name.equals("")) {
                iC += qp.j(1, this.name);
            }
            return this.afz != null ? iC + qp.c(2, this.afz) : iC;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof b)) {
                return false;
            }
            b bVar = (b) o;
            if (this.name == null) {
                if (bVar.name != null) {
                    return false;
                }
            } else if (!this.name.equals(bVar.name)) {
                return false;
            }
            if (this.afz == null) {
                if (bVar.afz != null) {
                    return false;
                }
            } else if (!this.afz.equals(bVar.afz)) {
                return false;
            }
            return a(bVar);
        }

        public int hashCode() {
            return (((((this.name == null ? 0 : this.name.hashCode()) + 527) * 31) + (this.afz != null ? this.afz.hashCode() : 0)) * 31) + rQ();
        }

        public b na() {
            this.name = "";
            this.afz = null;
            this.ayW = null;
            this.azh = -1;
            return this;
        }

        @Override // com.google.android.gms.internal.qw
        /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
        public b b(qo qoVar) throws IOException {
            while (true) {
                int iRz = qoVar.rz();
                switch (iRz) {
                    case 0:
                        break;
                    case 10:
                        this.name = qoVar.readString();
                        break;
                    case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                        if (this.afz == null) {
                            this.afz = new d();
                        }
                        qoVar.a(this.afz);
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
        public b[] afA;
        public String type;

        public c() {
            nb();
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        public void a(qp qpVar) throws IOException {
            if (!this.type.equals("")) {
                qpVar.b(1, this.type);
            }
            if (this.afA != null && this.afA.length > 0) {
                for (int i = 0; i < this.afA.length; i++) {
                    b bVar = this.afA[i];
                    if (bVar != null) {
                        qpVar.a(2, bVar);
                    }
                }
            }
            super.a(qpVar);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        protected int c() {
            int iC = super.c();
            if (!this.type.equals("")) {
                iC += qp.j(1, this.type);
            }
            if (this.afA == null || this.afA.length <= 0) {
                return iC;
            }
            int iC2 = iC;
            for (int i = 0; i < this.afA.length; i++) {
                b bVar = this.afA[i];
                if (bVar != null) {
                    iC2 += qp.c(2, bVar);
                }
            }
            return iC2;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof c)) {
                return false;
            }
            c cVar = (c) o;
            if (this.type == null) {
                if (cVar.type != null) {
                    return false;
                }
            } else if (!this.type.equals(cVar.type)) {
                return false;
            }
            if (qu.equals(this.afA, cVar.afA)) {
                return a(cVar);
            }
            return false;
        }

        public int hashCode() {
            return (((((this.type == null ? 0 : this.type.hashCode()) + 527) * 31) + qu.hashCode(this.afA)) * 31) + rQ();
        }

        public c nb() {
            this.type = "";
            this.afA = b.mZ();
            this.ayW = null;
            this.azh = -1;
            return this;
        }

        @Override // com.google.android.gms.internal.qw
        /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
        public c b(qo qoVar) throws IOException {
            while (true) {
                int iRz = qoVar.rz();
                switch (iRz) {
                    case 0:
                        break;
                    case 10:
                        this.type = qoVar.readString();
                        break;
                    case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                        int iB = qz.b(qoVar, 18);
                        int length = this.afA == null ? 0 : this.afA.length;
                        b[] bVarArr = new b[iB + length];
                        if (length != 0) {
                            System.arraycopy(this.afA, 0, bVarArr, 0, length);
                        }
                        while (length < bVarArr.length - 1) {
                            bVarArr[length] = new b();
                            qoVar.a(bVarArr[length]);
                            qoVar.rz();
                            length++;
                        }
                        bVarArr[length] = new b();
                        qoVar.a(bVarArr[length]);
                        this.afA = bVarArr;
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

    public static final class d extends qq<d> {
        public String NJ;
        public boolean afB;
        public long afC;
        public double afD;
        public c afE;

        public d() {
            nc();
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        public void a(qp qpVar) throws IOException {
            if (this.afB) {
                qpVar.b(1, this.afB);
            }
            if (!this.NJ.equals("")) {
                qpVar.b(2, this.NJ);
            }
            if (this.afC != 0) {
                qpVar.b(3, this.afC);
            }
            if (Double.doubleToLongBits(this.afD) != Double.doubleToLongBits(0.0d)) {
                qpVar.a(4, this.afD);
            }
            if (this.afE != null) {
                qpVar.a(5, this.afE);
            }
            super.a(qpVar);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        protected int c() {
            int iC = super.c();
            if (this.afB) {
                iC += qp.c(1, this.afB);
            }
            if (!this.NJ.equals("")) {
                iC += qp.j(2, this.NJ);
            }
            if (this.afC != 0) {
                iC += qp.d(3, this.afC);
            }
            if (Double.doubleToLongBits(this.afD) != Double.doubleToLongBits(0.0d)) {
                iC += qp.b(4, this.afD);
            }
            return this.afE != null ? iC + qp.c(5, this.afE) : iC;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof d)) {
                return false;
            }
            d dVar = (d) o;
            if (this.afB != dVar.afB) {
                return false;
            }
            if (this.NJ == null) {
                if (dVar.NJ != null) {
                    return false;
                }
            } else if (!this.NJ.equals(dVar.NJ)) {
                return false;
            }
            if (this.afC != dVar.afC || Double.doubleToLongBits(this.afD) != Double.doubleToLongBits(dVar.afD)) {
                return false;
            }
            if (this.afE == null) {
                if (dVar.afE != null) {
                    return false;
                }
            } else if (!this.afE.equals(dVar.afE)) {
                return false;
            }
            return a(dVar);
        }

        public int hashCode() {
            int iHashCode = (((this.NJ == null ? 0 : this.NJ.hashCode()) + (((this.afB ? 1231 : 1237) + 527) * 31)) * 31) + ((int) (this.afC ^ (this.afC >>> 32)));
            long jDoubleToLongBits = Double.doubleToLongBits(this.afD);
            return (((((iHashCode * 31) + ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32)))) * 31) + (this.afE != null ? this.afE.hashCode() : 0)) * 31) + rQ();
        }

        public d nc() {
            this.afB = false;
            this.NJ = "";
            this.afC = 0L;
            this.afD = 0.0d;
            this.afE = null;
            this.ayW = null;
            this.azh = -1;
            return this;
        }

        @Override // com.google.android.gms.internal.qw
        /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
        public d b(qo qoVar) throws IOException {
            while (true) {
                int iRz = qoVar.rz();
                switch (iRz) {
                    case 0:
                        break;
                    case 8:
                        this.afB = qoVar.rD();
                        break;
                    case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                        this.NJ = qoVar.readString();
                        break;
                    case MMException.DISPLAY_AD_NOT_PERMITTED /* 24 */:
                        this.afC = qoVar.rB();
                        break;
                    case 33:
                        this.afD = qoVar.readDouble();
                        break;
                    case 42:
                        if (this.afE == null) {
                            this.afE = new c();
                        }
                        qoVar.a(this.afE);
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
}
