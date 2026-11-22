package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

/* JADX INFO: loaded from: classes.dex */
public class AccountChangeEventCreator implements Parcelable.Creator<AccountChangeEvent> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void a(AccountChangeEvent accountChangeEvent, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.c(parcel, 1, accountChangeEvent.Ef);
        b.a(parcel, 2, accountChangeEvent.Eg);
        b.a(parcel, 3, accountChangeEvent.DZ, false);
        b.c(parcel, 4, accountChangeEvent.Eh);
        b.c(parcel, 5, accountChangeEvent.Ei);
        b.a(parcel, 6, accountChangeEvent.Ej, false);
        b.H(parcel, iH);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public AccountChangeEvent createFromParcel(Parcel parcel) {
        String strO = null;
        int iG = 0;
        int iG2 = a.G(parcel);
        long jI = 0;
        int iG3 = 0;
        String strO2 = null;
        int iG4 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    iG4 = a.g(parcel, iF);
                    break;
                case 2:
                    jI = a.i(parcel, iF);
                    break;
                case 3:
                    strO2 = a.o(parcel, iF);
                    break;
                case 4:
                    iG3 = a.g(parcel, iF);
                    break;
                case 5:
                    iG = a.g(parcel, iF);
                    break;
                case 6:
                    strO = a.o(parcel, iF);
                    break;
                default:
                    a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new AccountChangeEvent(iG4, jI, strO2, iG3, iG, strO);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public AccountChangeEvent[] newArray(int size) {
        return new AccountChangeEvent[size];
    }
}
