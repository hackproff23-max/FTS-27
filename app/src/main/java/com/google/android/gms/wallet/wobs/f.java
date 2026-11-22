package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public final class f implements SafeParcelable {
    public static final Parcelable.Creator<f> CREATOR = new i();
    private final int CK;
    l avm;
    g awM;
    String label;
    String type;

    f() {
        this.CK = 1;
    }

    f(int i, String str, g gVar, String str2, l lVar) {
        this.CK = i;
        this.label = str;
        this.awM = gVar;
        this.type = str2;
        this.avm = lVar;
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
        i.a(this, dest, flags);
    }
}
