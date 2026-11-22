package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public final class pw implements SafeParcelable {
    public static final Parcelable.Creator<pw> CREATOR = new px();
    private final int CK;
    int[] avX;

    pw() {
        this(1, null);
    }

    pw(int i, int[] iArr) {
        this.CK = i;
        this.avX = iArr;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.CK;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        px.a(this, out, flags);
    }
}
