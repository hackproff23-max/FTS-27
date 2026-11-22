package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

/* JADX INFO: loaded from: classes.dex */
public class MostRecentGameInfoEntityCreator implements Parcelable.Creator<MostRecentGameInfoEntity> {
    static void a(MostRecentGameInfoEntity mostRecentGameInfoEntity, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.a(parcel, 1, mostRecentGameInfoEntity.mw(), false);
        b.c(parcel, 1000, mostRecentGameInfoEntity.getVersionCode());
        b.a(parcel, 2, mostRecentGameInfoEntity.mx(), false);
        b.a(parcel, 3, mostRecentGameInfoEntity.my());
        b.a(parcel, 4, (Parcelable) mostRecentGameInfoEntity.mz(), i, false);
        b.a(parcel, 5, (Parcelable) mostRecentGameInfoEntity.mA(), i, false);
        b.a(parcel, 6, (Parcelable) mostRecentGameInfoEntity.mB(), i, false);
        b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cx, reason: merged with bridge method [inline-methods] */
    public MostRecentGameInfoEntity createFromParcel(Parcel parcel) {
        Uri uri = null;
        int iG = a.G(parcel);
        int iG2 = 0;
        long jI = 0;
        Uri uri2 = null;
        Uri uri3 = null;
        String strO = null;
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
                case 3:
                    jI = a.i(parcel, iF);
                    break;
                case 4:
                    uri3 = (Uri) a.a(parcel, iF, Uri.CREATOR);
                    break;
                case 5:
                    uri2 = (Uri) a.a(parcel, iF, Uri.CREATOR);
                    break;
                case 6:
                    uri = (Uri) a.a(parcel, iF, Uri.CREATOR);
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
        return new MostRecentGameInfoEntity(iG2, strO2, strO, jI, uri3, uri2, uri);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: ei, reason: merged with bridge method [inline-methods] */
    public MostRecentGameInfoEntity[] newArray(int i) {
        return new MostRecentGameInfoEntity[i];
    }
}
