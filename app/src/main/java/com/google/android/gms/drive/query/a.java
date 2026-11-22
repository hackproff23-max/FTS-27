package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class a implements Parcelable.Creator<Query> {
    static void a(Query query, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, query.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable) query.RY, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, query.RZ, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, (Parcelable) query.Sa, i, false);
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 5, query.Sb, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: aQ, reason: merged with bridge method [inline-methods] */
    public Query createFromParcel(Parcel parcel) {
        ArrayList<String> arrayListC = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        SortOrder sortOrder = null;
        String strO = null;
        LogicalFilter logicalFilter = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    logicalFilter = (LogicalFilter) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, LogicalFilter.CREATOR);
                    break;
                case 3:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 4:
                    sortOrder = (SortOrder) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, SortOrder.CREATOR);
                    break;
                case 5:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.C(parcel, iF);
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
        return new Query(iG2, logicalFilter, strO, sortOrder, arrayListC);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cf, reason: merged with bridge method [inline-methods] */
    public Query[] newArray(int i) {
        return new Query[i];
    }
}
