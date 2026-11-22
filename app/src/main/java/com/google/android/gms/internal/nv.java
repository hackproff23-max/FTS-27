package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class nv implements Parcelable.Creator<nu> {
    static void a(nu nuVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, nuVar.ahn, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, nuVar.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, nuVar.nu(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, nuVar.nv());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, nuVar.aht, false);
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 6, nuVar.ahu, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cQ, reason: merged with bridge method [inline-methods] */
    public nu createFromParcel(Parcel parcel) {
        boolean zC = false;
        ArrayList<String> arrayListC = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        ArrayList arrayListC2 = null;
        String strO = null;
        ArrayList arrayListC3 = null;
        int iG2 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    arrayListC3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, oa.CREATOR);
                    break;
                case 2:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 3:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 4:
                    arrayListC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, oe.CREATOR);
                    break;
                case 6:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.C(parcel, iF);
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
        return new nu(iG2, arrayListC3, strO, zC, arrayListC2, arrayListC);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: eM, reason: merged with bridge method [inline-methods] */
    public nu[] newArray(int i) {
        return new nu[i];
    }
}
