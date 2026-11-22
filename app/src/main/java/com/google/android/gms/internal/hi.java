package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class hi implements Parcelable.Creator<hh> {
    static void a(hh hhVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, hhVar.CX, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, hhVar.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, (Parcelable) hhVar.CY, i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, hhVar.CZ);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, hhVar.Da, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: L, reason: merged with bridge method [inline-methods] */
    public hh[] newArray(int i) {
        return new hh[i];
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public hh createFromParcel(Parcel parcel) {
        byte[] bArrR = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        int iG3 = -1;
        hp hpVar = null;
        String strO = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 3:
                    hpVar = (hp) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, hp.CREATOR);
                    break;
                case 4:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 5:
                    bArrR = com.google.android.gms.common.internal.safeparcel.a.r(parcel, iF);
                    break;
                case 1000:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new hh(iG2, strO, hpVar, iG3, bArrR);
    }
}
