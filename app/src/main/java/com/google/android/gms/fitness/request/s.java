package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.Session;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class s implements Parcelable.Creator<SessionInsertRequest> {
    static void a(SessionInsertRequest sessionInsertRequest, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable) sessionInsertRequest.getSession(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, sessionInsertRequest.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, sessionInsertRequest.getDataSets(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, sessionInsertRequest.getAggregateDataPoints(), false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bS, reason: merged with bridge method [inline-methods] */
    public SessionInsertRequest createFromParcel(Parcel parcel) {
        ArrayList arrayListC;
        ArrayList arrayListC2;
        Session session;
        int iG;
        ArrayList arrayList = null;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int i = 0;
        ArrayList arrayList2 = null;
        Session session2 = null;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG = i;
                    ArrayList arrayList3 = arrayList2;
                    session = (Session) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Session.CREATOR);
                    arrayListC = arrayList;
                    arrayListC2 = arrayList3;
                    break;
                case 2:
                    session = session2;
                    iG = i;
                    ArrayList arrayList4 = arrayList;
                    arrayListC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, DataSet.CREATOR);
                    arrayListC = arrayList4;
                    break;
                case 3:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, DataPoint.CREATOR);
                    arrayListC2 = arrayList2;
                    session = session2;
                    iG = i;
                    break;
                case 1000:
                    ArrayList arrayList5 = arrayList;
                    arrayListC2 = arrayList2;
                    session = session2;
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    arrayListC = arrayList5;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    arrayListC = arrayList;
                    arrayListC2 = arrayList2;
                    session = session2;
                    iG = i;
                    break;
            }
            i = iG;
            session2 = session;
            arrayList2 = arrayListC2;
            arrayList = arrayListC;
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new SessionInsertRequest(i, session2, arrayList2, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dn, reason: merged with bridge method [inline-methods] */
    public SessionInsertRequest[] newArray(int i) {
        return new SessionInsertRequest[i];
    }
}
