package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ExtendedGameEntityCreator implements Parcelable.Creator<ExtendedGameEntity> {
    static void a(ExtendedGameEntity extendedGameEntity, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.a(parcel, 1, (Parcelable) extendedGameEntity.getGame(), i, false);
        b.c(parcel, 1000, extendedGameEntity.getVersionCode());
        b.c(parcel, 2, extendedGameEntity.lZ());
        b.a(parcel, 3, extendedGameEntity.ma());
        b.c(parcel, 4, extendedGameEntity.mb());
        b.a(parcel, 5, extendedGameEntity.mc());
        b.a(parcel, 6, extendedGameEntity.md());
        b.a(parcel, 7, extendedGameEntity.me(), false);
        b.a(parcel, 8, extendedGameEntity.mf());
        b.a(parcel, 9, extendedGameEntity.mg(), false);
        b.c(parcel, 10, extendedGameEntity.lY(), false);
        b.a(parcel, 11, (Parcelable) extendedGameEntity.mh(), i, false);
        b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cu */
    public ExtendedGameEntity createFromParcel(Parcel parcel) {
        int iG = a.G(parcel);
        int iG2 = 0;
        GameEntity gameEntity = null;
        int iG3 = 0;
        boolean zC = false;
        int iG4 = 0;
        long jI = 0;
        long jI2 = 0;
        String strO = null;
        long jI3 = 0;
        String strO2 = null;
        ArrayList arrayListC = null;
        SnapshotMetadataEntity snapshotMetadataEntity = null;
        while (parcel.dataPosition() < iG) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    gameEntity = (GameEntity) a.a(parcel, iF, GameEntity.CREATOR);
                    break;
                case 2:
                    iG3 = a.g(parcel, iF);
                    break;
                case 3:
                    zC = a.c(parcel, iF);
                    break;
                case 4:
                    iG4 = a.g(parcel, iF);
                    break;
                case 5:
                    jI = a.i(parcel, iF);
                    break;
                case 6:
                    jI2 = a.i(parcel, iF);
                    break;
                case 7:
                    strO = a.o(parcel, iF);
                    break;
                case 8:
                    jI3 = a.i(parcel, iF);
                    break;
                case 9:
                    strO2 = a.o(parcel, iF);
                    break;
                case 10:
                    arrayListC = a.c(parcel, iF, GameBadgeEntity.CREATOR);
                    break;
                case 11:
                    snapshotMetadataEntity = (SnapshotMetadataEntity) a.a(parcel, iF, SnapshotMetadataEntity.CREATOR);
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
        return new ExtendedGameEntity(iG2, gameEntity, iG3, zC, iG4, jI, jI2, strO, jI3, strO2, arrayListC, snapshotMetadataEntity);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: eb, reason: merged with bridge method [inline-methods] */
    public ExtendedGameEntity[] newArray(int i) {
        return new ExtendedGameEntity[i];
    }
}
