package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ax implements Parcelable.Creator<aw> {
    static void a(aw awVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, awVar.versionCode);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, awVar.statusCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, awVar.ayc);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, awVar.aye, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: eE, reason: merged with bridge method [inline-methods] */
    public aw createFromParcel(Parcel parcel) {
        int iG = 0;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        long jI = 0;
        ArrayList arrayListC = null;
        int iG3 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 3:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 4:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, an.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new aw(iG3, iG, jI, arrayListC);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: gO, reason: merged with bridge method [inline-methods] */
    public aw[] newArray(int i) {
        return new aw[i];
    }
}
