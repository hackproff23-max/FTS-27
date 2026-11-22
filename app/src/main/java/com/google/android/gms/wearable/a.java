package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class a implements Parcelable.Creator<Asset> {
    static void a(Asset asset, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, asset.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, asset.getData(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, asset.getDigest(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, (Parcelable) asset.axb, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, (Parcelable) asset.uri, i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: el, reason: merged with bridge method [inline-methods] */
    public Asset createFromParcel(Parcel parcel) {
        Uri uri = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        ParcelFileDescriptor parcelFileDescriptor = null;
        String strO = null;
        byte[] bArrR = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    bArrR = com.google.android.gms.common.internal.safeparcel.a.r(parcel, iF);
                    break;
                case 3:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 4:
                    parcelFileDescriptor = (ParcelFileDescriptor) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, ParcelFileDescriptor.CREATOR);
                    break;
                case 5:
                    uri = (Uri) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Uri.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new Asset(iG2, bArrR, strO, parcelFileDescriptor, uri);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: gu, reason: merged with bridge method [inline-methods] */
    public Asset[] newArray(int i) {
        return new Asset[i];
    }
}
