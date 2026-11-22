package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.millennialmedia.android.MMException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class pj implements Parcelable.Creator<pi> {
    static void a(pi piVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        Set<Integer> set = piVar.aon;
        if (set.contains(1)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, piVar.CK);
        }
        if (set.contains(2)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, piVar.apl, true);
        }
        if (set.contains(3)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, (Parcelable) piVar.apm, i, true);
        }
        if (set.contains(4)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, piVar.apn, true);
        }
        if (set.contains(5)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, piVar.apo, true);
        }
        if (set.contains(6)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 6, piVar.app);
        }
        if (set.contains(7)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, (Parcelable) piVar.apq, i, true);
        }
        if (set.contains(8)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, piVar.apr, true);
        }
        if (set.contains(9)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, piVar.OS, true);
        }
        if (set.contains(12)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 12, piVar.ow);
        }
        if (set.contains(14)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 14, piVar.CE, true);
        }
        if (set.contains(15)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 15, (Parcelable) piVar.aps, i, true);
        }
        if (set.contains(16)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 16, piVar.apt);
        }
        if (set.contains(19)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 19, (Parcelable) piVar.apu, i, true);
        }
        if (set.contains(18)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 18, piVar.FR, true);
        }
        if (set.contains(21)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 21, piVar.apw);
        }
        if (set.contains(20)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 20, piVar.apv, true);
        }
        if (set.contains(23)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 23, piVar.apy, true);
        }
        if (set.contains(22)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 22, piVar.apx, true);
        }
        if (set.contains(25)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 25, piVar.apA);
        }
        if (set.contains(24)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 24, piVar.apz);
        }
        if (set.contains(27)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 27, piVar.vf, true);
        }
        if (set.contains(26)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 26, piVar.apB, true);
        }
        if (set.contains(29)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 29, piVar.apD);
        }
        if (set.contains(28)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 28, piVar.apC, true);
        }
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dz, reason: merged with bridge method [inline-methods] */
    public pi createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        HashSet hashSet = new HashSet();
        int iG2 = 0;
        String strO = null;
        pi.a aVar = null;
        String strO2 = null;
        String strO3 = null;
        int iG3 = 0;
        pi.b bVar = null;
        String strO4 = null;
        String strO5 = null;
        int iG4 = 0;
        String strO6 = null;
        pi.c cVar = null;
        boolean zC = false;
        String strO7 = null;
        pi.d dVar = null;
        String strO8 = null;
        int iG5 = 0;
        ArrayList arrayListC = null;
        ArrayList arrayListC2 = null;
        int iG6 = 0;
        int iG7 = 0;
        String strO9 = null;
        String strO10 = null;
        ArrayList arrayListC3 = null;
        boolean zC2 = false;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    hashSet.add(1);
                    break;
                case 2:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(2);
                    break;
                case 3:
                    pi.a aVar2 = (pi.a) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, pi.a.CREATOR);
                    hashSet.add(3);
                    aVar = aVar2;
                    break;
                case 4:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(4);
                    break;
                case 5:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(5);
                    break;
                case 6:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    hashSet.add(6);
                    break;
                case 7:
                    pi.b bVar2 = (pi.b) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, pi.b.CREATOR);
                    hashSet.add(7);
                    bVar = bVar2;
                    break;
                case 8:
                    strO4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(8);
                    break;
                case 9:
                    strO5 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(9);
                    break;
                case 10:
                case 11:
                case 13:
                case 17:
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
                case 12:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    hashSet.add(12);
                    break;
                case 14:
                    strO6 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(14);
                    break;
                case 15:
                    pi.c cVar2 = (pi.c) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, pi.c.CREATOR);
                    hashSet.add(15);
                    cVar = cVar2;
                    break;
                case 16:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    hashSet.add(16);
                    break;
                case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                    strO7 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(18);
                    break;
                case 19:
                    pi.d dVar2 = (pi.d) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, pi.d.CREATOR);
                    hashSet.add(19);
                    dVar = dVar2;
                    break;
                case 20:
                    strO8 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(20);
                    break;
                case MMException.DISPLAY_AD_EXPIRED /* 21 */:
                    iG5 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    hashSet.add(21);
                    break;
                case MMException.DISPLAY_AD_NOT_FOUND /* 22 */:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, pi.f.CREATOR);
                    hashSet.add(22);
                    break;
                case MMException.DISPLAY_AD_ALREADY_DISPLAYED /* 23 */:
                    arrayListC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, pi.g.CREATOR);
                    hashSet.add(23);
                    break;
                case MMException.DISPLAY_AD_NOT_PERMITTED /* 24 */:
                    iG6 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    hashSet.add(24);
                    break;
                case MMException.AD_BROKEN_REFERENCE /* 25 */:
                    iG7 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    hashSet.add(25);
                    break;
                case MMException.AD_NO_ACTIVITY /* 26 */:
                    strO9 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(26);
                    break;
                case 27:
                    strO10 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(27);
                    break;
                case 28:
                    arrayListC3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, pi.h.CREATOR);
                    hashSet.add(28);
                    break;
                case 29:
                    zC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    hashSet.add(29);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new pi(hashSet, iG2, strO, aVar, strO2, strO3, iG3, bVar, strO4, strO5, iG4, strO6, cVar, zC, strO7, dVar, strO8, iG5, arrayListC, arrayListC2, iG6, iG7, strO9, strO10, arrayListC3, zC2);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: fx, reason: merged with bridge method [inline-methods] */
    public pi[] newArray(int i) {
        return new pi[i];
    }
}
