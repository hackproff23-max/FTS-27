package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class h implements Parcelable.Creator<g> {
    static void a(g gVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, gVar.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, gVar.awN);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, gVar.awO, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, gVar.awP);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, gVar.awQ, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, gVar.awR);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 7, gVar.awS);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: ef, reason: merged with bridge method [inline-methods] */
    public g createFromParcel(Parcel parcel) {
        String strO = null;
        int iG = 0;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        double dM = 0.0d;
        long jI = 0;
        int iG3 = -1;
        String strO2 = null;
        int iG4 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 3:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 4:
                    dM = com.google.android.gms.common.internal.safeparcel.a.m(parcel, iF);
                    break;
                case 5:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 6:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 7:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new g(iG4, iG, strO2, dM, strO, jI, iG3);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: go, reason: merged with bridge method [inline-methods] */
    public g[] newArray(int i) {
        return new g[i];
    }
}
