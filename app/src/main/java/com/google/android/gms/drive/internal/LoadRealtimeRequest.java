package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

/* JADX INFO: loaded from: classes.dex */
public class LoadRealtimeRequest implements SafeParcelable {
    public static final Parcelable.Creator<LoadRealtimeRequest> CREATOR = new aj();
    final int CK;
    final DriveId Oj;
    final boolean QF;

    LoadRealtimeRequest(int versionCode, DriveId driveId, boolean useTestMode) {
        this.CK = versionCode;
        this.Oj = driveId;
        this.QF = useTestMode;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        aj.a(this, dest, flags);
    }
}
