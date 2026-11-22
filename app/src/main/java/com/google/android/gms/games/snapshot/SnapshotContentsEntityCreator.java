package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.Contents;

/* JADX INFO: loaded from: classes.dex */
public class SnapshotContentsEntityCreator implements Parcelable.Creator<SnapshotContentsEntity> {
    static void a(SnapshotContentsEntity snapshotContentsEntity, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.a(parcel, 1, (Parcelable) snapshotContentsEntity.ir(), i, false);
        b.c(parcel, 1000, snapshotContentsEntity.getVersionCode());
        b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cD, reason: merged with bridge method [inline-methods] */
    public SnapshotContentsEntity createFromParcel(Parcel parcel) {
        int iG = a.G(parcel);
        int iG2 = 0;
        Contents contents = null;
        while (parcel.dataPosition() < iG) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    contents = (Contents) a.a(parcel, iF, Contents.CREATOR);
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
        return new SnapshotContentsEntity(iG2, contents);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: eq, reason: merged with bridge method [inline-methods] */
    public SnapshotContentsEntity[] newArray(int i) {
        return new SnapshotContentsEntity[i];
    }
}
