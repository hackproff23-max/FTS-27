package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class OnSyncMoreResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnSyncMoreResponse> CREATOR = new ax();
    final int CK;
    final boolean PJ;

    OnSyncMoreResponse(int versionCode, boolean moreEntriesMayExist) {
        this.CK = versionCode;
        this.PJ = moreEntriesMayExist;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        ax.a(this, dest, flags);
    }
}
