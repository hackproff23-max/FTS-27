package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class p implements Parcelable.Creator<Session> {
    static void a(Session session, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, session.jo());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, session.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, session.jp());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, session.getName(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, session.getIdentifier(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, session.getDescription(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 7, session.jm());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, (Parcelable) session.jx(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bC, reason: merged with bridge method [inline-methods] */
    public Session createFromParcel(Parcel parcel) {
        long jI = 0;
        int iG = 0;
        a aVar = null;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        String strO = null;
        String strO2 = null;
        String strO3 = null;
        long jI2 = 0;
        int iG3 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    jI2 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 2:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 3:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 4:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 5:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 7:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 8:
                    aVar = (a) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, a.CREATOR);
                    break;
                case 1000:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new Session(iG3, jI2, jI, strO3, strO2, strO, iG, aVar);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cW, reason: merged with bridge method [inline-methods] */
    public Session[] newArray(int i) {
        return new Session[i];
    }
}
