package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.millennialmedia.android.MMException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class fk implements Parcelable.Creator<fj> {
    static void a(fj fjVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, fjVar.versionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, fjVar.sg, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, fjVar.tU, false);
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 4, fjVar.qw, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 5, fjVar.errorCode);
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 6, fjVar.qx, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, fjVar.tV);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, fjVar.tW);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, fjVar.tX);
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 10, fjVar.tY, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, fjVar.qA);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 12, fjVar.orientation);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 13, fjVar.tZ, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 14, fjVar.ua);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 15, fjVar.ub, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 19, fjVar.ud, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 18, fjVar.uc);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 21, fjVar.ue, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 23, fjVar.ug);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 22, fjVar.uf);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 25, fjVar.uh);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 24, fjVar.tT);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public fj createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        String strO = null;
        String strO2 = null;
        ArrayList<String> arrayListC = null;
        int iG3 = 0;
        ArrayList<String> arrayListC2 = null;
        long jI = 0;
        boolean zC = false;
        long jI2 = 0;
        ArrayList<String> arrayListC3 = null;
        long jI3 = 0;
        int iG4 = 0;
        String strO3 = null;
        long jI4 = 0;
        String strO4 = null;
        boolean zC2 = false;
        String strO5 = null;
        String strO6 = null;
        boolean zC3 = false;
        boolean zC4 = false;
        boolean zC5 = false;
        boolean zC6 = false;
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
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 4:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.C(parcel, iF);
                    break;
                case 5:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 6:
                    arrayListC2 = com.google.android.gms.common.internal.safeparcel.a.C(parcel, iF);
                    break;
                case 7:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 8:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 9:
                    jI2 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 10:
                    arrayListC3 = com.google.android.gms.common.internal.safeparcel.a.C(parcel, iF);
                    break;
                case 11:
                    jI3 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 12:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 13:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 14:
                    jI4 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 15:
                    strO4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 16:
                case 17:
                case 20:
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
                case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                    zC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 19:
                    strO5 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case MMException.DISPLAY_AD_EXPIRED /* 21 */:
                    strO6 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case MMException.DISPLAY_AD_NOT_FOUND /* 22 */:
                    zC3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case MMException.DISPLAY_AD_ALREADY_DISPLAYED /* 23 */:
                    zC4 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case MMException.DISPLAY_AD_NOT_PERMITTED /* 24 */:
                    zC5 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case MMException.AD_BROKEN_REFERENCE /* 25 */:
                    zC6 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new fj(iG2, strO, strO2, arrayListC, iG3, arrayListC2, jI, zC, jI2, arrayListC3, jI3, iG4, strO3, jI4, strO4, zC2, strO5, strO6, zC3, zC4, zC5, zC6);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
    public fj[] newArray(int i) {
        return new fj[i];
    }
}
