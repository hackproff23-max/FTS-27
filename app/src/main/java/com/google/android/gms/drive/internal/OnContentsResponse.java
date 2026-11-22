package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;

/* JADX INFO: loaded from: classes.dex */
public class OnContentsResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnContentsResponse> CREATOR = new al();
    final int CK;
    final Contents PW;
    final boolean QJ;

    OnContentsResponse(int versionCode, Contents contents, boolean outOfDate) {
        this.CK = versionCode;
        this.PW = contents;
        this.QJ = outOfDate;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Contents iL() {
        return this.PW;
    }

    public boolean iM() {
        return this.QJ;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        al.a(this, dest, flags);
    }
}
