package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class i implements Parcelable.Creator<f> {
    static void a(f fVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, fVar.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, fVar.label, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, (Parcelable) fVar.awM, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, fVar.type, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, (Parcelable) fVar.avm, i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: eg, reason: merged with bridge method [inline-methods] */
    public f createFromParcel(Parcel parcel) {
        l lVar = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        String strO = null;
        g gVar = null;
        String strO2 = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 3:
                    gVar = (g) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, g.CREATOR);
                    break;
                case 4:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 5:
                    lVar = (l) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, l.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new f(iG2, strO2, gVar, strO, lVar);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: gp, reason: merged with bridge method [inline-methods] */
    public f[] newArray(int i) {
        return new f[i];
    }
}
