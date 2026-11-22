package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

/* JADX INFO: loaded from: classes.dex */
public class ParticipantResultCreator implements Parcelable.Creator<ParticipantResult> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void a(ParticipantResult participantResult, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.a(parcel, 1, participantResult.getParticipantId(), false);
        b.c(parcel, 1000, participantResult.getVersionCode());
        b.c(parcel, 2, participantResult.getResult());
        b.c(parcel, 3, participantResult.getPlacing());
        b.H(parcel, iH);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public ParticipantResult createFromParcel(Parcel parcel) {
        int iG = 0;
        int iG2 = a.G(parcel);
        String strO = null;
        int iG3 = 0;
        int iG4 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    strO = a.o(parcel, iF);
                    break;
                case 2:
                    iG3 = a.g(parcel, iF);
                    break;
                case 3:
                    iG = a.g(parcel, iF);
                    break;
                case 1000:
                    iG4 = a.g(parcel, iF);
                    break;
                default:
                    a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new ParticipantResult(iG4, strO, iG3, iG);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public ParticipantResult[] newArray(int size) {
        return new ParticipantResult[size];
    }
}
