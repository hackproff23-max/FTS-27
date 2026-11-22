package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class at implements SafeParcelable {
    public static final Parcelable.Creator<at> CREATOR = new au();
    public final int ayd;
    public final int statusCode;
    public final int versionCode;

    at(int i, int i2, int i3) {
        this.versionCode = i;
        this.statusCode = i2;
        this.ayd = i3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        au.a(this, dest, flags);
    }
}
