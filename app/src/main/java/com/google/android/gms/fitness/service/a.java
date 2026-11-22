package com.google.android.gms.fitness.service;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataSource;

/* JADX INFO: loaded from: classes.dex */
public class a implements Parcelable.Creator<FitnessSensorServiceRequest> {
    static void a(FitnessSensorServiceRequest fitnessSensorServiceRequest, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable) fitnessSensorServiceRequest.getDataSource(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, fitnessSensorServiceRequest.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, fitnessSensorServiceRequest.kg(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, fitnessSensorServiceRequest.jI());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, fitnessSensorServiceRequest.kx());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: ck, reason: merged with bridge method [inline-methods] */
    public FitnessSensorServiceRequest createFromParcel(Parcel parcel) {
        long jI = 0;
        IBinder iBinderP = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        long jI2 = 0;
        DataSource dataSource = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    dataSource = (DataSource) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DataSource.CREATOR);
                    break;
                case 2:
                    iBinderP = com.google.android.gms.common.internal.safeparcel.a.p(parcel, iF);
                    break;
                case 3:
                    jI2 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 4:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
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
        return new FitnessSensorServiceRequest(iG2, dataSource, iBinderP, jI2, jI);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dF, reason: merged with bridge method [inline-methods] */
    public FitnessSensorServiceRequest[] newArray(int i) {
        return new FitnessSensorServiceRequest[i];
    }
}
