package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class dq implements Parcelable.Creator<dr> {
    static void a(dr drVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, drVar.versionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) drVar.sb, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, drVar.ck(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, drVar.cl(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, drVar.cm(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, drVar.cn(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, drVar.sg, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, drVar.sh);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, drVar.si, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, drVar.cp(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 11, drVar.orientation);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 12, drVar.sk);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 13, drVar.rH, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 14, (Parcelable) drVar.lO, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 15, drVar.co(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 17, (Parcelable) drVar.sn, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 16, drVar.sm, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public dr createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        Cdo cdo = null;
        IBinder iBinderP = null;
        IBinder iBinderP2 = null;
        IBinder iBinderP3 = null;
        IBinder iBinderP4 = null;
        String strO = null;
        boolean zC = false;
        String strO2 = null;
        IBinder iBinderP5 = null;
        int iG3 = 0;
        int iG4 = 0;
        String strO3 = null;
        gs gsVar = null;
        IBinder iBinderP6 = null;
        String strO4 = null;
        y yVar = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    cdo = (Cdo) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Cdo.CREATOR);
                    break;
                case 3:
                    iBinderP = com.google.android.gms.common.internal.safeparcel.a.p(parcel, iF);
                    break;
                case 4:
                    iBinderP2 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, iF);
                    break;
                case 5:
                    iBinderP3 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, iF);
                    break;
                case 6:
                    iBinderP4 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, iF);
                    break;
                case 7:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 8:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 9:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 10:
                    iBinderP5 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, iF);
                    break;
                case 11:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 12:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 13:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 14:
                    gsVar = (gs) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, gs.CREATOR);
                    break;
                case 15:
                    iBinderP6 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, iF);
                    break;
                case 16:
                    strO4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 17:
                    yVar = (y) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, y.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new dr(iG2, cdo, iBinderP, iBinderP2, iBinderP3, iBinderP4, strO, zC, strO2, iBinderP5, iG3, iG4, strO3, gsVar, iBinderP6, strO4, yVar);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public dr[] newArray(int i) {
        return new dr[i];
    }
}
