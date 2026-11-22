package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class hn implements Parcelable.Creator<hl.b> {
    static void a(hl.b bVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, bVar.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable) bVar.Dd, i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, bVar.De, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: O, reason: merged with bridge method [inline-methods] */
    public hl.b[] newArray(int i) {
        return new hl.b[i];
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public hl.b createFromParcel(Parcel parcel) {
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
                case 1:
                    Status status3 = (Status) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Status.CREATOR);
                    iG = i;
                    arrayListC = arrayList;
                    status = status3;
                    break;
                case 2:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF, hr.CREATOR);
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
        return new hl.b(i, status2, arrayList);
    }
}
