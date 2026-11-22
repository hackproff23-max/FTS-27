package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.identity.intents.model.UserAddress;

/* JADX INFO: loaded from: classes.dex */
public class k implements Parcelable.Creator<MaskedWallet> {
    static void a(MaskedWallet maskedWallet, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, maskedWallet.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, maskedWallet.auL, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, maskedWallet.auM, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, maskedWallet.auR, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, maskedWallet.auO, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, (Parcelable) maskedWallet.auP, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, (Parcelable) maskedWallet.auQ, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, (Parcelable[]) maskedWallet.avw, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, (Parcelable[]) maskedWallet.avx, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, (Parcelable) maskedWallet.auS, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, (Parcelable) maskedWallet.auT, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 12, (Parcelable[]) maskedWallet.auU, i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dS, reason: merged with bridge method [inline-methods] */
    public MaskedWallet createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        String strO = null;
        String strO2 = null;
        String[] strArrA = null;
        String strO3 = null;
        Address address = null;
        Address address2 = null;
        LoyaltyWalletObject[] loyaltyWalletObjectArr = null;
        OfferWalletObject[] offerWalletObjectArr = null;
        UserAddress userAddress = null;
        UserAddress userAddress2 = null;
        InstrumentInfo[] instrumentInfoArr = null;
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
                    strArrA = com.google.android.gms.common.internal.safeparcel.a.A(parcel, iF);
                    break;
                case 5:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 6:
                    address = (Address) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Address.CREATOR);
                    break;
                case 7:
                    address2 = (Address) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Address.CREATOR);
                    break;
                case 8:
                    loyaltyWalletObjectArr = (LoyaltyWalletObject[]) com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF, LoyaltyWalletObject.CREATOR);
                    break;
                case 9:
                    offerWalletObjectArr = (OfferWalletObject[]) com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF, OfferWalletObject.CREATOR);
                    break;
                case 10:
                    userAddress = (UserAddress) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, UserAddress.CREATOR);
                    break;
                case 11:
                    userAddress2 = (UserAddress) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, UserAddress.CREATOR);
                    break;
                case 12:
                    instrumentInfoArr = (InstrumentInfo[]) com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF, InstrumentInfo.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new MaskedWallet(iG2, strO, strO2, strArrA, strO3, address, address2, loyaltyWalletObjectArr, offerWalletObjectArr, userAddress, userAddress2, instrumentInfoArr);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: fZ, reason: merged with bridge method [inline-methods] */
    public MaskedWallet[] newArray(int i) {
        return new MaskedWallet[i];
    }
}
