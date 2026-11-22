package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class ParcelableIndexReference implements SafeParcelable {
    public static final Parcelable.Creator<ParcelableIndexReference> CREATOR = new q();
    final int CK;
    final String SM;
    final boolean SN;
    final int mIndex;

    ParcelableIndexReference(int versionCode, String objectId, int index, boolean canBeDeleted) {
        this.CK = versionCode;
        this.SM = objectId;
        this.mIndex = index;
        this.SN = canBeDeleted;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        q.a(this, dest, flags);
    }
}
