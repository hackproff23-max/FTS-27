package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ao implements Parcelable.Creator<an> {
    static void a(an anVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, anVar.versionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, anVar.packageName, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, anVar.label, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, anVar.ayc);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: eA, reason: merged with bridge method [inline-methods] */
    public an createFromParcel(Parcel parcel) {
        String strO = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        long jI = 0;
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
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new an(iG2, strO2, strO, jI);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: gJ, reason: merged with bridge method [inline-methods] */
    public an[] newArray(int i) {
        return new an[i];
    }
}
