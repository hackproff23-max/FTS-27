package com.google.android.gms.games.multiplayer;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.PlayerEntity;

/* JADX INFO: loaded from: classes.dex */
public class ParticipantEntityCreator implements Parcelable.Creator<ParticipantEntity> {
    static void a(ParticipantEntity participantEntity, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.a(parcel, 1, participantEntity.getParticipantId(), false);
        b.c(parcel, 1000, participantEntity.getVersionCode());
        b.a(parcel, 2, participantEntity.getDisplayName(), false);
        b.a(parcel, 3, (Parcelable) participantEntity.getIconImageUri(), i, false);
        b.a(parcel, 4, (Parcelable) participantEntity.getHiResImageUri(), i, false);
        b.c(parcel, 5, participantEntity.getStatus());
        b.a(parcel, 6, participantEntity.le(), false);
        b.a(parcel, 7, participantEntity.isConnectedToRoom());
        b.a(parcel, 8, (Parcelable) participantEntity.getPlayer(), i, false);
        b.c(parcel, 9, participantEntity.getCapabilities());
        b.a(parcel, 10, (Parcelable) participantEntity.getResult(), i, false);
        b.a(parcel, 11, participantEntity.getIconImageUrl(), false);
        b.a(parcel, 12, participantEntity.getHiResImageUrl(), false);
        b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cA */
    public ParticipantEntity createFromParcel(Parcel parcel) {
        int iG = a.G(parcel);
        int iG2 = 0;
        String strO = null;
        String strO2 = null;
        Uri uri = null;
        Uri uri2 = null;
        int iG3 = 0;
        String strO3 = null;
        boolean zC = false;
        PlayerEntity playerEntity = null;
        int iG4 = 0;
        ParticipantResult participantResult = null;
        String strO4 = null;
        String strO5 = null;
        while (parcel.dataPosition() < iG) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    strO = a.o(parcel, iF);
                    break;
                case 2:
                    strO2 = a.o(parcel, iF);
                    break;
                case 3:
                    uri = (Uri) a.a(parcel, iF, Uri.CREATOR);
                    break;
                case 4:
                    uri2 = (Uri) a.a(parcel, iF, Uri.CREATOR);
                    break;
                case 5:
                    iG3 = a.g(parcel, iF);
                    break;
                case 6:
                    strO3 = a.o(parcel, iF);
                    break;
                case 7:
                    zC = a.c(parcel, iF);
                    break;
                case 8:
                    playerEntity = (PlayerEntity) a.a(parcel, iF, PlayerEntity.CREATOR);
                    break;
                case 9:
                    iG4 = a.g(parcel, iF);
                    break;
                case 10:
                    participantResult = (ParticipantResult) a.a(parcel, iF, ParticipantResult.CREATOR);
                    break;
                case 11:
                    strO4 = a.o(parcel, iF);
                    break;
                case 12:
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
        return new ParticipantEntity(iG2, strO, strO2, uri, uri2, iG3, strO3, zC, playerEntity, iG4, participantResult, strO4, strO5);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: em, reason: merged with bridge method [inline-methods] */
    public ParticipantEntity[] newArray(int i) {
        return new ParticipantEntity[i];
    }
}
