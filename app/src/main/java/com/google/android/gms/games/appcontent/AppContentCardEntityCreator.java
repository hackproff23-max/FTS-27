package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AppContentCardEntityCreator implements Parcelable.Creator<AppContentCardEntity> {
    static void a(AppContentCardEntity appContentCardEntity, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.c(parcel, 1, appContentCardEntity.getActions(), false);
        b.c(parcel, 1000, appContentCardEntity.getVersionCode());
        b.c(parcel, 2, appContentCardEntity.kN(), false);
        b.c(parcel, 3, appContentCardEntity.kH(), false);
        b.a(parcel, 4, appContentCardEntity.kI(), false);
        b.c(parcel, 5, appContentCardEntity.kO());
        b.a(parcel, 6, appContentCardEntity.getDescription(), false);
        b.a(parcel, 7, appContentCardEntity.kP(), false);
        b.a(parcel, 8, (Parcelable) appContentCardEntity.getIconImageUri(), i, false);
        b.a(parcel, 9, (Parcelable) appContentCardEntity.kL(), i, false);
        b.a(parcel, 10, appContentCardEntity.kQ(), false);
        b.a(parcel, 11, appContentCardEntity.getTitle(), false);
        b.c(parcel, 12, appContentCardEntity.kR());
        b.a(parcel, 13, appContentCardEntity.getType(), false);
        b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cp, reason: merged with bridge method [inline-methods] */
    public AppContentCardEntity createFromParcel(Parcel parcel) {
        int iG = a.G(parcel);
        int iG2 = 0;
        ArrayList arrayListC = null;
        ArrayList arrayListC2 = null;
        ArrayList arrayListC3 = null;
        String strO = null;
        int iG3 = 0;
        String strO2 = null;
        Bundle bundleQ = null;
        Uri uri = null;
        Uri uri2 = null;
        String strO3 = null;
        String strO4 = null;
        int iG4 = 0;
        String strO5 = null;
        while (parcel.dataPosition() < iG) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    arrayListC = a.c(parcel, iF, AppContentActionEntity.CREATOR);
                    break;
                case 2:
                    arrayListC2 = a.c(parcel, iF, AppContentAnnotationEntity.CREATOR);
                    break;
                case 3:
                    arrayListC3 = a.c(parcel, iF, AppContentConditionEntity.CREATOR);
                    break;
                case 4:
                    strO = a.o(parcel, iF);
                    break;
                case 5:
                    iG3 = a.g(parcel, iF);
                    break;
                case 6:
                    strO2 = a.o(parcel, iF);
                    break;
                case 7:
                    bundleQ = a.q(parcel, iF);
                    break;
                case 8:
                    uri = (Uri) a.a(parcel, iF, Uri.CREATOR);
                    break;
                case 9:
                    uri2 = (Uri) a.a(parcel, iF, Uri.CREATOR);
                    break;
                case 10:
                    strO3 = a.o(parcel, iF);
                    break;
                case 11:
                    strO4 = a.o(parcel, iF);
                    break;
                case 12:
                    iG4 = a.g(parcel, iF);
                    break;
                case 13:
                    strO5 = a.o(parcel, iF);
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
        return new AppContentCardEntity(iG2, arrayListC, arrayListC2, arrayListC3, strO, iG3, strO2, bundleQ, uri, uri2, strO3, strO4, iG4, strO5);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dK, reason: merged with bridge method [inline-methods] */
    public AppContentCardEntity[] newArray(int i) {
        return new AppContentCardEntity[i];
    }
}
