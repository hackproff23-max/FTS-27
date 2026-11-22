package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class s implements Parcelable.Creator<Subscription> {
    static void a(Subscription subscription, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable) subscription.getDataSource(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, subscription.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) subscription.getDataType(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, subscription.jI());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, subscription.getAccuracyMode());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bE, reason: merged with bridge method [inline-methods] */
    public Subscription createFromParcel(Parcel parcel) {
        DataType dataType = null;
        int iG = 0;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        long jI = 0;
        DataSource dataSource = null;
        int iG3 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    dataSource = (DataSource) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DataSource.CREATOR);
                    break;
                case 2:
                    dataType = (DataType) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DataType.CREATOR);
                    break;
                case 3:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 4:
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
        return new Subscription(iG3, dataSource, dataType, jI, iG);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cY, reason: merged with bridge method [inline-methods] */
    public Subscription[] newArray(int i) {
        return new Subscription[i];
    }
}
