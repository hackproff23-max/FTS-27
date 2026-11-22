package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class l implements Parcelable.Creator<MaskedWalletRequest> {
    static void a(MaskedWalletRequest maskedWalletRequest, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, maskedWalletRequest.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, maskedWalletRequest.auM, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, maskedWalletRequest.avz);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, maskedWalletRequest.avA);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, maskedWalletRequest.avB);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, maskedWalletRequest.avC, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, maskedWalletRequest.auG, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, maskedWalletRequest.avD, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, (Parcelable) maskedWalletRequest.auV, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, maskedWalletRequest.avE);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, maskedWalletRequest.avF);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 12, (Parcelable[]) maskedWalletRequest.avG, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 13, maskedWalletRequest.avH);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 14, maskedWalletRequest.avI);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 15, maskedWalletRequest.avJ, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dT, reason: merged with bridge method [inline-methods] */
    public MaskedWalletRequest createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        String strO = null;
        boolean zC = false;
        boolean zC2 = false;
        boolean zC3 = false;
        String strO2 = null;
        String strO3 = null;
        String strO4 = null;
        Cart cart = null;
        boolean zC4 = false;
        boolean zC5 = false;
        CountrySpecification[] countrySpecificationArr = null;
        boolean zC6 = true;
        boolean zC7 = true;
        ArrayList arrayListC = null;
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
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 4:
                    zC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 5:
                    zC3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 6:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 7:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 8:
                    strO4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 9:
                    cart = (Cart) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Cart.CREATOR);
                    break;
                case 10:
                    zC4 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 11:
                    zC5 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 12:
                    countrySpecificationArr = (CountrySpecification[]) com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF, CountrySpecification.CREATOR);
                    break;
                case 13:
                    zC6 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 14:
                    zC7 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 15:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, com.google.android.gms.identity.intents.model.CountrySpecification.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new MaskedWalletRequest(iG2, strO, zC, zC2, zC3, strO2, strO3, strO4, cart, zC4, zC5, countrySpecificationArr, zC6, zC7, arrayListC);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: ga, reason: merged with bridge method [inline-methods] */
    public MaskedWalletRequest[] newArray(int i) {
        return new MaskedWalletRequest[i];
    }
}
