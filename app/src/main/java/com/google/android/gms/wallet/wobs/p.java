package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public final class p implements SafeParcelable {
    public static final Parcelable.Creator<p> CREATOR = new q();
    private final int CK;
    String awT;
    l awX;
    n awY;
    n awZ;
    String tU;

    p() {
        this.CK = 1;
    }

    p(int i, String str, String str2, l lVar, n nVar, n nVar2) {
        this.CK = i;
        this.awT = str;
        this.tU = str2;
        this.awX = lVar;
        this.awY = nVar;
        this.awZ = nVar2;
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
        q.a(this, dest, flags);
    }
}
