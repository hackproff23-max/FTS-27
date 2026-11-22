package com.google.android.gms.identity.intents.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class b implements Parcelable.Creator<UserAddress> {
    static void a(UserAddress userAddress, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, userAddress.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, userAddress.name, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, userAddress.afK, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, userAddress.afL, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, userAddress.afM, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, userAddress.afN, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, userAddress.afO, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, userAddress.afP, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, userAddress.afQ, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, userAddress.vk, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, userAddress.afR, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 12, userAddress.afS, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 13, userAddress.afT, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 14, userAddress.afU);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 15, userAddress.afV, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 16, userAddress.afW, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cH, reason: merged with bridge method [inline-methods] */
    public UserAddress createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        String strO = null;
        String strO2 = null;
        String strO3 = null;
        String strO4 = null;
        String strO5 = null;
        String strO6 = null;
        String strO7 = null;
        String strO8 = null;
        String strO9 = null;
        String strO10 = null;
        String strO11 = null;
        String strO12 = null;
        boolean zC = false;
        String strO13 = null;
        String strO14 = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 3:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 4:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 5:
                    strO4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 6:
                    strO5 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 7:
                    strO6 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 8:
                    strO7 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 9:
                    strO8 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 10:
                    strO9 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 11:
                    strO10 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 12:
                    strO11 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 13:
                    strO12 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 14:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 15:
                    strO13 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 16:
                    strO14 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new UserAddress(iG2, strO, strO2, strO3, strO4, strO5, strO6, strO7, strO8, strO9, strO10, strO11, strO12, zC, strO13, strO14);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: eu, reason: merged with bridge method [inline-methods] */
    public UserAddress[] newArray(int i) {
        return new UserAddress[i];
    }
}
