package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: classes.dex */
public class j implements Parcelable.Creator<CreateFileIntentSenderRequest> {
    static void a(CreateFileIntentSenderRequest createFileIntentSenderRequest, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, createFileIntentSenderRequest.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) createFileIntentSenderRequest.Px, i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, createFileIntentSenderRequest.ve);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, createFileIntentSenderRequest.OH, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, (Parcelable) createFileIntentSenderRequest.OJ, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, createFileIntentSenderRequest.Py, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: ag, reason: merged with bridge method [inline-methods] */
    public CreateFileIntentSenderRequest createFromParcel(Parcel parcel) {
        int iG = 0;
        Integer numH = null;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        DriveId driveId = null;
        String strO = null;
        MetadataBundle metadataBundle = null;
        int iG3 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    metadataBundle = (MetadataBundle) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, MetadataBundle.CREATOR);
                    break;
                case 3:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 4:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 5:
                    driveId = (DriveId) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DriveId.CREATOR);
                    break;
                case 6:
                    numH = com.google.android.gms.common.internal.safeparcel.a.h(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new CreateFileIntentSenderRequest(iG3, metadataBundle, iG, strO, driveId, numH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: br, reason: merged with bridge method [inline-methods] */
    public CreateFileIntentSenderRequest[] newArray(int i) {
        return new CreateFileIntentSenderRequest[i];
    }
}
