package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: classes.dex */
public class OnMetadataResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnMetadataResponse> CREATOR = new at();
    final int CK;
    final MetadataBundle Px;

    OnMetadataResponse(int versionCode, MetadataBundle metadata) {
        this.CK = versionCode;
        this.Px = metadata;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MetadataBundle iU() {
        return this.Px;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        at.a(this, dest, flags);
    }
}
