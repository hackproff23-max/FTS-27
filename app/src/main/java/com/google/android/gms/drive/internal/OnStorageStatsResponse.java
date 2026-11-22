package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.StorageStats;

/* JADX INFO: loaded from: classes.dex */
public class OnStorageStatsResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnStorageStatsResponse> CREATOR = new aw();
    final int CK;
    StorageStats QS;

    OnStorageStatsResponse(int versionCode, StorageStats storageStats) {
        this.CK = versionCode;
        this.QS = storageStats;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        aw.a(this, dest, flags);
    }
}
