package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class n implements Parcelable.Creator<m> {
    static void a(m mVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, mVar.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) mVar.getUri(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, mVar.rk(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, mVar.getData(), false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: eq, reason: merged with bridge method [inline-methods] */
    public m createFromParcel(Parcel parcel) {
        byte[] bArrR;
        Bundle bundleQ;
        Uri uri;
        int iG;
        byte[] bArr = null;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int i = 0;
        Bundle bundle = null;
        Uri uri2 = null;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    byte[] bArr2 = bArr;
                    bundleQ = bundle;
                    uri = uri2;
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    bArrR = bArr2;
                    break;
                case 2:
                    iG = i;
                    Bundle bundle2 = bundle;
                    uri = (Uri) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Uri.CREATOR);
                    bArrR = bArr;
                    bundleQ = bundle2;
                    break;
                case 3:
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    bArrR = bArr;
                    bundleQ = bundle;
                    uri = uri2;
                    iG = i;
                    break;
                case 4:
                    uri = uri2;
                    iG = i;
                    byte[] bArr3 = bArr;
                    bundleQ = com.google.android.gms.common.internal.safeparcel.a.q(parcel, iF);
                    bArrR = bArr3;
                    break;
                case 5:
                    bArrR = com.google.android.gms.common.internal.safeparcel.a.r(parcel, iF);
                    bundleQ = bundle;
                    uri = uri2;
                    iG = i;
                    break;
            }
            i = iG;
            uri2 = uri;
            bundle = bundleQ;
            bArr = bArrR;
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new m(i, uri2, bundle, bArr);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: gz, reason: merged with bridge method [inline-methods] */
    public m[] newArray(int i) {
        return new m[i];
    }
}
