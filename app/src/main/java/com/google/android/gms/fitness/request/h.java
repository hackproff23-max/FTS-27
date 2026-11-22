package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class h implements Parcelable.Creator<DataSourcesRequest> {
    static void a(DataSourcesRequest dataSourcesRequest, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, dataSourcesRequest.getDataTypes(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, dataSourcesRequest.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, dataSourcesRequest.jZ(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, dataSourcesRequest.jY());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bM, reason: merged with bridge method [inline-methods] */
    public DataSourcesRequest createFromParcel(Parcel parcel) {
        ArrayList<Integer> arrayListB = null;
        boolean zC = false;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        ArrayList arrayListC = null;
        int iG2 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, DataType.CREATOR);
                    break;
                case 2:
                    arrayListB = com.google.android.gms.common.internal.safeparcel.a.B(parcel, iF);
                    break;
                case 3:
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
        return new DataSourcesRequest(iG2, arrayListC, arrayListB, zC);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dg, reason: merged with bridge method [inline-methods] */
    public DataSourcesRequest[] newArray(int i) {
        return new DataSourcesRequest[i];
    }
}
