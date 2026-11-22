package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jx;

/* JADX INFO: loaded from: classes.dex */
public final class Scope implements SafeParcelable {
    public static final Parcelable.Creator<Scope> CREATOR = new e();
    final int CK;
    private final String Kv;

    Scope(int versionCode, String scopeUri) {
        jx.b(scopeUri, (Object) "scopeUri must not be null or empty");
        this.CK = versionCode;
        this.Kv = scopeUri;
    }

    public Scope(String scopeUri) {
        this(1, scopeUri);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Scope) {
            return this.Kv.equals(((Scope) o).Kv);
        }
        return false;
    }

    public String gO() {
        return this.Kv;
    }

    public int hashCode() {
        return this.Kv.hashCode();
    }

    public String toString() {
        return this.Kv;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        e.a(this, dest, flags);
    }
}
