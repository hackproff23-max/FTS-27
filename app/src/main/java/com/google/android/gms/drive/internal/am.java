package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class am implements Parcelable.Creator<OnDeviceUsagePreferenceResponse> {
    static void a(OnDeviceUsagePreferenceResponse onDeviceUsagePreferenceResponse, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, onDeviceUsagePreferenceResponse.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) onDeviceUsagePreferenceResponse.QK, i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: ar, reason: merged with bridge method [inline-methods] */
    public OnDeviceUsagePreferenceResponse createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        FileUploadPreferencesImpl fileUploadPreferencesImpl = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    fileUploadPreferencesImpl = (FileUploadPreferencesImpl) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, FileUploadPreferencesImpl.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new OnDeviceUsagePreferenceResponse(iG2, fileUploadPreferencesImpl);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bG, reason: merged with bridge method [inline-methods] */
    public OnDeviceUsagePreferenceResponse[] newArray(int i) {
        return new OnDeviceUsagePreferenceResponse[i];
    }
}
