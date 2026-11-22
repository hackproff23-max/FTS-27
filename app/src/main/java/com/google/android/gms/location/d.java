package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.mopub.nativeads.MoPubNativeAdPositioning;

/* JADX INFO: loaded from: classes.dex */
public class d implements Parcelable.Creator<LocationRequest> {
    static void a(LocationRequest locationRequest, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, locationRequest.mPriority);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, locationRequest.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, locationRequest.agr);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, locationRequest.ags);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, locationRequest.Wg);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, locationRequest.age);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 6, locationRequest.agt);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, locationRequest.agu);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, locationRequest.agv);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cJ, reason: merged with bridge method [inline-methods] */
    public LocationRequest createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        int iG3 = 102;
        long jI = 3600000;
        long jI2 = 600000;
        boolean zC = false;
        long jI3 = Long.MAX_VALUE;
        int iG4 = MoPubNativeAdPositioning.MoPubClientPositioning.NO_REPEAT;
        float fL = 0.0f;
        long jI4 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 3:
                    jI2 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 4:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 5:
                    jI3 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 6:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 7:
                    fL = com.google.android.gms.common.internal.safeparcel.a.l(parcel, iF);
                    break;
                case 8:
                    jI4 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 1000:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new LocationRequest(iG2, iG3, jI, jI2, zC, jI3, iG4, fL, jI4);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: eA, reason: merged with bridge method [inline-methods] */
    public LocationRequest[] newArray(int i) {
        return new LocationRequest[i];
    }
}
