package com.google.android.gms.games.event;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.PlayerEntity;

/* JADX INFO: loaded from: classes.dex */
public class EventEntityCreator implements Parcelable.Creator<EventEntity> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void a(EventEntity eventEntity, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.a(parcel, 1, eventEntity.getEventId(), false);
        b.c(parcel, 1000, eventEntity.getVersionCode());
        b.a(parcel, 2, eventEntity.getName(), false);
        b.a(parcel, 3, eventEntity.getDescription(), false);
        b.a(parcel, 4, (Parcelable) eventEntity.getIconImageUri(), i, false);
        b.a(parcel, 5, eventEntity.getIconImageUrl(), false);
        b.a(parcel, 6, (Parcelable) eventEntity.getPlayer(), i, false);
        b.a(parcel, 7, eventEntity.getValue());
        b.a(parcel, 8, eventEntity.getFormattedValue(), false);
        b.a(parcel, 9, eventEntity.isVisible());
        b.H(parcel, iH);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public EventEntity createFromParcel(Parcel parcel) {
        boolean zC = false;
        String strO = null;
        int iG = a.G(parcel);
        long jI = 0;
        PlayerEntity playerEntity = null;
        String strO2 = null;
        Uri uri = null;
        String strO3 = null;
        String strO4 = null;
        String strO5 = null;
        int iG2 = 0;
        while (parcel.dataPosition() < iG) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    strO5 = a.o(parcel, iF);
                    break;
                case 2:
                    strO4 = a.o(parcel, iF);
                    break;
                case 3:
                    strO3 = a.o(parcel, iF);
                    break;
                case 4:
                    uri = (Uri) a.a(parcel, iF, Uri.CREATOR);
                    break;
                case 5:
                    strO2 = a.o(parcel, iF);
                    break;
                case 6:
                    playerEntity = (PlayerEntity) a.a(parcel, iF, PlayerEntity.CREATOR);
                    break;
                case 7:
                    jI = a.i(parcel, iF);
                    break;
                case 8:
                    strO = a.o(parcel, iF);
                    break;
                case 9:
                    zC = a.c(parcel, iF);
                    break;
                case 1000:
                    iG2 = a.g(parcel, iF);
                    break;
                default:
                    a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new EventEntity(iG2, strO5, strO4, strO3, uri, strO2, playerEntity, jI, strO, zC);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public EventEntity[] newArray(int size) {
        return new EventEntity[size];
    }
}
