package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class a implements Parcelable.Creator<Contents> {
    static void a(Contents contents, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, contents.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) contents.LG, i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, contents.ve);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, contents.Oi);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, (Parcelable) contents.Oj, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, contents.Ok);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: R, reason: merged with bridge method [inline-methods] */
    public Contents createFromParcel(Parcel parcel) {
        DriveId driveId = null;
        boolean zC = false;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        int iG3 = 0;
        ParcelFileDescriptor parcelFileDescriptor = null;
        int iG4 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    parcelFileDescriptor = (ParcelFileDescriptor) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, ParcelFileDescriptor.CREATOR);
                    break;
                case 3:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 4:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 5:
                    driveId = (DriveId) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DriveId.CREATOR);
                    break;
                case 6:
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
                case 7:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new Contents(iG4, parcelFileDescriptor, iG3, iG2, driveId, zC);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: aX, reason: merged with bridge method [inline-methods] */
    public Contents[] newArray(int i) {
        return new Contents[i];
    }
}
