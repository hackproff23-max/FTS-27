package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public final class InstrumentInfo implements SafeParcelable {
    public static final Parcelable.Creator<InstrumentInfo> CREATOR = new h();
    private final int CK;
    private String auX;
    private String auY;

    InstrumentInfo(int versionCode, String instrumentType, String instrumentDetails) {
        this.CK = versionCode;
        this.auX = instrumentType;
        this.auY = instrumentDetails;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getInstrumentDetails() {
        return this.auY;
    }

    public String getInstrumentType() {
        return this.auX;
    }

    public int getVersionCode() {
        return this.CK;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        h.a(this, out, flags);
    }
}
