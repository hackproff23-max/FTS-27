package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.CameraPosition;

/* JADX INFO: loaded from: classes.dex */
public class a implements Parcelable.Creator<GoogleMapOptions> {
    static void a(GoogleMapOptions googleMapOptions, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, googleMapOptions.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, googleMapOptions.nD());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, googleMapOptions.nE());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, googleMapOptions.getMapType());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, (Parcelable) googleMapOptions.getCamera(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, googleMapOptions.nF());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, googleMapOptions.nG());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, googleMapOptions.nH());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, googleMapOptions.nI());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, googleMapOptions.nJ());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, googleMapOptions.nK());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 12, googleMapOptions.nL());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 14, googleMapOptions.nM());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cX, reason: merged with bridge method [inline-methods] */
    public GoogleMapOptions createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        byte bE = 0;
        byte bE2 = 0;
        int iG3 = 0;
        CameraPosition cameraPosition = null;
        byte bE3 = 0;
        byte bE4 = 0;
        byte bE5 = 0;
        byte bE6 = 0;
        byte bE7 = 0;
        byte bE8 = 0;
        byte bE9 = 0;
        byte bE10 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    bE = com.google.android.gms.common.internal.safeparcel.a.e(parcel, iF);
                    break;
                case 3:
                    bE2 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, iF);
                    break;
                case 4:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 5:
                    cameraPosition = (CameraPosition) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, CameraPosition.CREATOR);
                    break;
                case 6:
                    bE3 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, iF);
                    break;
                case 7:
                    bE4 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, iF);
                    break;
                case 8:
                    bE5 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, iF);
                    break;
                case 9:
                    bE6 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, iF);
                    break;
                case 10:
                    bE7 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, iF);
                    break;
                case 11:
                    bE8 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, iF);
                    break;
                case 12:
                    bE9 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, iF);
                    break;
                case 13:
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
                case 14:
                    bE10 = com.google.android.gms.common.internal.safeparcel.a.e(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new GoogleMapOptions(iG2, bE, bE2, iG3, cameraPosition, bE3, bE4, bE5, bE6, bE7, bE8, bE9, bE10);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: eT, reason: merged with bridge method [inline-methods] */
    public GoogleMapOptions[] newArray(int i) {
        return new GoogleMapOptions[i];
    }
}
