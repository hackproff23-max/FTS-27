package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class f implements Parcelable.Creator<DataSet> {
    static void a(DataSet dataSet, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable) dataSet.getDataSource(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, dataSet.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) dataSet.getDataType(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.d(parcel, 3, dataSet.jv(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, dataSet.jw(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, dataSet.jn());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bu, reason: merged with bridge method [inline-methods] */
    public DataSet createFromParcel(Parcel parcel) {
        boolean zC = false;
        ArrayList arrayListC = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        ArrayList arrayList = new ArrayList();
        DataType dataType = null;
        DataSource dataSource = null;
        int iG2 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    dataSource = (DataSource) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DataSource.CREATOR);
                    break;
                case 2:
                    dataType = (DataType) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DataType.CREATOR);
                    break;
                case 3:
                    com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, arrayList, getClass().getClassLoader());
                    break;
                case 4:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, DataSource.CREATOR);
                    break;
                case 5:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
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
        return new DataSet(iG2, dataSource, dataType, arrayList, arrayListC, zC);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cN, reason: merged with bridge method [inline-methods] */
    public DataSet[] newArray(int i) {
        return new DataSet[i];
    }
}
