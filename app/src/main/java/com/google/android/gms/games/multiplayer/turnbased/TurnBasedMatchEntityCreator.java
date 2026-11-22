package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.millennialmedia.android.MMException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class TurnBasedMatchEntityCreator implements Parcelable.Creator<TurnBasedMatchEntity> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void a(TurnBasedMatchEntity turnBasedMatchEntity, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.a(parcel, 1, (Parcelable) turnBasedMatchEntity.getGame(), i, false);
        b.a(parcel, 2, turnBasedMatchEntity.getMatchId(), false);
        b.a(parcel, 3, turnBasedMatchEntity.getCreatorId(), false);
        b.a(parcel, 4, turnBasedMatchEntity.getCreationTimestamp());
        b.a(parcel, 5, turnBasedMatchEntity.getLastUpdaterId(), false);
        b.a(parcel, 6, turnBasedMatchEntity.getLastUpdatedTimestamp());
        b.a(parcel, 7, turnBasedMatchEntity.getPendingParticipantId(), false);
        b.c(parcel, 8, turnBasedMatchEntity.getStatus());
        b.c(parcel, 10, turnBasedMatchEntity.getVariant());
        b.c(parcel, 11, turnBasedMatchEntity.getVersion());
        b.a(parcel, 12, turnBasedMatchEntity.getData(), false);
        b.c(parcel, 13, turnBasedMatchEntity.getParticipants(), false);
        b.a(parcel, 14, turnBasedMatchEntity.getRematchId(), false);
        b.a(parcel, 15, turnBasedMatchEntity.getPreviousMatchData(), false);
        b.a(parcel, 17, turnBasedMatchEntity.getAutoMatchCriteria(), false);
        b.c(parcel, 16, turnBasedMatchEntity.getMatchNumber());
        b.c(parcel, 1000, turnBasedMatchEntity.getVersionCode());
        b.a(parcel, 19, turnBasedMatchEntity.isLocallyModified());
        b.c(parcel, 18, turnBasedMatchEntity.getTurnStatus());
        b.a(parcel, 21, turnBasedMatchEntity.getDescriptionParticipantId(), false);
        b.a(parcel, 20, turnBasedMatchEntity.getDescription(), false);
        b.H(parcel, iH);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public TurnBasedMatchEntity createFromParcel(Parcel parcel) {
        int iG = a.G(parcel);
        int iG2 = 0;
        GameEntity gameEntity = null;
        String strO = null;
        String strO2 = null;
        long jI = 0;
        String strO3 = null;
        long jI2 = 0;
        String strO4 = null;
        int iG3 = 0;
        int iG4 = 0;
        int iG5 = 0;
        byte[] bArrR = null;
        ArrayList arrayListC = null;
        String strO5 = null;
        byte[] bArrR2 = null;
        int iG6 = 0;
        Bundle bundleQ = null;
        int iG7 = 0;
        boolean zC = false;
        String strO6 = null;
        String strO7 = null;
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
                    strO2 = a.o(parcel, iF);
                    break;
                case 4:
                    jI = a.i(parcel, iF);
                    break;
                case 5:
                    strO3 = a.o(parcel, iF);
                    break;
                case 6:
                    jI2 = a.i(parcel, iF);
                    break;
                case 7:
                    strO4 = a.o(parcel, iF);
                    break;
                case 8:
                    iG3 = a.g(parcel, iF);
                    break;
                case 10:
                    iG4 = a.g(parcel, iF);
                    break;
                case 11:
                    iG5 = a.g(parcel, iF);
                    break;
                case 12:
                    bArrR = a.r(parcel, iF);
                    break;
                case 13:
                    arrayListC = a.c(parcel, iF, ParticipantEntity.CREATOR);
                    break;
                case 14:
                    strO5 = a.o(parcel, iF);
                    break;
                case 15:
                    bArrR2 = a.r(parcel, iF);
                    break;
                case 16:
                    iG6 = a.g(parcel, iF);
                    break;
                case 17:
                    bundleQ = a.q(parcel, iF);
                    break;
                case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                    iG7 = a.g(parcel, iF);
                    break;
                case 19:
                    zC = a.c(parcel, iF);
                    break;
                case 20:
                    strO6 = a.o(parcel, iF);
                    break;
                case MMException.DISPLAY_AD_EXPIRED /* 21 */:
                    strO7 = a.o(parcel, iF);
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
        return new TurnBasedMatchEntity(iG2, gameEntity, strO, strO2, jI, strO3, jI2, strO4, iG3, iG4, iG5, bArrR, arrayListC, strO5, bArrR2, iG6, bundleQ, iG7, zC, strO6, strO7);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public TurnBasedMatchEntity[] newArray(int size) {
        return new TurnBasedMatchEntity[size];
    }
}
