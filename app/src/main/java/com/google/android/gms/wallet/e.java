package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class e implements Parcelable.Creator<d> {
    static void a(d dVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, dVar.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) dVar.auJ, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, (Parcelable) dVar.auK, i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dM, reason: merged with bridge method [inline-methods] */
    public d createFromParcel(Parcel parcel) {
        OfferWalletObject offerWalletObject;
        LoyaltyWalletObject loyaltyWalletObject;
        int iG;
        OfferWalletObject offerWalletObject2 = null;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int i = 0;
        LoyaltyWalletObject loyaltyWalletObject2 = null;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    OfferWalletObject offerWalletObject3 = offerWalletObject2;
                    loyaltyWalletObject = loyaltyWalletObject2;
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    offerWalletObject = offerWalletObject3;
                    break;
                case 2:
                    LoyaltyWalletObject loyaltyWalletObject3 = (LoyaltyWalletObject) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, LoyaltyWalletObject.CREATOR);
                    iG = i;
                    offerWalletObject = offerWalletObject2;
                    loyaltyWalletObject = loyaltyWalletObject3;
                    break;
                case 3:
                    offerWalletObject = (OfferWalletObject) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, OfferWalletObject.CREATOR);
                    loyaltyWalletObject = loyaltyWalletObject2;
                    iG = i;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    offerWalletObject = offerWalletObject2;
                    loyaltyWalletObject = loyaltyWalletObject2;
                    iG = i;
                    break;
            }
            i = iG;
            loyaltyWalletObject2 = loyaltyWalletObject;
            offerWalletObject2 = offerWalletObject;
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new d(i, loyaltyWalletObject2, offerWalletObject2);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: fT, reason: merged with bridge method [inline-methods] */
    public d[] newArray(int i) {
        return new d[i];
    }
}
