package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class fi implements Parcelable.Creator<fh> {
    static void a(fh fhVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, fhVar.versionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, fhVar.tK, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, (Parcelable) fhVar.tL, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, (Parcelable) fhVar.lS, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, fhVar.lL, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, (Parcelable) fhVar.applicationInfo, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, (Parcelable) fhVar.tM, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, fhVar.tN, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, fhVar.tO, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, fhVar.tP, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, (Parcelable) fhVar.lO, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 12, fhVar.tQ, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 13, fhVar.tR);
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 14, fhVar.mc, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 15, fhVar.tS, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 16, fhVar.tT);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public fh createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        Bundle bundleQ = null;
        av avVar = null;
        ay ayVar = null;
        String strO = null;
        ApplicationInfo applicationInfo = null;
        PackageInfo packageInfo = null;
        String strO2 = null;
        String strO3 = null;
        String strO4 = null;
        gs gsVar = null;
        Bundle bundleQ2 = null;
        int iG3 = 0;
        ArrayList<String> arrayListC = null;
        Bundle bundleQ3 = null;
        boolean zC = false;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    bundleQ = com.google.android.gms.common.internal.safeparcel.a.q(parcel, iF);
                    break;
                case 3:
                    avVar = (av) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, av.CREATOR);
                    break;
                case 4:
                    ayVar = (ay) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, ay.CREATOR);
                    break;
                case 5:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 6:
                    applicationInfo = (ApplicationInfo) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, ApplicationInfo.CREATOR);
                    break;
                case 7:
                    packageInfo = (PackageInfo) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, PackageInfo.CREATOR);
                    break;
                case 8:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 9:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 10:
                    strO4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 11:
                    gsVar = (gs) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, gs.CREATOR);
                    break;
                case 12:
                    bundleQ2 = com.google.android.gms.common.internal.safeparcel.a.q(parcel, iF);
                    break;
                case 13:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 14:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.C(parcel, iF);
                    break;
                case 15:
                    bundleQ3 = com.google.android.gms.common.internal.safeparcel.a.q(parcel, iF);
                    break;
                case 16:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new fh(iG2, bundleQ, avVar, ayVar, strO, applicationInfo, packageInfo, strO2, strO3, strO4, gsVar, bundleQ2, iG3, arrayListC, bundleQ3, zC);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public fh[] newArray(int i) {
        return new fh[i];
    }
}
