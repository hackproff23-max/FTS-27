package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.Contents;

/* JADX INFO: loaded from: classes.dex */
public class al implements Parcelable.Creator<OnContentsResponse> {
    static void a(OnContentsResponse onContentsResponse, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, onContentsResponse.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) onContentsResponse.PW, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, onContentsResponse.QJ);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: aq, reason: merged with bridge method [inline-methods] */
    public OnContentsResponse createFromParcel(Parcel parcel) {
        boolean zC;
        Contents contents;
        int iG;
        boolean z = false;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        Contents contents2 = null;
        int i = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    boolean z2 = z;
                    contents = contents2;
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    zC = z2;
                    break;
                case 2:
                    Contents contents3 = (Contents) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Contents.CREATOR);
                    iG = i;
                    zC = z;
                    contents = contents3;
                    break;
                case 3:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    contents = contents2;
                    iG = i;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    zC = z;
                    contents = contents2;
                    iG = i;
                    break;
            }
            i = iG;
            contents2 = contents;
            z = zC;
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new OnContentsResponse(i, contents2, z);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bF, reason: merged with bridge method [inline-methods] */
    public OnContentsResponse[] newArray(int i) {
        return new OnContentsResponse[i];
    }
}
