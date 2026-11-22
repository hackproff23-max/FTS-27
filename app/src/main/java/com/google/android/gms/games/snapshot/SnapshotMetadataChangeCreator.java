package com.google.android.gms.games.snapshot;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

/* JADX INFO: loaded from: classes.dex */
public class SnapshotMetadataChangeCreator implements Parcelable.Creator<SnapshotMetadataChangeEntity> {
    static void a(SnapshotMetadataChangeEntity snapshotMetadataChangeEntity, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.a(parcel, 1, snapshotMetadataChangeEntity.getDescription(), false);
        b.c(parcel, 1000, snapshotMetadataChangeEntity.getVersionCode());
        b.a(parcel, 2, snapshotMetadataChangeEntity.getPlayedTimeMillis(), false);
        b.a(parcel, 4, (Parcelable) snapshotMetadataChangeEntity.getCoverImageUri(), i, false);
        b.a(parcel, 5, (Parcelable) snapshotMetadataChangeEntity.mT(), i, false);
        b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cE, reason: merged with bridge method [inline-methods] */
    public SnapshotMetadataChangeEntity createFromParcel(Parcel parcel) {
        Uri uri = null;
        int iG = a.G(parcel);
        int iG2 = 0;
        com.google.android.gms.common.data.a aVar = null;
        Long lJ = null;
        String strO = null;
        while (parcel.dataPosition() < iG) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    strO = a.o(parcel, iF);
                    break;
                case 2:
                    lJ = a.j(parcel, iF);
                    break;
                case 4:
                    uri = (Uri) a.a(parcel, iF, Uri.CREATOR);
                    break;
                case 5:
                    aVar = (com.google.android.gms.common.data.a) a.a(parcel, iF, com.google.android.gms.common.data.a.CREATOR);
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
        return new SnapshotMetadataChangeEntity(iG2, strO, lJ, aVar, uri);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: er, reason: merged with bridge method [inline-methods] */
    public SnapshotMetadataChangeEntity[] newArray(int i) {
        return new SnapshotMetadataChangeEntity[i];
    }
}
