package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.q;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class f implements Parcelable.Creator<SessionReadResult> {
    static void a(SessionReadResult sessionReadResult, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, sessionReadResult.getSessions(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, sessionReadResult.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, sessionReadResult.kv(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, (Parcelable) sessionReadResult.getStatus(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: ci, reason: merged with bridge method [inline-methods] */
    public SessionReadResult createFromParcel(Parcel parcel) {
        Status status = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        ArrayList arrayListC = null;
        ArrayList arrayListC2 = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    arrayListC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, Session.CREATOR);
                    break;
                case 2:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, q.CREATOR);
                    break;
                case 3:
                    status = (Status) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Status.CREATOR);
                    break;
                case 1000:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new SessionReadResult(iG2, arrayListC2, arrayListC, status);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dD, reason: merged with bridge method [inline-methods] */
    public SessionReadResult[] newArray(int i) {
        return new SessionReadResult[i];
    }
}
