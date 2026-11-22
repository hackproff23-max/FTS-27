package com.google.android.gms.games.quest;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

/* JADX INFO: loaded from: classes.dex */
public class MilestoneEntityCreator implements Parcelable.Creator<MilestoneEntity> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void a(MilestoneEntity milestoneEntity, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.a(parcel, 1, milestoneEntity.getMilestoneId(), false);
        b.c(parcel, 1000, milestoneEntity.getVersionCode());
        b.a(parcel, 2, milestoneEntity.getCurrentProgress());
        b.a(parcel, 3, milestoneEntity.getTargetProgress());
        b.a(parcel, 4, milestoneEntity.getCompletionRewardData(), false);
        b.c(parcel, 5, milestoneEntity.getState());
        b.a(parcel, 6, milestoneEntity.getEventId(), false);
        b.H(parcel, iH);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public MilestoneEntity createFromParcel(Parcel parcel) {
        long jI = 0;
        int iG = 0;
        String strO = null;
        int iG2 = a.G(parcel);
        byte[] bArrR = null;
        long jI2 = 0;
        String strO2 = null;
        int iG3 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    strO2 = a.o(parcel, iF);
                    break;
                case 2:
                    jI2 = a.i(parcel, iF);
                    break;
                case 3:
                    jI = a.i(parcel, iF);
                    break;
                case 4:
                    bArrR = a.r(parcel, iF);
                    break;
                case 5:
                    iG = a.g(parcel, iF);
                    break;
                case 6:
                    strO = a.o(parcel, iF);
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
        return new MilestoneEntity(iG3, strO2, jI2, jI, bArrR, iG, strO);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public MilestoneEntity[] newArray(int size) {
        return new MilestoneEntity[size];
    }
}
