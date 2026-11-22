package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ig implements Parcelable.Creator<Cif> {
    static void a(Cif cif, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, cif.responseCode);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, cif.versionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, cif.Ep, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, cif.Eq, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: U, reason: merged with bridge method [inline-methods] */
    public Cif[] newArray(int i) {
        return new Cif[i];
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public Cif createFromParcel(Parcel parcel) {
        byte[] bArrR = null;
        int iG = 0;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        Bundle bundleQ = null;
        int iG3 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    bundleQ = com.google.android.gms.common.internal.safeparcel.a.q(parcel, iF);
                    break;
                case 3:
                    bArrR = com.google.android.gms.common.internal.safeparcel.a.r(parcel, iF);
                    break;
                case 1000:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new Cif(iG3, iG, bundleQ, bArrR);
    }
}
