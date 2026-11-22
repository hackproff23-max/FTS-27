package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class nz implements Parcelable.Creator<ny> {
    static void a(ny nyVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, nyVar.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) nyVar.nt(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, nyVar.getInterval());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, nyVar.getPriority());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cS, reason: merged with bridge method [inline-methods] */
    public ny createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        nu nuVar = null;
        long jI = ny.ahz;
        int iG3 = 102;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 2:
                    nuVar = (nu) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, nu.CREATOR);
                    break;
                case 3:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 4:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 1000:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new ny(iG2, nuVar, jI, iG3);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: eO, reason: merged with bridge method [inline-methods] */
    public ny[] newArray(int i) {
        return new ny[i];
    }
}
