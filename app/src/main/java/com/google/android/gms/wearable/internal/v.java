package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class v implements SafeParcelable {
    public static final Parcelable.Creator<v> CREATOR = new w();
    public final List<al> axL;
    public final int statusCode;
    public final int versionCode;

    v(int i, int i2, List<al> list) {
        this.versionCode = i;
        this.statusCode = i2;
        this.axL = list;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        w.a(this, dest, flags);
    }
}
