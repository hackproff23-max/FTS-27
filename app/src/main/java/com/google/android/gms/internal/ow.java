package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ow implements Parcelable.Creator<ov> {
    static void a(ov ovVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, ovVar.versionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, ovVar.packageName, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, ovVar.ang);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, ovVar.anh);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, ovVar.ani, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, ovVar.anj, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, ovVar.ank);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, ovVar.anl, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: du, reason: merged with bridge method [inline-methods] */
    public ov createFromParcel(Parcel parcel) {
        int iG = 0;
        String strO = null;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        boolean zC = true;
        String strO2 = null;
        String strO3 = null;
        int iG3 = 0;
        String strO4 = null;
        int iG4 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    strO4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 3:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 4:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 5:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 6:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 7:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 8:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new ov(iG4, strO4, iG3, iG, strO3, strO2, zC, strO);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: fr, reason: merged with bridge method [inline-methods] */
    public ov[] newArray(int i) {
        return new ov[i];
    }
}
