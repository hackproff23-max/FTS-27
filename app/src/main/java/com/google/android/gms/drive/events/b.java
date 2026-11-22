package com.google.android.gms.drive.events;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class b implements Parcelable.Creator<CompletionEvent> {
    static void a(CompletionEvent completionEvent, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, completionEvent.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) completionEvent.Oj, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, completionEvent.DZ, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, (Parcelable) completionEvent.OY, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, (Parcelable) completionEvent.OZ, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, (Parcelable) completionEvent.Pa, i, false);
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 7, completionEvent.Pb, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 8, completionEvent.FP);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, completionEvent.Pc, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: Y, reason: merged with bridge method [inline-methods] */
    public CompletionEvent createFromParcel(Parcel parcel) {
        int iG = 0;
        IBinder iBinderP = null;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        ArrayList<String> arrayListC = null;
        MetadataBundle metadataBundle = null;
        ParcelFileDescriptor parcelFileDescriptor = null;
        ParcelFileDescriptor parcelFileDescriptor2 = null;
        String strO = null;
        DriveId driveId = null;
        int iG3 = 0;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    iG3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 2:
                    driveId = (DriveId) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, DriveId.CREATOR);
                    break;
                case 3:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    break;
                case 4:
                    parcelFileDescriptor2 = (ParcelFileDescriptor) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, ParcelFileDescriptor.CREATOR);
                    break;
                case 5:
                    parcelFileDescriptor = (ParcelFileDescriptor) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, ParcelFileDescriptor.CREATOR);
                    break;
                case 6:
                    metadataBundle = (MetadataBundle) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, MetadataBundle.CREATOR);
                    break;
                case 7:
                    arrayListC = com.google.android.gms.common.internal.safeparcel.a.C(parcel, iF);
                    break;
                case 8:
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    break;
                case 9:
                    iBinderP = com.google.android.gms.common.internal.safeparcel.a.p(parcel, iF);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new CompletionEvent(iG3, driveId, strO, parcelFileDescriptor2, parcelFileDescriptor, metadataBundle, arrayListC, iG, iBinderP);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: bg, reason: merged with bridge method [inline-methods] */
    public CompletionEvent[] newArray(int i) {
        return new CompletionEvent[i];
    }
}
