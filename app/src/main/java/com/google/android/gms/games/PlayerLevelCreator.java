package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

/* JADX INFO: loaded from: classes.dex */
public class PlayerLevelCreator implements Parcelable.Creator<PlayerLevel> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void a(PlayerLevel playerLevel, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.c(parcel, 1, playerLevel.getLevelNumber());
        b.c(parcel, 1000, playerLevel.getVersionCode());
        b.a(parcel, 2, playerLevel.getMinXp());
        b.a(parcel, 3, playerLevel.getMaxXp());
        b.H(parcel, iH);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public PlayerLevel createFromParcel(Parcel parcel) {
        long jI = 0;
        int iG = 0;
        int iG2 = a.G(parcel);
        long jI2 = 0;
        int iG3 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    iG = a.g(parcel, iF);
                    break;
                case 2:
                    jI2 = a.i(parcel, iF);
                    break;
                case 3:
                    jI = a.i(parcel, iF);
                    break;
                case 1000:
                    iG3 = a.g(parcel, iF);
                    break;
                default:
                    a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new PlayerLevel(iG3, iG, jI2, jI);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public PlayerLevel[] newArray(int size) {
        return new PlayerLevel[size];
    }
}
