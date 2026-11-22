package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class g implements Parcelable.Creator<StorageStats> {
    static void a(StorageStats storageStats, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, storageStats.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, storageStats.OM);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, storageStats.ON);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, storageStats.OO);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, storageStats.OP);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 6, storageStats.OQ);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: V, reason: merged with bridge method [inline-methods] */
    public StorageStats createFromParcel(Parcel parcel) {
        int iG = 0;
        long jI = 0;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        long jI2 = 0;
        long jI3 = 0;
        long jI4 = 0;
        int iG3 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    jI4 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 3:
                    jI3 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 4:
                    jI2 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 5:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 6:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new StorageStats(iG3, jI4, jI3, jI2, jI, iG);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bd, reason: merged with bridge method [inline-methods] */
    public StorageStats[] newArray(int i) {
        return new StorageStats[i];
    }
}
