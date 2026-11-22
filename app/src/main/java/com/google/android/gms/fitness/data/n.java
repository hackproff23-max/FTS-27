package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class n implements Parcelable.Creator<RawDataPoint> {
    static void a(RawDataPoint rawDataPoint, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, rawDataPoint.Ud);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, rawDataPoint.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, rawDataPoint.Ue);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, (Parcelable[]) rawDataPoint.Uf, i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, rawDataPoint.UJ);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 5, rawDataPoint.UK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, rawDataPoint.Uh);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, rawDataPoint.Ui);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bA, reason: merged with bridge method [inline-methods] */
    public RawDataPoint createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        long jI = 0;
        long jI2 = 0;
        Value[] valueArr = null;
        int iG3 = 0;
        int iG4 = 0;
        long jI3 = 0;
        long jI4 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 2:
                    jI2 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 3:
                    valueArr = (Value[]) com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF, Value.CREATOR);
                    break;
                case 4:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 5:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 6:
                    jI3 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 7:
                    jI4 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
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
        return new RawDataPoint(iG2, jI, jI2, valueArr, iG3, iG4, jI3, jI4);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cT, reason: merged with bridge method [inline-methods] */
    public RawDataPoint[] newArray(int i) {
        return new RawDataPoint[i];
    }
}
