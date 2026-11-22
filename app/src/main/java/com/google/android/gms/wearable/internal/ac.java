package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ac implements Parcelable.Creator<ab> {
    static void a(ab abVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, abVar.versionCode);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, abVar.statusCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, (Parcelable) abVar.axO, i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: ex, reason: merged with bridge method [inline-methods] */
    public ab createFromParcel(Parcel parcel) {
        int iG = 0;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        al alVar = null;
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
                    alVar = (al) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, al.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new ab(iG3, iG, alVar);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: gG, reason: merged with bridge method [inline-methods] */
    public ab[] newArray(int i) {
        return new ab[i];
    }
}
