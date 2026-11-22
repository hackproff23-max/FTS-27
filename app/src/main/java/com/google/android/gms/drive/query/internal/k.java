package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class k implements Parcelable.Creator<NotFilter> {
    static void a(NotFilter notFilter, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, notFilter.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable) notFilter.Sv, i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: ba, reason: merged with bridge method [inline-methods] */
    public NotFilter createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        FilterHolder filterHolder = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    filterHolder = (FilterHolder) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, FilterHolder.CREATOR);
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
        return new NotFilter(iG2, filterHolder);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cp, reason: merged with bridge method [inline-methods] */
    public NotFilter[] newArray(int i) {
        return new NotFilter[i];
    }
}
