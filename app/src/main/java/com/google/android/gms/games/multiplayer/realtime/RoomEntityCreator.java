package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class RoomEntityCreator implements Parcelable.Creator<RoomEntity> {
    static void a(RoomEntity roomEntity, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.a(parcel, 1, roomEntity.getRoomId(), false);
        b.c(parcel, 1000, roomEntity.getVersionCode());
        b.a(parcel, 2, roomEntity.getCreatorId(), false);
        b.a(parcel, 3, roomEntity.getCreationTimestamp());
        b.c(parcel, 4, roomEntity.getStatus());
        b.a(parcel, 5, roomEntity.getDescription(), false);
        b.c(parcel, 6, roomEntity.getVariant());
        b.a(parcel, 7, roomEntity.getAutoMatchCriteria(), false);
        b.c(parcel, 8, roomEntity.getParticipants(), false);
        b.c(parcel, 9, roomEntity.getAutoMatchWaitEstimateSeconds());
        b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cC */
    public RoomEntity createFromParcel(Parcel parcel) {
        int iG = 0;
        ArrayList arrayListC = null;
        int iG2 = a.G(parcel);
        long jI = 0;
        Bundle bundleQ = null;
        int iG3 = 0;
        String strO = null;
        int iG4 = 0;
        String strO2 = null;
        String strO3 = null;
        int iG5 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    strO3 = a.o(parcel, iF);
                    break;
                case 2:
                    strO2 = a.o(parcel, iF);
                    break;
                case 3:
                    jI = a.i(parcel, iF);
                    break;
                case 4:
                    iG4 = a.g(parcel, iF);
                    break;
                case 5:
                    strO = a.o(parcel, iF);
                    break;
                case 6:
                    iG3 = a.g(parcel, iF);
                    break;
                case 7:
                    bundleQ = a.q(parcel, iF);
                    break;
                case 8:
                    arrayListC = a.c(parcel, iF, ParticipantEntity.CREATOR);
                    break;
                case 9:
                    iG = a.g(parcel, iF);
                    break;
                case 1000:
                    iG5 = a.g(parcel, iF);
                    break;
                default:
                    a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new RoomEntity(iG5, strO3, strO2, jI, iG4, strO, iG3, bundleQ, arrayListC, iG);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: eo, reason: merged with bridge method [inline-methods] */
    public RoomEntity[] newArray(int i) {
        return new RoomEntity[i];
    }
}
