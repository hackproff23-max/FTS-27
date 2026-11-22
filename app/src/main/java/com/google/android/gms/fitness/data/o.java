package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class o implements Parcelable.Creator<RawDataSet> {
    static void a(RawDataSet rawDataSet, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, rawDataSet.UJ);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, rawDataSet.CK);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, rawDataSet.UL);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, rawDataSet.UM, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, rawDataSet.Uc);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bB, reason: merged with bridge method [inline-methods] */
    public RawDataSet createFromParcel(Parcel parcel) {
        boolean zC = false;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        ArrayList arrayListC = null;
        int iG2 = 0;
        int iG3 = 0;
        int iG4 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 3:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, RawDataPoint.CREATOR);
                    break;
                case 4:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 1000:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new RawDataSet(iG4, iG3, iG2, arrayListC, zC);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cU, reason: merged with bridge method [inline-methods] */
    public RawDataSet[] newArray(int i) {
        return new RawDataSet[i];
    }
}
