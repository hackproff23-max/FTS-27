package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class ap implements SafeParcelable {
    public static final Parcelable.Creator<ap> CREATOR = new aq();
    public final m axM;
    public final int statusCode;
    public final int versionCode;

    ap(int i, int i2, m mVar) {
        this.versionCode = i;
        this.statusCode = i2;
        this.axM = mVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        aq.a(this, dest, flags);
    }
}
