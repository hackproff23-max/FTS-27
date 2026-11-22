package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class kt implements Parcelable.Creator<kr.a> {
    static void a(kr.a aVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, aVar.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, aVar.hI());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, aVar.hO());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, aVar.hJ());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, aVar.hP());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, aVar.hQ(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 7, aVar.hR());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, aVar.hT(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, (Parcelable) aVar.hV(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: M, reason: merged with bridge method [inline-methods] */
    public kr.a createFromParcel(Parcel parcel) {
        km kmVar = null;
        int iG = 0;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        String strO = null;
        String strO2 = null;
        boolean zC = false;
        int iG3 = 0;
        boolean zC2 = false;
        int iG4 = 0;
        int iG5 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG5 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 3:
                    zC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 4:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 5:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 6:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 7:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 8:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 9:
                    kmVar = (km) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, km.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new kr.a(iG5, iG4, zC2, iG3, zC, strO2, iG, strO, kmVar);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: aN, reason: merged with bridge method [inline-methods] */
    public kr.a[] newArray(int i) {
        return new kr.a[i];
    }
}
