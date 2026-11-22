package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class OnResourceIdSetResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnResourceIdSetResponse> CREATOR = new av();
    private final int CK;
    private final List<String> Po;

    OnResourceIdSetResponse(int versionCode, List<String> resourceIds) {
        this.CK = versionCode;
        this.Po = resourceIds;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.CK;
    }

    public List<String> iF() {
        return this.Po;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        av.a(this, dest, flags);
    }
}
