package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

/* JADX INFO: loaded from: classes.dex */
public class c implements Parcelable.Creator<StreetViewPanoramaOptions> {
    static void a(StreetViewPanoramaOptions streetViewPanoramaOptions, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, streetViewPanoramaOptions.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) streetViewPanoramaOptions.getStreetViewPanoramaCamera(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, streetViewPanoramaOptions.getPanoramaId(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, (Parcelable) streetViewPanoramaOptions.getPosition(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, streetViewPanoramaOptions.getRadius(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, streetViewPanoramaOptions.nS());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, streetViewPanoramaOptions.nI());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, streetViewPanoramaOptions.nT());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, streetViewPanoramaOptions.nU());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, streetViewPanoramaOptions.nE());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cY, reason: merged with bridge method [inline-methods] */
    public StreetViewPanoramaOptions createFromParcel(Parcel parcel) {
        Integer numH = null;
        byte bE = 0;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        byte bE2 = 0;
        byte bE3 = 0;
        byte bE4 = 0;
        byte bE5 = 0;
        LatLng latLng = null;
        String strO = null;
        StreetViewPanoramaCamera streetViewPanoramaCamera = null;
        int iG2 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    streetViewPanoramaCamera = (StreetViewPanoramaCamera) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, StreetViewPanoramaCamera.CREATOR);
                    break;
                case 3:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 4:
                    latLng = (LatLng) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, LatLng.CREATOR);
                    break;
                case 5:
                    numH = com.google.android.gms.common.internal.safeparcel.a.h(parcel, iF);
                    break;
                case 6:
                    bE5 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, iF);
                    break;
                case 7:
                    bE4 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, iF);
                    break;
                case 8:
                    bE3 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, iF);
                    break;
                case 9:
                    bE2 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, iF);
                    break;
                case 10:
                    bE = com.google.android.gms.common.internal.safeparcel.a.e(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new StreetViewPanoramaOptions(iG2, streetViewPanoramaCamera, strO, latLng, numH, bE5, bE4, bE3, bE2, bE);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: eU, reason: merged with bridge method [inline-methods] */
    public StreetViewPanoramaOptions[] newArray(int i) {
        return new StreetViewPanoramaOptions[i];
    }
}
