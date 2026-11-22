package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class pg implements Parcelable.Creator<pf> {
    static void a(pf pfVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        Set<Integer> set = pfVar.aon;
        if (set.contains(1)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, pfVar.CK);
        }
        if (set.contains(2)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, pfVar.CE, true);
        }
        if (set.contains(4)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, (Parcelable) pfVar.api, i, true);
        }
        if (set.contains(5)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, pfVar.apa, true);
        }
        if (set.contains(6)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, (Parcelable) pfVar.apj, i, true);
        }
        if (set.contains(7)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, pfVar.vc, true);
        }
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dy, reason: merged with bridge method [inline-methods] */
    public pf createFromParcel(Parcel parcel) {
        String strO = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        HashSet hashSet = new HashSet();
        int iG2 = 0;
        pd pdVar = null;
        String strO2 = null;
        pd pdVar2 = null;
        String strO3 = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    hashSet.add(1);
                    break;
                case 2:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(2);
                    break;
                case 3:
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
                case 4:
                    pd pdVar3 = (pd) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, pd.CREATOR);
                    hashSet.add(4);
                    pdVar2 = pdVar3;
                    break;
                case 5:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(5);
                    break;
                case 6:
                    pd pdVar4 = (pd) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, pd.CREATOR);
                    hashSet.add(6);
                    pdVar = pdVar4;
                    break;
                case 7:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(7);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new pf(hashSet, iG2, strO3, pdVar2, strO2, pdVar, strO);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: fw, reason: merged with bridge method [inline-methods] */
    public pf[] newArray(int i) {
        return new pf[i];
    }
}
