package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class r implements Parcelable.Creator<q> {
    static void a(q qVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable) qVar.getSession(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, qVar.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) qVar.jH(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bD, reason: merged with bridge method [inline-methods] */
    public q createFromParcel(Parcel parcel) {
        DataSet dataSet;
        Session session;
        int iG;
        DataSet dataSet2 = null;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int i = 0;
        Session session2 = null;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    Session session3 = (Session) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Session.CREATOR);
                    iG = i;
                    dataSet = dataSet2;
                    session = session3;
                    break;
                case 2:
                    dataSet = (DataSet) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DataSet.CREATOR);
                    session = session2;
                    iG = i;
                    break;
                case 1000:
                    DataSet dataSet3 = dataSet2;
                    session = session2;
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    dataSet = dataSet3;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    dataSet = dataSet2;
                    session = session2;
                    iG = i;
                    break;
            }
            i = iG;
            session2 = session;
            dataSet2 = dataSet;
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new q(i, session2, dataSet2);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cX, reason: merged with bridge method [inline-methods] */
    public q[] newArray(int i) {
        return new q[i];
    }
}
