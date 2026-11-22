package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

/* JADX INFO: loaded from: classes.dex */
public class AppContentAnnotationEntityCreator implements Parcelable.Creator<AppContentAnnotationEntity> {
    static void a(AppContentAnnotationEntity appContentAnnotationEntity, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.a(parcel, 1, appContentAnnotationEntity.getDescription(), false);
        b.c(parcel, 1000, appContentAnnotationEntity.getVersionCode());
        b.a(parcel, 2, (Parcelable) appContentAnnotationEntity.kL(), i, false);
        b.a(parcel, 3, appContentAnnotationEntity.getTitle(), false);
        b.a(parcel, 4, appContentAnnotationEntity.getType(), false);
        b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: co, reason: merged with bridge method [inline-methods] */
    public AppContentAnnotationEntity createFromParcel(Parcel parcel) {
        String strO = null;
        int iG = a.G(parcel);
        int iG2 = 0;
        String strO2 = null;
        Uri uri = null;
        String strO3 = null;
        while (parcel.dataPosition() < iG) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    strO3 = a.o(parcel, iF);
                    break;
                case 2:
                    uri = (Uri) a.a(parcel, iF, Uri.CREATOR);
                    break;
                case 3:
                    strO2 = a.o(parcel, iF);
                    break;
                case 4:
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
        return new AppContentAnnotationEntity(iG2, strO3, uri, strO2, strO);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dJ, reason: merged with bridge method [inline-methods] */
    public AppContentAnnotationEntity[] newArray(int i) {
        return new AppContentAnnotationEntity[i];
    }
}
