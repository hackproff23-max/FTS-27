package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AppContentSectionEntityCreator implements Parcelable.Creator<AppContentSectionEntity> {
    static void a(AppContentSectionEntity appContentSectionEntity, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.c(parcel, 1, appContentSectionEntity.getActions(), false);
        b.c(parcel, 1000, appContentSectionEntity.getVersionCode());
        b.a(parcel, 2, (Parcelable) appContentSectionEntity.kY(), i, false);
        b.c(parcel, 3, appContentSectionEntity.kZ(), false);
        b.a(parcel, 4, appContentSectionEntity.kI(), false);
        b.a(parcel, 5, appContentSectionEntity.kP(), false);
        b.a(parcel, 6, appContentSectionEntity.kQ(), false);
        b.a(parcel, 7, appContentSectionEntity.getTitle(), false);
        b.a(parcel, 8, appContentSectionEntity.getType(), false);
        b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cr, reason: merged with bridge method [inline-methods] */
    public AppContentSectionEntity createFromParcel(Parcel parcel) {
        String strO = null;
        int iG = a.G(parcel);
        int iG2 = 0;
        String strO2 = null;
        String strO3 = null;
        Bundle bundleQ = null;
        String strO4 = null;
        ArrayList arrayListC = null;
        Uri uri = null;
        ArrayList arrayListC2 = null;
        while (parcel.dataPosition() < iG) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    arrayListC2 = a.c(parcel, iF, AppContentActionEntity.CREATOR);
                    break;
                case 2:
                    uri = (Uri) a.a(parcel, iF, Uri.CREATOR);
                    break;
                case 3:
                    arrayListC = a.c(parcel, iF, AppContentCardEntity.CREATOR);
                    break;
                case 4:
                    strO4 = a.o(parcel, iF);
                    break;
                case 5:
                    bundleQ = a.q(parcel, iF);
                    break;
                case 6:
                    strO3 = a.o(parcel, iF);
                    break;
                case 7:
                    strO2 = a.o(parcel, iF);
                    break;
                case 8:
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
        return new AppContentSectionEntity(iG2, arrayListC2, uri, arrayListC, strO4, bundleQ, strO3, strO2, strO);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dM, reason: merged with bridge method [inline-methods] */
    public AppContentSectionEntity[] newArray(int i) {
        return new AppContentSectionEntity[i];
    }
}
