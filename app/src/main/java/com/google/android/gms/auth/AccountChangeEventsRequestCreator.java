package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

/* JADX INFO: loaded from: classes.dex */
public class AccountChangeEventsRequestCreator implements Parcelable.Creator<AccountChangeEventsRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void a(AccountChangeEventsRequest accountChangeEventsRequest, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.c(parcel, 1, accountChangeEventsRequest.Ef);
        b.c(parcel, 2, accountChangeEventsRequest.Ei);
        b.a(parcel, 3, accountChangeEventsRequest.DZ, false);
        b.H(parcel, iH);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public AccountChangeEventsRequest createFromParcel(Parcel parcel) {
        int iG = 0;
        int iG2 = a.G(parcel);
        String strO = null;
        int iG3 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    iG3 = a.g(parcel, iF);
                    break;
                case 2:
                    iG = a.g(parcel, iF);
                    break;
                case 3:
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
        return new AccountChangeEventsRequest(iG3, iG, strO);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public AccountChangeEventsRequest[] newArray(int size) {
        return new AccountChangeEventsRequest[size];
    }
}
