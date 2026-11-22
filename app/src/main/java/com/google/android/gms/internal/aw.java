package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class aw implements Parcelable.Creator<av> {
    static void a(av avVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, avVar.versionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, avVar.od);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, avVar.extras, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, avVar.oe);
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 5, avVar.of, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, avVar.og);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 7, avVar.oh);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, avVar.oi);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, avVar.oj, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, (Parcelable) avVar.ok, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, (Parcelable) avVar.ol, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 12, avVar.om, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 13, avVar.on, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public av createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        long jI = 0;
        Bundle bundleQ = null;
        int iG3 = 0;
        ArrayList<String> arrayListC = null;
        boolean zC = false;
        int iG4 = 0;
        boolean zC2 = false;
        String strO = null;
        bj bjVar = null;
        Location location = null;
        String strO2 = null;
        Bundle bundleQ2 = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 3:
                    bundleQ = com.google.android.gms.common.internal.safeparcel.a.q(parcel, iF);
                    break;
                case 4:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 5:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.C(parcel, iF);
                    break;
                case 6:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 7:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 8:
                    zC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 9:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 10:
                    bjVar = (bj) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, bj.CREATOR);
                    break;
                case 11:
                    location = (Location) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Location.CREATOR);
                    break;
                case 12:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 13:
                    bundleQ2 = com.google.android.gms.common.internal.safeparcel.a.q(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new av(iG2, jI, bundleQ, iG3, arrayListC, zC, iG4, zC2, strO, bjVar, location, strO2, bundleQ2);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public av[] newArray(int i) {
        return new av[i];
    }
}
