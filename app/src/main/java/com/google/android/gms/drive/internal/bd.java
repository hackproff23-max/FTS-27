package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DrivePreferences;

/* JADX INFO: loaded from: classes.dex */
public class bd implements Parcelable.Creator<SetDrivePreferencesRequest> {
    static void a(SetDrivePreferencesRequest setDrivePreferencesRequest, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, setDrivePreferencesRequest.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) setDrivePreferencesRequest.QN, i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: aH, reason: merged with bridge method [inline-methods] */
    public SetDrivePreferencesRequest createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        DrivePreferences drivePreferences = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    drivePreferences = (DrivePreferences) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DrivePreferences.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new SetDrivePreferencesRequest(iG2, drivePreferences);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bW, reason: merged with bridge method [inline-methods] */
    public SetDrivePreferencesRequest[] newArray(int i) {
        return new SetDrivePreferencesRequest[i];
    }
}
