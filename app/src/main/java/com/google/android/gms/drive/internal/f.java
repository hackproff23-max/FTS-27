package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: classes.dex */
public class f implements Parcelable.Creator<CloseContentsAndUpdateMetadataRequest> {
    static void a(CloseContentsAndUpdateMetadataRequest closeContentsAndUpdateMetadataRequest, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, closeContentsAndUpdateMetadataRequest.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) closeContentsAndUpdateMetadataRequest.Pp, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, (Parcelable) closeContentsAndUpdateMetadataRequest.Pq, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, (Parcelable) closeContentsAndUpdateMetadataRequest.Pr, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, closeContentsAndUpdateMetadataRequest.Oz);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, closeContentsAndUpdateMetadataRequest.Oy, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 7, closeContentsAndUpdateMetadataRequest.Ps);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: ad, reason: merged with bridge method [inline-methods] */
    public CloseContentsAndUpdateMetadataRequest createFromParcel(Parcel parcel) {
        int iG = 0;
        String strO = null;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        boolean zC = false;
        Contents contents = null;
        MetadataBundle metadataBundle = null;
        DriveId driveId = null;
        int iG3 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    driveId = (DriveId) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DriveId.CREATOR);
                    break;
                case 3:
                    metadataBundle = (MetadataBundle) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, MetadataBundle.CREATOR);
                    break;
                case 4:
                    contents = (Contents) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Contents.CREATOR);
                    break;
                case 5:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 6:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 7:
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
        return new CloseContentsAndUpdateMetadataRequest(iG3, driveId, metadataBundle, contents, zC, strO, iG);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bn, reason: merged with bridge method [inline-methods] */
    public CloseContentsAndUpdateMetadataRequest[] newArray(int i) {
        return new CloseContentsAndUpdateMetadataRequest[i];
    }
}
