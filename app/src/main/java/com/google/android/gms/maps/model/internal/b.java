package com.google.android.gms.maps.model.internal;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class b implements Parcelable.Creator<a> {
    static void a(a aVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, aVar.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, aVar.oh());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, aVar.oi(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, (Parcelable) aVar.getBitmap(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dp, reason: merged with bridge method [inline-methods] */
    public a createFromParcel(Parcel parcel) {
        Bitmap bitmap = null;
        byte bE = 0;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        Bundle bundleQ = null;
        int iG2 = 0;
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
                    bundleQ = com.google.android.gms.common.internal.safeparcel.a.q(parcel, iF);
                    break;
                case 4:
                    bitmap = (Bitmap) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Bitmap.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new a(iG2, bE, bundleQ, bitmap);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: fl, reason: merged with bridge method [inline-methods] */
    public a[] newArray(int i) {
        return new a[i];
    }
}
