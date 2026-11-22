package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class od implements Parcelable.Creator<oc> {
    static void a(oc ocVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, ocVar.getName(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, ocVar.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) ocVar.nx(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, ocVar.getAddress(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, ocVar.ny(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, ocVar.getPhoneNumber(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, ocVar.nz(), false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cU, reason: merged with bridge method [inline-methods] */
    public oc createFromParcel(Parcel parcel) {
        String strO = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        String strO2 = null;
        ArrayList arrayListC = null;
        String strO3 = null;
        LatLng latLng = null;
        String strO4 = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    strO4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 2:
                    latLng = (LatLng) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, LatLng.CREATOR);
                    break;
                case 3:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 4:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, oa.CREATOR);
                    break;
                case 5:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
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
        return new oc(iG2, strO4, latLng, strO3, arrayListC, strO2, strO);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: eQ, reason: merged with bridge method [inline-methods] */
    public oc[] newArray(int i) {
        return new oc[i];
    }
}
