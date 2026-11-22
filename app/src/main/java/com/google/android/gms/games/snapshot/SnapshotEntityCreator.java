package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

/* JADX INFO: loaded from: classes.dex */
public class SnapshotEntityCreator implements Parcelable.Creator<SnapshotEntity> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void a(SnapshotEntity snapshotEntity, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.a(parcel, 1, (Parcelable) snapshotEntity.getMetadata(), i, false);
        b.c(parcel, 1000, snapshotEntity.getVersionCode());
        b.a(parcel, 3, (Parcelable) snapshotEntity.getSnapshotContents(), i, false);
        b.H(parcel, iH);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public SnapshotEntity createFromParcel(Parcel parcel) {
        SnapshotContentsEntity snapshotContentsEntity;
        SnapshotMetadataEntity snapshotMetadataEntity;
        int iG;
        SnapshotContentsEntity snapshotContentsEntity2 = null;
        int iG2 = a.G(parcel);
        int i = 0;
        SnapshotMetadataEntity snapshotMetadataEntity2 = null;
        while (parcel.dataPosition() < iG2) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    SnapshotMetadataEntity snapshotMetadataEntity3 = (SnapshotMetadataEntity) a.a(parcel, iF, SnapshotMetadataEntity.CREATOR);
                    iG = i;
                    snapshotContentsEntity = snapshotContentsEntity2;
                    snapshotMetadataEntity = snapshotMetadataEntity3;
                    break;
                case 3:
                    snapshotContentsEntity = (SnapshotContentsEntity) a.a(parcel, iF, SnapshotContentsEntity.CREATOR);
                    snapshotMetadataEntity = snapshotMetadataEntity2;
                    iG = i;
                    break;
                case 1000:
                    SnapshotContentsEntity snapshotContentsEntity3 = snapshotContentsEntity2;
                    snapshotMetadataEntity = snapshotMetadataEntity2;
                    iG = a.g(parcel, iF);
                    snapshotContentsEntity = snapshotContentsEntity3;
                    break;
                default:
                    a.b(parcel, iF);
                    snapshotContentsEntity = snapshotContentsEntity2;
                    snapshotMetadataEntity = snapshotMetadataEntity2;
                    iG = i;
                    break;
            }
            i = iG;
            snapshotMetadataEntity2 = snapshotMetadataEntity;
            snapshotContentsEntity2 = snapshotContentsEntity;
        }
        if (parcel.dataPosition() != iG2) {
            throw new a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new SnapshotEntity(i, snapshotMetadataEntity2, snapshotContentsEntity2);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public SnapshotEntity[] newArray(int size) {
        return new SnapshotEntity[size];
    }
}
