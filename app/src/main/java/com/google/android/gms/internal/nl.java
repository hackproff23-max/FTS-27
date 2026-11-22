package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class nl implements SafeParcelable {
    private final int CK;
    LocationRequest We;
    boolean ahh;
    boolean ahi;
    boolean ahj;
    List<nd> ahk;
    final String mTag;
    static final List<nd> ahg = Collections.emptyList();
    public static final nm CREATOR = new nm();

    nl(int i, LocationRequest locationRequest, boolean z, boolean z2, boolean z3, List<nd> list, String str) {
        this.CK = i;
        this.We = locationRequest;
        this.ahh = z;
        this.ahi = z2;
        this.ahj = z3;
        this.ahk = list;
        this.mTag = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (!(other instanceof nl)) {
            return false;
        }
        nl nlVar = (nl) other;
        return jv.equal(this.We, nlVar.We) && this.ahh == nlVar.ahh && this.ahi == nlVar.ahi && this.ahj == nlVar.ahj && jv.equal(this.ahk, nlVar.ahk);
    }

    int getVersionCode() {
        return this.CK;
    }

    public int hashCode() {
        return this.We.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.We.toString());
        sb.append(" requestNlpDebugInfo=");
        sb.append(this.ahh);
        sb.append(" restorePendingIntentListeners=");
        sb.append(this.ahi);
        sb.append(" triggerUpdate=");
        sb.append(this.ahj);
        sb.append(" clients=");
        sb.append(this.ahk);
        if (this.mTag != null) {
            sb.append(" tag=");
            sb.append(this.mTag);
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        nm.a(this, parcel, flags);
    }
}
