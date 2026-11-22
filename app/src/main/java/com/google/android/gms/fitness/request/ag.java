package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.Subscription;

/* JADX INFO: loaded from: classes.dex */
public class ag implements Parcelable.Creator<af> {
    static void a(af afVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable) afVar.kn(), i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, afVar.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, afVar.ko());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: ca, reason: merged with bridge method [inline-methods] */
    public af createFromParcel(Parcel parcel) {
        boolean zC;
        Subscription subscription;
        int iG;
        boolean z = false;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        Subscription subscription2 = null;
        int i = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    Subscription subscription3 = (Subscription) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Subscription.CREATOR);
                    iG = i;
                    zC = z;
                    subscription = subscription3;
                    break;
                case 2:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    subscription = subscription2;
                    iG = i;
                    break;
                case 1000:
                    boolean z2 = z;
                    subscription = subscription2;
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    zC = z2;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    zC = z;
                    subscription = subscription2;
                    iG = i;
                    break;
            }
            i = iG;
            subscription2 = subscription;
            z = zC;
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new af(i, subscription2, z);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dv, reason: merged with bridge method [inline-methods] */
    public af[] newArray(int i) {
        return new af[i];
    }
}
