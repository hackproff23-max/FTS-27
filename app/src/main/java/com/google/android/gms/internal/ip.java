package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.ApplicationMetadata;

/* JADX INFO: loaded from: classes.dex */
public class ip implements Parcelable.Creator<io> {
    static void a(io ioVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, ioVar.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, ioVar.fZ());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, ioVar.gi());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, ioVar.gj());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, (Parcelable) ioVar.getApplicationMetadata(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 6, ioVar.gk());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: ai, reason: merged with bridge method [inline-methods] */
    public io[] newArray(int i) {
        return new io[i];
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public io createFromParcel(Parcel parcel) {
        int iG = 0;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        double dM = 0.0d;
        ApplicationMetadata applicationMetadata = null;
        int iG3 = 0;
        boolean zC = false;
        int iG4 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    dM = com.google.android.gms.common.internal.safeparcel.a.m(parcel, iF);
                    break;
                case 3:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 4:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 5:
                    applicationMetadata = (ApplicationMetadata) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, ApplicationMetadata.CREATOR);
                    break;
                case 6:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new io(iG4, dM, zC, iG3, applicationMetadata, iG);
    }
}
