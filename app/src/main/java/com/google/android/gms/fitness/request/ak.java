package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;

/* JADX INFO: loaded from: classes.dex */
public class ak implements Parcelable.Creator<aj> {
    static void a(aj ajVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable) ajVar.getDataType(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, ajVar.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) ajVar.getDataSource(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cc, reason: merged with bridge method [inline-methods] */
    public aj createFromParcel(Parcel parcel) {
        DataSource dataSource;
        DataType dataType;
        int iG;
        DataSource dataSource2 = null;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int i = 0;
        DataType dataType2 = null;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    DataType dataType3 = (DataType) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DataType.CREATOR);
                    iG = i;
                    dataSource = dataSource2;
                    dataType = dataType3;
                    break;
                case 2:
                    dataSource = (DataSource) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DataSource.CREATOR);
                    dataType = dataType2;
                    iG = i;
                    break;
                case 1000:
                    DataSource dataSource3 = dataSource2;
                    dataType = dataType2;
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    dataSource = dataSource3;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    dataSource = dataSource2;
                    dataType = dataType2;
                    iG = i;
                    break;
            }
            i = iG;
            dataType2 = dataType;
            dataSource2 = dataSource;
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new aj(i, dataType2, dataSource2);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dx, reason: merged with bridge method [inline-methods] */
    public aj[] newArray(int i) {
        return new aj[i];
    }
}
