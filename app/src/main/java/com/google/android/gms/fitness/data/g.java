package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class g implements Parcelable.Creator<DataSource> {
    static void a(DataSource dataSource, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable) dataSource.getDataType(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, dataSource.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, dataSource.getName(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, dataSource.getType());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, (Parcelable) dataSource.getDevice(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, (Parcelable) dataSource.jx(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, dataSource.getStreamName(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, dataSource.jz());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bv, reason: merged with bridge method [inline-methods] */
    public DataSource createFromParcel(Parcel parcel) {
        boolean zC = false;
        String strO = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        a aVar = null;
        Device device = null;
        int iG2 = 0;
        String strO2 = null;
        DataType dataType = null;
        int iG3 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    dataType = (DataType) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DataType.CREATOR);
                    break;
                case 2:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 3:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 4:
                    device = (Device) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Device.CREATOR);
                    break;
                case 5:
                    aVar = (a) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, a.CREATOR);
                    break;
                case 6:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 7:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 1000:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new DataSource(iG3, dataType, strO2, iG2, device, aVar, strO, zC);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cO, reason: merged with bridge method [inline-methods] */
    public DataSource[] newArray(int i) {
        return new DataSource[i];
    }
}
