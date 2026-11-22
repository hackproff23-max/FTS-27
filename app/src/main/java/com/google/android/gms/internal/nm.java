package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.location.LocationRequest;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class nm implements Parcelable.Creator<nl> {
    static void a(nl nlVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable) nlVar.We, i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, nlVar.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, nlVar.ahh);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, nlVar.ahi);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, nlVar.ahj);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 5, nlVar.ahk, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, nlVar.mTag, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cM, reason: merged with bridge method [inline-methods] */
    public nl createFromParcel(Parcel parcel) {
        String strO = null;
        boolean zC = true;
        boolean zC2 = false;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        List<nd> listC = nl.ahg;
        boolean zC3 = true;
        LocationRequest locationRequest = null;
        int iG2 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    locationRequest = (LocationRequest) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, LocationRequest.CREATOR);
                    break;
                case 2:
                    zC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 3:
                    zC3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 4:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 5:
                    listC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, nd.CREATOR);
                    break;
                case 6:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
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
        return new nl(iG2, locationRequest, zC2, zC3, zC, listC, strO);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: eG, reason: merged with bridge method [inline-methods] */
    public nl[] newArray(int i) {
        return new nl[i];
    }
}
