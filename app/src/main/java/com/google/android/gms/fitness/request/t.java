package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class t implements Parcelable.Creator<SessionReadRequest> {
    static void a(SessionReadRequest sessionReadRequest, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, sessionReadRequest.getSessionName(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, sessionReadRequest.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, sessionReadRequest.getSessionId(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, sessionReadRequest.jo());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, sessionReadRequest.jp());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 5, sessionReadRequest.getDataTypes(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 6, sessionReadRequest.getDataSources(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, sessionReadRequest.kj());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, sessionReadRequest.jV());
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 9, sessionReadRequest.getExcludedPackages(), false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bT, reason: merged with bridge method [inline-methods] */
    public SessionReadRequest createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        String strO = null;
        String strO2 = null;
        long jI = 0;
        long jI2 = 0;
        ArrayList arrayListC = null;
        ArrayList arrayListC2 = null;
        boolean zC = false;
        boolean zC2 = false;
        ArrayList<String> arrayListC3 = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 2:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 3:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 4:
                    jI2 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 5:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, DataType.CREATOR);
                    break;
                case 6:
                    arrayListC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, DataSource.CREATOR);
                    break;
                case 7:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 8:
                    zC2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 9:
                    arrayListC3 = com.google.android.gms.common.internal.safeparcel.a.C(parcel, iF);
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
        return new SessionReadRequest(iG2, strO, strO2, jI, jI2, arrayListC, arrayListC2, zC, zC2, arrayListC3);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: do, reason: not valid java name and merged with bridge method [inline-methods] */
    public SessionReadRequest[] newArray(int i) {
        return new SessionReadRequest[i];
    }
}
