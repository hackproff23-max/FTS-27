package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class u implements Parcelable.Creator<t> {
    static void a(t tVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, tVar.versionCode);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, tVar.statusCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, (Parcelable[]) tVar.axK, i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: et, reason: merged with bridge method [inline-methods] */
    public t createFromParcel(Parcel parcel) {
        int iG = 0;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        com.google.android.gms.wearable.c[] cVarArr = null;
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
                    cVarArr = (com.google.android.gms.wearable.c[]) com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF, com.google.android.gms.wearable.c.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new t(iG3, iG, cVarArr);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: gC, reason: merged with bridge method [inline-methods] */
    public t[] newArray(int i) {
        return new t[i];
    }
}
