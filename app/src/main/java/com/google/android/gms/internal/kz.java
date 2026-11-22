package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class kz implements Parcelable.Creator<ky> {
    static void a(ky kyVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, kyVar.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, kyVar.ic(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, (Parcelable) kyVar.id(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
    public ky createFromParcel(Parcel parcel) {
        kv kvVar = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        Parcel parcelD = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    parcelD = com.google.android.gms.common.internal.safeparcel.a.D(parcel, iF);
                    break;
                case 3:
                    kvVar = (kv) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, kv.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new ky(iG2, parcelD, kvVar);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: aR, reason: merged with bridge method [inline-methods] */
    public ky[] newArray(int i) {
        return new ky[i];
    }
}
