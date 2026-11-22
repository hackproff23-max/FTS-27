package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class dz implements Parcelable.Creator<ea> {
    static void a(ea eaVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, eaVar.versionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, eaVar.cu(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, eaVar.cv(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, eaVar.cw(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, eaVar.ct(), false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public ea createFromParcel(Parcel parcel) {
        IBinder iBinderP = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        IBinder iBinderP2 = null;
        IBinder iBinderP3 = null;
        IBinder iBinderP4 = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
                case 3:
                    iBinderP4 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, iF);
                    break;
                case 4:
                    iBinderP3 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, iF);
                    break;
                case 5:
                    iBinderP2 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, iF);
                    break;
                case 6:
                    iBinderP = com.google.android.gms.common.internal.safeparcel.a.p(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new ea(iG2, iBinderP4, iBinderP3, iBinderP2, iBinderP);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public ea[] newArray(int i) {
        return new ea[i];
    }
}
