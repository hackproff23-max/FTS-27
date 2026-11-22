package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class hs implements Parcelable.Creator<hr> {
    static void a(hr hrVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable) hrVar.Dw, i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, hrVar.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, hrVar.Dx);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, hrVar.Dy);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, hrVar.pc, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, (Parcelable) hrVar.Dz, i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: S, reason: merged with bridge method [inline-methods] */
    public hr[] newArray(int i) {
        return new hr[i];
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
    public hr createFromParcel(Parcel parcel) {
        int iG = 0;
        hd hdVar = null;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        long jI = 0;
        String strO = null;
        hf hfVar = null;
        int iG3 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    hfVar = (hf) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, hf.CREATOR);
                    break;
                case 2:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 3:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 4:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 5:
                    hdVar = (hd) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, hd.CREATOR);
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
        return new hr(iG3, hfVar, jI, iG, strO, hdVar);
    }
}
