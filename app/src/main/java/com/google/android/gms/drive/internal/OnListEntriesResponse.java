package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class OnListEntriesResponse extends com.google.android.gms.drive.i implements SafeParcelable {
    public static final Parcelable.Creator<OnListEntriesResponse> CREATOR = new ar();
    final int CK;
    final boolean PJ;
    final DataHolder QQ;

    OnListEntriesResponse(int versionCode, DataHolder entries, boolean moreEntriesMayExist) {
        this.CK = versionCode;
        this.QQ = entries;
        this.PJ = moreEntriesMayExist;
    }

    @Override // com.google.android.gms.drive.i
    protected void I(Parcel parcel, int i) {
        ar.a(this, parcel, i);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DataHolder iR() {
        return this.QQ;
    }

    public boolean iS() {
        return this.PJ;
    }
}
