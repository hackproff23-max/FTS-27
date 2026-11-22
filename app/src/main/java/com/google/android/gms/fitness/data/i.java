package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class i implements Parcelable.Creator<Device> {
    static void a(Device device, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, device.getManufacturer(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, device.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, device.getModel(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, device.getVersion(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, device.jF(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 5, device.getType());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 6, device.jC());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bx, reason: merged with bridge method [inline-methods] */
    public Device createFromParcel(Parcel parcel) {
        int iG = 0;
        String strO = null;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG3 = 0;
        String strO2 = null;
        String strO3 = null;
        String strO4 = null;
        int iG4 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    strO4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 2:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 3:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 4:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 5:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 6:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 1000:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new Device(iG4, strO4, strO3, strO2, strO, iG3, iG);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cQ, reason: merged with bridge method [inline-methods] */
    public Device[] newArray(int i) {
        return new Device[i];
    }
}
