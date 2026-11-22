package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class DrivePreferences implements SafeParcelable {
    public static final Parcelable.Creator<DrivePreferences> CREATOR = new d();
    final int CK;
    final boolean Ox;

    DrivePreferences(int versionCode, boolean syncOverWifiOnly) {
        this.CK = versionCode;
        this.Ox = syncOverWifiOnly;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        d.a(this, parcel, flags);
    }
}
