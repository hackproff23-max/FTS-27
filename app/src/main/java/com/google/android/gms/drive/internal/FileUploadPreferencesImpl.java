package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.FileUploadPreferences;

/* JADX INFO: loaded from: classes.dex */
public final class FileUploadPreferencesImpl implements SafeParcelable, FileUploadPreferences {
    public static final Parcelable.Creator<FileUploadPreferencesImpl> CREATOR = new ab();
    final int CK;
    int QA;
    boolean QB;
    int Qz;

    FileUploadPreferencesImpl(int versionCode, int networkTypePreference, int batteryUsagePreference, boolean allowRoaming) {
        this.CK = versionCode;
        this.Qz = networkTypePreference;
        this.QA = batteryUsagePreference;
        this.QB = allowRoaming;
    }

    public static boolean by(int i) {
        switch (i) {
            case 1:
            case 2:
                return true;
            default:
                return false;
        }
    }

    public static boolean bz(int i) {
        switch (i) {
            case 256:
            case FileUploadPreferences.BATTERY_USAGE_CHARGING_ONLY /* 257 */:
                return true;
            default:
                return false;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.drive.FileUploadPreferences
    public int getBatteryUsagePreference() {
        if (bz(this.QA)) {
            return this.QA;
        }
        return 0;
    }

    @Override // com.google.android.gms.drive.FileUploadPreferences
    public int getNetworkTypePreference() {
        if (by(this.Qz)) {
            return this.Qz;
        }
        return 0;
    }

    @Override // com.google.android.gms.drive.FileUploadPreferences
    public boolean isRoamingAllowed() {
        return this.QB;
    }

    @Override // com.google.android.gms.drive.FileUploadPreferences
    public void setBatteryUsagePreference(int batteryUsagePreference) {
        if (!bz(batteryUsagePreference)) {
            throw new IllegalArgumentException("Invalid battery usage preference value.");
        }
        this.QA = batteryUsagePreference;
    }

    @Override // com.google.android.gms.drive.FileUploadPreferences
    public void setNetworkTypePreference(int networkTypePreference) {
        if (!by(networkTypePreference)) {
            throw new IllegalArgumentException("Invalid data connection preference value.");
        }
        this.Qz = networkTypePreference;
    }

    @Override // com.google.android.gms.drive.FileUploadPreferences
    public void setRoamingAllowed(boolean allowRoaming) {
        this.QB = allowRoaming;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        ab.a(this, parcel, flags);
    }
}
