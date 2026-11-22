package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.CompletionEvent;

/* JADX INFO: loaded from: classes.dex */
public class aq implements Parcelable.Creator<OnEventResponse> {
    static void a(OnEventResponse onEventResponse, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, onEventResponse.CK);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, onEventResponse.Pm);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, (Parcelable) onEventResponse.QO, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, (Parcelable) onEventResponse.QP, i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: av, reason: merged with bridge method [inline-methods] */
    public OnEventResponse createFromParcel(Parcel parcel) {
        CompletionEvent completionEvent;
        ChangeEvent changeEvent;
        int iG;
        int iG2;
        CompletionEvent completionEvent2 = null;
        int i = 0;
        int iG3 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        ChangeEvent changeEvent2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < iG3) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    CompletionEvent completionEvent3 = completionEvent2;
                    changeEvent = changeEvent2;
                    iG = i;
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    completionEvent = completionEvent3;
                    break;
                case 2:
                    iG2 = i2;
                    ChangeEvent changeEvent3 = changeEvent2;
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    completionEvent = completionEvent2;
                    changeEvent = changeEvent3;
                    break;
                case 3:
                    iG = i;
                    iG2 = i2;
                    CompletionEvent completionEvent4 = completionEvent2;
                    changeEvent = (ChangeEvent) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, ChangeEvent.CREATOR);
                    completionEvent = completionEvent4;
                    break;
                case 4:
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    completionEvent = completionEvent2;
                    changeEvent = changeEvent2;
                    iG = i;
                    iG2 = i2;
                    break;
                case 5:
                    completionEvent = (CompletionEvent) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, CompletionEvent.CREATOR);
                    changeEvent = changeEvent2;
                    iG = i;
                    iG2 = i2;
                    break;
            }
            i2 = iG2;
            i = iG;
            changeEvent2 = changeEvent;
            completionEvent2 = completionEvent;
        }
        if (parcel.dataPosition() != iG3) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG3, parcel);
        }
        return new OnEventResponse(i2, i, changeEvent2, completionEvent2);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bK, reason: merged with bridge method [inline-methods] */
    public OnEventResponse[] newArray(int i) {
        return new OnEventResponse[i];
    }
}
