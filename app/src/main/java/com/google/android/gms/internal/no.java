package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class no implements Parcelable.Creator<nn> {
    static void a(nn nnVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, nnVar.getRequestId(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, nnVar.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, nnVar.getExpirationTime());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, nnVar.nn());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, nnVar.getLatitude());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, nnVar.getLongitude());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, nnVar.no());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 7, nnVar.np());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 8, nnVar.getNotificationResponsiveness());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 9, nnVar.nq());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cN, reason: merged with bridge method [inline-methods] */
    public nn createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        String strO = null;
        int iG3 = 0;
        short sF = 0;
        double dM = 0.0d;
        double dM2 = 0.0d;
        float fL = 0.0f;
        long jI = 0;
        int iG4 = 0;
        int iG5 = -1;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 2:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 3:
                    sF = com.google.android.gms.common.internal.safeparcel.a.f(parcel, iF);
                    break;
                case 4:
                    dM = com.google.android.gms.common.internal.safeparcel.a.m(parcel, iF);
                    break;
                case 5:
                    dM2 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, iF);
                    break;
                case 6:
                    fL = com.google.android.gms.common.internal.safeparcel.a.l(parcel, iF);
                    break;
                case 7:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 8:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 9:
                    iG5 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
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
        return new nn(iG2, strO, iG3, sF, dM, dM2, fL, jI, iG4, iG5);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: eJ, reason: merged with bridge method [inline-methods] */
    public nn[] newArray(int i) {
        return new nn[i];
    }
}
