package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class OnListParentsResponse extends com.google.android.gms.drive.i implements SafeParcelable {
    public static final Parcelable.Creator<OnListParentsResponse> CREATOR = new as();
    final int CK;
    final DataHolder QR;

    OnListParentsResponse(int versionCode, DataHolder parents) {
        this.CK = versionCode;
        this.QR = parents;
    }

    @Override // com.google.android.gms.drive.i
    protected void I(Parcel parcel, int i) {
        as.a(this, parcel, i);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DataHolder iT() {
        return this.QR;
    }
}
