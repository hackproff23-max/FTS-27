package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataType;

/* JADX INFO: loaded from: classes.dex */
public class d implements Parcelable.Creator<DataTypeResult> {
    static void a(DataTypeResult dataTypeResult, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable) dataTypeResult.getStatus(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, dataTypeResult.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, (Parcelable) dataTypeResult.getDataType(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cg, reason: merged with bridge method [inline-methods] */
    public DataTypeResult createFromParcel(Parcel parcel) {
        DataType dataType;
        Status status;
        int iG;
        DataType dataType2 = null;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int i = 0;
        Status status2 = null;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    Status status3 = (Status) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Status.CREATOR);
                    iG = i;
                    dataType = dataType2;
                    status = status3;
                    break;
                case 3:
                    dataType = (DataType) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DataType.CREATOR);
                    status = status2;
                    iG = i;
                    break;
                case 1000:
                    DataType dataType3 = dataType2;
                    status = status2;
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    dataType = dataType3;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    dataType = dataType2;
                    status = status2;
                    iG = i;
                    break;
            }
            i = iG;
            status2 = status;
            dataType2 = dataType;
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new DataTypeResult(i, status2, dataType2);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dB, reason: merged with bridge method [inline-methods] */
    public DataTypeResult[] newArray(int i) {
        return new DataTypeResult[i];
    }
}
