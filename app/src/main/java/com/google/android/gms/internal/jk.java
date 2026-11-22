package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;

/* JADX INFO: loaded from: classes.dex */
public class jk implements Parcelable.Creator<jj> {
    static void a(jj jjVar, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, jjVar.version);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, jjVar.MD);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, jjVar.ME);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, jjVar.MF, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, jjVar.MG, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, (Parcelable[]) jjVar.MH, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, jjVar.MI, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
    public jj createFromParcel(Parcel parcel) {
        int iG = 0;
        Bundle bundleQ = null;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        Scope[] scopeArr = null;
        IBinder iBinderP = null;
        String strO = null;
        int iG3 = 0;
        int iG4 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 3:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 4:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 5:
                    iBinderP = com.google.android.gms.common.internal.safeparcel.a.p(parcel, iF);
                    break;
                case 6:
                    scopeArr = (Scope[]) com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF, Scope.CREATOR);
                    break;
                case 7:
                    bundleQ = com.google.android.gms.common.internal.safeparcel.a.q(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new jj(iG4, iG3, iG, strO, iBinderP, scopeArr, bundleQ);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: aC, reason: merged with bridge method [inline-methods] */
    public jj[] newArray(int i) {
        return new jj[i];
    }
}
