package com.google.android.gms.plus.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class i implements Parcelable.Creator<h> {
    static void a(h hVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, hVar.getAccountName(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, hVar.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, hVar.oz(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, hVar.oA(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, hVar.oB(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, hVar.oC(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, hVar.oD(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, hVar.oE(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, hVar.oF(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, (Parcelable) hVar.oG(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dw, reason: merged with bridge method [inline-methods] */
    public h createFromParcel(Parcel parcel) {
        PlusCommonExtras plusCommonExtras = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        String strO = null;
        String strO2 = null;
        String strO3 = null;
        String strO4 = null;
        String[] strArrA = null;
        String[] strArrA2 = null;
        String[] strArrA3 = null;
        String strO5 = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    strO5 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 2:
                    strArrA3 = com.google.android.gms.common.internal.safeparcel.a.A(parcel, iF);
                    break;
                case 3:
                    strArrA2 = com.google.android.gms.common.internal.safeparcel.a.A(parcel, iF);
                    break;
                case 4:
                    strArrA = com.google.android.gms.common.internal.safeparcel.a.A(parcel, iF);
                    break;
                case 5:
                    strO4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 6:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 7:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 8:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 9:
                    plusCommonExtras = (PlusCommonExtras) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, PlusCommonExtras.CREATOR);
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
        return new h(iG2, strO5, strArrA3, strArrA2, strArrA, strO4, strO3, strO2, strO, plusCommonExtras);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: fu, reason: merged with bridge method [inline-methods] */
    public h[] newArray(int i) {
        return new h[i];
    }
}
