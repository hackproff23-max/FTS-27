package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class as implements Parcelable.Creator<ar> {
    static void a(ar arVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, arVar.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, arVar.rm(), false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: eC, reason: merged with bridge method [inline-methods] */
    public ar createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        IBinder iBinderP = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    iBinderP = com.google.android.gms.common.internal.safeparcel.a.p(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new ar(iG2, iBinderP);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: gL, reason: merged with bridge method [inline-methods] */
    public ar[] newArray(int i) {
        return new ar[i];
    }
}
