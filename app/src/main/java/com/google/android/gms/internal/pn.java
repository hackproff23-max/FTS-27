package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class pn implements Parcelable.Creator<pi.b.C0119b> {
    static void a(pi.b.C0119b c0119b, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        Set<Integer> set = c0119b.aon;
        if (set.contains(1)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, c0119b.CK);
        }
        if (set.contains(2)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, c0119b.lj);
        }
        if (set.contains(3)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, c0119b.vf, true);
        }
        if (set.contains(4)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, c0119b.li);
        }
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dD, reason: merged with bridge method [inline-methods] */
    public pi.b.C0119b createFromParcel(Parcel parcel) {
        int iG = 0;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        HashSet hashSet = new HashSet();
        String strO = null;
        int iG3 = 0;
        int iG4 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    hashSet.add(1);
                    break;
                case 2:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    hashSet.add(2);
                    break;
                case 3:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    hashSet.add(3);
                    break;
                case 4:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    hashSet.add(4);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new pi.b.C0119b(hashSet, iG4, iG3, strO, iG);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: fB, reason: merged with bridge method [inline-methods] */
    public pi.b.C0119b[] newArray(int i) {
        return new pi.b.C0119b[i];
    }
}
