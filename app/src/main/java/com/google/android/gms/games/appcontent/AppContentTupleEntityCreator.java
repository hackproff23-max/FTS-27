package com.google.android.gms.games.appcontent;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

/* JADX INFO: loaded from: classes.dex */
public class AppContentTupleEntityCreator implements Parcelable.Creator<AppContentTupleEntity> {
    static void a(AppContentTupleEntity appContentTupleEntity, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.a(parcel, 1, appContentTupleEntity.getName(), false);
        b.c(parcel, 1000, appContentTupleEntity.getVersionCode());
        b.a(parcel, 2, appContentTupleEntity.getValue(), false);
        b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cs, reason: merged with bridge method [inline-methods] */
    public AppContentTupleEntity createFromParcel(Parcel parcel) {
        String strO = null;
        int iG = a.G(parcel);
        int iG2 = 0;
        String strO2 = null;
        while (parcel.dataPosition() < iG) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    strO2 = a.o(parcel, iF);
                    break;
                case 2:
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
        return new AppContentTupleEntity(iG2, strO2, strO);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dN, reason: merged with bridge method [inline-methods] */
    public AppContentTupleEntity[] newArray(int i) {
        return new AppContentTupleEntity[i];
    }
}
