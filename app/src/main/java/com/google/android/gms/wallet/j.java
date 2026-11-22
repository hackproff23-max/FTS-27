package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.la;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.p;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.millennialmedia.android.MMException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class j implements Parcelable.Creator<LoyaltyWalletObject> {
    static void a(LoyaltyWalletObject loyaltyWalletObject, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, loyaltyWalletObject.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, loyaltyWalletObject.fl, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, loyaltyWalletObject.avd, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, loyaltyWalletObject.ave, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, loyaltyWalletObject.avf, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, loyaltyWalletObject.aki, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, loyaltyWalletObject.avg, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, loyaltyWalletObject.avh, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, loyaltyWalletObject.avi, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, loyaltyWalletObject.avj, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, loyaltyWalletObject.avk, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 12, loyaltyWalletObject.state);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 13, loyaltyWalletObject.avl, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 14, (Parcelable) loyaltyWalletObject.avm, i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 15, loyaltyWalletObject.avn, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 17, loyaltyWalletObject.avp, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 16, loyaltyWalletObject.avo, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 19, loyaltyWalletObject.avr);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 18, loyaltyWalletObject.avq, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 21, loyaltyWalletObject.avt, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 20, loyaltyWalletObject.avs, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 23, (Parcelable) loyaltyWalletObject.avv, i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 22, loyaltyWalletObject.avu, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dR, reason: merged with bridge method [inline-methods] */
    public LoyaltyWalletObject createFromParcel(Parcel parcel) {
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
        String strO9 = null;
        String strO10 = null;
        int iG3 = 0;
        ArrayList arrayListIe = la.ie();
        com.google.android.gms.wallet.wobs.l lVar = null;
        ArrayList arrayListIe2 = la.ie();
        String strO11 = null;
        String strO12 = null;
        ArrayList arrayListIe3 = la.ie();
        boolean zC = false;
        ArrayList arrayListIe4 = la.ie();
        ArrayList arrayListIe5 = la.ie();
        ArrayList arrayListIe6 = la.ie();
        com.google.android.gms.wallet.wobs.f fVar = null;
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
                    strO9 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 11:
                    strO10 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 12:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 13:
                    arrayListIe = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, p.CREATOR);
                    break;
                case 14:
                    lVar = (com.google.android.gms.wallet.wobs.l) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, com.google.android.gms.wallet.wobs.l.CREATOR);
                    break;
                case 15:
                    arrayListIe2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, LatLng.CREATOR);
                    break;
                case 16:
                    strO11 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 17:
                    strO12 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                    arrayListIe3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, com.google.android.gms.wallet.wobs.d.CREATOR);
                    break;
                case 19:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 20:
                    arrayListIe4 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, com.google.android.gms.wallet.wobs.n.CREATOR);
                    break;
                case MMException.DISPLAY_AD_EXPIRED /* 21 */:
                    arrayListIe5 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, com.google.android.gms.wallet.wobs.j.CREATOR);
                    break;
                case MMException.DISPLAY_AD_NOT_FOUND /* 22 */:
                    arrayListIe6 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, com.google.android.gms.wallet.wobs.n.CREATOR);
                    break;
                case MMException.DISPLAY_AD_ALREADY_DISPLAYED /* 23 */:
                    fVar = (com.google.android.gms.wallet.wobs.f) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, com.google.android.gms.wallet.wobs.f.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new LoyaltyWalletObject(iG2, strO, strO2, strO3, strO4, strO5, strO6, strO7, strO8, strO9, strO10, iG3, arrayListIe, lVar, arrayListIe2, strO11, strO12, arrayListIe3, zC, arrayListIe4, arrayListIe5, arrayListIe6, fVar);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: fY, reason: merged with bridge method [inline-methods] */
    public LoyaltyWalletObject[] newArray(int i) {
        return new LoyaltyWalletObject[i];
    }
}
