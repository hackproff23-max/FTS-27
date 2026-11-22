package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jv;
import com.google.android.gms.internal.jx;
import com.google.android.gms.internal.mg;

/* JADX INFO: loaded from: classes.dex */
public final class a implements SafeParcelable {
    private final int CK;
    private final String CS;
    private final String TU;
    private final String TV;
    public static final a TT = new a(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE, String.valueOf(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE), null);
    public static final Parcelable.Creator<a> CREATOR = new b();

    a(int i, String str, String str2, String str3) {
        this.CK = i;
        this.CS = (String) jx.i(str);
        this.TU = "";
        this.TV = str3;
    }

    public a(String str, String str2, String str3) {
        this(1, str, "", str3);
    }

    private boolean a(a aVar) {
        return this.CS.equals(aVar.CS) && jv.equal(this.TU, aVar.TU) && jv.equal(this.TV, aVar.TV);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof a) && a((a) that));
    }

    public String getPackageName() {
        return this.CS;
    }

    public String getVersion() {
        return this.TU;
    }

    int getVersionCode() {
        return this.CK;
    }

    public int hashCode() {
        return jv.hashCode(this.CS, this.TU, this.TV);
    }

    public String jk() {
        return this.TV;
    }

    a jl() {
        return new a(mg.bw(this.CS), mg.bw(this.TU), mg.bw(this.TV));
    }

    public String toString() {
        return String.format("Application{%s:%s:%s}", this.CS, this.TU, this.TV);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        b.a(this, parcel, flags);
    }
}
