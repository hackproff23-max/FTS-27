package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class a implements Parcelable.Creator<Address> {
    static void a(Address address, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, address.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, address.name, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, address.afK, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, address.afL, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, address.afM, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, address.vk, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, address.auD, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, address.auE, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, address.afR, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, address.afT, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, address.afU);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 12, address.afV, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dJ, reason: merged with bridge method [inline-methods] */
    public Address createFromParcel(Parcel parcel) {
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
        boolean zC = false;
        String strO10 = null;
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
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 12:
                    strO10 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new Address(iG2, strO, strO2, strO3, strO4, strO5, strO6, strO7, strO8, strO9, zC, strO10);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: fQ, reason: merged with bridge method [inline-methods] */
    public Address[] newArray(int i) {
        return new Address[i];
    }
}
