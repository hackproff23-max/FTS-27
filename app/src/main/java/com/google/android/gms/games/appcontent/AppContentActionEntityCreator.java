package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AppContentActionEntityCreator implements Parcelable.Creator<AppContentActionEntity> {
    static void a(AppContentActionEntity appContentActionEntity, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.c(parcel, 1, appContentActionEntity.kH(), false);
        b.c(parcel, 1000, appContentActionEntity.getVersionCode());
        b.a(parcel, 2, appContentActionEntity.kI(), false);
        b.a(parcel, 3, appContentActionEntity.getExtras(), false);
        b.a(parcel, 4, appContentActionEntity.getLabel(), false);
        b.a(parcel, 5, appContentActionEntity.kJ(), false);
        b.a(parcel, 6, appContentActionEntity.getType(), false);
        b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cn, reason: merged with bridge method [inline-methods] */
    public AppContentActionEntity createFromParcel(Parcel parcel) {
        String strO = null;
        int iG = a.G(parcel);
        int iG2 = 0;
        String strO2 = null;
        String strO3 = null;
        Bundle bundleQ = null;
        String strO4 = null;
        ArrayList arrayListC = null;
        while (parcel.dataPosition() < iG) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    arrayListC = a.c(parcel, iF, AppContentConditionEntity.CREATOR);
                    break;
                case 2:
                    strO4 = a.o(parcel, iF);
                    break;
                case 3:
                    bundleQ = a.q(parcel, iF);
                    break;
                case 4:
                    strO3 = a.o(parcel, iF);
                    break;
                case 5:
                    strO2 = a.o(parcel, iF);
                    break;
                case 6:
                    strO = a.o(parcel, iF);
                    break;
                case 1000:
                    iG2 = a.g(parcel, iF);
                    break;
                default:
                    a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new AppContentActionEntity(iG2, arrayListC, strO4, bundleQ, strO3, strO2, strO);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dI, reason: merged with bridge method [inline-methods] */
    public AppContentActionEntity[] newArray(int i) {
        return new AppContentActionEntity[i];
    }
}
