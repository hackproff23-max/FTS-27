package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class pz implements Parcelable.Creator<py> {
    static void a(py pyVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, pyVar.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, pyVar.avY, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, pyVar.avZ, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dY, reason: merged with bridge method [inline-methods] */
    public py createFromParcel(Parcel parcel) {
        String[] strArrA = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        byte[][] bArrS = (byte[][]) null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    strArrA = com.google.android.gms.common.internal.safeparcel.a.A(parcel, iF);
                    break;
                case 3:
                    bArrS = com.google.android.gms.common.internal.safeparcel.a.s(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new py(iG2, strArrA, bArrS);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: gf, reason: merged with bridge method [inline-methods] */
    public py[] newArray(int i) {
        return new py[i];
    }
}
