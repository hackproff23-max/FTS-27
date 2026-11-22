package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataSource;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class c implements Parcelable.Creator<DataSourcesResult> {
    static void a(DataSourcesResult dataSourcesResult, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, dataSourcesResult.getDataSources(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, dataSourcesResult.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) dataSourcesResult.getStatus(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cf, reason: merged with bridge method [inline-methods] */
    public DataSourcesResult createFromParcel(Parcel parcel) {
        Status status = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        ArrayList arrayListC = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, DataSource.CREATOR);
                    break;
                case 2:
                    status = (Status) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Status.CREATOR);
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
        return new DataSourcesResult(iG2, arrayListC, status);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dA, reason: merged with bridge method [inline-methods] */
    public DataSourcesResult[] newArray(int i) {
        return new DataSourcesResult[i];
    }
}
