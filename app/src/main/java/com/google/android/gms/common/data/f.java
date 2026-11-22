package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class f implements Parcelable.Creator<DataHolder> {
    static void a(DataHolder dataHolder, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, dataHolder.gY(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, dataHolder.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable[]) dataHolder.gZ(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, dataHolder.getStatusCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, dataHolder.gV(), false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public DataHolder createFromParcel(Parcel parcel) {
        int iG = 0;
        Bundle bundleQ = null;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        CursorWindow[] cursorWindowArr = null;
        String[] strArrA = null;
        int iG3 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    strArrA = com.google.android.gms.common.internal.safeparcel.a.A(parcel, iF);
                    break;
                case 2:
                    cursorWindowArr = (CursorWindow[]) com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF, CursorWindow.CREATOR);
                    break;
                case 3:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 4:
                    bundleQ = com.google.android.gms.common.internal.safeparcel.a.q(parcel, iF);
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
        DataHolder dataHolder = new DataHolder(iG3, strArrA, cursorWindowArr, iG, bundleQ);
        dataHolder.gX();
        return dataHolder;
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: aw, reason: merged with bridge method [inline-methods] */
    public DataHolder[] newArray(int i) {
        return new DataHolder[i];
    }
}
