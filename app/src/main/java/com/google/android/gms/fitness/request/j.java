package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jv;

/* JADX INFO: loaded from: classes.dex */
public class j implements SafeParcelable {
    public static final Parcelable.Creator<j> CREATOR = new k();
    private final int CK;
    private final String mName;

    j(int i, String str) {
        this.CK = i;
        this.mName = str;
    }

    public j(String str) {
        this.CK = 1;
        this.mName = str;
    }

    private boolean a(j jVar) {
        return jv.equal(this.mName, jVar.mName);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return o == this || ((o instanceof j) && a((j) o));
    }

    public String getName() {
        return this.mName;
    }

    int getVersionCode() {
        return this.CK;
    }

    public int hashCode() {
        return jv.hashCode(this.mName);
    }

    public String toString() {
        return jv.h(this).a("name", this.mName).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        k.a(this, dest, flags);
    }
}
