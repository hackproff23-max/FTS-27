package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class b implements Parcelable.Creator<DataReadResult> {
    static void a(DataReadResult dataReadResult, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.d(parcel, 1, dataReadResult.kt(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, dataReadResult.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) dataReadResult.getStatus(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.d(parcel, 3, dataReadResult.ks(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 5, dataReadResult.kr());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 6, dataReadResult.jw(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 7, dataReadResult.ku(), false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: ce, reason: merged with bridge method [inline-methods] */
    public DataReadResult createFromParcel(Parcel parcel) {
        int iG = 0;
        ArrayList arrayListC = null;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayListC2 = null;
        Status status = null;
        int iG3 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, arrayList, getClass().getClassLoader());
                    break;
                case 2:
                    status = (Status) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Status.CREATOR);
                    break;
                case 3:
                    com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, arrayList2, getClass().getClassLoader());
                    break;
                case 5:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 6:
                    arrayListC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, DataSource.CREATOR);
                    break;
                case 7:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, DataType.CREATOR);
                    break;
                case 1000:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new DataReadResult(iG3, arrayList, status, arrayList2, iG, arrayListC2, arrayListC);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dz, reason: merged with bridge method [inline-methods] */
    public DataReadResult[] newArray(int i) {
        return new DataReadResult[i];
    }
}
