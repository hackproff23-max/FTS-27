package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ot implements Parcelable.Creator<or> {
    static void a(or orVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, orVar.versionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, orVar.amW);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, orVar.tag, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, orVar.amX, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, orVar.amY, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dt, reason: merged with bridge method [inline-methods] */
    public or createFromParcel(Parcel parcel) {
        Bundle bundleQ = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        long jI = 0;
        byte[] bArrR = null;
        String strO = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 3:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 4:
                    bArrR = com.google.android.gms.common.internal.safeparcel.a.r(parcel, iF);
                    break;
                case 5:
                    bundleQ = com.google.android.gms.common.internal.safeparcel.a.q(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new or(iG2, jI, strO, bArrR, bundleQ);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: fq, reason: merged with bridge method [inline-methods] */
    public or[] newArray(int i) {
        return new or[i];
    }
}
