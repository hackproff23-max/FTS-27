package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class az implements Parcelable.Creator<ay> {
    static void a(ay ayVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, ayVar.versionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, ayVar.op, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, ayVar.height);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, ayVar.heightPixels);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, ayVar.oq);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 6, ayVar.width);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 7, ayVar.widthPixels);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, (Parcelable[]) ayVar.or, i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ay createFromParcel(Parcel parcel) {
        ay[] ayVarArr = null;
        int iG = 0;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG3 = 0;
        boolean zC = false;
        int iG4 = 0;
        int iG5 = 0;
        String strO = null;
        int iG6 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG6 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 3:
                    iG5 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 4:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 5:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 6:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 7:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 8:
                    ayVarArr = (ay[]) com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF, ay.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new ay(iG6, strO, iG5, iG4, zC, iG3, iG, ayVarArr);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public ay[] newArray(int i) {
        return new ay[i];
    }
}
