package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.Session;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class g implements Parcelable.Creator<SessionStopResult> {
    static void a(SessionStopResult sessionStopResult, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, sessionStopResult.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) sessionStopResult.getStatus(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, sessionStopResult.getSessions(), false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cj, reason: merged with bridge method [inline-methods] */
    public SessionStopResult createFromParcel(Parcel parcel) {
        ArrayList arrayListC;
        Status status;
        int iG;
        ArrayList arrayList = null;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int i = 0;
        Status status2 = null;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 2:
                    Status status3 = (Status) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Status.CREATOR);
                    iG = i;
                    arrayListC = arrayList;
                    status = status3;
                    break;
                case 3:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, Session.CREATOR);
                    status = status2;
                    iG = i;
                    break;
                case 1000:
                    ArrayList arrayList2 = arrayList;
                    status = status2;
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    arrayListC = arrayList2;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    arrayListC = arrayList;
                    status = status2;
                    iG = i;
                    break;
            }
            i = iG;
            status2 = status;
            arrayList = arrayListC;
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new SessionStopResult(i, status2, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dE, reason: merged with bridge method [inline-methods] */
    public SessionStopResult[] newArray(int i) {
        return new SessionStopResult[i];
    }
}
