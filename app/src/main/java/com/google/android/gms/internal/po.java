package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class po implements Parcelable.Creator<pi.c> {
    static void a(pi.c cVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        Set<Integer> set = cVar.aon;
        if (set.contains(1)) {
            com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, cVar.CK);
        }
        if (set.contains(2)) {
            com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, cVar.vf, true);
        }
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dE, reason: merged with bridge method [inline-methods] */
    public pi.c createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        HashSet hashSet = new HashSet();
        int iG2 = 0;
        String strO = null;
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
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new pi.c(hashSet, iG2, strO);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: fC, reason: merged with bridge method [inline-methods] */
    public pi.c[] newArray(int i) {
        return new pi.c[i];
    }
}
