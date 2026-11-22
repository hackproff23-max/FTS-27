package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class an implements SafeParcelable {
    public static final Parcelable.Creator<an> CREATOR = new ao();
    public final long ayc;
    public final String label;
    public final String packageName;
    public final int versionCode;

    an(int i, String str, String str2, long j) {
        this.versionCode = i;
        this.packageName = str;
        this.label = str2;
        this.ayc = j;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        ao.a(this, out, flags);
    }
}
