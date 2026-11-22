package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class b implements Parcelable.Creator<ParcelableEvent> {
    static void a(ParcelableEvent parcelableEvent, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, parcelableEvent.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, parcelableEvent.vZ, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, parcelableEvent.SJ, false);
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 4, parcelableEvent.SQ, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, parcelableEvent.SR);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, parcelableEvent.SM, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, parcelableEvent.SS, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, (Parcelable) parcelableEvent.ST, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, (Parcelable) parcelableEvent.SU, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, (Parcelable) parcelableEvent.SV, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, (Parcelable) parcelableEvent.SW, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 12, (Parcelable) parcelableEvent.SX, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 13, (Parcelable) parcelableEvent.SY, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 14, (Parcelable) parcelableEvent.SZ, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 15, (Parcelable) parcelableEvent.Ta, i, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bh, reason: merged with bridge method [inline-methods] */
    public ParcelableEvent createFromParcel(Parcel parcel) {
        int iG = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int iG2 = 0;
        String strO = null;
        String strO2 = null;
        ArrayList<String> arrayListC = null;
        boolean zC = false;
        String strO3 = null;
        String strO4 = null;
        TextInsertedDetails textInsertedDetails = null;
        TextDeletedDetails textDeletedDetails = null;
        ValuesAddedDetails valuesAddedDetails = null;
        ValuesRemovedDetails valuesRemovedDetails = null;
        ValuesSetDetails valuesSetDetails = null;
        ValueChangedDetails valueChangedDetails = null;
        ReferenceShiftedDetails referenceShiftedDetails = null;
        ObjectChangedDetails objectChangedDetails = null;
        while (parcel.dataPosition() < iG) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 3:
                    strO2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 4:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.C(parcel, iF);
                    break;
                case 5:
                    zC = com.google.android.gms.common.internal.safeparcel.a.c(parcel, iF);
                    break;
                case 6:
                    strO3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 7:
                    strO4 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 8:
                    textInsertedDetails = (TextInsertedDetails) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, TextInsertedDetails.CREATOR);
                    break;
                case 9:
                    textDeletedDetails = (TextDeletedDetails) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, TextDeletedDetails.CREATOR);
                    break;
                case 10:
                    valuesAddedDetails = (ValuesAddedDetails) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, ValuesAddedDetails.CREATOR);
                    break;
                case 11:
                    valuesRemovedDetails = (ValuesRemovedDetails) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, ValuesRemovedDetails.CREATOR);
                    break;
                case 12:
                    valuesSetDetails = (ValuesSetDetails) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, ValuesSetDetails.CREATOR);
                    break;
                case 13:
                    valueChangedDetails = (ValueChangedDetails) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, ValueChangedDetails.CREATOR);
                    break;
                case 14:
                    referenceShiftedDetails = (ReferenceShiftedDetails) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, ReferenceShiftedDetails.CREATOR);
                    break;
                case 15:
                    objectChangedDetails = (ObjectChangedDetails) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, ObjectChangedDetails.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new ParcelableEvent(iG2, strO, strO2, arrayListC, zC, strO3, strO4, textInsertedDetails, textDeletedDetails, valuesAddedDetails, valuesRemovedDetails, valuesSetDetails, valueChangedDetails, referenceShiftedDetails, objectChangedDetails);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cx, reason: merged with bridge method [inline-methods] */
    public ParcelableEvent[] newArray(int i) {
        return new ParcelableEvent[i];
    }
}
