package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ac implements Parcelable.Creator<StartBleScanRequest> {
    static void a(StartBleScanRequest startBleScanRequest, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, startBleScanRequest.getDataTypes(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, startBleScanRequest.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, startBleScanRequest.km(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, startBleScanRequest.getTimeoutSecs());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bY, reason: merged with bridge method [inline-methods] */
    public StartBleScanRequest createFromParcel(Parcel parcel) {
        IBinder iBinderP = null;
        int iG = 0;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        ArrayList arrayListC = null;
        int iG3 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, DataType.CREATOR);
                    break;
                case 2:
                    iBinderP = com.google.android.gms.common.internal.safeparcel.a.p(parcel, iF);
                    break;
                case 3:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
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
        return new StartBleScanRequest(iG3, arrayListC, iBinderP, iG);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dt, reason: merged with bridge method [inline-methods] */
    public StartBleScanRequest[] newArray(int i) {
        return new StartBleScanRequest[i];
    }
}
