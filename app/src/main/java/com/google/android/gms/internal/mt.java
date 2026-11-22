package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataSource;

/* JADX INFO: loaded from: classes.dex */
public class mt implements Parcelable.Creator<ms> {
    static void a(ms msVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable) msVar.getDataSource(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, msVar.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bH, reason: merged with bridge method [inline-methods] */
    public ms createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        DataSource dataSource = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    dataSource = (DataSource) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DataSource.CREATOR);
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
        return new ms(iG2, dataSource);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: db, reason: merged with bridge method [inline-methods] */
    public ms[] newArray(int i) {
        return new ms[i];
    }
}
