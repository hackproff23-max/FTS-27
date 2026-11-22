package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AccountChangeEventsResponseCreator implements Parcelable.Creator<AccountChangeEventsResponse> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void a(AccountChangeEventsResponse accountChangeEventsResponse, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.c(parcel, 1, accountChangeEventsResponse.Ef);
        b.c(parcel, 2, accountChangeEventsResponse.mp, false);
        b.H(parcel, iH);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public AccountChangeEventsResponse createFromParcel(Parcel parcel) {
        int iG = a.G(parcel);
        int iG2 = 0;
        ArrayList arrayListC = null;
        while (parcel.dataPosition() < iG) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    iG2 = a.g(parcel, iF);
                    break;
                case 2:
                    arrayListC = a.c(parcel, iF, AccountChangeEvent.CREATOR);
                    break;
                default:
                    a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new AccountChangeEventsResponse(iG2, arrayListC);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public AccountChangeEventsResponse[] newArray(int size) {
        return new AccountChangeEventsResponse[size];
    }
}
