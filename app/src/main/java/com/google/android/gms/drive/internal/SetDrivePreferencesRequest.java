package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DrivePreferences;

/* JADX INFO: loaded from: classes.dex */
public class SetDrivePreferencesRequest implements SafeParcelable {
    public static final Parcelable.Creator<SetDrivePreferencesRequest> CREATOR = new bd();
    final int CK;
    final DrivePreferences QN;

    SetDrivePreferencesRequest(int versionCode, DrivePreferences prefs) {
        this.CK = versionCode;
        this.QN = prefs;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        bd.a(this, dest, flags);
    }
}
