package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class nt implements Parcelable.Creator<ns> {
    static void a(ns nsVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, nsVar.np());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, nsVar.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, nsVar.ns());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, (Parcelable) nsVar.nt(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cP, reason: merged with bridge method [inline-methods] */
    public ns createFromParcel(Parcel parcel) {
        int iG = 0;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG3 = -1;
        nu nuVar = null;
        int iG4 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 3:
                    nuVar = (nu) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, nu.CREATOR);
                    break;
                case 1000:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new ns(iG4, iG, iG3, nuVar);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: eL, reason: merged with bridge method [inline-methods] */
    public ns[] newArray(int i) {
        return new ns[i];
    }
}
