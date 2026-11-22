package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.PlayerEntity;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class GameRequestEntityCreator implements Parcelable.Creator<GameRequestEntity> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void a(GameRequestEntity gameRequestEntity, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.a(parcel, 1, (Parcelable) gameRequestEntity.getGame(), i, false);
        b.c(parcel, 1000, gameRequestEntity.getVersionCode());
        b.a(parcel, 2, (Parcelable) gameRequestEntity.getSender(), i, false);
        b.a(parcel, 3, gameRequestEntity.getData(), false);
        b.a(parcel, 4, gameRequestEntity.getRequestId(), false);
        b.c(parcel, 5, gameRequestEntity.getRecipients(), false);
        b.c(parcel, 7, gameRequestEntity.getType());
        b.a(parcel, 9, gameRequestEntity.getCreationTimestamp());
        b.a(parcel, 10, gameRequestEntity.getExpirationTimestamp());
        b.a(parcel, 11, gameRequestEntity.mS(), false);
        b.c(parcel, 12, gameRequestEntity.getStatus());
        b.H(parcel, iH);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public GameRequestEntity createFromParcel(Parcel parcel) {
        int iG = a.G(parcel);
        int iG2 = 0;
        GameEntity gameEntity = null;
        PlayerEntity playerEntity = null;
        byte[] bArrR = null;
        String strO = null;
        ArrayList arrayListC = null;
        int iG3 = 0;
        long jI = 0;
        long jI2 = 0;
        Bundle bundleQ = null;
        int iG4 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    gameEntity = (GameEntity) a.a(parcel, iF, GameEntity.CREATOR);
                    break;
                case 2:
                    playerEntity = (PlayerEntity) a.a(parcel, iF, PlayerEntity.CREATOR);
                    break;
                case 3:
                    bArrR = a.r(parcel, iF);
                    break;
                case 4:
                    strO = a.o(parcel, iF);
                    break;
                case 5:
                    arrayListC = a.c(parcel, iF, PlayerEntity.CREATOR);
                    break;
                case 7:
                    iG3 = a.g(parcel, iF);
                    break;
                case 9:
                    jI = a.i(parcel, iF);
                    break;
                case 10:
                    jI2 = a.i(parcel, iF);
                    break;
                case 11:
                    bundleQ = a.q(parcel, iF);
                    break;
                case 12:
                    iG4 = a.g(parcel, iF);
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
        return new GameRequestEntity(iG2, gameEntity, playerEntity, bArrR, strO, arrayListC, iG3, jI, jI2, bundleQ, iG4);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public GameRequestEntity[] newArray(int size) {
        return new GameRequestEntity[size];
    }
}
