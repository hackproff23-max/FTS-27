package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class bk implements Parcelable.Creator<bj> {
    static void a(bj bjVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, bjVar.versionCode);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, bjVar.oQ);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, bjVar.backgroundColor);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, bjVar.oR);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 5, bjVar.oS);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 6, bjVar.oT);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 7, bjVar.oU);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 8, bjVar.oV);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 9, bjVar.oW);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, bjVar.oX, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 11, bjVar.oY);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 12, bjVar.oZ, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 13, bjVar.pa);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 14, bjVar.pb);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 15, bjVar.pc, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public bj createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        int iG3 = 0;
        int iG4 = 0;
        int iG5 = 0;
        int iG6 = 0;
        int iG7 = 0;
        int iG8 = 0;
        int iG9 = 0;
        int iG10 = 0;
        String strO = null;
        int iG11 = 0;
        String strO2 = null;
        int iG12 = 0;
        int iG13 = 0;
        String strO3 = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 3:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 4:
                    iG5 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 5:
                    iG6 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 6:
                    iG7 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 7:
                    iG8 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 8:
                    iG9 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 9:
                    iG10 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 10:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 11:
                    iG11 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 12:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 13:
                    iG12 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 14:
                    iG13 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 15:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new bj(iG2, iG3, iG4, iG5, iG6, iG7, iG8, iG9, iG10, strO, iG11, strO2, iG12, iG13, strO3);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public bj[] newArray(int i) {
        return new bj[i];
    }
}
