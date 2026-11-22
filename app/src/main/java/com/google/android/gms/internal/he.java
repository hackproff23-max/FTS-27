package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class he implements Parcelable.Creator<hd> {
    static void a(hd hdVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable[]) hdVar.CL, i, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, hdVar.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, hdVar.CM, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, hdVar.CN);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, (Parcelable) hdVar.account, i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: J, reason: merged with bridge method [inline-methods] */
    public hd[] newArray(int i) {
        return new hd[i];
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public hd createFromParcel(Parcel parcel) {
        boolean zC = false;
        Account account = null;
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        String strO = null;
        hh[] hhVarArr = null;
        int iG2 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    hhVarArr = (hh[]) com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF, hh.CREATOR);
                    break;
                case 2:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 3:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 4:
                    account = (Account) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, Account.CREATOR);
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
        return new hd(iG2, hhVarArr, strO, zC, account);
    }
}
