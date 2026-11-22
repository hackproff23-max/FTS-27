package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

/* JADX INFO: loaded from: classes.dex */
public class PlayerLevelInfoCreator implements Parcelable.Creator<PlayerLevelInfo> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void a(PlayerLevelInfo playerLevelInfo, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.a(parcel, 1, playerLevelInfo.getCurrentXpTotal());
        b.c(parcel, 1000, playerLevelInfo.getVersionCode());
        b.a(parcel, 2, playerLevelInfo.getLastLevelUpTimestamp());
        b.a(parcel, 3, (Parcelable) playerLevelInfo.getCurrentLevel(), i, false);
        b.a(parcel, 4, (Parcelable) playerLevelInfo.getNextLevel(), i, false);
        b.H(parcel, iH);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public PlayerLevelInfo createFromParcel(Parcel parcel) {
        long jI = 0;
        PlayerLevel playerLevel = null;
        int iG = a.G(parcel);
        int iG2 = 0;
        PlayerLevel playerLevel2 = null;
        long jI2 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    jI2 = a.i(parcel, iF);
                    break;
                case 2:
                    jI = a.i(parcel, iF);
                    break;
                case 3:
                    playerLevel2 = (PlayerLevel) a.a(parcel, iF, PlayerLevel.CREATOR);
                    break;
                case 4:
                    playerLevel = (PlayerLevel) a.a(parcel, iF, PlayerLevel.CREATOR);
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
        return new PlayerLevelInfo(iG2, jI2, jI, playerLevel2, playerLevel);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public PlayerLevelInfo[] newArray(int size) {
        return new PlayerLevelInfo[size];
    }
}
