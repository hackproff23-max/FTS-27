package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class p implements Parcelable.Creator<ParcelableCollaborator> {
    static void a(ParcelableCollaborator parcelableCollaborator, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, parcelableCollaborator.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, parcelableCollaborator.SH);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, parcelableCollaborator.SI);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, parcelableCollaborator.vZ, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, parcelableCollaborator.SJ, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, parcelableCollaborator.OS, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, parcelableCollaborator.SK, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, parcelableCollaborator.SL, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: be, reason: merged with bridge method [inline-methods] */
    public ParcelableCollaborator createFromParcel(Parcel parcel) {
        boolean zC = false;
        String strO = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        String strO2 = null;
        String strO3 = null;
        String strO4 = null;
        String strO5 = null;
        boolean zC2 = false;
        int iG2 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    zC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 3:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 4:
                    strO5 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 5:
                    strO4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 6:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 7:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
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
        return new ParcelableCollaborator(iG2, zC2, zC, strO5, strO4, strO3, strO2, strO);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cu, reason: merged with bridge method [inline-methods] */
    public ParcelableCollaborator[] newArray(int i) {
        return new ParcelableCollaborator[i];
    }
}
