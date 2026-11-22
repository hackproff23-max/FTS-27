package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ku implements Parcelable.Creator<kv.b> {
    static void a(kv.b bVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, bVar.versionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, bVar.fv, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, (Parcelable) bVar.NZ, i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: N, reason: merged with bridge method [inline-methods] */
    public kv.b createFromParcel(Parcel parcel) {
        kr.a aVar = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        String strO = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 3:
                    aVar = (kr.a) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, kr.a.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new kv.b(iG2, strO, aVar);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: aO, reason: merged with bridge method [inline-methods] */
    public kv.b[] newArray(int i) {
        return new kv.b[i];
    }
}
