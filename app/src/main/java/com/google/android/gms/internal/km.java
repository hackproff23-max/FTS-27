package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class km implements SafeParcelable {
    public static final kn CREATOR = new kn();
    private final int CK;
    private final ko NF;

    km(int i, ko koVar) {
        this.CK = i;
        this.NF = koVar;
    }

    private km(ko koVar) {
        this.CK = 1;
        this.NF = koVar;
    }

    public static km a(kr.b<?, ?> bVar) {
        if (bVar instanceof ko) {
            return new km((ko) bVar);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        kn knVar = CREATOR;
        return 0;
    }

    int getVersionCode() {
        return this.CK;
    }

    ko hF() {
        return this.NF;
    }

    public kr.b<?, ?> hG() {
        if (this.NF != null) {
            return this.NF;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        kn knVar = CREATOR;
        kn.a(this, out, flags);
    }
}
