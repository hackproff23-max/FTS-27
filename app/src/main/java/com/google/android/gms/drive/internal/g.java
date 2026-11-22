package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.Contents;

/* JADX INFO: loaded from: classes.dex */
public class g implements Parcelable.Creator<CloseContentsRequest> {
    static void a(CloseContentsRequest closeContentsRequest, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, closeContentsRequest.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) closeContentsRequest.Pr, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, closeContentsRequest.Pt, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: ae, reason: merged with bridge method [inline-methods] */
    public CloseContentsRequest createFromParcel(Parcel parcel) {
        Boolean boolD;
        Contents contents;
        int iG;
        Boolean bool = null;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int i = 0;
        Contents contents2 = null;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    Boolean bool2 = bool;
                    contents = contents2;
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    boolD = bool2;
                    break;
                case 2:
                    Contents contents3 = (Contents) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Contents.CREATOR);
                    iG = i;
                    boolD = bool;
                    contents = contents3;
                    break;
                case 3:
                    boolD = com.google.android.gms.common.internal.safeparcel.a.d(parcel, iF);
                    contents = contents2;
                    iG = i;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    boolD = bool;
                    contents = contents2;
                    iG = i;
                    break;
            }
            i = iG;
            contents2 = contents;
            bool = boolD;
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new CloseContentsRequest(i, contents2, bool);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bo, reason: merged with bridge method [inline-methods] */
    public CloseContentsRequest[] newArray(int i) {
        return new CloseContentsRequest[i];
    }
}
