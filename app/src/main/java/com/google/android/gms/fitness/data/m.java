package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class m implements Parcelable.Creator<RawBucket> {
    static void a(RawBucket rawBucket, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, rawBucket.LW);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, rawBucket.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, rawBucket.TO);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, (Parcelable) rawBucket.TQ, i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, rawBucket.UI);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 5, rawBucket.Ua, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 6, rawBucket.Ub);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, rawBucket.Uc);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bz, reason: merged with bridge method [inline-methods] */
    public RawBucket createFromParcel(Parcel parcel) {
        long jI = 0;
        ArrayList arrayListC = null;
        boolean zC = false;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        int iG3 = 0;
        Session session = null;
        long jI2 = 0;
        int iG4 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    jI2 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 2:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 3:
                    session = (Session) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Session.CREATOR);
                    break;
                case 4:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 5:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, RawDataSet.CREATOR);
                    break;
                case 6:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 7:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 1000:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new RawBucket(iG4, jI2, jI, session, iG3, arrayListC, iG2, zC);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cS, reason: merged with bridge method [inline-methods] */
    public RawBucket[] newArray(int i) {
        return new RawBucket[i];
    }
}
