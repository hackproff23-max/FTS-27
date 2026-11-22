package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class hq implements Parcelable.Creator<hp> {
    static void a(hp hpVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, hpVar.name, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, hpVar.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, hpVar.Dh, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, hpVar.Di);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, hpVar.weight);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, hpVar.Dj);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, hpVar.Dk, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, (Parcelable[]) hpVar.Dl, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, hpVar.Dm, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, hpVar.Dn, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: R, reason: merged with bridge method [inline-methods] */
    public hp[] newArray(int i) {
        return new hp[i];
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
    public hp createFromParcel(Parcel parcel) {
        boolean zC = false;
        String strO = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 1;
        int[] iArrU = null;
        hj[] hjVarArr = null;
        String strO2 = null;
        boolean zC2 = false;
        String strO3 = null;
        String strO4 = null;
        int iG3 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    strO4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 2:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 3:
                    zC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 4:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 5:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 6:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 7:
                    hjVarArr = (hj[]) com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF, hj.CREATOR);
                    break;
                case 8:
                    iArrU = com.google.android.gms.common.internal.safeparcel.a.u(parcel, iF);
                    break;
                case 11:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 1000:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new hp(iG3, strO4, strO3, zC2, iG2, zC, strO2, hjVarArr, iArrU, strO);
    }
}
