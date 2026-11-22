package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class b implements Parcelable.Creator<WebImage> {
    static void a(WebImage webImage, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, webImage.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) webImage.getUrl(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, webImage.getWidth());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, webImage.getHeight());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public WebImage createFromParcel(Parcel parcel) {
        int iG;
        int iG2;
        Uri uri;
        int iG3;
        int i = 0;
        int iG4 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        Uri uri2 = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < iG4) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    int i4 = i;
                    iG2 = i2;
                    uri = uri2;
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    iG = i4;
                    break;
                case 2:
                    iG3 = i3;
                    int i5 = i2;
                    uri = (Uri) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Uri.CREATOR);
                    iG = i;
                    iG2 = i5;
                    break;
                case 3:
                    uri = uri2;
                    iG3 = i3;
                    int i6 = i;
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    iG = i6;
                    break;
                case 4:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    iG2 = i2;
                    uri = uri2;
                    iG3 = i3;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    iG = i;
                    iG2 = i2;
                    uri = uri2;
                    iG3 = i3;
                    break;
            }
            i3 = iG3;
            uri2 = uri;
            i2 = iG2;
            i = iG;
        }
        if (parcel.dataPosition() != iG4) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG4, parcel);
        }
        return new WebImage(i3, uri2, i2, i);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: aA, reason: merged with bridge method [inline-methods] */
    public WebImage[] newArray(int i) {
        return new WebImage[i];
    }
}
