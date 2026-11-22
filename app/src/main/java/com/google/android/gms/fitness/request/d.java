package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class d implements Parcelable.Creator<DataDeleteRequest> {
    static void a(DataDeleteRequest dataDeleteRequest, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, dataDeleteRequest.jo());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, dataDeleteRequest.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, dataDeleteRequest.jp());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, dataDeleteRequest.getDataSources(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, dataDeleteRequest.getDataTypes(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 5, dataDeleteRequest.getSessions(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, dataDeleteRequest.jR());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, dataDeleteRequest.jS());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bJ, reason: merged with bridge method [inline-methods] */
    public DataDeleteRequest createFromParcel(Parcel parcel) {
        long jI = 0;
        ArrayList arrayListC = null;
        boolean zC = false;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        boolean zC2 = false;
        ArrayList arrayListC2 = null;
        ArrayList arrayListC3 = null;
        long jI2 = 0;
        int iG2 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    jI2 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 2:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 3:
                    arrayListC3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, DataSource.CREATOR);
                    break;
                case 4:
                    arrayListC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, DataType.CREATOR);
                    break;
                case 5:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, Session.CREATOR);
                    break;
                case 6:
                    zC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 7:
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
        return new DataDeleteRequest(iG2, jI2, jI, arrayListC3, arrayListC2, arrayListC, zC2, zC);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dd, reason: merged with bridge method [inline-methods] */
    public DataDeleteRequest[] newArray(int i) {
        return new DataDeleteRequest[i];
    }
}
