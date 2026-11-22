package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

/* JADX INFO: loaded from: classes.dex */
public class AppContentConditionEntityCreator implements Parcelable.Creator<AppContentConditionEntity> {
    static void a(AppContentConditionEntity appContentConditionEntity, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.a(parcel, 1, appContentConditionEntity.kT(), false);
        b.c(parcel, 1000, appContentConditionEntity.getVersionCode());
        b.a(parcel, 2, appContentConditionEntity.kU(), false);
        b.a(parcel, 3, appContentConditionEntity.kV(), false);
        b.a(parcel, 4, appContentConditionEntity.kW(), false);
        b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cq, reason: merged with bridge method [inline-methods] */
    public AppContentConditionEntity createFromParcel(Parcel parcel) {
        Bundle bundleQ = null;
        int iG = a.G(parcel);
        int iG2 = 0;
        String strO = null;
        String strO2 = null;
        String strO3 = null;
        while (parcel.dataPosition() < iG) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    strO3 = a.o(parcel, iF);
                    break;
                case 2:
                    strO2 = a.o(parcel, iF);
                    break;
                case 3:
                    strO = a.o(parcel, iF);
                    break;
                case 4:
                    bundleQ = a.q(parcel, iF);
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
        return new AppContentConditionEntity(iG2, strO3, strO2, strO, bundleQ);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dL, reason: merged with bridge method [inline-methods] */
    public AppContentConditionEntity[] newArray(int i) {
        return new AppContentConditionEntity[i];
    }
}
