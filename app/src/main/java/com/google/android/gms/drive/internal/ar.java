package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;

/* JADX INFO: loaded from: classes.dex */
public class ar implements Parcelable.Creator<OnListEntriesResponse> {
    static void a(OnListEntriesResponse onListEntriesResponse, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, onListEntriesResponse.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) onListEntriesResponse.QQ, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, onListEntriesResponse.PJ);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: aw, reason: merged with bridge method [inline-methods] */
    public OnListEntriesResponse createFromParcel(Parcel parcel) {
        boolean zC;
        DataHolder dataHolder;
        int iG;
        boolean z = false;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        DataHolder dataHolder2 = null;
        int i = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    boolean z2 = z;
                    dataHolder = dataHolder2;
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    zC = z2;
                    break;
                case 2:
                    DataHolder dataHolder3 = (DataHolder) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DataHolder.CREATOR);
                    iG = i;
                    zC = z;
                    dataHolder = dataHolder3;
                    break;
                case 3:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    dataHolder = dataHolder2;
                    iG = i;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    zC = z;
                    dataHolder = dataHolder2;
                    iG = i;
                    break;
            }
            i = iG;
            dataHolder2 = dataHolder;
            z = zC;
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new OnListEntriesResponse(i, dataHolder2, z);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bL, reason: merged with bridge method [inline-methods] */
    public OnListEntriesResponse[] newArray(int i) {
        return new OnListEntriesResponse[i];
    }
}
