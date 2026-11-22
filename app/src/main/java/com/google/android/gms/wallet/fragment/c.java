package com.google.android.gms.wallet.fragment;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class c implements Parcelable.Creator<WalletFragmentStyle> {
    static void a(WalletFragmentStyle walletFragmentStyle, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, walletFragmentStyle.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, walletFragmentStyle.awy, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, walletFragmentStyle.awz);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: eb, reason: merged with bridge method [inline-methods] */
    public WalletFragmentStyle createFromParcel(Parcel parcel) {
        int iG = 0;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        Bundle bundleQ = null;
        int iG3 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    bundleQ = com.google.android.gms.common.internal.safeparcel.a.q(parcel, iF);
                    break;
                case 3:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new WalletFragmentStyle(iG3, bundleQ, iG);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: gj, reason: merged with bridge method [inline-methods] */
    public WalletFragmentStyle[] newArray(int i) {
        return new WalletFragmentStyle[i];
    }
}
