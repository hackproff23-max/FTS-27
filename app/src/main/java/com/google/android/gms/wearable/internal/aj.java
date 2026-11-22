package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class aj implements Parcelable.Creator<ai> {
    static void a(ai aiVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, aiVar.CK);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, aiVar.getRequestId());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, aiVar.getPath(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, aiVar.getData(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, aiVar.getSourceNodeId(), false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: ey, reason: merged with bridge method [inline-methods] */
    public ai createFromParcel(Parcel parcel) {
        int iG = 0;
        String strO = null;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        byte[] bArrR = null;
        String strO2 = null;
        int iG3 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 3:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 4:
                    bArrR = com.google.android.gms.common.internal.safeparcel.a.r(parcel, iF);
                    break;
                case 5:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new ai(iG3, iG, strO2, bArrR, strO);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: gH, reason: merged with bridge method [inline-methods] */
    public ai[] newArray(int i) {
        return new ai[i];
    }
}
