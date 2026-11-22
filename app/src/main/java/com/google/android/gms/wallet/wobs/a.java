package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.la;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class a implements Parcelable.Creator<CommonWalletObject> {
    static void a(CommonWalletObject commonWalletObject, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, commonWalletObject.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, commonWalletObject.fl, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, commonWalletObject.avk, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, commonWalletObject.name, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, commonWalletObject.ave, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, commonWalletObject.avg, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, commonWalletObject.avh, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, commonWalletObject.avi, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, commonWalletObject.avj, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 10, commonWalletObject.state);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 11, commonWalletObject.avl, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 12, (Parcelable) commonWalletObject.avm, i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 13, commonWalletObject.avn, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 14, commonWalletObject.avo, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 15, commonWalletObject.avp, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 17, commonWalletObject.avr);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 16, commonWalletObject.avq, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 19, commonWalletObject.avt, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 18, commonWalletObject.avs, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 20, commonWalletObject.avu, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: ec, reason: merged with bridge method [inline-methods] */
    public CommonWalletObject createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        String strO = null;
        String strO2 = null;
        String strO3 = null;
        String strO4 = null;
        String strO5 = null;
        String strO6 = null;
        String strO7 = null;
        String strO8 = null;
        int iG3 = 0;
        ArrayList arrayListIe = la.ie();
        l lVar = null;
        ArrayList arrayListIe2 = la.ie();
        String strO9 = null;
        String strO10 = null;
        ArrayList arrayListIe3 = la.ie();
        boolean zC = false;
        ArrayList arrayListIe4 = la.ie();
        ArrayList arrayListIe5 = la.ie();
        ArrayList arrayListIe6 = la.ie();
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 3:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 4:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 5:
                    strO4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 6:
                    strO5 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 7:
                    strO6 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 8:
                    strO7 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 9:
                    strO8 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 10:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 11:
                    arrayListIe = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, p.CREATOR);
                    break;
                case 12:
                    lVar = (l) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, l.CREATOR);
                    break;
                case 13:
                    arrayListIe2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, LatLng.CREATOR);
                    break;
                case 14:
                    strO9 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 15:
                    strO10 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 16:
                    arrayListIe3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, d.CREATOR);
                    break;
                case 17:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                    arrayListIe4 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, n.CREATOR);
                    break;
                case 19:
                    arrayListIe5 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, j.CREATOR);
                    break;
                case 20:
                    arrayListIe6 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, n.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new CommonWalletObject(iG2, strO, strO2, strO3, strO4, strO5, strO6, strO7, strO8, iG3, arrayListIe, lVar, arrayListIe2, strO9, strO10, arrayListIe3, zC, arrayListIe4, arrayListIe5, arrayListIe6);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: gl, reason: merged with bridge method [inline-methods] */
    public CommonWalletObject[] newArray(int i) {
        return new CommonWalletObject[i];
    }
}
