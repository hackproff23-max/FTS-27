package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class StorageStats implements SafeParcelable {
    public static final Parcelable.Creator<StorageStats> CREATOR = new g();
    final int CK;
    final long OM;
    final long ON;
    final long OO;
    final long OP;
    final int OQ;

    StorageStats(int versionCode, long metadataSizeBytes, long cachedContentsSizeBytes, long pinnedItemsSizeBytes, long totalSizeBytes, int numPinnedItems) {
        this.CK = versionCode;
        this.OM = metadataSizeBytes;
        this.ON = cachedContentsSizeBytes;
        this.OO = pinnedItemsSizeBytes;
        this.OP = totalSizeBytes;
        this.OQ = numPinnedItems;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        g.a(this, out, flags);
    }
}
