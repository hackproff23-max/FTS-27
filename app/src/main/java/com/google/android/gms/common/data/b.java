package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class b implements Parcelable.Creator<a> {
    static void a(a aVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, aVar.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) aVar.KS, i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, aVar.Gt);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public a createFromParcel(Parcel parcel) {
        int iG;
        ParcelFileDescriptor parcelFileDescriptor;
        int iG2;
        int i = 0;
        int iG3 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        ParcelFileDescriptor parcelFileDescriptor2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < iG3) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    int i3 = i;
                    parcelFileDescriptor = parcelFileDescriptor2;
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    iG = i3;
                    break;
                case 2:
                    ParcelFileDescriptor parcelFileDescriptor3 = (ParcelFileDescriptor) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, ParcelFileDescriptor.CREATOR);
                    iG2 = i2;
                    iG = i;
                    parcelFileDescriptor = parcelFileDescriptor3;
                    break;
                case 3:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    parcelFileDescriptor = parcelFileDescriptor2;
                    iG2 = i2;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    iG = i;
                    parcelFileDescriptor = parcelFileDescriptor2;
                    iG2 = i2;
                    break;
            }
            i2 = iG2;
            parcelFileDescriptor2 = parcelFileDescriptor;
            i = iG;
        }
        if (parcel.dataPosition() != iG3) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG3, parcel);
        }
        return new a(i2, parcelFileDescriptor2, i);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: ar, reason: merged with bridge method [inline-methods] */
    public a[] newArray(int i) {
        return new a[i];
    }
}
