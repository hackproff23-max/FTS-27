package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public final class py implements SafeParcelable {
    public static final Parcelable.Creator<py> CREATOR = new pz();
    private final int CK;
    String[] avY;
    byte[][] avZ;

    py() {
        this(1, new String[0], new byte[0][]);
    }

    py(int i, String[] strArr, byte[][] bArr) {
        this.CK = i;
        this.avY = strArr;
        this.avZ = bArr;
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
        pz.a(this, out, flags);
    }
}
