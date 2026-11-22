package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class e implements Parcelable.Creator<DataPoint> {
    static void a(DataPoint dataPoint, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable) dataPoint.getDataSource(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, dataPoint.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, dataPoint.getTimestampNanos());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, dataPoint.ju());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, (Parcelable[]) dataPoint.jr(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, (Parcelable) dataPoint.getOriginalDataSource(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, dataPoint.js());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, dataPoint.jt());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bt, reason: merged with bridge method [inline-methods] */
    public DataPoint createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        DataSource dataSource = null;
        long jI = 0;
        long jI2 = 0;
        Value[] valueArr = null;
        DataSource dataSource2 = null;
        long jI3 = 0;
        long jI4 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    dataSource = (DataSource) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DataSource.CREATOR);
                    break;
                case 3:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 4:
                    jI2 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 5:
                    valueArr = (Value[]) com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF, Value.CREATOR);
                    break;
                case 6:
                    dataSource2 = (DataSource) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DataSource.CREATOR);
                    break;
                case 7:
                    jI3 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 8:
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
        return new DataPoint(iG2, dataSource, jI, jI2, valueArr, dataSource2, jI3, jI4);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cM, reason: merged with bridge method [inline-methods] */
    public DataPoint[] newArray(int i) {
        return new DataPoint[i];
    }
}
