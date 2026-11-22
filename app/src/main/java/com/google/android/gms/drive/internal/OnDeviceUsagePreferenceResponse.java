package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class OnDeviceUsagePreferenceResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnDeviceUsagePreferenceResponse> CREATOR = new am();
    final int CK;
    final FileUploadPreferencesImpl QK;

    OnDeviceUsagePreferenceResponse(int versionCode, FileUploadPreferencesImpl preferences) {
        this.CK = versionCode;
        this.QK = preferences;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FileUploadPreferencesImpl iN() {
        return this.QK;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        am.a(this, dest, flags);
    }
}
