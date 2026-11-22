package com.google.android.gms.games.quest;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.GameEntity;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class QuestEntityCreator implements Parcelable.Creator<QuestEntity> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void a(QuestEntity questEntity, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.a(parcel, 1, (Parcelable) questEntity.getGame(), i, false);
        b.a(parcel, 2, questEntity.getQuestId(), false);
        b.a(parcel, 3, questEntity.getAcceptedTimestamp());
        b.a(parcel, 4, (Parcelable) questEntity.getBannerImageUri(), i, false);
        b.a(parcel, 5, questEntity.getBannerImageUrl(), false);
        b.a(parcel, 6, questEntity.getDescription(), false);
        b.a(parcel, 7, questEntity.getEndTimestamp());
        b.a(parcel, 8, questEntity.getLastUpdatedTimestamp());
        b.a(parcel, 9, (Parcelable) questEntity.getIconImageUri(), i, false);
        b.a(parcel, 10, questEntity.getIconImageUrl(), false);
        b.a(parcel, 12, questEntity.getName(), false);
        b.a(parcel, 13, questEntity.mR());
        b.a(parcel, 14, questEntity.getStartTimestamp());
        b.c(parcel, 15, questEntity.getState());
        b.c(parcel, 17, questEntity.mQ(), false);
        b.c(parcel, 16, questEntity.getType());
        b.c(parcel, 1000, questEntity.getVersionCode());
        b.H(parcel, iH);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public QuestEntity createFromParcel(Parcel parcel) {
        int iG = a.G(parcel);
        int iG2 = 0;
        GameEntity gameEntity = null;
        String strO = null;
        long jI = 0;
        Uri uri = null;
        String strO2 = null;
        String strO3 = null;
        long jI2 = 0;
        long jI3 = 0;
        Uri uri2 = null;
        String strO4 = null;
        String strO5 = null;
        long jI4 = 0;
        long jI5 = 0;
        int iG3 = 0;
        int iG4 = 0;
        ArrayList arrayListC = null;
        while (parcel.dataPosition() < iG) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    gameEntity = (GameEntity) a.a(parcel, iF, GameEntity.CREATOR);
                    break;
                case 2:
                    strO = a.o(parcel, iF);
                    break;
                case 3:
                    jI = a.i(parcel, iF);
                    break;
                case 4:
                    uri = (Uri) a.a(parcel, iF, Uri.CREATOR);
                    break;
                case 5:
                    strO2 = a.o(parcel, iF);
                    break;
                case 6:
                    strO3 = a.o(parcel, iF);
                    break;
                case 7:
                    jI2 = a.i(parcel, iF);
                    break;
                case 8:
                    jI3 = a.i(parcel, iF);
                    break;
                case 9:
                    uri2 = (Uri) a.a(parcel, iF, Uri.CREATOR);
                    break;
                case 10:
                    strO4 = a.o(parcel, iF);
                    break;
                case 12:
                    strO5 = a.o(parcel, iF);
                    break;
                case 13:
                    jI4 = a.i(parcel, iF);
                    break;
                case 14:
                    jI5 = a.i(parcel, iF);
                    break;
                case 15:
                    iG3 = a.g(parcel, iF);
                    break;
                case 16:
                    iG4 = a.g(parcel, iF);
                    break;
                case 17:
                    arrayListC = a.c(parcel, iF, MilestoneEntity.CREATOR);
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
        return new QuestEntity(iG2, gameEntity, strO, jI, uri, strO2, strO3, jI2, jI3, uri2, strO4, strO5, jI4, jI5, iG3, iG4, arrayListC);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public QuestEntity[] newArray(int size) {
        return new QuestEntity[size];
    }
}
