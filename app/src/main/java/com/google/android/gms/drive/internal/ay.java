package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;

/* JADX INFO: loaded from: classes.dex */
public class ay implements Parcelable.Creator<OpenContentsRequest> {
    static void a(OpenContentsRequest openContentsRequest, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, openContentsRequest.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) openContentsRequest.Pp, i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, openContentsRequest.Oi);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, openContentsRequest.QT);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: aD, reason: merged with bridge method [inline-methods] */
    public OpenContentsRequest createFromParcel(Parcel parcel) {
        int iG;
        int iG2;
        DriveId driveId;
        int iG3;
        int i = 0;
        int iG4 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        DriveId driveId2 = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < iG4) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    int i4 = i;
                    iG2 = i2;
                    driveId = driveId2;
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    iG = i4;
                    break;
                case 2:
                    iG3 = i3;
                    int i5 = i2;
                    driveId = (DriveId) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DriveId.CREATOR);
                    iG = i;
                    iG2 = i5;
                    break;
                case 3:
                    driveId = driveId2;
                    iG3 = i3;
                    int i6 = i;
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    iG = i6;
                    break;
                case 4:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    iG2 = i2;
                    driveId = driveId2;
                    iG3 = i3;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    iG = i;
                    iG2 = i2;
                    driveId = driveId2;
                    iG3 = i3;
                    break;
            }
            i3 = iG3;
            driveId2 = driveId;
            i2 = iG2;
            i = iG;
        }
        if (parcel.dataPosition() != iG4) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG4, parcel);
        }
        return new OpenContentsRequest(i3, driveId2, i2, i);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bS, reason: merged with bridge method [inline-methods] */
    public OpenContentsRequest[] newArray(int i) {
        return new OpenContentsRequest[i];
    }
}
