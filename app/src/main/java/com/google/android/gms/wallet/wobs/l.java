package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public final class l implements SafeParcelable {
    public static final Parcelable.Creator<l> CREATOR = new m();
    private final int CK;
    long awU;
    long awV;

    l() {
        this.CK = 1;
    }

    l(int i, long j, long j2) {
        this.CK = i;
        this.awU = j;
        this.awV = j2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.CK;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        m.a(this, dest, flags);
    }
}
