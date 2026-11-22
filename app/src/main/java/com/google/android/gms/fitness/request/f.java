package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataSet;

/* JADX INFO: loaded from: classes.dex */
public class f implements Parcelable.Creator<e> {
    static void a(e eVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable) eVar.jH(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, eVar.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bK, reason: merged with bridge method [inline-methods] */
    public e createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        DataSet dataSet = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    dataSet = (DataSet) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DataSet.CREATOR);
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
        return new e(iG2, dataSet);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: de, reason: merged with bridge method [inline-methods] */
    public e[] newArray(int i) {
        return new e[i];
    }
}
