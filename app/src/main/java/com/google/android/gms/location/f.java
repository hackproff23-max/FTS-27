package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class f implements Parcelable.Creator<e> {
    static void a(e eVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, eVar.agw);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, eVar.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, eVar.agx);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, eVar.agy);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cK, reason: merged with bridge method [inline-methods] */
    public e createFromParcel(Parcel parcel) {
        int iG = 1;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG3 = 0;
        long jI = 0;
        int iG4 = 1;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 3:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
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
        return new e(iG3, iG4, iG, jI);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: eE, reason: merged with bridge method [inline-methods] */
    public e[] newArray(int i) {
        return new e[i];
    }
}
