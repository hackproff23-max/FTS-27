package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.games.Notifications;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.millennialmedia.android.MMException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class pe implements Parcelable.Creator<pd> {
    static void a(pd pdVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        Set<Integer> set = pdVar.aon;
        if (set.contains(1)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, pdVar.CK);
        }
        if (set.contains(2)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) pdVar.aoo, i, true);
        }
        if (set.contains(3)) {
            com.google.android.gms.common.internal.safeparcel.b.b(parcel, 3, pdVar.aop, true);
        }
        if (set.contains(4)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, (Parcelable) pdVar.aoq, i, true);
        }
        if (set.contains(5)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, pdVar.aor, true);
        }
        if (set.contains(6)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, pdVar.aos, true);
        }
        if (set.contains(7)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, pdVar.aot, true);
        }
        if (set.contains(8)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 8, pdVar.aou, true);
        }
        if (set.contains(9)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 9, pdVar.aov);
        }
        if (set.contains(10)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 10, pdVar.aow, true);
        }
        if (set.contains(11)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, (Parcelable) pdVar.aox, i, true);
        }
        if (set.contains(12)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 12, pdVar.aoy, true);
        }
        if (set.contains(13)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 13, pdVar.aoz, true);
        }
        if (set.contains(14)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 14, pdVar.aoA, true);
        }
        if (set.contains(15)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 15, (Parcelable) pdVar.aoB, i, true);
        }
        if (set.contains(17)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 17, pdVar.aoD, true);
        }
        if (set.contains(16)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 16, pdVar.aoC, true);
        }
        if (set.contains(19)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 19, pdVar.aoE, true);
        }
        if (set.contains(18)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 18, pdVar.ov, true);
        }
        if (set.contains(21)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 21, pdVar.aoG, true);
        }
        if (set.contains(20)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 20, pdVar.aoF, true);
        }
        if (set.contains(23)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 23, pdVar.UO, true);
        }
        if (set.contains(22)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 22, pdVar.aoH, true);
        }
        if (set.contains(25)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 25, pdVar.aoJ, true);
        }
        if (set.contains(24)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 24, pdVar.aoI, true);
        }
        if (set.contains(27)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 27, pdVar.aoL, true);
        }
        if (set.contains(26)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 26, pdVar.aoK, true);
        }
        if (set.contains(29)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 29, (Parcelable) pdVar.aoN, i, true);
        }
        if (set.contains(28)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 28, pdVar.aoM, true);
        }
        if (set.contains(31)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 31, pdVar.aoP, true);
        }
        if (set.contains(30)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 30, pdVar.aoO, true);
        }
        if (set.contains(34)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 34, (Parcelable) pdVar.aoR, i, true);
        }
        if (set.contains(32)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 32, pdVar.CE, true);
        }
        if (set.contains(33)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 33, pdVar.aoQ, true);
        }
        if (set.contains(38)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 38, pdVar.agh);
        }
        if (set.contains(39)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 39, pdVar.mName, true);
        }
        if (set.contains(36)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 36, pdVar.agg);
        }
        if (set.contains(37)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 37, (Parcelable) pdVar.aoS, i, true);
        }
        if (set.contains(42)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 42, pdVar.aoV, true);
        }
        if (set.contains(43)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 43, pdVar.aoW, true);
        }
        if (set.contains(40)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 40, (Parcelable) pdVar.aoT, i, true);
        }
        if (set.contains(41)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 41, pdVar.aoU, true);
        }
        if (set.contains(46)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 46, (Parcelable) pdVar.aoZ, i, true);
        }
        if (set.contains(47)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 47, pdVar.apa, true);
        }
        if (set.contains(44)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 44, pdVar.aoX, true);
        }
        if (set.contains(45)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 45, pdVar.aoY, true);
        }
        if (set.contains(51)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 51, pdVar.ape, true);
        }
        if (set.contains(50)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 50, (Parcelable) pdVar.apd, i, true);
        }
        if (set.contains(49)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 49, pdVar.apc, true);
        }
        if (set.contains(48)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 48, pdVar.apb, true);
        }
        if (set.contains(55)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 55, pdVar.apg, true);
        }
        if (set.contains(54)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 54, pdVar.vf, true);
        }
        if (set.contains(53)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 53, pdVar.vc, true);
        }
        if (set.contains(52)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 52, pdVar.apf, true);
        }
        if (set.contains(56)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 56, pdVar.aph, true);
        }
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dx, reason: merged with bridge method [inline-methods] */
    public pd createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        HashSet hashSet = new HashSet();
        int iG2 = 0;
        pd pdVar = null;
        ArrayList<String> arrayListC = null;
        pd pdVar2 = null;
        String strO = null;
        String strO2 = null;
        String strO3 = null;
        ArrayList arrayListC2 = null;
        int iG3 = 0;
        ArrayList arrayListC3 = null;
        pd pdVar3 = null;
        ArrayList arrayListC4 = null;
        String strO4 = null;
        String strO5 = null;
        pd pdVar4 = null;
        String strO6 = null;
        String strO7 = null;
        String strO8 = null;
        ArrayList arrayListC5 = null;
        String strO9 = null;
        String strO10 = null;
        String strO11 = null;
        String strO12 = null;
        String strO13 = null;
        String strO14 = null;
        String strO15 = null;
        String strO16 = null;
        String strO17 = null;
        pd pdVar5 = null;
        String strO18 = null;
        String strO19 = null;
        String strO20 = null;
        String strO21 = null;
        pd pdVar6 = null;
        double dM = 0.0d;
        pd pdVar7 = null;
        double dM2 = 0.0d;
        String strO22 = null;
        pd pdVar8 = null;
        ArrayList arrayListC6 = null;
        String strO23 = null;
        String strO24 = null;
        String strO25 = null;
        String strO26 = null;
        pd pdVar9 = null;
        String strO27 = null;
        String strO28 = null;
        String strO29 = null;
        pd pdVar10 = null;
        String strO30 = null;
        String strO31 = null;
        String strO32 = null;
        String strO33 = null;
        String strO34 = null;
        String strO35 = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    hashSet.add(1);
                    break;
                case 2:
                    pd pdVar11 = (pd) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, pd.CREATOR);
                    hashSet.add(2);
                    pdVar = pdVar11;
                    break;
                case 3:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.C(parcel, iF);
                    hashSet.add(3);
                    break;
                case 4:
                    pd pdVar12 = (pd) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, pd.CREATOR);
                    hashSet.add(4);
                    pdVar2 = pdVar12;
                    break;
                case 5:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(5);
                    break;
                case 6:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(6);
                    break;
                case 7:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(7);
                    break;
                case 8:
                    arrayListC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, pd.CREATOR);
                    hashSet.add(8);
                    break;
                case 9:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    hashSet.add(9);
                    break;
                case 10:
                    arrayListC3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, pd.CREATOR);
                    hashSet.add(10);
                    break;
                case 11:
                    pd pdVar13 = (pd) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, pd.CREATOR);
                    hashSet.add(11);
                    pdVar3 = pdVar13;
                    break;
                case 12:
                    arrayListC4 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, pd.CREATOR);
                    hashSet.add(12);
                    break;
                case 13:
                    strO4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(13);
                    break;
                case 14:
                    strO5 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(14);
                    break;
                case 15:
                    pd pdVar14 = (pd) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, pd.CREATOR);
                    hashSet.add(15);
                    pdVar4 = pdVar14;
                    break;
                case 16:
                    strO6 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(16);
                    break;
                case 17:
                    strO7 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(17);
                    break;
                case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                    strO8 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(18);
                    break;
                case 19:
                    arrayListC5 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, pd.CREATOR);
                    hashSet.add(19);
                    break;
                case 20:
                    strO9 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(20);
                    break;
                case MMException.DISPLAY_AD_EXPIRED /* 21 */:
                    strO10 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(21);
                    break;
                case MMException.DISPLAY_AD_NOT_FOUND /* 22 */:
                    strO11 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(22);
                    break;
                case MMException.DISPLAY_AD_ALREADY_DISPLAYED /* 23 */:
                    strO12 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(23);
                    break;
                case MMException.DISPLAY_AD_NOT_PERMITTED /* 24 */:
                    strO13 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(24);
                    break;
                case MMException.AD_BROKEN_REFERENCE /* 25 */:
                    strO14 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(25);
                    break;
                case MMException.AD_NO_ACTIVITY /* 26 */:
                    strO15 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(26);
                    break;
                case 27:
                    strO16 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(27);
                    break;
                case 28:
                    strO17 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(28);
                    break;
                case 29:
                    pd pdVar15 = (pd) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, pd.CREATOR);
                    hashSet.add(29);
                    pdVar5 = pdVar15;
                    break;
                case 30:
                    strO18 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(30);
                    break;
                case Notifications.NOTIFICATION_TYPES_ALL /* 31 */:
                    strO19 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(31);
                    break;
                case 32:
                    strO20 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(32);
                    break;
                case 33:
                    strO21 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(33);
                    break;
                case 34:
                    pd pdVar16 = (pd) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, pd.CREATOR);
                    hashSet.add(34);
                    pdVar6 = pdVar16;
                    break;
                case 35:
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
                case 36:
                    dM = com.google.android.gms.common.internal.safeparcel.a.m(parcel, iF);
                    hashSet.add(36);
                    break;
                case 37:
                    pd pdVar17 = (pd) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, pd.CREATOR);
                    hashSet.add(37);
                    pdVar7 = pdVar17;
                    break;
                case 38:
                    dM2 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, iF);
                    hashSet.add(38);
                    break;
                case 39:
                    strO22 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(39);
                    break;
                case 40:
                    pd pdVar18 = (pd) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, pd.CREATOR);
                    hashSet.add(40);
                    pdVar8 = pdVar18;
                    break;
                case 41:
                    arrayListC6 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, pd.CREATOR);
                    hashSet.add(41);
                    break;
                case 42:
                    strO23 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(42);
                    break;
                case 43:
                    strO24 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(43);
                    break;
                case 44:
                    strO25 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(44);
                    break;
                case 45:
                    strO26 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(45);
                    break;
                case 46:
                    pd pdVar19 = (pd) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, pd.CREATOR);
                    hashSet.add(46);
                    pdVar9 = pdVar19;
                    break;
                case 47:
                    strO27 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(47);
                    break;
                case 48:
                    strO28 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(48);
                    break;
                case 49:
                    strO29 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(49);
                    break;
                case 50:
                    pd pdVar20 = (pd) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, pd.CREATOR);
                    hashSet.add(50);
                    pdVar10 = pdVar20;
                    break;
                case 51:
                    strO30 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(51);
                    break;
                case 52:
                    strO31 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(52);
                    break;
                case 53:
                    strO32 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(53);
                    break;
                case 54:
                    strO33 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(54);
                    break;
                case 55:
                    strO34 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(55);
                    break;
                case 56:
                    strO35 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(56);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new pd(hashSet, iG2, pdVar, arrayListC, pdVar2, strO, strO2, strO3, arrayListC2, iG3, arrayListC3, pdVar3, arrayListC4, strO4, strO5, pdVar4, strO6, strO7, strO8, arrayListC5, strO9, strO10, strO11, strO12, strO13, strO14, strO15, strO16, strO17, pdVar5, strO18, strO19, strO20, strO21, pdVar6, dM, pdVar7, dM2, strO22, pdVar8, arrayListC6, strO23, strO24, strO25, strO26, pdVar9, strO27, strO28, strO29, pdVar10, strO30, strO31, strO32, strO33, strO34, strO35);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: fv, reason: merged with bridge method [inline-methods] */
    public pd[] newArray(int i) {
        return new pd[i];
    }
}
