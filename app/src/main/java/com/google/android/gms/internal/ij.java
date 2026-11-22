package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class ij implements SafeParcelable {
    public static final Parcelable.Creator<ij> CREATOR = new ik();
    private final int CK;
    private String Hd;

    public ij() {
        this(1, null);
    }

    ij(int i, String str) {
        this.CK = i;
        this.Hd = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ij) {
            return in.a(this.Hd, ((ij) obj).Hd);
        }
        return false;
    }

    public String fT() {
        return this.Hd;
    }

    public int getVersionCode() {
        return this.CK;
    }

    public int hashCode() {
        return jv.hashCode(this.Hd);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        ik.a(this, out, flags);
    }
}
