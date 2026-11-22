package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class c implements Parcelable.Creator<LaunchOptions> {
    static void a(LaunchOptions launchOptions, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, launchOptions.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, launchOptions.getRelaunchIfRunning());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, launchOptions.getLanguage(), false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: aa, reason: merged with bridge method [inline-methods] */
    public LaunchOptions[] newArray(int i) {
        return new LaunchOptions[i];
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
    public LaunchOptions createFromParcel(Parcel parcel) {
        boolean zC = false;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        String strO = null;
        int iG2 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 3:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new LaunchOptions(iG2, zC, strO);
    }
}
