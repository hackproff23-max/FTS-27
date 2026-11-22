package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.location.LocationRequest;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class p implements Parcelable.Creator<o> {
    static void a(o oVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable) oVar.getDataSource(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, oVar.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) oVar.getDataType(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, oVar.kg(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, oVar.VX);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 5, oVar.VY);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, oVar.jI());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, oVar.kd());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, (Parcelable) oVar.kb(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, oVar.kc());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 10, oVar.getAccuracyMode());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 11, oVar.ke(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 12, oVar.kf());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bQ, reason: merged with bridge method [inline-methods] */
    public o createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        DataSource dataSource = null;
        DataType dataType = null;
        IBinder iBinderP = null;
        int iG3 = 0;
        int iG4 = 0;
        long jI = 0;
        long jI2 = 0;
        PendingIntent pendingIntent = null;
        long jI3 = 0;
        int iG5 = 0;
        ArrayList arrayListC = null;
        long jI4 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    dataSource = (DataSource) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DataSource.CREATOR);
                    break;
                case 2:
                    dataType = (DataType) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DataType.CREATOR);
                    break;
                case 3:
                    iBinderP = com.google.android.gms.common.internal.safeparcel.a.p(parcel, iF);
                    break;
                case 4:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 5:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 6:
                    jI = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 7:
                    jI2 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 8:
                    pendingIntent = (PendingIntent) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, PendingIntent.CREATOR);
                    break;
                case 9:
                    jI3 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
                    break;
                case 10:
                    iG5 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 11:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, LocationRequest.CREATOR);
                    break;
                case 12:
                    jI4 = com.google.android.gms.common.internal.safeparcel.a.i(parcel, iF);
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
        return new o(iG2, dataSource, dataType, iBinderP, iG3, iG4, jI, jI2, pendingIntent, jI3, iG5, arrayListC, jI4);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dk, reason: merged with bridge method [inline-methods] */
    public o[] newArray(int i) {
        return new o[i];
    }
}
