package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class h implements Parcelable.Creator<UserMetadata> {
    static void a(UserMetadata userMetadata, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, userMetadata.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, userMetadata.OR, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, userMetadata.OS, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, userMetadata.OT, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, userMetadata.OU);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, userMetadata.OV, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
    public UserMetadata createFromParcel(Parcel parcel) {
        boolean zC = false;
        String strO = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        String strO2 = null;
        String strO3 = null;
        String strO4 = null;
        int iG2 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    strO4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 3:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 4:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 5:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 6:
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
        return new UserMetadata(iG2, strO4, strO3, strO2, zC, strO);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: be, reason: merged with bridge method [inline-methods] */
    public UserMetadata[] newArray(int i) {
        return new UserMetadata[i];
    }
}
