package com.google.android.gms.wearable;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class d implements Parcelable.Creator<c> {
    static void a(c cVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, cVar.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, cVar.getName(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, cVar.getAddress(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, cVar.getType());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 5, cVar.getRole());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, cVar.isEnabled());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, cVar.isConnected());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, cVar.rj(), false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: em, reason: merged with bridge method [inline-methods] */
    public c createFromParcel(Parcel parcel) {
        String strO = null;
        boolean zC = false;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        boolean zC2 = false;
        int iG2 = 0;
        int iG3 = 0;
        String strO2 = null;
        String strO3 = null;
        int iG4 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 3:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 4:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 5:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 6:
                    zC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 7:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 8:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new c(iG4, strO3, strO2, iG3, iG2, zC2, zC, strO);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: gv, reason: merged with bridge method [inline-methods] */
    public c[] newArray(int i) {
        return new c[i];
    }
}
