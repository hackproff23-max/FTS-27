package com.google.android.gms.internal;

import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.millennialmedia.android.MMException;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public interface pu {

    public static final class a extends qq<a> {
        public long auB;
        public c.j auC;
        public c.f gs;

        public a() {
            rc();
        }

        public static a l(byte[] bArr) throws qv {
            return (a) qw.a(new a(), bArr);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        public void a(qp qpVar) throws IOException {
            qpVar.b(1, this.auB);
            if (this.gs != null) {
                qpVar.a(2, this.gs);
            }
            if (this.auC != null) {
                qpVar.a(3, this.auC);
            }
            super.a(qpVar);
        }

        @Override // com.google.android.gms.internal.qq, com.google.android.gms.internal.qw
        protected int c() {
            int iC = super.c() + qp.d(1, this.auB);
            if (this.gs != null) {
                iC += qp.c(2, this.gs);
            }
            return this.auC != null ? iC + qp.c(3, this.auC) : iC;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof a)) {
                return false;
            }
            a aVar = (a) o;
            if (this.auB != aVar.auB) {
                return false;
            }
            if (this.gs == null) {
                if (aVar.gs != null) {
                    return false;
                }
            } else if (!this.gs.equals(aVar.gs)) {
                return false;
            }
            if (this.auC == null) {
                if (aVar.auC != null) {
                    return false;
                }
            } else if (!this.auC.equals(aVar.auC)) {
                return false;
            }
            return a(aVar);
        }

        public int hashCode() {
            return (((((this.gs == null ? 0 : this.gs.hashCode()) + ((((int) (this.auB ^ (this.auB >>> 32))) + 527) * 31)) * 31) + (this.auC != null ? this.auC.hashCode() : 0)) * 31) + rQ();
        }

        public a rc() {
            this.auB = 0L;
            this.gs = null;
            this.auC = null;
            this.ayW = null;
            this.azh = -1;
            return this;
        }

        @Override // com.google.android.gms.internal.qw
        /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
        public a b(qo qoVar) throws IOException {
            while (true) {
                int iRz = qoVar.rz();
                switch (iRz) {
                    case 0:
                        break;
                    case 8:
                        this.auB = qoVar.rB();
                        break;
                    case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                        if (this.gs == null) {
                            this.gs = new c.f();
                        }
                        qoVar.a(this.gs);
                        break;
                    case MMException.AD_NO_ACTIVITY /* 26 */:
                        if (this.auC == null) {
                            this.auC = new c.j();
                        }
                        qoVar.a(this.auC);
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
