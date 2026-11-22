package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.la;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class d implements SafeParcelable {
    public static final Parcelable.Creator<d> CREATOR = new e();
    private final int CK;
    String awJ;
    String awK;
    ArrayList<b> awL;

    d() {
        this.CK = 1;
        this.awL = la.ie();
    }

    d(int i, String str, String str2, ArrayList<b> arrayList) {
        this.CK = i;
        this.awJ = str;
        this.awK = str2;
        this.awL = arrayList;
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
        e.a(this, dest, flags);
    }
}
