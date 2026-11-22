package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class q implements Parcelable.Creator<p> {
    static void a(p pVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, pVar.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, pVar.awT, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, pVar.tU, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, (Parcelable) pVar.awX, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, (Parcelable) pVar.awY, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, (Parcelable) pVar.awZ, i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: ek, reason: merged with bridge method [inline-methods] */
    public p createFromParcel(Parcel parcel) {
        n nVar = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        n nVar2 = null;
        l lVar = null;
        String strO = null;
        String strO2 = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 3:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 4:
                    lVar = (l) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, l.CREATOR);
                    break;
                case 5:
                    nVar2 = (n) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, n.CREATOR);
                    break;
                case 6:
                    nVar = (n) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, n.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new p(iG2, strO2, strO, lVar, nVar2, nVar);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: gt, reason: merged with bridge method [inline-methods] */
    public p[] newArray(int i) {
        return new p[i];
    }
}
