package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class OnDownloadProgressResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnDownloadProgressResponse> CREATOR = new an();
    final int CK;
    final long QL;
    final long QM;

    OnDownloadProgressResponse(int versionCode, long bytesLoaded, long bytesExpected) {
        this.CK = versionCode;
        this.QL = bytesLoaded;
        this.QM = bytesExpected;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long iO() {
        return this.QL;
    }

    public long iP() {
        return this.QM;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        an.a(this, dest, flags);
    }
}
