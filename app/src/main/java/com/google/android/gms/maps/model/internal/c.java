package com.google.android.gms.maps.model.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public final class c implements SafeParcelable {
    public static final d CREATOR = new d();
    private final int CK;
    private Bundle amK;
    private int type;

    c(int i, int i2, Bundle bundle) {
        this.CK = i;
        this.type = i2;
        this.amK = bundle;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getType() {
        return this.type;
    }

    public int getVersionCode() {
        return this.CK;
    }

    public Bundle oi() {
        return this.amK;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        d.a(this, out, flags);
    }
}
