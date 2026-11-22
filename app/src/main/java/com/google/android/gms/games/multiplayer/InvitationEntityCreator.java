package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.GameEntity;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class InvitationEntityCreator implements Parcelable.Creator<InvitationEntity> {
    static void a(InvitationEntity invitationEntity, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.a(parcel, 1, (Parcelable) invitationEntity.getGame(), i, false);
        b.c(parcel, 1000, invitationEntity.getVersionCode());
        b.a(parcel, 2, invitationEntity.getInvitationId(), false);
        b.a(parcel, 3, invitationEntity.getCreationTimestamp());
        b.c(parcel, 4, invitationEntity.getInvitationType());
        b.a(parcel, 5, (Parcelable) invitationEntity.getInviter(), i, false);
        b.c(parcel, 6, invitationEntity.getParticipants(), false);
        b.c(parcel, 7, invitationEntity.getVariant());
        b.c(parcel, 8, invitationEntity.getAvailableAutoMatchSlots());
        b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cz */
    public InvitationEntity createFromParcel(Parcel parcel) {
        ArrayList arrayListC = null;
        int iG = 0;
        int iG2 = a.G(parcel);
        long jI = 0;
        int iG3 = 0;
        ParticipantEntity participantEntity = null;
        int iG4 = 0;
        String strO = null;
        GameEntity gameEntity = null;
        int iG5 = 0;
        while (parcel.dataPosition() < iG2) {
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
                    iG4 = a.g(parcel, iF);
                    break;
                case 5:
                    participantEntity = (ParticipantEntity) a.a(parcel, iF, ParticipantEntity.CREATOR);
                    break;
                case 6:
                    arrayListC = a.c(parcel, iF, ParticipantEntity.CREATOR);
                    break;
                case 7:
                    iG3 = a.g(parcel, iF);
                    break;
                case 8:
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
        return new InvitationEntity(iG5, gameEntity, strO, jI, iG4, participantEntity, arrayListC, iG3, iG);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: el, reason: merged with bridge method [inline-methods] */
    public InvitationEntity[] newArray(int i) {
        return new InvitationEntity[i];
    }
}
