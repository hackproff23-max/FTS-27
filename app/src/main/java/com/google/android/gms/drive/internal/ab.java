package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ab implements Parcelable.Creator<FileUploadPreferencesImpl> {
    static void a(FileUploadPreferencesImpl fileUploadPreferencesImpl, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, fileUploadPreferencesImpl.CK);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, fileUploadPreferencesImpl.Qz);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, fileUploadPreferencesImpl.QA);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, fileUploadPreferencesImpl.QB);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: al, reason: merged with bridge method [inline-methods] */
    public FileUploadPreferencesImpl createFromParcel(Parcel parcel) {
        boolean zC = false;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        int iG3 = 0;
        int iG4 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 3:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 4:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new FileUploadPreferencesImpl(iG4, iG3, iG2, zC);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bA, reason: merged with bridge method [inline-methods] */
    public FileUploadPreferencesImpl[] newArray(int i) {
        return new FileUploadPreferencesImpl[i];
    }
}
