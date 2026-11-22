package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class g implements Parcelable.Creator<DataReadRequest> {
    static void a(DataReadRequest dataReadRequest, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, dataReadRequest.getDataTypes(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, dataReadRequest.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, dataReadRequest.getDataSources(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, dataReadRequest.jo());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, dataReadRequest.jp());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 5, dataReadRequest.getAggregatedDataTypes(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 6, dataReadRequest.getAggregatedDataSources(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 7, dataReadRequest.getBucketType());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, dataReadRequest.jX());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, (Parcelable) dataReadRequest.getActivityDataSource(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 10, dataReadRequest.getLimit());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 12, dataReadRequest.jW());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 13, dataReadRequest.jV());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bL, reason: merged with bridge method [inline-methods] */
    public DataReadRequest createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        ArrayList arrayListC = null;
        ArrayList arrayListC2 = null;
        long jI = 0;
        long jI2 = 0;
        ArrayList arrayListC3 = null;
        ArrayList arrayListC4 = null;
        int iG3 = 0;
        long jI3 = 0;
        DataSource dataSource = null;
        int iG4 = 0;
        boolean zC = false;
        boolean zC2 = false;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, DataType.CREATOR);
                    break;
                case 2:
                    arrayListC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, DataSource.CREATOR);
                    break;
                case 3:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 4:
                    jI2 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 5:
                    arrayListC3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, DataType.CREATOR);
                    break;
                case 6:
                    arrayListC4 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, DataSource.CREATOR);
                    break;
                case 7:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 8:
                    jI3 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 9:
                    dataSource = (DataSource) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DataSource.CREATOR);
                    break;
                case 10:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 12:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 13:
                    zC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
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
        return new DataReadRequest(iG2, arrayListC, arrayListC2, jI, jI2, arrayListC3, arrayListC4, iG3, jI3, dataSource, iG4, zC, zC2);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: df, reason: merged with bridge method [inline-methods] */
    public DataReadRequest[] newArray(int i) {
        return new DataReadRequest[i];
    }
}
