package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.StorageStats;

/* JADX INFO: loaded from: classes.dex */
public class aw implements Parcelable.Creator<OnStorageStatsResponse> {
    static void a(OnStorageStatsResponse onStorageStatsResponse, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, onStorageStatsResponse.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) onStorageStatsResponse.QS, i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: aB, reason: merged with bridge method [inline-methods] */
    public OnStorageStatsResponse createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        StorageStats storageStats = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    storageStats = (StorageStats) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, StorageStats.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new OnStorageStatsResponse(iG2, storageStats);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bQ, reason: merged with bridge method [inline-methods] */
    public OnStorageStatsResponse[] newArray(int i) {
        return new OnStorageStatsResponse[i];
    }
}
